package com.example.receitasmexicanas


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.comidamexicana.databinding.ItemFoodBinding
import com.example.receitasmexicanas.modelResponse.FoodResponse




class FoodAdapter(
    private var data: List<FoodResponse>,
    private val onClickListener: (FoodResponse) -> Unit
): RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FoodResponse) {
            item.let {

                binding.textTitle.text = item.title
                binding.textDistance.text = "Dificuldade: ${item.difficulty}"
                binding.textId.text = item.id.toString()

                Glide.with(itemView.context)
                    .load(it.image)
                    .into(binding.image)
                binding.card.setOnClickListener { onClickListener(item) }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodAdapter.ViewHolder {
        val binding =
            ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemViewType(position: Int): Int = position

    fun updateData(newData: List<FoodResponse>) {
        data = newData
        notifyDataSetChanged()
    }

}