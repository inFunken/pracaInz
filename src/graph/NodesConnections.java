package graph;

/**
 * Created by Piotr on 07.02.2018.
 */
public class NodesConnections {
    int nodeId;
    int amount;
    int cityId;
    String cityName;
    double geoWidth;
    double geoHeight;
    int ranking;

    public NodesConnections(int nodeId, int amount, int cityId, String cityName, double geoWidth, double geoHeight, int ranking) {
        this.nodeId = nodeId;
        this.amount = amount;
        this.cityId = cityId;
        this.cityName = cityName;
        this.geoWidth = geoWidth;
        this.geoHeight = geoHeight;
        this.ranking = ranking;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getGeoWidth() {
        return geoWidth;
    }

    public void setGeoWidth(double geoWidth) {
        this.geoWidth = geoWidth;
    }

    public double getGeoHeight() {
        return geoHeight;
    }

    public void setGeoHeight(double geoHeight) {
        this.geoHeight = geoHeight;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
