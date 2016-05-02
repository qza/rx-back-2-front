package org.koko.rxb2f;

import org.glassfish.jersey.server.ResourceConfig;
import org.koko.rxb2f.api.CorsFilter;
import org.koko.rxb2f.api.RestApi;
import org.springframework.stereotype.Component;

@Component
public class RxB2fRestApp extends ResourceConfig {

    public RxB2fRestApp() {
        register(CorsFilter.class);
        register(RestApi.class);
    }

}
