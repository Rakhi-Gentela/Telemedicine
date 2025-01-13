package rakhi.teessideS3380164.telemedicineapp

import android.app.Activity
import android.content.Intent
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
import rakhi.teessideS3380164.telemedicineapp.ui.theme.BookAppointmentActivity

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
    val currentContext = LocalContext.current as Activity
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
                        currentContext.finish()
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
                        location = filteredDoctors[doctor].location,
                        doctorsData = filteredDoctors[doctor]
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
    location: String,
    doctorsData: DoctorsData
) {
    val currentContext = LocalContext.current as Activity

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                DoctorChoice.doctorDetails =doctorsData
                currentContext.startActivity(Intent(currentContext, BookAppointmentActivity::class.java))
            },
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
//                Text(
//                    text = location,
//                    fontSize = 14.sp,
//                    color = Color.Gray
//                )
            }
        }
    }
}

object DoctorChoice{
    var doctorDetails = DoctorsData()
}


fun getDoctorsData(): List<DoctorsData> {
    return listOf(
        DoctorsData(
            doctorName = "Dr. James Webb",
            speciality = "Orthopaedic Surgeon",
            location = "Excellent care from Mr Webb and the MAKO team at Woodlands, Darlington completing two partial knee replacements, May and October 2024."
        ),
        DoctorsData(
            doctorName = "Dr. Fred P Nath",
            speciality = "Neurosurgeon",
            location = "Mr F Nath operated on my son Andrew Peter Suggett in 2013, we were told by Mr Nath that Andrew had a demoid cyst in his brain that would of."
        ),
        DoctorsData(
            doctorName = "Dr. Gerald Fernandez",
            speciality = "General Medicine",
            location = "Excellent GP. Helped with my issues. Caring and very knowledgeable."
        ),
        DoctorsData(
            doctorName = "Dr. Adriaan J Oostdijk",
            speciality = "Cephalalgia (headache)",
            location = "Had a very good experience with this doctor.Very kind and listening skills."
        ),
        DoctorsData(
            doctorName = "Dr. W J Beeby",
            speciality = "Conjunctivitis (sore/pink eye)",
            location = "Very good doctor would not see nobody else."
        ),
        DoctorsData(
            doctorName = "Dr. Nicola J Mayes",
            speciality = "Pharyngitis (sore throat)",
            location = "Really knowledgeable GP who helps you 100%. Always has time to listen and has a friendly manner."
        ),
        DoctorsData(
            doctorName = "Dr. Brian P Corbett",
            speciality = "Tussis (cough)",
            location = "Good doctor . very pleasant , good bedside manner . always polite."
        ),
        DoctorsData(
            doctorName = "Dr. M J Robson",
            speciality = "Pyrexia (fever)",
            location = "Took 16 years to find diagnosis fo a unknow illness that another gp found in 8 months! so glad he retired about time too!"
        )
    )
}


