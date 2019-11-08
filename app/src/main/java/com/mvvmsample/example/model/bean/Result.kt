package com.mvvmsample.example.model.bean

/**
 * Subclasses defined by the sealed class must inherit from the sealed class, representing a restricted set of classes
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data : T): Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}