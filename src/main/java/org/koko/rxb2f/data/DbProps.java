package org.koko.rxb2f.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ConfigurationProperties(prefix = "db")
public class DbProps {

    private Optional<String> host = Optional.of("localhost");
    private Optional<Integer> port = Optional.of(5432);
    private Optional<String> name = Optional.of("rxb2f");
    private Optional<String> user = Optional.of("postgres");
    private Optional<String> pass = Optional.of("root");

    public String getHost() {
        return host.get();
    }

    public void setHost(String host) {
        Optional.ofNullable(host).ifPresent(e -> this.host = Optional.of(e));
    }

    public Integer getPort() {
        return port.get();
    }

    public void setPort(Integer port) {
        Optional.ofNullable(port).ifPresent(e -> this.port = Optional.of(e));
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        Optional.ofNullable(name).ifPresent(e -> this.name = Optional.of(e));
    }

    public String getUser() {
        return user.get();
    }

    public void setUser(String user) {
        Optional.ofNullable(user).ifPresent(e -> this.user = Optional.of(e));
    }

    public String getPass() {
        return pass.get();
    }

    public void setPass(String pass) {
        Optional.ofNullable(pass).ifPresent(e -> this.pass = Optional.of(e));
    }
}
