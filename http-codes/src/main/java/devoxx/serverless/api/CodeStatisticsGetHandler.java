package devoxx.serverless.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import copy.of.com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import java.util.HashMap;
import java.util.Map;

public class CodeStatisticsGetHandler extends AbstractGetHandler {

    private final DynamoDB dynamoDB;

    public CodeStatisticsGetHandler() {
        this.dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());

    }

    @Override
    public AwsProxyResponse doHandle(AwsProxyRequest request) throws Exception {
        Table table = dynamoDB.getTable("HttpCodeStatistics");
        //
        //        ItemCollection<ScanOutcome> scanResult = table.scan();
        //        
        //        scanResult.get
        //        ItemCollection<ScanOutcome> scan = table.scan();
        //        ScanRequest scanRequest = new ScanRequest()
        //                .withTableName("HttpCodeStatistics");
        //        
        //        ScanResult result = dynamoDB.scan(scanRequest);
        //        List<Item> items = new ArrayList<>();
        //        for (Item o : scan) {
        //            items.add(o);
        //        }

        Map<Integer, Integer> res = new HashMap<>();

        ItemCollection<ScanOutcome> scan = table.scan();
        scan.forEach((so) -> {
            System.out.println("aze");
            System.out.println(so.getInt("code") + "  aze  " + so.getInt("occurences"));
            res.put(so.getInt("code"), so.getInt("occurences"));
        });

        return createSuccessResponse(res);
    }
}
