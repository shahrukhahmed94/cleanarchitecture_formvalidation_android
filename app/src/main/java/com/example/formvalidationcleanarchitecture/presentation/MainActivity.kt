package com.example.formvalidationcleanarchitecture.presentation

import android.graphics.Paint.Align
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.formvalidationcleanarchitecture.ui.theme.FormValidationCleanArchitectureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormValidationCleanArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = viewModel<MainViewModel>()
                    val state = viewModel.state
                    val context = LocalContext.current

                    LaunchedEffect(key1 = context){
                        viewModel.validateEvents.collect { event->

                            when(event) {
                                is MainViewModel.ValidationEvent.Success -> {
                                    Toast.makeText(
                                        context,
                                        "Registered Successfully!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }

                        }
                    }


                   Column(
                       modifier = Modifier
                           .fillMaxSize()
                           .padding(32.dp),
                       verticalArrangement = Arrangement.Center
                   ) {

                       TextField(
                           value = state.email,
                           onValueChange = {
                               
                               viewModel.onEvent(RegistrationFormEvent.EmailChanged(it))

                          },
                           isError = state.emailError != null,
                           modifier = Modifier.fillMaxWidth(),
                           placeholder = {
                               Text(text = "Email")
                           },
                           keyboardOptions = KeyboardOptions(
                               keyboardType = KeyboardType.Email
                           )
                       )

                       if (state.emailError !=null) {
                           Text(
                               text = state.emailError,
                               color = MaterialTheme.colors.error,
                               modifier = Modifier.align(Alignment.End)
                               )
                       }
                       
                       Spacer(modifier = Modifier.height(16.dp))


                       TextField(
                           value = state.password,
                           onValueChange = {

                               viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it))

                           },
                           isError = state.emailError != null,
                           modifier = Modifier.fillMaxWidth(),
                           placeholder = {
                               Text(text = "Password")
                           },
                           keyboardOptions = KeyboardOptions(
                               keyboardType = KeyboardType.Password
                           ),
                           visualTransformation = PasswordVisualTransformation()
                       )

                       if (state.passwordError !=null) {
                           Text(
                               text = state.passwordError,
                               color = MaterialTheme.colors.error,
                               modifier = Modifier.align(Alignment.End)
                           )
                       }

                       Spacer(modifier = Modifier.height(16.dp))

                       TextField(
                           value = state.repeatedPassword,
                           onValueChange = {

                               viewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))

                           },
                           isError = state.repeatedPassword != null,
                           modifier = Modifier.fillMaxWidth(),
                           placeholder = {
                               Text(text = "Repeated Password")
                           },
                           keyboardOptions = KeyboardOptions(
                               keyboardType = KeyboardType.Password
                           ),
                           visualTransformation = PasswordVisualTransformation()
                       )

                       if (state.repeatedPassword !=null) {
                           Text(
                               text = state.repeatedPassword,
                               color = MaterialTheme.colors.error,
                               modifier = Modifier.align(Alignment.End)
                           )
                       }

                       Spacer(modifier = Modifier.height(16.dp))

                       Row(modifier = Modifier.fillMaxWidth()) {

                           Checkbox(
                               checked = state.acceptedTerms,
                               onCheckedChange = {
                                   viewModel.onEvent(RegistrationFormEvent.AcceptTerms(it))
                               })
                           Spacer(modifier = Modifier.width(8.dp))

                           Text(text = "Accept Terms")


                       }
                       if (state.termsError !=null) {
                           Text(text = state.termsError,
                           color = MaterialTheme.colors.error)
                       }

                       Button(onClick = {
                           viewModel.onEvent(RegistrationFormEvent.Submit)
                       },
                       modifier = Modifier.align(Alignment.End)
                           ) {

                           Text(text = "Submit")


                       }


                   }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FormValidationCleanArchitectureTheme {
        Greeting("Android")
    }
}