package kacan.chargingstations.activities;

import androidx.fragment.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import kacan.chargingstations.R;
import kacan.chargingstations.adapters.CountryListAdapter;
import kacan.chargingstations.conf.Constants;
import kacan.chargingstations.interfaces.OnFoundStationsListener;
import kacan.chargingstations.models.Country;
import kacan.chargingstations.models.CountryList;
import kacan.chargingstations.models.Station;
import kacan.chargingstations.models.StationList;
import kacan.chargingstations.utils.AppUtils;

/**
 * Activity MapsActivity služi za prikaz stanica za punjenje po državama na Google mapi.
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, OnFoundStationsListener {

    private GoogleMap mMap;
    private CameraUpdate cu;
    private StationList stations;
    private CountryList countries;
    private SupportMapFragment mapFragment;
    private Country country;
    private CountryListAdapter countryListAdapter;
    private AppUtils appUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        appUtils = new AppUtils(this);

        Intent intent = getIntent();
        stations = (StationList) intent.getSerializableExtra(Constants.STATIONS_DATA);
        countries = (CountryList) intent.getSerializableExtra(Constants.COUNTRIES_DATA);
        String countryName = intent.getStringExtra(Constants.COUNTRY_DATA);
        country = countries.getCountries().get(appUtils.getCountryIndex(countries, countryName));

        if (countries != null) {
            final Spinner spinner = findViewById(R.id.spinner1);
            countryListAdapter = new CountryListAdapter(this, countries.getCountries());
            spinner.setAdapter(countryListAdapter);
            spinner.setSelection(appUtils.getCountryIndex(countries, countryName));
            spinner.post(new Runnable() {
                public void run() {
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            country = countryListAdapter.getCountry(position);
                            appUtils.findStations(country.getShortName(), Constants.MAX_RESULTS, MapsActivity.this, true);
                        }

                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            });
        }

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(MapsActivity.this);
        }
    }

    /**
     * Metoda koja služi za uređenje mape kad je ona dostupna.
     * Pokreće se kad je mapa spremna za korištenje.
     * Zoom na državu koja je odabrana i
     * dodavanje markera punionica.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        mMap.setOnInfoWindowClickListener(this);

        double swLat = 0;
        double swLng = 0;
        double neLat = 0;
        double neLng = 0;

        if (countries != null & country != null) {
            for (Country co : countries.getCountries()) {
                if (co.getShortName().equalsIgnoreCase(country.getShortName())) {
                    swLat = Double.parseDouble(co.getSwLat());
                    swLng = Double.parseDouble(co.getSwLng());
                    neLat = Double.parseDouble(co.getNeLat());
                    neLng = Double.parseDouble(co.getNeLng());
                }
            }


            // zoom
            LatLng pos = new LatLng(swLat, swLng);
            LatLng pos1 = new LatLng(neLat, neLng);
            LatLngBounds.Builder b = new LatLngBounds.Builder();
            b.include(pos);
            b.include(pos1);
            cu = CameraUpdateFactory.newLatLngBounds(b.build(), 20);
            mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {

                @Override
                public void onMapLoaded() {
                    mMap.moveCamera(cu);
                }
            });
        }

        if (stations != null) {
            for (Station st : stations.getStations()) {
                LatLng stationPosition = new LatLng(st.getAddressInfo().getLatitude(), st.getAddressInfo().getLongitude());
                mMap.addMarker(new MarkerOptions()
                        .position(stationPosition)
                        .title(st.getAddressInfo().getTitle())
                        .snippet(st.getAddressInfo().getTown() + ", " + st.getAddressInfo().getAddressLine1()));
            }
        }
    }

    /**
     * Metoda koja služi za prikaz detalja kad kliknemo na info prozor.
     */
    @Override
    public void onInfoWindowClick(Marker marker) {
        double lat = marker.getPosition().latitude;
        double lng = marker.getPosition().longitude;
        Station station = null;

        for (Station st : stations.getStations()) {
            if (st.getAddressInfo().getLongitude() == lng & st.getAddressInfo().getLatitude() == lat)
                station = st;
        }

        Intent intent = new Intent(this, DetailsActivity.class);
        if (station != null) intent.putExtra(Constants.STATION_DATA, station);
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
            if (mapFragment != null) {
                mapFragment.getMapAsync(MapsActivity.this);
            }
        }
    }

}
