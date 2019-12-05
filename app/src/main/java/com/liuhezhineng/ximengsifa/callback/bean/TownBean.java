package com.liuhezhineng.ximengsifa.callback.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lishangnan on 2019/11/14.
 */

public class TownBean implements Serializable {

    /**
     * status : 0
     * msg :
     * body : [{"isParent":true,"name":"镶黄旗司法局行政复议与应诉股","pId":"0","id":"f321dbfc67f54c11a45817e0aa5e5b27","pIds":"0,"},{"isParent":true,"name":"苏尼特右旗司法局法制股","pId":"0","id":"6512e18f8fdc4f56a7c509d356f03f21","pIds":"0,"},{"isParent":true,"name":"东乌旗司法局法制股","pId":"0","id":"182945156c6e456e878079643c504067","pIds":"0,"},{"isParent":true,"name":"西乌珠穆沁旗司法局行政复议与应诉科","pId":"0","id":"6cd48eb9338c45379457c90cf1422e1c","pIds":"0,"},{"isParent":true,"name":"锡林浩特市司法局行政复议与应诉股","pId":"0","id":"76e521a4786749a8af6600f28e7e1df0","pIds":"0,"},{"isParent":true,"name":"多伦县司法局行政复议与应诉科","pId":"0","id":"4f6d3786bc9b46e4b8f7ae8c5a9e4848","pIds":"0,"},{"isParent":true,"name":"锡盟司法局行政复议与应诉科","pId":"0","id":"60e62bc88fee4ccd8f0d2233b4d28a83","pIds":"0,"},{"isParent":true,"name":"二连浩特市司法局行政复议与应诉科","pId":"0","id":"263ee087d84540e886d22c16f0d26814","pIds":"0,"},{"isParent":true,"name":"正蓝旗司法局法制办公室","pId":"0","id":"ead747be354245f585d8a274f25e650b","pIds":"0,"},{"isParent":true,"name":"苏尼特左旗司法局执法监督与应诉科","pId":"0","id":"a97e4d787e8b4a5a9473d3ecbf3e6a83","pIds":"0,"},{"isParent":true,"name":"乌拉盖管理区司法局法制办公室","pId":"0","id":"984e53b55fa14c08a75e330492f4d7c2","pIds":"0,"},{"isParent":true,"name":"太仆寺旗司法局行政复议与应诉科","pId":"0","id":"826611c9bd39494ea72efb42a75bf79d","pIds":"0,"},{"isParent":true,"name":"正镶白旗司法局行政复议与应诉科","pId":"0","id":"3ce4be9cb38b4380b3893bebd55e9ee2","pIds":"0,"},{"isParent":true,"name":"阿巴嘎旗司法局行政复议与应诉股","pId":"0","id":"1c7ee0a90142465e88ffb36d6edbcfa1","pIds":"0,"}]
     */

    private int status;
    private String msg;
    private List<BodyBean> body;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }


    public static class BodyBean {
        /**
         * isParent : true
         * name : 镶黄旗司法局行政复议与应诉股
         * pId : 0
         * id : f321dbfc67f54c11a45817e0aa5e5b27
         * pIds : 0,
         */

        private boolean isParent;
        private String name;
        private String pId;
        private String id;
        private String pIds;

        public boolean isIsParent() {
            return isParent;
        }

        public void setIsParent(boolean isParent) {
            this.isParent = isParent;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPId() {
            return pId;
        }

        public void setPId(String pId) {
            this.pId = pId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPIds() {
            return pIds;
        }

        public void setPIds(String pIds) {
            this.pIds = pIds;
        }
    }
}
