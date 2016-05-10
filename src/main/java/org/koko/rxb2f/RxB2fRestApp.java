package org.koko.rxb2f;

import org.glassfish.jersey.server.ResourceConfig;
import org.koko.rxb2f.api.CorsFilter;
import org.koko.rxb2f.domain.event.EventApi;
import org.koko.rxb2f.external.event.EventLocationApi;
import org.springframework.stereotype.Component;

@Component
public class RxB2fRestApp extends ResourceConfig {

    public RxB2fRestApp() {
        register(CorsFilter.class);
        register(EventApi.class);
        register(EventLocationApi.class);
    }

}
