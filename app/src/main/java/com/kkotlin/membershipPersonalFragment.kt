package com.kkotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

//import com.mongodb.ConnectionString
//import com.mongodb.client.MongoClients
//import com.mongodb.client.model.Filters
//import org.bson.Document

class membershipPersonalFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Firebase.database


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_membership_personal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        var fbAuth : FirebaseAuth? = null
//        var fbFirestore : FirebaseFirestore? = null
//
//        fbAuth = FirebaseAuth.getInstance()
//        fbFirestore = FirebaseFirestore.getInstance()

//        val check = view.findViewById<EditText>(R.id.membership_personal_check)
        val finalButton = view.findViewById<Button>(R.id.membership_personal_finalButton)
//
//
        finalButton.setOnClickListener {

            val database = Firebase.database
            val myRef = database.getReference("membership_personal")
//            val myRef = database.getReference("membership_personal").child(Firebase.auth.currentUser!!.uid)

            val birth = view.findViewById<EditText>(R.id.membership_personal_birth)?.text.toString()

            val foot = view.findViewById<EditText>(R.id.membership_personal_foot).text.toString()
            val height = view.findViewById<EditText>(R.id.membership_personal_height).text.toString()
            val name = view.findViewById<EditText>(R.id.membership_personal_name).text.toString()
            val position = view.findViewById<EditText>(R.id.membership_personal_position).text.toString()
            val team = view.findViewById<EditText>(R.id.membership_personal_team).text.toString()
            val teamButton = view.findViewById<Button>(R.id.membership_personal_teamButton)
            val weight = view.findViewById<EditText>(R.id.membership_personal_weight).text.toString()

            val data = MemberPersonalData(birth, foot, height, name, position, team, weight)
            print(data)
            Log.d(data.birth, data.birth.toString())
            Log.d(data.foot, data.foot.toString())

            myRef
                .push()
                .setValue(data)
        }
    }


}