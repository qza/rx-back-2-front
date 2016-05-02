package org.koko.rxb2f.data;

import com.github.pgasync.Db;
import com.github.pgasync.Row;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rx.Observable;

import java.text.SimpleDateFormat;

@Component
public class DbAccess {

    private Db db;

    @Autowired
    public DbAccess(DbConfig dbConfig) {
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
