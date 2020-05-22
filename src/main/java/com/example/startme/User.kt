package com.example.startme

class User(nameMe: String,surnameMe: String, emailMe : String, passwordMe: String){
    private val name: String = nameMe
    private val surname: String = surnameMe
    private val email: String = emailMe.toLowerCase()
    private val password: String = passwordMe
    private var image: Int = -1
    fun getName(): String {
        return name
    }
    fun getSurname(): String {
        return surname
    }
    fun getEmail(): String {
        return email
    }
    fun setImage(a: Int){
        this.image = a
    }
    fun getImage(): Int{
        return image
    }
    fun checkPassword(supposedPassword : String): Boolean {
        return supposedPassword.equals(password)
    }


}