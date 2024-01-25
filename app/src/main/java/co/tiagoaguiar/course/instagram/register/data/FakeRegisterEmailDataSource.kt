package co.tiagoaguiar.course.instagram.register.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.model.Database

class FakeRegisterEmailDataSource : RegisterEmailDataSource{
    override fun create(email: String, callback: RegisterEmailCallback) {
          Handler(Looper.getMainLooper()).postDelayed({ // oque acontece depois de 2 segundos

              val userAuth= Database.usersAuth.firstOrNull{ email==it.email}

              if(userAuth== null){
                  callback.onSucess()
              }else{
                  callback.onFailure("Email ja cadastrado")
              }

              callback.onComplete()

         }, 2000)
    }


}