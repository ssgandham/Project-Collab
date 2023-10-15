package com.example.projectcollab

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectcollab.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        binding = ActivityIntroBinding.inflate(layoutInflater)
//        val typeface: Typeface =
//            Typeface.createFromAsset(assets, "carbon bl.ttf")
//
//        binding?.appIntroName?.typeface = typeface

        binding?.btnSignInIntro?.setOnClickListener {

            // Launch the sign in screen.
            startActivity(Intent(this@IntroActivity, SignInActivity::class.java))
        }

        binding?.btnSignUpIntro?.setOnClickListener {

            // Launch the sign up screen.
            startActivity(Intent(this@IntroActivity, SignUpActivity::class.java))
        }
    }
}