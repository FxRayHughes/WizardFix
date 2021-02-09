# WizardFix

##### 一个极简的变量系统
 就是一个简单普通的能 __跨服__ 的能 __全局__ 能 __个人__ 还能 __JS运算__ 的变量罢了

## Command
```yaml
  WizardFix v1.0.0
  
  命令: /wizardfix [...]
  参数:
    - look [目标] [变量名]
      查看变量
    - set [目标] [变量名] [参数]
      设置变量
    - add [目标] [变量名] [参数]
      增加变量
    - take [目标] [变量名] [参数]
      增加变量
    - edit [目标] [变量名] [动作] [参数]
      修改变量
    - js [目标] [变量名] [参数]
      运算修改变量
```

## Papi
```
获取玩家变量:
key = 变量名,def = 默认值
%wizard_info_key_def%
获取其他玩家变量:
player = 玩家名,可离线
%wizard_who_player_key_def%
判断变量:
!数值变量才可以用!
如果key满足this就返回yes
不满足或为空就返回no
%wizard_has_key_this_yes_no%
判断变量:
!文字变量才可以用!
如果key满足this就返回yes
不满足或为空就返回no
%wizard_is_key_this_yes_no%
```

## Use
````
全局变量:
首先 这个插件是对单个玩家使用的,按照传统角度无法全局变量
但是我兼容了离线玩家嗯,也就是你创建一个号登陆游戏后再ban掉即可
然后操作那个账号的变量就是全局变量了...
%wizard_who_Server_key_def% <-像这种 就可以获取到Server的Key变量

离线补全:
嗯 我给离线玩家整了个补全功能,这样就可以获取到离线玩家

关于跨服:
当然是支持的,你需要改TabooLib文件夹的settings.yml
LOCAL-PLAYER-BRIDGE项,自己进行修改
当前只支持mongodb 因为真的mysql优化不好
````
## API
````kotlin
//=> 获取玩家数据
//离线玩家,玩家 , key = 查询键, def=为空时返回内容
Data(OfflinePlayer).get(Key, Def)
//=> 设置玩家数据
//value = 设置内容 [小数,字符串,整数]
Data(OfflinePlayer).set(Key, Value)
//=> 编辑玩家数据
//symbol = 符号 [ + - * / =]
// edit写法不支持字符串类型变量
Data(OfflinePlayer).edit(key, symbol, value)

//更简单的API[不推荐用]
//不言而喻
WizardObject.getIntegral(player, integral, def)
WizardObject.setIntegral(player, integral, value)
WizardObject.addIntegral(player, integral, value)
WizardObject.takeIntegral(player, integral, value)

//获取离线玩家的对象 通过ID
WizardFix.getOfflinePlayer(name)
//获取所有玩家的名字,包括离线玩家
WizardFix.getPlayerList()
````
