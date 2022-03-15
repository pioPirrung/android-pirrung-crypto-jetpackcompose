package de.pirrung.crypto.domain.use_case.get_coins

import de.pirrung.crypto.common.Resource
import de.pirrung.crypto.data.remote.dto.toCoin
import de.pirrung.crypto.domain.model.Coin
import de.pirrung.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCoinsUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke() : Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("Could not reach server. Check internet connection"))
        }
    }

}