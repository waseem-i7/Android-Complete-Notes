 package com.mwi7.newsbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class MainActivity : AppCompatActivity() {

     companion object{
         private const val API_KEY = "b988c0d46813450ba095643143ea338b"
         private const val COUNTRY="in"
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       fetchNews()
    }

     private fun fetchNews() {
         val networkHelper=NetworkHelper(this)
         if(networkHelper.isNetworkConnected()){
             val request = RetrofitBuilder.buildService()
             val call=request.getNews(COUNTRY, API_KEY)
             showProgress()
             call.enqueue(object : Callback<NewsResponse>{
                 override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                     hideProgress()
                    if(response.isSuccessful && response.body() !=null){
                        val newsResponse=response.body()!!
                        val news =newsResponse.articles
                        showNews(news)
                    }else{
                        hideProgress()
                        showErrorMessage(resources.getString(R.string.error_msg))
                    }
                 }

                 override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                     hideProgress()
                   showErrorMessage(t.message)
                 }

             })

         }else{
            showErrorMessage(resources.getString(R.string.error_msg))
         }
     }

     private fun showNews(news: List<News>) {
        recyclerView.visibility= View.VISIBLE
        progressBar.visibility=View.GONE
         recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator=DefaultItemAnimator()
        recyclerView.adapter= news?.let { NewsAdapter(it) }
    }

     private fun showProgress(){
         progressBar.visibility=View.VISIBLE
     }
     private fun hideProgress(){
         progressBar.visibility=View.GONE
     }
     private fun showErrorMessage(errorMessage :String?){
         errorView.visibility=View.VISIBLE
         errorView.text=errorMessage
     }
}