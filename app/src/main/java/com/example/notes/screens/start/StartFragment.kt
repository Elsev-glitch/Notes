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
import com.example.notes.utilits.APP_ACTIVITY
import com.example.notes.utilits.TYPE_ROOM

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
        init()
    }

    private fun init() {
        mViewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        mBinding.btnRoom.setOnClickListener {
            mViewModel.initRepository(TYPE_ROOM){
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}