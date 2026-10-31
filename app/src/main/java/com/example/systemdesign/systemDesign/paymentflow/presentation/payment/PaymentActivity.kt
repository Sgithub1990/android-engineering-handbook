package com.example.systemdesign.systemDesign.paymentflow.presentation.payment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

@AndroidEntryPoint
class PaymentActivity: ComponentActivity(), PaymentResultWithDataListener {

    private val viewModel: PaymentViewModel by viewModels()
    lateinit var checkout: Checkout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**preload Razorpay resource*/
        Checkout.preload(applicationContext)

        /**init checkout*/
        checkout = Checkout()

        /**set up public key */
        checkout.setPublicKey("")

        setContent {
             LaunchedEffect(Unit) {
                 viewModel.paymentCheckout.collect {
                   if(it!=null){
                       val jsonObject = JSONObject().apply {
                           put("order_id", it.orderId)
                           put("amount", it.totalAmount)
                           put("currency", it.currency)
                           put("name", "My Shopping App")
                       }
                       checkout.open(this@PaymentActivity,jsonObject)
                   }
                 }
             }

            PaymentScreen(viewModel)
        }

    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        viewModel.onPaymentSuccess(p1)


    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        viewModel.onPaymentFailed(p2)


    }
}