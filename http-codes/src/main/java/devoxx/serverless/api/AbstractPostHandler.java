package devoxx.serverless.api;

import com.amazonaws.services.lambda.runtime.Context;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import org.apache.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.Set;

import static devoxx.serverless.model.common.ErrorResponse.fromMessage;
import static devoxx.serverless.model.common.ValidationErrorResponse.fromConstraintViolations;

abstract class AbstractPostHandler<REQUEST> extends AbstractErrorHandler {

    private final static Logger logger = Logger.getLogger(AbstractPostHandler.class);

    private final static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public abstract AwsProxyResponse doHandle(REQUEST request) throws Exception;

    @Override
    public AwsProxyResponse doHandle(InputStream inputStream, Context context) throws Exception {
        AwsProxyRequest request = mapper.readValue(inputStream, AwsProxyRequest.class);
        if (request.getBody() != null) {
            // read and validate request
            REQUEST input = mapper.readValue(request.getBody(), getRequestClass());
            Set<ConstraintViolation<REQUEST>> violations = validator.validate(input);
            if (!violations.isEmpty()) {
                violations.stream().forEach(v -> logger.error("Validation error: " + v.getPropertyPath() + " " + v.getMessage()));
                String validationErrorAsJson = mapper.writeValueAsString(fromConstraintViolations(violations));
                return new AwsProxyResponse(400, Collections.emptyMap(), validationErrorAsJson);
            }
            // handle and response
            return doHandle(input);
        } else {
            String errorAsJson = mapper.writeValueAsString(fromMessage("POST request body must not be empty"));
            return new AwsProxyResponse(400, Collections.emptyMap(), errorAsJson);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<REQUEST> getRequestClass() {
        return (Class<REQUEST>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
