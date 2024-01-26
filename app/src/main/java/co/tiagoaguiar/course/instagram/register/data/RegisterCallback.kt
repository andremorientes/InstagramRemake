package co.tiagoaguiar.course.instagram.register.data

import co.tiagoaguiar.course.instagram.common.model.UserAuth

interface RegisterCallback {

    fun onSucess()
    fun onFailure(message: String)
    fun onComplete()
}