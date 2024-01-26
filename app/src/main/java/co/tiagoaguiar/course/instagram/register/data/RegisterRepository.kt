package co.tiagoaguiar.course.instagram.register.data

class RegisterRepository(private val datasource: RegisterDataSource) {

    fun create(email: String, callback: RegisterCallback){
        //Responsavel por decidir o que fazr com os dados, pode chamar o servidor ou BD Local

        datasource.create(email, callback)
    }

    fun create(email:String,name: String,password: String ,callback: RegisterCallback){
        //Responsavel por decidir o que fazr com os dados, pode chamar o servidor ou BD Local

        datasource.create(email,name,password, callback)
    }
}