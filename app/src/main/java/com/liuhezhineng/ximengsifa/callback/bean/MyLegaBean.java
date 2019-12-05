package com.liuhezhineng.ximengsifa.callback.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lishangnan on 2019/11/14.
 */

public class MyLegaBean implements Parcelable {


    /**
     * status : 0
     * msg :
     * body : {"procInsId":"29b9b6ba385141da870c8acd9ddeeb28","procDefId":"reconsider_apply:4:0f2560c9706a423f858d55f6b175ff04","procDefKey":"reconsider_apply","taskId":"76712d30e3fd44aea28ae22a9d472213","taskName":"复议机构审核","taskDefKey":"reconsider_approve","businessTable":"oa_reconsideration_apply","businessId":"5baea2a317ad40fcac8b4f3a20d68097","title":"","task":"","assignee":"","assigneeName":"","procDefName":"","vars":"","businessData":{"id":"5baea2a317ad40fcac8b4f3a20d68097","createBy":{"id":"2bc42c7fc1ee42a983e3dac529aa5b9b","name":"","loginFlag":"1","loginCount":0,"userExpand":{"id":""},"area":{"id":"","name":"","sort":30,"parentId":"0"},"townarea":{"id":"","name":"","sort":30,"parentId":"0"},"admin":false,"roleNames":"","roleOfficeIdList":""},"createDate":"2019-11-14 13:08:25","updateDate":"2019-11-14 13:08:25","caseTitle":"测试","applyName":"李尚南","applySex":"1","applyBirthday":"1991-01-10","applyPapernum":"130627199101105614","applyWorkUnit":"测试","applyAddress":"测试","applyZipCode":"测试","applyPhone":"13521257475","applyLegalName":"测试","applyPost":"测试","agentName":"","agentWorkUnit":"","agentAddress":"","agentZipCode":"","agentPhone":"","respondentName":"测试","respondentLegalName":"测试","respondentPost":"测试测试","respondentAddress":"","respondentZipCode":"测试","incidentDate":"2019-11-14","administrationBehavior":"测试","reconsiderationOfficeId":{"id":"826611c9bd39494ea72efb42a75bf79d","name":"太仆寺旗司法局行政复议与应诉科","sort":30,"parentId":"0"},"reconsiderationRequest":"测试","reconsiderationReason":"测试测试测试测试测试测试测试测试","applyDate":"2019-11-14","caseFile":"","caseCounty":{"id":"","name":"","sort":30,"parentId":"0"},"caseTown":{"id":"","name":"","sort":30,"parentId":"0"},"oaReconsiderationCorrection":{"id":"","applyId":"","title":"","symbol":"","reason":"","requiredMaterials":"","correctionDate":""},"oaReconsiderationAcceptance":{"id":"","applyId":"","title":"","symbol":"","acceptanceDate":""},"oaReconsiderationNotAcceptance":{"id":"","applyId":"","title":"","symbol":"","reason":"","regulations":"","notAcceptanceDate":""},"incidentDateDesc":"2019-11-14","applyDateDesc":"2019-11-14"},"version":"","commentId":"","isEvaluate":"","evaluatedList":[]}
     */

    private int status;
    private String msg;
    private BodyBean body;

    protected MyLegaBean(Parcel in) {
        status = in.readInt();
        msg = in.readString();
        body = in.readParcelable(BodyBean.class.getClassLoader());
    }

    public static final Creator<MyLegaBean> CREATOR = new Creator<MyLegaBean>() {
        @Override
        public MyLegaBean createFromParcel(Parcel in) {
            return new MyLegaBean(in);
        }

        @Override
        public MyLegaBean[] newArray(int size) {
            return new MyLegaBean[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeString(msg);
        dest.writeParcelable(body, flags);
    }

    public static class BodyBean implements Parcelable {
        /**
         * procInsId : 29b9b6ba385141da870c8acd9ddeeb28
         * procDefId : reconsider_apply:4:0f2560c9706a423f858d55f6b175ff04
         * procDefKey : reconsider_apply
         * taskId : 76712d30e3fd44aea28ae22a9d472213
         * taskName : 复议机构审核
         * taskDefKey : reconsider_approve
         * businessTable : oa_reconsideration_apply
         * businessId : 5baea2a317ad40fcac8b4f3a20d68097
         * title :
         * task :
         * assignee :
         * assigneeName :
         * procDefName :
         * vars :
         * businessData : {"id":"5baea2a317ad40fcac8b4f3a20d68097","createBy":{"id":"2bc42c7fc1ee42a983e3dac529aa5b9b","name":"","loginFlag":"1","loginCount":0,"userExpand":{"id":""},"area":{"id":"","name":"","sort":30,"parentId":"0"},"townarea":{"id":"","name":"","sort":30,"parentId":"0"},"admin":false,"roleNames":"","roleOfficeIdList":""},"createDate":"2019-11-14 13:08:25","updateDate":"2019-11-14 13:08:25","caseTitle":"测试","applyName":"李尚南","applySex":"1","applyBirthday":"1991-01-10","applyPapernum":"130627199101105614","applyWorkUnit":"测试","applyAddress":"测试","applyZipCode":"测试","applyPhone":"13521257475","applyLegalName":"测试","applyPost":"测试","agentName":"","agentWorkUnit":"","agentAddress":"","agentZipCode":"","agentPhone":"","respondentName":"测试","respondentLegalName":"测试","respondentPost":"测试测试","respondentAddress":"","respondentZipCode":"测试","incidentDate":"2019-11-14","administrationBehavior":"测试","reconsiderationOfficeId":{"id":"826611c9bd39494ea72efb42a75bf79d","name":"太仆寺旗司法局行政复议与应诉科","sort":30,"parentId":"0"},"reconsiderationRequest":"测试","reconsiderationReason":"测试测试测试测试测试测试测试测试","applyDate":"2019-11-14","caseFile":"","caseCounty":{"id":"","name":"","sort":30,"parentId":"0"},"caseTown":{"id":"","name":"","sort":30,"parentId":"0"},"oaReconsiderationCorrection":{"id":"","applyId":"","title":"","symbol":"","reason":"","requiredMaterials":"","correctionDate":""},"oaReconsiderationAcceptance":{"id":"","applyId":"","title":"","symbol":"","acceptanceDate":""},"oaReconsiderationNotAcceptance":{"id":"","applyId":"","title":"","symbol":"","reason":"","regulations":"","notAcceptanceDate":""},"incidentDateDesc":"2019-11-14","applyDateDesc":"2019-11-14"}
         * version :
         * commentId :
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
        private TaskBean task;
        private String assignee;
        private String assigneeName;
        private String procDefName;
        private String vars;
        private BusinessDataBean businessData;
        private String version;
        private String commentId;
        private String isEvaluate;
        private List<?> evaluatedList;

        protected BodyBean(Parcel in) {
            procInsId = in.readString();
            procDefId = in.readString();
            procDefKey = in.readString();
            taskId = in.readString();
            taskName = in.readString();
            taskDefKey = in.readString();
            businessTable = in.readString();
            businessId = in.readString();
            title = in.readString();
            task = in.readParcelable(TaskBean.class.getClassLoader());
            assignee = in.readString();
            assigneeName = in.readString();
            procDefName = in.readString();
            vars = in.readString();
            businessData = in.readParcelable(BusinessDataBean.class.getClassLoader());
            version = in.readString();
            commentId = in.readString();
            isEvaluate = in.readString();
        }

        public static final Creator<BodyBean> CREATOR = new Creator<BodyBean>() {
            @Override
            public BodyBean createFromParcel(Parcel in) {
                return new BodyBean(in);
            }

            @Override
            public BodyBean[] newArray(int size) {
                return new BodyBean[size];
            }
        };

        public TaskBean getTask() {
            return task;
        }

        public void setTask(TaskBean task) {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(procInsId);
            dest.writeString(procDefId);
            dest.writeString(procDefKey);
            dest.writeString(taskId);
            dest.writeString(taskName);
            dest.writeString(taskDefKey);
            dest.writeString(businessTable);
            dest.writeString(businessId);
            dest.writeString(title);
            dest.writeParcelable(task, flags);
            dest.writeString(assignee);
            dest.writeString(assigneeName);
            dest.writeString(procDefName);
            dest.writeString(vars);
            dest.writeParcelable(businessData, flags);
            dest.writeString(version);
            dest.writeString(commentId);
            dest.writeString(isEvaluate);
        }


        public static class BusinessDataBean implements Parcelable {
            /**
             * id : 5baea2a317ad40fcac8b4f3a20d68097
             * createBy : {"id":"2bc42c7fc1ee42a983e3dac529aa5b9b","name":"","loginFlag":"1","loginCount":0,"userExpand":{"id":""},"area":{"id":"","name":"","sort":30,"parentId":"0"},"townarea":{"id":"","name":"","sort":30,"parentId":"0"},"admin":false,"roleNames":"","roleOfficeIdList":""}
             * createDate : 2019-11-14 13:08:25
             * updateDate : 2019-11-14 13:08:25
             * caseTitle : 测试
             * applyName : 李尚南
             * applySex : 1
             * applyBirthday : 1991-01-10
             * applyPapernum : 130627199101105614
             * applyWorkUnit : 测试
             * applyAddress : 测试
             * applyZipCode : 测试
             * applyPhone : 13521257475
             * applyLegalName : 测试
             * applyPost : 测试
             * agentName :
             * agentWorkUnit :
             * agentAddress :
             * agentZipCode :
             * agentPhone :
             * respondentName : 测试
             * respondentLegalName : 测试
             * respondentPost : 测试测试
             * respondentAddress :
             * respondentZipCode : 测试
             * incidentDate : 2019-11-14
             * administrationBehavior : 测试
             * reconsiderationOfficeId : {"id":"826611c9bd39494ea72efb42a75bf79d","name":"太仆寺旗司法局行政复议与应诉科","sort":30,"parentId":"0"}
             * reconsiderationRequest : 测试
             * reconsiderationReason : 测试测试测试测试测试测试测试测试
             * applyDate : 2019-11-14
             * caseFile :
             * caseCounty : {"id":"","name":"","sort":30,"parentId":"0"}
             * caseTown : {"id":"","name":"","sort":30,"parentId":"0"}
             * oaReconsiderationCorrection : {"id":"","applyId":"","title":"","symbol":"","reason":"","requiredMaterials":"","correctionDate":""}
             * oaReconsiderationAcceptance : {"id":"","applyId":"","title":"","symbol":"","acceptanceDate":""}
             * oaReconsiderationNotAcceptance : {"id":"","applyId":"","title":"","symbol":"","reason":"","regulations":"","notAcceptanceDate":""}
             * incidentDateDesc : 2019-11-14
             * applyDateDesc : 2019-11-14
             */

            private String id;
            private CreateByBean createBy;
            private String createDate;
            private String updateDate;
            private String caseTitle;
            private String applyName;
            private String applySex;
            private String applyBirthday;
            private String applyPapernum;
            private String applyWorkUnit;
            private String applyAddress;
            private String applyZipCode;
            private String applyPhone;
            private String applyLegalName;
            private String applyPost;
            private String agentName;
            private String agentWorkUnit;
            private String agentAddress;
            private String agentZipCode;
            private String agentPhone;
            private String respondentName;
            private String respondentLegalName;
            private String respondentPost;
            private String respondentAddress;
            private String respondentZipCode;
            private String incidentDate;
            private String administrationBehavior;
            private ReconsiderationOfficeIdBean reconsiderationOfficeId;
            private String reconsiderationRequest;
            private String reconsiderationReason;
            private String applyDate;
            private String caseFile;
            private CaseCountyBean caseCounty;
            private CaseTownBean caseTown;
            private OaReconsiderationCorrectionBean oaReconsiderationCorrection;
            private OaReconsiderationAcceptanceBean oaReconsiderationAcceptance;
            private OaReconsiderationNotAcceptanceBean oaReconsiderationNotAcceptance;
            private String incidentDateDesc;
            private String applyDateDesc;

            protected BusinessDataBean(Parcel in) {
                id = in.readString();
                createDate = in.readString();
                updateDate = in.readString();
                caseTitle = in.readString();
                applyName = in.readString();
                applySex = in.readString();
                applyBirthday = in.readString();
                applyPapernum = in.readString();
                applyWorkUnit = in.readString();
                applyAddress = in.readString();
                applyZipCode = in.readString();
                applyPhone = in.readString();
                applyLegalName = in.readString();
                applyPost = in.readString();
                agentName = in.readString();
                agentWorkUnit = in.readString();
                agentAddress = in.readString();
                agentZipCode = in.readString();
                agentPhone = in.readString();
                respondentName = in.readString();
                respondentLegalName = in.readString();
                respondentPost = in.readString();
                respondentAddress = in.readString();
                respondentZipCode = in.readString();
                incidentDate = in.readString();
                administrationBehavior = in.readString();
                reconsiderationRequest = in.readString();
                reconsiderationReason = in.readString();
                applyDate = in.readString();
                caseFile = in.readString();
                incidentDateDesc = in.readString();
                applyDateDesc = in.readString();
            }

            public static final Creator<BusinessDataBean> CREATOR = new Creator<BusinessDataBean>() {
                @Override
                public BusinessDataBean createFromParcel(Parcel in) {
                    return new BusinessDataBean(in);
                }

                @Override
                public BusinessDataBean[] newArray(int size) {
                    return new BusinessDataBean[size];
                }
            };

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getCaseTitle() {
                return caseTitle;
            }

            public void setCaseTitle(String caseTitle) {
                this.caseTitle = caseTitle;
            }

            public String getApplyName() {
                return applyName;
            }

            public void setApplyName(String applyName) {
                this.applyName = applyName;
            }

            public String getApplySex() {
                return applySex;
            }

            public void setApplySex(String applySex) {
                this.applySex = applySex;
            }

            public String getApplyBirthday() {
                return applyBirthday;
            }

            public void setApplyBirthday(String applyBirthday) {
                this.applyBirthday = applyBirthday;
            }

            public String getApplyPapernum() {
                return applyPapernum;
            }

            public void setApplyPapernum(String applyPapernum) {
                this.applyPapernum = applyPapernum;
            }

            public String getApplyWorkUnit() {
                return applyWorkUnit;
            }

            public void setApplyWorkUnit(String applyWorkUnit) {
                this.applyWorkUnit = applyWorkUnit;
            }

            public String getApplyAddress() {
                return applyAddress;
            }

            public void setApplyAddress(String applyAddress) {
                this.applyAddress = applyAddress;
            }

            public String getApplyZipCode() {
                return applyZipCode;
            }

            public void setApplyZipCode(String applyZipCode) {
                this.applyZipCode = applyZipCode;
            }

            public String getApplyPhone() {
                return applyPhone;
            }

            public void setApplyPhone(String applyPhone) {
                this.applyPhone = applyPhone;
            }

            public String getApplyLegalName() {
                return applyLegalName;
            }

            public void setApplyLegalName(String applyLegalName) {
                this.applyLegalName = applyLegalName;
            }

            public String getApplyPost() {
                return applyPost;
            }

            public void setApplyPost(String applyPost) {
                this.applyPost = applyPost;
            }

            public String getAgentName() {
                return agentName;
            }

            public void setAgentName(String agentName) {
                this.agentName = agentName;
            }

            public String getAgentWorkUnit() {
                return agentWorkUnit;
            }

            public void setAgentWorkUnit(String agentWorkUnit) {
                this.agentWorkUnit = agentWorkUnit;
            }

            public String getAgentAddress() {
                return agentAddress;
            }

            public void setAgentAddress(String agentAddress) {
                this.agentAddress = agentAddress;
            }

            public String getAgentZipCode() {
                return agentZipCode;
            }

            public void setAgentZipCode(String agentZipCode) {
                this.agentZipCode = agentZipCode;
            }

            public String getAgentPhone() {
                return agentPhone;
            }

            public void setAgentPhone(String agentPhone) {
                this.agentPhone = agentPhone;
            }

            public String getRespondentName() {
                return respondentName;
            }

            public void setRespondentName(String respondentName) {
                this.respondentName = respondentName;
            }

            public String getRespondentLegalName() {
                return respondentLegalName;
            }

            public void setRespondentLegalName(String respondentLegalName) {
                this.respondentLegalName = respondentLegalName;
            }

            public String getRespondentPost() {
                return respondentPost;
            }

            public void setRespondentPost(String respondentPost) {
                this.respondentPost = respondentPost;
            }

            public String getRespondentAddress() {
                return respondentAddress;
            }

            public void setRespondentAddress(String respondentAddress) {
                this.respondentAddress = respondentAddress;
            }

            public String getRespondentZipCode() {
                return respondentZipCode;
            }

            public void setRespondentZipCode(String respondentZipCode) {
                this.respondentZipCode = respondentZipCode;
            }

            public String getIncidentDate() {
                return incidentDate;
            }

            public void setIncidentDate(String incidentDate) {
                this.incidentDate = incidentDate;
            }

            public String getAdministrationBehavior() {
                return administrationBehavior;
            }

            public void setAdministrationBehavior(String administrationBehavior) {
                this.administrationBehavior = administrationBehavior;
            }

            public ReconsiderationOfficeIdBean getReconsiderationOfficeId() {
                return reconsiderationOfficeId;
            }

            public void setReconsiderationOfficeId(ReconsiderationOfficeIdBean reconsiderationOfficeId) {
                this.reconsiderationOfficeId = reconsiderationOfficeId;
            }

            public String getReconsiderationRequest() {
                return reconsiderationRequest;
            }

            public void setReconsiderationRequest(String reconsiderationRequest) {
                this.reconsiderationRequest = reconsiderationRequest;
            }

            public String getReconsiderationReason() {
                return reconsiderationReason;
            }

            public void setReconsiderationReason(String reconsiderationReason) {
                this.reconsiderationReason = reconsiderationReason;
            }

            public String getApplyDate() {
                return applyDate;
            }

            public void setApplyDate(String applyDate) {
                this.applyDate = applyDate;
            }

            public String getCaseFile() {
                return caseFile;
            }

            public void setCaseFile(String caseFile) {
                this.caseFile = caseFile;
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

            public OaReconsiderationCorrectionBean getOaReconsiderationCorrection() {
                return oaReconsiderationCorrection;
            }

            public void setOaReconsiderationCorrection(OaReconsiderationCorrectionBean oaReconsiderationCorrection) {
                this.oaReconsiderationCorrection = oaReconsiderationCorrection;
            }

            public OaReconsiderationAcceptanceBean getOaReconsiderationAcceptance() {
                return oaReconsiderationAcceptance;
            }

            public void setOaReconsiderationAcceptance(OaReconsiderationAcceptanceBean oaReconsiderationAcceptance) {
                this.oaReconsiderationAcceptance = oaReconsiderationAcceptance;
            }

            public OaReconsiderationNotAcceptanceBean getOaReconsiderationNotAcceptance() {
                return oaReconsiderationNotAcceptance;
            }

            public void setOaReconsiderationNotAcceptance(OaReconsiderationNotAcceptanceBean oaReconsiderationNotAcceptance) {
                this.oaReconsiderationNotAcceptance = oaReconsiderationNotAcceptance;
            }

            public String getIncidentDateDesc() {
                return incidentDateDesc;
            }

            public void setIncidentDateDesc(String incidentDateDesc) {
                this.incidentDateDesc = incidentDateDesc;
            }

            public String getApplyDateDesc() {
                return applyDateDesc;
            }

            public void setApplyDateDesc(String applyDateDesc) {
                this.applyDateDesc = applyDateDesc;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeString(createDate);
                dest.writeString(updateDate);
                dest.writeString(caseTitle);
                dest.writeString(applyName);
                dest.writeString(applySex);
                dest.writeString(applyBirthday);
                dest.writeString(applyPapernum);
                dest.writeString(applyWorkUnit);
                dest.writeString(applyAddress);
                dest.writeString(applyZipCode);
                dest.writeString(applyPhone);
                dest.writeString(applyLegalName);
                dest.writeString(applyPost);
                dest.writeString(agentName);
                dest.writeString(agentWorkUnit);
                dest.writeString(agentAddress);
                dest.writeString(agentZipCode);
                dest.writeString(agentPhone);
                dest.writeString(respondentName);
                dest.writeString(respondentLegalName);
                dest.writeString(respondentPost);
                dest.writeString(respondentAddress);
                dest.writeString(respondentZipCode);
                dest.writeString(incidentDate);
                dest.writeString(administrationBehavior);
                dest.writeString(reconsiderationRequest);
                dest.writeString(reconsiderationReason);
                dest.writeString(applyDate);
                dest.writeString(caseFile);
                dest.writeString(incidentDateDesc);
                dest.writeString(applyDateDesc);
            }

            public static class CreateByBean implements Parcelable {
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

                protected CreateByBean(Parcel in) {
                    id = in.readString();
                    name = in.readString();
                    loginFlag = in.readString();
                    loginCount = in.readInt();
                    userExpand = in.readParcelable(UserExpandBean.class.getClassLoader());
                    area = in.readParcelable(AreaBean.class.getClassLoader());
                    townarea = in.readParcelable(TownareaBean.class.getClassLoader());
                    admin = in.readByte() != 0;
                    roleNames = in.readString();
                    roleOfficeIdList = in.readString();
                }

                public static final Creator<CreateByBean> CREATOR = new Creator<CreateByBean>() {
                    @Override
                    public CreateByBean createFromParcel(Parcel in) {
                        return new CreateByBean(in);
                    }

                    @Override
                    public CreateByBean[] newArray(int size) {
                        return new CreateByBean[size];
                    }
                };

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeString(name);
                    dest.writeString(loginFlag);
                    dest.writeInt(loginCount);
                    dest.writeParcelable(userExpand, flags);
                    dest.writeParcelable(area, flags);
                    dest.writeParcelable(townarea, flags);
                    dest.writeByte((byte) (admin ? 1 : 0));
                    dest.writeString(roleNames);
                    dest.writeString(roleOfficeIdList);
                }

                public static class UserExpandBean implements Parcelable {
                    /**
                     * id :
                     */

                    private String id;

                    protected UserExpandBean(Parcel in) {
                        id = in.readString();
                    }

                    public static final Creator<UserExpandBean> CREATOR = new Creator<UserExpandBean>() {
                        @Override
                        public UserExpandBean createFromParcel(Parcel in) {
                            return new UserExpandBean(in);
                        }

                        @Override
                        public UserExpandBean[] newArray(int size) {
                            return new UserExpandBean[size];
                        }
                    };

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(id);
                    }
                }

                public static class AreaBean implements Parcelable {
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

                    protected AreaBean(Parcel in) {
                        id = in.readString();
                        name = in.readString();
                        sort = in.readInt();
                        parentId = in.readString();
                    }

                    public static final Creator<AreaBean> CREATOR = new Creator<AreaBean>() {
                        @Override
                        public AreaBean createFromParcel(Parcel in) {
                            return new AreaBean(in);
                        }

                        @Override
                        public AreaBean[] newArray(int size) {
                            return new AreaBean[size];
                        }
                    };

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

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(id);
                        dest.writeString(name);
                        dest.writeInt(sort);
                        dest.writeString(parentId);
                    }
                }

                public static class TownareaBean implements Parcelable {
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

                    protected TownareaBean(Parcel in) {
                        id = in.readString();
                        name = in.readString();
                        sort = in.readInt();
                        parentId = in.readString();
                    }

                    public static final Creator<TownareaBean> CREATOR = new Creator<TownareaBean>() {
                        @Override
                        public TownareaBean createFromParcel(Parcel in) {
                            return new TownareaBean(in);
                        }

                        @Override
                        public TownareaBean[] newArray(int size) {
                            return new TownareaBean[size];
                        }
                    };

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

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(id);
                        dest.writeString(name);
                        dest.writeInt(sort);
                        dest.writeString(parentId);
                    }
                }
            }

            public static class ReconsiderationOfficeIdBean implements Parcelable {
                /**
                 * id : 826611c9bd39494ea72efb42a75bf79d
                 * name : 太仆寺旗司法局行政复议与应诉科
                 * sort : 30
                 * parentId : 0
                 */

                private String id;
                private String name;
                private int sort;
                private String parentId;

                protected ReconsiderationOfficeIdBean(Parcel in) {
                    id = in.readString();
                    name = in.readString();
                    sort = in.readInt();
                    parentId = in.readString();
                }

                public static final Creator<ReconsiderationOfficeIdBean> CREATOR = new Creator<ReconsiderationOfficeIdBean>() {
                    @Override
                    public ReconsiderationOfficeIdBean createFromParcel(Parcel in) {
                        return new ReconsiderationOfficeIdBean(in);
                    }

                    @Override
                    public ReconsiderationOfficeIdBean[] newArray(int size) {
                        return new ReconsiderationOfficeIdBean[size];
                    }
                };

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeString(name);
                    dest.writeInt(sort);
                    dest.writeString(parentId);
                }
            }

            public static class CaseCountyBean implements Parcelable {
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

                protected CaseCountyBean(Parcel in) {
                    id = in.readString();
                    name = in.readString();
                    sort = in.readInt();
                    parentId = in.readString();
                }

                public static final Creator<CaseCountyBean> CREATOR = new Creator<CaseCountyBean>() {
                    @Override
                    public CaseCountyBean createFromParcel(Parcel in) {
                        return new CaseCountyBean(in);
                    }

                    @Override
                    public CaseCountyBean[] newArray(int size) {
                        return new CaseCountyBean[size];
                    }
                };

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeString(name);
                    dest.writeInt(sort);
                    dest.writeString(parentId);
                }
            }

            public static class CaseTownBean implements Parcelable {
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

                protected CaseTownBean(Parcel in) {
                    id = in.readString();
                    name = in.readString();
                    sort = in.readInt();
                    parentId = in.readString();
                }

                public static final Creator<CaseTownBean> CREATOR = new Creator<CaseTownBean>() {
                    @Override
                    public CaseTownBean createFromParcel(Parcel in) {
                        return new CaseTownBean(in);
                    }

                    @Override
                    public CaseTownBean[] newArray(int size) {
                        return new CaseTownBean[size];
                    }
                };

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeString(name);
                    dest.writeInt(sort);
                    dest.writeString(parentId);
                }
            }

            public static class OaReconsiderationCorrectionBean implements Parcelable {
                /**
                 * id :
                 * applyId :
                 * title :
                 * symbol :
                 * reason :
                 * requiredMaterials :
                 * correctionDate :
                 */

                private String id;
                private String applyId;
                private String title;
                private String symbol;
                private String reason;
                private String requiredMaterials;
                private String correctionDate;

                protected OaReconsiderationCorrectionBean(Parcel in) {
                    id = in.readString();
                    applyId = in.readString();
                    title = in.readString();
                    symbol = in.readString();
                    reason = in.readString();
                    requiredMaterials = in.readString();
                    correctionDate = in.readString();
                }

                public static final Creator<OaReconsiderationCorrectionBean> CREATOR = new Creator<OaReconsiderationCorrectionBean>() {
                    @Override
                    public OaReconsiderationCorrectionBean createFromParcel(Parcel in) {
                        return new OaReconsiderationCorrectionBean(in);
                    }

                    @Override
                    public OaReconsiderationCorrectionBean[] newArray(int size) {
                        return new OaReconsiderationCorrectionBean[size];
                    }
                };

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

                public String getCorrectionDate() {
                    return correctionDate;
                }

                public void setCorrectionDate(String correctionDate) {
                    this.correctionDate = correctionDate;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeString(applyId);
                    dest.writeString(title);
                    dest.writeString(symbol);
                    dest.writeString(reason);
                    dest.writeString(requiredMaterials);
                    dest.writeString(correctionDate);
                }
            }

            public static class OaReconsiderationAcceptanceBean implements Parcelable {
                /**
                 * id :
                 * applyId :
                 * title :
                 * symbol :
                 * acceptanceDate :
                 */

                private String id;
                private String applyId;
                private String title;
                private String symbol;
                private String acceptanceDate;

                protected OaReconsiderationAcceptanceBean(Parcel in) {
                    id = in.readString();
                    applyId = in.readString();
                    title = in.readString();
                    symbol = in.readString();
                    acceptanceDate = in.readString();
                }

                public static final Creator<OaReconsiderationAcceptanceBean> CREATOR = new Creator<OaReconsiderationAcceptanceBean>() {
                    @Override
                    public OaReconsiderationAcceptanceBean createFromParcel(Parcel in) {
                        return new OaReconsiderationAcceptanceBean(in);
                    }

                    @Override
                    public OaReconsiderationAcceptanceBean[] newArray(int size) {
                        return new OaReconsiderationAcceptanceBean[size];
                    }
                };

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

                public String getAcceptanceDate() {
                    return acceptanceDate;
                }

                public void setAcceptanceDate(String acceptanceDate) {
                    this.acceptanceDate = acceptanceDate;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeString(applyId);
                    dest.writeString(title);
                    dest.writeString(symbol);
                    dest.writeString(acceptanceDate);
                }
            }

            public static class OaReconsiderationNotAcceptanceBean implements Parcelable {
                /**
                 * id :
                 * applyId :
                 * title :
                 * symbol :
                 * reason :
                 * regulations :
                 * notAcceptanceDate :
                 */

                private String id;
                private String applyId;
                private String title;
                private String symbol;
                private String reason;
                private String regulations;
                private String notAcceptanceDate;

                protected OaReconsiderationNotAcceptanceBean(Parcel in) {
                    id = in.readString();
                    applyId = in.readString();
                    title = in.readString();
                    symbol = in.readString();
                    reason = in.readString();
                    regulations = in.readString();
                    notAcceptanceDate = in.readString();
                }

                public static final Creator<OaReconsiderationNotAcceptanceBean> CREATOR = new Creator<OaReconsiderationNotAcceptanceBean>() {
                    @Override
                    public OaReconsiderationNotAcceptanceBean createFromParcel(Parcel in) {
                        return new OaReconsiderationNotAcceptanceBean(in);
                    }

                    @Override
                    public OaReconsiderationNotAcceptanceBean[] newArray(int size) {
                        return new OaReconsiderationNotAcceptanceBean[size];
                    }
                };

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

                public String getRegulations() {
                    return regulations;
                }

                public void setRegulations(String regulations) {
                    this.regulations = regulations;
                }

                public String getNotAcceptanceDate() {
                    return notAcceptanceDate;
                }

                public void setNotAcceptanceDate(String notAcceptanceDate) {
                    this.notAcceptanceDate = notAcceptanceDate;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeString(applyId);
                    dest.writeString(title);
                    dest.writeString(symbol);
                    dest.writeString(reason);
                    dest.writeString(regulations);
                    dest.writeString(notAcceptanceDate);
                }
            }
        }


        public static class TaskBean  implements  Parcelable{
            private String processInstanceId;
            private String processDefinitionId;
            private String createTime;
            private String endTime;

            protected TaskBean(Parcel in) {
                processInstanceId = in.readString();
                processDefinitionId = in.readString();
                createTime = in.readString();
                endTime = in.readString();
            }

            public static final Creator<TaskBean> CREATOR = new Creator<TaskBean>() {
                @Override
                public TaskBean createFromParcel(Parcel in) {
                    return new TaskBean(in);
                }

                @Override
                public TaskBean[] newArray(int size) {
                    return new TaskBean[size];
                }
            };

            public String getProcessInstanceId() {
                return processInstanceId;
            }

            public void setProcessInstanceId(String processInstanceId) {
                this.processInstanceId = processInstanceId;
            }

            public String getProcessDefinitionId() {
                return processDefinitionId;
            }

            public void setProcessDefinitionId(String processDefinitionId) {
                this.processDefinitionId = processDefinitionId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(processInstanceId);
                dest.writeString(processDefinitionId);
                dest.writeString(createTime);
                dest.writeString(endTime);
            }
        }

    }
}
