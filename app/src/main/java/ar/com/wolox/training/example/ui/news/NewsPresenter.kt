package ar.com.wolox.training.example.ui.news

import ar.com.wolox.training.example.model.News
import ar.com.wolox.training.example.network.NewsService
import ar.com.wolox.training.example.ui.login.repository.LoginRepository
import ar.com.wolox.training.example.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val mRetrofitServices: RetrofitServices, private val loginRepository: LoginRepository) : BasePresenter<INewsView>() {

    override fun onViewAttached() {
        getNews()
    }

    fun getNews() {
        mRetrofitServices.getService(NewsService::class.java).getNews().enqueue(
                networkCallback {
                    onResponseSuccessful { response ->
                        runIfViewAttached { view ->
                            view.loadNews(parseLikes(response))
                        }
                    }

                    onResponseFailed { _, _ -> runIfViewAttached(Runnable { view.showError() }) }

                    onCallFailure { runIfViewAttached(Runnable { view.showError() }) }
                }
        )
    }

    /**
     * Simulate pagination. In a real situation this method must use the param page
     *
     * @param page
     */
    fun loadMoreNews(page: Int) {
        mRetrofitServices.getService(NewsService::class.java).getNews().enqueue(
                networkCallback {
                    onResponseSuccessful { response ->
                        runIfViewAttached { view ->
                            view.loadMoreNews(parseLikes(response))
                        }
                    }

                    onResponseFailed { _, _ -> runIfViewAttached(Runnable { view.showError() }) }

                    onCallFailure { runIfViewAttached(Runnable { view.showError() }) }
                }
        )
    }

    /**
     * Bind Like when current user id exists in property likes (List<Int>)
     */
    private fun parseLikes(newsList: List<News>?): List<News>? {
        val currentUserId = loginRepository.userSession.id.toInt()
        return newsList?.map { getLike(it, currentUserId) }
    }

    private fun getLike(news: News, currentUserId: Int): News {
        news.like = news.likes?.contains(currentUserId) ?: false
        return news
    }
}