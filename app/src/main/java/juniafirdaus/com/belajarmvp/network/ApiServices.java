package juniafirdaus.com.belajarmvp.network;

import juniafirdaus.com.belajarmvp.BuildConfig;
import juniafirdaus.com.belajarmvp.models.NowResponse;
import juniafirdaus.com.belajarmvp.modelsearch.SearchResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiServices {
    @GET("search/movie?api_key=" + BuildConfig.TOKEN + BuildConfig.QUERY)
    Observable<SearchResponse> getTopBooks(@Query("query") String movie);

    @GET(BuildConfig.MOVIE + "now_playing?api_key=" + BuildConfig.TOKEN)
    Observable<NowResponse> getNowPlaying();
}