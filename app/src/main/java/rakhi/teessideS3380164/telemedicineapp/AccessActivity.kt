package rakhi.teessideS3380164.telemedicineapp

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

class AccessActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AccessActivityScreen()
        }
    }
}

@Composable
fun AccessActivityScreen() {
    var patientEmail by remember { mutableStateOf("") }
    var patientPassword by remember { mutableStateOf("") }
    val errorMessage by remember { mutableStateOf("") }

    val currentContext = LocalContext.current as Activity

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
                text = "Login to your account",
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

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "Enter Password"
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    value = patientPassword,
                    onValueChange = { patientPassword = it },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                }

                Button(
                    onClick = {
                        when{
                            patientEmail.isEmpty() -> {
                                Toast.makeText(currentContext, "Email is compulsory to login", Toast.LENGTH_SHORT).show()
                            }
                            patientPassword.isEmpty() -> {
                                Toast.makeText(currentContext, "Password is compulsory to login", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                fetchPatientDetails(patientEmail , patientPassword, currentContext)
                            }
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
                        text = "Login",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "I don't have account !", fontSize = 15.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Register",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.color_deep_blue), // Blue text color for "Sign Up"
                        modifier = Modifier.clickable {
                            currentContext.startActivity(
                                Intent(
                                    currentContext,
                                    UserRegistrationActivity::class.java
                                )
                            )
                            currentContext.finish()
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun AccessActivityPreview() {
    AccessActivityScreen()
}

fun fetchPatientDetails(userEmail: String, userPassword: String, currentContext: Activity) {
    val sanitizedEmail = sanitizeEmail(userEmail)
    fetchPatientDataFromDatabase(sanitizedEmail,
        onSuccess = { patientData ->
            if (isPasswordValid(userPassword, patientData.patientPass)) {
                handleSuccessfulLogin(currentContext, patientData)
            } else {
                showToast(currentContext, "Incorrect Credentials")
            }
        },
        onFailure = {
            showToast(currentContext, "No User Found")
        }
    )
}

private fun sanitizeEmail(email: String): String = email.replace(".", ",")


private fun fetchPatientDataFromDatabase(
    email: String,
    onSuccess: (PatientData) -> Unit,
    onFailure: () -> Unit
) {
    val databaseReference = FirebaseDatabase.getInstance().reference
    databaseReference.child("Patients").child(email).get()
        .addOnSuccessListener { snapshot ->
            val patientData = snapshot.getValue(PatientData::class.java)
            if (patientData != null) onSuccess(patientData) else onFailure()
        }
        .addOnFailureListener {
            println("Error retrieving data: ${it.message}")
            onFailure()
        }
}


private fun isPasswordValid(inputPassword: String, actualPassword: String): Boolean {
    return inputPassword == actualPassword
}


private fun handleSuccessfulLogin(currentContext: Activity, patientData: PatientData) {
    showToast(currentContext, "Login Successful")
    PatientDetails.savePatientLoginStatus(currentContext, true)
    PatientDetails.savePatientEmail(currentContext, patientData.patientEmail)
    PatientDetails.savePatientAge(currentContext, patientData.patientAge)
    PatientDetails.savePatientName(currentContext, patientData.patientFN)
    currentContext.startActivity(Intent(currentContext, PatientDashboardActivity::class.java))
    currentContext.finish()
}


private fun showToast(currentContext: Context, message: String) {
    Toast.makeText(currentContext, message, Toast.LENGTH_SHORT).show()
}
