package ray.mintcat.wizardfix

import io.izzel.taboolib.module.db.local.LocalPlayer
import org.bukkit.OfflinePlayer
import org.bukkit.configuration.file.FileConfiguration
import java.math.RoundingMode
import java.text.DecimalFormat

class Data(val player: OfflinePlayer) {

    private val localPlayer: FileConfiguration = LocalPlayer.get(player)

    fun get(key: String, def: Any): String {
        return localPlayer.getString("Wizard.list.$key", def.toString()) ?: def.toString()
    }

    fun set(key: String, value: String) {
        localPlayer.set("Wizard.list.$key", value)
        LocalPlayer.save(player)
    }

    fun edit(key: String, symbol: String, value: String) {
        val info = get(key,"0.0").toDouble()
        val double = value.toDouble()
        val run = when (symbol) {
            "+" -> info + double
            "-" -> info - double
            "*" -> info * double
            "/" -> info / double
            "=" -> double
            else -> info + 0.0
        }
        set(key,run.formats())
    }

    fun Double.formats(): String {
        val format = DecimalFormat("0.##")
        //未保留小数的舍弃规则，RoundingMode.FLOOR表示直接舍弃。
        format.roundingMode = RoundingMode.FLOOR
        return format.format(this)
    }

}