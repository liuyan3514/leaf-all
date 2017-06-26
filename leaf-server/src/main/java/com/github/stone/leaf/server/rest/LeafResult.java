package com.github.stone.leaf.server.rest;

import com.github.stone.leaf.server.enums.LeafMessage;

/**
 * leaf rest result
 *
 * @author stone
 */
public class LeafResult {
    
    private final Object data;

    private final String code;

    private final String message;

    public LeafResult(LeafMessage message) {
        this(null, message);
    }

    public LeafResult(Object data, LeafMessage message) {
        this.data = data;
        this.code = message.name();
        this.message = message.getMessage();
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

}
