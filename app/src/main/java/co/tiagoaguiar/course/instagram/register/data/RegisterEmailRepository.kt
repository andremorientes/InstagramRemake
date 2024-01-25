package co.tiagoaguiar.course.instagram.register.data

class RegisterEmailRepository(private val datasource: RegisterEmailDataSource) {

    fun create(email: String, callback: RegisterEmailCallback){
        //Responsavel por decidir o que fazr com os dados, pode chamar o servidor ou BD Local

        datasource.create(email, callback)
    }
}