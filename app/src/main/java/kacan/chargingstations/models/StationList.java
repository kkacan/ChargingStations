package kacan.chargingstations.models;

import java.io.Serializable;
import java.util.List;

/**
 * Klasa koja predstavlja objekt tipa StationList
 * koja implementira serijalizaciju. Sadrži listu objekta Station.
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public class StationList implements Serializable {

    private List<Station> stations;

    public StationList() {
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
