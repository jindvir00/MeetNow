package com.example.meetnow

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class ProfileFragment : Fragment() {

    private val sharedPrefFile = "userProfileDetails"


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileName = view.findViewById<TextView>(R.id.username_profile_tv)
        val profileEmail = view.findViewById<TextView>(R.id.email_profile_tv)
        val profilePhone = view.findViewById<TextView>(R.id.phone_profile_tv)
        val profileDob = view.findViewById<TextView>(R.id.dob_profile_tv)
        val logoutBtn = view.findViewById<Button>(R.id.logout_btn)
        val sharedPreferencesUser = view.context.getSharedPreferences(sharedPrefFile , Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPreferencesUser.edit()

        logoutBtn.setOnClickListener {

            val intent = Intent(view.context.applicationContext , LoginActivity::class.java)
            editor.putBoolean("signed",false)
            editor.apply()
            startActivity(intent)
            activity?.finish()

        }


        val profileNameVal = sharedPreferencesUser.getString("name", "Error in Loading")
        val profileEmailVal = sharedPreferencesUser.getString("email", "Error in Loading")
        val profileDobVal = sharedPreferencesUser.getString("dob", "Error in Loading")
        val profilePhoneVal = sharedPreferencesUser.getString("phone", "Error in Loading")




        if( profileNameVal.equals("Error in Loading") && profileEmailVal.equals("Error in Loading") && profilePhoneVal.equals("Error in Loading")){
            profileName.setText(profileNameVal.toString())
            profileEmail.setText(profileEmailVal.toString())
            profilePhone.setText(profilePhoneVal.toString())
            profilePhone.setText(profileDobVal.toString())
        }else{
            profileName.setText(profileNameVal.toString())
            profileEmail.setText(profileEmailVal.toString())
            profilePhone.setText(profilePhoneVal.toString())
            profileDob.setText(profileDobVal.toString())
        }
    }
}