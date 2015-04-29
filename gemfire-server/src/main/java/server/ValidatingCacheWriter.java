package server;

import com.gemstone.gemfire.cache.CacheWriterException;
import com.gemstone.gemfire.cache.Declarable;
import com.gemstone.gemfire.cache.EntryEvent;
import com.gemstone.gemfire.cache.util.CacheWriterAdapter;
import domain.Trip;

import java.util.Properties;

/**
 * This implementation provides a specific function for all new BookMaster entries. It validates that there are
 * no other entries having the same itemNumber. This in theory would ensure someone doesn't mistakenly try to create
 * two books with the same item number.
 * <p/>
 * This implementation has factored out the validation part from the handling of invalid entries. It's the job of
 * the CacheWriter method(s) to extract the value and region to validate and to handle an invalid case. It's the
 * job of validateNewValue to handle the server of determining if the object in question is valid.
 */
public class ValidatingCacheWriter extends CacheWriterAdapter<String, Trip> implements Declarable {

    @Override
    public void beforeCreate(EntryEvent<String, Trip> event) throws CacheWriterException {

    }

    @Override
    public void beforeUpdate(EntryEvent<String, Trip> event) throws CacheWriterException {

    }


    @Override
    public void init(Properties props) {

    }


}
