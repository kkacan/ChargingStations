package kacan.chargingstations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Klasa koja predstavlja objekt tipa Connection
 * koja implementira serijalizaciju. Sadrži sve parametre priključka stanice i njihove getterse i setterse.
 * Kreirana alatom jsonschema2pojo (http://www.jsonschema2pojo.org/).
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public class Connection implements Serializable {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("ConnectionTypeID")
    @Expose
    private Integer connectionTypeID;
    @SerializedName("StatusTypeID")
    @Expose
    private Integer statusTypeID;
    @SerializedName("LevelID")
    @Expose
    private Integer levelID;
    @SerializedName("Amps")
    @Expose
    private Integer amps;
    @SerializedName("Voltage")
    @Expose
    private Integer voltage;
    @SerializedName("PowerKW")
    @Expose
    private Double powerKW;
    @SerializedName("CurrentTypeID")
    @Expose
    private Integer currentTypeID;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;

    public Connection() {
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Integer getConnectionTypeID() {
        return connectionTypeID;
    }

    public void setConnectionTypeID(Integer connectionTypeID) {
        this.connectionTypeID = connectionTypeID;
    }

    public Integer getStatusTypeID() {
        return statusTypeID;
    }

    public void setStatusTypeID(Integer statusTypeID) {
        this.statusTypeID = statusTypeID;
    }

    public Integer getLevelID() {
        return levelID;
    }

    public void setLevelID(Integer levelID) {
        this.levelID = levelID;
    }

    public Integer getAmps() {
        return amps;
    }

    public void setAmps(Integer amps) {
        this.amps = amps;
    }

    public Integer getVoltage() {
        return voltage;
    }

    public void setVoltage(Integer voltage) {
        this.voltage = voltage;
    }

    public Double getPowerKW() {
        return powerKW;
    }

    public void setPowerKW(Double powerKW) {
        this.powerKW = powerKW;
    }

    public Integer getCurrentTypeID() {
        return currentTypeID;
    }

    public void setCurrentTypeID(Integer currentTypeID) {
        this.currentTypeID = currentTypeID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}