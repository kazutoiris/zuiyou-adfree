package com.zuiyou.adfree

import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.log.loggerE
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit


@InjectYukiHookWithXposed
object HookEntry : IYukiHookXposedInit {
    override fun onInit() = configs {
        isDebug = false
    }

    override fun onHook() = encase {
        loadApp("cn.xiaochuankeji.tieba") {

            "cn.xiaochuankeji.hermes.core.provider.ADProvider".hook {
                injectMember {
                    constructor {
                        paramCount = 3
                    }
                    beforeHook {
                        args[1] = ""
                    }
                }
                injectMember {
                    method {
                        name = "getConfigKey"
                    }
                    beforeHook {
                        result = ""
                    }
                }.onAllFailure {
                    loggerE(it.toString())
                }
            }
        }
    }

}