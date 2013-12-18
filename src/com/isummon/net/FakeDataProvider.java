package com.isummon.net;

import com.isummon.model.HDStatus;
import com.isummon.model.HDType;
import com.isummon.model.SimpleHDActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by horzwxy on 12/18/13.
 */
public class FakeDataProvider {

    public static List<SimpleHDActivity> getSimpleHDActivities() {
        ArrayList<SimpleHDActivity> result = new ArrayList<SimpleHDActivity>();

        SimpleHDActivity r1 = new SimpleHDActivity(
                1,
                "我们去加班吧！！！！",
                1,
                "horzwxy",
                120000000d,
                35000000d,
                HDType.ENTERTAINMENT,
                HDStatus.NO_VACANCY);
        result.add(r1);

        SimpleHDActivity r2 = new SimpleHDActivity(
                2,
                "我去炸学校，天天不迟到",
                2,
                "罗玉凤",
                120000000d,
                35000000d,
                HDType.SPORT,
                HDStatus.CANCELED);
        result.add(r2);

        SimpleHDActivity r3 = new SimpleHDActivity(
                3,
                "入党",
                3,
                "陈水扁",
                120000000d,
                35000000d,
                HDType.STUDY,
                HDStatus.OPEN);
        result.add(r3);

        SimpleHDActivity r4 = new SimpleHDActivity(
                4,
                "只卖998",
                4,
                "计算机院长",
                120000000d,
                35000000d,
                HDType.OTHER,
                HDStatus.OPEN);
        result.add(r4);

        SimpleHDActivity r5 = new SimpleHDActivity(
                5,
                "项目管理presentation",
                5,
                "某老师",
                120000000d,
                35000000d,
                HDType.DINING,
                HDStatus.NO_VACANCY);
        result.add(r5);
        return result;
    }
}
