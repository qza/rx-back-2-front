package org.koko.rxb2f.external;

import org.koko.rxb2f.support.Randomized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Component
@Path("/external/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventLocationApi {

    @Autowired
    private Randomized randomized;

    @GET
    @Path("/{code}/location")
    public Response getEventLocation(@PathParam("code") String code, @QueryParam("delay") String delay) {
        Optional.ofNullable(delay).ifPresent(should -> randomized.delay());
        EventLocation eventLocation = new EventLocation(randomized.randomPlace(), randomized.randomCoordinates());
        return Response.ok(eventLocation).build();
    }

}
