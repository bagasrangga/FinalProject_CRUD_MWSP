package com.mws.finalproject_crud_mwsp

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_manage_student.*
import kotlinx.android.synthetic.main.student_list.*
import org.json.JSONObject

class ManageStudentActivity : AppCompatActivity() {

    lateinit var i : Intent
    private var gender = "Pria"
    private var jenjang = "KULIAH"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_student)
        i = intent

        supportActionBar?.title = "TUTORING APP'S"

        if(i.hasExtra("editmode")){

            if(i.getStringExtra("editmode").equals("1")){

                onEditMode()

            }

        }

        btnUpdate.setOnClickListener {
            update()
        }

        btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Hapus data ini ?")
                .setPositiveButton("HAPUS", DialogInterface.OnClickListener { dialogInterface, i ->
                    delete()
                })
                .setNegativeButton("BATAL", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
                .show()
        }

        rgGender.setOnCheckedChangeListener { radioGroup, i ->

            when(i){

                R.id.radioPria->{
                    gender = "Pria"
                }

                R.id.radioWanita->{
                    gender = "Wanita"
                }

            }

        }

        rgJenjang.setOnCheckedChangeListener(){ radioGroup, i ->
            when(i){
                R.id.radioSD->{
                    jenjang = "SD"
                }

                R.id.radioSMP->{
                    jenjang = "SMP"
                }
                R.id.radioSMA->{
                    jenjang = "SMA"
                }
                R.id.radioKULIAH->{
                    jenjang = "KULIAH"
                }

            }
        }

        btnCreate.setOnClickListener {
            create()
        }

    }

    private fun onEditMode(){

        edIdStudent.setText(i.getStringExtra("id_student"))
        edNama.setText(i.getStringExtra("nama"))
        edAlamat.setText(i.getStringExtra("alamat"))
        edEmail.setText(i.getStringExtra("email"))

        btnCreate.visibility = View.GONE
        btnUpdate.visibility = View.VISIBLE
        btnDelete.visibility = View.VISIBLE

        edIdStudent.isEnabled = false

        gender = i.getStringExtra("gender").toString()

        if(gender.equals("Pria")){
            rgGender.check(R.id.radioPria)
        }else{
            rgGender.check(R.id.radioWanita)
        }

        jenjang = i.getStringExtra("jenjang").toString()
        if (jenjang.equals("SD")){
            rgJenjang.check(R.id.radioSD)
        } else if (jenjang.equals("SMP")) {
            rgJenjang.check(R.id.radioSMP)
        } else if (jenjang.equals("SMPA")) {
            rgJenjang.check(R.id.radioSMA)
        } else {
            rgJenjang.check(R.id.radioKULIAH)
        }
    }

    private fun delete(){

        val loading = ProgressDialog(this)
        loading.setMessage("Menghapus data...")
        loading.show()

        AndroidNetworking.get(ApiEndPoint.DELETE+"?id_student="+edIdStudent.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"), Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@ManageStudentActivity.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR","error")
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()}
            })

    }

    private fun update(){

        val loading = ProgressDialog(this)
        loading.setMessage("Mengubah data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.UPDATE)
            .addBodyParameter("id_student",edIdStudent.text.toString())
            .addBodyParameter("nama",edNama.text.toString())
            .addBodyParameter("alamat",edAlamat.text.toString())
            .addBodyParameter("email",edEmail.text.toString())
            .addBodyParameter("jenjang",jenjang)
            .addBodyParameter("gender",gender)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"), Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@ManageStudentActivity.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR","error")
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()               }


            })

    }

    private fun create(){

        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.CREATE)
            .addBodyParameter("id_student",edIdStudent.text.toString())
            .addBodyParameter("nama",edNama.text.toString())
            .addBodyParameter("alamat",edAlamat.text.toString())
            .addBodyParameter("email",edEmail.text.toString())
            .addBodyParameter("jenjang",jenjang)
            .addBodyParameter("gender",gender)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"), Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@ManageStudentActivity.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR","error")
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()}


            })

    }
    }
