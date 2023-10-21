package com.example.projectcollab.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectcollab.R
import com.example.projectcollab.databinding.ActivityItemsBoardBinding
import com.example.projectcollab.model.Board

class TestAdapter(

    val context: Context, private val mList: List<Board>) :
    RecyclerView.Adapter<TestAdapter.ViewHolder>() {
    var onClickListener: OnClickListener? = null

    // Inflates the item views which is designed in xml layout file
    // create a new
    // ViewHolder and initializes some private fields to be used by RecyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ActivityItemsBoardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    // Binds each item in the ArrayList to a view

    // Called when RecyclerView needs
    // a new {@link ViewHolder} of the
    // given type to represent
    // an item.

    // This new ViewHolder should be constructed with
    // a new View that can represent the items
    // of the given type. You can either create a
    // new View manually or inflate it from an XML
    // layout file.
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
        // Finally add an onclickListener to the item.
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, model )
            }
        }
    }

    // Gets the number of items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // A function to bind the onclickListener.
    fun SetOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    // onClickListener Interface
    interface OnClickListener {
        fun onClick(position: Int, model: Board)
    }

    // A ViewHolder describes an item view and metadata
    // about its place within the RecyclerView.
    class ViewHolder(binding: ActivityItemsBoardBinding) : RecyclerView.ViewHolder(binding.root) {
        val iv_board_image_list: ImageView = itemView.findViewById(R.id.iv_board_image_list)
        val tv_name: TextView = itemView.findViewById(R.id.tv_name)
        val tv_created_by: TextView = itemView.findViewById(R.id.tv_created_by)
    }
}
