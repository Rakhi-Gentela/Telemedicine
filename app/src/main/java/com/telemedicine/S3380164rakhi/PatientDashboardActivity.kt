package com.telemedicine.S3380164rakhi

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class PatientDashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PatientDashboard(::onCardClicked)
        }
    }

    private fun onCardClicked(cardNo:Int){
        when(cardNo){
            1 -> startActivity(Intent(this, DoctorsActivity::class.java))
            2 -> startActivity(Intent(this, MedicinesActivity::class.java))
            3 -> startActivity(Intent(this, PatientActivity::class.java))
            4 -> {
                startActivity(Intent(this, InfoActivity::class.java))
            }
            5 -> {
                PatientDetails.savePatientLoginStatus(this, false)
                val intent = Intent(this, AccessActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
        }
    }
}

@Composable
fun PatientDashboard(onCardClicked : (cardNo:Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Welcome to Tele Medicine",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF5D3FD3),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 12.dp)
        )
        Text(
            text = "Hi, User !",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF5D3FD3),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // First row of cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CardWithImageAndText(imageRes = R.drawable.doctor, title = "Doctors",onCardClicked,1)
            CardWithImageAndText(imageRes = R.drawable.ic_telemedicine, title = "Diseases and Symptoms",onCardClicked,2)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CardWithImageAndText(imageRes = R.drawable.profile, title = "Profile",onCardClicked,3)
            CardWithImageAndText(imageRes = R.drawable.information, title = "TeleMedicine Info", onCardClicked,4)
        }

        Spacer(modifier = Modifier.height(16.dp))

        CardWithImageAndText(imageRes = R.drawable.logout, title = "Logout", onCardClicked,5)

    }
}



@Composable
fun CardWithImageAndText(imageRes: Int, title: String,onCardClicked : (cardNo:Int) -> Unit,cardId: Int) {

    Card(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .clickable {
                onCardClicked(cardId)


            },
        shape = RoundedCornerShape(16.dp)
    )

    {
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(64.dp)
                    .width(64.dp)
                )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

