package ray.mintcat.wizardfix

import io.izzel.taboolib.module.db.local.LocalPlayer
import org.bukkit.OfflinePlayer
import java.math.RoundingMode
import java.text.DecimalFormat

class Data(private val player: OfflinePlayer) {

    private val localPlayer = LocalPlayer.get(player)

    fun get(key: String, def: Any): String {
        return localPlayer.getString("Wizard.list.$key", def.toString()) ?: def.toString()
    }

    fun get(key: String): String? {
        return localPlayer.getString("Wizard.list.$key")
    }

    fun getKeyList(): MutableSet<String>? {
        return localPlayer.getConfigurationSection("Wizard.list")?.getKeys(false)
    }

    fun getPlayer(): OfflinePlayer {
        return this.player
    }

    //使用者请使用edit的 =
    private fun set(key: String, value: String) {
        localPlayer.set("Wizard.list.$key", value)
        LocalPlayer.save(player)
    }

    fun edit(key: String, symbol: String, value: String) {
        val info = get(key, "0.0")
        val run = when (symbol) {
            "+" -> info.toDouble() + value.toDouble()
            "-" -> info.toDouble() - value.toDouble()
            "*" -> info.toDouble() * value.toDouble()
            "/" -> info.toDouble() / value.toDouble()
            "=" -> value
            else -> value
        }
        set(key, run.formats())
    }

    private fun Any.formats(): String {
        if (this is Double) {
            val format = DecimalFormat("0.##")
            //未保留小数的舍弃规则，RoundingMode.FLOOR表示直接舍弃。
            format.roundingMode = RoundingMode.FLOOR
            return format.format(this)
        } else {
            return this.toString()
        }
    }
}
