package com.example.recyclerview_am_kt

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.ForwardingListener
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_am_kt.databinding.WordItemListBinding



/*****************3.- EXTIENDO LA CLASE WORDADATER de Recyclerview.Adapter CONTIENE LA CLASE INTERNA WORDVIEWHOLER */
// 4 -implementar los métos de adapter OnCreateViewHolder, OnBindViewHolder, getItemCount()

class WordAdapter (
    /***************** Paso 1.Representación de los datos */
    private val mwordList : MutableList<String>,
    private val listener: (String,Int)-> Unit // 👈 //enviamos palabra +posicion

): RecyclerView.Adapter<WordAdapter.WordViewHolder?>() {



    //5 ESTE METODO INICIALIZA LAS VISTAS LAS INFLA
    // EN EL CONSTRUCTOR RECIBE UN OBJETO LE PASAMOS BINDING
    // INSTANCIAMOS VIEWBINDING PARA RETONAR NUESTRO VIEWHOLDER CON ESA DEPENDENCIA

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
       val mBinding =  WordItemListBinding.inflate(LayoutInflater.from(parent.getContext()),
        parent,false)

        return WordViewHolder(mBinding)
    }





     // paso 6 ESTE  MÉTODO DIBUJA LAS VISTAS
    // VA POSICIONANDO CADA ELEMENTO DEL RV

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
       // val element = mwordList.get(position)
       // holder.textView.setText(element)
        val element = mwordList[position]
        holder.textView.text= element

        // Log cuando se asigna cada item
        Log.d("WordAdapter","onBindViewHolder : position=$position, word= $element")
        // Click listener

        holder.itemView.setOnClickListener {
            // Log antes de llamar al listener
            Log.d("WordAdapter", "Item clicked: word=$element, position=$position")
            listener(element, position)
        }
    }


    // 7 Este método lleva la cuenta de cuantos elementos hay en el listado
    override fun getItemCount(): Int {
        return mwordList.size
    }


    /*****************2. Paso Crear clase interna que se llama ViewHolder */
    // INICIALIZAMOs TEXVIEW QUE ES DENTRO DEL XML  Y LLAMAMOS MBINDING QUE ES DE LA CLASE XML QUE CONTIENE TEXVIEW
    // NOS PEDIRA IMPLEMENTAR SU CONSTRUCTOR DE LA SUPER CLASE
    // CAMBIAMOS EL ELEMENTO ANTERIOR Y REFERENCIAMOS A LA CLASE BINDING QUE REPRESENTA NUESTRO LAYOUT WORD ITEM LIST


    inner class WordViewHolder(mBinding: WordItemListBinding) :
        RecyclerView.ViewHolder(mBinding.getRoot()) {

        val textView: TextView

        init {

            textView = mBinding.textView
        }


    }
}