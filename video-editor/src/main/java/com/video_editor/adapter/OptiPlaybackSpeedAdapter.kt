
package com.video_editor.adapter

import android.content.Context
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.video_editor.R
import com.video_editor.interfaces.OptiPlaybackSpeedListener
import com.video_editor.utils.OptiConstant

class OptiPlaybackSpeedAdapter(private val playbackList: ArrayList<String>, val context: Context, optiPlaybackSpeedListener: OptiPlaybackSpeedListener) :
    androidx.recyclerview.widget.RecyclerView.Adapter<OptiPlaybackSpeedAdapter.MyPostViewHolder>() {

    private var tagName: String = OptiPlaybackSpeedAdapter::class.java.simpleName
    private var myPlaybackList = playbackList
    private var myPlaybackSpeedListener = optiPlaybackSpeedListener
    private var selectedPosition: Int = -1
    private var selectedPlayback: Float = 0F
    private var selectedTempo: Float = 0F

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyPostViewHolder {
        return MyPostViewHolder(LayoutInflater.from(context).inflate(R.layout.opti_playback_view, p0, false))
    }

    override fun getItemCount(): Int {
        return myPlaybackList.size
    }

    override fun onBindViewHolder(holder: MyPostViewHolder, position: Int) {

        holder.tvSpeed.text = playbackList[position]

        if (selectedPosition == position) {
            holder.tvSpeed.setBackgroundColor(Color.WHITE)
            holder.tvSpeed.setTextColor(Color.BLACK)
        } else {
            holder.tvSpeed.setBackgroundColor(Color.BLACK)
            holder.tvSpeed.setTextColor(Color.WHITE)
        }

        holder.tvSpeed.setOnClickListener {

            selectedPosition = position

            //based on selected play back speed - playback & tempo is selected for processing
            when (playbackList[position]) {
                OptiConstant.SPEED_0_25 -> {
                    selectedPlayback = 1.75F
                    selectedTempo = 0.50F
                }

                OptiConstant.SPEED_0_5 -> {
                    selectedPlayback = 1.50F
                    selectedTempo = 0.50F
                }

                OptiConstant.SPEED_0_75 -> {
                    selectedPlayback = 1.25F
                    selectedTempo = 0.75F
                }

                OptiConstant.SPEED_1_0 -> {
                    selectedPlayback = 1.0F
                    selectedTempo = 1.0F
                }

                OptiConstant.SPEED_1_25 -> {
                    selectedPlayback = 0.75F
                    selectedTempo = 1.25F
                }

                OptiConstant.SPEED_1_5 -> {
                    selectedPlayback = 0.50F
                    selectedTempo = 2.0F
                }
            }
            notifyDataSetChanged()
        }
    }

    fun setPlayback() {
        Log.v(tagName, "selectedPlayback: $selectedPlayback, selectedTempo: $selectedTempo")
        myPlaybackSpeedListener.processVideo(selectedPlayback.toString(), selectedTempo.toString())
    }

    class MyPostViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var tvSpeed: TextView = itemView.findViewById(R.id.tv_speed)
    }
}