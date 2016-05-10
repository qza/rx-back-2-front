package org.koko.rxb2f.external;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.koko.rxb2f.RxB2fApp;
import org.koko.rxb2f.support.Randomized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@WebAppConfiguration
@SpringApplicationConfiguration(classes = RxB2fApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest
public class EventLocationApiTest {

    @Autowired
    private Randomized randomized;

    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void shouldGetEventLocation() {
        String url = "http://localhost:8080/external/events/" + randomized.randomCode() + "/location";
        ResponseEntity<EventLocation> entity = restTemplate.getForEntity(url, EventLocation.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody());
        assertNotNull(entity.getBody().getCoordinates());
    }

}
