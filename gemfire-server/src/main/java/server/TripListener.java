package server;

import com.gemstone.gemfire.cache.Declarable;
import com.gemstone.gemfire.cache.EntryEvent;
import com.gemstone.gemfire.cache.RegionEvent;
import com.gemstone.gemfire.cache.query.*;
import com.gemstone.gemfire.cache.util.CacheListenerAdapter;
import domain.Trip;
import domain.TripStat;

import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TripListener<K, V> extends CacheListenerAdapter<K, V>
        implements Declarable {
    private Logger logger = Logger.getLogger(TripListener.class.getName());

    public void afterCreate(EntryEvent<K, V> e) {
        System.out.println("    Received afterCreate event for entry: "
                + e.getKey() + ", " + e.getNewValue());
        logger.info(e.getKey() + ", " + e.getNewValue());
        query(e);


    }

    public void afterUpdate(EntryEvent<K, V> e) {
        System.out.println("    Received afterUpdate event for entry: "
                + e.getKey() + ", " + e.getNewValue());
        query(e);
    }

    public void afterDestroy(EntryEvent<K, V> e) {
//        System.out.println("    Received afterDestroy event for entry: "
//                + e.getKey());
    }

    public void afterInvalidate(EntryEvent<K, V> e) {
//        System.out.println("    Received afterInvalidate event for entry: "
//                + e.getKey());
    }

    public void afterRegionLive(RegionEvent e) {
        System.out
                .println("    Received afterRegionLive event, sent to durable clients after \nthe server has finished replaying stored events.  ");
    }

    public void init(Properties props) {
        String filename = props.getProperty("filename");
        initializeLogger(filename);
    }

    private void initializeLogger(String filename) {
        FileHandler fh;
        logger.setLevel(Level.INFO);
        try {
            fh = new FileHandler(filename, true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }


    private void query(EntryEvent<K, V> e) {
        if (e.getNewValue() instanceof Trip) {
            Trip info = (Trip) e.getNewValue();
            Object[] queryParams = new Object[2];
            queryParams[0] = info.getStartCell();
            queryParams[1] = info.getEndCell();
            String queryString = "Select count(*) from /Trip where startCell = $1 and endCell=$2";
            QueryService queryService = e.getRegion().getRegionService().getQueryService();
            Query query = queryService.newQuery(queryString);
            SelectResults results = null;
            try {
                results = (SelectResults) query.execute(queryParams);
            } catch (FunctionDomainException e1) {
                e1.printStackTrace();
            } catch (TypeMismatchException e1) {
                e1.printStackTrace();
            } catch (NameResolutionException e1) {
                e1.printStackTrace();
            } catch (QueryInvocationTargetException e1) {
                e1.printStackTrace();
            }
            int count = 0;
            for (Object o : results) {
                System.out.println(o);
                count = Integer.valueOf(o.toString());
            }
            TripStat stat = new TripStat(info.getStartCell(), info.getEndCell(), count, info.getPickupDatetime(), info.getDropoffDatetime());
            e.getRegion().getRegionService().getRegion("TripStat").put(info.getStartCell() + info.getEndCell(), stat);

        }

    }
}