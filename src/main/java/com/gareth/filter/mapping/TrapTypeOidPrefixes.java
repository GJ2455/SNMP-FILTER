package com.gareth.filter.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrapTypeOidPrefixes {

    @JsonProperty("trap-type-oid-prefix")
    private Set<String> prefixes;

    public Set<String> getTrapTypeOidPrefix() {
        return prefixes;
    }
}