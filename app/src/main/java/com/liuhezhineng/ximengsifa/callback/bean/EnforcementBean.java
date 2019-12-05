package com.liuhezhineng.ximengsifa.callback.bean;

import java.util.List;

/**
 * Created by lishangnan on 2019/11/13.
 */

public class EnforcementBean {


    /**
     * status : 0
     * msg :
     * body : [{"id":"295c6539318146d4851b7a52f9f13b69","name":"行政主体基本信息","logo":"","serverType":"1","pid":"637ad86ddd884dd4b3591a316eb7c644","pname":"行政执法公示专栏","link":"","leve":3,"sourceId":"96ad72db678540f29f6d6fd7cd50c630"},{"id":"062f5165505447adb7d06dd9aa012a3c","name":"行政权责清单","logo":"","serverType":"1","pid":"637ad86ddd884dd4b3591a316eb7c644","pname":"行政执法公示专栏","link":"","leve":3,"sourceId":"ab793caec9f64acfa9190b84c3674235"},{"id":"bd03099efb1945658d0c2638411be121","name":"行政执法事前公开","logo":"","serverType":"1","pid":"637ad86ddd884dd4b3591a316eb7c644","pname":"行政执法公示专栏","link":"","leve":3,"sourceId":"8b1108ceb5f04b618decc6570d65c161"},{"id":"3179e1310d604bf8839089b944fb822e","name":"行政执法事中公开","logo":"","serverType":"1","pid":"637ad86ddd884dd4b3591a316eb7c644","pname":"行政执法公示专栏","link":"","leve":3,"sourceId":"0608b65a7b1d4b0a9b977e235ced4477"},{"id":"592d0853e9ac4935981541395f925530","name":"行政执法事后公开","logo":"","serverType":"1","pid":"637ad86ddd884dd4b3591a316eb7c644","pname":"行政执法公示专栏","link":"","leve":3,"sourceId":"09c2bb3a1498441691f9f46b899eb354"}]
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
         * id : 295c6539318146d4851b7a52f9f13b69
         * name : 行政主体基本信息
         * logo :
         * serverType : 1
         * pid : 637ad86ddd884dd4b3591a316eb7c644
         * pname : 行政执法公示专栏
         * link :
         * leve : 3
         * sourceId : 96ad72db678540f29f6d6fd7cd50c630
         */

        private String id;
        private String name;
        private String logo;
        private String serverType;
        private String pid;
        private String pname;
        private String link;
        private int leve;
        private String sourceId;

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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getServerType() {
            return serverType;
        }

        public void setServerType(String serverType) {
            this.serverType = serverType;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getLeve() {
            return leve;
        }

        public void setLeve(int leve) {
            this.leve = leve;
        }

        public String getSourceId() {
            return sourceId;
        }

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }
    }
}
