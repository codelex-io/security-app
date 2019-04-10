package io.codelex.securityapp.route;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RouteResponse {

    private final List<Rows> rows;
    
    @JsonCreator
    public RouteResponse(@JsonProperty("rows") List<Rows> rows) {
        this.rows = rows;
    }

    public List<Rows> getRows() {
        return rows;
    }

    public static class Rows {
        private final List<Elm> elements;

        public List<Elm> getElements() {
            return elements;
        }

        @JsonCreator
        public Rows(@JsonProperty("rows") List<Elm> elements) {
            this.elements = elements;
        }
    }

    public static class Elm {
        private final Dist distance;

        @JsonCreator
        public Elm(@JsonProperty("distance") Dist distance) {
            this.distance = distance;
        }

        public Dist getDistance() {
            return distance;
        }
    }

    public static class Dist {
        private final Long value;

        @JsonCreator
        public Dist(@JsonProperty("value") Long value) {
            this.value = value;
        }

        public Long getValue() {
            return value;
        }
    }
}
