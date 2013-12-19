package com.isummon.model;

/**
 * Created by horzwxy on 12/19/13.
 */
public class Contact {
    private int targetId;
    private String targetName;
    private int targetAvatar;

    public Contact(int targetId, String targetName, int targetAvatar) {
        this.targetId = targetId;
        this.targetName = targetName;
        this.targetAvatar = targetAvatar;
    }

    public int getTargetId() {
        return targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public int getTargetAvatar() {
        return targetAvatar;
    }
}
