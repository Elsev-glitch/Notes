package com.example.notes.screens.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.model.AppNote
import com.example.notes.screens.main.MainAdapter
import com.example.notes.screens.main.MainViewModel
import com.example.notes.utilits.APP_ACTIVITY
import com.example.notes.utilits.hideKeyBoard
import com.example.notes.utilits.showToast
import kotlinx.android.synthetic.main.fragment_add_new_note.*

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: NoteViewModel
    private lateinit var mCurrentNote:AppNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        mCurrentNote = arguments?.getSerializable("note") as AppNote
        mBinding.noteName.setText(mCurrentNote.name)
        mBinding.noteText.setText(mCurrentNote.text)

        mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        mBinding.btnUpdate.setOnClickListener{
            val name = mBinding.noteName.text.toString()
            val text = mBinding.noteText.text.toString()
            if (name.isEmpty()){
                showToast(getString(R.string.input_name))
            } else {
                mViewModel.update(AppNote(name = name, text = text)) {
                    APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}