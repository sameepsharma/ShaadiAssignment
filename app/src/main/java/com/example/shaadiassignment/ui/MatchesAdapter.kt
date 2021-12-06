package com.example.shaadiassignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shaadiassignment.R
import com.example.shaadiassignment.db.entities.Match
import com.example.shaadiassignment.db.entities.MatchesEntity

class MatchesAdapter(
    val clickListener: MatchesClickListener,
    val matchList: MutableList<MatchesEntity>
) :
    RecyclerView.Adapter<MatchesAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    interface MatchesClickListener {
        fun onAcceptClick(entity: MatchesEntity)
        fun onDeclineClick()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.matches_item, parent, false)
        )


    override fun getItemCount(): Int {
        return matchList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val match = matchList[position]

        Glide.with(holder.itemView).load(match.largeThumbnail)
            .into(holder.itemView.findViewById(R.id.iv_match))

        holder.itemView.findViewById<Button>(R.id.btn_accept).setOnClickListener {

            holder.itemView.findViewById<LinearLayout>(R.id.ll_buttons).visibility = View.GONE
            holder.itemView.findViewById<Button>(R.id.btn_indi).visibility = View.VISIBLE
            holder.itemView.findViewById<Button>(R.id.btn_indi).text = "ACCEPTED"

            clickListener.onAcceptClick(match)

        }

        holder.itemView.findViewById<Button>(R.id.btn_decline).setOnClickListener {

            holder.itemView.findViewById<LinearLayout>(R.id.ll_buttons).visibility = View.GONE
            holder.itemView.findViewById<Button>(R.id.btn_indi).visibility = View.VISIBLE
            holder.itemView.findViewById<Button>(R.id.btn_indi).text = "DECLINED"

            clickListener.onAcceptClick(match)

        }

        holder.itemView.findViewById<Button>(R.id.btn_decline).setOnClickListener {

            clickListener.onDeclineClick()

        }

    }
}