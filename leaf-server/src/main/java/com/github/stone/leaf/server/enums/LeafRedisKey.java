package com.github.stone.leaf.server.enums;

/**
 * redis key gen
 *
 * @author stone
 */
public enum LeafRedisKey {

    LEAF_CURRENT("leaf:current");

    private String prefix;

    private LeafRedisKey(String prefix) {
        this.prefix = prefix;
    }

    public String getKey(Object... arguments) {
        if (arguments == null || arguments.length == 0) {
            return prefix;
        }
        StringBuilder sb = new StringBuilder(prefix);
        for (Object argument : arguments) {
            sb.append(":").append(argument);
        }
        return sb.toString();
    }
}
