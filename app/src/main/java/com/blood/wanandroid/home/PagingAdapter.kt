package com.blood.wanandroid.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.blood.wanandroid.R
import com.blood.wanandroid.bean.DataX
import com.blood.wanandroid.databinding.LayoutItemArticleBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class PagingAdapter : PagingDataAdapter<DataX, PagingAdapter.ViewHolder>(ARTICLE_COMPARATOR),
    View.OnClickListener, HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var homeViewModel: HomeViewModel

    companion object {
        private val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<DataX>() {
            override fun areItemsTheSame(oldItem: DataX, newItem: DataX): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: DataX, newItem: DataX): Boolean =
                oldItem == newItem
        }
    }

    class ViewHolder(val binding: LayoutItemArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.root.tag = holder
        holder.binding.root.setOnClickListener(this)
        //        holder.binding.position.text = position.toString()
        holder.binding.id.text = data?.id.toString()
        holder.binding.title.text = data?.title?.trim().toString()
        holder.binding.shareDate.text = data?.niceShareDate?.trim().toString()
        //        holder.binding.shareDate.text = DateFormat.getDateInstance().format(data?.shareDate)
        holder.binding.desc.text = data?.desc?.trim().toString()
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutItemArticleBinding>(
            LayoutInflater.from(parent.context), R.layout.layout_item_article, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onClick(v: View?) {
        val holder = v?.tag as ViewHolder
        val position = holder.absoluteAdapterPosition
        val article = getItem(position) ?: return
        homeViewModel.select(article)
    }

}