package sample.qiitaclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import sample.qiitaclient.model.Article
import sample.qiitaclient.model.User
import sample.qiitaclient.view.ArticleView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//      Articleオブジェクトの生成
        val articleView = ArticleView(applicationContext)

        articleView.setArticle(Article(id = "123",
                title = "Kotlin入門",
                url = "http://www.example.com/articles/123",
                user = User(id="456",name="たろう",profileImageUrl = "")))



        setContentView(articleView)
    }
}
