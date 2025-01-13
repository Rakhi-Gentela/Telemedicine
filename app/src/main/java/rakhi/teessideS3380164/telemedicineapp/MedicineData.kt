package rakhi.teessideS3380164.telemedicineapp

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