package com.rakhiS3380164.telemedicine

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