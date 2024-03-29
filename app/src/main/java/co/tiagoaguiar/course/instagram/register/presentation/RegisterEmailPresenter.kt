package co.tiagoaguiar.course.instagram.register.presentation

import android.util.Patterns
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.register.RegisterEmail
import co.tiagoaguiar.course.instagram.register.data.RegisterCallback
import co.tiagoaguiar.course.instagram.register.data.RegisterRepository

class RegisterEmailPresenter(private var view: RegisterEmail.View?,
                             private val repository: RegisterRepository
): RegisterEmail.Presenter {
    override fun created(email: String) {
        val isEmailValid= Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (!isEmailValid){
            view?.displayEmailFailure(R.string.invalid_email)
        }else{
            view?.displayEmailFailure(null)
        }


        if (isEmailValid){
            view?.showProgress(true)

            repository.create(email, object : RegisterCallback{
                override fun onSucess() {
                    view?.goToNameAndPasswordScreen(email)

                }

                override fun onFailure(message:String) {
                    view?.onEmailFailure(message)
                }
                override fun onComplete() {
                    view?.showProgress(false)
                }

            })
        }
    }

    override fun onDestroy() {
        view= null
    }
}