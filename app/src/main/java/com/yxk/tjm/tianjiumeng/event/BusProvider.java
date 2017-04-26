package com.yxk.tjm.tianjiumeng.event;

import com.squareup.otto.Bus;

/**
 * Created by ZKT on 2017/4/19.
 */

public class BusProvider {
    private volatile static Bus bus = null;

    private BusProvider() {
    }

    public static Bus getInstance() {
        if (bus == null) {
            synchronized (BusProvider.class) {
                if (bus == null) {
                    bus = new Bus();
                }
            }
        }
        return bus;
    }
}
