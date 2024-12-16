package com.rakhiS3380164.telemedicine

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DiseaseDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DiseaseDetails(ClickedDisease.medicineData)
        }
    }
}

@Composable
fun DiseaseDetails(medicineData: MedicineData) {
    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
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
                        context.finish()
                    }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = medicineData.disease,
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(id = R.color.color_light_sky_blue),
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Causes",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = medicineData.causes,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.black)
            )

            Text(
                text = "Symptoms",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = medicineData.symptoms,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.black)
            )

            Text(
                text = "Treatment and prevention",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = medicineData.treatmentPreventions,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.black)
            )


        }
    }
}