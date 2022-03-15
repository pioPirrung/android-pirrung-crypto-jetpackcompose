package de.pirrung.crypto.data.repository

import de.pirrung.crypto.data.remote.CoinPaprikaApi
import de.pirrung.crypto.data.remote.dto.CoinDetailDto
import de.pirrung.crypto.data.remote.dto.CoinDto
import de.pirrung.crypto.domain.repository.CoinRepository

class CoinRepositoryImpl(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinDetails(coinId: String): CoinDetailDto {
        return api.getCoinDetails(coinId)
    }


}