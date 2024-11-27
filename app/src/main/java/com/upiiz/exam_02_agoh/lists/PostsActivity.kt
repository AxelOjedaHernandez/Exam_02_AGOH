package com.upiiz.exam_02_agoh

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upiiz.exam_02_agoh.adapters.PostAdapter
import com.upiiz.exam_02_agoh.models.Post
import com.upiiz.exam_02_agoh.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        val userId = intent.getIntExtra("USER_ID", -1)
        recyclerView = findViewById(R.id.recyclerViewPosts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        if (userId != -1) {
            fetchPosts(userId)
        } else {
            showToast("Error al obtener el ID del usuario.")
        }
    }

    private fun fetchPosts(userId: Int) {
        RetrofitClient.instance.getPosts(userId).enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body()!!
                    postAdapter = PostAdapter(posts) { post ->
                        val intent = Intent(this@PostsActivity, CommentsActivity::class.java)
                        intent.putExtra("POST_ID", post.id)
                        startActivity(intent)
                    }
                    recyclerView.adapter = postAdapter
                } else {
                    showToast("Error al obtener los posts.")
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                showToast("Error de conexión.")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
