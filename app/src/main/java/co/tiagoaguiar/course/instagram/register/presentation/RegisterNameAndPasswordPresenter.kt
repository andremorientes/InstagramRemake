package co.tiagoaguiar.course.instagram.register.presentation

import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.register.RegisterNameAndPassword
import co.tiagoaguiar.course.instagram.register.data.RegisterCallback
import co.tiagoaguiar.course.instagram.register.data.RegisterRepository

class RegisterNameAndPasswordPresenter(private var view: RegisterNameAndPassword.View?,
                                       private val repository: RegisterRepository
): RegisterNameAndPassword.Presenter {


    override fun created(email: String, name: String, password: String, confirm: String) {
        val isNameValid = name.length>3
        val isPasswordValid= password.length>=8
        val isConfirmValid= password== confirm

        if (!isNameValid){
            view?.displayNameFailure(R.string.invalid_name)
        }else{
            view?.displayNameFailure(null)
        }

        if (!isConfirmValid){
            view?.displayPasswordFailure(R.string.password_not_equal)
        }else{
            if (!isPasswordValid){
                view?.displayPasswordFailure(R.string.invalid_password)
            }else{
                view?.displayPasswordFailure(null)
            }

        }


        if (isNameValid && isPasswordValid && isConfirmValid){
            view?.showProgress(true)

            repository.create(email, name, password,object : RegisterCallback{
                override fun onSucess() {
                    view?.onCreateSucess(name)

                }

                override fun onFailure(message:String) {
                    view?.onCreateFailure(message)
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