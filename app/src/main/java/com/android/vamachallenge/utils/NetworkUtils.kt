package com.android.vamachallenge.utils

import com.android.vamachallenge.models.NetworkError
import com.android.vamachallenge.models.Resource
import com.google.gson.Gson
import retrofit2.Response

object NetworkUtils {

    suspend fun <T> safeApiCall(block: suspend () -> Response<T>): Resource<T> {
        try {
            val response = block.invoke()
            return if (response.isSuccessful) {
                Resource.Success(data = response.body()!!)
            } else {
                val error = Gson().fromJson(response.errorBody()?.string(), NetworkError::class.java)
                Resource.Failure(networkError = error)
            }
        } catch (e: Exception) {
            val error = NetworkError(error = e.message ?: "")
            return Resource.Failure(networkError = error)
        }
    }
}