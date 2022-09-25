package com.example.test.model

import com.example.test.model.entities.blog.BlogDetails
import com.example.test.retrofit.BlogDetailsApi
import com.example.test.retrofit.main.entities.blog.BlogDetailsResponseEntity
import com.example.test.utils.ThisApplicationNetworkErrors.unknownError
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class BlogDetailsRepository @Inject constructor(
    private val blogDetailsApi: BlogDetailsApi
) {

    suspend fun getBlogDetails(blogId: String): BlogDetailsResponse {
        return try {
            val blogDetails = blogDetailsApi.getBlogDetails(blogId = blogId)
            if (blogDetails.success.not()) {
                BlogDetailsResponse.Error(blogDetails.error?: unknownError)
            } else {
                BlogDetailsResponse.Success(blogDetails.mapToBlogDetails())
            }
        }catch (e:Throwable){
            return BlogDetailsResponse.Error(e.localizedMessage ?: unknownError)
        }

    }


    sealed class BlogDetailsResponse {
        data class Error(val message: String) : BlogDetailsResponse()
        data class Success(val blogDetails: BlogDetails) : BlogDetailsResponse()
    }
}