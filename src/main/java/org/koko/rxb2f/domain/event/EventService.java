package org.koko.rxb2f.domain.event;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.pgasync.Db;
import com.github.pgasync.Row;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.client.HttpClient;

import org.koko.rxb2f.data.DbConfig;
import org.koko.rxb2f.external.event.EventLocation;
import org.koko.rxb2f.support.RxHttpClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rx.Observable;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Service
public class EventService {

    private Db db;
    private HttpClient<ByteBuf, ByteBuf> rxHttpClient;

    private final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy'T'hh:mm:ss");
    private final Logger log = LoggerFactory.getLogger(EventService.class);

    @Autowired
    public EventService(DbConfig dbConfig, RxHttpClient rxHttpClient) {
        this.db = dbConfig.getDb();
        this.rxHttpClient = rxHttpClient.initLocationApiClient();
    }

    public Observable<Event> selectEvents() {
        return db.queryRows("select * from events").map(this::asEvent);
    }

    public Observable<EventLocation> selectEventLocation(Event event) {
        return rxHttpClient
                .createGet("/external/events" + event.getCode() + "/location")
                .doOnNext(r -> log.debug("received event location response: {}", r.toString()))
                .flatMap(resp -> resp.getContent().map(this::asEventLocation));

    }

    private Event asEvent(Row row) {
        return new Event(row.getString("code"), row.getString("title"), sdf.format(row.getDate("date_prod")));
    }

    private EventLocation asEventLocation(ByteBuf byteBuf) {
        try {
            return new ObjectMapper().readValue(byteBuf.array(), EventLocation.class);
        } catch (IOException e) {
            log.error("error mapping location json", e);
        }
        return null;
    }


}
