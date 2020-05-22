package com.example.startme

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    companion object {
        //HashMap made of users' emails and their accounts
        lateinit var users: HashMap<String,User>

    }
    lateinit var prefs: SharedPreferences
    private fun checkAccuracy(email: String, password: String): User?{
        //validation process
        if (!email.equals("") && email.matches(Regex("\\w+[@]\\w+[.]\\w+"))){
                if (!email.equals("") && (password.length>=6) &&
                    password.contains(Regex("[[:upper:]]"))
                    && password.contains(Regex("[[:lower:]]"))
                    && password.contains(Regex("\\d"))){
                    if (users.containsKey(email) && users.get(email)!!.checkPassword(password)){
                        return users.get(email)
                    }else{
                        Toast.makeText(applicationContext, R.string.login_failed,
                            Toast.LENGTH_SHORT).show()
                    }
                } else{
                    Toast.makeText(applicationContext, R.string.invalid_password,
                        Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(applicationContext, R.string.invalid_username , Toast.LENGTH_SHORT).show()
        }
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val array = resources.getStringArray(R.array.usersDataArray)
        users = UsersDataBase().addAllUsers(array) ?: throw ExceptionInInitializerError()
        prefs = getSharedPreferences("Program State", Context.MODE_PRIVATE)
        val email = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val button = findViewById<Button>(R.id.login)
        button.setOnClickListener {
            val user : User? = checkAccuracy(email.getText().toString().toLowerCase(),password
                .getText().toString())
            if (user!= null){
                val intent = Intent(this, MyPage::class.java)
                intent.putExtra(R.string.user.toString(),user.getEmail())
                prefs.edit().putBoolean(R.string.isLogged.toString(),true).apply()
                prefs.edit().putString(R.string.currentUser.toString(),user.getEmail()).apply()
                startActivity(intent)
            }
        }
    }
    override fun onResume() {
        super.onResume()
        //if there are cache represented in the system we use it to authorize
        if (prefs.contains(R.string.isLogged.toString())
            && prefs.getBoolean(R.string.isLogged.toString(),false)) {
            val array = resources.getStringArray(R.array.usersDataArray)
            users = UsersDataBase().addAllUsers(array) ?: throw ExceptionInInitializerError()
            val email = prefs.getString(R.string.currentUser.toString(),"")
            val intent = Intent(this, MyPage::class.java)
            intent.putExtra(R.string.user.toString(),email)
            startActivity(intent)
        }
    }
}
