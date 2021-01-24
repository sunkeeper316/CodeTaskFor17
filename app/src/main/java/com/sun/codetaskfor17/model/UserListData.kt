package com.sun.codetaskfor17.model

import com.google.gson.annotations.SerializedName

class UserListData  {

    @SerializedName("total_count")
    var total_count: Int? = 0
    @SerializedName("incomplete_results")
    var incomplete_results: Boolean? = false
    @SerializedName("items")
    var items: MutableList<UserData>? = null
}