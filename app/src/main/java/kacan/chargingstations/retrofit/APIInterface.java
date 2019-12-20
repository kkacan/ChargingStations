package kacan.chargingstations.retrofit;

import java.util.List;
import kacan.chargingstations.models.Station;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Sučelje za poziv API-a pomoću retrofita
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public interface APIInterface {

    @GET(".")
    Call<List<Station>> getStations(@Query("output") String output, @Query("countrycode") String countrycode,
                                    @Query("maxresults") String maxresults, @Query("compact") String compact,
                                    @Query("verbose") String verbose);

}
