package com.vydrin.dissertation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Out implements Serializable {
    private String hash;
    private String value;
    private String scripts;

    @Override
    public String toString() {
        return "Out{" +
                "hash='" + hash + '\'' +
                ", value='" + value + '\'' +
                ", scripts='" + scripts + '\'' +
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

    public String getScripts() {
        return scripts;
    }

    public void setScripts(String scripts) {
        this.scripts = scripts;
    }
}
