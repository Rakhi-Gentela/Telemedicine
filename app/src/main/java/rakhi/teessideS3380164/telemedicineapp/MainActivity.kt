package rakhi.teessideS3380164.telemedicineapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import rakhi.teessideS3380164.telemedicineapp.ui.theme.TeleMedicineTheme
import kotlinx.coroutines.delay

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TeleMedicineTheme {
                EntryScreenMA(this)
            }
        }
    }

}

@Composable
fun EntryScreenMA(fa: FragmentActivity) {
    var isSplashScreenActive by remember { mutableStateOf(true) }
    val currentContext = LocalContext.current as Activity

    LaunchedEffect(Unit) {
        delay(3000)
        isSplashScreenActive = false
    }

    if (isSplashScreenActive) {
        EntryScreen()

    } else {
        val patientStatus = PatientDetails.getPatientLoginStatus(currentContext)

        if (patientStatus) {
            checkFingerPrint(fa, currentContext)
        } else {
            currentContext.startActivity(Intent(currentContext, AccessActivity::class.java))
            currentContext.finish()
        }
    }

}

@Composable
fun EntryScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.color_deep_blue)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.weight(1f))


            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.ic_telemedicine),
                contentDescription = "Telemedicine App by Rakhi",
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Welcome To",
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.color_light_sky_blue), // Green color similar to the design
                fontSize = 26.sp,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Telemedicine App\nby Rakhi",
                color = colorResource(id = R.color.color_light_sky_blue), // Green color similar to the design
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))


        }
    }

}


@Preview(showBackground = true)
@Composable
fun EntryScreenPreview() {
    EntryScreen()
}

fun checkFingerPrint(fragmentActivity: FragmentActivity, currentContext: Context) {
    val biometricManager = BiometricManager.from(fragmentActivity)
    if (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS) {
        val executor = ContextCompat.getMainExecutor(fragmentActivity)
        val biometricPrompt = BiometricPrompt(
            fragmentActivity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    currentContext.startActivity(
                        Intent(
                            currentContext,
                            PatientDashboardActivity::class.java
                        )
                    )
                    (currentContext as Activity).finish()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        fragmentActivity,
                        "Failed to check fingerprint",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(fragmentActivity, "Failed", Toast.LENGTH_LONG).show()
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("FingerPrint Login")
            .setSubtitle("Verify to continue")
            .setNegativeButtonText("Close")
            .build()

        biometricPrompt.authenticate(promptInfo)
    } else {
        Toast.makeText(fragmentActivity, "Device doesn't support fingerprint", Toast.LENGTH_LONG)
            .show()
        currentContext.startActivity(Intent(currentContext, PatientDashboardActivity::class.java))
        (currentContext as Activity).finish()
    }
}