package ar.com.wolox.android.example.ui.newsdetail

import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.model.ToggleLikeDTO
import ar.com.wolox.android.example.network.NewsService
import ar.com.wolox.android.example.ui.login.repository.LoginRepository
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import java.util.ArrayList
import javax.inject.Inject

class NewsDetailPresenter @Inject constructor(private val mRetrofitServices: RetrofitServices, private val loginRepository: LoginRepository) : BasePresenter<INewsDetailView>() {

    fun toggleLike(news: News?) {
        val currentUserId = loginRepository.userSession.id.toInt()
        var like = false
        if (news?.likes?.contains(currentUserId) == true) {
            (news.likes as ArrayList).remove(currentUserId)
        } else {
            (news?.likes as ArrayList).add(currentUserId)
            like = true
        }

        callToggleLike(news.likes, news.id, like)
    }

    private fun callToggleLike(likes: List<Int>?, newsId: Int?, like: Boolean) {
        newsId?.let {
            mRetrofitServices.getService(NewsService::class.java).toogleLike(ToggleLikeDTO(likes), it).enqueue(networkCallback {
                onResponseSuccessful {
                    view.onToggleSuccess(like)
                }
                onResponseFailed { _, _ -> runIfViewAttached(Runnable { view.showError() }) }

                onCallFailure { runIfViewAttached(Runnable { view.showError() }) }
            })
        }
    }
}