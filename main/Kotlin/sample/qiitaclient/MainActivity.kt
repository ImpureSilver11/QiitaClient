package sample.qiitaclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import sample.qiitaclient.client.ArticleClient
import sample.qiitaclient.model.Article
import sample.qiitaclient.model.User


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.list_view) as ListView
        val listAdapter = ArticleListAdapter(applicationContext)
        val queryEditText =findViewById(R.id.query_edit_text) as EditText
        val searchButton = findViewById(R.id.search_button) as Button

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://qiita.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        val articleClient = retrofit.create(ArticleClient::class.java)

//        listAdapter.articles = listOf(dummyArticle("Kotlin入門","たろう"),
//                dummyArticle("JAVA入門","二郎"))
//
//
//        listView.adapter = listAdapter
//        listView .setOnItemClickListener{ adapterView, View, position, id ->
//            val article =listAdapter.articles[position]
//            ArticleActivity.intent(this,article).let { startActivity(it) }
//        }

        searchButton.setOnClickListener{
            articleClient.search(queryEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        progress_bar.visibility = View.GONE
                    }
//                    .bindToLifecycle(this)
                    .subscribe({
                        queryEditText.text.clear()
                        listAdapter.articles = it
                        listAdapter.notifyDataSetChanged()
                    }, {
                        toast("エラー: $it")
                    })
        }
//      Articleオブジェクトの生成
//        val articleView = ArticleView(applicationContext)
//
//        articleView.setArticle(Article(id = "123",
//                title = "Kotlin入門",
//                url = "http://www.example.com/articles/123",
//                user = User(id="456",name="たろう",profileImageUrl = "")))
//        setContentView(articleView)
    }
    private fun dummyArticle(title: String , userName: String ): Article =
            Article(id= "",
                    title= title,
                    url="http://kotlinlang.org",
                    user = User(id="",name=userName,profileImageUrl = ""))
}
