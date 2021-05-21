package app.mita.chatamin.intent

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val readRequestCode: Int = 42
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //IntentButtonクリック時にSecondActivityへ画面遷移
        intentButton.setOnClickListener {
            val toSecondActivityIntent = Intent(this,SecondActivity::class.java)
            startActivity(toSecondActivityIntent)
        }

        //playStoreButtonクリック時にplayストアを開く
        playStoreButton.setOnClickListener {
            val playStoreIntent = Intent(Intent.ACTION_VIEW)
            playStoreIntent.data = Uri.parse("https://play.google.com/store/apps")
            playStoreIntent.setPackage("com.android.vending")
            startActivity(playStoreIntent)
        }

        //mapButtonクリック時に地図を開く
        mapButton.setOnClickListener {
            val mapIntent = Intent(Intent.ACTION_VIEW)
            mapIntent.data = Uri.parse("geo:35.6473,139.7360")
            if(mapIntent.resolveActivity(packageManager)!=null){
                startActivity(mapIntent)
            }
        }

        //browserButtonクリック時にブラウザを開く
        browserButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse("https://life-is-tech.com/")
            if(browserIntent.resolveActivity(packageManager)!=null){
                startActivity(browserIntent)}
        }

        //galleryButtonクリック時にギャラリーを開く
        galleryButton.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            galleryIntent.addCategory(Intent.CATEGORY_OPENABLE)
            galleryIntent.type = "image/*"
            startActivityForResult(galleryIntent,readRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)

        if(requestCode ==readRequestCode && resultCode == Activity.RESULT_OK){
            resultData?.data?.also{uri ->
                imageView.setImageURI(uri)
            }
        }
    }
}
