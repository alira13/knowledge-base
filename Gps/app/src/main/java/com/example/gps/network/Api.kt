package com.example.gps.network

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.isSuccess
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType

import io.ktor.http.contentType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    @SerialName("code") val code: Int,
    @SerialName("success") val success: Boolean,
    @SerialName("data") val data: T? = null
)

sealed interface ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>
    data class Error(val th: Throwable, val message: String) : ApiResult<Nothing>

    @Serializable
    data class HttpError(
        @SerialName("code") val code: Int,
        @SerialName("msg") val message: String,
        @SerialName("success") val success: Boolean,
        @SerialName("data") val data: Map<String, String>?
    ) : ApiResult<Nothing>
}

sealed interface ApiResult2 {
    data object Success : ApiResult2
    data class Error(val th: Throwable) : ApiResult2

    @Serializable
    data class HttpError(
        @SerialName("code") val code: Int,
        @SerialName("msg") val message: String,
        @SerialName("success") val success: Boolean,
        @SerialName("data") val data: Map<String, String>?
    ) : ApiResult2
}


class Api(
    val httpClient: HttpClient,
    val sessionIdProvider: () -> String = {""}
) {
    suspend inline fun <reified R> get(
        url: String,
        params: Map<String, Any> = emptyMap()
    ): ApiResult<R> {
        return request<R, Unit>(HttpMethod.Get, url = url, params = params, body = null)
    }

    suspend inline fun download(url: String): ByteArray {
        return httpClient.get(url) {
            header(key = "Cookie", value = "sessionId=" + sessionIdProvider())
        }.body()
    }

    suspend inline fun <reified R, reified B> post(
        url: String,
        body: B,
        params: Map<String, Any> = emptyMap()
    ): ApiResult<R> {
        return request(HttpMethod.Post, url = url, params = params, body = body)
    }

    suspend inline fun getRaw(
        url: String,
    ): ApiResult2 {
        return requestRaw(httpMethod = HttpMethod.Get, url = url, body = null)
    }

    suspend inline fun <reified B> postRaw(
        url: String,
        body: B? = null
    ): ApiResult2 {
        return requestRaw(httpMethod = HttpMethod.Post, url = url, body = body)
    }

    suspend inline fun <reified R> delete(
        url: String,
        params: Map<String, Any> = emptyMap()
    ): ApiResult2 {
        return requestRaw<Unit>(
            httpMethod = HttpMethod.Delete,
            url = url
        )
    }

    suspend inline fun <reified R, reified B> patch(
        url: String,
        body: B?,
        params: Map<String, Any> = emptyMap()
    ): ApiResult<R> {
        return request(HttpMethod.Patch, url = url, params = params, body = body)
    }

    suspend inline fun <reified B : Any> requestRaw(
        httpMethod: HttpMethod,
        url: String,
        body: B? = null
    ): ApiResult2 {
        val response = httpClient.request {
            url {
                method = httpMethod
                protocol = URLProtocol.HTTP
                url(urlString = url)
                contentType(ContentType.Application.Json)
                header(key = "Cookie", value = "sessionId=" + sessionIdProvider())
//                path(path)
                if (body != null) {
                    setBody(body)
                }
            }
        }

        return if (response.status.isSuccess()) {
            runCatching {
//                Napier.d("[ \uD83D\uDC9A ] SUCCESS: $response")
                ApiResult2.Success
            }.getOrElse {
                Napier.e(it) { "[ ‼️ ] Error!" }
                ApiResult2.Error(it)
            }
        } else {
            runCatching { response.body<ApiResult2.HttpError>() }
                .getOrElse {
                    Napier.e(it) { "[ ‼️ ] Error!" }
                    ApiResult2.Error(it)
                }
        }
    }

    suspend inline fun <reified R, reified B> request(
        httpMethod: HttpMethod,
        params: Map<String, Any> = emptyMap(),
        url: String, body: B? = null,
//        errorHandler: suspend ResponseException.() -> T
    ): ApiResult<R> {
        val response = httpClient.request {
            method = httpMethod
            url(urlString = url)
            contentType(ContentType.Application.Json)
            header(key = "Cookie", value = "sessionId=" + sessionIdProvider())
            params.forEach { p -> parameter(p.key, p.value) }
            if (body != null) {
                setBody(body)
            }
        }

        return if (response.status.isSuccess()) {
            runCatching {
                val data = response.body<ApiResponse<R>>()
                ApiResult.Success(data.data!!)
            }.getOrElse {
                Napier.e(it) { "[ ‼️ ] Error!" }
                ApiResult.Error(it, "KMP ERROR!")
            }
        } else {
            runCatching { response.body<ApiResult.HttpError>() }
                .getOrElse {
                    Napier.e(it) { "[ ‼️ ] Error!" }
                    ApiResult.Error(it, "KMP ERROR!")
                }
        }
    }

    suspend inline fun <reified R> postMultipart(
        url: String,
        form: MultiPartFormDataContent
    ): ApiResult<R> {
        Napier.d("SEND MULTIPART ROUTE: $url")

        Napier.d("sessionId= ${sessionIdProvider()}")
        val response = httpClient.post(url) {
            setBody(form)
            header(key = "Cookie", value = "sessionId=" + sessionIdProvider())
            onUpload { bytesSentTotal, contentLength ->
//                Napier.d { "UPLOAD INFO: $bytesSentTotal bytes from $contentLength" }
            }
        }

        return if (response.status.isSuccess()) {
            runCatching {
                val data = response.body<ApiResponse<R>>()
//                Napier.d("[ \uD83D\uDC9A ] SUCCESS: $response")
                ApiResult.Success(data.data!!)
            }.getOrElse {
                Napier.e(it) { "[ ‼️ ] Error!" }
                ApiResult.Error(it, "KMP ERROR!")
            }
        } else {
            runCatching { response.body<ApiResult.HttpError>() }
                .getOrElse {
                    Napier.e(it) { "[ ‼️ ] Error!" }
                    Napier.e("[ ‼️ ] RESPONSE ERROR: $response")
                    ApiResult.Error(it, "KMP ERROR!")
                }
        }
    }
}
