package com.example.systemdesign.epam.testCase.MockFake


interface Repository {
    suspend fun login(email: String,password: Int): Boolean
}