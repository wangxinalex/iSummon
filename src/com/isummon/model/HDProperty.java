package com.isummon.model;

/**
 * Created by horzwxy on 12/15/13.
 */
public enum HDProperty {
    SECRET("绝密活动"),
    ANYTIME_OPEN("随时欢迎加入"),
    OTHER("无");

    private String chn;

    HDProperty(String chn) {
        this.chn = chn;
    }

    public String getChn() {
        return this.chn;
    }

    public static String[] getChns() {
        HDProperty[] values = values();
        String[] result = new String[values.length];
        for(int i = 0; i < result.length; i++) {
            result[i] = values[i].getChn();
        }
        return result;
    }
}
