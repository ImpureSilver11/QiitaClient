package sample.qiitaclient.view


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import sample.qiitaclient.R
import sample.qiitaclient.bindView
import sample.qiitaclient.model.Article

class ArticleView : FrameLayout{

    constructor(context: Context?) : super(context)

    constructor(context: Context?,
                attrs: AttributeSet?): super(context, attrs)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr:Int) :super(context, attrs, defStyleAttr)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int,
                defStyleRes:Int) : super(context, attrs, defStyleAttr,defStyleRes)

//    null許容型の表現
//    var profileImageView: ImageView? = null
//var titleTextView:TextView? = null
//    var userNameTectView :TextView?=null
//    lazyを用いた表現
//    val profileImageView: ImageView by lazy {
//    findViewById(R.id.profile_image_view) as ImageView
//}
//    val titleTextView: TextView by lazy {
//        findViewById(R.id.title_text_view) as TextView
//    }
//
//    val userNameTextView: TextView by lazy {
//        findViewById(R.id.user_name_text_view) as TextView
//    }

//    拡張関数により、lazyをsample.qiitaclient.extensionsのbindViewで記述している
    val profileImageView: ImageView by bindView(R.id.profile_image_view)
    val titleTextView: TextView by bindView(R.id.title_text_view)
    val userNameTextView: TextView by bindView(R.id.user_name_text_view)


    init {
        LayoutInflater.from(context).inflate(R.layout.view_article,this)
//        'null許容型を使った表現
//        profileImageView = findViewById(R.id.profile_image_view) as ImageView
//        titleTextView = findViewById(R.id.title_text_view) as TextView
//        userNameTectView = findViewById(R.id.user_name_text_view) as TextView
    }

    fun setArticle(article: Article){
        titleTextView.text = article.title
        userNameTextView.text = article.user.name
        Glide.with(context).load(article.user.profileImageUrl).into(profileImageView)
//        profileImageView?.setBackgroundColor(Color.RED)

    }
}
