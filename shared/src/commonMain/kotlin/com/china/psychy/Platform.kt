package com.china.psychy

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform