package kacan.chargingstations.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import kacan.chargingstations.R;
import kacan.chargingstations.models.Country;

/**
 * Adapter za prikaz naziva država u spinner-u
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public class CountryListAdapter extends ArrayAdapter<Country> {

    private final List<Country> countries;
    private final Activity context;

    private static class ViewHolder {
        TextView country_title;

    }

    public CountryListAdapter(Activity context, List<Country> countries) {
        super(context, R.layout.spinner_item, countries);
        this.context = context;
        this.countries = countries;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = context.getLayoutInflater().inflate(R.layout.spinner_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.country_title = view.findViewById(R.id.country_title);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.country_title.setText(countries.get(position).getLongName());

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = context.getLayoutInflater().inflate(R.layout.spinner_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.country_title = view.findViewById(R.id.country_title);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.country_title.setText(countries.get(position).getLongName());

        return view;
    }

    @Override
    public int getPosition(@Nullable Country item) {
        return super.getPosition(item);
    }

    public Country getCountry(int position) {
        return countries.get(position);
    }

}
