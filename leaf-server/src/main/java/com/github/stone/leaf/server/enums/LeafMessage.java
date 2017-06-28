package com.github.stone.leaf.server.enums;

/**
 * leaf message
 *
 * @author stone
 */
public enum LeafMessage {

    SUCCESS("操作成功"),

    NOT_FOUND_LEAF("节点不存在"),;

    private final String message;

    private LeafMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
