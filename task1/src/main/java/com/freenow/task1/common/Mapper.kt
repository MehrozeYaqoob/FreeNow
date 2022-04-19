package com.freenow.task1.common

interface Mapper<From, To> {
    fun map(from: From?): To
}
