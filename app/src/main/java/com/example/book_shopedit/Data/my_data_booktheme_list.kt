package com.example.book_shopedit.Data

data class my_data_booktheme_list (

    val title:String,

    val author:String,

    val translator:String,

    val press:String,

    val intro:String,

    val image:String,

    val comment_score:Int,

    val book_id:Int,

    val theme_id: Int,

    var is_in_like:Boolean = false




)