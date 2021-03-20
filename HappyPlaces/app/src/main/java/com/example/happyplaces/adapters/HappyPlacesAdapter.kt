package com.example.happyplaces.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.happyplaces.R
import com.example.happyplaces.databinding.ItemHappyPlaceBinding
import com.example.happyplaces.models.HappyPlaceModel
import de.hdodenhof.circleimageview.CircleImageView

open class HappyPlacesAdapter(
    private val context: Context,
    private var list: ArrayList<HappyPlaceModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHappyPlaceBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if(holder is MyViewHolder) holder.bind(model)

//        if (holder is MyViewHolder) {
//            holder.ivPlaceImage.setImageURI(Uri.parse(model.image))
//            holder.tvTitle.text = model.title
//            holder.tvDescription.text = model.description
//        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
//
//    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val ivPlaceImage = view.findViewById(R.id.iv_place_image) as CircleImageView
//        val tvTitle = view.findViewById(R.id.tvTitle) as TextView
//        val tvDescription = view.findViewById(R.id.tvDescription) as TextView
//    }

    class MyViewHolder(private val binding: ItemHappyPlaceBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: HappyPlaceModel){
            binding.ivPlaceImage.setImageURI(Uri.parse(model.image))
            binding.tvTitle.text = model.title
            binding.tvDescription.text = model.description
        }
    }
}
