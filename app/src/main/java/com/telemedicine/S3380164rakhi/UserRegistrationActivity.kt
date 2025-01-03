package com.telemedicine.S3380164rakhi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.FirebaseDatabase

class UserRegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UserRegistrationActivityScreen()
        }
    }
}

@Composable
fun UserRegistrationActivityScreen() {

    var patientFN by remember { mutableStateOf("") }
    var patientAge by remember { mutableStateOf("") }
    var patientEmail by remember { mutableStateOf("") }
    var patientPass by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }
    var validationError by remember { mutableStateOf("") }

    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.color_deep_blue))
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_telemedicine), // Replace with your drawable resource
            contentDescription = "App Logo",
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(32.dp))


        Card(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topStart = 36.dp,
                        topEnd = 36.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Create New Account",
                color = colorResource(id = R.color.color_deep_blue),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                            topStart = 36.dp,
                            topEnd = 36.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    ),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.color_light_sky_blue))
            ) {
                Spacer(modifier = Modifier.height(32.dp))


                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "Enter FullName"
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    value = patientFN,
                    onValueChange = { patientFN = it },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "Enter Age"
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    value = patientAge,
                    onValueChange = { patientAge = it },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                    )
                )


                Spacer(modifier = Modifier.height(8.dp))


                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "Enter Email"
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    value = patientEmail,
                    onValueChange = { patientEmail = it },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "Enter Password"
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    value = patientPass,
                    onValueChange = { patientPass = it },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "Confirm Password"
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    value = confirmPass,
                    onValueChange = { confirmPass = it },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))
                if (validationError.isNotEmpty()) {
                    Text(
                        text = validationError,
                        color = Color.Red,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                Button(
                    onClick = {

                        fun validateInput(): String {
                            return when {
                                patientFN.isBlank() -> "Please enter your full name."
                                isValidUsername(patientFN) -> "Invalid username"
                                patientEmail.isBlank() -> "Please enter your email."
                                isValidEmail(patientEmail) -> "Invalid email"
                                patientPass.isBlank() -> "Please enter your password."
                                confirmPass.isBlank() -> "Please confirm your password."
                                (patientPass != confirmPass) -> "Passwords do not match"
                                else -> "All Fields  Valid"
                            }
                        }

                        validationError = validateInput()

                        if (validationError == "All Fields  Valid") {
                            val patientData = PatientData(
                                patientFN = patientFN,
                                patientEmail = patientEmail,
                                patientAge = patientAge,
                                patientPass = patientPass,
                            )
                            patientAlreadyRegistered(patientData, context)
                        }

                    },
                    modifier = Modifier
                        .width(200.dp)
                        .align(Alignment.CenterHorizontally),
                    contentPadding = PaddingValues(vertical = 12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.color_deep_blue),
                        contentColor = colorResource(
                            id = R.color.color_light_sky_blue
                        )
                    )
                ) {
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "I'm an old user !", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Login",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.color_deep_blue), // Blue text color for "Sign Up"
                        modifier = Modifier.clickable {
                            context.startActivity(Intent(context, AccessActivity::class.java))
                            context.finish()
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }
}

fun isValidUsername(username: String): Boolean {
    val regex =
        "^[a-zA-Z]*$".toRegex() // Matches only alphabets, excluding spaces and other characters
    return !regex.matches(username)
}


fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    return !emailRegex.matches(email)
}

@Preview(showBackground = true)
@Composable
fun UserRegistrationActivityPreview() {
    UserRegistrationActivityScreen()
}

private fun savePatientData(patientData: PatientData, context: Activity) {
    val sanitizedEmail = sanitizeEmail(patientData.patientEmail)
    saveDataToFirebase(sanitizedEmail, patientData,
        onSuccess = {
            showToast(context, "Account Created Sucessfully")
            navigateToAccessActivity(context)
        },
        onFailure = { errorMessage ->
            showToast(context, "User Registration Failed: $errorMessage")
        }
    )
}

private fun sanitizeEmail(email: String): String = email.replace(".", ",")

private fun saveDataToFirebase(
    path: String,
    data: Any,
    onSuccess: () -> Unit,
    onFailure: (String) -> Unit
) {
    val ref = FirebaseDatabase.getInstance().getReference("Patients")
    ref.child(path).setValue(data)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                onFailure(task.exception?.message ?: "Unknown error")
            }
        }
        .addOnFailureListener { exception ->
            onFailure(exception.message ?: "Unknown error")
        }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

private fun navigateToAccessActivity(context: Activity) {
    context.startActivity(Intent(context, AccessActivity::class.java))
    context.finish()
}

private fun patientAlreadyRegistered(patientData1: PatientData, context: Activity) {
    val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference =
        firebaseDatabase.getReference("Patients").child(patientData1.patientEmail.replace(".", ","))

    databaseReference.get().addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val patientData = task.result?.getValue(PatientData::class.java)
            if (patientData != null) {
                Toast.makeText(context, "Patient already exists", Toast.LENGTH_SHORT).show()
            } else {
                savePatientData(patientData1, context)
            }
        } else {
            Toast.makeText(
                context,
                "Failed to retrieve user data",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
