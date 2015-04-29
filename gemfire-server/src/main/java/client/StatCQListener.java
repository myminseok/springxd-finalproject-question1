package client;

import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.query.*;
import domain.Trip;
import domain.TripStat;

import java.text.SimpleDateFormat;

public class StatCQListener implements CqListener {

    private ClientCache cache;

    public StatCQListener(ClientCache cache) {
        this.cache = cache;
    }

    @Override
    public void close() {
        System.out.println("SimpleCQListener:Received Close Event");

    }

    @Override
    public void onError(CqEvent event) {
        System.out.println("SimpleCQListener:Received onError event");
        System.out.println("SimpleCQListener:Throwable: " + event.getThrowable());

    }

    @Override
    public void onEvent(CqEvent event) {
        TripStat stat = (TripStat) event.getNewValue();
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Trip.DATE_FORMAT);
        sb.append("pickup:" + dateFormat.format(stat.getPickupDatetime()) + ", dropoff:" + dateFormat.format(stat.getDropoffDatetime()) + ", ");

        query(sb);

        System.out.println(sb);
    }


    private void query(StringBuilder sb) {
        String queryString = "select distinct * from /TripStat ORDER BY counter desc limit 10";
        QueryService queryService = cache.getRegion("TripStat").getRegionService().getQueryService();
        Query query = queryService.newQuery(queryString);
        SelectResults<TripStat> results = null;
        try {
            results = (SelectResults) query.execute();
        } catch (FunctionDomainException e) {
            e.printStackTrace();
        } catch (TypeMismatchException e) {
            e.printStackTrace();
        } catch (NameResolutionException e) {
            e.printStackTrace();
        } catch (QueryInvocationTargetException e) {
            e.printStackTrace();
        }

        sb.append("cell ranking:");
        int i=0;
        for (TripStat o : results) {
            sb.append("[" + o.getStartCell() + ", " + o.getEndCell() + "] ");
            i++;
        }

        for(;i<10;i++){
            sb.append("[NULL] ");
        }

    }

}

