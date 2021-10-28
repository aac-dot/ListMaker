package com.aa.cap7.listmaker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aa.cap7.listmaker.databinding.MainFragmentBinding
import com.aa.cap7.listmaker.models.TaskList

/*
* ListMaker - parte 1
* Passos:
*   1 - Realizar a conexão da activity com o seu layout para poder acessar através do recurso viewBinding.
*   2 - Indicar o layout e o adapter da recycler view do arquivo de layout
*   3 - Criar a classe Adapter, a classe e o arquivo de layout de view holder.
*   4 - Na classe do adapter, ela deve estender a classe RecyclerView.Adapter, passando como argumento o viewholder criado.
*   5 - Na classe adapter é necessário implementar os métodos da superclasse, tendo esses métodos como ligação com o viewholder fornecido.
*   6 - O método onCreateViewHolder na classe adapter é necessário fazer uma conexão (binding) com o arquivo de layout do viewholder criado anteriormente.
*   7 - No método onBindViewHolder, usamos o paramêtro hold para acessar as views do arquivo de layout do viewholder, definindo uma lista no seguinte formato:
*           1 - Minha tarefa.
*       Em que 1 é um número incrementado automaticamente e Minha tarefa é o nome da tarefa.
*
* */

class MainFragment(val clickListener: MainFragmentInteractionListener) :
    Fragment(), ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {

    interface MainFragmentInteractionListener {
        fun listItemTapped(list : TaskList)
    }

    private lateinit var binding : MainFragmentBinding

    companion object {
        fun newInstance(clickListener: MainFragmentInteractionListener) = MainFragment(clickListener)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        // Define o layout da recycler view
        binding.listsRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
        // return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity())))
            .get(MainViewModel::class.java)

        val recyclerViewAdapter = ListSelectionRecyclerViewAdapter(viewModel.lists, this)
        binding.listsRecyclerview.adapter = recyclerViewAdapter

        viewModel.onListAdded = {
            recyclerViewAdapter.listsUpdated()
        }
    }

    override fun listItemClicked(list: TaskList) {
        clickListener.listItemTapped(list)
    }
}