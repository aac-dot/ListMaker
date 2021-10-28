package com.aa.cap7.listmaker.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.aa.cap7.listmaker.models.TaskList

class MainViewModel(private val sharedPreferences : SharedPreferences) : ViewModel() {
    lateinit var onListAdded : (() -> Unit)

    val lists : MutableList<TaskList> by lazy {
        retrieveLists()
    }

    /*
    * Função responsavél por criar uma lista a partir da sharedPreferences salva.
    * */
    private fun retrieveLists() : MutableList<TaskList> {
        val sharedPreferencesContents = sharedPreferences.all
        val taskLists = ArrayList<TaskList>()

        for (taskList in sharedPreferencesContents) {
            val itemHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, itemHashSet)

            taskLists.add(list)
        }

        return taskLists
    }
    /*
    * Função responsavél por cria uma tarefa no sharedPreferences a partir
    * da lista forncida como paramêtro.
    *
    * */
    fun saveList(list : TaskList) {
        // Cria uma sharedPreferences compativel a partir da lista, convertendo essa
        // list em um hashSet.
        sharedPreferences.edit().putStringSet(list.nameTask, list.tasks.toHashSet()).apply()

        // Adiciona a tarefa na lista de tarefas e
        // chama a função onListAdded para notificar
        // todos que a usam que um nova tarefa foi adicionada
        lists.add(list)
        onListAdded.invoke()
    }


}