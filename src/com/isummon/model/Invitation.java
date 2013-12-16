package com.isummon.model;

/**
 * Created by horzwxy on 12/16/13.
 */
public class Invitation {
    private int originId;
    private int targetId;
    private int hdId;

    public Invitation(int originId, int targetId, int hdId) {
        this.originId = originId;
        this.targetId = targetId;
        this.hdId = hdId;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public int getHdId() {
        return hdId;
    }
}
