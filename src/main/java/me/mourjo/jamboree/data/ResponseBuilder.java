package me.mourjo.jamboree.data;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class ResponseBuilder extends ResponseEntity<Map<String, String>> {
    public ResponseBuilder(HttpStatusCode status) {
        super(status);
    }

    public ResponseBuilder(Map<String, String> body, HttpStatusCode status) {
        super(body, status);
    }

    public ResponseBuilder(MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(headers, status);
    }

    public ResponseBuilder(Map<String, String> body, MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(body, headers, status);
    }

    public ResponseBuilder(Map<String, String> body, MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
    }
}
