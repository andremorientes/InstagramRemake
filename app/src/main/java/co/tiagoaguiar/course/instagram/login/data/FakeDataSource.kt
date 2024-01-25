package co.tiagoaguiar.course.instagram.login.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.model.Database

class FakeDataSource : LoginDataSource{
    override fun login(email: String, password: String, callback: LoginCallback) {
          Handler(Looper.getMainLooper()).postDelayed({ // oque acontece depois de 2 segundos

              val userAuth= Database.usersAuth.firstOrNull{ email==it.email}
              when {
                  userAuth==null -> {
                      callback.onFailure("Usuario não encontrado")
                  }
                  userAuth.password!=password -> {
                      callback.onFailure("Senha está incorreta")
                  }
                  else -> {
                      Database.sessionAuth=userAuth
                      callback.onSucess(userAuth)
                  }
              }

              callback.onComplete()

         }, 2000)
    }
}