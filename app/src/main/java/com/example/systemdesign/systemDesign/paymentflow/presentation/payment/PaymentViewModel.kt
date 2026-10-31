package com.example.systemdesign.systemDesign.paymentflow.presentation.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.systemdesign.systemDesign.paymentflow.data.local.ApiState
import com.example.systemdesign.systemDesign.paymentflow.data.local.cart.CartDao
import com.example.systemdesign.systemDesign.paymentflow.data.remote.OrderRequestData
import com.example.systemdesign.systemDesign.paymentflow.data.remote.dto.OrderResponse
import com.example.systemdesign.systemDesign.paymentflow.domain.repository.PaymentRepository
import com.razorpay.PaymentData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PaymentViewModel @Inject constructor(val paymentRepository: PaymentRepository,val cartDao: CartDao): ViewModel() {

    val _order = MutableStateFlow<ApiState<OrderResponse>>(ApiState.Loading)
    val  order = _order.asStateFlow()

    val _paymentCheckout = MutableSharedFlow<OrderResponse?>()
    val paymentCheckout = _paymentCheckout.asSharedFlow()

    var orderRequest: MutableList<OrderRequestData>
   init {
       val order = cartDao.getOrderCart()
       orderRequest = mutableListOf()
       order.map {
           orderRequest.add(OrderRequestData(it.productId,it.quantity))
       }
       handlePayment()
   }




   fun  handlePayment(){
       viewModelScope.launch {
           _order.value = ApiState.Loading
           try {
               val cartItems = cartDao.getOrderCart()
               val orderRequest = cartItems.map {
                   OrderRequestData(
                       productId = it.productId,
                       quantity = it.quantity
                   )
               }

               val result =   paymentRepository.createOrder(orderRequest)
               _paymentCheckout.emit(result)
                paymentRepository.insertOrder(result)
                _order.value = ApiState.Success(result)
           }catch (e: Exception){
               _order.value = ApiState.Error("Something went wrong")
           }


           }
       }

    fun onPaymentSuccess(data:PaymentData?){

    }

    fun onPaymentFailed(dao: PaymentData?){

    }

   }






