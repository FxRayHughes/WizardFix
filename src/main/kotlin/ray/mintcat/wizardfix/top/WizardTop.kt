package ray.mintcat.wizardfix.top

import ray.mintcat.wizardfix.Data
import ray.mintcat.wizardfix.PlayerUtil

object WizardTop {
    val topList = HashMap<String, ArrayList<TopData>>()

    fun getTopJust(key: String): ArrayList<TopData>? {
        load(key)
        val dates = topList[key] ?: return null
        dates.sortBy { it.value }
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

    fun getTopBack(key: String): ArrayList<TopData>? {
        load(key)
        val dates = topList[key] ?: return null
        dates.sortByDescending { it.value }
        return dates
    }


    fun load(key: String) {
        if (topList[key]?.size ?: 0 < 1) {
            PlayerUtil.getOfflinePlayerList().forEach {
                val data = Data(it).get(key, "0.0")
                if (topList[key]?.size ?: 0 < 1) {
                    topList[key] = arrayListOf()
                }
                topList[key]!!.add(TopData(it, key, data))
            }
        }
    }
}