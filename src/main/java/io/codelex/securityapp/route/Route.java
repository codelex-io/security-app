package io.codelex.securityapp.route;

public class Route implements Comparable<Route> {
    
    private Long distance;

    public Route(Long distance) {
        this.distance = distance;
    }

    public Long getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Route o) {
        return (int)(this.distance - o.getDistance());
    }
}
