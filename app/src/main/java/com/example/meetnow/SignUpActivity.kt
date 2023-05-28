package com.example.meetnow

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.meetnow.database.DBHelperLocal
import com.example.meetnow.databinding.ActivitySignUpBinding
import com.example.meetnow.model.RegisterModel
import com.example.meetnow.retorfit.MeetNowApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private val sharedPrefFile = "userProfileDetails"
    private val sharedPref = "userProfileDetails"
    private val pickImage = 100
    private var imageUri: Uri? = null

    private val BASE_URL =  "https://meetnow-backend-api.vercel.app/"

    private lateinit var retrofit: Retrofit
    private lateinit var meetNowApi: MeetNowApi

    var cal : Calendar = Calendar.getInstance()
//    private var BASE_URL =

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferencesUser = getSharedPreferences(sharedPref , Context.MODE_PRIVATE)
        val signedVal = sharedPreferencesUser.getBoolean("signed" , false)


        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        meetNowApi = retrofit.create(MeetNowApi::class.java)


        if (signedVal){
            val intent = Intent(this@SignUpActivity , MainActivity::class.java)
            startActivity(intent)
            finish()
        }
//        else{
//            val intent = Intent(this@SignUpActivity , LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }


        binding.loginSignupBtn.setOnClickListener {
            val intent = Intent(this@SignUpActivity , LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.signupBtn.setOnClickListener {


            binding.loading.visibility = View.VISIBLE

            navigateToMain()


            binding.loading.visibility = View.INVISIBLE
        }


    }


    private fun navigateToMain(){

        val nameEtVal = binding.nameSignup.text.toString()
        val phoneEtVal = binding.phoneSignup.text.toString()
        val emailEtVal = binding.emailSignup.text.toString()
        val passwordEtVal = binding.signupPassword.text.toString()
        val dobVal = binding.dobSignup.text.toString()
        val uriImage = imageUri.toString()


        val sharedPreferencesUser = getSharedPreferences(sharedPrefFile , Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPreferencesUser.edit()
//        editor.putString("name",nameEtVal)
//        editor.putString("phone",phoneEtVal)
//        editor.putString("email",emailEtVal)
//        editor.putString("password",passwordEtVal)
//        editor.putBoolean("signed" , true)
//        editor.apply()

        if (nameEtVal.isEmpty() && phoneEtVal.isEmpty() && emailEtVal.isEmpty() && passwordEtVal.isEmpty()) {
            Toast.makeText(this@SignUpActivity, "Please enter all the data..", Toast.LENGTH_SHORT).show();
        }
        else{
            val map: HashMap<String,String> = HashMap()
            map.put("name" , nameEtVal)
            map.put("dob" , dobVal)
            map.put("phone" , phoneEtVal)
            map.put("email" , emailEtVal)
            map.put("password" , passwordEtVal) // update backend
//            map.put("image" , uriImage) // update backend

            val call : Call<RegisterModel> = meetNowApi.addUser(map)
            call.enqueue(object : Callback<RegisterModel>{
                override fun onResponse(call: Call<RegisterModel>, response: Response<RegisterModel>) {
                    if (response.code() == 201){


                        val result = response.body()
                        Toast.makeText(this@SignUpActivity , "Registered Successfully ${result?.name}" , Toast.LENGTH_SHORT)
                            .show()

//                        editor.putString("id" , result?.name)
                        editor.putString("name" , result?.name)
                        editor.putString("email" , result?.email)
                        editor.putString("phone" , result?.phone)
                        editor.putString("dob" , result?.dob)
                        val intent = Intent(this@SignUpActivity , MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else if(response.code() == 500){
                        Toast.makeText(this@SignUpActivity , response.message() , Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
                    Toast.makeText(this@SignUpActivity , t.message , Toast.LENGTH_SHORT)
                        .show()
                }

            })
        }

//        val db = DBHelperLocal(this@SignUpActivity)
//        db.addUserDetails(nameEtVal,phoneEtVal,emailEtVal,passwordEtVal)

        binding.nameSignup.setText("");
        binding.phoneSignup.setText("");
        binding.emailSignup.setText("");
        binding.signupPassword.setText("")

        Toast.makeText(this@SignUpActivity, "User Registered", Toast.LENGTH_SHORT).show();


    }

}