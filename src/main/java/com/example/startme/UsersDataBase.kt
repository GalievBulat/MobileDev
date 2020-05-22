package com.example.startme
import java.lang.IndexOutOfBoundsException


class UsersDataBase{
    //the HashMap used to manage the authorization
    private val users: HashMap<String,User> = HashMap()
    public fun addAllUsers(s: Array<String>): HashMap<String,User>?{
        for (i: Int in 0..s.size-1 step 4){
            try {
                users.put(s[i+2].toLowerCase(),User(s[i], s[i + 1], s[i + 2], s[i + 3]))
            } catch (e: IndexOutOfBoundsException){
                //in case of a wrong xml file program doesn't stop working
                users.get("kakadurf@gmail.com")?.setImage(R.drawable.kakadurf)
                users.get("hahadurf@gmail.com")?.setImage(R.drawable.n18048)
                users.get("hahadurf@mail.ru")?.setImage(R.drawable.hufflepuff)
                users.get("hahadurf@gmail.co")?.setImage(R.drawable.n18048)
                return users
            }
        }
        //avatars for some users
        users.get("kakadurf@gmail.com")?.setImage(R.drawable.kakadurf)
        users.get("hahadurf@gmail.com")?.setImage(R.drawable.n18048)
        users.get("hahadurf@mail.ru")?.setImage(R.drawable.hufflepuff)
        users.get("hahadurf@gmail.co")?.setImage(R.drawable.n18048)
        return users
    }
}