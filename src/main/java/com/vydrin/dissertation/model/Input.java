package com.vydrin.dissertation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Input implements Serializable {
    private String script;
    private PrevOut prev_out;

    @Override
    public String toString() {
        return "Input{" +
                "script='" + script + '\'' +
                ", prev_out=" + prev_out +
                '}';
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public PrevOut getPrev_out() {
        return prev_out;
    }

    public void setPrev_out(PrevOut prev_out) {
        this.prev_out = prev_out;
    }

    static class PrevOut{
        private String hash;
        private String value;
        private String tx_index;
        private String n;

        @Override
        public String toString() {
            return "PrevOut{" +
                    "hash='" + hash + '\'' +
                    ", value='" + value + '\'' +
                    ", tx_index='" + tx_index + '\'' +
                    ", n='" + n + '\'' +
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

        public String getTx_index() {
            return tx_index;
        }

        public void setTx_index(String tx_index) {
            this.tx_index = tx_index;
        }

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }
    }
}
