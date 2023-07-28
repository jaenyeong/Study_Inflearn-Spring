package com.jaenyeong.spring02_basic.autowired.injectionway

import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AutowiredBean(
    // constructor injection
    @Autowired
    internal val constructorInjection: ConstructorInjection
) {
    // field injection
    @Autowired
    internal lateinit var fieldInjection: FieldInjection

    // function(method) injection
    internal lateinit var functionInjection: FunctionInjection

    // function(method) injection with ObjectProvider
    // 사용 시 특징
    // 1. 주입되는 빈의 `lazy loading` 가능
    // 2. 주의해야 하지만 `null` 또는 `빈 컬렉션`을 반환해 의존성이 항상 존재하지 않는 경우에 유용함
    // 3. 같은 타입의 의존성이 여러개인 경우 컬렉션을 통해 모두 가져올 수 있음
    // 4. `@Qualifier`, `@Primary`를 직접 사용하기 어려움
    internal lateinit var providerInjection: ProviderInjection

    // 코틀린은 setter를 자동으로 생성하기 때문에 `setFunctionInjection`으로 네이밍 하는 경우 에러 발생
    // Platform declaration clash: The following declarations have the same JVM signature (setFunctionInjection$Spring02_Basic_test(Lcom/jaenyeong/spring02_basic/autowired/injectionbean/FunctionInjection;)V):
    //    fun `<set-functionInjection>`(`<set-?>`: FunctionInjection): Unit defined in com.jaenyeong.spring02_basic.autowired.injectionbean.AutowiredService
    //    fun setFunctionInjection(functionInjection: FunctionInjection): Unit defined in com.jaenyeong.spring02_basic.autowired.injectionbean.AutowiredService
    @Autowired
    internal fun changeFunctionInjection(functionInjection: FunctionInjection) {
        this.functionInjection = functionInjection
    }

    @Autowired
    internal fun changeProviderInjection(providerInjection: ObjectProvider<ProviderInjection>) {
        this.providerInjection = providerInjection.getObject()
    }
}
