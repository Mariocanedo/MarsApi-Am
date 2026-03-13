package com.example.marsapi_am.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.marsapi_am.R
import com.example.marsapi_am.databinding.FragmentSecondBinding

/**
 * A simple [androidx.fragment.app.Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var _binding: FragmentSecondBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recibir datos
        val id = arguments?.getString("id")
        val imgSrc = arguments?.getString("imgSrc")

        // mostrar datos

        binding.textMarsId.text = id
        Glide.with(requireContext())
            .load(imgSrc)
            .centerCrop()
            .into(binding.imageMars)


        // boton volver

        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
      //  _binding = null
    }
}