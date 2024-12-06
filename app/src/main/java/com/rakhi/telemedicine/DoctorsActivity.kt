package com.rakhi.telemedicine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DoctorsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            PersonSearchScreen()
        }
    }
}

@Composable
fun DiseaseSelectionScreen() {
    var selectedDisease by remember { mutableStateOf("") }
    val diseases = listOf("Diabetes", "Hypertension", "Asthma", "Arthritis")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Dropdown for Disease Selection
//        DiseaseDropdownMenu(
//            diseases = diseases,
//            selectedDisease = selectedDisease,
//            onDiseaseSelected = { selectedDisease = it }
//        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DetailColumn(
                imageResource = R.drawable.ic_telemedicine,
                name = "Medicine F",
                uses = "For diabetes management",
                onViewClick = { }
            )
            DetailColumn(
                imageResource = R.drawable.ic_telemedicine,
                name = "Medicine G",
                uses = "For cold and cough",
                onViewClick = { }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Two side-by-side columns for details
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DetailColumn(
                imageResource = R.drawable.ic_telemedicine, // Replace with your image resource
                name = "Medicine H",
                uses = "For heartburn relief",
                onViewClick = { /* Handle View action for Medicine H */ }
            )
            DetailColumn(
                imageResource = R.drawable.ic_telemedicine, // Replace with your image resource
                name = "Medicine I",
                uses = "For headache relief",
                onViewClick = { /* Handle View action for Medicine I */ }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiseaseDropdownMenu(
    diseases: List<String>,
    selectedDisease: String,
    onDiseaseSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedDisease,
            onValueChange = { },
            label = { Text("Select Disease") },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            diseases.forEach { disease ->
                DropdownMenuItem(
                    text = { Text(disease) },
                    onClick = {
                        onDiseaseSelected(disease)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun DetailColumn(
    imageResource: Int,
    name: String,
    uses: String,
    onViewClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    )
    // Image with size 20dp
    {
        Card(
            modifier = Modifier
                .width(150.dp)
                .padding(vertical = 12.dp, horizontal = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        {
            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "Icon",
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(8.dp))

            // Name and uses text

            Text(
                text = name, fontSize = 16.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
            )
            Text(
                text = uses, fontSize = 14.sp, color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(8.dp))

            // View Button
            Button(
                onClick = onViewClick,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {

                Text(
                    text = "View",
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

