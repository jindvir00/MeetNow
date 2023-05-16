package com.example.meetnow.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.meetnow.ui.login.LoginFragment
import com.example.meetnow.ui.login.SignUpFragment


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return LoginFragment()
            }
            1 -> {
                return SignUpFragment()
            }
            else -> {
                return LoginFragment()
            }
        }
//        return PlaceholderFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {

        when(position) {
            0 -> {
                return "Login"
            }
            1 -> {
                return "SignUp"
            }
        }
        return super.getPageTitle(position)
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}