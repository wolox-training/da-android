package ar.com.wolox.training.example.ui.login

import ar.com.wolox.training.example.ui.login.model.User
import ar.com.wolox.training.example.ui.login.repository.LoginRepository
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import retrofit2.mock.Calls

class LoginPresenterTest {

    private lateinit var mLoginView: ILoginView
    private lateinit var mLoginPresenter: LoginPresenter
    private lateinit var mLoginRepository: LoginRepository

    @Before
    fun createInstances() {
        MockitoAnnotations.initMocks(this)
        mLoginView = mock(ILoginView::class.java)
        mLoginRepository = mock(LoginRepository::class.java)
        mLoginPresenter = LoginPresenter(mLoginRepository)
    }

    @Test
    fun userSessionIsStored() {
        val user = User()
        user.id = 1
        user.name = "Test"
        mLoginPresenter.attachView(mLoginView)
        mLoginPresenter.saveUserSession(user)
        verify<LoginRepository>(mLoginRepository, times(1)).saveUserSession(user)
    }

    @Test
    fun validateEmail() {
        mLoginPresenter.attachView(mLoginView)
        assertTrue(mLoginPresenter.validateUserEmail("diego.alarcon@wolox.com.ar"))
    }

    @Test
    fun validateForm() {
        mLoginPresenter.attachView(mLoginView)
        mLoginPresenter.validateForm("diego.alarcon@wolox.com.ar", "12345678")
        verify(mLoginView, times(1)).onFormIsValid()
    }

    @Test
    fun validateLoginApiFail() {
        mLoginPresenter.attachView(mLoginView)
        val users = emptyList<User>()
        Mockito.`when`(mLoginRepository.doGetUser("diego.alarcon@wolox.com.ar", "12345678")).thenReturn(Calls.response(users))

        mLoginPresenter.performLogin("diego.alarcon@wolox.com.ar", "12345678")
        Mockito.verify(mLoginView).onUserLoginFailed()
    }

    @Test
    fun validateLoginApiSuccess() {
        mLoginPresenter.attachView(mLoginView)
        val users = mutableListOf<User>()
        users.add(User())
        Mockito.`when`(mLoginRepository.doGetUser("susan.stevens38@example.com", "12345678")).thenReturn(Calls.response(users))

        mLoginPresenter.performLogin("susan.stevens38@example.com", "12345678")
        Mockito.verify(mLoginView).onUserLoginSucceeded(users[0])
    }
}