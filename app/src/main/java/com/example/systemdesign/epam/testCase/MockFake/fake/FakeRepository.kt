package com.example.systemdesign.epam.testCase.MockFake.fake

import com.example.systemdesign.epam.testCase.MockFake.Repository

class FakeRepository: Repository {
    override suspend fun login(email: String, password: Int): Boolean {
      return email=="sh@gmail.com" && password ==1234
    }
}