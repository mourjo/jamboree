package me.mourjo.jamboree.data;

import me.mourjo.jamboree.apischema.PartyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class Response<T> extends ResponseEntity<T> {

    private static final Logger logger = LoggerFactory.getLogger(Response.class);

    public Response(T body, HttpStatusCode status) {
        super(body, status);
    }

    public static Response<PartyResponse> error(HttpStatus status, String error) {
        MDC.put("RESPONSE_CODE", Integer.toString(status.value()));
        logger.error("Error: {}", error);
        logger.debug("Finished processing request");
        MDC.remove("RESPONSE_CODE");
        return new Response(new PartyResponse(null, null, null, null, null, error), status);
    }


    public static Response<PartyResponse> of(HttpStatus status, PartyResponse body) {
        try {
            MDC.put("RESPONSE_CODE", Integer.toString(status.value()));
            if (status.isError()) {
                logger.error("Error: {}", body);
            }
            logger.debug("Finished processing request");
            return new Response(body, status);
        } finally {
            MDC.remove("RESPONSE_CODE");
        }
    }
}
