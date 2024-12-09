package com.example.demoapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.demoapp.NetworkUtils
import com.example.demoapp.R
import com.example.demoapp.ViewPagerType
import com.example.demoapp.adapters.ViewPagerAdapter
import com.example.demoapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewPagerArray = arrayOf(ViewPagerType.APPLICATION, ViewPagerType.SETTINGS)
    private lateinit var  binding : ActivityMainBinding
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


private fun init() {
    val viewPager = binding.viewPager
    val tabLayout = binding.tabLayout

    val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
    viewPager.adapter = viewPagerAdapter


    val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
    viewPager.adapter = adapter

    TabLayoutMediator(tabLayout, viewPager) { tab, position ->
        tab.text = viewPagerArray[position]
    }.attach()

    Glide.with(this)
        .load(R.mipmap.user_default_image_round)
        .apply(RequestOptions.circleCropTransform())
        .placeholder(R.mipmap.user_default_image_round)
        .into(binding.ivUserImage)

    val cancelIcon = ContextCompat.getDrawable(this, R.drawable.cancel_icon)
    if (!NetworkUtils.isInternetAvailable(this)) {
        binding.tvStatus.text = getString(R.string.not_connected)
        binding.tvStatus.setCompoundDrawablesRelative(null, null, cancelIcon, null)
    }
}
}