package rakhi.teessideS3380164.telemedicineapp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import rakhi.teessideS3380164.telemedicineapp.ui.theme.AppointmentData

class MyAppointmentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyAppointmentsScreen()
        }
    }
}

@Composable
fun MyAppointmentsScreen() {
    val currentContext = LocalContext.current as Activity

    val patientEmail = PatientDetails.getPatientEmail(currentContext)!!

    var appointmentsList by remember { mutableStateOf(listOf<AppointmentData>()) }
    var isDataFetched by remember { mutableStateOf(true) }

    LaunchedEffect(patientEmail) {
        myAppointments(patientEmail) { appointments ->
            appointmentsList = appointments
            isDataFetched = false
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
                        currentContext.finish()
                    }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "My Appointments",
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(id = R.color.color_light_sky_blue),
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        )
        {

            if (isDataFetched) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {

                if (appointmentsList.isEmpty()) {
                    Spacer(modifier = Modifier.height(36.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        text = "You didn't book any appointments",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )


                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 12.dp)
                    ) {
                        items(appointmentsList.size) { index ->
                            AppointmentDetails(appointmentData = appointmentsList[index])
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun AppointmentDetails(
    appointmentData: AppointmentData
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        listOf(
            "Patient Name" to appointmentData.patientName,
            "Doctor Name" to appointmentData.doctorName,
            "Disease" to appointmentData.diseaseName,
            "Appointment Date" to appointmentData.appointmentDate,
            "Time" to appointmentData.slotTime
        ).forEach { (label, value) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "$label :",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(0.4f),
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = value,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.weight(0.6f),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}


fun myAppointments(patientEmail: String, callback: (List<AppointmentData>) -> Unit) {
    val patientE = patientEmail.replace(".", ",")

    val databaseReference =
        FirebaseDatabase.getInstance().getReference("Appointments/$patientE")

    databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val myAppointmentsList = mutableListOf<AppointmentData>()
            for (donationSnapshot in snapshot.children) {
                val appointment = donationSnapshot.getValue(AppointmentData::class.java)
                appointment?.let { myAppointmentsList.add(it) }
            }
            callback(myAppointmentsList)
        }

        override fun onCancelled(error: DatabaseError) {
            println("Error: ${error.message}")
            callback(emptyList())
        }
    })
}

