package ray.mintcat.wizard

import org.bukkit.entity.Player
import ray.mintcat.wizardfix.Data

object WizardObject {

    fun getIntegral(player: Player, integral: Any, def: Any): String {
        return Data(player).get(integral.toString(),def.toString())
    }

    fun setIntegral(player: Player, integral: Any, value: Any) {
        Data(player).set(integral.toString(),value.toString())
    }

    fun addIntegral(player: Player, integral: Any, value: Double) {
        Data(player).edit(integral.toString(),"+",value.toString())
    }

    fun takeIntegral(player: Player, integral: Any, value: Double) {
        Data(player).edit(integral.toString(),"-",value.toString())
    }
}