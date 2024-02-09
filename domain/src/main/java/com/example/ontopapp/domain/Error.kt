package com.example.ontopapp.domain

interface Error {
    class Server(val code: Int) : Error
    data object Connectivity : Error
    class Unknown(val message: String) : Error
    data object  EmptyEditText : Error
}
