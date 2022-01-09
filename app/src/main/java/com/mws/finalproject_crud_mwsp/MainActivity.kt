package com.mws.finalproject_crud_mwsp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var btnLogin : Button
    private lateinit var edtUsername : EditText
    private lateinit var edtPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "TUTORING APP'S"

        btnLogin = findViewById(R.id.btnLogin)
        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (edtUsername.text.toString().isEmpty()) {
            edtUsername.error = "Masukkan Username"
            return
        }
        if (edtPassword.text.toString().isEmpty()) {
            edtPassword.error = "Masukkan Passowrd"
            return
        }
        if (edtUsername.getText().toString().equals("user") && edtPassword.getText().toString().equals("user")) {
            //correct
            Toast.makeText(this, "Login sucessfull", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DashboardActivity::class.java)
            val data = Bundle()
            data.putString("massage","Ini isi dari bundle")
            intent.putExtras(data)
            startActivity(intent)
        }
        else
        //incorrect
            Toast.makeText(this, "Login Failed !!!", Toast.LENGTH_SHORT).show()
    }
}