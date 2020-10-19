package io.github.commandertvis.calc

import androidx.compose.desktop.Window
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import scientifik.kmath.asm.compile
import scientifik.kmath.ast.MstExpression
import scientifik.kmath.ast.parseMath
import scientifik.kmath.operations.RealField

fun main() = Window(title = "Compose for Desktop", size = IntSize(300, 300)) {
    val result = remember { mutableStateOf("") }
    val formula = remember { mutableStateOf("") }

    MaterialTheme {
        Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
            TextField(value = "", onValueChange = {
                println(it)
                try {
                    result.value = MstExpression(RealField, formula.value.parseMath()).compile().invoke(emptyMap()).toString()
                } catch (any: Exception) {
                }
            })

            Text(result.value)
        }
    }
}
