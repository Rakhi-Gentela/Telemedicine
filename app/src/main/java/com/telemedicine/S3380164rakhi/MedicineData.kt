package com.telemedicine.S3380164rakhi

data class MedicineData (
    val medicineName: String = "",
    val uses: String = "",
    val image: Int = 0,
    val disease: String = "",
    var causes: String = "",
    var symptoms: String = "",
    var treatmentPreventions: String = "",

)


object ClickedDisease{
    var medicineData = MedicineData()
}