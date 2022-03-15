package de.pirrung.crypto.di

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import de.pirrung.crypto.common.Constants
import de.pirrung.crypto.data.remote.CoinPaprikaApi
import de.pirrung.crypto.data.repository.CoinRepositoryImpl
import de.pirrung.crypto.domain.repository.CoinRepository
import de.pirrung.crypto.domain.use_case.get_coin_detail.GetCoinDetailsUseCase
import de.pirrung.crypto.domain.use_case.get_coins.GetCoinsUseCase
import de.pirrung.crypto.presentation.coin_detail.CoinDetailViewModel
import de.pirrung.crypto.presentation.coin_list.CoinListViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<CoinPaprikaApi> { providePaprikaApi() }

    single<CoinRepository> { CoinRepositoryImpl(get()) }

    single<GetCoinsUseCase> { GetCoinsUseCase(get()) }

    single<GetCoinDetailsUseCase> { GetCoinDetailsUseCase(get()) }

    viewModel {
        CoinListViewModel(get())
    }

    viewModel { (bundle: Bundle) ->
        CoinDetailViewModel(get(), bundle)
    }

}

fun providePaprikaApi(): CoinPaprikaApi {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()
        .create(CoinPaprikaApi::class.java)
}