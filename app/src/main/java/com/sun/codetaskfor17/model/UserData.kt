package com.sun.codetaskfor17.model

import com.google.gson.annotations.SerializedName

class UserData {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("login")
    var login: String? = null
    @SerializedName("avatar_url")
    var avatarUrl: String? = null
}