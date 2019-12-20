package kacan.chargingstations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Klasa koja predstavlja objekt tipa Country
 * koja implementira serijalizaciju. Sadrži sve parametre država i njihove getterse i setterse.
 * Kreirana alatom jsonschema2pojo (http://www.jsonschema2pojo.org/).
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public class Country implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("long_name")
    @Expose
    private String longName;
    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("center_lat")
    @Expose
    private String centerLat;
    @SerializedName("center_lng")
    @Expose
    private String centerLng;
    @SerializedName("sw_lat")
    @Expose
    private String swLat;
    @SerializedName("sw_lng")
    @Expose
    private String swLng;
    @SerializedName("ne_lat")
    @Expose
    private String neLat;
    @SerializedName("ne_lng")
    @Expose
    private String neLng;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(String centerLat) {
        this.centerLat = centerLat;
    }

    public String getCenterLng() {
        return centerLng;
    }

    public void setCenterLng(String centerLng) {
        this.centerLng = centerLng;
    }

    public String getSwLat() {
        return swLat;
    }

    public void setSwLat(String swLat) {
        this.swLat = swLat;
    }

    public String getSwLng() {
        return swLng;
    }

    public void setSwLng(String swLng) {
        this.swLng = swLng;
    }

    public String getNeLat() {
        return neLat;
    }

    public void setNeLat(String neLat) {
        this.neLat = neLat;
    }

    public String getNeLng() {
        return neLng;
    }

    public void setNeLng(String neLng) {
        this.neLng = neLng;
    }

}


