package co.tiagoaguiar.course.instagram.common.base

import co.tiagoaguiar.course.instagram.login.data.FakeDataSource
import co.tiagoaguiar.course.instagram.login.data.LoginRepository
import co.tiagoaguiar.course.instagram.register.data.FakeRegisterDataSource
import co.tiagoaguiar.course.instagram.register.data.RegisterRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository{
        return  LoginRepository(FakeDataSource())/// substituir o FakeDataSource Servidor
    }

    fun resgisterEmailRepository(): RegisterRepository{
        return  RegisterRepository(FakeRegisterDataSource())

    }
}