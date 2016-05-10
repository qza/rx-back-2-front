package org.koko.rxb2f.domain.event;

import com.github.pgasync.Db;
import com.github.pgasync.Row;

import org.koko.rxb2f.data.DbConfig;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import rx.Observable;

import java.text.SimpleDateFormat;

@Service
public class EventService {

    private Db db;

    @Autowired
    public EventService(DbConfig dbConfig) {
        this.db = dbConfig.getDb();
    }

    public Observable<Event> selectEvents() {
        return db.queryRows("select * from events").map(row -> asEvent(row));
    }

    private Event asEvent(Row row) {
        return new Event(row.getString("code"), row.getString("title"), sdf.format(row.getDate("date_prod")));
    }

    private final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy'T'hh:mm:ss");

}
