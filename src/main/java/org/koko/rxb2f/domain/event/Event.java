package org.koko.rxb2f.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.koko.rxb2f.external.event.EventLocation;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {

    private final String code;
    private final String title;
    private final String date;
    private final EventLocation location;

    @JsonCreator
    public Event(@JsonProperty("code") String code,
                 @JsonProperty("title") String title,
                 @JsonProperty("date") String date,
                 @JsonProperty("location") EventLocation location) {
        this.code = code;
        this.title = title;
        this.date = date;
        this.location = location;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public EventLocation getLocation() {
        return location;
    }
}
