package com.jaenyeong.spring02_basic.scan.filter

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

@Target(CLASS)
@Retention(RUNTIME)
@MustBeDocumented
annotation class CustomIncludeBean
