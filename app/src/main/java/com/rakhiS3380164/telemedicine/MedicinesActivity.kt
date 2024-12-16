package com.rakhiS3380164.telemedicine

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MedicinesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicineSearchScreen()
        }
    }
}

@Composable
fun MedicineSearchScreen() {
    val context = LocalContext.current as Activity
    val medicines = getMedicineData()
    var selectedDisease by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }

//    val filteredMedicines = medicines.filter { medicine ->
//        (selectedDisease.isEmpty() || medicine.disease.contains(selectedDisease, ignoreCase = true)) &&
//                (searchQuery.isEmpty() || medicine.medicineName.contains(searchQuery, ignoreCase = true))
//    }

    val filteredMedicines = if (selectedDisease == "All") {
        medicines
    } else {
        medicines.filter { medicine ->
            (selectedDisease.isEmpty() || medicine.disease.contains(
                selectedDisease,
                ignoreCase = true
            )) &&
                    (searchQuery.isEmpty() || medicine.disease.contains(
                        searchQuery,
                        ignoreCase = true
                    ))
        }
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
                text = "Diseases and Symptoms",
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(id = R.color.color_light_sky_blue),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }


        Column(
            modifier = Modifier
                .padding(16.dp)
        )
        {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search Disease...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_24), // Replace with your search icon
                        contentDescription = "Search Icon"
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_clear_24), // Replace with your clear icon
                                contentDescription = "Clear Icon"
                            )
                        }
                    }
                }
            )

            /*
            // Dropdown for Disease Selection
            DiseaseDropdownMenu(
                diseases = listOf("All", "Diabetes", "Hypertension", "Asthma", "Arthritis"),
                selectedDisease = selectedDisease,
                onDiseaseSelected = { selectedDisease = it }
            )
            */

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp) // Horizontal spacing
            ) {
                items(filteredMedicines.size) { medicine ->
                    MedicineItemRow(
                        imageResource = filteredMedicines[medicine].image,
                        name = filteredMedicines[medicine].medicineName,
                        uses = filteredMedicines[medicine].uses,
                        disease = filteredMedicines[medicine].disease,
                        medicineData = filteredMedicines[medicine]
                    )
                }
            }


            /*
            // Medicine List
            LazyColumn {
                items(filteredMedicines.size) { medicine ->
                    MedicineItemRow(
                        imageResource = filteredMedicines[medicine].image,
                        name = filteredMedicines[medicine].medicineName,
                        uses = filteredMedicines[medicine].uses,
                        disease = filteredMedicines[medicine].disease,
                        medicineData = filteredMedicines[medicine]
                    )
                }
            }
            */
        }
    }
}

@Composable
fun MedicineItemRow(
    imageResource: Int,
    name: String,
    uses: String,
    disease: String,
    medicineData: MedicineData
) {

    val context = LocalContext.current as Activity

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                ClickedDisease.medicineData = medicineData
                context.startActivity(Intent(context, DiseaseDetailsActivity::class.java))
            },

        ) {
//        Row(
//            modifier = Modifier
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = painterResource(id = imageResource),
//                contentDescription = "Medicine Image",
//                modifier = Modifier
//                    .size(50.dp)
//                    .padding(end = 8.dp)
//            )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp, vertical = 6.dp)
                .height(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "Medicine Image",
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 8.dp)

            )
//                Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
//                Text(text = uses, fontSize = 14.sp, color = Color.Gray)
            Text(
                text = disease, fontSize = 16.sp, color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
//        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiseaseDropdownMenu(
    diseases: List<String>,
    selectedDisease: String,
    onDiseaseSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) } // State to control dropdown visibility

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
//        TextField(
//            value = selectedCategory,
//            onValueChange = {},
//            readOnly = true,
//            label = { Text("Filter by Categories") },
//            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//            modifier = Modifier
//                .menuAnchor()
//                .fillMaxWidth()
//        )

        TextField(
            value = selectedDisease,
            onValueChange = {},
            label = { Text("Select Disease") },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()

        ) {
            diseases.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onDiseaseSelected(option)
                        expanded = false
                    },
                    text = { Text(option) }
                )
            }
        }

//        ExposedDropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            diseases.forEach { disease ->
//                DropdownMenuItem(
//                    text = { Text(disease) },
//                    onClick = {
//                        onDiseaseSelected(disease)
//                        expanded = false
//                    }
//                )
//            }
//        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiseaseDropdownMenuOld(
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
            onValueChange = {},
            label = { Text("Select Disease") },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
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


fun getMedicineData(): List<MedicineData> {
    return listOf(
        MedicineData(
            medicineName = "Paracetamol",
            uses = "Pain relief, fever reduction",
            image = R.drawable.headache, // Replace with your image resource
            disease = "Cephalalgia (headache)",
            causes = "Headaches may be attributed to a host of reasons ranging from lack of sleep, dehydration, stress and anxiety, and hormonal changes to exposure to bright light and loud noise.",
            symptoms = "You may experience sensitivity to light, loss of appetite, facial pain and pressure, dizziness, and blurred vision.",
            treatmentPreventions = "Resting in a quiet dark room, administering a hot or cold compress, gentle head massages, and over-the-counter medication may help soothe a headache."
        ),
        MedicineData(
            medicineName = "Ibuprofen",
            uses = "Anti-inflammatory, pain relief",
            image = R.drawable.common_cold, // Replace with your image resource
            disease = "Rhinovirus (common cold)",
            causes = "Although many viruses can cause a cold, the rhinovirus is the most common cause of the common cold.",
            symptoms = "These may include a runny or stuffy nose, sore throat, cough, and congestion.",
            treatmentPreventions = "Most cases are mild, so you should be able to treat yours with plenty of rest, proper hydration and over-the-counter nasal decongestants."
        ),
        MedicineData(
            medicineName = "Amoxicillin",
            uses = "Antibiotic for infections",
            image = R.drawable.swimmers_ear, // Replace with your image resource
            disease = "Otitis externa (swimmer’s ear)",
            causes = "Regular exposure to water and sticking stuff in your ear can increase your risk of developing the condition. If your ears hurt, itch or discharges pus, take a trip to your GP for a proper diagnosis and treatment.",
            symptoms = "Redness on the outer ear, itch in ear, ear pain, pus in ear.",
            treatmentPreventions = "The best way to avoid otitis externa is to keep your ears clean and dry. Swimmer's ear is usually treated with antibiotics."
        ),
        MedicineData(
            medicineName = "Cetirizine",
            uses = "Allergy relief",
            image = R.drawable.sore_eye, // Replace with your image resource
            disease = "Conjunctivitis (sore/pink eye)",
            causes = "Common causes include viruses, bacteria or allergies. It may also be caused by irritants such as foreign particles that went into the eye.",
            symptoms = "Redness, itchiness, a feeling of irritation, discharge or tearing from one or both eyes.",
            treatmentPreventions = "Treatment is usually aimed at relieving symptoms. It may involve using artificial tears, cleaning the eyelids with a wet cloth and applying cold or warm compresses."
        ),
        MedicineData(
            medicineName = "Metformin",
            uses = "Blood sugar control",
            image = R.drawable.sore_throat, // Replace with your image resource
            disease = "Pharyngitis (sore throat)",
            causes = "Pharyngitis is most commonly caused by an inflammation of the pharynx (the membrane-lined cavity behind the nose and mouth) due to a viral or bacterial infection. It may also be caused by allergies, dry air or exposure to second-hand smoke.",
            symptoms = "Sore, dry or scratchy throat.",
            treatmentPreventions = "Symptoms can be relieved by drinking plenty of fluids, gargling with warm salt water and taking throat lozenges to soothe the pain, but if the pain persists, approach an ENT doctor."
        ),
        MedicineData(
            medicineName = "Omeprazole",
            uses = "Acid reflux treatment",
            image = R.drawable.cough, // Replace with your image resource
            disease = "Tussis (cough)",
            causes = "Coughs are usually a result of an upper or lower respiratory tract infection – like the flu, which is caused by that pesky rhinovirus – or by something else, like acid reflux, asthma or smoking.",
            symptoms = "In most cases, a cough happens voluntarily or involuntarily to clear the throat and airways of irritants.",
            treatmentPreventions = "To prevent coughing, try to avoid coming into contact with people who are sick. You should also drink plenty of water to stay hydrated. If your cough is due to allergies, identify the triggers and avoid exposure to them."
        ),
        MedicineData(
            medicineName = "Atorvastatin",
            uses = "Cholesterol management",
            image = R.drawable.fever, // Replace with your image resource
            disease = "Pyrexia (fever)",
            causes = "Fevers are usually a symptom of something else, like a lung or ear infection, and will usually go away after a few days rest.",
            symptoms = "A body temperature that is higher than normal (i.e. 37°C), shivers and feeling cold, sweating, low appetite, lack of energy and feeling sleepy.",
            treatmentPreventions = "Once the cause of the fever is determined, treatment is administered. Fevers due to bacteria may be treated with an antibiotic. For a viral fever, an NSAID may be given to relieve symptoms."
        ),
        MedicineData(
            medicineName = "Aspirin",
            uses = "Pain relief, blood thinner",
            image = R.drawable.stomach_flu, // Replace with your image resource
            disease = "Gastroenteritis (stomach flu)",
            causes = "Bacteria and viruses are the most common causes of gastroenteritis. Sometimes it can also be attributed to parasites, food allergies, antibiotics and toxins.",
            symptoms = "The classic symptoms to look out for are diarrhoea, vomiting, stomach pain and pyrexia (you know what that means now!).",
            treatmentPreventions = "Most people will not require any specific treatment as it is a self-limiting disease. It's important to stay hydrated for rapid recovery. Medications may be prescribed to relieve other symptoms, such as fever and vomiting."
        ),
        MedicineData(
            medicineName = "Salbutamol",
            uses = "Asthma relief",
            image = R.drawable.bruise, // Replace with your image resource
            disease = "Contusion (bruise)",
            causes = "Contusions can result from a fall, accident, sports injury or medical procedure. It happens due to the pooling of blood under the skin after an internal blood vessel injury, resulting in discolouration and inflammation.",
            symptoms = "Black-and-blue marks that may appear red or purple at first. The bruised area and surrounding skin may also be tender to touch.",
            treatmentPreventions = "Most contusions fade away without treatment. For more severe contusions, resting and elevating the injured area can prevent swelling and relieve pain. Ice packs or heating pads and pain medications can also help in the healing process."
        )
    )
}
