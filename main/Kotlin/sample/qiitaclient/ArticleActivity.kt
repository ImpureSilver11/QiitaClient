package sample.qiitaclient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.provider.CalendarContract
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_article.view.*
import sample.qiitaclient.model.Article
import sample.qiitaclient.view.ArticleView

class ArticleActivity() : AppCompatActivity() {

    companion object {
        private const val ARTICLE_EXTRA: String = "article"
        fun intent(context: Context, article: Article): Intent =
                Intent(context, ArticleActivity::class.java)
                        .putExtra(ARTICLE_EXTRA, article)
    }

//    constructor(parcel: Parcel) : this() {
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val articleView = findViewById<ArticleView>(R.id.article_view)
        val webView = findViewById<WebView>(R.id.web_view)

        val article: Article = intent.getParcelableExtra(ARTICLE_EXTRA)
        articleView.setArticle(article)
        webView.loadUrl(article.url)
    }

//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<ArticleActivity> {
//        override fun createFromParcel(parcel: Parcel): ArticleActivity {
//            return ArticleActivity(parcel)
//        }
//
//        override fun newArray(size: Int): Array<ArticleActivity?> {
//            return arrayOfNulls(size)
//        }
//    }
}