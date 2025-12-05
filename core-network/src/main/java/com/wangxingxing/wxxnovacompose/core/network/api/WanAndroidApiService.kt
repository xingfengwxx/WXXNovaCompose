package com.wangxingxing.wxxnovacompose.core.network.api

import com.wangxingxing.wxxnovacompose.core.network.BaseResponse
import com.wangxingxing.wxxnovacompose.core.models.remote.ArticlePageData
import com.wangxingxing.wxxnovacompose.core.models.remote.Banner
import com.wangxingxing.wxxnovacompose.core.models.remote.ProjectCategory
import com.wangxingxing.wxxnovacompose.core.models.remote.UserInfo
import retrofit2.http.*


/**
 * author : 王星星
 * date : 2025/11/20 19:26
 * email : 1099420259@qq.com
 * description : WanAndroid API 接口定义
 */
interface WanAndroidApiService {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录响应
     */
    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseResponse<UserInfo>
    
    /**
     * 获取项目分类
     * @return 项目分类响应
     */
    @GET("project/tree/json")
    suspend fun getProjectTree(): BaseResponse<List<ProjectCategory>>
    
    /**
     * 获取 Banner 列表
     * @return Banner 响应
     */
    @GET("banner/json")
    suspend fun getBannerList(): BaseResponse<List<Banner>>
    
    /**
     * 获取文章列表
     * @param page 页码，从0开始
     * @param pageSize 每页数量，取值范围[1-40]，不传则使用默认值
     * @return 文章列表响应
     */
    @GET("article/list/{page}/json")
    suspend fun getArticleList(
        @Path("page") page: Int,
        @Query("page_size") pageSize: Int? = null
    ): BaseResponse<ArticlePageData>
}