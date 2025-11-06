package com.example.firebaseauth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebaseauth.databinding.ActivityResigterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class ResigterActiviti : AppCompatActivity() {
    private lateinit var binding: ActivityResigterBinding
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResigterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signIn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        binding.RegiButton.setOnClickListener {

            val email = binding.emailEditText.text.toString().trim()
            val passW = binding.passwordEditText.text.toString().trim()
            val cmPassW = binding.confirmPasswordEditText.text.toString().trim()

            if (email.isEmpty() || passW.isEmpty() || cmPassW.isEmpty()){
                Toast.makeText(this,"please fill all field", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (passW != cmPassW){
                Toast.makeText(this, "Please Comfirm Password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email,passW)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        Toast.makeText(this,"Registration is Successful", Toast.LENGTH_SHORT)
                    }else{
                        Toast.makeText(this,"Registration is Not Successfull", Toast.LENGTH_SHORT)
                    }

                    startActivity(Intent(this, MainActivity::class.java))
                    finish()

                }





        }

    }
}