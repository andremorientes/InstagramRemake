package co.tiagoaguiar.course.instagram.register

import androidx.annotation.StringRes
import co.tiagoaguiar.course.instagram.common.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.base.BaseView

interface RegisterNameAndPassword {
    interface Presenter: BasePresenter {
        fun created(email:String,name: String, password: String, confirm: String)
    }

    interface View: BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayNameFailure(@StringRes nameError: Int?)
        fun displayPasswordFailure(@StringRes passwordError: Int?)
       fun onCreateSucess(name: String)
       fun onCreateFailure(message: String)
    }
}