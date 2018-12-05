package in.cleartax.dropwizard.sharding.transactions;

import java.lang.annotation.*;

/**
 * Created by mohitsingh on 05/12/18.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReadOnlyReplicaIdentifier {
    boolean useDefault();

    //Enum : strategy to identify readReplica like RoundRobin
}
