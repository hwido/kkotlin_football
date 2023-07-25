package com.hwido.football

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginIndividualActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = Firebase.database
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_individualactivity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        var fbAuth : FirebaseAuth? = null
//        var fbFirestore : FirebaseFirestore? = null
//
//        fbAuth = FirebaseAuth.getInstance()
//        fbFirestore = FirebaseFirestore.getInstance()

//        val check = view.findViewById<EditText>(R.id.membership_personal_check)
        val finalButton = view.findViewById<Button>(R.id.login_individualactivity_finalButton)
//
//
        finalButton.setOnClickListener {

            val database = Firebase.database
            val myRef = database.getReference("membership_personal")
//            val myRef = database.getReference("membership_personal").child(Firebase.auth.currentUser!!.uid)

            val birth = view.findViewById<EditText>(R.id.login_individualactivity_birth)?.text.toString()

            val foot = view.findViewById<EditText>(R.id.login_individualactivity_foot).text.toString()
            val height = view.findViewById<EditText>(R.id.login_individualactivity_height).text.toString()
            val name = view.findViewById<EditText>(R.id.login_individualactivity_name).text.toString()
            val position = view.findViewById<EditText>(R.id.login_individualactivity_position).text.toString()
            val team = view.findViewById<EditText>(R.id.login_individualactivity_team).text.toString()
            val teamButton = view.findViewById<Button>(R.id.login_individualactivity_teamButton)
            val weight = view.findViewById<EditText>(R.id.login_individualactivity_weight).text.toString()

            val data = LoginMemberPersonalData(birth, foot, height, name, position, team, weight)
            print(data)
            Log.d(data.birth, data.birth.toString())
            Log.d(data.foot, data.foot.toString())

            myRef
                .push()
                .setValue(data)
        }
    }
}