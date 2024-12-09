package com.example.demoapp.frgments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapp.ApiResult
import com.example.demoapp.App
import com.example.demoapp.R
import com.example.demoapp.adapters.ApplicationsAdapter
import com.example.demoapp.databinding.FragmentApplicationBinding
import com.example.demoapp.viewmodel.KidsAppDataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ApplicationFragment : Fragment() {


    private lateinit var binding : FragmentApplicationBinding
    private  val viewModel : KidsAppDataViewModel by activityViewModels()
    private lateinit var adapter: ApplicationsAdapter
    private lateinit var appsList : List<App>


    private fun init() {
        // Observe the LiveData
        observeKidsAppData()
        binding.progressbar.visibility = View.VISIBLE
        // Fetch data
        viewModel.getKidsAppData()
        adapter = ApplicationsAdapter()


        binding.etSearch.doAfterTextChanged {
            val searchQuery = it.toString()
            if (searchQuery.isEmpty()) {
                showData(appsList)
            }
            else{
            val filteredList = appsList.filter { app -> app.appName.contains(searchQuery, ignoreCase = true) }
            showData(filteredList)}
        }


    }

    private fun observeKidsAppData() {
        viewModel.kidsAppData.observe(viewLifecycleOwner, {
            when (it) {
                is ApiResult.Success -> {
                    Log.d("KidsAppData", "Data: ${it.data}")
                    binding.progressbar.visibility = View.GONE
                    binding.tvWrong.visibility = View.GONE
                    appsList = it.data?.appList ?: emptyList()
                    showData(appsList)
                }
                is ApiResult.Error -> {
                    binding.progressbar.visibility = View.GONE
                    Log.d("KidsAppData", "Error: ${it.message}")
                    binding.tvWrong.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    binding.tvWrong.visibility = View.VISIBLE
                    binding.progressbar.visibility = View.GONE
                    Log.d("KidsAppData", "Loading")
                }
            }
        })
    }

    private fun showData(appsList : List<App>) {
        binding.rvAppsList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvAppsList.adapter = adapter
        adapter.submitList(appsList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentApplicationBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

}