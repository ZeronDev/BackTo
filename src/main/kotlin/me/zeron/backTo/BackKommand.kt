package me.zeron.backTo

import com.github.shynixn.mccoroutine.bukkit.launch
import io.github.monun.kommand.PluginKommand
import kotlinx.coroutines.delay
import me.zeron.backTo.Data.alreadyCommand
import me.zeron.backTo.Data.backCoroutine
import me.zeron.backTo.Data.canCommand
import me.zeron.backTo.Data.canCommandCountDown
import me.zeron.backTo.Data.deathLocation
import me.zeron.backTo.Data.prefix
import me.zeron.backTo.Data.recentDies
import me.zeron.backTo.MainCore.Companion.plugin
import net.kyori.adventure.text.Component.text
import org.bukkit.entity.Player

object BackKommand {
    fun backKommand(kommand: PluginKommand) {
        kommand.register("back") {
            requires { sender is Player }
            executes {
                if (recentDies.contains(player)) {
                    if (!canCommandCountDown.containsKey(player)) {
                        if (!alreadyCommand.contains(player)) {
                            if (canCommand.contains(player)) {
                                canCommand.remove(player)
                                player.sendActionBar(text("§c§l☠죽은 장소로 이동했습니다☠"))
                                player.teleport(deathLocation[player]!!)
                                plugin.launch {
                                    alreadyCommand.add(player)
                                    backCoroutine[player]?.join()
                                    alreadyCommand.remove(player)
                                }
                            } else player.sendMessage(prefix.append(text("§c1분이 지나 죽은 위치로 되돌아갈 수 없습니다")))
                        } else player.sendMessage(prefix.append(text("§c이미 죽은 위치로 한 번 돌아갔습니다")))
                    } else player.sendMessage(prefix.append(text("§c${canCommandCountDown[player]}초 후에 사용 가능합니다")))
                } else player.sendMessage(prefix.append(text("§c최근에 사망한 적이 없습니다")))
            }
        }
    }
}