package co.tiagoaguiar.course.instagram.register.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.base.DependencyInjector
import co.tiagoaguiar.course.instagram.common.utils.TxtWatcher
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterNamePasswordBinding
import co.tiagoaguiar.course.instagram.register.RegisterNameAndPassword
import co.tiagoaguiar.course.instagram.register.presentation.RegisterNameAndPasswordPresenter
import java.lang.IllegalArgumentException

class RegisterNamePasswordFragment: Fragment(R.layout.fragment_register_name_password),
    RegisterNameAndPassword.View{

    private var binding: FragmentRegisterNamePasswordBinding?= null

    override lateinit  var presenter: RegisterNameAndPassword.Presenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding= FragmentRegisterNamePasswordBinding.bind(view)

        val  repopsitory= DependencyInjector.resgisterEmailRepository()
        presenter= RegisterNameAndPasswordPresenter(this, repopsitory)

      val email= arguments?.getString(KEY_EMAIL) ?: throw IllegalArgumentException("Email not found")

        binding?.let {
            with(it){

                registerTxtLogin.setOnClickListener {
                    activity?.finish()
                }
                registerNameBtnNext.setOnClickListener {
                    presenter.created(
                        email,
                    registerEditName.text.toString(),
                    registerEditPassword.text.toString(),
                    registerEditConfirm.text.toString())
                }

                registerEditName.addTextChangedListener(watcher)
                registerEditName.addTextChangedListener(TxtWatcher{
                    displayNameFailure(null)
                })
                registerEditPassword.addTextChangedListener(watcher)
                registerEditPassword.addTextChangedListener(TxtWatcher{
                    displayPasswordFailure(null)
                })
                registerEditConfirm.addTextChangedListener(watcher)
                registerEditConfirm.addTextChangedListener(TxtWatcher{
                   displayPasswordFailure(null)
                })

            }
        }
    }

    override fun onDestroy() {
        binding=null
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object {
        const val KEY_EMAIL= "key_email"
    }

    private val watcher =  TxtWatcher{
        binding?.registerNameBtnNext?.isEnabled=binding?.registerEditName?.text.toString().isNotEmpty()
                &&binding?.registerEditPassword?.text.toString().isNotEmpty()
                &&binding?.registerEditConfirm?.text.toString().isNotEmpty()

    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerNameBtnNext?.showProgress(enabled)
    }

    override fun displayNameFailure(nameError: Int?) {
        binding?.registerEditNameInput?.error=nameError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding?.registerEditPasswordInput?.error= passwordError?.let { getString(it) }
    }

    override fun onCreateSucess(name: String) {
        // TODO: ABRIR TELA DE BEM VINDO
    }

    override fun onCreateFailure(message: String) {
      Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }

}