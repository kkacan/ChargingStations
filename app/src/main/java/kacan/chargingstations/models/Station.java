package kacan.chargingstations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Klasa koja predstavlja objekt tipa Station
 * koja implementira serijalizaciju. Sadrži sve parametre stanice i njihove getterse i setterse.
 * Kreirana alatom jsonschema2pojo (http://www.jsonschema2pojo.org/).
 *
 * @author Kristijan Kačan
 * @since prosinac, 2019.
 */
public class Station implements Serializable {

    @SerializedName("IsRecentlyVerified")
    @Expose
    private Boolean isRecentlyVerified;
    @SerializedName("DateLastVerified")
    @Expose
    private String dateLastVerified;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("UUID")
    @Expose
    private String uUID;
    @SerializedName("DataProviderID")
    @Expose
    private Integer dataProviderID;
    @SerializedName("OperatorID")
    @Expose
    private Integer operatorID;
    @SerializedName("UsageTypeID")
    @Expose
    private Integer usageTypeID;
    @SerializedName("UsageCost")
    @Expose
    private String usageCost;
    @SerializedName("AddressInfo")
    @Expose
    private AddressInfo addressInfo;
    @SerializedName("Connections")
    @Expose
    private List<Connection> connections = null;
    @SerializedName("NumberOfPoints")
    @Expose
    private Integer numberOfPoints;
    @SerializedName("GeneralComments")
    @Expose
    private String generalComments;
    @SerializedName("StatusTypeID")
    @Expose
    private Integer statusTypeID;
    @SerializedName("DateLastStatusUpdate")
    @Expose
    private String dateLastStatusUpdate;
    @SerializedName("DataQualityLevel")
    @Expose
    private Integer dataQualityLevel;
    @SerializedName("DateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("SubmissionStatusTypeID")
    @Expose
    private Integer submissionStatusTypeID;

    public Station() {
    }

    public Boolean getIsRecentlyVerified() {
        return isRecentlyVerified;
    }

    public void setIsRecentlyVerified(Boolean isRecentlyVerified) {
        this.isRecentlyVerified = isRecentlyVerified;
    }

    public String getDateLastVerified() {
        return dateLastVerified;
    }

    public void setDateLastVerified(String dateLastVerified) {
        this.dateLastVerified = dateLastVerified;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getUUID() {
        return uUID;
    }

    public void setUUID(String uUID) {
        this.uUID = uUID;
    }

    public Integer getDataProviderID() {
        return dataProviderID;
    }

    public void setDataProviderID(Integer dataProviderID) {
        this.dataProviderID = dataProviderID;
    }

    public Integer getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
    }

    public Integer getUsageTypeID() {
        return usageTypeID;
    }

    public void setUsageTypeID(Integer usageTypeID) {
        this.usageTypeID = usageTypeID;
    }

    public String getUsageCost() {
        return usageCost;
    }

    public void setUsageCost(String usageCost) {
        this.usageCost = usageCost;
    }

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public Integer getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Integer numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public String getGeneralComments() {
        return generalComments;
    }

    public void setGeneralComments(String generalComments) {
        this.generalComments = generalComments;
    }

    public Integer getStatusTypeID() {
        return statusTypeID;
    }

    public void setStatusTypeID(Integer statusTypeID) {
        this.statusTypeID = statusTypeID;
    }

    public String getDateLastStatusUpdate() {
        return dateLastStatusUpdate;
    }

    public void setDateLastStatusUpdate(String dateLastStatusUpdate) {
        this.dateLastStatusUpdate = dateLastStatusUpdate;
    }

    public Integer getDataQualityLevel() {
        return dataQualityLevel;
    }

    public void setDataQualityLevel(Integer dataQualityLevel) {
        this.dataQualityLevel = dataQualityLevel;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getSubmissionStatusTypeID() {
        return submissionStatusTypeID;
    }

    public void setSubmissionStatusTypeID(Integer submissionStatusTypeID) {
        this.submissionStatusTypeID = submissionStatusTypeID;
    }

}