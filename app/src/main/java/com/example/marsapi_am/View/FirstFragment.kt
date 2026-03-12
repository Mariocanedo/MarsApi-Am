package com.example.marsapi_am.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.InvalidationTracker
import com.example.marsapi_am.R
import com.example.marsapi_am.ViewModel.MarsViewModel
import com.example.marsapi_am.databinding.FragmentFirstBinding

/**
 * A simple [androidx.fragment.app.Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    private lateinit var  _binding : FragmentFirstBinding
 // instancia del viewModel
     private val viewModel : MarsViewModel by activityViewModels ()
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // instanciamos el Adapter
        val adapter = AdapterMars()

        _binding.rvTerrains.adapter = adapter
        _binding.rvTerrains.layoutManager = GridLayoutManager(context,2)


        viewModel.liveDataFromInternet.observe(viewLifecycleOwner, Observer { data ->
            data?.let {


                // Mostrar en Logcat
                Log.d("FirstFragment", "Datos recibidos desde internet: $it")
            }
        })









        adapter.selectedTerrain.observe(viewLifecycleOwner, Observer {
            it.let {

                viewModel.selected(it)
                // ir desde el primer fragmento al segundo completar código
            }


        })



    }

    override fun onDestroyView() {
        super.onDestroyView()
     //   _binding = null
    }
}