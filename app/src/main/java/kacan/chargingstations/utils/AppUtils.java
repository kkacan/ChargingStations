package kacan.chargingstations.utils;

import android.app.ProgressDialog;
import android.content.Context;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import kacan.chargingstations.R;
import kacan.chargingstations.interfaces.OnFoundStationsListener;
import kacan.chargingstations.models.CountryList;
import kacan.chargingstations.models.Station;
import kacan.chargingstations.models.StationList;
import kacan.chargingstations.retrofit.APIClient;
import kacan.chargingstations.retrofit.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Klasa AppUtils sadrži korisne metode koje se koriste više puta.
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public class AppUtils {

    private StationList stations;
    private OnFoundStationsListener listener;
    private ProgressDialog mProgressDialog;

    /**
     * Konsruktor
     *
     * @param listener Listener za dohvat podataka nakon pretrage
     */
    public AppUtils(OnFoundStationsListener listener) {
        this.listener = listener;
    }

    /**
     * Metoda za dohvaćanje indeksa države prema nazivu.
     *
     * @param countries    Lista država
     * @param countryTitle Naziv države
     * @return Indeks države
     */
    public int getCountryIndex(CountryList countries, String countryTitle) {

        int index = 0;

        for (int i = 0; i < countries.getCountries().size(); i++) {
            if (countries.getCountries().get(i).getLongName().equalsIgnoreCase(countryTitle)) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Metoda za dohvaćanje stanica prema državi.
     *
     * @param countryShortName Skraćeni naziv države
     * @param maxResults       Maksimalan broj rezultata koji želimo da nam vrati API
     * @param context          Kontekst
     * @param showDialog       Prikaži dialog učitavanja
     */
    public void findStations(String countryShortName, String maxResults, Context context, boolean showDialog) {

        if (showDialog) showProgressDialog(context);

        try {
            Retrofit client = APIClient.getClient();
            if (client != null) {
                APIInterface apiInterface = client.create(APIInterface.class);
                Call<List<Station>> stationResponse =
                        apiInterface.getStations("json", countryShortName, maxResults, "true", "false");
                stationResponse.enqueue(new Callback<List<Station>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Station>> call, @NonNull Response<List<Station>> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                stations = new StationList();
                                stations.setStations(response.body());
                                if (listener != null) listener.foundStationEventOccured(stations);
                            } else {
                                if (listener != null) listener.foundStationEventOccured(null);
                            }
                        } else {
                            if (listener != null) listener.foundStationEventOccured(null);
                        }
                        hideProgressDialog();
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Station>> call, @NonNull Throwable t) {
                        if (listener != null) listener.foundStationEventOccured(null);
                        hideProgressDialog();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (listener != null) listener.foundStationEventOccured(null);
        }
    }

    /**
     * Preuzeto sa https://www.codota.com/web/assistant/code/rs/5c7caebbac38dc0001e3f931#L77
     *
     * @param context Kontekst
     * @return json Vraća string vrijednost iz datoteke u json formatu
     */
    public String loadJSONFromAsset(Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open("json/countries.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * Metoda za prikaz dialoga učitavanja.
     *
     * @param context Kontekst
     */
    private void showProgressDialog(Context context) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage(context.getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    /**
     * Metoda za skrivanje dialoga učitavanja.
     */
    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


}
