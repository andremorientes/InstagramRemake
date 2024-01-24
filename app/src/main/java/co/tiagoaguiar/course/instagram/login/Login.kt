package co.tiagoaguiar.course.instagram.login

interface Login {
    //camada de View
    interface View{
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun onUserAuthenticade()
        fun onUserUnauthorized()

    }
}