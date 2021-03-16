package ray.mintcat.wizardfix.top

import org.bukkit.Bukkit
import ray.mintcat.wizardfix.Data
import ray.mintcat.wizardfix.PlayerUtil
import ray.mintcat.wizardfix.WizardFix

object WizardTop {

    private val topList = HashMap<String, ArrayList<TopData>>()

    fun getTopJust(key: String): ArrayList<TopData>? {
        load(key)
        val dates = topList[key] ?: return null
        dates.sortBy { it.value }
        return dates
    }

    fun getTopBack(key: String): ArrayList<TopData>? {
        load(key)
        val dates = topList[key] ?: return null
        dates.sortByDescending { it.value }
        return dates
    }

    fun getInfo(type: String, key: String, top: Int, def: String): String {
        when (type) {
            "JustPlayer" -> {
                try {
                    val name = getTopJust(key)?.get(top)?.player?.name
                    return name ?: def
                } catch (e: Exception) {
                    return def

                }
            }
            "JustValue" -> {
                try {
                    val name = getTopJust(key)?.get(top)?.value
                    return name ?: def
                } catch (e: Exception) {
                    return def

                }
            }
            "BackPlayer" -> {
                try {
                    val name = getTopBack(key)?.get(top)?.player?.name
                    return name ?: def
                } catch (e: Exception) {
                    return def

                }
            }
            "BackValue" -> {
                try {
                    val name = getTopBack(key)?.get(top)?.value
                    return name ?: def
                } catch (e: Exception) {
                    return def

                }
            }
        }
        return def
    }

    private fun load(key: String) {
        val list = mutableListOf<TopData>()
        PlayerUtil.getOfflinePlayerList().forEach { player ->
            val data = Data(player).get(key, "0.0")
            list.add(TopData(player, key, data))
        }
        topList[key]?.clear()
        topList[key] = list as ArrayList<TopData>
    }
}