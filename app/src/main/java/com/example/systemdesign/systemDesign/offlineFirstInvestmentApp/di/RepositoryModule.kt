package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.di
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.repository.InvRepositoryImp
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.domain.repository.InvRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(repository: InvRepositoryImp): InvRepository
}