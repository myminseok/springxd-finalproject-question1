package client;

import com.gemstone.gemfire.GemFireCheckedException;
import com.gemstone.gemfire.GemFireException;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.Pool;
import com.gemstone.gemfire.cache.client.PoolManager;
import com.gemstone.gemfire.cache.query.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CQClient {
    private ClientCache cache;

    public static void main(String[] args) throws Exception {
        CQClient consumer = new CQClient();
        consumer.getCache();

        try {
            consumer.registerCq();

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(System.in));

            System.out.println("Press enter to end");
            bufferedReader.readLine();
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        consumer.closeCache();

    }

    public void registerCq() throws GemFireException, GemFireCheckedException {
        // Get a reference to the pool
        Pool myPool = PoolManager.find("client");

        QueryService queryService = myPool.getQueryService();

        CqAttributesFactory cqAf = new CqAttributesFactory();
        cqAf.addCqListener(new StatCQListener(cache));
        CqAttributes cqa = cqAf.create();

        String query = "SELECT * FROM /TripStat";
        CqQuery myCq = queryService.newCq("myCQ", query, cqa);
        System.out.println("Made new CQ Service");
        SelectResults sResults = myCq.executeWithInitialResults();
//        for (Object o : sResults) {
//            Struct s = (Struct) o;
//            System.out.println("Intial result includes: " + s);
//        }

    }

    public void closeCache() {
        cache.close();
    }

    public void getCache() {

        cache = new ClientCacheFactory().set("name", "client.CQClient")
                .set("cache-xml-file", "xml/clientCache.xml").create();
    }

}
