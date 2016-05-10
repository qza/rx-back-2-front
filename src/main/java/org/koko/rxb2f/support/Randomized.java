package org.koko.rxb2f.support;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class Randomized {

    private final Random random = new Random();
    private final List<String> codeChars = Arrays.asList("a", "b", "c", "d", "e");
    private final String alphanumeric = "abcdefghijklmnopqrstuhwvyz1234567890";

    public String randomCode() {
        return IntStream.range(0, 5).mapToObj(c -> codeChars.get(random.nextInt(5))).reduce("", (i, c) -> i + c);
    }

    public String randomTitle() {
        return IntStream.range(0, 20).mapToObj(i -> String.valueOf(alphanumeric.charAt(random.nextInt(36)))).reduce("", (a, b) -> a + b);
    }

    public Void delay() {
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
        return null;
    }

}
