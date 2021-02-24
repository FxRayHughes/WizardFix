package ray.mintcat.wizardfix

import io.izzel.taboolib.module.compat.PlaceholderHook
import io.izzel.taboolib.module.inject.THook
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

@THook
class PapiHook : PlaceholderHook.Expansion {

    override fun plugin(): Plugin {
        return WizardFix.plugin
    }

    override fun identifier(): String {
        return "wizard"
    }

    override fun onPlaceholderRequest(player: Player, params: String): String {
        val param = params.split("_")
        return when (param[0]) {
            "who" -> {
                val players = WizardFix.getOfflinePlayer(param[1].replace("=","_"))
                if (players == null){
                    param[3]
                }else{
                    Data(players).get(param[2],param[3])
                }
            }
            "info" -> Data(player).get(param[1],param[2])
            "has" -> {
                val value = Data(player).get(param[1],0.0).toDouble()
                if (value >= param[2].toDouble()) {
                    param[3]
                } else {
                    param[4]
                }
            }
            "is" -> {
                val value = Data(player).get(param[1],param[2])
                if (value.equals(param[2], ignoreCase = true)) {
                    param[3]
                } else {
                    param[4]
                }
            }
            else -> {
                "N/A"
            }
        }
    }
}