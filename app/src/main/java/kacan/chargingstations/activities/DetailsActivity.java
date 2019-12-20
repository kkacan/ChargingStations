package kacan.chargingstations.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import kacan.chargingstations.R;
import kacan.chargingstations.conf.Constants;
import kacan.chargingstations.models.Station;

/**
 * Activity DetailsActivity služi za prikaz detalja električne punionice.
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public class DetailsActivity extends AppCompatActivity {

    /**
     * Metoda koja preklapa event onCreate.
     * Inicijalizira sve komponente ekrana i
     * postavlja podatke za prikaz.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        Station station = (Station) intent.getSerializableExtra(Constants.STATION_DATA);

        TextView title = findViewById(R.id.title);
        TextView address = findViewById(R.id.address);
        TextView town = findViewById(R.id.town);
        TextView connections = findViewById(R.id.connections);
        TextView power = findViewById(R.id.power);
        TextView usage = findViewById(R.id.usage_cost);
        TextView comment = findViewById(R.id.general_comments);

        title.setText(station.getAddressInfo().getTitle());
        address.setText(station.getAddressInfo().getAddressLine1());
        town.setText(station.getAddressInfo().getTown());
        connections.setText(String.valueOf(station.getConnections().size()));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < station.getConnections().size(); i++) {
            if (station.getConnections().get(i).getPowerKW() != null) {
                sb.append(station.getConnections().get(i).getPowerKW() + getString(R.string.kw));
                if (i < station.getConnections().size() - 1) sb.append(", ");
            } else {
                sb.append(getString(R.string.no_data));
            }
        }
        power.setText(sb.toString());
        usage.setText(station.getUsageCost());
        comment.setText(station.getGeneralComments());
    }
}
