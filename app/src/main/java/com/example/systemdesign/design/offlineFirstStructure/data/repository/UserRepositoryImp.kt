package com.example.systemdesign.design.offlineFirstStructure.data.repository

import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.systemdesign.design.offlineFirstStructure.data.local.UserDao
import com.example.systemdesign.design.offlineFirstStructure.data.local.UserEntity
import com.example.systemdesign.design.offlineFirstStructure.data.remote.UserApiService
import com.example.systemdesign.design.offlineFirstStructure.repository.UserRepository
import com.example.systemdesign.design.offlineFirstStructure.sync.UserSync
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(val workManager: WorkManager, val userService: com.example.systemdesign.design.offlineFirstStructure.data.remote.UserApiService, val userDao: com.example.systemdesign.design.offlineFirstStructure.data.local.UserDao):
    com.example.systemdesign.design.offlineFirstStructure.repository.UserRepository {

    override  fun getUsers(): Flow<List<com.example.systemdesign.design.offlineFirstStructure.data.local.UserEntity>> {
      return userDao.getUsers()
    }

    override suspend fun getUserFromRemote():List<com.example.systemdesign.design.offlineFirstStructure.data.local.UserEntity> {
      return userService.getUsers().users
    }



    override suspend fun insertUser(users: List<com.example.systemdesign.design.offlineFirstStructure.data.local.UserEntity>) {
       userDao.insertUsers(users)
    }

    override suspend  fun enQueWork(){
        val workRequest = OneTimeWorkRequestBuilder<com.example.systemdesign.design.offlineFirstStructure.sync.UserSync>()
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(true)
                    .build()
            )
            .setBackoffCriteria(BackoffPolicy.EXPONENTIAL,1000,TimeUnit.MILLISECONDS)
            .build()
        workManager.enqueue(workRequest)
    }
}