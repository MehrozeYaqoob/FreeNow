package com.freenow.task1.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.freenow.task1.R
import com.freenow.task1.databinding.ItemVehicleLayoutBinding
import com.freenow.task1.presentation.model.PoiUiModel

class VehicleViewHolder(private val binding: ItemVehicleLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(vehicle: PoiUiModel) {
        binding.tvIdValue.text = vehicle.id.toString()
        binding.tvFleetTypeValue.text = vehicle.fleetType
        when (vehicle.fleetType) {
            "TAXI" -> {
                binding.cvRoot.setCardBackgroundColor(itemView.context.getColorStateList(R.color.colorWhite))
            }
            else -> {
                binding.cvRoot.setCardBackgroundColor(itemView.context.getColorStateList(R.color.colorLightTransparent))
            }
        }
    }
}
