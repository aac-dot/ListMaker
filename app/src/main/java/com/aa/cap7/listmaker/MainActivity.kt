package com.aa.cap7.listmaker

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.aa.cap7.listmaker.databinding.MainActivityBinding
import com.aa.cap7.listmaker.models.TaskList
import com.aa.cap7.listmaker.ui.detail.ListDetailActivity
import com.aa.cap7.listmaker.ui.main.MainFragment
import com.aa.cap7.listmaker.ui.main.MainViewModel
import com.aa.cap7.listmaker.ui.main.MainViewModelFactory

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentInteractionListener {

    private lateinit var binding : MainActivityBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(this)))
            .get(MainViewModel::class.java)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val mainFragment = MainFragment.newInstance(this)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, mainFragment)
                .commitNow()
        }

        binding.fabButton.setOnClickListener {
            showDialogList()
        }
    }

    private fun showDialogList() {
        // Obtém as string do arquivo string.xml.
        val dialogTitle = getString(R.string.name_of_list)
        val buttonTitle = getString(R.string.create_list)

        // Constroi o mecanismo de alerta de dialógo.
        val builder = AlertDialog.Builder(this)

        // Cria uma caixa de texto apenas com texto.
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        // Insere o texto e a caixa de texo no alerta.
        builder.setTitle(dialogTitle) // aqui adiciona o titulo
        builder.setView(listTitleEditText) // aqui adiciona a caixa de texto

        builder.setPositiveButton(buttonTitle) {
            dialog, _ -> dialog.dismiss()

            val taskList = TaskList(listTitleEditText.text.toString())
            viewModel.saveList(TaskList(listTitleEditText.text.toString()))
            showListDetail(taskList)
        }

        // Mostra todos os recursos criados acima nessa função.
        builder.create().show()
    }

    private fun showListDetail(list : TaskList) {
        val listDetailIntent = Intent(this, ListDetailActivity::class.java)

        listDetailIntent.putExtra(INTENT_KEY_LIST, list)

        startActivity(listDetailIntent)
    }

    companion object {
        const val INTENT_KEY_LIST = "list"
    }

    override fun listItemTapped(list: TaskList) {
        showListDetail(list)
    }
}