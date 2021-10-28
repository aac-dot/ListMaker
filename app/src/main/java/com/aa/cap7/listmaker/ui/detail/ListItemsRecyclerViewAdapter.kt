package com.aa.cap7.listmaker.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aa.cap7.listmaker.databinding.ListItemViewHolderBinding
import com.aa.cap7.listmaker.models.TaskList

class ListItemsRecyclerViewAdapter(var taskList : TaskList) : RecyclerView.Adapter<ListItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val binding = ListItemViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.binding.textViewTask.text = taskList.tasks[position]
    }

    override fun getItemCount(): Int {
        return taskList.tasks.size
    }
}