package team.chichi.pamapp.feature.CollectData

import lombok.Data
import java.sql.Timestamp

@Data
data class CALLData(
    val call_Id: Long,
    val u_Id: String,
    val c_Phone: String,
    val call_Type: Int,
    val call_Time: Timestamp,
    val call_Duration: String,
)