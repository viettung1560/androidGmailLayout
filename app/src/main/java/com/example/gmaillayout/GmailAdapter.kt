package com.example.gmaillayout

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class GmailAdapter (val gmails: List<GmailModel>):BaseAdapter(){
    override fun getCount(): Int = gmails.size

    override fun getItem(position: Int): Any = gmails[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.layout_gmail, parent, false)
            viewHolder = ViewHolder(itemView)
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = itemView.tag as ViewHolder
        }

        val gmail = gmails[position]

        viewHolder.imageAvatar.setImageResource(R.drawable.dynamic)
        val layerDrawable = viewHolder.imageAvatar.drawable as LayerDrawable
        val backgroundDrawable = layerDrawable.findDrawableByLayerId(R.id.background_circle) as GradientDrawable
        backgroundDrawable.setColor(gmail.color)
        layerDrawable.setDrawableByLayerId(R.id.foreground_icon, gmail.drawable)

        viewHolder.textName.text = gmail.name
        viewHolder.textMsg.text = gmail.msg
        viewHolder.textTime.text = gmail.time
        viewHolder.star.isChecked = gmail.star

        viewHolder.star.setOnClickListener {
            gmail.star = viewHolder.star.isChecked
            notifyDataSetChanged()
        }

        return itemView
    }

    class ViewHolder(itemView: View) {
        val imageAvatar: ImageView
        val textName: TextView
        val textMsg: TextView
        val textTime: TextView
        val star: CheckBox
        init {
            imageAvatar = itemView.findViewById(R.id.image_view)
            textName = itemView.findViewById(R.id.text_name)
            textMsg = itemView.findViewById(R.id.text_msg)
            textTime = itemView.findViewById(R.id.text_time)
            star = itemView.findViewById(R.id.star)
        }
    }
}