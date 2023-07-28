package com.jaenyeong.spring02_basic.autowired.sametypebean

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
@Primary
class SubPrimaryBean : SuperPrimaryBean
