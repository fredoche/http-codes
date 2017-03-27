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
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static devoxx.serverless.model.common.ErrorResponse.fromException;
import static devoxx.serverless.model.common.MessageResponse.fromMessage;
import static java.util.Collections.emptyMap;

abstract class AbstractErrorHandler {


 

    protected abstract AwsProxyResponse doHandle(InputStream inputStream, Context context) throws Exception;

   
}
