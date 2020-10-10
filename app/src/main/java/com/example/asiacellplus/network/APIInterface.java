package com.example.asiacellplus.network;

import com.example.asiacellplus.model.BannerResponce;
import com.example.asiacellplus.model.CategoryListResponce;
import com.example.asiacellplus.model.IteamListResponce;
import com.example.asiacellplus.model.MelodyListResopnce;
import com.example.asiacellplus.model.ServiceListResopnce;
import com.example.asiacellplus.model.search.SearchListResponce;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by anupamchugh on 09/01/17.
 */

public interface APIInterface {

    @POST("add-mobile")
    @FormUrlEncoded
    Call<Object> addPhone(@Field("mobile_no") String number);

    @POST("get-banner")
    @FormUrlEncoded
    Call<BannerResponce> getBanner(@Field("lang_id") String lang);

    @POST("get-category")
    @FormUrlEncoded
    Call<CategoryListResponce> getCategoryList(@Field("lang_id") String lang);


    @POST("get-top-10-melody")
    @FormUrlEncoded
    Call<MelodyListResopnce> getMelodyList(@Field("lang_id") String lang);

    @POST("get-top-10-services")
    @FormUrlEncoded
    Call<ServiceListResopnce> getServiceList(@Field("lang_id") String lang);

    @POST("search-items")
    @FormUrlEncoded
    Call<SearchListResponce> getSearchList(@Field("lang_id") String lang, @Field("text") String text);

    @POST("get-item")
    @FormUrlEncoded
    Call<IteamListResponce> getiItem(@Field("lang_id") String lang, @Field("category_id") String text);

    @POST("add-token")
    @FormUrlEncoded
    Call<Object> addToken(@Field("lang_id") String lang, @Field("device") String device,@Field("token") String token);

    @POST("delete-token")
    @FormUrlEncoded
    Call<Object> deleteToken(@Field("device") String device,@Field("token") String token);




    /*@GET("/api/unknown")
    Call<MultipleResource> doGetListResources();

    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/
}
