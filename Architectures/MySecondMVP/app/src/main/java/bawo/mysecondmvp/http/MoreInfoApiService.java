package bawo.mysecondmvp.http;

import bawo.mysecondmvp.http.apimodel.TopRated;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MoreInfoApiService {
    @GET("top_rated")
    Observable<TopRated> getTopRatedMovies(@Query("page")  Integer Page);
}
