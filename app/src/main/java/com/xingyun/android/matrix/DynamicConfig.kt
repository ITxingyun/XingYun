package com.xingyun.android.matrix

import com.tencent.mrs.plugin.IDynamicConfig

class DynamicConfig : IDynamicConfig {


    override fun get(key: String?, defStr: String?): String {
        return defStr ?: ""
    }

    override fun get(key: String?, defInt: Int): Int {
        return defInt
    }

    override fun get(key: String?, defLong: Long): Long {
        return defLong
    }

    override fun get(key: String?, defBool: Boolean): Boolean {
        return defBool
    }

    override fun get(key: String?, defFloat: Float): Float {
        return defFloat
    }
}