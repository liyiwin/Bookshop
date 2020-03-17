package com.example.book_shopedit.Data

data class my_data_book_list  (

    var title:String,

    var author:String,

    var translator:String,

    var press:String,

    var intro:String,

    var image:String,

    var comment_score:Int,

    var book_id:Int,

    var category_id: Int,

    var is_in_like:Boolean = false

)