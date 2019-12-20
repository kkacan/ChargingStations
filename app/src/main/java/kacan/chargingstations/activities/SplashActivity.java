package kacan.chargingstations.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import kacan.chargingstations.R;
import kacan.chargingstations.conf.Constants;
import kacan.chargingstations.interfaces.OnFoundStationsListener;
import kacan.chargingstations.models.Country;
import kacan.chargingstations.models.CountryList;
import kacan.chargingstations.models.StationList;
import kacan.chargingstations.utils.AppUtils;

/**
 * Activity SplashActivity služi za dohvaćanje podataka iz API-a dok se aplikacija učitava.
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public class SplashActivity extends AppCompatActivity implements OnFoundStationsListener {

    private CountryList countries;

    /**
     * Metoda koja preklapa event onCreate.
     * Služi za učitavanje država i pokreće dohvat stanica iz API-a.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        AppUtils appUtils = new AppUtils(this);

        Type listType = new TypeToken<List<Country>>() {
        }.getType();
        countries = new CountryList();
        countries.setCountries((List<Country>) new Gson().fromJson(appUtils.loadJSONFromAsset(this), listType));
        appUtils.findStations(Constants.MAIN_COUNTRY, Constants.MAX_RESULTS, SplashActivity.this, false);
    }

    /**
     * Metoda koja sluša event foundStationEventOccured i poziva ListActivity kad primi podatke.
     *
     * @param stationList objekt klase StationList pomoću kojeg primamo podatke iz API-a
     */
    @Override
    public void foundStationEventOccured(StationList stationList) {

        Intent intent = new Intent(SplashActivity.this, ListActivity.class);
        intent.putExtra(Constants.STATIONS_DATA, stationList);
        intent.putExtra(Constants.COUNTRIES_DATA, countries);
        startActivity(intent);
        finish();
    }
}
