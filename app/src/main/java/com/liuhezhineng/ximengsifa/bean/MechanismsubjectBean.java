package com.liuhezhineng.ximengsifa.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.liuhezhineng.ximengsifa.bean.base.Base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lishangnan on 2019/11/11.
 */

public class MechanismsubjectBean implements Base {

    /**
     * status : 0
     * msg :
     * body : {"pageNo":1,"pageSize":2,"count":3,"list":[{"id":"7459b9a0d64c422b890f575e77c9fce7","createBy":{"id":"1","name":"","loginFlag":"1","loginCount":0,"userExpand":{"id":""},"area":{"id":"","name":"","sort":30,"parentId":"0"},"townarea":{"id":"","name":"","sort":30,"parentId":"0"},"admin":true,"roleNames":"","roleOfficeIdList":""},"createDate":"2019-08-21 18:25:21","updateDate":"2019-10-30 11:33:49","name":"锡林浩特市城市管理综合执法局","abbreviation":"锡市城管局","phone":"0479-6981113","manager":"项存明","area":{"id":"7","name":"锡林浩特市","sort":30,"parentId":"0"},"town":{"id":"","name":"暂无","sort":30,"parentId":"0"},"creditcode":"1115250201168356F","faxNumber":"暂无","zipCode":"暂无","address":"暂无","level":"6","levelDesc":"科级以下","superiorOffice":"锡林浩特市人民政府","guidanceOffice":"盟住建","documentName":"锡市城管局","officeType":"2","officeTypeDesc":"执法、监督部门","officeNature":"5","officeNatureDesc":"其他","officeLevel":"2","officeLevelDesc":"区县级","superviseOffice":"","powersType":"","superviseOfficeType":"3","superviseOfficeTypeDesc":"其他","imageUrl":"","coordinate":"116.117076,43.949743","officeId":"92bc395d3fbd4035b651ed883acae00b"},{"id":"2fc277ed77054977adaa6d04167c740e","createBy":{"id":"1","name":"","loginFlag":"1","loginCount":0,"userExpand":{"id":""},"area":{"id":"","name":"","sort":30,"parentId":"0"},"townarea":{"id":"","name":"","sort":30,"parentId":"0"},"admin":true,"roleNames":"","roleOfficeIdList":""},"createDate":"2019-08-21 18:25:21","updateDate":"2019-10-30 11:33:38","name":"锡林浩特市市场监督管理局","abbreviation":"锡市市场监督管理局","phone":"0479-6832073","manager":"白青松","area":{"id":"7","name":"锡林浩特市","sort":30,"parentId":"0"},"town":{"id":"","name":"暂无","sort":30,"parentId":"0"},"creditcode":"111525020116680069","faxNumber":"暂无","zipCode":"暂无","address":"暂无","level":"","levelDesc":"","superiorOffice":"锡林浩特市人民政府","guidanceOffice":"锡盟市场监督管理局","documentName":"锡市市监局","officeType":"2","officeTypeDesc":"执法、监督部门","officeNature":"0","officeNatureDesc":"组成部门","officeLevel":"2","officeLevelDesc":"区县级","superviseOffice":"锡林浩特市司法局","powersType":"0,1,2","powersTypeList":[{"id":"9cc44c5c9d4e4dc0846fd93983eb1b29","remarks":"","createDate":"2019-08-21 16:41:55","updateDate":"2019-08-21 16:41:55","value":"0","label":"行政处罚","type":"law_enforcement_subject_powers_type","description":"执法主体职权类别","sort":10,"parentId":"0","languageType":"CN"},{"id":"daf104939f6c4a6888534ea1b3d6584f","remarks":"","createDate":"2019-08-21 16:42:16","updateDate":"2019-08-21 16:42:16","value":"1","label":"行政许可","type":"law_enforcement_subject_powers_type","description":"执法主体职权类别","sort":20,"parentId":"0","languageType":"CN"},{"id":"f12dd1095e97429b8de83d82d4f56213","remarks":"","createDate":"2019-08-21 16:42:29","updateDate":"2019-08-21 16:42:29","value":"2","label":"行政强制","type":"law_enforcement_subject_powers_type","description":"执法主体职权类别","sort":30,"parentId":"0","languageType":"CN"}],"superviseOfficeType":"","superviseOfficeTypeDesc":"","imageUrl":"","coordinate":"116.106803,43.937265","officeId":"db74b8d7eb6848948e1c25a50c40a333"}]}
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

    public static class BodyBean {
        /**
         * pageNo : 1
         * pageSize : 2
         * count : 3
         * list : [{"id":"7459b9a0d64c422b890f575e77c9fce7","createBy":{"id":"1","name":"","loginFlag":"1","loginCount":0,"userExpand":{"id":""},"area":{"id":"","name":"","sort":30,"parentId":"0"},"townarea":{"id":"","name":"","sort":30,"parentId":"0"},"admin":true,"roleNames":"","roleOfficeIdList":""},"createDate":"2019-08-21 18:25:21","updateDate":"2019-10-30 11:33:49","name":"锡林浩特市城市管理综合执法局","abbreviation":"锡市城管局","phone":"0479-6981113","manager":"项存明","area":{"id":"7","name":"锡林浩特市","sort":30,"parentId":"0"},"town":{"id":"","name":"暂无","sort":30,"parentId":"0"},"creditcode":"1115250201168356F","faxNumber":"暂无","zipCode":"暂无","address":"暂无","level":"6","levelDesc":"科级以下","superiorOffice":"锡林浩特市人民政府","guidanceOffice":"盟住建","documentName":"锡市城管局","officeType":"2","officeTypeDesc":"执法、监督部门","officeNature":"5","officeNatureDesc":"其他","officeLevel":"2","officeLevelDesc":"区县级","superviseOffice":"","powersType":"","superviseOfficeType":"3","superviseOfficeTypeDesc":"其他","imageUrl":"","coordinate":"116.117076,43.949743","officeId":"92bc395d3fbd4035b651ed883acae00b"},{"id":"2fc277ed77054977adaa6d04167c740e","createBy":{"id":"1","name":"","loginFlag":"1","loginCount":0,"userExpand":{"id":""},"area":{"id":"","name":"","sort":30,"parentId":"0"},"townarea":{"id":"","name":"","sort":30,"parentId":"0"},"admin":true,"roleNames":"","roleOfficeIdList":""},"createDate":"2019-08-21 18:25:21","updateDate":"2019-10-30 11:33:38","name":"锡林浩特市市场监督管理局","abbreviation":"锡市市场监督管理局","phone":"0479-6832073","manager":"白青松","area":{"id":"7","name":"锡林浩特市","sort":30,"parentId":"0"},"town":{"id":"","name":"暂无","sort":30,"parentId":"0"},"creditcode":"111525020116680069","faxNumber":"暂无","zipCode":"暂无","address":"暂无","level":"","levelDesc":"","superiorOffice":"锡林浩特市人民政府","guidanceOffice":"锡盟市场监督管理局","documentName":"锡市市监局","officeType":"2","officeTypeDesc":"执法、监督部门","officeNature":"0","officeNatureDesc":"组成部门","officeLevel":"2","officeLevelDesc":"区县级","superviseOffice":"锡林浩特市司法局","powersType":"0,1,2","powersTypeList":[{"id":"9cc44c5c9d4e4dc0846fd93983eb1b29","remarks":"","createDate":"2019-08-21 16:41:55","updateDate":"2019-08-21 16:41:55","value":"0","label":"行政处罚","type":"law_enforcement_subject_powers_type","description":"执法主体职权类别","sort":10,"parentId":"0","languageType":"CN"},{"id":"daf104939f6c4a6888534ea1b3d6584f","remarks":"","createDate":"2019-08-21 16:42:16","updateDate":"2019-08-21 16:42:16","value":"1","label":"行政许可","type":"law_enforcement_subject_powers_type","description":"执法主体职权类别","sort":20,"parentId":"0","languageType":"CN"},{"id":"f12dd1095e97429b8de83d82d4f56213","remarks":"","createDate":"2019-08-21 16:42:29","updateDate":"2019-08-21 16:42:29","value":"2","label":"行政强制","type":"law_enforcement_subject_powers_type","description":"执法主体职权类别","sort":30,"parentId":"0","languageType":"CN"}],"superviseOfficeType":"","superviseOfficeTypeDesc":"","imageUrl":"","coordinate":"116.106803,43.937265","officeId":"db74b8d7eb6848948e1c25a50c40a333"}]
         */

        private int pageNo;
        private int pageSize;
        private int count;
        private List<ListBean> list;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {
            /**
             * id : 7459b9a0d64c422b890f575e77c9fce7
             * createBy : {"id":"1","name":"","loginFlag":"1","loginCount":0,"userExpand":{"id":""},"area":{"id":"","name":"","sort":30,"parentId":"0"},"townarea":{"id":"","name":"","sort":30,"parentId":"0"},"admin":true,"roleNames":"","roleOfficeIdList":""}
             * createDate : 2019-08-21 18:25:21
             * updateDate : 2019-10-30 11:33:49
             * name : 锡林浩特市城市管理综合执法局
             * abbreviation : 锡市城管局
             * phone : 0479-6981113
             * manager : 项存明
             * area : {"id":"7","name":"锡林浩特市","sort":30,"parentId":"0"}
             * town : {"id":"","name":"暂无","sort":30,"parentId":"0"}
             * creditcode : 1115250201168356F
             * faxNumber : 暂无
             * zipCode : 暂无
             * address : 暂无
             * level : 6
             * levelDesc : 科级以下
             * superiorOffice : 锡林浩特市人民政府
             * guidanceOffice : 盟住建
             * documentName : 锡市城管局
             * officeType : 2
             * officeTypeDesc : 执法、监督部门
             * officeNature : 5
             * officeNatureDesc : 其他
             * officeLevel : 2
             * officeLevelDesc : 区县级
             * superviseOffice :
             * powersType :
             * superviseOfficeType : 3
             * superviseOfficeTypeDesc : 其他
             * imageUrl :
             * coordinate : 116.117076,43.949743
             * officeId : 92bc395d3fbd4035b651ed883acae00b
             * powersTypeList : [{"id":"9cc44c5c9d4e4dc0846fd93983eb1b29","remarks":"","createDate":"2019-08-21 16:41:55","updateDate":"2019-08-21 16:41:55","value":"0","label":"行政处罚","type":"law_enforcement_subject_powers_type","description":"执法主体职权类别","sort":10,"parentId":"0","languageType":"CN"},{"id":"daf104939f6c4a6888534ea1b3d6584f","remarks":"","createDate":"2019-08-21 16:42:16","updateDate":"2019-08-21 16:42:16","value":"1","label":"行政许可","type":"law_enforcement_subject_powers_type","description":"执法主体职权类别","sort":20,"parentId":"0","languageType":"CN"},{"id":"f12dd1095e97429b8de83d82d4f56213","remarks":"","createDate":"2019-08-21 16:42:29","updateDate":"2019-08-21 16:42:29","value":"2","label":"行政强制","type":"law_enforcement_subject_powers_type","description":"执法主体职权类别","sort":30,"parentId":"0","languageType":"CN"}]
             */

            private String id;
            private CreateByBean createBy;
            private String createDate;
            private String updateDate;
            private String name;
            private String abbreviation;
            private String phone;
            private String manager;
            private AreaBeanX area;
            private TownBean town;
            private String creditcode;
            private String faxNumber;
            private String zipCode;
            private String address;
            private String level;
            private String levelDesc;
            private String superiorOffice;
            private String guidanceOffice;
            private String documentName;
            private String officeType;
            private String officeTypeDesc;
            private String officeNature;
            private String officeNatureDesc;
            private String officeLevel;
            private String officeLevelDesc;
            private String superviseOffice;
            private String powersType;
            private String superviseOfficeType;
            private String superviseOfficeTypeDesc;
            private String imageUrl;
            private String coordinate;
            private String officeId;
            private List<PowersTypeListBean> powersTypeList;

            protected ListBean(Parcel in) {
                id = in.readString();
                createDate = in.readString();
                updateDate = in.readString();
                name = in.readString();
                abbreviation = in.readString();
                phone = in.readString();
                manager = in.readString();
                creditcode = in.readString();
                faxNumber = in.readString();
                zipCode = in.readString();
                address = in.readString();
                level = in.readString();
                levelDesc = in.readString();
                superiorOffice = in.readString();
                guidanceOffice = in.readString();
                documentName = in.readString();
                officeType = in.readString();
                officeTypeDesc = in.readString();
                officeNature = in.readString();
                officeNatureDesc = in.readString();
                officeLevel = in.readString();
                officeLevelDesc = in.readString();
                superviseOffice = in.readString();
                powersType = in.readString();
                superviseOfficeType = in.readString();
                superviseOfficeTypeDesc = in.readString();
                imageUrl = in.readString();
                coordinate = in.readString();
                officeId = in.readString();
            }

            public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel in) {
                    return new ListBean(in);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAbbreviation() {
                return abbreviation;
            }

            public void setAbbreviation(String abbreviation) {
                this.abbreviation = abbreviation;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getManager() {
                return manager;
            }

            public void setManager(String manager) {
                this.manager = manager;
            }

            public AreaBeanX getArea() {
                return area;
            }

            public void setArea(AreaBeanX area) {
                this.area = area;
            }

            public TownBean getTown() {
                return town;
            }

            public void setTown(TownBean town) {
                this.town = town;
            }

            public String getCreditcode() {
                return creditcode;
            }

            public void setCreditcode(String creditcode) {
                this.creditcode = creditcode;
            }

            public String getFaxNumber() {
                return faxNumber;
            }

            public void setFaxNumber(String faxNumber) {
                this.faxNumber = faxNumber;
            }

            public String getZipCode() {
                return zipCode;
            }

            public void setZipCode(String zipCode) {
                this.zipCode = zipCode;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getLevelDesc() {
                return levelDesc;
            }

            public void setLevelDesc(String levelDesc) {
                this.levelDesc = levelDesc;
            }

            public String getSuperiorOffice() {
                return superiorOffice;
            }

            public void setSuperiorOffice(String superiorOffice) {
                this.superiorOffice = superiorOffice;
            }

            public String getGuidanceOffice() {
                return guidanceOffice;
            }

            public void setGuidanceOffice(String guidanceOffice) {
                this.guidanceOffice = guidanceOffice;
            }

            public String getDocumentName() {
                return documentName;
            }

            public void setDocumentName(String documentName) {
                this.documentName = documentName;
            }

            public String getOfficeType() {
                return officeType;
            }

            public void setOfficeType(String officeType) {
                this.officeType = officeType;
            }

            public String getOfficeTypeDesc() {
                return officeTypeDesc;
            }

            public void setOfficeTypeDesc(String officeTypeDesc) {
                this.officeTypeDesc = officeTypeDesc;
            }

            public String getOfficeNature() {
                return officeNature;
            }

            public void setOfficeNature(String officeNature) {
                this.officeNature = officeNature;
            }

            public String getOfficeNatureDesc() {
                return officeNatureDesc;
            }

            public void setOfficeNatureDesc(String officeNatureDesc) {
                this.officeNatureDesc = officeNatureDesc;
            }

            public String getOfficeLevel() {
                return officeLevel;
            }

            public void setOfficeLevel(String officeLevel) {
                this.officeLevel = officeLevel;
            }

            public String getOfficeLevelDesc() {
                return officeLevelDesc;
            }

            public void setOfficeLevelDesc(String officeLevelDesc) {
                this.officeLevelDesc = officeLevelDesc;
            }

            public String getSuperviseOffice() {
                return superviseOffice;
            }

            public void setSuperviseOffice(String superviseOffice) {
                this.superviseOffice = superviseOffice;
            }

            public String getPowersType() {
                return powersType;
            }

            public void setPowersType(String powersType) {
                this.powersType = powersType;
            }

            public String getSuperviseOfficeType() {
                return superviseOfficeType;
            }

            public void setSuperviseOfficeType(String superviseOfficeType) {
                this.superviseOfficeType = superviseOfficeType;
            }

            public String getSuperviseOfficeTypeDesc() {
                return superviseOfficeTypeDesc;
            }

            public void setSuperviseOfficeTypeDesc(String superviseOfficeTypeDesc) {
                this.superviseOfficeTypeDesc = superviseOfficeTypeDesc;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getCoordinate() {
                return coordinate;
            }

            public void setCoordinate(String coordinate) {
                this.coordinate = coordinate;
            }

            public String getOfficeId() {
                return officeId;
            }

            public void setOfficeId(String officeId) {
                this.officeId = officeId;
            }

            public List<PowersTypeListBean> getPowersTypeList() {
                return powersTypeList;
            }

            public void setPowersTypeList(List<PowersTypeListBean> powersTypeList) {
                this.powersTypeList = powersTypeList;
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
                dest.writeString(name);
                dest.writeString(abbreviation);
                dest.writeString(phone);
                dest.writeString(manager);
                dest.writeString(creditcode);
                dest.writeString(faxNumber);
                dest.writeString(zipCode);
                dest.writeString(address);
                dest.writeString(level);
                dest.writeString(levelDesc);
                dest.writeString(superiorOffice);
                dest.writeString(guidanceOffice);
                dest.writeString(documentName);
                dest.writeString(officeType);
                dest.writeString(officeTypeDesc);
                dest.writeString(officeNature);
                dest.writeString(officeNatureDesc);
                dest.writeString(officeLevel);
                dest.writeString(officeLevelDesc);
                dest.writeString(superviseOffice);
                dest.writeString(powersType);
                dest.writeString(superviseOfficeType);
                dest.writeString(superviseOfficeTypeDesc);
                dest.writeString(imageUrl);
                dest.writeString(coordinate);
                dest.writeString(officeId);
            }

            public static class CreateByBean implements  Parcelable{
                /**
                 * id : 1
                 * name :
                 * loginFlag : 1
                 * loginCount : 0
                 * userExpand : {"id":""}
                 * area : {"id":"","name":"","sort":30,"parentId":"0"}
                 * townarea : {"id":"","name":"","sort":30,"parentId":"0"}
                 * admin : true
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

                public static class UserExpandBean  implements Parcelable{
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

                public static class TownareaBean implements Parcelable{
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

            public static class AreaBeanX implements Parcelable {
                /**
                 * id : 7
                 * name : 锡林浩特市
                 * sort : 30
                 * parentId : 0
                 */

                private String id;
                private String name;
                private int sort;
                private String parentId;

                protected AreaBeanX(Parcel in) {
                    id = in.readString();
                    name = in.readString();
                    sort = in.readInt();
                    parentId = in.readString();
                }

                public static final Creator<AreaBeanX> CREATOR = new Creator<AreaBeanX>() {
                    @Override
                    public AreaBeanX createFromParcel(Parcel in) {
                        return new AreaBeanX(in);
                    }

                    @Override
                    public AreaBeanX[] newArray(int size) {
                        return new AreaBeanX[size];
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

            public static class TownBean implements Parcelable {
                /**
                 * id :
                 * name : 暂无
                 * sort : 30
                 * parentId : 0
                 */

                private String id;
                private String name;
                private int sort;
                private String parentId;

                protected TownBean(Parcel in) {
                    id = in.readString();
                    name = in.readString();
                    sort = in.readInt();
                    parentId = in.readString();
                }

                public static final Creator<TownBean> CREATOR = new Creator<TownBean>() {
                    @Override
                    public TownBean createFromParcel(Parcel in) {
                        return new TownBean(in);
                    }

                    @Override
                    public TownBean[] newArray(int size) {
                        return new TownBean[size];
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

            public static class PowersTypeListBean  implements Parcelable{
                /**
                 * id : 9cc44c5c9d4e4dc0846fd93983eb1b29
                 * remarks :
                 * createDate : 2019-08-21 16:41:55
                 * updateDate : 2019-08-21 16:41:55
                 * value : 0
                 * label : 行政处罚
                 * type : law_enforcement_subject_powers_type
                 * description : 执法主体职权类别
                 * sort : 10
                 * parentId : 0
                 * languageType : CN
                 */

                private String id;
                private String remarks;
                private String createDate;
                private String updateDate;
                private String value;
                private String label;
                private String type;
                private String description;
                private int sort;
                private String parentId;
                private String languageType;

                protected PowersTypeListBean(Parcel in) {
                    id = in.readString();
                    remarks = in.readString();
                    createDate = in.readString();
                    updateDate = in.readString();
                    value = in.readString();
                    label = in.readString();
                    type = in.readString();
                    description = in.readString();
                    sort = in.readInt();
                    parentId = in.readString();
                    languageType = in.readString();
                }

                public static final Creator<PowersTypeListBean> CREATOR = new Creator<PowersTypeListBean>() {
                    @Override
                    public PowersTypeListBean createFromParcel(Parcel in) {
                        return new PowersTypeListBean(in);
                    }

                    @Override
                    public PowersTypeListBean[] newArray(int size) {
                        return new PowersTypeListBean[size];
                    }
                };

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

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
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

                public String getLanguageType() {
                    return languageType;
                }

                public void setLanguageType(String languageType) {
                    this.languageType = languageType;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeString(remarks);
                    dest.writeString(createDate);
                    dest.writeString(updateDate);
                    dest.writeString(value);
                    dest.writeString(label);
                    dest.writeString(type);
                    dest.writeString(description);
                    dest.writeInt(sort);
                    dest.writeString(parentId);
                    dest.writeString(languageType);
                }
            }
        }
    }
}
