package com.example.quizapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.models.QuestionAnswer
import com.example.quizapp.ui.theme.QuizAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            var myQuestion by remember { mutableStateOf("") }
            var myAnswer by remember { mutableStateOf("") }
            val context = LocalContext.current
            val startLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                intent = it.data
                if (intent?.extras != null) {
                    val qa = intent.extras!!.getParcelable<QuestionAnswer>("data")
                    // val qa = intent.extras!!.getParcelable("data",QuestionAnswer::class.java)
                    if (qa != null) {
                        myAnswer = qa.answer
                        Log.d("qa", qa.toString())
                    }

                }
            }

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
                                text = "Type your question below: ",
                                textAlign = TextAlign.Center,
                                style = typography.labelMedium,
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
                                value = myQuestion,
                                textStyle = TextStyle(fontSize = 20.sp),
                                onValueChange = {
                                    myQuestion = it
                                    Log.d("question", "question=" + myQuestion)
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    textColor = Color.Black,
                                    cursorColor = Color.Red,
                                    containerColor = Color.Green

                                ),
                                readOnly = false,
                                enabled = true,
                                placeholder = { Text("Type here..") },
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
                                    val question = QuestionAnswer(question = myQuestion)
                                    val intent = Intent(context, AnswerActivity::class.java)
                                    intent.putExtra("data", question)
                                    //if you don't need a result back just have the following
                                    //context.startActivity(intent)
                                    startLauncher.launch(intent)
                                }) {
                                Text(text = "Pose question", fontSize = 36.sp)
                            }
                        }

                        Row(
                            Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(20.dp)
                        )
                        {
                            Text(
                                text = "Answer received: $myAnswer",
                                textAlign = TextAlign.Center,
                                style = typography.labelMedium,
                                fontSize = 22.sp
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
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("geo:56.119605, 10.158907")
                                    )
                                    startActivity(intent)
                                }) {
                                Text(text = "Start Map", fontSize = 36.sp)
                            }
                        }

                        Row(
                            Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(20.dp)
                        )
                        {
                            Button(
                                onClick = {

                                    val intent = Intent(
                                        Intent.ACTION_DIAL,
                                        Uri.parse("tel:+82345623")
                                    )
                                    startActivity(intent)
                                }) {
                                Text(text = "Phone call", fontSize = 36.sp)
                            }
                        }

                        Row(
                            Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(20.dp)
                        )
                        {
                            Button(
                                onClick = {
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("http://www.dr.dk")
                                    )
                                    startActivity(intent)
                                }) {
                                Text(text = "Browse web", fontSize = 36.sp)
                            }
                        }


                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuizAppTheme {
        Greeting("Android")
    }
}