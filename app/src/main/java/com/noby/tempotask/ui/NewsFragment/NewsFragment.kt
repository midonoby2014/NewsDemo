package com.noby.tempotask.ui.NewsFragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.noby.tempotask.R
import com.noby.tempotask.data.network.Resource
import com.noby.tempotask.databinding.NewsFragmentBinding
import dagger.hilt.EntryPoint

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noby.tempotask.adapter.ArticlesAdapter
import com.noby.tempotask.data.models.Article
import com.noby.tempotask.data.room.entity.NewsArticleDb
import com.noby.tempotask.util.OnQueryTextChanged
import com.noby.tempotask.util.ViewState

import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Ahmed Noby Ahmed on 6/16/21.
 */
@AndroidEntryPoint
class NewsFragment  : Fragment(), ArticlesAdapter.onItemClickListener {

    private val viewModel: NewsViewModel by viewModels()
    lateinit var binding:NewsFragmentBinding
    lateinit var adapter:ArticlesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.news_fragment, container, false)
        var view:View = binding!!.getRoot()
       initRecycle()
       getData()

        setHasOptionsMenu(true)
        return view
    }

    fun getData(){
       // viewModel.SetNewsPages(requireContext())
        viewModel.getNewsArticles().observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Success -> {
                    hideProgressBar()
                    adapter.submitList(state.data)}
                is ViewState.Loading -> showProgressBar()
                is ViewState.Error -> {
                    hideProgressBar()
                    Toast.makeText(requireActivity() , state.message, Toast.LENGTH_LONG ).show()
                // toast("Something went wrong ¯\\_(ツ)_/¯ => ${state.message}")
                }
            }
        }
    }

    private fun initRecycle() {
         adapter = ArticlesAdapter(this)
        binding.rvList.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        binding.rvList.adapter = adapter
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_fragment_task, menu)
        val searchItem = menu.findItem(R.id.action_seatch)
        val searchView = searchItem.actionView as SearchView

        searchView.OnQueryTextChanged {
            viewModel.searchQuery.value = it
            Log.e(TAG ,  it)

        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_seatch -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    private fun hideProgressBar() {
       binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }



    override fun onItemClick(article: NewsArticleDb) {
        Intent(Intent.ACTION_VIEW)
            .apply { data = Uri.parse(article.url) }
            .also { startActivity(it) }
    }
}