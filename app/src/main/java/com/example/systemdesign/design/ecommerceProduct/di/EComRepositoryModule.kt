package com.example.systemdesign.design.ecommerceProduct.di

import com.example.systemdesign.design.ecommerceProduct.data.repository.EComRepositoryImp
import com.example.systemdesign.design.ecommerceProduct.domain.repository.EComRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EComRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindEComRepository(
        eComRepositoryImp: EComRepositoryImp
    ): EComRepository
}
