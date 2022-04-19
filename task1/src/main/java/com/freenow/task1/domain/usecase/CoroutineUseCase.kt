package com.freenow.task1.domain.usecase

interface CoroutineUseCase<Params, Result> {
    suspend fun run(params: Params): Result
}
