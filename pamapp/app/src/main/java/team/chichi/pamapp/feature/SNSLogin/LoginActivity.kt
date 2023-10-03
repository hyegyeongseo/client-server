package team.chichi.pamapp.feature.SNSLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.model.AuthErrorCause.*
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.Constants.TAG
import com.kakao.sdk.user.UserApiClient
import team.chichi.pamapp.R
import team.chichi.pamapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_login)

        binding.kakaoLoginButton.setOnClickListener {
            kakaoLogin()
        }

    }
    private fun kakaoLogin(){
        // 사용자가 앞서 로그인을 통해 발급 받은 토큰이 있는지 확인 // OpenID Connect ID token/access token
        if (AuthApiClient.instance.hasToken()) {
            // 액세스 토큰의 유효성을 확인
            UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError() == true) {
                        // 로그인 필요
                        Toast.makeText(this, "로그인이 필요합니다", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        // 기타 에러
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    // 토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
                    //Log.i(TAG, "토큰 정보 보기 성공" + "\n회원번호: ${tokenInfo?.id}" + "\n만료시간: ${tokenInfo?.expiresIn}초")// 임시 데이터
                    Toast.makeText(this, "토큰이 유효함을 확인하였습니다",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, AfterLoginSuccessActivity::class.java)
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }
            }

        }
        else {
            // 로그인 필요
            Toast.makeText(this, "로그인이 필요합니다", Toast.LENGTH_SHORT).show()
        }

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> { // 클라이언트 ID가 잘못된 경우
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음 (android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AfterLoginSuccessActivity::class.java)
                startActivity(intent)
            }
        }

        val context = this

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오 계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)){
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오 계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled){
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오 계정이 없는 경우, 카카오 계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)

                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공")
                    val intent = Intent(this, AfterLoginSuccessActivity::class.java)
                    startActivity(intent)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }


    }



}