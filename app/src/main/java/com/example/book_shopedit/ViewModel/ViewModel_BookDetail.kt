package com.example.book_shopedit.ViewModel

import android.app.Application
import android.icu.text.CaseMap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class ViewModel_BookDetail(app: Application): AndroidViewModel(app) {

    private  var comment_score =  MutableLiveData<String>()

    private var image =  MutableLiveData<String>()

    private var author =  MutableLiveData<String>()

     private var introduce  =  MutableLiveData<String>()

    private var title =  MutableLiveData<String>()

    private var price =  MutableLiveData<String>()

    private var id =  MutableLiveData<String>()

    private var publishDate =  MutableLiveData<String>()

    private var  press =  MutableLiveData<String>()

    private var language =  MutableLiveData<String>()

    private var ISBN_string =  MutableLiveData<String>()

    private var wishlist_status =  MutableLiveData<String>()



    fun set_wishlist_status(code:String){

        wishlist_status.value  = code

    }

    fun get_wishlist_status():MutableLiveData<String>{

        return  wishlist_status


    }

    fun set_comment_score (my_comment_score:String){

        comment_score.value = my_comment_score
    }

    fun get_comment_score ():MutableLiveData<String>{

        return comment_score

    }

    fun set_image (my_image:String){

        image.value = my_image
    }

    fun get_image ():MutableLiveData<String>{

        return image

    }

    fun set_author(my_author:String){

        author.value = my_author
    }

    fun get_author():MutableLiveData<String>{

        return  author

    }


    fun set_introduce(my_introduce:String){

        introduce.value =  my_introduce

    }

    fun get_introduce():MutableLiveData<String>{

        return  introduce

    }


    fun set_title(my_title:String){

        title.value = my_title

    }

    fun get_title():MutableLiveData<String>{

        return title

    }



    fun set_price(my_price:String){

        price.value = my_price

    }

    fun get_price():MutableLiveData<String>{

        return price

    }

    fun set_id(my_id:String){

        id.value = my_id

    }

    fun get_id():MutableLiveData<String>{

        return id

    }

    fun set_publishDate(my_publishDate:String){

        publishDate.value = my_publishDate

    }

    fun get_publishDate():MutableLiveData<String>{

        return publishDate

    }
    fun set_press(my_press:String){

        press.value = my_press

    }

    fun get_press():MutableLiveData<String>{

        return press

    }


    fun set_language(my_language:String){

        language.value = my_language

    }

    fun get_language():MutableLiveData<String>{

        return language

    }


    fun set_ISBN_string(my_ISBN_string:String){

        ISBN_string.value = my_ISBN_string

    }

    fun get_ISBN_string():MutableLiveData<String>{

        return ISBN_string

    }
















}