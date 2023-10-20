package com.example.projectcollab

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectcollab.adapters.CustomAdapter
import com.example.projectcollab.databinding.ActivityItemsBoardBinding
import com.example.projectcollab.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.projemanag.activities.BaseActivity
import com.projemanag.firebase.FirestoreClass
import com.projemanag.model.User
import com.example.projectcollab.databinding.AppBarMainBinding
import com.example.projectcollab.model.Board
import com.projemanag.utils.Constants

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mUserName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Log.d("MainActivity", "Before FAB clicked")
        binding?.fabCreateBoardNew?.setOnClickListener {
            Log.d("MainActivity", "FAB clicked")
            startActivity(Intent(this@MainActivity, CreateBoardActivity::class.java))
        }

        setupActionBar()

        binding?.navView?.setNavigationItemSelectedListener(this)




        FirestoreClass().loadUserData(this@MainActivity)

        binding?.fabCreateBoardNew?.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateBoardActivity::class.java)
            intent.putExtra(Constants.NAME, mUserName)
            startActivity(intent)
        }


    }


    // TODO (Step 7: Implement members of NavigationView.OnNavigationItemSelectedListener.)
    // START
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        // TODO (Step 9: Add the click events of navigation menu items.)
        // START
        when (menuItem.itemId) {
            R.id.nav_my_profile -> {

                startActivity(Intent(this@MainActivity, MyProfileActivity::class.java))
            }

            R.id.nav_sign_out -> {
                // Here sign outs the user from firebase in this device.
                FirebaseAuth.getInstance().signOut()

                // Send the user to the intro screen of the application.
                val intent = Intent(this, IntroActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding?.drawerLayout?.closeDrawer(GravityCompat.START)
        // END
        return true
    }

    private fun setupActionBar() {

        setSupportActionBar(binding?.toolbarMainActivity)
        binding?.toolbarMainActivity?.setNavigationIcon(R.drawable.ic_action_navigation_menu)

        // TODO (Step 3: Add click event for navigation in the action bar and call the toggleDrawer function.)
        // START
        binding?.toolbarMainActivity?.setNavigationOnClickListener {
            toggleDrawer()
        }
        // END
    }

    // TODO (Step 2: Create a function for opening and closing the Navigation Drawer.)
    // START
    /**
     * A function for opening and closing the Navigation Drawer.
     */
    private fun toggleDrawer() {

        if (binding?.drawerLayout?.isDrawerOpen(GravityCompat.START)!!) {
            binding?.drawerLayout?.closeDrawer(GravityCompat.START)
        } else {
            binding?.drawerLayout?.openDrawer(GravityCompat.START)
        }
    }

    // TODO (Step 7: Add a parameter to check whether to read the boards list or not.)
    /**
     * A function to get the current user details from firebase.
     */
    fun updateNavigationUserDetails(user: User) {

        mUserName = user.name

        // The instance of the header view of the navigation view.
        val headerView = binding?.navView?.getHeaderView(0)

        // The instance of the user image of the navigation view.
        val navUserImage = headerView?.findViewById<ImageView>(R.id.iv_user_image)

        // Load the user image in the ImageView.
        Glide
            .with(this@MainActivity)
            .load(user.image) // URL of the image
            .centerCrop() // Scale type of the image.
            .placeholder(R.drawable.ic_user_place_holder) // A default place holder
            .into(navUserImage!!) // the view in which the image will be loaded.

        // The instance of the user name TextView of the navigation view.
        val navUsername = headerView.findViewById<TextView>(R.id.tv_username)
        // Set the user name
        navUsername.text = user.name

        FirestoreClass().getBoardsList(this@MainActivity)

    }

    // TODO (Step 1: Create a function to populate the result of BOARDS list in the UI i.e in the recyclerView.)
    // START
    /**
     * A function to populate the result of BOARDS list in the UI i.e in the recyclerView.
     */
    fun populateBoardsListToUI(boardsList: ArrayList<Board>) {
        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(this@MainActivity, boardsList)

        // Setting the Adapter with the recyclerview
        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

//            // Create an instance of BoardItemsAdapter and pass the boardList to it.
//            val adapter = BoardItemsAdapter(this@MainActivity, boardsList)
//            binding?.rvBoardsList?.adapter = adapter // Attach the adapter to the recyclerView.
    }
    // END

    /**
     * A companion object to declare the constants.
     */
    companion object {
        //A unique code for starting the activity for result
        const val MY_PROFILE_REQUEST_CODE: Int = 11
    }
}