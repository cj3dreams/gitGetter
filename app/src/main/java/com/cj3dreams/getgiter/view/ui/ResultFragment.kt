package com.cj3dreams.getgiter.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cj3dreams.getgiter.MainActivity
import com.cj3dreams.getgiter.R
import com.cj3dreams.getgiter.view.adapter.ResultAdapter
import com.cj3dreams.getgiter.vm.ResultViewModel
import kotlinx.android.synthetic.main.fragment_result.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.content.Intent
import android.net.Uri
import com.cj3dreams.getgiter.model.entities.DownloadsEntity


class ResultFragment : Fragment(), View.OnClickListener {
    private val resultViewModel: ResultViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_result, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).searchLiveData.observe(viewLifecycleOwner, {
            resultViewModel.getRepoByUsername(it)
        })

        recyclerViewResult.layoutManager = LinearLayoutManager(requireContext())
        resultViewModel.repoByUserNameLiveData.observe(viewLifecycleOwner, {
            recyclerViewResult.adapter = ResultAdapter(it, this)
        })

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.itemRepoDownloadTx -> {
                val bundle = Bundle()
                bundle.putSerializable("downloadsEntity", v.tag as DownloadsEntity)
                (activity as MainActivity).navController
                    .navigate(R.id.action_resultFragment_to_downloadsFragment, bundle)
            }
            R.id.itemRepoCardView -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(v.tag as String)))
        }
    }
}