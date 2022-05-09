package ru.serget.mybiglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.serget.mybiglist.databinding.ItemAwardingBinding
import ru.serget.mybiglist.presenter.list.IPresenterList
import ru.serget.mybiglist.view.list.IAwardingView

class AwardingRVAdapter(private val presenter: IPresenterList<IAwardingView>):
    RecyclerView.Adapter<AwardingRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemAwardingBinding, override var pos: Int):
        RecyclerView.ViewHolder(binding.root), IAwardingView{

        override fun setId(id: String) {
            binding.idAwarding.text = id
        }

        override fun setCoinPrice(coinPrice: Int) {
            binding.coinPriceAwarding.text = coinPrice.toString()
        }

        override fun loadIcon(url: String) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAwardingBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            pos = -1
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int = presenter.getCount()
}