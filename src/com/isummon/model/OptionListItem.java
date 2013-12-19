package com.isummon.model;

/**
 * Created by horzwxy on 12/19/13.
 */
public enum OptionListItem {

    MANAGE_CONTACT("我的好友");

    public String chn;

    OptionListItem(String chn) {
        this.chn = chn;
    }

    @Override
    public String toString() {
        return this.chn;
    }
}
