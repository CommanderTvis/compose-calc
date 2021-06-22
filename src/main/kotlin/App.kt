package io.github.commandertvis.calc

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import space.kscience.kmath.ast.MstExpression
import space.kscience.kmath.ast.parseMath
import space.kscience.kmath.expressions.invoke
import space.kscience.kmath.operations.*

private val RESTRICTED_CHARS = charArrayOf('\n', '\r')

fun main() = Window(title = "Calculator", size = IntSize(300, 300)) {
    val result = remember { mutableStateOf("") }
    val formula = remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize(), Arrangement.spacedBy(10.dp)) {
        Text("Context:")

        val radioOptions = listOf("Reals", "Ints", "Longs")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

        Row {
            radioOptions.forEach { text ->
                Row {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body1.merge(),
                    )
                }
            }
        }

        Text("Expression:")

        TextField(value = formula.value, onValueChange = {
            if (it.any { c -> c in RESTRICTED_CHARS }) return@TextField
            formula.value = it

            try {
                val algebra: Algebra<*> = when (selectedOption) {
                    "Reals" -> RealField
                    "Ints" -> IntRing
                    "Longs" -> LongRing
                    else -> error("Illegal selected option.")
                }

                result.value = MstExpression(algebra, formula.value.parseMath())().toString()
            } catch (any: Exception) {
            }
        })

        Text("Result:")
        Text(result.value)
    }
}
