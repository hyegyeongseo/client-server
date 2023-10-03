package team.chichi.pamapp.feature.CollectData

import android.Manifest
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.kakao.sdk.user.Constants.TAG
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.chichi.pamapp.R
import team.chichi.pamapp.databinding.ActivityCallCollectionBinding
import java.sql.Timestamp

class CallCollectionActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityCallCollectionBinding

    companion object {
        private const val BASE_URL = "https://pambackend-pamapi.azuremicroservices.io"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_collection)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_CALL_LOG),
            MODE_PRIVATE
        )

        val call_save_button = findViewById<Button>(R.id.call_save_button)
        call_save_button.setOnClickListener{

            UserApiClient.instance.me { user, error ->
                if (error != null) {
                    Log.e(TAG, "사용자 정보 요청 실패 $error")
                } else if (user != null) {
                    Log.i(TAG, "사용자 정보 요청 성공 : $user")
                    val u_Id = user.id.toString()
                    //binding.txtEmail.text = user.kakaoAccount?.email
                    if (u_Id != null) {
                        LoadCallData(u_Id)
                    }
                }
            }
        }

    }


    private fun LoadCallData(u_Id : String) {
        val callSet = arrayOf(
            CallLog.Calls._ID,
            u_Id,
            CallLog.Calls.NUMBER,
            CallLog.Calls.TYPE,
            CallLog.Calls.DATE,
            CallLog.Calls.DURATION
        )
        val c: Cursor? = contentResolver.query(CallLog.Calls.CONTENT_URI, callSet, null, null, null)
        if (c == null || !c.moveToFirst()) {
            Log.d("통화기록존재여부", "통화기록없음")
            return
        }

        val callBuff = StringBuffer()
        c.moveToFirst()
        do {
            callBuff.append(c.getString(0)) // CallLog.Calls._ID // call_Id

            callBuff.append(c.getString(1))

            callBuff.append(c.getString(2)) // CallLog.Calls.NUMBER // c_Phone

            when (c.getInt(3)) { // CallLog.Calls.TYPE // call_Type
                CallLog.Calls.INCOMING_TYPE -> callBuff.append(0) // 0
                CallLog.Calls.OUTGOING_TYPE -> callBuff.append(1) // 1
                CallLog.Calls.MISSED_TYPE -> callBuff.append("부재중") // 미사용
                CallLog.Calls.REJECTED_TYPE -> callBuff.append("종료") // 미사용
            }

            val callDate = c.getLong(4) // CallLog.Calls.DATE // call_Time
            val call_Time = Timestamp(callDate)
            callBuff.append(call_Time)

            callBuff.append(c.getString(5)) // CallLog.Calls.DURATION // call_Duration

        } while (c.moveToNext())

        c.close()

        val lines = callBuff.toString().split("\n")
        val callList = mutableListOf<CALLData>()
        for (line in lines) {
            if (line.isBlank()) {
                continue
            }
            val tokens = line.split(":")
            val callData = CALLData(
                call_Id = tokens[0].toLong(),
                u_Id = tokens[1],
                c_Phone = tokens[2],
                call_Type = tokens[3].toInt(),
                call_Time = Timestamp.valueOf(tokens[4]),
                call_Duration = tokens[5],
            )
            callList.add(callData)
        }


        sendCallDataToServer(callList)

    }


    private fun sendCallDataToServer(callList: MutableList<CALLData>) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val callApi = retrofit.create(CallApi::class.java)
        val call = callApi.saveCALLData(callList)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("데이터 전송", "Call data 저장 요청 성공")
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("데이터 전송", "Call data 저장 요청 성공")
            }
        })

    }

}
