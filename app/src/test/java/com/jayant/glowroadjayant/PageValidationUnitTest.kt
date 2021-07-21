package com.jayant.glowroadjayant

import com.google.common.truth.Truth.assertThat
import com.jayant.glowroadjayant.utils.Validator
import org.junit.Test

class PageValidationUnitTest {

    @Test
    fun pageNumberInvalid() {
        val result = Validator.isPageNumberValid(0)
        assertThat(result).isFalse()
    }

    @Test
    fun pageNumberValid() {
        val result = Validator.isPageNumberValid(1)
        assertThat(result).isTrue()
    }

}