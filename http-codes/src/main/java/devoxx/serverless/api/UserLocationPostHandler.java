package devoxx.serverless.api;

import com.fasterxml.jackson.core.JsonProcessingException;
//import static devoxx/serverless.util.FirehoseClientBuilder.getClient;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;

public class UserLocationPostHandler extends AbstractPostHandler<Object> {

    private final String kinesisStreamName;
//    private final AmazonKinesisFirehoseClient firehoseClient;

    public UserLocationPostHandler() {
        this.kinesisStreamName = System.getenv("TARGET_KINESIS_STREAM_NAME");
//        this.firehoseClient = getClient(kinesisStreamName);
    }

    /**
     * for tests
     *
     * @param firehoseClient
     */
    public UserLocationPostHandler(Object firehoseClient) {
        this.kinesisStreamName = System.getenv("TARGET_KINESIS_STREAM_NAME");
//        this.firehoseClient = firehoseClient;
    }

    @Override
    public AwsProxyResponse doHandle(Object input) throws JsonProcessingException {
//        // create record
//        PutRecordRequest putRecordRequest = new PutRecordRequest();
//        putRecordRequest.setDeliveryStreamName(kinesisStreamName);
//        Record r = new Record();
//        r.setData(ByteBuffer.wrap((mapper.writeValueAsString(input) + "\n").getBytes(Charsets.UTF_8)));
//        putRecordRequest.setRecord(r);
//        // put record in kinesis and return
//        firehoseClient.putRecord(putRecordRequest);
//        // create success response
//        return createSuccessResponse("Geolocation recorded for user " + input.getUserId());
        return null;
    }

}
