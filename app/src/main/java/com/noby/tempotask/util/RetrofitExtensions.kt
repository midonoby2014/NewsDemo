package com.noby.tempotask.util

import retrofit2.Response
import retrofit2.Retrofit

// Retrofit


inline fun <reified T> Retrofit.create(): T = create(T::class.java)

fun <T> httpError(code: Int): Response<T> = Response.error<T>(code, null)