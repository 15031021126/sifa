package com.liuhezhineng.ximengsifa.callback.bean;

/**
 * Created by lishangnan on 2019/11/14.
 */

public class LegNoAgree {


    /**
     * act : {"procInsId":"7173200990804914a42aa5bfb37662a4","procDefId":"reconsider_apply:2:557d34bff8eb419983afe9650a480a72","procDefKey":"reconsider_apply","taskId":"557d34bff8eb419983afe9650a480a7","taskName":"复议机构审核","taskDefKey":"reconsider_approve","flag":"yes"}
     * id :
     * applyId :
     * title :
     * symbol :
     * reason :
     * requiredMaterials :
     */

    private ActBean act;
    private String id;
    private String applyId;
    private String title;
    private String symbol;
    private String reason;
    private String requiredMaterials;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRequiredMaterials() {
        return requiredMaterials;
    }

    public void setRequiredMaterials(String requiredMaterials) {
        this.requiredMaterials = requiredMaterials;
    }

    public static class ActBean {
        /**
         * procInsId : 7173200990804914a42aa5bfb37662a4
         * procDefId : reconsider_apply:2:557d34bff8eb419983afe9650a480a72
         * procDefKey : reconsider_apply
         * taskId : 557d34bff8eb419983afe9650a480a7
         * taskName : 复议机构审核
         * taskDefKey : reconsider_approve
         * flag : yes
         */

        private String procInsId;
        private String procDefId;
        private String procDefKey;
        private String taskId;
        private String taskName;
        private String taskDefKey;
        private String flag;

        public String getProcInsId() {
            return procInsId;
        }

        public void setProcInsId(String procInsId) {
            this.procInsId = procInsId;
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

        public String getTaskDefKey() {
            return taskDefKey;
        }

        public void setTaskDefKey(String taskDefKey) {
            this.taskDefKey = taskDefKey;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }
}