package com.jaenyeong.spring02_basic.annotation

import org.springframework.beans.factory.annotation.Qualifier
import java.lang.annotation.Inherited
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.CONSTRUCTOR
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.LOCAL_VARIABLE
import kotlin.annotation.AnnotationTarget.PROPERTY
import kotlin.annotation.AnnotationTarget.PROPERTY_GETTER
import kotlin.annotation.AnnotationTarget.PROPERTY_SETTER
import kotlin.annotation.AnnotationTarget.TYPE_PARAMETER
import kotlin.annotation.AnnotationTarget.VALUE_PARAMETER

@Target(CLASS,
    TYPE_PARAMETER,
    PROPERTY,
    FIELD,
    LOCAL_VARIABLE,
    VALUE_PARAMETER,
    CONSTRUCTOR,
    FUNCTION,
    PROPERTY_GETTER,
    PROPERTY_SETTER
)
@Retention(RUNTIME)
@Inherited
@MustBeDocumented
@Qualifier("mainDiscountPolicy")
annotation class MainDiscountPolicy()
