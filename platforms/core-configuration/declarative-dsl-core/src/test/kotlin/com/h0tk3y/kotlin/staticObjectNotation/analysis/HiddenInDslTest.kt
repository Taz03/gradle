/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.com.h0tk3y.kotlin.staticObjectNotation.analysis

import com.h0tk3y.kotlin.staticObjectNotation.HiddenInDeclarativeDsl
import com.h0tk3y.kotlin.staticObjectNotation.Restricted
import com.h0tk3y.kotlin.staticObjectNotation.analysis.ErrorReason
import com.h0tk3y.kotlin.staticObjectNotation.analysis.FqName
import com.h0tk3y.kotlin.staticObjectNotation.demo.resolve
import com.h0tk3y.kotlin.staticObjectNotation.schemaBuilder.schemaFromTypes
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

private class HasHiddenProperty {
    @Restricted
    var x: Int = 0

    @Restricted
    @HiddenInDeclarativeDsl
    var y: Int = 0
}


class HiddenInDslTest {
    val schema = schemaFromTypes(HasHiddenProperty::class, listOf(HasHiddenProperty::class))

    @Test
    fun `handles the hidden properties correctly`() {
        val aType = schema.dataClassesByFqName.getValue(FqName.parse(HasHiddenProperty::class.qualifiedName!!))
        assertTrue { aType.properties.single { it.name == "y" }.isHiddenInDsl }

        val result = schema.resolve(
            """
            x = 1
            y = 2
            """.trimIndent()
        )

        assertEquals(2, result.errors.size)
        assertEquals("y = 2", result.errors.single { it.errorReason is ErrorReason.UnresolvedAssignmentLhs }.element.sourceData.text())
        assertEquals("y", result.errors.single { it.errorReason is ErrorReason.UnresolvedReference }.element.sourceData.text())
    }
}
