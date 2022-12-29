package me.zeron.backTo

import io.github.monun.kommand.kommand
import me.zeron.backTo.BackKommand.backKommand
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class MainCore : JavaPlugin() {
    companion object {
        lateinit var plugin: Plugin
    }

    override fun onEnable() {
        plugin = this
        logger.info("[ BackTo ] 플러그인 활성화 중")

        kommand { backKommand(this) }

        server.pluginManager.registerEvents(DeadListener, this)
    }

    override fun onDisable() {
        logger.info("[ BackTo ] 플러그인 비활성화 중")
    }
}