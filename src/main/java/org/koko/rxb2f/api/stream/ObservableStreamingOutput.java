package org.koko.rxb2f.api.stream;

import org.koko.rxb2f.data.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;

public class ObservableStreamingOutput implements StreamingOutput {

    final Observable<Event> eventObservable;

    final Logger log = LoggerFactory.getLogger(ObservableStreamingOutput.class);

    public ObservableStreamingOutput(Observable<Event> eventObservable) {
        this.eventObservable = eventObservable;
    }

    @Override
    public void write(OutputStream output) throws IOException, WebApplicationException {
        CountDownLatch latch = new CountDownLatch(1);
        StreamingObserver streamingObserver = new StreamingObserver(output, latch);
        eventObservable.subscribe(streamingObserver);
        try {
            log.info("waiting for stream to end");
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
