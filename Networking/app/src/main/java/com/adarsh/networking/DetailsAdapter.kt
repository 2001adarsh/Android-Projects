package com.adarsh.networking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_individual.view.*

class DetailsAdapter(private val users:ArrayList<Details>) : RecyclerView.Adapter<DetailsAdapter
.DetailsVH>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsVH
        = DetailsVH(LayoutInflater.from(parent?.context).inflate(R.layout.item_individual,
            parent, false))


    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DetailsVH, position: Int) {
        holder.bind(users[position])
    }

    class DetailsVH(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        fun bind(userAlone: Details){
            itemView?.login.text = userAlone.login
            itemView.userId.text = userAlone.id.toString()
            itemView.html_link.text = userAlone.html_url
            Picasso.get().load(userAlone.avatar_url).into(itemView.img);
        }
    }

}