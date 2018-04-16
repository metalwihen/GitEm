package com.metalwihen.sample.gitem.net;

import com.metalwihen.sample.gitem.net.user.User;
import com.metalwihen.sample.gitem.net.user_list.UserListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {

    @GET("search/users")
    Observable<UserListResponse> searchUsers(@Query("q") String query, @Query("sort") String sortBy, @Query("order") String orderBy);

    @GET("users/{username}")
    Observable<User> getUser(@Path("username") String username);

}
