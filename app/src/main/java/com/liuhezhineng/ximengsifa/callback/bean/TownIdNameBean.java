package com.liuhezhineng.ximengsifa.callback.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.io.Serializable;

/**
 * Created by lishangnan on 2019/11/14.
 */

public class TownIdNameBean  implements Serializable,IPickerViewData {
    String id;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
