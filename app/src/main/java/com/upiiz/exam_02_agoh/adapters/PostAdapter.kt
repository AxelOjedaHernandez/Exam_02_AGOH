package com.upiiz.exam_02_agoh.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.upiiz.exam_02_agoh.R
import com.upiiz.exam_02_agoh.models.Post

class PostAdapter(
    private val postList: List<Post>,
    private val onItemClick: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val postTitle: TextView = itemView.findViewById(R.id.tvPostTitle)
        private val postBody: TextView = itemView.findViewById(R.id.tvPostBody)

        fun bind(post: Post) {
            postTitle.text = post.title
            postBody.text = post.body
            itemView.setOnClickListener { onItemClick(post) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size
}
