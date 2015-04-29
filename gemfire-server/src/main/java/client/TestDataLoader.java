package client;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import domain.Trip;

public class TestDataLoader {

    private ClientCache cache;
    private Region<Integer, Trip> infos;

    /**
     * @param args
     */
    public static void main(String[] args) {
        TestDataLoader loader = new TestDataLoader();
        loader.getCache();
        loader.getBookMasterRegion();
        loader.populateBooks();

    }

    public void setCache(ClientCache cache) {
        this.cache = cache;
    }

    public void closeCache() {
        cache.close();
    }

    public void getCache() {
        this.cache = new ClientCacheFactory()
                .set("name", "ClientWorker")
                .set("cache-xml-file", "xml/clientCache.xml")
                .create();
    }

    public void getBookMasterRegion() {
        infos = cache.getRegion("Trip");
        System.out.println("Got the domain.Trip Region: " + infos);
    }


    public void populateBooks() {
        //07290D3599E7A0D62097A346EFCC1FB5,E7750A37CAB07D0DFF0AF7E3573AC141,2013-01-01 00:00:00,2013-01-01 00:02:00,120,0.44,-73.956528,40.716976,-73.962440,40.715008,CSH,3.50,0.50,0.50,0.00,0.00,4.50
        Trip obj = new Trip("07290D3599E7A0D62097A346EFCC1FB5", "E7750A37CAB07D0DFF0AF7E3573AC141",
                "2013-01-01 00:00:00", "2013-01-01 00:02:00",
                110, (long) 0.44, -73.956528, 41.716976, -73.962440, 40.715008,
                "CSH", (long) 3.50);

        infos.put(new Integer(121), obj);
        System.out.println("Inserted a book: " + obj);

        obj = new Trip("17290D3599E7A0D62097A346EFCC1FB5", "17750A37CAB07D0DFF0AF7E3573AC141",
                "2013-01-01 00:00:00", "2013-01-01 00:02:00",
                120, (long) 0.44, -73.956528, 40.716976, -73.962440, 40.715008,
                "CSH", (long) 3.50);
        infos.put(new Integer(122), obj);
        System.out.println("Inserted a book: " + obj);
        obj = new Trip("17290D3599E7A0D62097A346EFCC1FB5", "17750A37CAB07D0DFF0AF7E3573AC141",
                "2013-01-01 00:00:00", "2013-01-01 00:02:00",
                130, (long) 0.44, -73.956528, 43.716976, -73.962440, 40.715008,
                "CSH", (long) 3.50);

        infos.put(new Integer(123), obj);
        System.out.println("Inserted a book: " + obj);

        obj = new Trip("47290D3599E7A0D62097A346EFCC1FB5", "47750A37CAB07D0DFF0AF7E3573AC141",
                "2013-01-01 00:00:00", "2013-01-01 00:02:00",
                130, (long) 0.44, -73.956528, 40.716976, -73.962440, 40.715008,
                "CSH", (long) 3.50);

        infos.put(new Integer(124), obj);
        System.out.println("Inserted a book: " + obj);

    }


}
