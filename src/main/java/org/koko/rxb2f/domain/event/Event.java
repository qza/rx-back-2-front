package org.koko.rxb2f.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {

    private final String code;
    private final String title;
    private final String date;

    @JsonCreator
    public Event(@JsonProperty("code") String code,
                 @JsonProperty("title") String title,
                 @JsonProperty("date") String date) {
        this.code = code;
        this.title = title;
        this.date = date;
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
}
