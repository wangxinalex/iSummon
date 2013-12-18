package com.isummon.model;

/**
 * Created by horzwxy on 12/15/13.
 */
public enum HDStatus {
    NO_VACANCY("名额已满"),
    CANCELED("已取消"),
    OPEN("正在招募受骗者");

    private String chn;

    HDStatus(String chn) {
        this.chn = chn;
    }

    public String getChn() {
        return chn;
    }

    public static String[] getChns() {
        HDStatus[] values = values();
        String[] result = new String[values.length];
        for(int i = 0; i < result.length; i++) {
            result[i] = values[i].getChn();
        }
        return result;
    }
}
