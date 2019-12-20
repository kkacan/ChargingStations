package kacan.chargingstations.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import kacan.chargingstations.R;
import kacan.chargingstations.adapters.CountryListAdapter;
import kacan.chargingstations.adapters.StationListAdapter;
import kacan.chargingstations.conf.Constants;
import kacan.chargingstations.interfaces.OnFoundStationsListener;
import kacan.chargingstations.models.Country;
import kacan.chargingstations.models.CountryList;
import kacan.chargingstations.models.Station;
import kacan.chargingstations.models.StationList;
import kacan.chargingstations.utils.AppUtils;

/**
 * Activity ListActivity služi za prikaz liste stanica za punjenje po državama.
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public class ListActivity extends AppCompatActivity implements StationListAdapter.ItemClickInterface, OnFoundStationsListener, View.OnClickListener {

    private StationListAdapter stationListAdapter;
    private StationList stations;
    private CountryList countries;
    private Country country;
    private CountryListAdapter countryListAdapter;
    private AppUtils appUtils;
    private TextView textView;

    /**
     * Metoda koja preklapa event onCreate.
     * Inicijalizira sve komponente glavnog ekrana i
     * postavlja listener na spinner.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        appUtils = new AppUtils(this);

        Intent intent = getIntent();
        stations = (StationList) intent.getSerializableExtra(Constants.STATIONS_DATA);
        countries = (CountryList) intent.getSerializableExtra(Constants.COUNTRIES_DATA);
        country = countries.getCountries().get(appUtils.getCountryIndex(countries, Constants.MAIN_COUNTRY_LONG));

        textView = findViewById(R.id.no_data);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dv = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dv.setDrawable(getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(dv);
        stationListAdapter = new StationListAdapter(this);
        stationListAdapter.setStations(stations.getStations());
        stationListAdapter.notifyDataSetChanged();
        stationListAdapter.setClickListener(this);
        recyclerView.setAdapter(stationListAdapter);

        final Spinner spinner = findViewById(R.id.spinner1);
        countryListAdapter = new CountryListAdapter(this, countries.getCountries());
        spinner.setAdapter(countryListAdapter);
        spinner.setSelection(appUtils.getCountryIndex(countries, Constants.MAIN_COUNTRY_LONG));

        spinner.post(new Runnable() {
            public void run() {
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        country = countryListAdapter.getCountry(position);
                        appUtils.findStations(country.getShortName(), Constants.MAX_RESULTS, ListActivity.this, true);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Station station = stationListAdapter.getStation(position);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(Constants.STATION_DATA, station);
        startActivity(intent);
    }

    /**
     * Metoda koja sluša event foundStationEventOccured.
     *
     * @param stationList objekt klase StationList pomoću kojeg primamo podatke iz API-a
     */
    @Override
    public void foundStationEventOccured(StationList stationList) {
        if (stationList != null) {
            stations.setStations(stationList.getStations());
            stationListAdapter.setStations(stationList.getStations());
            stationListAdapter.notifyDataSetChanged();
            if (stationList.getStations().isEmpty()) {
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Metoda koja preklapa event onClick.
     * Sluša event klika na gumb
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button) {
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra(Constants.STATIONS_DATA, stations);
            intent.putExtra(Constants.COUNTRIES_DATA, countries);
            intent.putExtra(Constants.COUNTRY_DATA, country.getLongName());
            startActivity(intent);
        }
    }

}
