package com.example.systemdesign.epam.testCase.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FlowUtil {

   suspend fun flow(): Flow<Int>{
        return flowOf(4,5)
    }
}