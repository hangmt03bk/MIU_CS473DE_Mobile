package com.cs473.foodiepal.blog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.cs473.foodiepal.R
import com.cs473.foodiepal.databinding.ActivityBlogWebViewBinding
import com.cs473.foodiepal.databinding.ActivityMainBinding

class BlogWebView : AppCompatActivity() {
    private lateinit var binding: ActivityBlogWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlogWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabBack.setOnClickListener{ finish()}
        initWebView()
    }

    private fun initWebView(){
        val url = intent.getStringExtra("url")?:""
        binding.wvBlog.settings.javaScriptEnabled = true
        binding.wvBlog.settings.builtInZoomControls = true
        binding.wvBlog.webViewClient = WebViewClient()
        binding.wvBlog.loadUrl(url)
    }
}