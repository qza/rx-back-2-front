package org.koko.rxb2f.data;

import com.github.pgasync.ConnectionPoolBuilder;
import com.github.pgasync.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbConfig {

    private final Db db;

    @Autowired
    public DbConfig(DbProps props) {
        db = new ConnectionPoolBuilder()
                .hostname(props.getHost())
                .port(props.getPort())
                .database(props.getName())
                .username(props.getUser())
                .password(props.getPass())
                .poolSize(20)
                .build();
    }

    public Db getDb() {
        return db;
    }
}
