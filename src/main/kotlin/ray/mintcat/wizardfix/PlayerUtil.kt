package ray.mintcat.wizardfix

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import java.util.*

object PlayerUtil {

    fun getPlayerList(): List<String> {
        return WizardFix.data.getKeys(false).toList()
    }

    fun getOfflinePlayer(name: String): OfflinePlayer? {
        val info = WizardFix.data.getString(name, "null")
        if (info == "null" || info == null) {
            return null
        }
        return Bukkit.getOfflinePlayer(UUID.fromString(info))
    }

    fun getOfflinePlayerList(): List<OfflinePlayer> {
        return getPlayerList().map { getOfflinePlayer(it)!! }
    }
}