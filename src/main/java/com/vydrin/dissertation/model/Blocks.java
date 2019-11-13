package com.vydrin.dissertation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Blocks {
    private Block[] blocks;

    @Override
    public String toString() {
        return "Blocks{" +
                "blocks=" + Arrays.toString(blocks) +
                '}';
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }
}
