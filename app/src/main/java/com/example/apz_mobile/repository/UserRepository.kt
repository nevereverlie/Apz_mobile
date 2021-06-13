package com.example.apz_mobile.repository

import com.example.apz_mobile.models.LoginRequest
import com.example.apz_mobile.models.RegisterRequest
import com.example.apz_mobile.models.User
import io.reactivex.Single


class UserRepository {

    private val apiService = ApiService.getWebService()

    fun loginUser(username: String, password: String) =
        apiService.loginUser(LoginRequest(username, password))

    fun registerUser(username: String, password: String, firstname: String, lastname: String) =
        apiService.registerUser(RegisterRequest(username, password, firstname, lastname))

    fun getUsers(): Single<List<User>> =
        apiService.getUsers()

    fun getUserById(userId: Int): Single<User> =
        apiService.getUser(userId)

    //fun getUser(): Single<List<User>> = apiService.getUser(ApiService.token)
}