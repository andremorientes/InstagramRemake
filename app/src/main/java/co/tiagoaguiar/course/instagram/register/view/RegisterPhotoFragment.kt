package co.tiagoaguiar.course.instagram.register.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.view.CustomDialog

class RegisterPhotoFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_register_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val customDialog= CustomDialog(requireContext())

        customDialog.addButton( R.string.photo, R.string.gallery){
            when(it.id){
                R.string.photo ->{
                    //aqui abre a camera

                    Log.i("Teste", "Foto")
                }
                R.string.gallery->{
                    //Aqui abre a galeria
                    Log.i("Teste", "Galeria")

                }

            }

        }
        customDialog.show()
    }
}