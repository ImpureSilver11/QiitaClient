package sample.qiitaclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DialogTitle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import sample.qiitaclient.model.Article
import sample.qiitaclient.model.User
import sample.qiitaclient.view.ArticleView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val listAdapter = ArticleListAdapter(applicationContext)
        listAdapter.articles = listOf(dummyArticle("Kotlin入門","たろう"),
                dummyArticle("JAVA入門","二郎"))

        val listView: ListView = findViewById(R.id.list_view) as ListView
        listView.adapter = listAdapter
        listView .setOnItemClickListener{ AdapterView, View, position, id ->
            val article =listAdapter .articles[position]
            ArticleActivity.intent(this,article).let { startActivity(it) }
        }
//      Articleオブジェクトの生成
//        val articleView = ArticleView(applicationContext)
//
//        articleView.setArticle(Article(id = "123",
//                title = "Kotlin入門",
//                url = "http://www.example.com/articles/123",
//                user = User(id="456",name="たろう",profileImageUrl = "")))
//
//
//
//        setContentView(articleView)
    }
    private fun dummyArticle(title: String , userName: String ): Article =
            Article(id= "",
                    title= title,
                    url="http://kotlinlang.org",
                    user = User(id="",name=userName,profileImageUrl = ""))
}
