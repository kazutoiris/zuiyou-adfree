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

//            method 1
            "cn.xiaochuankeji.hermes.core.Hermes".hook {
                injectMember {
                    method {
                        name = "getProviderList\$core_release"
                    }
                    afterHook {
                        (result as MutableList<*>).clear()
                    }
                }.onAllFailure {
                    loggerE(msg = "Hook getProviderList fail: ${it.message}")
                }
            }

            "cn.xiaochuankeji.hermes.core.workflow.init.InitUtil".hook {
                injectMember {
                    method {
                        name = "init"
                        paramCount = 1
                    }
                    replaceToFalse()
                }.onAllFailure {
                    loggerE(msg = "Hook init fail: ${it.message}")
                }
            }

//            method 2
            "cn.xiaochuankeji.hermes.core.provider.ADProvider".hook {
                injectMember {
                    method {
                        name = "getChannel"
                    }
                    beforeHook {
                        result = 0
                    }
                }.onAllFailure {
                    loggerE(msg = "Hook getChannel fail: ${it.message}")
                }
            }

            "cn.xiaochuankeji.hermes.core.provider.ADProvider".hook {
                injectMember {
                    method {
                        name = "getConfigKey"
                    }
                    beforeHook {
                        result = ""
                    }
                }.onAllFailure {
                    loggerE(msg = "Hook getConfigKey fail: ${it.message}")
                }
            }

//            method 3
            "cn.xiaochuankeji.hermes.core.api.entity.ADSDKConfigResponseData".hook {
                injectMember {
                    method {
                        name = "getEnable"
                    }
                    replaceToFalse()
                }.onAllFailure {
                    loggerE(msg = "Hook getEnable fail: ${it.message}")
                }
            }

//            method 4
            "cn.xiaochuankeji.hermes.core.api.entity.ThirdSDKConfigResponse".hook {
                injectMember {
                    method {
                        name = "getEnable"
                    }
                    replaceToFalse()
                }.onAllFailure {
                    loggerE(msg = "Hook getEnable fail: ${it.message}")
                }
            }

//            method 5
            "cn.xiaochuankeji.hermes.core.api.entity.ADConfigResponseDataKt".hook {
                injectMember {
                    method {
                        name = "allRegisteredSDKConfigs"
                        paramCount = 1
                    }
                    beforeHook {
                        result = emptyMap<Any, Any>()
                    }
                }.onAllFailure {
                    loggerE(msg = "Hook allRegisteredSDKConfigs fail: ${it.message}")
                }
            }

        }
    }

}