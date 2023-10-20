package com.example.projectcollab

import android.os.Bundle
import com.projemanag.activities.BaseActivity

class ContentMainActivity :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_board)
    }
}