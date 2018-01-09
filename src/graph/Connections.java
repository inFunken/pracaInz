package graph;

/**
 * Created by Piotr on 09.01.2018.
 */
public class Connections {

    int connectionId;

    int nodeId1;
    int cityId1;
    String cityName1;
    double height1;
    double width1;

    int nodeId2;
    int cityId2;
    String cityName2;
    double height2;
    double width2;

    public Connections(int connectionId, int nodeId1, int cityId1, String cityName1, double height1, double width1, int connectionId1, int nodeId2, int cityId2, String cityName2, double height2, double width2) {
        this.connectionId = connectionId;
        this.nodeId1 = nodeId1;
        this.cityId1 = cityId1;
        this.cityName1 = cityName1;
        this.height1 = height1;
        this.width1 = width1;
        this.nodeId2 = nodeId2;
        this.cityId2 = cityId2;
        this.cityName2 = cityName2;
        this.height2 = height2;
        this.width2 = width2;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }

    public int getNodeId1() {
        return nodeId1;
    }

    public void setNodeId1(int nodeId1) {
        this.nodeId1 = nodeId1;
    }

    public int getCityId1() {
        return cityId1;
    }

    public void setCityId1(int cityId1) {
        this.cityId1 = cityId1;
    }

    public String getCityName1() {
        return cityName1;
    }

    public void setCityName1(String cityName1) {
        this.cityName1 = cityName1;
    }

    public double getHeight1() {
        return height1;
    }

    public void setHeight1(double height1) {
        this.height1 = height1;
    }

    public double getWidth1() {
        return width1;
    }

    public void setWidth1(double width1) {
        this.width1 = width1;
    }

    public int getNodeId2() {
        return nodeId2;
    }

    public void setNodeId2(int nodeId2) {
        this.nodeId2 = nodeId2;
    }

    public int getCityId2() {
        return cityId2;
    }

    public void setCityId2(int cityId2) {
        this.cityId2 = cityId2;
    }

    public String getCityName2() {
        return cityName2;
    }

    public void setCityName2(String cityName2) {
        this.cityName2 = cityName2;
    }

    public double getHeight2() {
        return height2;
    }

    public void setHeight2(double height2) {
        this.height2 = height2;
    }

    public double getWidth2() {
        return width2;
    }

    public void setWidth2(double width2) {
        this.width2 = width2;
    }
}
