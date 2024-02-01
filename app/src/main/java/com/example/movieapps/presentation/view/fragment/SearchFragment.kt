package com.example.movieapps.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieapps.databinding.FragmentSearchBinding
import com.example.movieapps.presentation.base.BaseFragment


class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun setupView() {

    }


}