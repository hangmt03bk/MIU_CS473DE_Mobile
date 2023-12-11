package com.cs473.foodiepal.blog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cs473.foodiepal.R
import com.cs473.foodiepal.databinding.FragmentBlogBinding
import com.cs473.foodiepal.recipes.RecipesDialog
import com.cs473.foodiepal.recipes.UserData


class BlogFragment : Fragment(), BlogDialog.OnSaveBlogListener, BlogAdapter.OnViewBlogListener {
    private lateinit var binding: FragmentBlogBinding
    private lateinit var adapter: BlogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_blog, container, false)
        binding = FragmentBlogBinding.bind(view)

        binding.fabBlogAdd.setOnClickListener{onAddBlog()}
        binding.rvBlogs.layoutManager = LinearLayoutManager(requireContext())
        adapter = BlogAdapter(this, UserData.user.blogs)
        binding.rvBlogs.adapter = adapter

        return binding.root
    }

    private fun onAddBlog() {
        val dialog = BlogDialog()
        dialog.setTargetFragment(this,0)
        dialog.show(parentFragmentManager, BlogDialog::class.java.name)
    }

    override fun onSaveBlog(blog: Blog) {
        UserData.user.blogs.add(0,blog)
        adapter.notifyItemInserted(0)
    }

    override fun onViewBlog(blog: Blog) {
       val intent = Intent(context, BlogWebView::class.java)
        intent.putExtra("url", blog.url)
        startActivity(intent)
    }


}