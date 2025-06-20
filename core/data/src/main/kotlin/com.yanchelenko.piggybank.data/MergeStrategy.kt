package com.yanchelenko.piggybank.data

/*
public interface MergeStrategy<E> {

    public fun merge(
        right: E,
        left: E
    ): E
}

internal class RequestResponseMergeStrategy<T : Any> : MergeStrategy<com.yanchelenko.piggybank.common.RequestResult<T>> {
    @Suppress("CyclomaticComplexMethod")
    override fun merge(
        right: com.yanchelenko.piggybank.common.RequestResult<T>,
        left: com.yanchelenko.piggybank.common.RequestResult<T>
    ): com.yanchelenko.piggybank.common.RequestResult<T> {
        return when {
            right is InProgress && left is InProgress -> merge(right, left)
            right is Success && left is RequestResult.InProgress -> merge(right, left)
            right is InProgress && left is Success -> merge(right, left)
            right is Success && left is Success -> merge(right, left)
            right is Success && left is Error -> merge(right, left)
            right is InProgress && left is Error -> merge(right, left)
            right is Error && left is InProgress -> merge(right, left)
            right is Error && left is Success -> merge(right, left)

            else -> error("Unimplemented branch right=$right & left=$left")
        }
    }

    private fun merge(
        cache: InProgress<T>,
        server: InProgress<T>
    ): com.yanchelenko.piggybank.common.RequestResult<T> {
        return when {
            server.data != null -> InProgress(server.data)
            else -> InProgress(cache.data)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun merge(
        cache: Success<T>,
        server: InProgress<T>
    ): com.yanchelenko.piggybank.common.RequestResult<T> {
        return InProgress(cache.data)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun merge(
        cache: InProgress<T>,
        server: Success<T>
    ): com.yanchelenko.piggybank.common.RequestResult<T> {
        return InProgress(server.data)
    }
    /*

    private fun merge(
        cache: Success<T>,
        server: Error<T>
    ): com.yanchelenko.piggybank.common.com.yanchelenko.piggybank.common.RequestResult<T> {
        return Error(data = cache.data, error = server.error)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun merge(
        cache: Success<T>,
        server: Success<T>
    ): com.yanchelenko.piggybank.common.com.yanchelenko.piggybank.common.RequestResult<T> {
        return Success(data = server.data)
    }

    private fun merge(
        cache: InProgress<T>,
        server: Error<T>
    ): com.yanchelenko.piggybank.common.com.yanchelenko.piggybank.common.RequestResult<T> {
        return Error(data = server.data ?: cache.data, error = server.error)
    }

    private fun merge(
        cache: Error<T>,
        server: InProgress<T>
    ): com.yanchelenko.piggybank.common.com.yanchelenko.piggybank.common.RequestResult<T> {
        return server
    }

    private fun merge(
        cache: Error<T>,
        server: Success<T>
    ): com.yanchelenko.piggybank.common.com.yanchelenko.piggybank.common.RequestResult<T> {
        return server
    }

     */
}

 */
