package com.example.recyclerview_am_kt

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview_am_kt.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //1 -Creamos un list que represente los datos

    private val datalist : MutableList<String?> = ArrayList<String?>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // para probar las palabras
        Log.d("Listado",setData().toString())

        //1 Instanciar el adapter y le pasamos los datos con las palabras

        val adapter = WordAdapter(setData())

        //2 le pasamos el adapter al recyclerView
        binding.Rv.setAdapter(adapter)

        // paso 3 lñe indicamos al recyclerview como mostrar los datos
        binding.Rv.setLayoutManager(LinearLayoutManager(getContext()))
        binding.Rv.setHasFixedSize(true)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }


    // PASO 2 CREAR UN LIST DE PALABRAS

    private fun setData(): MutableList<String?>{

        for (i in 0 .. 98){
            datalist.add("Palabra " +i)
        }
        return  datalist
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}