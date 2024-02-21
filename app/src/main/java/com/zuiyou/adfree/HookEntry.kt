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

            "cn.xiaochuankeji.tieba.networking.data.VipPrivilege".hook {
                injectMember {
                    method {
                        name = "b"
                    }
                    beforeHook {
                        result = true
                    }
                }
                injectMember {
                    method {
                        name = "c"
                    }
                    beforeHook {
                        result = true
                    }
                }.onAllFailure {
                    loggerE(msg = "Hook VipPrivilege fail: ${it.message}")
                }
            }

            "cn.xiaochuankeji.tieba.networking.data.MemberVipInfo".hook {
                injectMember {
                    method {
                        name = "hasNewProfileBubble"
                    }
                    beforeHook {
                        result = true
                    }
                }
                injectMember {
                    method {
                        name = "hasPhotoAccessory"
                    }
                    beforeHook {
                        result = true
                    }
                }
                injectMember {
                    method {
                        name = "hasProfileDialog"
                    }
                    beforeHook {
                        result = true
                    }
                }
                injectMember {
                    method {
                        name = "hasVipAvatar"
                    }
                    beforeHook {
                        result = true
                    }
                }
                injectMember {
                    method {
                        name = "hasVipBackground"
                    }
                    beforeHook {
                        result = true
                    }
                }
                injectMember {
                    method {
                        name = "hasVipDanmaku"
                    }
                    beforeHook {
                        result = true
                    }
                }
                injectMember {
                    method {
                        name = "hasVipPackage"
                    }
                    beforeHook {
                        result = true
                    }
                }
                injectMember {
                    method {
                        name = "isVip"
                    }
                    beforeHook {
                        result = true
                    }
                }
                injectMember {
                    method {
                        name = "isYearVipPackage"
                    }
                    beforeHook {
                        result = true
                    }
                }.onAllFailure {
                    loggerE(msg = "Hook MemberVipInfo fail: ${it.message}")
                }
            }

            "cf".hook {
                injectMember {
                    method {
                        name = "s"
                    }
                    beforeHook {
                        result = true
                    }
                }.onAllFailure {
                    loggerE(msg = "Hook cf fail: ${it.message}")
                }
            }

            "cn.xiaochuankeji.tieba.ui.base.SplashActivity".hook {
                injectMember {
                    method {
                        name = "B2"
                    }
                    beforeHook {
                        loggerE(msg = args.contentToString())
                        args[1] = android.os.Bundle()
                    }
                }.onAllFailure {
                    loggerE(msg = "Hook SplashActivity fail: ${it.message}")
                }
            }
        }
    }

}