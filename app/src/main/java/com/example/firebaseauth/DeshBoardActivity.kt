package com.example.firebaseauth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebaseauth.databinding.ActivityDeshBoardBinding
import com.google.firebase.auth.FirebaseAuth

class DeshBoardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeshBoardBinding
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDeshBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.Logoutbtn.setOnClickListener {
            auth.signOut()
            Toast.makeText(this,"logout Successfull", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ResigterActiviti::class.java))
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser ==null ){
            Toast.makeText(this,"User Not found.Please Registration",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ResigterActiviti::class.java))
            finish()
        }else{
            currentUser.reload().addOnCompleteListener { task ->
                if (!task.isSuccessful || FirebaseAuth.getInstance().currentUser == null){
                    Toast.makeText(this,"Your Account no longer exists.",Toast.LENGTH_SHORT).show()
                    auth.signOut()
                    startActivity(Intent(this, ResigterActiviti::class.java))
                    finish()
                }
            }
        }
    }
}