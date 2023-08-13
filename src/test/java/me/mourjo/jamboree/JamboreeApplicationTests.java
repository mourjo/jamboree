package me.mourjo.jamboree;

import me.mourjo.jamboree.rest.PartyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JamboreeApplicationTests {

    @Autowired
    private PartyController partyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Value(value = "${local.server.port}")
    private int port;

    @Test
    void testCreateGetParty() {
        var response = restTemplate.getForObject(getEndpoint("1"), Map.class);
        assertEquals(Map.of("error", "Not found"), response);

        for (int i = 1; i <= 10; i++) {
            var data = Map.of("name", "party-" + i, "location", "Kolkata");
            response = restTemplate.postForObject(postEndpoint(), data, Map.class);
            assertEquals(String.valueOf(i), response.get("id"));
            assertEquals("Kolkata", response.get("location"));
            assertEquals("party-" + i, response.get("name"));
        }

        response = restTemplate.getForObject(getEndpoint("1"), Map.class);
        assertEquals("Kolkata", response.get("location"));
        assertEquals("party-1", response.get("name"));

        response = restTemplate.getForObject(getEndpoint("10"), Map.class);
        assertEquals("Kolkata", response.get("location"));
        assertEquals("party-10", response.get("name"));
    }

    private String getEndpoint(String id) {
        return String.format("http://localhost:%s/party/%s", port, id);
    }

    private String postEndpoint() {
        return String.format("http://localhost:%s/party/", port);
    }

}

