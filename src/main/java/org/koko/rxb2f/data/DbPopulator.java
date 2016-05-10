package org.koko.rxb2f.data;

import com.github.pgasync.Db;

import org.koko.rxb2f.api.stream.StreamingObserver;

import org.koko.rxb2f.support.Randomized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.IntStream;

@Component
public class DbPopulator {

    private final Db db;
    private final Randomized randomized;

    private final Logger log = LoggerFactory.getLogger(StreamingObserver.class);

    @Autowired
    public DbPopulator(DbConfig dbConfig, Randomized randomized) {
        this.db = dbConfig.getDb();
        this.randomized = randomized;
        this.defineTables();
    }

    public void populate(Integer count) {
        IntStream.range(0, count).forEach(ind -> {
            LocalDate date = LocalDate.now();
            db.querySet(
                    "insert into events(code, title, date_prod) values($1, $2, $3)",
                    randomized.randomCode(), randomized.randomTitle(), date
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
                        "    date_prod   date" +
                        ");"
        ).subscribe(
                result -> log.info("database prepared")
        );
    }

}
