package com.example.projectcollab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectcollab.databinding.ActivityCreateBoardBinding
import com.example.projectcollab.databinding.ActivitySignInBinding

class CreateBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateBoardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupActionBar()
    }

    private fun setupActionBar() {

        setSupportActionBar(binding?.toolbarCreateBoardActivity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        binding?.toolbarCreateBoardActivity?.setNavigationOnClickListener { onBackPressed() }
    }
}