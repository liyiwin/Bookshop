package com.example.book_shopedit.Model

import android.app.Activity
import com.example.book_shopedit.ViewModel.ViewModel_BookDetail
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()

fun get_book_detail(activity: Activity,id:String,viewModel: ViewModel_BookDetail){

    val url = "http://104.199.148.167/api/books/$id"
    val request = Request.Builder().url(url).build()
    val call = OkHttpClient .newCall(request)
    call.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()

            val code = response.code()

            if (code == 200){

                val jsonObject = JSONObject(myjson)
                val data = jsonObject.getJSONArray("data")
                val comment_score = data.getJSONObject(0).getInt("comment_score")
                val my_image =  data.getJSONObject(0).getString("image")
                val my_author =   data.getJSONObject(0).getString("author")
                val my_introduce =data.getJSONObject(0).getString("intro")
                val my_title =  data.getJSONObject(0).getString("title")
                val my_price =data.getJSONObject(0).getInt("price")
                val my_id = data.getJSONObject(0).getInt("id")

                activity.runOnUiThread(object :Runnable{
                    override fun run() {

                        viewModel.set_comment_score(comment_score.toString())

                        viewModel.set_image(my_image)

                        viewModel.set_author(my_author)

                        viewModel.set_introduce(my_introduce)

                        viewModel.set_title(my_title)

                        viewModel.set_price(my_price.toString())

                        viewModel.set_id(my_id.toString())


                    }

                })

            }

        }
    })

}


fun get_book_publish(activity: Activity,my_id:String,viewModel: ViewModel_BookDetail){

    val url2 = "http://104.199.148.167/api/publisher/books/$my_id "
    val request2 = Request.Builder().url(url2).build()
    val call2 = OkHttpClient .newCall(request2)
    call2.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()

            val code = response.code()

            if (code == 200){

                val jsonObject = JSONObject(myjson)

                val data = jsonObject.getJSONArray("data")
                val my_author =  data.getJSONObject(0).getString("author")
                val publication_date =   data.getJSONObject(0).getString("publication_date")
                val press =data.getJSONObject(0).getString("press")
                val language =data.getJSONObject(0).getString("language")
                val ISBN =data.getJSONObject(0).getString("ISBN")

                activity.runOnUiThread(object :Runnable{
                    override fun run() {

                        viewModel.set_publishDate(publication_date)

                        viewModel.set_press(press)

                        viewModel.set_language(language)

                        viewModel.set_ISBN_string(ISBN)


                    }

                })

            }


        }
    })

}




fun rating_book(id_add:String,mytoken:String,comment_star:String,activity: Activity,viewModel: ViewModel_BookDetail){


    val url = "http://104.199.148.167/api/books/stars"
    val body =  FormBody.Builder().add("book_id",id_add).add("api_token",mytoken).add("comment_star",comment_star).build()
    val request = Request.Builder().url(url).post(body) .build()
    val call = OkHttpClient .newCall(request)
    call.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()

            val code = response.code()

            if (code == 200){

                val jsonObject = JSONObject(myjson)

                val score = jsonObject.getInt("data")

                activity.runOnUiThread{

                    viewModel.set_comment_score(score.toString())

                }
            }
        }
    })

}



fun add_book_into_wishlist(id_add:String,mytoken:String,activity: Activity,viewModel: ViewModel_BookDetail){


    val url2 = "http://104.199.148.167/api/books/addToWishList"
    val body2 = FormBody.Builder().add("book_id",id_add).add("api_token",mytoken).build()
    val request2 = Request.Builder().url(url2).post(body2) .build()
    val call2 = OkHttpClient .newCall(request2)
    call2.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()

            val code = response.code()

            if (code == 200){

                activity.runOnUiThread{

                   viewModel.set_wishlist_status("in wishlist")


                }

            }

        }
    })


}


fun delete_book_from_wishlist (id_delete:String,mytoken:String,activity:Activity,viewModel: ViewModel_BookDetail){

    val url3 = "http://104.199.148.167/api/books/deleteWishBook/$id_delete"

    val body3 = FormBody.Builder().add("book_id", id_delete).add("api_token", mytoken).build()

    val request3 = Request.Builder().url(url3).delete(body3).build()

    val call3 = OkHttpClient.newCall(request3)

    call3.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val code = response.code()

            if (code == 200) {

                activity.runOnUiThread {


                    viewModel.set_wishlist_status("not in wishlist")


                }

            }

        }


    })

}



fun existOrnot_in_wishlist(activity:Activity,id: String,mytoken: String,viewModel: ViewModel_BookDetail){

    val url2 = "http://104.199.148.167/api/books/existOrNot/$id"
    val request2 = Request.Builder().url(url2).addHeader("Authorization", "Bearer $mytoken").build()
    val call2 = OkHttpClient .newCall(request2)
    call2.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {



            val myjson = response.body()?.string()!!.trim()

            val code2 = response.code()

            if (code2 == 200){

                val jsonObject = JSONObject(myjson)

                val messege =  jsonObject.getString("message")

                activity.runOnUiThread {


                    if (messege == "The book is not on the list."){

                        viewModel.set_wishlist_status("not in wishlist")


                    }

                    else{

                        viewModel.set_wishlist_status("in wishlist")


                    }


                }




            }
        }
    })

}