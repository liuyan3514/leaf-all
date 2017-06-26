package com.github.stone.leaf.server.enums;

/**
 * leaf message
 *
 * @author stone
 */
public enum LeafMessage {

    ;

    private final String message;

    private LeafMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
