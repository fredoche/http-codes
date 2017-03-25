package devoxx.serverless.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import java.util.HashMap;
import java.util.Map;

public class CodePutHandler extends AbstractGetHandler {

    private final DynamoDB dynamoDB;

    public CodePutHandler() {
        this.dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
    }

    @Override
    public AwsProxyResponse doHandle(AwsProxyRequest request) throws Exception {
        Integer code = Integer.parseInt(request.getPathParameters().get("code"));

        Table table = dynamoDB.getTable("HttpCodeStatistics");

        Item item = table.getItem("code", code);

        if (item == null) {
            item = table.putItem(new Item()
                    .withPrimaryKey("code", code)
                    .withInt("occurences", 0))
                    .getItem();
        } else {
//            System.out.println("plop");
//            item = table.updateItem("code", new AttributeUpdate("occurences").addNumeric(1)).getItem();
//            System.out.println("plip");

            UpdateItemSpec updateItemSpec = new UpdateItemSpec()
                    .withPrimaryKey("code", code)
                    .withReturnValues(ReturnValue.ALL_NEW)
                    .withUpdateExpression("add #o :val1")
                    .withNameMap(new NameMap()
                            .with("#o", "occurences"))
                    .withValueMap(new ValueMap()
                            .withNumber(":val1", 1));

            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
            item = outcome.getItem();
        }

        return createSuccessResponse(item.toJSON());
    }
}
