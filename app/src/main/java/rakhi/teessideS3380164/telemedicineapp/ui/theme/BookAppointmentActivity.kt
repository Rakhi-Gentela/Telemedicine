package rakhi.teessideS3380164.telemedicineapp.ui.theme

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.FirebaseDatabase
import rakhi.teessideS3380164.telemedicineapp.DoctorChoice
import rakhi.teessideS3380164.telemedicineapp.PatientDetails
import rakhi.teessideS3380164.telemedicineapp.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class BookAppointmentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookAppointmentScreen()
        }
    }
}

@Composable
fun BookAppointmentScreen() {
    val context = LocalContext.current as Activity

    var showDialog by remember { mutableStateOf(false) }


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
                text = "Book Appointment",
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(id = R.color.color_light_sky_blue),
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(id = R.drawable.doctor),
                contentDescription = "Doctor Image",
                modifier = Modifier
                    .size(100.dp)
            )

            Text(
                text = DoctorChoice.doctorDetails.doctorName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = DoctorChoice.doctorDetails.speciality,
                fontSize = 16.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = DoctorChoice.doctorDetails.location,
                fontSize = 14.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier
                    .width(200.dp)
                    .clip(RoundedCornerShape(12.dp)) // Makes the borders curved
                    .background(color = colorResource(id = R.color.color_deep_blue))
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.color_light_sky_blue),
                        shape = RoundedCornerShape(12.dp) // Same curvature as clip
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .clickable {
                        showDialog = true
                    },
                text = "Book Appointment",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.color_light_sky_blue)
            )

            if (showDialog) {
                AppointmentDialog(onDismiss = { showDialog = false })
            }

        }
    }
}

data class AppointmentData(
    val patientName: String = "",
    val doctorName: String = "",
    val diseaseName: String = "",
    val appointmentDate: String = "",
    val slotTime: String = ""
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppointmentDialog(
    onDismiss: () -> Unit
) {
//    var patientName by remember { mutableStateOf(TextFieldValue("")) }
//    var doctorName by remember { mutableStateOf(TextFieldValue("")) }
//    var diseaseName by remember { mutableStateOf(TextFieldValue("")) }
    var appointmentDate by remember { mutableStateOf("") }
    var selectedSlot by remember { mutableStateOf("") }

    val context = LocalContext.current
    val firebaseDatabase = FirebaseDatabase.getInstance().getReference("Appointments")

    val patName = PatientDetails.getPatientName(context)
    val docName = DoctorChoice.doctorDetails.doctorName
    val disName = DoctorChoice.doctorDetails.speciality

    // Slot timings
    val slotTimings = listOf(
        "9 AM - 10 AM", "10 AM - 11 AM", "11 AM - 12 PM", "12 PM - 1 PM",
        "1 PM - 2 PM", "2 PM - 3 PM", "3 PM - 4 PM", "4 PM - 5 PM", "5 PM - 6 PM"
    )

    // Date Picker
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            appointmentDate = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Book Appointment", fontSize = 20.sp, color = Color.Black)
        },
        buttons = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = patName!!,
                    onValueChange = { },
                    label = { Text("Patient Name") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = docName,
                    onValueChange = { },
                    label = { Text("Doctor Name") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = disName,
                    onValueChange = { },
                    label = { Text("Disease Name") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = appointmentDate,
                    onValueChange = {},
                    label = { Text("Appointment Date") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    enabled = false,
                    trailingIcon = {
                        IconButton(onClick = { datePickerDialog.show() }) {
                            Icon(Icons.Default.DateRange, contentDescription = "Select Date")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Select Slot", fontSize = 16.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    slotTimings.forEach { slot ->
                        Chip(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .border(
                                    width = 1.dp,
                                    color = if (selectedSlot == slot) Color.DarkGray else Color.Transparent,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 6.dp),
                            onClick = { selectedSlot = slot }
                        ) {
                            Text(
                                text = slot,
                                color = if (selectedSlot == slot) Color.White else Color.Black,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (patName.isNotEmpty() &&
                            docName.isNotEmpty() &&
                            disName.isNotEmpty() &&
                            appointmentDate.isNotEmpty() &&
                            selectedSlot.isNotEmpty()
                        ) {
                            val appointment = AppointmentData(
                                patientName = patName,
                                doctorName = docName,
                                diseaseName = disName,
                                appointmentDate = appointmentDate,
                                slotTime = selectedSlot
                            )

                            val patientEMail = PatientDetails.getPatientEmail(context)!!.replace(".", ",")

                            val currentDate =
                                SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                            val appointmentNumber = currentDate.format(Date())

                            try {
                                firebaseDatabase.child(patientEMail).child(appointmentNumber)
                                    .setValue(appointment)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(
                                                context,
                                                "Appointment Booked",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            onDismiss()
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Appointment Bookeing Failed",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            onDismiss()
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Toast.makeText(
                                            context,
                                            "Appointment Booking Failed",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            }catch (e: Exception)
                            {
                                Log.e("BookAppointmentActivity", "Error: $e")
                            }
                        } else {
                            Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT)
                                .show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Submit",
                        color = Color.White
                    )
                }
            }
        }
    )
}