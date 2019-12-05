package com.liuhezhineng.ximengsifa.callback;

import com.liuhezhineng.ximengsifa.bean.bussiness.Task;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lishangnan on 2019/11/19.
 */

public class UpdataBean  implements Serializable{

    /**
     * status : 0
     * msg :
     * body : {"procInsId":"d3a67e7fd4164d659c413b283aaed9d2","procDefId":"fast_legal:7:6eb2475f1a074a8089333e20215ab08c","procDefKey":"fast_legal","taskId":"50a092c86bb5449081c147bfa625abce","taskName":"申请人修改","taskDefKey":"apply_update","businessTable":"oa_fast_legal","businessId":"36e5ede94c6b4b6b93203d0b7cb7ddde","title":"","task":"","assignee":"","assigneeName":"","procDefName":"","vars":"","businessData":{"id":"36e5ede94c6b4b6b93203d0b7cb7ddde","remarks":"","createBy":{"id":"2bc42c7fc1ee42a983e3dac529aa5b9b","name":"","loginFlag":"1","loginCount":0,"userExpand":{"id":""},"area":{"id":"","name":"","sort":30,"parentId":"0"},"townarea":{"id":"","name":"","sort":30,"parentId":"0"},"admin":false,"roleNames":"","roleOfficeIdList":""},"createDate":"2019-11-18 09:36:13","updateDate":"2019-11-18 09:46:09","evaluation":"","warningState":"","warningTime":"","warningCountDown":"","userIds":"","procInsId":"d3a67e7fd4164d659c413b283aaed9d2","acceptManName":"12348服务人员1","acceptManCode":"","caseAcceptCode":"2019111893600001","accuserName":"李尚南","accuserSex":"1","accuserEthnic":"","accuserBirthday":"1991-01-10","accuserPhone":"13521257475","accuserIdCard":"130627199101105614","accuserCounty":{"id":"","name":"","sort":30,"parentId":"0"},"accuserTown":{"id":"","name":"","sort":30,"parentId":"0"},"caseClassify":"apply_lawyer","caseType":"29","caseCounty":{"id":"5","name":"锡林郭勒盟","sort":30,"parentId":"0"},"caseTown":{"id":"5","name":"锡林郭勒盟","sort":30,"parentId":"0"},"caseTitle":"测试测试测试测试测试","caseReason":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","caseTime":"2019-11-18","caseInvolvecount":"4","caseSource":"","caseInvolveMoney":"1","caseRank":"","office":{"id":"","name":"","sort":30,"parentId":"0"},"transactor":{"id":"","name":"","loginFlag":"1","loginCount":0,"userExpand":{"id":""},"area":{"id":"","name":"","sort":30,"parentId":"0"},"townarea":{"id":"","name":"","sort":30,"parentId":"0"},"admin":false,"roleNames":"","roleOfficeIdList":""},"handleResult":"","caseFile":"","caseClassifyDesc":"律师服务","caseTypeDesc":"刑事","caseSourceDesc":"","caseRankDesc":"","accuserSexDesc":"男","accuserEthnicDesc":"","caseInvolveMoneyDesc":"0-5000","isSubmit":"","qrcode":"","voicePath":"","eachVoicePath":"","vidyoRoomId":"","vidyoRoomUrl":"","staffOffice":{"id":"","name":"","sort":30,"parentId":"0"},"caseAddress":"测试测试测试","isTransferPenalty":"","isPetition":"","isAffectStability":"","personnelType":"0","actualAmount":"10","isTransferPenaltyDesc":"","isPetitionDesc":"","isAffectStabilityDesc":""},"version":"","commentId":"36e5ede94c6b4b6b93203d0b7cb7ddde","isEvaluate":"","evaluatedList":[]}
     */

    private int status;
    private String msg;
    private BodyBean body;

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

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean implements  Serializable{
        /**
         * procInsId : d3a67e7fd4164d659c413b283aaed9d2
         * procDefId : fast_legal:7:6eb2475f1a074a8089333e20215ab08c
         * procDefKey : fast_legal
         * taskId : 50a092c86bb5449081c147bfa625abce
         * taskName : 申请人修改
         * taskDefKey : apply_update
         * businessTable : oa_fast_legal
         * businessId : 36e5ede94c6b4b6b93203d0b7cb7ddde
         * title :
         * task :
         * assignee :
         * assigneeName :
         * procDefName :
         * vars :
         * businessData : {"id":"36e5ede94c6b4b6b93203d0b7cb7ddde","remarks":"","createBy":{"id":"2bc42c7fc1ee42a983e3dac529aa5b9b","name":"","loginFlag":"1","loginCount":0,"userExpand":{"id":""},"area":{"id":"","name":"","sort":30,"parentId":"0"},"townarea":{"id":"","name":"","sort":30,"parentId":"0"},"admin":false,"roleNames":"","roleOfficeIdList":""},"createDate":"2019-11-18 09:36:13","updateDate":"2019-11-18 09:46:09","evaluation":"","warningState":"","warningTime":"","warningCountDown":"","userIds":"","procInsId":"d3a67e7fd4164d659c413b283aaed9d2","acceptManName":"12348服务人员1","acceptManCode":"","caseAcceptCode":"2019111893600001","accuserName":"李尚南","accuserSex":"1","accuserEthnic":"","accuserBirthday":"1991-01-10","accuserPhone":"13521257475","accuserIdCard":"130627199101105614","accuserCounty":{"id":"","name":"","sort":30,"parentId":"0"},"accuserTown":{"id":"","name":"","sort":30,"parentId":"0"},"caseClassify":"apply_lawyer","caseType":"29","caseCounty":{"id":"5","name":"锡林郭勒盟","sort":30,"parentId":"0"},"caseTown":{"id":"5","name":"锡林郭勒盟","sort":30,"parentId":"0"},"caseTitle":"测试测试测试测试测试","caseReason":"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","caseTime":"2019-11-18","caseInvolvecount":"4","caseSource":"","caseInvolveMoney":"1","caseRank":"","office":{"id":"","name":"","sort":30,"parentId":"0"},"transactor":{"id":"","name":"","loginFlag":"1","loginCount":0,"userExpand":{"id":""},"area":{"id":"","name":"","sort":30,"parentId":"0"},"townarea":{"id":"","name":"","sort":30,"parentId":"0"},"admin":false,"roleNames":"","roleOfficeIdList":""},"handleResult":"","caseFile":"","caseClassifyDesc":"律师服务","caseTypeDesc":"刑事","caseSourceDesc":"","caseRankDesc":"","accuserSexDesc":"男","accuserEthnicDesc":"","caseInvolveMoneyDesc":"0-5000","isSubmit":"","qrcode":"","voicePath":"","eachVoicePath":"","vidyoRoomId":"","vidyoRoomUrl":"","staffOffice":{"id":"","name":"","sort":30,"parentId":"0"},"caseAddress":"测试测试测试","isTransferPenalty":"","isPetition":"","isAffectStability":"","personnelType":"0","actualAmount":"10","isTransferPenaltyDesc":"","isPetitionDesc":"","isAffectStabilityDesc":""}
         * version :
         * commentId : 36e5ede94c6b4b6b93203d0b7cb7ddde
         * isEvaluate :
         * evaluatedList : []
         */

        private String procInsId;
        private String procDefId;
        private String procDefKey;
        private String taskId;
        private String taskName;
        private String taskDefKey;
        private String businessTable;
        private String businessId;
        private String title;
        private String assignee;
        private String assigneeName;
        private String procDefName;
        private String vars;
        private BusinessDataBean businessData;
        private String version;
        private String commentId;
        private String isEvaluate;
        private List<?> evaluatedList;

        private String task;

        public void setTask(String task) {
            this.task = task;
        }

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

        public String getBusinessTable() {
            return businessTable;
        }

        public void setBusinessTable(String businessTable) {
            this.businessTable = businessTable;
        }

        public String getBusinessId() {
            return businessId;
        }

        public void setBusinessId(String businessId) {
            this.businessId = businessId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public String getAssignee() {
            return assignee;
        }

        public void setAssignee(String assignee) {
            this.assignee = assignee;
        }

        public String getAssigneeName() {
            return assigneeName;
        }

        public void setAssigneeName(String assigneeName) {
            this.assigneeName = assigneeName;
        }

        public String getProcDefName() {
            return procDefName;
        }

        public void setProcDefName(String procDefName) {
            this.procDefName = procDefName;
        }

        public String getVars() {
            return vars;
        }

        public void setVars(String vars) {
            this.vars = vars;
        }

        public BusinessDataBean getBusinessData() {
            return businessData;
        }

        public void setBusinessData(BusinessDataBean businessData) {
            this.businessData = businessData;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public String getIsEvaluate() {
            return isEvaluate;
        }

        public void setIsEvaluate(String isEvaluate) {
            this.isEvaluate = isEvaluate;
        }

        public List<?> getEvaluatedList() {
            return evaluatedList;
        }

        public void setEvaluatedList(List<?> evaluatedList) {
            this.evaluatedList = evaluatedList;
        }

        public static class BusinessDataBean  implements Serializable{
            /**
             * id : 36e5ede94c6b4b6b93203d0b7cb7ddde
             * remarks :
             * createBy : {"id":"2bc42c7fc1ee42a983e3dac529aa5b9b","name":"","loginFlag":"1","loginCount":0,"userExpand":{"id":""},"area":{"id":"","name":"","sort":30,"parentId":"0"},"townarea":{"id":"","name":"","sort":30,"parentId":"0"},"admin":false,"roleNames":"","roleOfficeIdList":""}
             * createDate : 2019-11-18 09:36:13
             * updateDate : 2019-11-18 09:46:09
             * evaluation :
             * warningState :
             * warningTime :
             * warningCountDown :
             * userIds :
             * procInsId : d3a67e7fd4164d659c413b283aaed9d2
             * acceptManName : 12348服务人员1
             * acceptManCode :
             * caseAcceptCode : 2019111893600001
             * accuserName : 李尚南
             * accuserSex : 1
             * accuserEthnic :
             * accuserBirthday : 1991-01-10
             * accuserPhone : 13521257475
             * accuserIdCard : 130627199101105614
             * accuserCounty : {"id":"","name":"","sort":30,"parentId":"0"}
             * accuserTown : {"id":"","name":"","sort":30,"parentId":"0"}
             * caseClassify : apply_lawyer
             * caseType : 29
             * caseCounty : {"id":"5","name":"锡林郭勒盟","sort":30,"parentId":"0"}
             * caseTown : {"id":"5","name":"锡林郭勒盟","sort":30,"parentId":"0"}
             * caseTitle : 测试测试测试测试测试
             * caseReason : 测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试
             * caseTime : 2019-11-18
             * caseInvolvecount : 4
             * caseSource :
             * caseInvolveMoney : 1
             * caseRank :
             * office : {"id":"","name":"","sort":30,"parentId":"0"}
             * transactor : {"id":"","name":"","loginFlag":"1","loginCount":0,"userExpand":{"id":""},"area":{"id":"","name":"","sort":30,"parentId":"0"},"townarea":{"id":"","name":"","sort":30,"parentId":"0"},"admin":false,"roleNames":"","roleOfficeIdList":""}
             * handleResult :
             * caseFile :
             * caseClassifyDesc : 律师服务
             * caseTypeDesc : 刑事
             * caseSourceDesc :
             * caseRankDesc :
             * accuserSexDesc : 男
             * accuserEthnicDesc :
             * caseInvolveMoneyDesc : 0-5000
             * isSubmit :
             * qrcode :
             * voicePath :
             * eachVoicePath :
             * vidyoRoomId :
             * vidyoRoomUrl :
             * staffOffice : {"id":"","name":"","sort":30,"parentId":"0"}
             * caseAddress : 测试测试测试
             * isTransferPenalty :
             * isPetition :
             * isAffectStability :
             * personnelType : 0
             * actualAmount : 10
             * isTransferPenaltyDesc :
             * isPetitionDesc :
             * isAffectStabilityDesc :
             */

            private String id;
            private String remarks;
            private CreateByBean createBy;
            private String createDate;
            private String updateDate;
            private String evaluation;
            private String warningState;
            private String warningTime;
            private String warningCountDown;
            private String userIds;
            private String procInsId;
            private String acceptManName;
            private String acceptManCode;
            private String caseAcceptCode;
            private String accuserName;
            private String accuserSex;
            private String accuserEthnic;
            private String accuserBirthday;
            private String accuserPhone;
            private String accuserIdCard;
            private AccuserCountyBean accuserCounty;
            private AccuserTownBean accuserTown;
            private String caseClassify;
            private String caseType;
            private CaseCountyBean caseCounty;
            private CaseTownBean caseTown;
            private String caseTitle;
            private String caseReason;
            private String caseTime;
            private String caseInvolvecount;
            private String caseSource;
            private String caseInvolveMoney;
            private String caseRank;
            private OfficeBean office;
            private TransactorBean transactor;
            private String handleResult;
            private String caseFile;
            private String caseClassifyDesc;
            private String caseTypeDesc;
            private String caseSourceDesc;
            private String caseRankDesc;
            private String accuserSexDesc;
            private String accuserEthnicDesc;
            private String caseInvolveMoneyDesc;
            private String isSubmit;
            private String qrcode;
            private String voicePath;
            private String eachVoicePath;
            private String vidyoRoomId;
            private String vidyoRoomUrl;
            private StaffOfficeBean staffOffice;
            private String caseAddress;
            private String isTransferPenalty;
            private String isPetition;
            private String isAffectStability;
            private String personnelType;
            private String actualAmount;
            private String isTransferPenaltyDesc;
            private String isPetitionDesc;
            private String isAffectStabilityDesc;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public CreateByBean getCreateBy() {
                return createBy;
            }

            public void setCreateBy(CreateByBean createBy) {
                this.createBy = createBy;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public String getEvaluation() {
                return evaluation;
            }

            public void setEvaluation(String evaluation) {
                this.evaluation = evaluation;
            }

            public String getWarningState() {
                return warningState;
            }

            public void setWarningState(String warningState) {
                this.warningState = warningState;
            }

            public String getWarningTime() {
                return warningTime;
            }

            public void setWarningTime(String warningTime) {
                this.warningTime = warningTime;
            }

            public String getWarningCountDown() {
                return warningCountDown;
            }

            public void setWarningCountDown(String warningCountDown) {
                this.warningCountDown = warningCountDown;
            }

            public String getUserIds() {
                return userIds;
            }

            public void setUserIds(String userIds) {
                this.userIds = userIds;
            }

            public String getProcInsId() {
                return procInsId;
            }

            public void setProcInsId(String procInsId) {
                this.procInsId = procInsId;
            }

            public String getAcceptManName() {
                return acceptManName;
            }

            public void setAcceptManName(String acceptManName) {
                this.acceptManName = acceptManName;
            }

            public String getAcceptManCode() {
                return acceptManCode;
            }

            public void setAcceptManCode(String acceptManCode) {
                this.acceptManCode = acceptManCode;
            }

            public String getCaseAcceptCode() {
                return caseAcceptCode;
            }

            public void setCaseAcceptCode(String caseAcceptCode) {
                this.caseAcceptCode = caseAcceptCode;
            }

            public String getAccuserName() {
                return accuserName;
            }

            public void setAccuserName(String accuserName) {
                this.accuserName = accuserName;
            }

            public String getAccuserSex() {
                return accuserSex;
            }

            public void setAccuserSex(String accuserSex) {
                this.accuserSex = accuserSex;
            }

            public String getAccuserEthnic() {
                return accuserEthnic;
            }

            public void setAccuserEthnic(String accuserEthnic) {
                this.accuserEthnic = accuserEthnic;
            }

            public String getAccuserBirthday() {
                return accuserBirthday;
            }

            public void setAccuserBirthday(String accuserBirthday) {
                this.accuserBirthday = accuserBirthday;
            }

            public String getAccuserPhone() {
                return accuserPhone;
            }

            public void setAccuserPhone(String accuserPhone) {
                this.accuserPhone = accuserPhone;
            }

            public String getAccuserIdCard() {
                return accuserIdCard;
            }

            public void setAccuserIdCard(String accuserIdCard) {
                this.accuserIdCard = accuserIdCard;
            }

            public AccuserCountyBean getAccuserCounty() {
                return accuserCounty;
            }

            public void setAccuserCounty(AccuserCountyBean accuserCounty) {
                this.accuserCounty = accuserCounty;
            }

            public AccuserTownBean getAccuserTown() {
                return accuserTown;
            }

            public void setAccuserTown(AccuserTownBean accuserTown) {
                this.accuserTown = accuserTown;
            }

            public String getCaseClassify() {
                return caseClassify;
            }

            public void setCaseClassify(String caseClassify) {
                this.caseClassify = caseClassify;
            }

            public String getCaseType() {
                return caseType;
            }

            public void setCaseType(String caseType) {
                this.caseType = caseType;
            }

            public CaseCountyBean getCaseCounty() {
                return caseCounty;
            }

            public void setCaseCounty(CaseCountyBean caseCounty) {
                this.caseCounty = caseCounty;
            }

            public CaseTownBean getCaseTown() {
                return caseTown;
            }

            public void setCaseTown(CaseTownBean caseTown) {
                this.caseTown = caseTown;
            }

            public String getCaseTitle() {
                return caseTitle;
            }

            public void setCaseTitle(String caseTitle) {
                this.caseTitle = caseTitle;
            }

            public String getCaseReason() {
                return caseReason;
            }

            public void setCaseReason(String caseReason) {
                this.caseReason = caseReason;
            }

            public String getCaseTime() {
                return caseTime;
            }

            public void setCaseTime(String caseTime) {
                this.caseTime = caseTime;
            }

            public String getCaseInvolvecount() {
                return caseInvolvecount;
            }

            public void setCaseInvolvecount(String caseInvolvecount) {
                this.caseInvolvecount = caseInvolvecount;
            }

            public String getCaseSource() {
                return caseSource;
            }

            public void setCaseSource(String caseSource) {
                this.caseSource = caseSource;
            }

            public String getCaseInvolveMoney() {
                return caseInvolveMoney;
            }

            public void setCaseInvolveMoney(String caseInvolveMoney) {
                this.caseInvolveMoney = caseInvolveMoney;
            }

            public String getCaseRank() {
                return caseRank;
            }

            public void setCaseRank(String caseRank) {
                this.caseRank = caseRank;
            }

            public OfficeBean getOffice() {
                return office;
            }

            public void setOffice(OfficeBean office) {
                this.office = office;
            }

            public TransactorBean getTransactor() {
                return transactor;
            }

            public void setTransactor(TransactorBean transactor) {
                this.transactor = transactor;
            }

            public String getHandleResult() {
                return handleResult;
            }

            public void setHandleResult(String handleResult) {
                this.handleResult = handleResult;
            }

            public String getCaseFile() {
                return caseFile;
            }

            public void setCaseFile(String caseFile) {
                this.caseFile = caseFile;
            }

            public String getCaseClassifyDesc() {
                return caseClassifyDesc;
            }

            public void setCaseClassifyDesc(String caseClassifyDesc) {
                this.caseClassifyDesc = caseClassifyDesc;
            }

            public String getCaseTypeDesc() {
                return caseTypeDesc;
            }

            public void setCaseTypeDesc(String caseTypeDesc) {
                this.caseTypeDesc = caseTypeDesc;
            }

            public String getCaseSourceDesc() {
                return caseSourceDesc;
            }

            public void setCaseSourceDesc(String caseSourceDesc) {
                this.caseSourceDesc = caseSourceDesc;
            }

            public String getCaseRankDesc() {
                return caseRankDesc;
            }

            public void setCaseRankDesc(String caseRankDesc) {
                this.caseRankDesc = caseRankDesc;
            }

            public String getAccuserSexDesc() {
                return accuserSexDesc;
            }

            public void setAccuserSexDesc(String accuserSexDesc) {
                this.accuserSexDesc = accuserSexDesc;
            }

            public String getAccuserEthnicDesc() {
                return accuserEthnicDesc;
            }

            public void setAccuserEthnicDesc(String accuserEthnicDesc) {
                this.accuserEthnicDesc = accuserEthnicDesc;
            }

            public String getCaseInvolveMoneyDesc() {
                return caseInvolveMoneyDesc;
            }

            public void setCaseInvolveMoneyDesc(String caseInvolveMoneyDesc) {
                this.caseInvolveMoneyDesc = caseInvolveMoneyDesc;
            }

            public String getIsSubmit() {
                return isSubmit;
            }

            public void setIsSubmit(String isSubmit) {
                this.isSubmit = isSubmit;
            }

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }

            public String getVoicePath() {
                return voicePath;
            }

            public void setVoicePath(String voicePath) {
                this.voicePath = voicePath;
            }

            public String getEachVoicePath() {
                return eachVoicePath;
            }

            public void setEachVoicePath(String eachVoicePath) {
                this.eachVoicePath = eachVoicePath;
            }

            public String getVidyoRoomId() {
                return vidyoRoomId;
            }

            public void setVidyoRoomId(String vidyoRoomId) {
                this.vidyoRoomId = vidyoRoomId;
            }

            public String getVidyoRoomUrl() {
                return vidyoRoomUrl;
            }

            public void setVidyoRoomUrl(String vidyoRoomUrl) {
                this.vidyoRoomUrl = vidyoRoomUrl;
            }

            public StaffOfficeBean getStaffOffice() {
                return staffOffice;
            }

            public void setStaffOffice(StaffOfficeBean staffOffice) {
                this.staffOffice = staffOffice;
            }

            public String getCaseAddress() {
                return caseAddress;
            }

            public void setCaseAddress(String caseAddress) {
                this.caseAddress = caseAddress;
            }

            public String getIsTransferPenalty() {
                return isTransferPenalty;
            }

            public void setIsTransferPenalty(String isTransferPenalty) {
                this.isTransferPenalty = isTransferPenalty;
            }

            public String getIsPetition() {
                return isPetition;
            }

            public void setIsPetition(String isPetition) {
                this.isPetition = isPetition;
            }

            public String getIsAffectStability() {
                return isAffectStability;
            }

            public void setIsAffectStability(String isAffectStability) {
                this.isAffectStability = isAffectStability;
            }

            public String getPersonnelType() {
                return personnelType;
            }

            public void setPersonnelType(String personnelType) {
                this.personnelType = personnelType;
            }

            public String getActualAmount() {
                return actualAmount;
            }

            public void setActualAmount(String actualAmount) {
                this.actualAmount = actualAmount;
            }

            public String getIsTransferPenaltyDesc() {
                return isTransferPenaltyDesc;
            }

            public void setIsTransferPenaltyDesc(String isTransferPenaltyDesc) {
                this.isTransferPenaltyDesc = isTransferPenaltyDesc;
            }

            public String getIsPetitionDesc() {
                return isPetitionDesc;
            }

            public void setIsPetitionDesc(String isPetitionDesc) {
                this.isPetitionDesc = isPetitionDesc;
            }

            public String getIsAffectStabilityDesc() {
                return isAffectStabilityDesc;
            }

            public void setIsAffectStabilityDesc(String isAffectStabilityDesc) {
                this.isAffectStabilityDesc = isAffectStabilityDesc;
            }

            public static class CreateByBean implements  Serializable{
                /**
                 * id : 2bc42c7fc1ee42a983e3dac529aa5b9b
                 * name :
                 * loginFlag : 1
                 * loginCount : 0
                 * userExpand : {"id":""}
                 * area : {"id":"","name":"","sort":30,"parentId":"0"}
                 * townarea : {"id":"","name":"","sort":30,"parentId":"0"}
                 * admin : false
                 * roleNames :
                 * roleOfficeIdList :
                 */

                private String id;
                private String name;
                private String loginFlag;
                private int loginCount;
                private UserExpandBean userExpand;
                private AreaBean area;
                private TownareaBean townarea;
                private boolean admin;
                private String roleNames;
                private String roleOfficeIdList;

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

                public String getLoginFlag() {
                    return loginFlag;
                }

                public void setLoginFlag(String loginFlag) {
                    this.loginFlag = loginFlag;
                }

                public int getLoginCount() {
                    return loginCount;
                }

                public void setLoginCount(int loginCount) {
                    this.loginCount = loginCount;
                }

                public UserExpandBean getUserExpand() {
                    return userExpand;
                }

                public void setUserExpand(UserExpandBean userExpand) {
                    this.userExpand = userExpand;
                }

                public AreaBean getArea() {
                    return area;
                }

                public void setArea(AreaBean area) {
                    this.area = area;
                }

                public TownareaBean getTownarea() {
                    return townarea;
                }

                public void setTownarea(TownareaBean townarea) {
                    this.townarea = townarea;
                }

                public boolean isAdmin() {
                    return admin;
                }

                public void setAdmin(boolean admin) {
                    this.admin = admin;
                }

                public String getRoleNames() {
                    return roleNames;
                }

                public void setRoleNames(String roleNames) {
                    this.roleNames = roleNames;
                }

                public String getRoleOfficeIdList() {
                    return roleOfficeIdList;
                }

                public void setRoleOfficeIdList(String roleOfficeIdList) {
                    this.roleOfficeIdList = roleOfficeIdList;
                }

                public static class UserExpandBean {
                    /**
                     * id :
                     */

                    private String id;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }
                }

                public static class AreaBean {
                    /**
                     * id :
                     * name :
                     * sort : 30
                     * parentId : 0
                     */

                    private String id;
                    private String name;
                    private int sort;
                    private String parentId;

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

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
                    }

                    public String getParentId() {
                        return parentId;
                    }

                    public void setParentId(String parentId) {
                        this.parentId = parentId;
                    }
                }

                public static class TownareaBean {
                    /**
                     * id :
                     * name :
                     * sort : 30
                     * parentId : 0
                     */

                    private String id;
                    private String name;
                    private int sort;
                    private String parentId;

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

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
                    }

                    public String getParentId() {
                        return parentId;
                    }

                    public void setParentId(String parentId) {
                        this.parentId = parentId;
                    }
                }
            }

            public static class AccuserCountyBean {
                /**
                 * id :
                 * name :
                 * sort : 30
                 * parentId : 0
                 */

                private String id;
                private String name;
                private int sort;
                private String parentId;

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

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }
            }

            public static class AccuserTownBean {
                /**
                 * id :
                 * name :
                 * sort : 30
                 * parentId : 0
                 */

                private String id;
                private String name;
                private int sort;
                private String parentId;

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

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }
            }

            public static class CaseCountyBean  implements  Serializable{
                /**
                 * id : 5
                 * name : 锡林郭勒盟
                 * sort : 30
                 * parentId : 0
                 */

                private String id;
                private String name;
                private int sort;
                private String parentId;

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

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }
            }

            public static class CaseTownBean implements  Serializable{
                /**
                 * id : 5
                 * name : 锡林郭勒盟
                 * sort : 30
                 * parentId : 0
                 */

                private String id;
                private String name;
                private int sort;
                private String parentId;

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

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }
            }

            public static class OfficeBean {
                /**
                 * id :
                 * name :
                 * sort : 30
                 * parentId : 0
                 */

                private String id;
                private String name;
                private int sort;
                private String parentId;

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

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }
            }

            public static class TransactorBean {
                /**
                 * id :
                 * name :
                 * loginFlag : 1
                 * loginCount : 0
                 * userExpand : {"id":""}
                 * area : {"id":"","name":"","sort":30,"parentId":"0"}
                 * townarea : {"id":"","name":"","sort":30,"parentId":"0"}
                 * admin : false
                 * roleNames :
                 * roleOfficeIdList :
                 */

                private String id;
                private String name;
                private String loginFlag;
                private int loginCount;
                private UserExpandBeanX userExpand;
                private AreaBeanX area;
                private TownareaBeanX townarea;
                private boolean admin;
                private String roleNames;
                private String roleOfficeIdList;

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

                public String getLoginFlag() {
                    return loginFlag;
                }

                public void setLoginFlag(String loginFlag) {
                    this.loginFlag = loginFlag;
                }

                public int getLoginCount() {
                    return loginCount;
                }

                public void setLoginCount(int loginCount) {
                    this.loginCount = loginCount;
                }

                public UserExpandBeanX getUserExpand() {
                    return userExpand;
                }

                public void setUserExpand(UserExpandBeanX userExpand) {
                    this.userExpand = userExpand;
                }

                public AreaBeanX getArea() {
                    return area;
                }

                public void setArea(AreaBeanX area) {
                    this.area = area;
                }

                public TownareaBeanX getTownarea() {
                    return townarea;
                }

                public void setTownarea(TownareaBeanX townarea) {
                    this.townarea = townarea;
                }

                public boolean isAdmin() {
                    return admin;
                }

                public void setAdmin(boolean admin) {
                    this.admin = admin;
                }

                public String getRoleNames() {
                    return roleNames;
                }

                public void setRoleNames(String roleNames) {
                    this.roleNames = roleNames;
                }

                public String getRoleOfficeIdList() {
                    return roleOfficeIdList;
                }

                public void setRoleOfficeIdList(String roleOfficeIdList) {
                    this.roleOfficeIdList = roleOfficeIdList;
                }

                public static class UserExpandBeanX {
                    /**
                     * id :
                     */

                    private String id;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }
                }

                public static class AreaBeanX {
                    /**
                     * id :
                     * name :
                     * sort : 30
                     * parentId : 0
                     */

                    private String id;
                    private String name;
                    private int sort;
                    private String parentId;

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

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
                    }

                    public String getParentId() {
                        return parentId;
                    }

                    public void setParentId(String parentId) {
                        this.parentId = parentId;
                    }
                }

                public static class TownareaBeanX {
                    /**
                     * id :
                     * name :
                     * sort : 30
                     * parentId : 0
                     */

                    private String id;
                    private String name;
                    private int sort;
                    private String parentId;

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

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
                    }

                    public String getParentId() {
                        return parentId;
                    }

                    public void setParentId(String parentId) {
                        this.parentId = parentId;
                    }
                }
            }

            public static class StaffOfficeBean {
                /**
                 * id :
                 * name :
                 * sort : 30
                 * parentId : 0
                 */

                private String id;
                private String name;
                private int sort;
                private String parentId;

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

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }
            }
        }
    }
}
