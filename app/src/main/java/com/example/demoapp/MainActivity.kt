package com.example.demoapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.demoapp.adapters.ViewPagerAdapter
import com.example.demoapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewPagerArray = arrayOf(ViewPagerType.APPLICATION,ViewPagerType.SETTINGS)
    private lateinit var  binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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


    }
}