package com.example.projectcollab.adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectcollab.R
import com.example.projectcollab.model.Board

class CustomAdapter(

    private val context: Context, private val mList: List<Board>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var onClickListener: OnClickListener? = null

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_items_board, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = mList[position]

        // sets the image to the imageview from our itemHolder class
//        holder.imageView.setImageResource(ItemsViewModel.image)

        Glide
            .with(context)
            .load(model.image)
            .centerCrop()
            .placeholder(R.drawable.ic_board_place_holder)
            .into(holder.iv_board_image_list)
        // sets the text to the textview from our itemHolder class
        holder.tv_name.text = model.name
        holder.tv_created_by.text = "Created By : ${model.createdBy}"


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val iv_board_image_list: ImageView = itemView.findViewById(R.id.iv_board_image_list)
        val tv_name: TextView = itemView.findViewById(R.id.tv_name)
        val tv_created_by: TextView = itemView.findViewById(R.id.tv_created_by)
    }

    /**
     * A function for OnClickListener where the Interface is the expected parameter..
     */
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    /**
     * An interface for onclick items.
     */
    interface OnClickListener {
        fun onClick(position: Int, model: Board)
    }
}