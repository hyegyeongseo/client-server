package team.chichi.pamapp.data.db

import com.google.gson.annotations.SerializedName
import lombok.Getter
import lombok.Setter
import lombok.ToString

@Getter
@Setter
@ToString
class UserDTO {

    @SerializedName("u_Id")
    val u_Id: String? = null

    @SerializedName("u_Pw")
    private val u_Pw:String? = null

    @SerializedName("u_Name")
    val u_Name:String? = null

    @SerializedName("u_Phone")
    private val u_Phone:String? = null

    @SerializedName("u_Email")
    private val u_Email:String? = null

    @SerializedName("u_Address")
    private val u_Address:String? = null

    @SerializedName("u_DetailAdd")
    private val u_DetailAdd:String? = null

    @SerializedName("u_ExtraAdd")
    private val u_ExtraAdd:String? = null


}