package com.github.stephenWanjala.demo.core.presentation

import com.github.stephenWanjala.demo.core.util.Resource
import com.github.stephenWanjala.demo.domain.repository.AuthRepository
import com.github.stephenWanjala.demo.login.domain.model.LoginRequest
import com.github.stephenWanjala.demo.login.domain.model.LoginResponse
import com.github.stephenWanjala.demo.signup.domain.model.RegisterRequest
import com.github.stephenWanjala.demo.signup.domain.model.RegisterResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthPresenter(
    private val authRepository: AuthRepository,
    private val scope: CoroutineScope
) {
    private val emailRegex = Regex("^([a-zA-Z0-9_.+-]+)@([a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+)$")

    private var _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.stateIn(scope = scope, started = SharingStarted.WhileSubscribed(), LoginState())

    private var _registerState = MutableStateFlow(RegisterState())
    val registerState =
        _registerState.stateIn(scope = scope, started = SharingStarted.WhileSubscribed(), RegisterState())


    init {
        scope.launch {
            _loginState.update { it.copy(isLoading = true) }
            if (loginState.value.loginResponse !== null) {
                val result = authRepository.authenticate(loginState.value.loginResponse!!.token)
                result.collectLatest { value ->
                    when (value) {
                        is Resource.Error -> _loginState.update { it.copy(error = value.error) }
                        is Resource.Loading -> _loginState.update { it.copy(isLoading = true) }
                        is Resource.Success -> {
                            value.data?.let { isAuthenticated ->
                                _loginState.update { it.copy(authenticated = isAuthenticated) }
                            }
                        }
                    }
                }
            }
            _loginState.update { it.copy(isLoading = false) }
        }
    }


    fun onLoginEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnterEmail -> {
                _loginState.update { it.copy(email = event.value) }
                validLoginInputs()
            }

            is LoginEvent.EnteredPassword -> {
                _loginState.update { it.copy(password = event.value) }
                validLoginInputs()
            }

            LoginEvent.IsLoginButtonEnabled -> {
                validLoginInputs()
            }

            LoginEvent.Login -> {
                login()
            }
        }

    }

    private fun validLoginInputs() {
        _loginState.update {
            it.copy(
                loginButtonEnabled = (loginState.value.email.isNotBlank() && loginState.value.password.isNotEmpty() && loginState.value.password.length > 6
                        && emailRegex.matches(loginState.value.email))
            )
        }
    }

    fun onRegisterEvent(event: RegisterEvent) {
        when (event) {
            RegisterEvent.IsRegisterButtonEnabled -> {
                _registerState.update {
                    it.copy(
                        registerButtonEnabled = (validRegisterInputs())
                    )
                }
            }

            is RegisterEvent.RegisterEmail -> {
                _registerState.update { it.copy(email = event.value) }
                _registerState.update {
                    it.copy(
                        registerButtonEnabled = (validRegisterInputs())
                    )
                }
            }
            is RegisterEvent.RegisterName -> {
                _registerState.update { it.copy(username = event.value) }
                _registerState.update {
                    it.copy(
                        registerButtonEnabled = (validRegisterInputs())
                    )
                }
            }
            is RegisterEvent.RegisterPassword -> {
                _registerState.update { it.copy(password = event.value.take(10)) }
                _registerState.update {
                    it.copy(
                        registerButtonEnabled = (validRegisterInputs())
                    )
                }
            }
            RegisterEvent.Register -> {
                register()
            }
        }
    }

    private fun validRegisterInputs() = (_registerState.value.email.isNotBlank() &&
            _registerState.value.password.isNotEmpty() &&
            _registerState.value.password.length > 6
            && registerState.value.username.isNotBlank()
            && registerState.value.username.length > 3
            && emailRegex.matches(registerState.value.email))

    private fun login() {
        scope.launch {
            _loginState.update { it.copy(isLoading = true) }
            val responseFlow = authRepository.login(
                LoginRequest(
                    email = loginState.value.email,
                    password = loginState.value.password
                )
            )

            responseFlow.collectLatest { loginResource ->
                when (loginResource) {
                    is Resource.Error -> {
                        _loginState.update { it.copy(error = loginResource.error) }
                    }

                    is Resource.Loading -> _loginState.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        loginResource.data?.let { response ->
                            _loginState.update { it.copy(loginResponse = response) }
                        }
                        _loginState.update { it.copy(isLoading = false) }
                    }
                }
            }

        }
    }


    private fun register() {
        scope.launch {
            _registerState.update { it.copy(isLoading = true) }
            val result = authRepository.register(
                RegisterRequest(
                    email = registerState.value.email,
                    password = registerState.value.password,
                    name = registerState.value.username
                )
            )
            result.collectLatest { value ->
                when (value) {
                    is Resource.Error -> _registerState.update { it.copy(error = value.error) }
                    is Resource.Loading -> _registerState.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        value.data?.let { data ->
                            _registerState.update { it.copy(regResult = data) }
                        }
                    }
                }
            }
            _registerState.update { it.copy(isLoading = false) }
        }
    }

}


data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val error: Throwable? = null,
    val loginButtonEnabled: Boolean = false,
    val loginResponse: LoginResponse? = null,
    val authenticated: Boolean = false,
)


data class RegisterState(
    val isLoading: Boolean = false,
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val error: Throwable? = null,
    val regResult: RegisterResponse? = null,
    val registerButtonEnabled: Boolean = false,
)


sealed class LoginEvent {
    data class EnterEmail(val value: String) : LoginEvent()
    data class EnteredPassword(val value: String) : LoginEvent()

    object Login : LoginEvent()
    object IsLoginButtonEnabled : LoginEvent()

}


sealed class RegisterEvent {
    data class RegisterEmail(val value: String) : RegisterEvent()
    data class RegisterName(val value: String) : RegisterEvent()
    data class RegisterPassword(val value: String) : RegisterEvent()

    object IsRegisterButtonEnabled : RegisterEvent()
    object Register : RegisterEvent()

}

