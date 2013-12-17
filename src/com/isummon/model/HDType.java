package com.isummon.model;

import com.isummon.R;

/**
 * Created by horzwxy on 12/15/13.
 */
public enum HDType {
    SPORT(R.drawable.sport, "运动"),
    STUDY(R.drawable.study, "学习"),
    ENTERTAINMENT(R.drawable.entertainment, "娱乐"),
    DINING(R.drawable.dining, "聚餐"),
    OTHER(R.drawable.other, "其他");

    private int drawableId;
    private String chn;

    HDType(int drawableId, String chn) {
        this.drawableId = drawableId;
        this.chn = chn;
    }

    public int getDrawableId() {
        return this.drawableId;
    }

    public String getChn() {
        return this.chn;
    }

    public static String[] getChns() {
        HDType[] types = values();
        String[] result = new String[types.length];
        for(int i = 0; i < types.length; i++) {
            result[i] = types[i].getChn();
        }
        return result;
    }
}
