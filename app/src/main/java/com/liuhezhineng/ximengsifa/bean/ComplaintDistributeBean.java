package com.liuhezhineng.ximengsifa.bean;

import com.liuhezhineng.ximengsifa.bean.advisory.CreateUser;
import com.liuhezhineng.ximengsifa.bean.base.Base;

/**
 * <pre>
 *     author : qingfeng
 *     e-mail : 1913518036@qq.com
 *     time   : 2019/01/03
 *     desc   : 投诉分配实体类
 *     version: 1.0
 * </pre>
 */
public class ComplaintDistributeBean implements Base {

    /* 投诉建议id */
    private String id;
    /* 处理人，需要其的 id */
    private CreateUser handleUser;

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CreateUser getHandleUser() {
        if (handleUser == null)
            handleUser = new CreateUser();
        return handleUser;
    }

    public void setHandleUser(CreateUser handleUser) {
        this.handleUser = handleUser;
    }
}
