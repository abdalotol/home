package com.abd22

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abd22.R
import android.app.ProgressDialog
import android.content.Intent
import com.google.firebase.firestore.FirebaseFirestore


class AddContactActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

     //   db = Firebase.firestore
        val etName = findViewById<EditText>(R.id.Name)
        val etNumber = findViewById<EditText>(R.id.Number)
        val etAddress = findViewById<EditText>(R.id.Address)
        val btn = findViewById<Button>(R.id.button)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")


        btn.setOnClickListener {
            if (etName.text.toString().isNotBlank() && etNumber.text.toString().isNotBlank()
                && etAddress.text.toString().isNotBlank()
            ) {
                progressDialog.show()
                val data = hashMapOf(
                    "name" to etName.text.toString(),
                    "number" to etNumber.text.toString(), "address" to etAddress.text.toString(),
                )
                db.collection("contacts").add(data).addOnSuccessListener {
                    startActivity(Intent(this, MainActivity::class.java))
                    progressDialog.dismiss()
                }
            } else {
                Toast.makeText(this, " Fill All Fields!", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }
        }


    }
}