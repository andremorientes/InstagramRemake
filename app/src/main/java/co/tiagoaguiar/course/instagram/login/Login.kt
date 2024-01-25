package co.tiagoaguiar.course.instagram.login

import co.tiagoaguiar.course.instagram.common.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.base.BaseView

interface Login {

    // camada de Presenter
    interface Presenter: BasePresenter{
        fun login(email:String,  password: String)
    }
    //camada de View
    interface View:BaseView<Presenter>{
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun onUserAuthenticade()
        fun onUserUnauthorized(message: String)

    }

}