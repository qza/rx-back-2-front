package org.koko.rxb2f.api;

import org.koko.rxb2f.api.stream.ObservableStreamingOutput;
import org.koko.rxb2f.api.stream.StreamingObserver;
import org.koko.rxb2f.data.DbAccess;
import org.koko.rxb2f.data.DbPopulator;
import org.koko.rxb2f.data.Event;

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
public class RestApi {

    final DbAccess dbAccess;
    final DbPopulator dbPopulator;

    final Logger log = LoggerFactory.getLogger(StreamingObserver.class);

    @Autowired
    public RestApi(DbAccess dbAccess, DbPopulator dbPopulator) {
        this.dbAccess = dbAccess;
        this.dbPopulator = dbPopulator;
    }

    @POST
    public Response populateDb(@QueryParam("count") @DefaultValue("1000") Integer count) {
        dbPopulator.populate(count);
        return Response.ok().build();
    }

    @GET
    public Response eventStream() {
        Observable<Event> events = dbAccess.selectEvents();
        ObservableStreamingOutput stream = new ObservableStreamingOutput(events);
        log.info("initiating event stream");
        return Response.ok(stream).build();
    }

}
