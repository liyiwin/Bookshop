package com.example.book_shopedit.Recoder_Page.NoteFragment

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CompoundButton
import android.widget.RatingBar
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

import com.example.book_shopedit.R
import com.example.book_shopedit.ViewModel.ViewModel_AddNote
import kotlinx.android.synthetic.main.fragment_add_note__attention.*
import kotlinx.android.synthetic.main.fragment_add_note__attention.view.*

/**
 * A simple [Fragment] subclass.
 */
class AddNote_AttentionFragment : Fragment() {

    lateinit var rootView:View

    var uri: Uri? = null

    var  my_path:String? = ""

   private val  viewModel_addNote by lazy { ViewModelProviders.of(activity!!
   ).get(ViewModel_AddNote::class.java) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_add_note__attention, container, false)

        permission()

        // 拍照按鈕

        rootView.upload_btn.setOnClickListener {

            intent_to_camera()

        }

        //  書名 Edittext

        val  listener_bookname = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                val viewmodel = viewModel_addNote

                Log.e("viewmodel1","$viewmodel")

                viewModel_addNote.set_title(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        }

        rootView.book_name_ed.addTextChangedListener(listener_bookname)


        //  出版社 Edittext

        val listener_publish  = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                viewModel_addNote.set_press(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


        }

          rootView.press_ed.addTextChangedListener(listener_publish)

        //  作者 Edittext

        val listener_author = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                viewModel_addNote.set_author(s.toString())

                       }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


          }

     rootView.author_ed.addTextChangedListener(listener_author)

     // 選擇語言的spinner

     rootView.language_spinner.onItemSelectedListener = object:  AdapterView.OnItemSelectedListener {
         override fun onNothingSelected(parent: AdapterView<*>?) {

              }

         override fun onItemSelected(
             parent: AdapterView<*>?,
             view: View?,
             position: Int,
             id: Long
         ) {
              val languge_array = resources.getStringArray(R.array.languge_array)

              val languge = languge_array[position]

              viewModel_addNote.set_language(languge)
         }


     }

        // 是否閱讀中的 checkBox


        rootView.status_checkBox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

                          if (isChecked == true){

                              viewModel_addNote.set_status("1")


                          }

                          else{

                              viewModel_addNote.set_status("0")


                          }


            }
        })

        // 評分的 RatingBar


       rootView.book_rating.onRatingBarChangeListener = object : RatingBar.OnRatingBarChangeListener{

           override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
               val myscore = rating.toInt().toString()

               viewModel_addNote.set_score(myscore)


           }

       }
        return   rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode == Activity.RESULT_OK){

            val cr = context!!.contentResolver

            val cursor = cr.query(uri!!, null, null, null, null)

            cursor?.moveToFirst()

            if (cursor != null) {

                my_path = cursor.getString(1)

                Log.e("path","path=" + my_path)
                // 这个就是我们想要的原图的路径
                cursor.close()

                viewModel_addNote.set_path(my_path!!)
            }

            Glide.with(context!!).load(uri).into(my_uploadphoto)

        }

        super.onActivityResult(requestCode, resultCode, data)


    }


    fun  permission(){

        val  permissionCheck1 = ActivityCompat.checkSelfPermission(context!!, android.Manifest.permission.WRITE_EXTERNAL_STORAGE )
        val  permissionCheck2 = ActivityCompat.checkSelfPermission(context!!, android.Manifest.permission.READ_EXTERNAL_STORAGE )
        val  permissionCheck3 = ActivityCompat.checkSelfPermission(context!!,android.Manifest.permission.CAMERA)

        if (permissionCheck1!= PackageManager.PERMISSION_GRANTED){

            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),0)

        }



        if (permissionCheck2 != PackageManager.PERMISSION_GRANTED){

            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),0)

        }


        if(permissionCheck3 != PackageManager.PERMISSION_GRANTED){

            requestPermissions(arrayOf(android.Manifest.permission.CAMERA),0)

        }

    }

    fun intent_to_camera(){

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        uri = context!!.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ContentValues());
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 1);


    }


}
