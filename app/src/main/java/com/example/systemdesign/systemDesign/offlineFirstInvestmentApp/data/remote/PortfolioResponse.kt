package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.remote

import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local.HoldingEntity
import com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local.PortfolioEntity

data class PortfolioResponse(
    val portfolio: PortfolioEntity?,
    val holdings: List<HoldingEntity>?
)
