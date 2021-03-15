package ray.mintcat.wizardfix

import io.izzel.taboolib.TabooLibAPI
import io.izzel.taboolib.module.compat.PlaceholderHook
import io.izzel.taboolib.module.inject.THook
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import ray.mintcat.wizardfix.top.WizardTop

@THook
class NewPapiHook : PlaceholderHook.Expansion, Helper {

    override fun plugin(): Plugin {
        return WizardFix.plugin
    }

    override fun identifier(): String {
        return "wizardfix"
    }

    override fun onPlaceholderRequest(player: Player, params: String): String {
        val toPapiA = params.replace("[", "%").replace("]", "%")
        val param = TabooLibAPI.getPluginBridge().setPlaceholders(player, toPapiA).split(";").toMutableList()
        return when (param[0]) {
            "topJust" -> {
                //%wizardfix_topJust;;type;;key;;1;;%
                when (param[1]) {
                    "player" -> {
                        WizardTop.getInfo("JustPlayer", param[2], param[3].toInt(), param[4])
                    }
                    "value" -> {
                        WizardTop.getInfo("JustValue", param[2], param[3].toInt(), param[4])
                    }
                    else -> "类型错误[player or value]"
                }
            }
            "topBack" -> {
                //%wizardfix_topBack;;type;;key;;1;;%
                when (param[1]) {
                    "player" -> {
                        WizardTop.getInfo("BackPlayer", param[2], param[3].toInt(), param[4])
                    }
                    "value" -> {
                        WizardTop.getInfo("BackValue", param[2], param[3].toInt(), param[4])
                    }
                    else -> "类型错误[player or value]"
                }
            }
            "who" -> {
                val players = PlayerUtil.getOfflinePlayer(param[1].replace("=", "_"))
                if (players == null) {
                    param[3]
                } else {
                    Data(players).get(param[2], param[3])
                }
            }
            "info" -> Data(player).get(param[1], param[2])
            "has" -> {
                val value = Data(player).get(param[1], 0.0).toDouble()
                if (value >= param[2].toDouble()) {
                    param[3]
                } else {
                    param[4]
                }
            }
            "is" -> {
                val value = Data(player).get(param[1], param[2])
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