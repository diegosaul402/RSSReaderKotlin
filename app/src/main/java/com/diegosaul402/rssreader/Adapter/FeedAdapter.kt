package com.diegosaul402.rssreader.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.diegosaul402.rssreader.Interface.ItemClickListener
import com.diegosaul402.rssreader.Model.RSSObject
import com.diegosaul402.rssreader.R

/**
 * Created by diego on 09/08/2017.
 */

class FeedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener{

    var txtTitle: TextView
    var txtPubDate: TextView
    var txtContent: TextView

    private var itemClickListener: ItemClickListener? = null

    init {
        txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        txtPubDate = itemView.findViewById<TextView>(R.id.txtPubDate)
        txtContent = itemView.findViewById<TextView>(R.id.txtContent)

        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onClick(p0: View?) {
        itemClickListener!!.onCLick(p0, adapterPosition, false)
    }

    override fun onLongClick(p0: View?): Boolean {
       itemClickListener!!.onCLick(p0, adapterPosition, true)
        return true
    }

}

class FeedAdapter(private val rssObject: RSSObject,
                  private val mContext: Context): RecyclerView.Adapter<FeedViewHolder>() {

    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FeedViewHolder {
        val itemView = inflater.inflate(R.layout.row, parent, false)
        return FeedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.txtTitle.text = rssObject.items[position].title
        holder.txtContent.text = rssObject.items[position].content
        holder.txtPubDate.text = rssObject.items[position].pubDate

        holder.setItemClickListener(ItemClickListener { view, position, isLongClick ->
            if(!isLongClick){
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.items[position].link))
                mContext.startActivity(browserIntent)
            }
        })
    }

    override fun getItemCount(): Int {
        return rssObject.items.size
    }

}