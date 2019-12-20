package kacan.chargingstations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Klasa koja predstavlja objekt tipa AddressInfo
 * koja implementira serijalizaciju. Sadrži sve parametre adrese stanice i njihove getterse i setterse.
 * Kreirana alatom jsonschema2pojo (http://www.jsonschema2pojo.org/).
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public class AddressInfo implements Serializable {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("AddressLine1")
    @Expose
    private String addressLine1;
    @SerializedName("Town")
    @Expose
    private String town;
    @SerializedName("StateOrProvince")
    @Expose
    private String stateOrProvince;
    @SerializedName("Postcode")
    @Expose
    private String postcode;
    @SerializedName("CountryID")
    @Expose
    private Integer countryID;
    @SerializedName("Latitude")
    @Expose
    private Float latitude;
    @SerializedName("Longitude")
    @Expose
    private Float longitude;
    @SerializedName("DistanceUnit")
    @Expose
    private Integer distanceUnit;

    public AddressInfo() {
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Integer getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(Integer distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

}