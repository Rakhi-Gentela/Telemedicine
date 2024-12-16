package com.rakhiS3380164.telemedicine

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class PatientActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PatientActivityScreen()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PatientActivityScreenP() {
    PatientActivityScreen()
}

@Composable
fun PatientActivityScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.color_deep_blue))
                .padding(vertical = 6.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back Arrow",
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
                    .clickable {
                        (context as Activity).finish()
                    }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Profile Details",
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(id = R.color.color_light_sky_blue),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.patient),
            contentDescription = "User",
            modifier = Modifier
                .width(64.dp)
                .height(64.dp)

        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {

            Text(
                text = "Name ",
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )


            Text(
                text = ": ",
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )



            Text(
                text = PatientDetails.getPatientName(context)!!,
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )

        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {

            Text(
                text = "Age    ",
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )


            Text(
                text = ": ",
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )



            Text(
                text = PatientDetails.getPatientAge(context)!!,
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )

        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {

            Text(
                text = "Email ",
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )


            Text(
                text = ": ",
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )


            Text(
                text = PatientDetails.getPatientEmail(context)!!,
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )

        }
    }
}