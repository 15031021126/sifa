package com.liuhezhineng.ximengsifa.callback.bean;

import java.io.Serializable;

/**
 * Created by lishangnan on 2019/11/15.
 */

public class Accept  implements Serializable {


    /**
     * act : {"flag":"yes","procDefId":"reconsider_apply:4:0f2560c9706a423f858d55f6b175ff04","procDefKey":"reconsider_apply","procInsId":"bdcc3bf39ae143228b382652a97f3939","taskDefKey":"reconsider_approve","taskId":"6544f7c399c34c6da202f52896a6b18b","taskName":"复议机构审核"}
     * id : f140bc863ab340bc9c8e1b6643aab414
     */

    private ActBean act;
    private String applyId;
    private String title;
    private String symbol;
    private String id;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public ActBean getAct() {
        return act;
    }

    public void setAct(ActBean act) {
        this.act = act;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class ActBean  implements Serializable {
        /**
         * flag : yes
         * procDefId : reconsider_apply:4:0f2560c9706a423f858d55f6b175ff04
         * procDefKey : reconsider_apply
         * procInsId : bdcc3bf39ae143228b382652a97f3939
         * taskDefKey : reconsider_approve
         * taskId : 6544f7c399c34c6da202f52896a6b18b
         * taskName : 复议机构审核
         */

        private String flag;
        private String procDefId;
        private String procDefKey;
        private String procInsId;
        private String taskDefKey;
        private String taskId;
        private String taskName;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getProcDefId() {
            return procDefId;
        }

        public void setProcDefId(String procDefId) {
            this.procDefId = procDefId;
        }

        public String getProcDefKey() {
            return procDefKey;
        }

        public void setProcDefKey(String procDefKey) {
            this.procDefKey = procDefKey;
        }

        public String getProcInsId() {
            return procInsId;
        }

        public void setProcInsId(String procInsId) {
            this.procInsId = procInsId;
        }

        public String getTaskDefKey() {
            return taskDefKey;
        }

        public void setTaskDefKey(String taskDefKey) {
            this.taskDefKey = taskDefKey;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }
    }
}
