package com.example.data.mappers

interface Mapper<S, D> {
    fun transform(data: S): D
}