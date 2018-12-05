package in.cleartax.dropwizard.sharding.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Map;

/**
 * Created by mohitsingh on 05/12/18.
 */
@Getter
@Setter
public class ShardMetaInfo {

    @NotEmpty
    @JsonProperty
    private Map<String, String> readOnlySlaves;

    @NotEmpty
    @JsonProperty
    private Map<String, String> writeOnlySlaves;

    @NotEmpty
    @JsonProperty
    private Map<String, String> readWriteSlaves;

    @NotBlank
    @JsonProperty
    private String defaultReadOnlySlave;

    @NotBlank
    @JsonProperty
    private String defaultWriteOnlySlave;

    @NotBlank
    @JsonProperty
    private String defaultReadWriteSlave;

    @JsonProperty
    public boolean isReadOnlySlaveEnabled;

}
