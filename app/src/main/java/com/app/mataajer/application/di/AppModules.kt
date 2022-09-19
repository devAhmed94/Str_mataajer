package com.app.mataajer.application.di

import android.app.Application
import androidx.room.Room
import com.app.mataajer.BuildConfig
import com.app.mataajer.application.app.DATA_BASE_NAME
import com.app.mataajer.application.preferences.fcmToken
import com.app.mataajer.application.urls.BASE_URL
import com.app.mataajer.application.utils.LocaleHelper
import com.app.mataajer.data.repo.localRepo.LocalRepo
import com.app.mataajer.data.repo.localRepo.LocalRepoImp
import com.app.mataajer.data.db.database.MyDatabase
import com.app.mataajer.data.network.ApiService
import com.app.mataajer.data.repo.remoteRepo.RemoteRepo
import com.app.mataajer.data.repo.remoteRepo.RemoteRepoImp
import com.app.mataajer.data.repo.Repository
import com.app.mataajer.data.repo.RepositoryImp
import com.app.mataajer.presentation.home.HomeViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 18/09/2022
 */
val viewModelModule = module {
    viewModel { HomeViewModel(repo = get()) }
}

val repositoryModule = module {
    single<RemoteRepo> { RemoteRepoImp(api = get()) }
    single<LocalRepo> { LocalRepoImp(appDatabase = get()) }
    single<Repository> { RepositoryImp(localRep = get(), remoteRepo = get()) }
}

val serviceApiModule = module {
    fun headerInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
                .header("Content-Type", "application/json")
                .header("Platform", "android")
                .header("FbToken", fcmToken)
                .header("Lang", LocaleHelper.locale)
                .header("Authorization", /*user?.authorization ?:*/ "")
                .build()

            it.proceed(request)
        }
    }

    fun loggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    fun getRetrofit(): Retrofit {
        val httpClient by lazy {
            OkHttpClient.Builder()
                .addInterceptor(headerInterceptor())
                .addInterceptor(loggingInterceptor())
                .build()
        }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    single { getRetrofit() }

    fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    single { getApiService(retrofit = get()) }
}

val databaseModule = module {

    fun buildRoomDB(app: Application): MyDatabase {
        return Room.databaseBuilder(
            app,
            MyDatabase::class.java,
            DATA_BASE_NAME
        ).build()
    }

    single { buildRoomDB(androidApplication()) }
}