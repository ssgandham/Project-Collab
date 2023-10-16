package com.example.projectcollab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.example.projectcollab.databinding.ActivitySignInBinding
import com.example.projectcollab.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.projemanag.activities.BaseActivity
import com.projemanag.firebase.FirestoreClass
import com.projemanag.model.User

class SignInActivity : BaseActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

//        setupActionBar()

        binding?.btnSignIn?.setOnClickListener {
            signInRegisteredUser()
        }
        // END
    }
    /**
     * A function for Sign-In using the registered user using the email and password.
     */
    private fun signInRegisteredUser() {
        // Here we get the text from editText and trim the space
        val email: String = binding?.etEmail?.text.toString().trim { it <= ' ' }
        val password: String = binding?.etPassword?.text.toString().trim { it <= ' ' }

        if (validateForm(email, password)) {

            // Sign-In using FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // TODO (Step 2: Remove the toast message and call the FirestoreClass signInUser function to get the data of user from database. And also move the code of hiding Progress Dialog and Launching MainActivity to Success function.)
                        // Calling the FirestoreClass signInUser function to get the data of user from database.
                        FirestoreClass().signInUser(this@SignInActivity)
                        // END
                    } else {
                        Toast.makeText(
                            this@SignInActivity,
                            task.exception!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    /**
     * A function to validate the entries of a user.
     */
    private fun validateForm(email: String, password: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            showErrorSnackBar("Please enter email.")
            false
        } else if (TextUtils.isEmpty(password)) {
            showErrorSnackBar("Please enter password.")
            false
        } else {
            true
        }
    }

    // TODO (Step 4: Create a function to get the user details from the firestore database after authentication.)
    // START
    /**
     * A function to get the user details from the firestore database after authentication.
     */
    fun signInSuccess(user: User) {

        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        finish()
    }
}