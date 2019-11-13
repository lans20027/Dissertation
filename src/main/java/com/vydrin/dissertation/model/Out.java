package com.vydrin.dissertation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Out implements Serializable {
    private String hash;
    private String value;
    private String script;

    @Override
    public String toString() {
        return "Out{" +
                "hash='" + hash + '\'' +
                ", value='" + value + '\'' +
                ", scripts='" + script + '\'' +
                '}';
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
