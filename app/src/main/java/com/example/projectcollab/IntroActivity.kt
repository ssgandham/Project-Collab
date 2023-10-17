package com.example.projectcollab

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.projectcollab.databinding.ActivityIntroBinding
import com.example.projectcollab.databinding.ActivitySignInBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        binding = ActivityIntroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding?.btnSignInIntro?.setOnClickListener {

            // Launch the sign in screen.
            startActivity(Intent(this, SignInActivity::class.java))
        }

        binding?.btnSignUpIntro?.setOnClickListener {
            Log.i("IntroActivity", "SignUpActivity")
            // Launch the sign up screen.
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}