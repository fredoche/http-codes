package devoxx.serverless.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import static devoxx.serverless.model.ErrorResponse.fromException;
import static devoxx.serverless.model.MessageResponse.fromMessage;
import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import static java.util.Collections.emptyMap;
import org.apache.log4j.Logger;

abstract class AbstractHandler implements RequestStreamHandler {

    private final static Logger logger = Logger.getLogger(AbstractHandler.class);

    static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
            .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Override
    public void handleRequest(InputStream inputStream, OutputStream output, Context context) throws IOException {
        AwsProxyResponse response;
        try {
            response = doHandle(inputStream, context);
        } catch (Exception e) {
            logger.error("error in request handling: ", e);
            String errorAsJson = mapper.writeValueAsString(fromException(e));
            response = new AwsProxyResponse(500, emptyMap(), errorAsJson);
        }
        mapper.writeValue(output, response);
    }

    public AwsProxyResponse doHandle(InputStream inputStream, Context context) throws Exception {
        AwsProxyRequest request = mapper.readValue(inputStream, AwsProxyRequest.class);
        return doHandle(request);
    }

    public abstract AwsProxyResponse doHandle(AwsProxyRequest request) throws Exception;

    protected AwsProxyResponse createSuccessResponse(String successMessage) throws JsonProcessingException {
        logger.debug(successMessage);
        String bodyAsJson = mapper.writeValueAsString(fromMessage(successMessage));
        return new AwsProxyResponse(200, bodyAsJson);
    }

    protected <T> AwsProxyResponse createSuccessResponse(T successMessage) throws JsonProcessingException {
        logger.debug(successMessage);
        String bodyAsJson = mapper.writeValueAsString(successMessage);
        return new AwsProxyResponse(200, bodyAsJson);
    }
}
