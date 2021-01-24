package com.sun.codetaskfor17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.sun.codetaskfor17.factory.UserViewModelFactory
import com.sun.codetaskfor17.model.repository.UserListRepository
import com.sun.codetaskfor17.view.UserListItemAdapter
import com.sun.codetaskfor17.viewmodel.UserViewModel


class MainActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    private lateinit var userViewModelFactory: UserViewModelFactory
    private lateinit var userListRepository: UserListRepository

    private lateinit var userListItemAdapter : UserListItemAdapter

    lateinit var et_searchview : EditText
    lateinit var progressBar : ProgressBar
    lateinit var rv_user : RecyclerView

    var currentPage = 1
    var currentText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userListRepository = UserListRepository()
        userViewModelFactory = UserViewModelFactory(userListRepository)
        userViewModel = ViewModelProviders.of(this, userViewModelFactory).get(UserViewModel::class.java)
        progressBar = findViewById(R.id.progressBar)

        userListItemAdapter = UserListItemAdapter(this)
        et_searchview = findViewById(R.id.et_searchview)
        et_searchview.setOnEditorActionListener(object  : TextView.OnEditorActionListener {
            override fun onEditorAction(textView: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    val text = textView?.text.toString()
                    if (!text.trim().equals("")){
                        currentText = text
                        getUser(text )
                    }else{
                        userListItemAdapter.removeAll()
                    }
                }
                return false
            }
        })

        rv_user = findViewById(R.id.rv_user)
        rv_user.layoutManager = LinearLayoutManager(this)
        rv_user.adapter = userListItemAdapter
        rv_user.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if( !recyclerView.canScrollVertically(1)){
                    if (currentText.trim().equals("")){
                        return
                    }
                    addUser(currentText , currentPage + 1)
                }
            }
        })
    }

    fun getUser(name:String){

        progressBar.visibility = View.VISIBLE
        userViewModel.userListModel(name , 1 , {
            progressBar.visibility = View.GONE
            it?.let{ userListData ->
                userListData.items?.let{ userList ->
                    if (!userList.isEmpty()){
                        currentPage = 1
                        userListItemAdapter.setUserList(userList)

                    }
                }
            } ?: run {
                Toast.makeText(baseContext,"刷新速度太快",Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun addUser(name:String , page : Int){
        progressBar.visibility = View.VISIBLE
        userViewModel.userListModel(name , page , {
            progressBar.visibility = View.GONE
            it?.let{ userListData ->
                userListData.items?.let{ userList ->
                    if (!userList.isEmpty()){
                        currentPage ++
                        userListItemAdapter.addUserList(userList)
                    }
                }
            }
        })
    }
}