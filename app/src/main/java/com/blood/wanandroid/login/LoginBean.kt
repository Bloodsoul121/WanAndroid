package com.blood.wanandroid.login

data class LoginBean(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val coinCount: Int,
    val collectIds: List<Any>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)

/*
{
    "admin": false,
    "chapterTops": [],
    "coinCount": 0,
    "collectIds": [],
    "email": "",
    "icon": "",
    "id": 83510,
    "nickname": "bloodsoul",
    "password": "",
    "publicName": "bloodsoul",
    "token": "",
    "type": 0,
    "username": "bloodsoul"
}
*/
