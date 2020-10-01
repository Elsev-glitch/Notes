package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.utilits.APP_ACTIVITY
import com.example.notes.utilits.Preference

class MainActivity : AppCompatActivity() {

    private var _binding:ActivityMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mToolbar: Toolbar
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        Preference.getPreference(this)
        init()
    }

    private fun init() {
        mToolbar = mBinding.toolbar
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        APP_ACTIVITY = this
        title = getString(R.string.title)
        setSupportActionBar(mToolbar)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}