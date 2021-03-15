package ray.mintcat.wizardfix

import io.izzel.taboolib.module.inject.TListener
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

@TListener
class Listener : Listener {


    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        WizardFix.data.set(event.player.name, event.player.uniqueId.toString())
        WizardFix.data.saveToFile()
    }


}