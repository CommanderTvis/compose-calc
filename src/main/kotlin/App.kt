package io.github.commandertvis.calc

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import scientifik.kmath.ast.MstExpression
import scientifik.kmath.ast.parseMath
import scientifik.kmath.operations.*

private val RESTRICTED_CHARS = charArrayOf('\n', '\r')

data class RadioState(var selectedOption: String? = null)

@Composable
fun LabeledRadioButton(
    text: String,
    radioState: RadioState
) = Row {
    RadioButton(selected = radioState.selectedOption == text, onClick = { radioState.selectedOption = text })
    Text(text)
}

fun main() = Window(title = "Compose for Desktop", size = IntSize(300, 300)) {
    val result = remember { mutableStateOf("") }
    val formula = remember { mutableStateOf("") }
    val state = remember { mutableStateOf(RadioState("Reals")) }

    Column(Modifier.fillMaxSize(), Arrangement.spacedBy(10.dp)) {
        Text("Context:")

        Row {
            LabeledRadioButton("Reals", state.value)
            LabeledRadioButton("Ints", state.value)
            LabeledRadioButton("Longs", state.value)
        }

        Text("Expression:")

        TextField(value = formula.value, onValueChange = {
            if (it.any { c -> c in RESTRICTED_CHARS }) return@TextField
            formula.value = it

            try {
                val algebra = when (state.value.selectedOption) {
                    "Reals" -> RealField
                    "Ints" -> IntRing
                    "Longs" -> LongRing
                    else -> error("Illegal selected option.")
                }

                result.value = MstExpression(algebra, formula.value.parseMath())(emptyMap()).toString()
            } catch (any: Exception) {
            }
        })

        Text("Result:")
        Text(result.value)
    }
}
