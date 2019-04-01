package ar.com.wolox.android.example.ui.news

import ar.com.wolox.android.example.network.NewsService
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val mRetrofitServices: RetrofitServices) : BasePresenter<INewsView>() {

    override fun onViewAttached() {
        getNews()
    }

    fun getNews() {
        mRetrofitServices.getService(NewsService::class.java).getNews().enqueue(
                networkCallback {
                    onResponseSuccessful { response ->
                        runIfViewAttached { view ->
                            view.loadNews(response)
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
                            view.loadMoreNews(response)
                        }
                    }

                    onResponseFailed { _, _ -> runIfViewAttached(Runnable { view.showError() }) }

                    onCallFailure { runIfViewAttached(Runnable { view.showError() }) }
                }
        )
    }
}