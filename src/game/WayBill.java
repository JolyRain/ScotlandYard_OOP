package game;

import graph.TypeRoad;
import graph.Vertex;

import java.util.Map;

public class WayBill {

    private Map<Vertex, Ticket> stepsMap;
    private Integer stepsToShow;

    public WayBill(Map<Vertex, Ticket> misterXStepsMap) {
        this.stepsMap = misterXStepsMap;
    }

    public Map<Vertex, Ticket> getStepsMap() {
        return stepsMap;
    }

    public void setStepsMap(Map<Vertex, Ticket> stepsMap) {
        this.stepsMap = stepsMap;
    }

    public Integer getStepsToShow() {
        return stepsToShow;
    }

    public void setStepsToShow(Integer stepsToShow) {
        this.stepsToShow = stepsToShow;
    }
}
