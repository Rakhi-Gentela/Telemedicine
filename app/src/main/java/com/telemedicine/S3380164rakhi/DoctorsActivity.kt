package com.telemedicine.S3380164rakhi

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DoctorsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            DoctorsSearchScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorsSearchScreen() {
    val context = LocalContext.current as Activity
    var searchQuery by remember { mutableStateOf("") }
    val doctors = getDoctorsData()
    val filteredDoctors = doctors.filter {
        it.doctorName.contains(searchQuery, ignoreCase = true) ||
                it.speciality.contains(searchQuery, ignoreCase = true) ||
                it.location.contains(searchQuery, ignoreCase = true)
    }

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
                text = "Doctors",
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(id = R.color.color_light_sky_blue),
                fontWeight = FontWeight.Bold
            )
        }

        Column(modifier = Modifier
                .padding(16.dp))
        {

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search...") },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_24), // Replace with your search icon
                        contentDescription = "Search icon"
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_clear_24), // Replace with your clear icon
                                contentDescription = "Clear icon"
                            )
                        }
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Search by Name, Location or Speciality",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Doctors List
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredDoctors.size) { doctor ->
                    DoctorItemRow(
                        imageResource = R.drawable.doctor, // Replace with your image resource
                        name = filteredDoctors[doctor].doctorName,
                        speciality = filteredDoctors[doctor].speciality,
                        location = filteredDoctors[doctor].location
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun DoctorItemRow(
    imageResource: Int,
    name: String,
    speciality: String,
    location: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "Doctor Image",
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 16.dp)
            )

            Column {
                Text(
                    text = name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = speciality,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = location,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


fun getDoctorsData(): List<DoctorsData> {
    return listOf(
        DoctorsData(
            doctorName = "Dr. Alice Johnson",
            speciality = "Cardiologist",
            location = "New York, NY"
        ),
        DoctorsData(
            doctorName = "Dr. Bob Smith",
            speciality = "Dermatologist",
            location = "Los Angeles, CA"
        ),
        DoctorsData(
            doctorName = "Dr. Clara Evans",
            speciality = "Neurologist",
            location = "Chicago, IL"
        ),
        DoctorsData(
            doctorName = "Dr. Daniel Lee",
            speciality = "Orthopedic Surgeon",
            location = "Houston, TX"
        ),
        DoctorsData(
            doctorName = "Dr. Emily Davis",
            speciality = "Pediatrician",
            location = "Phoenix, AZ"
        ),
        DoctorsData(
            doctorName = "Dr. Frank Wilson",
            speciality = "Ophthalmologist",
            location = "Philadelphia, PA"
        ),
        DoctorsData(
            doctorName = "Dr. Grace Martinez",
            speciality = "Gynecologist",
            location = "San Antonio, TX"
        ),
        DoctorsData(
            doctorName = "Dr. Henry Brown",
            speciality = "ENT Specialist",
            location = "San Diego, CA"
        ),
        DoctorsData(
            doctorName = "Dr. Irene Taylor",
            speciality = "Psychiatrist",
            location = "Dallas, TX"
        ),
        DoctorsData(
            doctorName = "Dr. Jack Anderson",
            speciality = "General Physician",
            location = "San Jose, CA"
        )
    )
}


