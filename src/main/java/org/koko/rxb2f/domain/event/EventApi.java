package org.koko.rxb2f.domain.event;

import org.koko.rxb2f.api.stream.ObservableStreamingOutput;
import org.koko.rxb2f.api.stream.StreamingObserver;
import org.koko.rxb2f.data.DbPopulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rx.Observable;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/rest/v1/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventApi {

    private final EventService eventService;
    private final DbPopulator dbPopulator;

    private final Logger log = LoggerFactory.getLogger(StreamingObserver.class);

    @Autowired
    public EventApi(EventService eventService, DbPopulator dbPopulator) {
        this.eventService = eventService;
        this.dbPopulator = dbPopulator;
    }

    @POST
    public Response populateDb(@QueryParam("count") @DefaultValue("1000") Integer count) {
        dbPopulator.populate(count);
        return Response.ok().build();
    }

    @GET
    public Response eventStream() {
        Observable<Event> events = eventService.selectEvents();
        ObservableStreamingOutput stream = new ObservableStreamingOutput(events);
        log.info("initiating event stream");
        return Response.ok(stream).build();
    }

}
