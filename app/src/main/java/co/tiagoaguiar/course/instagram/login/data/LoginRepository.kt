package co.tiagoaguiar.course.instagram.login.data

class LoginRepository(private val datasource: LoginDataSource) {

    fun login(email: String, password:String, callback: LoginCallback){
        //Responsavel por decidir o que fazr com os dados, pode chamar o servidor ou BD Local

        datasource.login(email,password, callback)
    }
}