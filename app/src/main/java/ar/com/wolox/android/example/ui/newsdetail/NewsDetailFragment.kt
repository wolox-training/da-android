package ar.com.wolox.android.example.ui.newsdetail

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.imagefullscreen.ImageFullScreenActivity
import ar.com.wolox.android.example.ui.imagefullscreen.ImageFullScreenActivity.Companion.IMAGE_URL
import ar.com.wolox.android.example.utils.getTimeFromDate
import ar.com.wolox.android.example.utils.toast
import ar.com.wolox.wolmo.core.fragment.WolmoDialogFragment
import kotlinx.android.synthetic.main.content_news_detail.*
import kotlinx.android.synthetic.main.fragment_news_detail.*

class NewsDetailFragment : WolmoDialogFragment<NewsDetailPresenter>(), INewsDetailView {

    private var previousLike: Boolean? = null
    private var currentLike: Boolean? = null

    override fun init() {
        loadIntentData()
    }

    override fun layout(): Int {
        return R.layout.fragment_news_detail
    }

    override fun setListeners() {
        vNewsDetailBackArrow?.setOnClickListener { activity?.onBackPressed() }
    }

    companion object {
        const val NEWS_DATA = "news_data"
        const val NEWS_DETAIL = 3003
        const val LIKE_DATA = "like_data"
    }

    private fun loadIntentData() {
        activity?.let { ctx ->
            val news = ctx.intent?.extras?.get(NEWS_DATA) as News?
            bindData(news)
        }
    }

    private fun bindData(news: News?) {
        news?.let {
            vDetailNewsPicture?.setImageURI(news.picture.orEmpty())
            vContentNewsDetailTitle?.text = news.title
            vContentNewsDetailText.text = news.text
            vContentNewsDetailTime.text = getTimeFromDate(news.createdAt)
            activity?.let { ctx -> toggleLikeImage(news.like, ctx, vContentNewsDetailLike) }
            vDetailNewsPicture?.setOnClickListener { openImageFullScreen(news.picture.orEmpty()) }
            vContentNewsDetailLike?.setOnClickListener {
                presenter.toggleLike(news)
                vContentNewsDetailLike?.isClickable = false
            }
            previousLike = news.like
        }
    }

    private fun toggleLikeImage(like: Boolean, context: Context, imageView: ImageView) {
        when {
            like -> imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_like_on))
            else -> imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_like_off))
        }
    }

    private fun openImageFullScreen(url: String) {
        val imageFullScreenIntent = Intent(activity, ImageFullScreenActivity::class.java)
        imageFullScreenIntent.putExtra(IMAGE_URL, url)
        startActivity(imageFullScreenIntent)
    }

    override fun showError() {
        vContentNewsDetailLike?.isClickable = true
        activity?.toast(getString(R.string.unknown_error))
    }

    override fun onToggleSuccess(like: Boolean) {
        activity?.let { ctx -> toggleLikeImage(like, ctx, vContentNewsDetailLike) }
        currentLike = like
        vContentNewsDetailLike?.isClickable = true
    }

    private fun finishWithResult() {
        val intent = Intent()
        currentLike?.let {
            if (previousLike != it) intent.putExtra(LIKE_DATA, it)
            activity?.setResult(RESULT_OK, intent)
        } ?: activity?.setResult(RESULT_CANCELED, intent)
    }

    override fun onBackPressed(): Boolean {
        finishWithResult()
        return super.onBackPressed()
    }
}
