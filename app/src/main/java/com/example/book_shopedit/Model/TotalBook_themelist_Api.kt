package com.example.book_shopedit.Model

import android.app.Activity
import com.example.book_shopedit.Data.my_data_booktheme_list
import com.example.book_shopedit.ViewModel.ViewModel_Total_list
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

private var OkHttpClient = okhttp3.OkHttpClient().newBuilder().build()


fun this_sort_themelist(viewModel: ViewModel_Total_list, activity: Activity, theme:String, list: MutableList<Int>){

    val my_book_list:MutableList<my_data_booktheme_list> = ArrayList<my_data_booktheme_list>()
    val url = "http://104.199.148.167/api/themes/books/$theme"
    val request = Request.Builder().url(url).build()
    val call1 = OkHttpClient .newCall(request)
    call1.enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {

            val myjson = response.body()?.string()!!.trim()

            val code = response.code()

            if (code == 200){

                val jsonObject = JSONObject(myjson)

                val data = jsonObject.getJSONArray("data")

                for (i in 0 until data.length()) {

                    val my_object = data.getJSONObject(i)
                    val title = my_object.getString("title")
                    val author = my_object.getString("author")
                    val translator = my_object.getString("translator")
                    val press = my_object.getString("press")
                    val intro = my_object.getString("intro")
                    val image = my_object.getString("image")
                    val comment_score = my_object.getInt("comment_score")
                    val book_id = my_object.getInt("book_id")
                    val theme_id = my_object.getInt("theme_id")

                    if (book_id in list) {

                        my_book_list.add(
                            my_data_booktheme_list(
                                title,
                                author,
                                translator,
                                press,
                                intro,
                                image,
                                comment_score,
                                book_id,
                                theme_id,
                                true
                            )
                        )


                    } else {

                        my_book_list.add(
                            my_data_booktheme_list(
                                title,
                                author,
                                translator,
                                press,
                                intro,
                                image,
                                comment_score,
                                book_id,
                                theme_id,
                                false
                            )
                        )

                    }

                }


                activity.runOnUiThread {

                    viewModel.set_total_theme_list(my_book_list)

                }

            }

        }

    })

}
