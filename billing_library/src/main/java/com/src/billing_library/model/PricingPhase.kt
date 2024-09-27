package com.src.billing_library.model

import com.android.billingclient.api.ProductDetails

data class PricingPhase(
    val billingCycleCount: Int,
    val priceAmountMicros: Long,
    val billingPeriod: String,
    val formattedPrice: String,
    val recurrenceMode: Int,
    val priceCurrencyCode: String
) : PriceAble {

    companion object {
        private fun ProductDetails.PricingPhase.toPricingPhase(): PricingPhase {
            return PricingPhase(
                billingCycleCount = billingCycleCount,
                priceAmountMicros = priceAmountMicros,
                billingPeriod = billingPeriod,
                formattedPrice = formattedPrice,
                recurrenceMode = recurrenceMode,
                priceCurrencyCode = priceCurrencyCode
            )
        }

        fun ProductDetails.PricingPhases.toPricingPhases(): List<PricingPhase> {
            return this.pricingPhaseList.map { it.toPricingPhase() }
        }

    }

    override fun getReadAblePrice(): String {
        return "${priceAmountMicros / 1_000_000} $priceCurrencyCode"
    }

}