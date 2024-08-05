package com.example.foreign_currency_unit_converter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foreign_currency_unit_converter.ui.theme.ForeignCurrencyUnitConverterTheme
//import com.example.Foreign.ui.theme.TutorialApplicationTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForeignCurrencyUnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var xExpanded by remember { mutableStateOf(false) }
    var yExpanded by remember { mutableStateOf(false) }
    var yValue by remember { mutableStateOf("INR") }
    var xValue by remember { mutableStateOf("USD") }
    var inputValue by remember { mutableStateOf("0")}
    var xConversionFactor by remember {mutableStateOf(1.00)}
    var yConversionFactor by remember { mutableStateOf(83.55) }
    var result by remember { mutableStateOf(0) }

    fun updateResult() {
        val inputValueInt = inputValue.toDouble()
        result = (inputValueInt*yConversionFactor/xConversionFactor).roundToInt()
    }

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Please enter a Value")
        Spacer(modifier = Modifier.height(32.dp))
        TextField(value=inputValue,
            onValueChange = {
                inputValue = it
                updateResult()
            },
            label = { Text(text = "Enter a Value") })
        Row {
            Box(){
                Button(onClick = { xExpanded = true }) {
                    Text(text = xValue)
                    Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Unit Dropdown")
                }
                DropdownMenu(expanded = xExpanded, onDismissRequest = { xExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Euro")},
                        onClick = {
                            xValue = "Euro"
                            xConversionFactor = 0.92
                            xExpanded = false
                            updateResult()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Japanese Yen")},
                        onClick = {
                            xValue = "Japanese Yen"
                            xConversionFactor = 158.54
                            xExpanded = false
                            updateResult()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Indian Rupee")},
                        onClick = {
                            xValue = "Indian Rupee"
                            xConversionFactor = 83.55
                            xExpanded = false
                            updateResult()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "USD")},
                        onClick = {
                            xValue = "USD"
                            xConversionFactor = 1.00
                            xExpanded = false
                            updateResult()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(){
                Button(onClick = { yExpanded=true }) {
                    Text(text = yValue)
                    Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Unit Dropdown")
                }
                DropdownMenu(expanded = yExpanded, onDismissRequest = { yExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Euro")},
                        onClick = {
                            yValue = "Euro"
                            yConversionFactor = 0.92
                            yExpanded = false
                            updateResult()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Japanese Yen")},
                        onClick = {
                            yValue = "Japanese Yen"
                            yConversionFactor = 158.54
                            yExpanded = false
                            updateResult()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Indian Rupee")},
                        onClick = {
                            yValue = "Indian Rupee"
                            yConversionFactor = 83.55
                            yExpanded = false
                            updateResult()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "USD")},
                        onClick = {
                            yValue = "USD"
                            yConversionFactor = 1.00
                            yExpanded = false
                            updateResult()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Converted Currency price is $result")
    }

}



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    ForeignCurrencyUnitConverterTheme {
        UnitConverter()
    }
}