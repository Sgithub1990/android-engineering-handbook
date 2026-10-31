package com.example.systemdesign.systemDesign.paymentflow.domain.useCase

sealed class PaymentUseCase{
    data class Success(val paymentSuccessUseCase: PaymentSuccessUseCase): PaymentUseCase()
    data class Pending(val paymentPendingUseCase: PaymentPendingUseCase): PaymentUseCase()
    data class Failed(val paymentFailedUseCase: PaymentFailedUseCase): PaymentUseCase()
}
