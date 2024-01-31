package com.example.movieapps.presentation.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movieapps.databinding.FragmentSearchBinding
import com.example.movieapps.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: BaseFragment<FragmentSearchBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun setupView() {

    }
}
