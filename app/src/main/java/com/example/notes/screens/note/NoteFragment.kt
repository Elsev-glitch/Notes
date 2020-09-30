package com.example.notes.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.model.AppNote
import com.example.notes.utilits.APP_ACTIVITY
import com.example.notes.utilits.showToast

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
        setHasOptionsMenu(true)
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
                mViewModel.update(AppNote(id=mCurrentNote.id, name = name, text = text, idFirebase = mCurrentNote.idFirebase)) {
                    APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete -> {
                mViewModel.delete(mCurrentNote){
                    APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}