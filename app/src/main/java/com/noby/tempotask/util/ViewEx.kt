package com.noby.tempotask.util


import androidx.appcompat.widget.SearchView

/**
 * Created by Ahmed Noby Ahmed on 6/16/21.
 */


inline fun SearchView.OnQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            return true
        }


    })
}