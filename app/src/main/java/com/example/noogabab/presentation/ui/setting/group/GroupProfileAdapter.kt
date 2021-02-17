package com.example.noogabab.presentation.ui.setting.group

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noogabab.R
import com.example.noogabab.presentation.ui.setting.GroupProfileFragment
import kotlinx.android.synthetic.main.item_group_profile.view.*

class GroupProfileAdapter(val arrayList: ArrayList<GroupProfileViewModel>) :
    RecyclerView.Adapter<GroupProfileAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(model: GroupProfileViewModel){
            itemView.txt_user_name_cardview.text = model.userName
            itemView.txt_group_name_cardview.text = model.groupName
            itemView.txt_bob_num_cardview.text = model.bobNum
            itemView.txt_snack_num_cardview.text = model.snackNum
            itemView.iv_user_profile_item.setImageResource(model.profile)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_group_profile,parent,false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])
     }

    override fun getItemCount(): Int {
        return arrayList.size
    }


}