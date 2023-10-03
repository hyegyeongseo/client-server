package team.chichi.pamapp.feature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.chichi.pamapp.databinding.ActivityMainBinding
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import team.chichi.pamapp.R
import team.chichi.pamapp.data.network.MyApi

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Retrofit 인스턴스 생성 및 API 인터페이스 구현체 생성 부분을 분리하여 전역 변수로 선언합니다.
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pambackend-pamapi.azuremicroservices.io") // 실제 API 서버의 기본 URL로 변경해야 합니다.
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(MyApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(",","---------------------------------------");

        binding.button.setOnClickListener {
            val u_Id = binding.editTextSelectUId.text.toString()
            fetchData(u_Id)
        }
    }

    private fun fetchData(u_Id: String) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.getUser(u_Id)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val userDTO = response.body()
                        if (userDTO != null) {
                            binding.textViewUserUId.setText(userDTO.u_Id)
                            binding.textViewUName.setText(userDTO.u_Name)

                            val font: Typeface? = ResourcesCompat.getFont(this@MainActivity,
                                R.font.cafe24_ssurround_v2_0
                            )
                            if (font != null) {
                                binding.textViewUserUId.typeface = font
                                binding.textViewUName.typeface = font
                            }

                        } else {
                            Toast.makeText(this@MainActivity, "존재하지 않는 회원 아이디입니다.", Toast.LENGTH_LONG).show()

                            val intent = Intent(this@MainActivity, SignUpActivity::class.java)
                            startActivity(intent)
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Error: ${response.code()}", Toast.LENGTH_LONG).show()

                        // 서버에서 오류 응답을 받은 경우 ErrorActivity로 이동
                        val intent = Intent(this@MainActivity, ErrorActivity::class.java)

                        // 필요한 경우 intent에 추가 정보를 넣어서 ErrorActivity로 전달할 수 있습니다.
                        // 예: intent.putExtra("errorCode", response.code())

                        startActivity(intent)
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity, "Error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
