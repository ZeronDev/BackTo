package me.zeron.backTo

import com.github.shynixn.mccoroutine.bukkit.launch
import kotlinx.coroutines.delay
import me.zeron.backTo.Data.backCoroutine
import me.zeron.backTo.Data.canCommand
import me.zeron.backTo.Data.canCommandCountDown
import me.zeron.backTo.Data.deathLocation
import me.zeron.backTo.Data.recentDies
import me.zeron.backTo.MainCore.Companion.plugin
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

object DeadListener : Listener {
    @EventHandler
    fun onDead(e: PlayerDeathEvent) {
        recentDies.add(e.player)

        deathLocation[e.player] = e.player.location

        val counter = plugin.launch {
            repeat(20) {
                canCommandCountDown[e.player] = 20-it
                delay(1000)
            }
            canCommandCountDown.remove(e.player)
        }

        backCoroutine[e.player] = plugin.launch {
            counter.join()
            canCommand.add(e.player)
            delay(60_000)
            if (canCommand.contains(e.player)) canCommand.remove(e.player)
            delay(60_000)
            recentDies.remove(e.player)
            deathLocation.remove(e.player)
        }
    }
}