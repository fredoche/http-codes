package devoxx.serverless.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;

public class CodeStatisticsGetHandler extends AbstractGetHandler {

    private final DynamoDB dynamoDB;

    public CodeStatisticsGetHandler() {
        this.dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());

    }

    @Override
    public AwsProxyResponse doHandle(AwsProxyRequest request) throws Exception {
        Table table = dynamoDB.getTable("HttpCodeStatistics");
        Item item = table.getItem("code", request.getPathParameters().get("code"));
        return createSuccessResponse(item.toJSON());
    }
}
