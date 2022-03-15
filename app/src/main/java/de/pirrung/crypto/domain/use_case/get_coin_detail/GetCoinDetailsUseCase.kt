package de.pirrung.crypto.domain.use_case.get_coin_detail

import de.pirrung.crypto.common.Resource
import de.pirrung.crypto.data.remote.dto.toCoin
import de.pirrung.crypto.data.remote.dto.toCoinDetail
import de.pirrung.crypto.domain.model.Coin
import de.pirrung.crypto.domain.model.CoinDetail
import de.pirrung.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCoinDetailsUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String) : Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinDetails(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("Could not reach server. Check internet connection"))
        }
    }

}