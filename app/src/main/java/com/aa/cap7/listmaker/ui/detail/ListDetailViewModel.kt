package com.aa.cap7.listmaker.ui.detail

import androidx.lifecycle.ViewModel
import com.aa.cap7.listmaker.models.TaskList

class ListDetailViewModel : ViewModel() {
    lateinit var onTaskAdded:(() -> Unit)

    lateinit var list : TaskList

    fun addTask(task : String) {
        list.tasks.add(task)
        onTaskAdded.invoke()
    }
}