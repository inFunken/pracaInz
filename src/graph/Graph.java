package graph;

import java.sql.Date;

public class Graph {
    private int graphId;
    private int nodeAmount;
    private int connectionAmount;
    private Date createDate;

    public Graph() {
        this.graphId = 0;
        this.nodeAmount = 0;
        this.connectionAmount = 0;
        this.createDate = null;
    }

    public Graph(int graphId, int nodeAmount, int connectionAmount, Date createDate) {
        this.graphId = graphId;
        this.nodeAmount = nodeAmount;
        this.connectionAmount = connectionAmount;
        this.createDate = createDate;
    }

    public int getGraphId() {
        return graphId;
    }

    public void setGraphId(int graphId) {
        this.graphId = graphId;
    }

    public int getNodeAmount() {
        return nodeAmount;
    }

    public void setNodeAmount(int nodeAmount) {
        this.nodeAmount = nodeAmount;
    }

    public int getConnectionAmount() {
        return connectionAmount;
    }

    public void setConnectionAmount(int connectionAmount) {
        this.connectionAmount = connectionAmount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
