package de.pirrung.crypto.presentation.coin_detail

import de.pirrung.crypto.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)