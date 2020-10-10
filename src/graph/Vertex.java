package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Vertex {
    private String stationNumber;
    private Map<TypeRoad, List<Edge>> roadMap;

    public Vertex() {
        roadMap = new HashMap<>();
    }

    public Vertex(String stationNumber) {
        this.stationNumber = stationNumber;
        roadMap = new HashMap<>();
    }

    public Map<TypeRoad, List<Edge>> getRoadMap() {
        return roadMap;
    }

    public void setRoadMap(Map<TypeRoad, List<Edge>> roadMap) {
        this.roadMap = roadMap;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Vertex vertex = (Vertex) object;
        return stationNumber.equals(vertex.stationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationNumber);
    }

    @Override
    public String toString() {
        return "{" + stationNumber + "}";
    }
}
