package com.example.test.retrofit

import com.example.test.Const
import com.example.test.retrofit.main.entities.blog.BlogDetailsResponseEntity
import com.example.test.retrofit.main.entities.main.MainPageResponseEntity
import com.example.test.retrofit.main.views.BlogItemResponseEntity
import com.example.test.retrofit.main.views.ObjectResponseEntity
import com.example.test.retrofit.main.views.RoomsResponseEntity
import com.example.test.retrofit.main.views.ToursResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


internal interface MainPageApi {

    /**
     * Константа, используется так, как по ТЗ нет необходимости писать конфиг файл
     */
    @GET("main?id=${Const.BASE_ID}")
    suspend fun getMainPage(): MainPageResponseEntity

    @GET
    suspend fun getObjectsItems(@Url url: String): ObjectResponseEntity

    @GET
    suspend fun getRoomsItems(@Url url: String): RoomsResponseEntity

    @GET
    suspend fun getBlogItems(@Url url: String): BlogItemResponseEntity

    @GET
    suspend fun getToursItems(@Url url: String): ToursResponseEntity

}

internal interface BlogDetailsApi {
    /**
     * Константа используется так, как по ТЗ нет необходимости писать конфиг файл
     */
    @GET("blog-info?id=${Const.BASE_ID}")
    suspend fun getBlogDetails(
        @Query("blog_id") blogId: String
    ): BlogDetailsResponseEntity
}