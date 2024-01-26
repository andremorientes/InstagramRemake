package co.tiagoaguiar.course.instagram.register.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.model.Database
import co.tiagoaguiar.course.instagram.common.model.UserAuth
import java.util.*

class FakeRegisterDataSource : RegisterDataSource{
    override fun create(email: String, callback: RegisterCallback) {
          Handler(Looper.getMainLooper()).postDelayed({ // oque acontece depois de 2 segundos

              val userAuth= Database.usersAuth.firstOrNull{ email==it.email}

              if(userAuth== null){
                  callback.onSucess()
              }else{
                  callback.onFailure("Email já cadastrado")
              }

              callback.onComplete()

         }, 2000)
    }

    override fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({ // oque acontece depois de 2 segundos

            val userAuth= Database.usersAuth.firstOrNull{ email==it.email}

            if(userAuth!= null){
                callback.onFailure("Usuario já Cadastrado")
            }else{
                val  created= Database.usersAuth.add(
                    UserAuth(UUID.randomUUID().toString(), name, email, password)
                )
                if (created){
                    callback.onSucess()
                }else{
                    callback.onFailure("Erro interno no servidor")
                }
            }

            callback.onComplete()

        }, 2000)
    }


}