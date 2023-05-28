package com.example.meetnow

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.meetnow.databinding.ActivityLoginBinding
import com.example.meetnow.model.LoginModel
import com.example.meetnow.model.RegisterModel
import com.example.meetnow.retorfit.MeetNowApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val sharedPrefFile = "userProfileDetails"
    private lateinit var retrofit: Retrofit
    private lateinit var meetNowApi: MeetNowApi
    private val BASE_URL =  "https://meetnow-backend-api.vercel.app/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        meetNowApi = retrofit.create(MeetNowApi::class.java)

        binding.loginBtn.setOnClickListener {
            navigateToMain()
        }
    }

    private fun navigateToMain(){
        binding.loading.visibility = View.VISIBLE

        val emailLoginValue = binding.loginEmail.text.toString()
        val passwordLoginValue = binding.loginPassword.text.toString()
        if(validateEmail(emailLoginValue) == validatePassword(passwordLoginValue))
        {
            val map: HashMap<String,String> = HashMap()
            map.put("email" , emailLoginValue)
//            map.put("password" , passwordLoginValue)

            val call : Call<LoginModel> = meetNowApi.getUser(map)

            call.enqueue(object : Callback<LoginModel>{
                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    if (response.code() == 200){

                        val result = response.body()
                        val email = result?.email


                        if(email.toString() == emailLoginValue){

                            Toast.makeText(this@LoginActivity , "Welcome $email", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this@LoginActivity , MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }

                    }else{
                        Toast.makeText(this@LoginActivity , "Wrong Creds" , Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                    Toast.makeText(this@LoginActivity , t.message , Toast.LENGTH_SHORT)
                        .show()
                }

            })
        }
        else{
            Toast.makeText(this , R.string.check_details , Toast.LENGTH_SHORT)
                .show()
        }

        binding.loading.visibility = View.INVISIBLE
    }

    private fun validateEmail(emailVal: String) : Boolean{

        return if(emailVal.isNotEmpty()) {

            val pattern: Pattern = Patterns.EMAIL_ADDRESS
            val emailValid =  pattern.matcher(emailVal).matches()
            emailValid
        } else
            false


    }

    private fun validatePassword(passwordVal: String) : Boolean{

        return passwordVal.length > 4 && passwordVal.isNotEmpty()

    }
}