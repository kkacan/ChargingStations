package kacan.chargingstations.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kacan.chargingstations.R;
import kacan.chargingstations.models.Station;

/**
 * Adapter za prikaz stanica u RecycleView-u
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public class StationListAdapter extends RecyclerView.Adapter<StationListAdapter.Row> {

    private List<Station> stations;
    private LayoutInflater layoutInflater;
    private ItemClickInterface itemClickInterface;
    private Context context;

    public StationListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    public Row onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(
                R.layout.list_row, parent, false);
        return new Row(view);
    }

    @Override
    public void onBindViewHolder(Row holder, int position) {
        Station station = stations.get(position);
        holder.title.setText(station.getAddressInfo().getTitle());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < station.getConnections().size(); i++) {
            if (station.getConnections().get(i).getPowerKW() != null) {
                sb.append(station.getConnections().get(i).getPowerKW() + context.getString(R.string.kw));
                if (i < station.getConnections().size() - 1) sb.append(", ");
            } else {
                sb.append(context.getString(R.string.no_data));
            }
        }
        holder.power.setText(sb.toString());
    }

    @Override
    public int getItemCount() {

        return stations == null ? 0 : stations.size();
    }

    public Station getStation(int position) {
        return stations.get(position);
    }

    public class Row extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private TextView power;

        public Row(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            power = view.findViewById(R.id.power);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickInterface != null) {
                itemClickInterface.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public void setClickListener(ItemClickInterface clickListener) {
        this.itemClickInterface = clickListener;
    }

    public interface ItemClickInterface {
        void onItemClick(View view, int position);
    }

}
