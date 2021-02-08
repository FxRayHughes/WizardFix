package ray.mintcat.wizardfix

import io.izzel.taboolib.module.inject.TListener
import io.izzel.taboolib.module.locale.chatcolor.TColor
import io.izzel.taboolib.util.book.BookFormatter
import io.izzel.taboolib.util.book.builder.PageBuilder
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.inventory.ItemStack

@TListener
class Listener : Listener {


    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        WizardFix.data.set(event.player.name, event.player.uniqueId.toString())
        WizardFix.data.saveToFile()
    }

    @EventHandler
    fun onPlayerLogin(event:PlayerLoginEvent){
        WizardFix.data.set(event.player.name, event.player.uniqueId.toString())
        WizardFix.data.saveToFile()
    }

}