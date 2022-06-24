package com.gareth.filter.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrefixConfig {

    @JsonProperty("prefix-config")
    private Set<String> prefixes;

    public Set<String> getPrefixes() {
        return prefixes;
    }
}