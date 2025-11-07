package com.example.firebaseauth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebaseauth.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this, DeshBoardActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this,"Login Not Successfull",Toast.LENGTH_SHORT).show()
                    }
                }
        }


        binding.registerText.setOnClickListener {
           val intent = Intent(this, ResigterActiviti::class.java)
            startActivity(intent)
        }

    }

    override fun onStart(){
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser!=null){
            startActivity(Intent(this, DeshBoardActivity::class.java))
            finish()
        }
    }
}