package devoxx.serverless.api;

import com.amazonaws.services.lambda.runtime.Context;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;

import java.io.InputStream;

abstract class AbstractGetHandler extends AbstractErrorHandler {

    public abstract AwsProxyResponse doHandle(AwsProxyRequest request) throws Exception;

    @Override
    public AwsProxyResponse doHandle(InputStream inputStream, Context context) throws Exception {
        AwsProxyRequest request = mapper.readValue(inputStream, AwsProxyRequest.class);
        return doHandle(request);
    }

}
