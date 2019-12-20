package kacan.chargingstations.retrofit;

import java.util.concurrent.TimeUnit;
import kacan.chargingstations.conf.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Klasa za postavljanje retrofit klijenta
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public class APIClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.OPENCHARGEMAP_API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
