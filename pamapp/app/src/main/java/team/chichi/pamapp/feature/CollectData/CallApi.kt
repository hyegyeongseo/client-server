package team.chichi.pamapp.feature.CollectData

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CallApi {
    @POST("history/call")
    fun saveCALLData(@Body callList: MutableList<CALLData>): Call<Void>
}