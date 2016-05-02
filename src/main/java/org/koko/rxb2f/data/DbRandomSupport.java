package org.koko.rxb2f.data;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class DbRandomSupport {

    final Random random = new Random();
    final List<String> codeChars = Arrays.asList("a", "b", "c", "d", "e");
    final String alphanumeric = new String("abcdefghijklmnopqrstuhwvyz1234567890");

    String randomCode() {
        return IntStream.range(0, 5).mapToObj(c -> codeChars.get(random.nextInt(5))).reduce("", (i, c) -> i + c);
    }

    String randomTitle() {
        return IntStream.range(0, 20).mapToObj(i -> String.valueOf(alphanumeric.charAt(random.nextInt(36)))).reduce("", (a, b) -> a + b);
    }

}
