package com.upiiz.exam_02_agoh

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upiiz.exam_02_agoh.adapters.CommentAdapter
import com.upiiz.exam_02_agoh.models.Comment
import com.upiiz.exam_02_agoh.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        val postId = intent.getIntExtra("POST_ID", -1)
        recyclerView = findViewById(R.id.recyclerViewComments)
        recyclerView.layoutManager = LinearLayoutManager(this)

        if (postId != -1) {
            fetchComments(postId)
        } else {
            showToast("Error al obtener el ID del post.")
        }
    }

    private fun fetchComments(postId: Int) {
        RetrofitClient.instance.getComments(postId).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    val comments = response.body()!!
                    commentAdapter = CommentAdapter(comments)
                    recyclerView.adapter = commentAdapter
                } else {
                    showToast("Error al obtener comentarios.")
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                showToast("Error de conexión.")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
