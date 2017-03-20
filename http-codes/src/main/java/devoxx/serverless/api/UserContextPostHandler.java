package devoxx.serverless.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;


//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
public class UserContextPostHandler extends AbstractPostHandler<Object> {

    private static final String CONTEXT_S3_PATH_PREFIX = "context/";
    private static final String CONTEXT_S3_PATH_SUFFIX = "/context.json";

    private final String s3BucketName;
//    private AmazonDynamoDBClient s3Client;

    public UserContextPostHandler() {
        this.s3BucketName = System.getenv("TARGET_S3_BUCKET_NAME");
//        this.s3Client = new AmazonS3Client();
//        this.s3Client.setRegion(RegionUtils.getRegion("eu-west-1"));
    }

    @Override
    public AwsProxyResponse doHandle(Object input) throws JsonProcessingException {
//        // create context object for S3 : One context by user
//        String path = CONTEXT_S3_PATH_PREFIX + input.getContext().getUserId() + CONTEXT_S3_PATH_SUFFIX;
//        String contextAsJson = mapper.writeValueAsString(input.getContext());
//        PutObjectRequest request = createPutObjectRequest(path, contextAsJson);
//        // put object in S3
//        s3Client.putObject(request);
//        // create success response
//        return createSuccessResponse("Context recorded for user " + input.getContext().getUserId());
        return null;
    }

//    private PutObjectRequest createPutObjectRequest(String path, String contextAsJson) {
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentEncoding("UTF-8");
//        metadata.setContentType("application/json");
//        return new PutObjectRequest(s3BucketName, path, new ByteArrayInputStream(contextAsJson.getBytes()), metadata);
//    }
}
