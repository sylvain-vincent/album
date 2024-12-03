package com.sylvainvincent.myalbums.core.common

fun getSystemUserAgent() = System.getProperty("http.agent") ?: "Unknown"