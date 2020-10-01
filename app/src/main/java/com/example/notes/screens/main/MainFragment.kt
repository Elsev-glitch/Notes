package com.example.notes.screens.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.model.AppNote
import com.example.notes.screens.main.MainViewModel
import com.example.notes.utilits.APP_ACTIVITY
import com.example.notes.utilits.Preference
import com.example.notes.utilits.hideKeyBoard


class MainFragment : Fragment() {

    private var _binding:FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter:MainAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mObserver: Observer<List<AppNote>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        mAdapter = MainAdapter()
        mRecyclerView = mBinding.recycleView
        mRecyclerView.adapter = mAdapter
        //Наблюдатель за изменением листа содержащего модель AppNote. При изменении добавляет новый элемент в mAdapter
        mObserver = Observer {
            val list = it.asReversed()
            mAdapter.setList(list)
        }
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mViewModel.allNote.observe(this, mObserver)
        hideKeyBoard()
        //Нажатие на кнопку добавить заметку
        mBinding.btnAdd.setOnClickListener{
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }

        setHasOptionsMenu(true)
    }

    companion object{
        fun click(note: AppNote){
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_noteFragment, bundle)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.exit -> {
                mViewModel.signOut()
                Preference.setInitUser(false)
                APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_startFragment)
            }
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mViewModel.allNote.removeObserver(mObserver)
        mRecyclerView.adapter = null

    }
}