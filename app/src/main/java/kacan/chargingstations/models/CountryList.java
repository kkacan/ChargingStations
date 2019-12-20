package kacan.chargingstations.models;

import java.io.Serializable;
import java.util.List;

/**
 * Klasa koja predstavlja objekt tipa CountryList
 * koja implementira serijalizaciju. Sadrži listu objekta Country.
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public class CountryList implements Serializable {

    private List<Country> countries;

    public CountryList() {
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
