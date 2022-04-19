package com.freenow.task1.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.freenow.task1.databinding.ItemVehicleLayoutBinding
import com.freenow.task1.presentation.model.PoiUiModel
import com.freenow.task1.presentation.model.VehicleUiModel
import com.freenow.task1.presentation.viewholder.VehicleViewHolder

class VehicleAdapter(private val itemList: ArrayList<PoiUiModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onClickAction: ((item: PoiUiModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VehicleViewHolder(ItemVehicleLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as VehicleViewHolder).bind(itemList[position])
        holder.itemView.setOnClickListener { onClickAction?.invoke(itemList[position]) }
    }

    fun addVehicles(vehicles: VehicleUiModel) {
        val poiList = vehicles.poiList
        val result = createDiffResult(this.itemList, poiList)
        this.itemList.clear()
        this.itemList.addAll(poiList)
        result.dispatchUpdatesTo(this)
    }

    private fun createDiffResult(oldList: List<PoiUiModel>, newList: List<PoiUiModel>): DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].id == newList[newItemPosition].id && oldList[oldItemPosition].id == newList[newItemPosition].id
            }

            override fun getOldListSize(): Int {
                return oldList.size
            }

            override fun getNewListSize(): Int {
                return newList.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }
        })
    }

}
