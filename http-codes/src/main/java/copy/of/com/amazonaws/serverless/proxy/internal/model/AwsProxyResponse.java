/*
 * Copyright 2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package copy.of.com.amazonaws.serverless.proxy.internal.model;

import static java.util.Collections.unmodifiableMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Response object for an API Gateway method using AWS_PROXY integrations
 */
public class AwsProxyResponse {

    //-------------------------------------------------------------
    // Variables - Private
    //-------------------------------------------------------------
    private int statusCode;
    private final Map<String, String> headers;
    private String body;

    //-------------------------------------------------------------
    // Constructors
    //-------------------------------------------------------------
    public AwsProxyResponse() {
        headers = new HashMap<>();
        addHeader("Access-Control-Allow-Origin", "*");
    }

    public AwsProxyResponse(int statusCode) {
        this();
        this.statusCode = statusCode;
    }

    public AwsProxyResponse(int statusCode, Map<String, String> headers) {
        this();
        this.statusCode = statusCode;
        this.headers.putAll(headers);
    }

    public AwsProxyResponse(int statusCode, String body) {
        this();
        this.statusCode = statusCode;
        this.body = body;
    }

    public AwsProxyResponse(int statusCode, Map<String, String> headers, String body) {
        this();
        this.statusCode = statusCode;
        this.headers.putAll(headers);
        this.body = body;
    }

    //-------------------------------------------------------------
    // Methods - Public
    //-------------------------------------------------------------
    public final void addHeader(String key, String value) {
        headers.put(key, value);
    }

    //-------------------------------------------------------------
    // Methods - Getter/Setter
    //-------------------------------------------------------------
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return unmodifiableMap(headers);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
