package com.upiiz.exam_02_agoh.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.upiiz.exam_02_agoh.R
import com.upiiz.exam_02_agoh.models.Comment

class CommentAdapter(
    private val commentList: List<Comment>
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val commentName: TextView = itemView.findViewById(R.id.tvCommentName)
        private val commentEmail: TextView = itemView.findViewById(R.id.tvCommentEmail)
        private val commentBody: TextView = itemView.findViewById(R.id.tvCommentBody)

        fun bind(comment: Comment) {
            commentName.text = comment.name
            commentEmail.text = comment.email
            commentBody.text = comment.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(commentList[position])
    }

    override fun getItemCount(): Int = commentList.size
}
