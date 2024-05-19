package com.agworks.calculator


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agworks.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                MainScreen()
            }
        }
    }
}

@Preview
@Composable
fun MainScreen() {
    val number1 = remember {
        mutableStateOf("")
    }
    val number2 = remember {
        mutableStateOf("")
    }
    val validState = remember(number1.value, number2.value) {
        number1.value.trim().isNotEmpty() && number2.value.trim().isNotEmpty()
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputField(
                valueState = number1,
                labelId = "Number 1",
                enabled = true,
                isSingleLine = true
            )
            InputField(
                valueState = number2,
                labelId = "Number 2",
                enabled = true,
                isSingleLine = true
            )
            Spacer(
                modifier = Modifier
                    .padding(5.dp)
            )
            val context = LocalContext.current
            Button(onClick = {
                if(!validState)
                {
                    Toast.makeText(context , "Please provide both number" , Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(context , "${number1.value.toInt() + number2.value.toInt()}" , Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(text = "Add")
            }
        }
    }
}

