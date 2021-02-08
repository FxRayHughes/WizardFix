package ray.mintcat.wizardfix

import io.izzel.taboolib.TabooLibAPI
import io.izzel.taboolib.module.command.base.*
import io.izzel.taboolib.util.Features
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@BaseCommand(name = "wizardfix", aliases = ["wiz", "wizard"], permission = "*")
class Command : BaseMainCommand(), Helper {

    @SubCommand(description = "查看变量", permission = "*")
    var look = object : BaseSubCommand() {
        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("目标") { WizardFix.getPlayerList() }, Argument("变量名"))
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = WizardFix.getOfflinePlayer(args[0])
            if (player == null) {
                sender.error("玩家 &f${args[0]} &7不存在!")
                return
            }
            sender.info("玩家 &f${args[0]} &7的 &f${args[1]} &7变量为 &f${Data(player).get(args[1], "不存在")}")
        }

    }

    @SubCommand(description = "设置变量", permission = "*")
    var set = object : BaseSubCommand() {
        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("目标") { WizardFix.getPlayerList() }, Argument("变量名"), Argument("参数"))
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = WizardFix.getOfflinePlayer(args[0])
            if (player == null) {
                sender.error("玩家 &f${args[0]} &7不存在!")
                return
            }

            Data(player).set(args[1], args[2])

            Bukkit.getScheduler().runTask(WizardFix.plugin, Runnable {
                if (sender.isOp && sender is Player) {
                    sender.info("玩家 &f${args[0]} &7的 &f${args[1]} &7变量被设置为 &f${Data(player).get(args[1], "不存在")}")
                }
            })
        }
    }

    @SubCommand(description = "增加变量", permission = "*")
    var add = object : BaseSubCommand() {
        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("目标") { WizardFix.getPlayerList() }, Argument("变量名"), Argument("参数"))
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = WizardFix.getOfflinePlayer(args[0])
            if (player == null) {
                sender.error("玩家 &f${args[0]} &7不存在!")
                return
            }

            Data(player).edit(args[1], "+", args[2])

            Bukkit.getScheduler().runTask(WizardFix.plugin, Runnable {
                if (sender.isOp && sender is Player) {
                    sender.info("玩家 &f${args[0]} &7的 &f${args[1]} &7变量被设置为 &f${Data(player).get(args[1], "不存在")}")
                }
            })
        }
    }

    @SubCommand(description = "增加变量", permission = "*")
    var take = object : BaseSubCommand() {
        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("目标") { WizardFix.getPlayerList() }, Argument("变量名"), Argument("参数"))
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = WizardFix.getOfflinePlayer(args[0])
            if (player == null) {
                sender.error("玩家 &f${args[0]} &7不存在!")
                return
            }

            Data(player).edit(args[1], "-", args[2])

            Bukkit.getScheduler().runTask(WizardFix.plugin, Runnable {
                if (sender.isOp && sender is Player) {
                    sender.info("玩家 &f${args[0]} &7的 &f${args[1]} &7变量被设置为 &f${Data(player).get(args[1], "不存在")}")
                }
            })
        }
    }

    @SubCommand(description = "修改变量", permission = "*")
    var edit = object : BaseSubCommand() {
        override fun getArguments(): Array<Argument> {
            return arrayOf(
                Argument("目标") { WizardFix.getPlayerList() },
                Argument("变量名"),
                Argument("动作") { listOf("+", "-", "*", "/", "=") },
                Argument("参数")
            )
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = WizardFix.getOfflinePlayer(args[0])
            if (player == null) {
                sender.error("玩家 &f${args[0]} &7不存在!")
                return
            }
            Data(player).edit(args[1], args[2], args[3])
            Bukkit.getScheduler().runTask(WizardFix.plugin, Runnable {
                if (sender.isOp && sender is Player) {
                    sender.info("玩家 &f${args[0]} &7的 &f${args[1]} &7变量被设置为 &f${Data(player).get(args[1], "不存在")}")
                }
            })
        }
    }

    @SubCommand(description = "运算修改变量", permission = "*")
    var js = object : BaseSubCommand() {
        override fun getArguments(): Array<Argument> {
            return arrayOf(
                Argument("目标") { WizardFix.getPlayerList() },
                Argument("变量名"),
                Argument("参数")
            )
        }

        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = WizardFix.getOfflinePlayer(args[0])
            if (player == null) {
                sender.error("玩家 &f${args[0]} &7不存在!")
                return
            }
            val message = TabooLibAPI.getPluginBridge().setPlaceholders(player.player,args[2]).replace("__","")
            val value = (Features.compileScript(message)?.eval() ?: "0.0").toString()
            Data(player).set(args[1],value)

            Bukkit.getScheduler().runTask(WizardFix.plugin, Runnable {
                if (sender.isOp && sender is Player) {
                    sender.info("玩家 &f${args[0]} &7的 &f${args[1]} &7变量被设置为 &f${Data(player).get(args[1], "不存在")}")
                }
            })
        }
    }

}