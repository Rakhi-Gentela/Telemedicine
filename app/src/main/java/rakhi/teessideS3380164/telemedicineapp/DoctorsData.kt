package rakhi.teessideS3380164.telemedicineapp

data class DoctorsData(

    var doctorName: String = "",
    var speciality: String = "",
    var location: String = "",

    )

data class PatientData(
    var patientFN: String = "",
    var patientAge: String = "",
    var patientEmail: String = "",
    var patientPass: String = "",


)