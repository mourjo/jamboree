package me.mourjo.jamboree.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class Response extends ResponseEntity<Map<String, String>> {

    private static Logger logger = LoggerFactory.getLogger(Response.class);

    public Response(Map<String, String> body, HttpStatusCode status) {
        super(body, status);
    }

    public static Response of(HttpStatus status, Map<String, String> body) {
        try {
            MDC.put("RESPONSE_CODE", Integer.toString(status.value()));
            if (status.isError() && body.containsKey("error")) {
                logger.error("Error: {}", body.get("error"));
            }
            logger.debug("Finished processing request");
            return new Response(body, status);
        } finally {
            MDC.remove("RESPONSE_CODE");
        }
    }
}
