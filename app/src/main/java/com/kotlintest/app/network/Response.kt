package com.kotlintest.app.network

import com.app.washeruser.repository.Status
import com.kotlintest.app.model.Sample

class Response private constructor(val status: Status, val data: List<Sample>?, val error: Throwable?) {
    companion object {

        fun loading(): Response {
            return Response(Status.LOADING, null, null)
        }
        fun loadingSecond(): Response {
            return Response(Status.SECONDLOADING, null, null)
        }

        fun dismiss(): Response {

            return Response(Status.DISMISS, null, null)
        }

        fun success(data: List<Sample>): Response {
            return Response(Status.SUCCESS, data, null)
        }

        fun error(error: Throwable): Response {
            return Response(Status.ERROR, null, error)
        }
    }
}