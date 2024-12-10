package com.example.demoapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.demoapp.utils.NetworkUtils
import com.example.demoapp.R
import com.example.demoapp.utils.ViewPagerType
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


    if (!NetworkUtils.isInternetAvailable(this)) {
        val cancelIcon = ContextCompat.getDrawable(this, R.drawable.close_circle)
        cancelIcon?.setBounds(0, 0, 60, 60)
        binding.tvStatus.text = getString(R.string.not_connected)
        binding.tvStatus.setCompoundDrawablesRelative(null, null, cancelIcon, null)
    }else{
        val checkIcon = ContextCompat.getDrawable(this, R.drawable.check_circle_round)
        checkIcon?.setBounds(0, 0, 60, 60)
        binding.tvStatus.text = getString(R.string.connected)
        binding.tvStatus.setCompoundDrawablesRelative(null, null, checkIcon, null)
    }

}
}