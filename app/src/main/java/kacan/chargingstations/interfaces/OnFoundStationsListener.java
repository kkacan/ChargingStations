package kacan.chargingstations.interfaces;

import kacan.chargingstations.models.StationList;

/**
 * Sučelje koje služi za prihvat podataka nakon učitavanja sa interneta
 */
public interface OnFoundStationsListener {

    void foundStationEventOccured(StationList stationList);

}
