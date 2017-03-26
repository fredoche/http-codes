package devoxx.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author fred
 */
public class HelloWorld implements RequestStreamHandler {

    static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
            .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        AwsProxyRequest request = mapper.readValue(input, AwsProxyRequest.class);

        String firstName = request.getPathParameters().get("firstName");
        final String message = "Bonjour " + firstName + "!";

        System.out.println(message);
        String bodyAsJson = mapper.writeValueAsString(new HelloResponse(message));

        mapper.writeValue(output, new AwsProxyResponse(200, bodyAsJson));
    }

    private class HelloResponse {

        private final String message;

        public HelloResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}
