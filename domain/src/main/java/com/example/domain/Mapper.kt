package com.example.domain

interface Mapper<S, D> {
    fun transform(data: S): D
}