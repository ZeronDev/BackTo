package me.zeron.backTo

import kotlinx.coroutines.Job
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Location
import org.bukkit.entity.Player

object Data {
    val prefix = Component.text("[ ").append(
        Component.text("N").decorate(TextDecoration.BOLD).color(TextColor.color(0x01FC7A)).
    append(Component.text("E").decorate(TextDecoration.BOLD).color(TextColor.color(0x03FCBE))).
    append(Component.text("O").decorate(TextDecoration.BOLD).color(TextColor.color(0x02FAEA))).
    append(Component.text("N").decorate(TextDecoration.BOLD).color(TextColor.color(0x03DCFD))).
    append(Component.text(" §f] ")))

    val deathLocation: MutableMap<Player, Location> = mutableMapOf() // 죽은 위치
    val canCommandCountDown: MutableMap<Player, Int> = mutableMapOf() // 사용할 수 있을 때 까지 카운트다운
    val canCommand: MutableList<Player> = mutableListOf() // 커맨드 사용 할 수 있는 사람들
    val backCoroutine: MutableMap<Player, Job> = mutableMapOf() // 코루틴
    val recentDies: MutableList<Player> = mutableListOf() // 메시지 바뀌는 사람들
    val alreadyCommand: MutableList<Player> = mutableListOf() // 이미 쓴 사람들
}