package co.tiagoaguiar.course.instagram.login

import androidx.annotation.StringRes
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
        fun displayEmailFailure(@StringRes emailError: Int?)
        fun displayPasswordFailure(@StringRes passwordError: Int?)
        fun onUserAuthenticade()
        fun onUserUnauthorized(message: String)

    }

}