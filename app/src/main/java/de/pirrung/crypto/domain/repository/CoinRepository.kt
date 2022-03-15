package de.pirrung.crypto.domain.repository

import de.pirrung.crypto.data.remote.dto.CoinDetailDto
import de.pirrung.crypto.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinDetails(coinId: String): CoinDetailDto
}