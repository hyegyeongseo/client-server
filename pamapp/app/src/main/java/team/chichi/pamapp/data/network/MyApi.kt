package team.chichi.pamapp.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import team.chichi.pamapp.data.db.UserDTO

interface MyApi {
    @GET("users/{u_Id}")
    suspend fun getUser(@Path("u_Id") u_Id:String): Response<UserDTO>

}