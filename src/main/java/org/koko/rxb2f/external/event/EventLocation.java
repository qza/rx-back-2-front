package org.koko.rxb2f.external.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventLocation {

    private final String place;
    private final Double[] coordinates;

    @JsonCreator
    EventLocation(@JsonProperty("place") String place,
                  @JsonProperty("coordinates") Double[] coordinates) {
        this.place = place;
        this.coordinates = coordinates;
    }

    public String getPlace() {
        return place;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }
}
