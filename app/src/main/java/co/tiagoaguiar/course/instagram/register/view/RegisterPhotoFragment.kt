package co.tiagoaguiar.course.instagram.register.view

import android.app.AlertDialog
import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Html.ImageGetter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.view.CropperImageFragment
import co.tiagoaguiar.course.instagram.common.view.CustomDialog
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterPhotoBinding

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo){

    private var binding: FragmentRegisterPhotoBinding?= null

    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("cropKey"){ requestKey, bundle ->
            val uri = bundle.getParcelable<Uri>(CropperImageFragment.KEY_URI)
            oncCropImageResult(uri)

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding= FragmentRegisterPhotoBinding.bind(view)

        binding?.let {
            with(it){
                registerBtnJump.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }
                registerBtnNext.isEnabled= true
                registerBtnNext.setOnClickListener {
                    opeDialog()
                }
            }
            }

    }

    override fun onDestroy() {
        binding= null
        super.onDestroy()
    }

    private fun openDialog(){
        val customDialog= CustomDialog(requireContext())
        customDialog.addButton( R.string.photo, R.string.gallery,R.string.go){
            when(it.id){
                R.string.photo ->{
                    //aqui abre a camera

                    Log.i("Teste", "Foto")
                }
                R.string.gallery->{
                    //Aqui abre a galeria
                    fragmentAttachListener?.goToGalleryScreen()

                }
                R.string.go->{
                    //Aqui abre a galeria
                    fragmentAttachListener?.goToGalleryScreen()


                }

            }

        }
        customDialog.show()
    }

    private fun opeDialog(){
       val dialog = LayoutInflater.from(context).inflate(R.layout.dialog, null)
        val showdialog= AlertDialog.Builder(context)
            .setView(dialog)
            .create()

        val openCamera: LinearLayout = dialog.findViewById(R.id.open_camera)
        val openGallery: LinearLayout = dialog.findViewById(R.id.open_gallery)

        openCamera.setOnClickListener {

        }

        openGallery.setOnClickListener {
            fragmentAttachListener?.goToGalleryScreen()
            showdialog.dismiss()
        }
        showdialog.show()
    }


    private fun oncCropImageResult(uri: Uri?){
        if (uri!= null){
           val bitmap= if (Build.VERSION.SDK_INT>=28){
                val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
                ImageDecoder.decodeBitmap(source)

            }else{
                 MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)

            }
            binding?.registerImgProfile?.setImageBitmap(bitmap)
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener)
            fragmentAttachListener= context
    }
}