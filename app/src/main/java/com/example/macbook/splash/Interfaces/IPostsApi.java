package com.example.macbook.splash.Interfaces;

import com.example.macbook.splash.Models.Child;
import com.example.macbook.splash.Models.ChildAdoptionRequest;
import com.example.macbook.splash.Models.ChildAdoptionRequestSubmitViewModel;
import com.example.macbook.splash.Models.ChildAdoptionResponse;
import com.example.macbook.splash.Models.ChildInscriptionRequest;
import com.example.macbook.splash.Models.ChildInscriptionRequestSubmitViewModel;
import com.example.macbook.splash.Models.ChildInscriptionResponse;
import com.example.macbook.splash.Models.Post;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

/**
 * Created by Master on 21/01/2018.
 */

public interface IPostsApi {

    //region PostsApi

    @GET("api/Posts")
    Call<List<Post>> getPosts();

    @GET("/api/Posts/{postId}")
    Call<Post> getPost(@Path("postId") int postId);

    @POST("/api/Posts")
    Call<Post> postPost(@Body Post post);

    @PUT("/api/Posts/{postId}")
    Call<Void> putPost(@Path("postId") int postId, @Body Post post);

    @DELETE("/api/Posts/{postId}")
    Call<Void> deletePost(@Path("postId") int postId);

    //endregion

    //region PostChildrenApi

    @GET("api/Posts/{postId}/Children")
    Call<List<Child>> getPostChildren(@Path("postId") int postId);

    @GET("/api/Posts/{postId}/Children/{childId}")
    Call<Child> getPostChild(@Path("postId") int postId, @Path("childId") int childId);

    @POST("/api/Posts/{postId}/Children/{childId}")
    Call<Void> linkChildToPost(@Path("postId") int postId, @Path("childId") int childId);

    @DELETE("/api/Posts/{postId}/Children/{childId}")
    Call<Void> unlinkChildAndPost(@Path("postId") int postId, @Path("childId") int childId);


    //endregion

    //region PostMediaApi

    @Streaming
    @GET("api/Posts/{postId}/Shots/{shotId}")
    Call<ResponseBody> GetPostShot();

    @Streaming
    @GET("api/Posts/{postId}/Videos/{videoId}")
    Call<ResponseBody> GetPostVideo();

    @Multipart
    @POST("api/Posts/{postId}/Shots")
    Call<Integer> AddPostShot(
            @Path("postId") int postId,
            @Part MultipartBody.Part file
    );

    @Multipart
    @POST("api/Posts/{postId}/Videos")
    Call<Integer> AddPostVideo(
            @Path("postId") int postId,
            @Part MultipartBody.Part file
    );

    //endregion

    //region ChildInscriptionRequest

    @GET("api/Parents/{parentId}/ChildInscriptionResponses")
    Call<ChildInscriptionResponse>  getChildInscriptionResponses(
            @Path("parentId") int parentId
    );

    @GET("api/Parents/{parentId}/ChildAdoptionResponses")
    Call<ChildAdoptionResponse> getChildAdoptionResponses(
            @Path("parentId") int parentId
    );

    @POST("api/Parents/{parentId}/ChildInscriptionRequests")
    Call<ChildInscriptionRequest> postChildInscriptionRequest(
            @Path("parentId") int parentId,
            @Body ChildInscriptionRequestSubmitViewModel viewModel
    );

    @POST("api/Parents/{parentId}/ChildAdoptionRequests")
    Call<ChildAdoptionRequest> postChildAdoptionRequest(
            @Path("parentId") int parentId,
            @Body ChildAdoptionRequestSubmitViewModel viewModel
    );

    //endregion
}
