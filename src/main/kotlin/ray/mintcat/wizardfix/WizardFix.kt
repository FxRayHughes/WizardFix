package ray.mintcat.wizardfix

import io.izzel.taboolib.loader.Plugin
import io.izzel.taboolib.module.config.TConfig
import io.izzel.taboolib.module.db.local.LocalFile
import io.izzel.taboolib.module.inject.TInject
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.configuration.file.FileConfiguration
import java.util.*

object WizardFix : Plugin() {

    fun getPlayerList(): List<String> {
        return data.getKeys(false).toList()
    }

    @TInject(value = ["uuids.yml"], locale = "LOCALE-PRIORITY")
    lateinit var data: TConfig
        private set

    fun getOfflinePlayer(name: String): OfflinePlayer? {
        val info = data.getString(name,"null")
        if (info == "null" || info == null){
            return null
        }
        return Bukkit.getOfflinePlayer(UUID.fromString(info))
    }


}