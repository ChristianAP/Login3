package com.example.appprueba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.view.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var txtname:EditText
    private lateinit var txtlastname:EditText
    private lateinit var txtemail:EditText
    private lateinit var txtpassword:EditText
    private lateinit var progressbar:ProgressBar
    private lateinit var dbReference:DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        txtname = findViewById(R.id.txtname)
        txtlastname = findViewById(R.id.txtlastname)
        txtemail = findViewById(R.id.txtemail)
        txtpassword = findViewById(R.id.txtpassword)
        progressbar =  findViewById(R.id.progressBar)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbReference = database.reference.child("User")
    }
    private fun createNewAccount(){
        val name: String = txtname.text.toString()
        val lastname: String = txtlastname.text.toString()
        val email: String = txtname.text.toString()
        val password: String = txtname.text.toString()

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(lastname) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            progressbar.visibility = View.VISIBLE

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){
                    task ->
                    if (task.isComplete){
                        val user:FirebaseUser?=auth.currentUser
                        verifyEmail(user)

                        val userBD=dbReference.child(user?.uid!!)

                        userBD.child("Name").setValue(name)
                        userBD.child("LastName").setValue(lastname)
                        action()
                    }
                }
        }
    }

    private fun action(){
        startActivity(Intent(this,LoginActivity::class.java))
    }
    private fun verifyEmail(user:FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){
                task ->
                if (task.isComplete){
                    Toast.makeText(this, "Email Enviado", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Error al enviar", Toast.LENGTH_LONG).show()
                }
            }
    }

     fun register(view: View){
        createNewAccount()
    }
}