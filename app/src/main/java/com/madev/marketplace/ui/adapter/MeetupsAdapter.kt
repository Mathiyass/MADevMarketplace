package com.madev.marketplace.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madev.marketplace.domain.model.Meetup
import com.madev.marketplace.databinding.ItemMeetupBinding
import java.text.SimpleDateFormat
import java.util.*

class MeetupsAdapter : ListAdapter<Meetup, MeetupsAdapter.VH>(MeetupDiff) {

    inner class VH(private val b: ItemMeetupBinding) : RecyclerView.ViewHolder(b.root) {
        private val displayFmt = SimpleDateFormat("MMM d, yyyy HH:mm", Locale.getDefault())
        private val isoFmt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

        fun bind(m: Meetup) {
            b.tvMeetupTitle.text = m.title
            b.tvMeetupLocation.text = m.location ?: "COORDS:: ${m.lat}, ${m.lng}"
            b.tvMeetupDesc.text = m.description ?: ""
            b.tvMeetupDate.text = try {
                val date = isoFmt.parse(m.startsAt!!.substringBefore("+").substringBefore("Z"))
                displayFmt.format(date!!)
            } catch (e: Exception) { m.startsAt ?: "TIME_TBD::" }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemMeetupBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    companion object {
        val MeetupDiff = object : DiffUtil.ItemCallback<Meetup>() {
            override fun areItemsTheSame(a: Meetup, b: Meetup) = a.meetupId == b.meetupId
            override fun areContentsTheSame(a: Meetup, b: Meetup) = a == b
        }
    }
}
