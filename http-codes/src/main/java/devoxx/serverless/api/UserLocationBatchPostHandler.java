package devoxx.serverless.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import org.apache.log4j.Logger;

public class UserLocationBatchPostHandler extends AbstractPostHandler<Object> {

    private final static Logger logger = Logger.getLogger(UserLocationBatchPostHandler.class);

    private final String kinesisStreamName;
//    private final AmazonKinesisFirehoseClient firehoseClient;

    public UserLocationBatchPostHandler() {
        this.kinesisStreamName = System.getenv("TARGET_KINESIS_STREAM_NAME");
    }

    /**
     * for tests
     *
     * @param firehoseClient
     */
    public UserLocationBatchPostHandler(Object firehoseClient) {
        this.kinesisStreamName = System.getenv("TARGET_KINESIS_STREAM_NAME");
//        this.firehoseClient = firehoseClient;
    }

    @Override
    public AwsProxyResponse doHandle(Object input) throws JsonProcessingException {
//        logger.info("Inserting locations for User " + input.getUserId());
//        // create records
//        PutRecordBatchRequest putRecordBatchRequest = new PutRecordBatchRequest();
//        putRecordBatchRequest.setDeliveryStreamName(kinesisStreamName);
//        List<Record> records = input.getLocations().stream()
//                .map(location -> new UserLocationRequest(input.getUserId(), location))
//                .map(userLocation -> {
//                    try {
//                        Record r = new Record();
//                        r.setData(ByteBuffer.wrap((mapper.writeValueAsString(userLocation) + "\n").getBytes(Charsets.UTF_8)));
//                        return r;
//                    } catch (JsonProcessingException jpe) {
//                        throw new RuntimeException(jpe);
//                    }
//                })
//                .collect(Collectors.toList());
//        putRecordBatchRequest.setRecords(records);
//        // put records in kinesis and return
//        firehoseClient.putRecordBatch(putRecordBatchRequest);
//        // create success response
//        return createSuccessResponse("Multiple geolocation recorded for user " + input.getUserId());
        return null;
    }

}
