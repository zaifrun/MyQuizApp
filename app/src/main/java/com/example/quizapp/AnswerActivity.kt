package com.example.quizapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.models.QuestionAnswer
import com.example.quizapp.ui.theme.QuizAppTheme

class AnswerActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var qa = intent.extras!!.getParcelable<QuestionAnswer>("data")
        //on api 33 or above
        //var qa = intent.extras!!.getParcelable("data",QuestionAnswer::class.java)
        setContent {

            var myAnswer by remember { mutableStateOf("") }

            QuizAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {

                        Row(Modifier.align(alignment = Alignment.CenterHorizontally))
                        {
                            Text(
                                text = qa?.question + "?",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.labelMedium,
                                fontSize = 22.sp
                            )
                        }
                        Row(Modifier.align(alignment = Alignment.CenterHorizontally))
                        {
                            Text(
                                text = "Type your answer below: ",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.labelMedium,
                                fontSize = 22.sp
                            )
                        }

                        Row(
                            Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(10.dp)
                        )
                        {
                            OutlinedTextField(
                                value = myAnswer,
                                textStyle = TextStyle(fontSize = 20.sp),
                                onValueChange = {
                                    myAnswer = it
                                    Log.d("answer", "answer=" + myAnswer)
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    textColor = Color.White,
                                    cursorColor = Color.Red,
                                    containerColor = Color.Blue

                                ),
                                readOnly = false,
                                enabled = true,
                                placeholder = { Text("Type here..", color = Color.White) },
                                maxLines = 2,
                                modifier = Modifier
                                    .padding(end = 10.dp, start = 20.dp, bottom = 10.dp)
                                    .height(80.dp)

                            )
                        }
                        Row(
                            Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(20.dp)
                        )
                        {
                            Button(
                                onClick = {
                                    qa?.answer = myAnswer
                                    val data = Intent()
                                    data.putExtra("data", qa)
                                    setResult(Activity.RESULT_OK, data)
                                    super.finish()

                                }) {
                                Text(text = "Give answer", fontSize = 36.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    QuizAppTheme {
        Greeting2("Android")
    }
}