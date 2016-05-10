package org.koko.rxb2f.api.stream;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.koko.rxb2f.domain.event.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;

public final class StreamingObserver implements Observer<Event> {

    private final ObjectMapper mapper;
    private final OutputStream output;
    private final CountDownLatch latch;

    final Logger log = LoggerFactory.getLogger(StreamingObserver.class);

    public StreamingObserver(OutputStream output, CountDownLatch latch) {
        this.mapper = new ObjectMapper();
        this.output = output;
        this.latch = latch;
    }

    @Override
    public void onNext(Event event) {
        try {
            mapper.writeValue(output, event);
        } catch (IOException e) {
            log.warn("error during write", e);
            latch.countDown();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("stream error", throwable);
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        try {
            log.info("stream end");
            output.close();
        } catch (IOException e) {
            log.warn("error closing output", e);
        } finally {
            latch.countDown();
        }
    }
}