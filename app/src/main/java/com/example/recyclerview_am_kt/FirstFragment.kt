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

    // Adapter del RecyclerView declarado como lateinit para poder usarlo en todo el fragment

    // Adapter declarado a nivel de clase (NO local)
    private lateinit var  adapter : WordAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 1️⃣ Llenamos la lista con datos iniciales
         setData()


        // para probar las palabras
        Log.d("Listado",setData().toString())

        // 2️⃣ IMPORTANTE: Inicializamos el adapter de la clase (sin "val")
        adapter = WordAdapter(datalist as MutableList<String?> as MutableList<String>) { selectedWord, position ->

            // Crear fragmento destino
            val secondFragment = SecondFragment()


            // Crear bundle y pasar la palabra y posición

            val bundle = Bundle()
            bundle.putString("selectedWord", selectedWord)
            bundle.putInt("position", position)
            secondFragment.arguments = bundle


            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, secondFragment)
                .addToBackStack(null)
                .commit()
        }


        // recibe los elementos con bundle y actualiza la palabra por medio de la posicion
        parentFragmentManager.setFragmentResultListener("updateWord", this){_, bundle ->
            val pos = bundle.getInt("position")
            val newWord = bundle.getString("newWord")
            if(pos in datalist.indices && newWord != null){

                datalist[pos]= newWord
                adapter.notifyItemChanged(pos)

            }
        }




        // recibe los elementos con bundle y actualiza la palabra por medio de la posicion
        parentFragmentManager.setFragmentResultListener("deleteWord", this){_, bundle ->
            val pos = bundle.getInt("position")

            if(pos in datalist.indices){
              datalist.removeAt(pos)
                adapter.notifyItemRemoved(pos)

            }
        }


        //1 Instanciar el adapter y le pasamos los datos con las palabras

           // val adapter = WordAdapter(setData())

            //2 le pasamos el adapter al recyclerView
            binding.Rv.setAdapter(adapter)

            // paso 3 lñe indicamos al recyclerview como mostrar los datos
            binding.Rv.setLayoutManager(LinearLayoutManager(getContext()))
            binding.Rv.setHasFixedSize(true)



        // Configuramos el FAB para agregar nuevas palabras
         binding!!.fab.setOnClickListener {
             addNewWord() // función que agrega una palabra nueva
         }

    }


    // PASO 2 CREAR UN LIST DE PALABRAS

    private fun setData(): MutableList<String?> {

        for (i in 0..98) {
            datalist.add("Palabra " + i)
        }
         return  datalist
    }


    // Función que agrega una nueva palabra al RecyclerView con Button

    private fun addNewWord(){

        val newIndex = datalist.size
        val newWord = "Palabra $newIndex"
        datalist.add(newWord)
        adapter.notifyItemInserted(newIndex)
        binding!!.Rv.scrollToPosition(newIndex)
    }











    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}