package co.tiagoaguiar.course.instagram.login.data

import android.os.Handler
import android.os.Looper

class FakeDataSource : LoginDataSource{
    override fun login(email: String, password: String, callback: LoginCallback) {
          Handler(Looper.getMainLooper()).postDelayed({ // oque acontece depois de 2 segundos
               if(email=="a@gmail.com" && password=="12345678"){
                   callback.onSucess()
               }else{
                   callback.onFailure("usuario não encontrado")
               }
              callback.onComplete()

         }, 2000)
    }
}