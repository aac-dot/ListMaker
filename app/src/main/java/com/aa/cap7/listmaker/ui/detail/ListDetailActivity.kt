package com.aa.cap7.listmaker.ui.detail

import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aa.cap7.listmaker.MainActivity
import com.aa.cap7.listmaker.R
import com.aa.cap7.listmaker.databinding.ListDetailActivityBinding

class ListDetailActivity : AppCompatActivity() {

    lateinit var binding : ListDetailActivityBinding
    lateinit var viewModel : ListDetailViewModel
    lateinit var fragment : ListDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.list_detail_activity)
        binding = ListDetailActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }

        viewModel = ViewModelProvider(this).get(ListDetailViewModel::class.java)
        viewModel.list = intent.getParcelableExtra(MainActivity.INTENT_KEY_LIST)!!
        title = viewModel.list.nameTask
    }

    private fun showCreateTaskDialog() {
        //1
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) { dialog, _ ->
                val task = taskEditText.text.toString()
                viewModel.addTask(task)
                dialog.dismiss()
            }
            .create()
            .show()
    }
}