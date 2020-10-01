package com.example.notes.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.databinding.FragmentStartBinding
import com.example.notes.screens.main.MainViewModel
import com.example.notes.utilits.*

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: StartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        mViewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        if (Preference.getInitUser()){
            mViewModel.initRepository(Preference.getDatabaseType()){
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        } else {
            init()
        }
    }

    private fun init() {
        mBinding.btnRoom.setOnClickListener {
            mViewModel.initRepository(TYPE_ROOM){
                Preference.setDatabaseType(TYPE_ROOM)
                Preference.setInitUser(true)
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }
        mBinding.btnFirebase.setOnClickListener {
            mBinding.email.visibility = View.VISIBLE
            mBinding.password.visibility = View.VISIBLE
            mBinding.btnLogin.visibility = View.VISIBLE
            mBinding.btnLogin.setOnClickListener {
                val email = mBinding.email.text.toString()
                val password = mBinding.password.text.toString()
                if (email.isNotEmpty() && password.isNotEmpty()){
                    EMAIL = email
                    PASSWORD = password
                    mViewModel.initRepository(TYPE_FIREBASE){
                        Preference.setDatabaseType(TYPE_FIREBASE)
                        Preference.setInitUser(true)
                        APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
                    }
                } else {
                    showToast(getString(R.string.start_empty))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}