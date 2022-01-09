package com.mws.finalproject_crud_mwsp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_list.view.*

class RVAAdapter (private val context: Context, private val arrayList: ArrayList<Students>) : RecyclerView.Adapter<RVAAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.student_list,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.cvList.setOnClickListener {
            val i = Intent(context,ManageStudentActivity::class.java)
            i.putExtra("editmode","1")
            i.putExtra("id_student",arrayList?.get(position)?.id_student)
            i.putExtra("nama",arrayList?.get(position)?.nama)
            i.putExtra("alamat",arrayList?.get(position)?.alamat)
            i.putExtra("email",arrayList?.get(position)?.email)
            i.putExtra("jenjang",arrayList?.get(position)?.jenjang)
            i.putExtra("gender",arrayList?.get(position)?.gender)
            context.startActivity(i)

        }

        holder.view.tvIdStudent.text = arrayList?.get(position)?.id_student
        holder.view.tvNama.text = "Nama : "+arrayList?.get(position)?.nama
        holder.view.tvAlamat.text = "Alamat : "+arrayList?.get(position)?.alamat
        holder.view.tvEmail.text = "Email : "+arrayList?.get(position)?.email
        holder.view.tvJenjang.text = "Jenjang : "+arrayList?.get(position)?.jenjang
        holder.view.tvGender.text = "Gender : "+arrayList?.get(position)?.gender

    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

}