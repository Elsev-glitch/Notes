package com.example.notes.screens.add_new_note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNewNoteBinding
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.model.AppNote
import com.example.notes.screens.main.MainViewModel
import com.example.notes.utilits.APP_ACTIVITY
import com.example.notes.utilits.showToast

class AddNewNoteFragment : Fragment() {

    private var _binding:FragmentAddNewNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: AddNewNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewNoteBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        mViewModel = ViewModelProvider(this).get(AddNewNoteViewModel::class.java)

        mBinding.btnAdd.setOnClickListener{
            val name = mBinding.inputName.text.toString()
            val text = mBinding.inputText.text.toString()
            if (name.isEmpty()){
                showToast(getString(R.string.input_name))
            } else {
                mViewModel.insert(AppNote(name = name, text = text)) {
                    APP_ACTIVITY.navController.navigate(R.id.action_addNewNoteFragment_to_mainFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}