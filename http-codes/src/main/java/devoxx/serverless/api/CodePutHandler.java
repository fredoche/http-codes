package devoxx.serverless.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;

public class CodePutHandler extends AbstractGetHandler {

    private final DynamoDB dynamoDB;

    public CodePutHandler() {
        this.dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
    }

    @Override
    public AwsProxyResponse doHandle(AwsProxyRequest request) throws Exception {
        Table table = dynamoDB.getTable("HttpCodeStatistics");
        String code = request.getPathParameters().get("code");

//        Item item = table.updateItem("Code", request.getPathParameters().get("code"));
//
//        UpdateItemSpec s = new UpdateItemSpec()
//                .withPrimaryKey("code", code)
//                
//                .withUpdateExpression("SET occurences  = 10")
//                .withReturnValues("ALL_NEW");
//        UpdateItemOutcome updateItem = table.updateItem(s);
        PutItemOutcome outcome = table.putItem(new Item()
                .withPrimaryKey("code", Integer.parseInt(code))
                .withInt("occurences", 1234));
        
        Item item = table.getItem("code", request.getPathParameters().get(code));

        return createSuccessResponse("rien");
    }
}
