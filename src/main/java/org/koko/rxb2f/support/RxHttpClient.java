package org.koko.rxb2f.support;

import io.netty.handler.logging.LogLevel;
import io.reactivex.netty.protocol.http.client.HttpClient;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Component
public class RxHttpClient {

    private SocketAddress locationApiAddress = new InetSocketAddress("localhost", 8080);

    public HttpClient initLocationApiClient() {
        return HttpClient.newClient(locationApiAddress).enableWireLogging(LogLevel.DEBUG);
    }

}
