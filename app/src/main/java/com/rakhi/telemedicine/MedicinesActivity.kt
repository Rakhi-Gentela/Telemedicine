package com.rakhi.telemedicine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MedicinesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiseaseSelectionScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonSearchScreen() {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24), // Replace with your search icon
                    contentDescription = "Search icon"
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = TextFieldValue("").toString() }) {
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


        PersonItemRow(
            imageResource = R.drawable.doctor, // Replace with your image resource
            name = "Dr. John Doe",
            speciality = "Cardiologist",
            location = "New York, NY"
        )


        Spacer(modifier = Modifier.height(4.dp))

        PersonItemRow(
            imageResource = R.drawable.doctor, // Replace with your image resource
            name = "Dr. Jane Smith",
            speciality = "Dermatologist",
            location = "Los Angeles, CA"
        )
        Spacer(modifier = Modifier.height(4.dp))

        PersonItemRow(
            imageResource = R.drawable.doctor, // Replace with your image resource
            name = "Dr. Michael Brown",
            speciality = "Orthopedic Surgeon",
            location = "Chicago, IL"
        )

        Spacer(modifier = Modifier.height(4.dp))
        PersonItemRow(
            imageResource = R.drawable.doctor, // Replace with your image resource
            name = "Dr. Emily Davis",
            speciality = "Pediatrician",
            location = "Houston, TX"
        )

        Spacer(modifier = Modifier.height(4.dp))
        PersonItemRow(
            imageResource = R.drawable.doctor, // Replace with your image resource
            name = "Dr. William Wilson",
            speciality = "Neurologist",
            location = "Miami, FL"
        )

    }
}

@Composable
fun PersonItemRow(
    imageResource: Int,
    name: String,
    speciality: String,
    location: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
            .padding(bottom = 4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image with fixed size of 50dp
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "Person Image",
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 8.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))

            // Column for name, speciality, and location
            Column(
                verticalArrangement = Arrangement.Center

            ) {
                Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = speciality, fontSize = 14.sp, color = Color.Gray)
                Text(text = location, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}