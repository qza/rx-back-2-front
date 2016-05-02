package org.koko.rxb2f.data;

import com.github.pgasync.Db;

import org.koko.rxb2f.api.stream.StreamingObserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.stream.IntStream;

@Component
public class DbPopulator {

    final Db db;
    final DbRandomSupport dbRandom;

    final Logger log = LoggerFactory.getLogger(StreamingObserver.class);

    @Autowired
    public DbPopulator(DbConfig dbConfig, DbRandomSupport dbRandom) {
        this.db = dbConfig.getDb();
        this.dbRandom = dbRandom;
        this.defineTables();
    }

    public void populate(Integer count) {
        IntStream.range(0, count).forEach(ind -> {
            Date date = new Date(System.currentTimeMillis());
            db.querySet(
                    "insert into events(code, title, date_prod) values($1, $2, $3)",
                    dbRandom.randomCode(), dbRandom.randomTitle(), date
            ).subscribe(
                    result -> log.info("Inserted {} rows", result.updatedRows()),
                    error -> log.error("Error inserting row: %s", error.getMessage()));
        });
    }

    private void defineTables() {

        assert db != null;

        db.querySet(
                "CREATE TABLE IF NOT EXISTS events ( " +
                        "    id          serial primary key, " +
                        "    code        char(5), " +
                        "    title       varchar(63), " +
                        "    date_prod   timestamp" +
                        ");"
        ).subscribe(
                result -> log.info("database prepared")
        );
    }

}
