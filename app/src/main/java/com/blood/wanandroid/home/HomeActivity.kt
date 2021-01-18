package com.blood.wanandroid.home

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.blood.wanandroid.R
import com.blood.wanandroid.base.BaseActivity
import com.blood.wanandroid.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        adapter = PagingAdapter()
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter.withLoadStateFooter(LiveLoadStateAdapter(adapter::retry))
        binding.rv.setHasFixedSize(true)

        binding.noDataView.setOnClickListener { adapter.retry() }

        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Error -> binding.noDataView.visibility = View.VISIBLE
                is LoadState.Loading, is LoadState.NotLoading -> binding.noDataView.visibility =
                    if (adapter.itemCount == 0) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launchWhenCreated {
            homeViewModel.getPager().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}