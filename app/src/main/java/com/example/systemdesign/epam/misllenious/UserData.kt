package com.example.systemdesign.epam.misllenious


data class UserData(
    val profile: UserProfile,
    val portfolio: UserPortfolio,
)




data class UserPortfolio(
    val userId: Long,
    val investedAmount: Long,
    val totalAmount: Long,
    val fundName: String,
)

data class UserProfile(
    val userId: Long,
    val userName: String,
    val mobile: Long,
)

