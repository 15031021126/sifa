package com.liuhezhineng.ximengsifa.callback.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by lishangnan on 2019/11/12.
 */

public class MechPeopleBean  implements  Parcelable{

    /**
     * status : 0
     * msg :
     * body : {"pageNo":1,"pageSize":2,"count":74,"list":[{"id":"92bc395d3fbd4035b651ed883acae00b","remarks":"","createBy":"","createDate":"","updateDate":"","evaluation":"","categoryId":"","area":{"id":"7","name":"锡林浩特市","sort":30,"parentId":"0"},"town":{"id":"","name":"","sort":30,"parentId":"0"},"agencyId":"02a29ea393064915a0ebea6f41213889","agencyName":"锡林浩特市城市管理综合执法局","agencyAddress":"暂无","agencyPhone":"0479-6981113","personName":"穆博文","imageUrl":"/userfiles/default/user.png","coordinate":"暂无","hasPerson":-1,"evaluate":0,"no":"152502101067","type":"暂无","zipCode":"暂无","email":"暂无","introduction":"暂无","sex":"男","ethnic":"汉族","education":"高中","age":"35","politicalFace":"群众","practisingYear":"2","roleId":"暂无","worktime":"暂无","teamSize":"暂无","birthday":"1984-02-23 00:00:00","isMongolian":"暂无","licenseForm":"暂无","businessExpertise":"暂无","mediatorType":"暂无","idCard":"152502198402230513","fax":"暂无","scopeOfBussess":"暂无","practisingTime":"2017-01-01","mainOrgans":"","status":"暂无","isAidLawyer":"暂无","officeId":"","loginStatus":"0","loginStatusTime":"2022-12-31","organization":"其他事业编","certificateType":"执法证"},{"id":"92bc395d3fbd4035b651ed883acae00b","remarks":"","createBy":"","createDate":"","updateDate":"","evaluation":"","categoryId":"","area":{"id":"7","name":"锡林浩特市","sort":30,"parentId":"0"},"town":{"id":"","name":"","sort":30,"parentId":"0"},"agencyId":"0c361852f754495fb772e59ba7a94459","agencyName":"锡林浩特市城市管理综合执法局","agencyAddress":"暂无","agencyPhone":"0479-6981113","personName":"苏依拉","imageUrl":"/userfiles/default/user.png","coordinate":"暂无","hasPerson":-1,"evaluate":0,"no":"152502101172","type":"暂无","zipCode":"暂无","email":"暂无","introduction":"暂无","sex":"男","ethnic":"暂无","education":"大专","age":"36","politicalFace":"群众","practisingYear":"2","roleId":"暂无","worktime":"暂无","teamSize":"暂无","birthday":"1983-03-14 00:00:00","isMongolian":"暂无","licenseForm":"暂无","businessExpertise":"暂无","mediatorType":"暂无","idCard":"152502198303140539","fax":"暂无","scopeOfBussess":"暂无","practisingTime":"2017-01-01","mainOrgans":"","status":"暂无","isAidLawyer":"暂无","officeId":"","loginStatus":"0","loginStatusTime":"2022-12-31","organization":"其他事业编","certificateType":"执法证"}]}
     */

    private int status;
    private String msg;
    private BodyBean body;

    protected MechPeopleBean(Parcel in) {
        status = in.readInt();
        msg = in.readString();
        body = in.readParcelable(BodyBean.class.getClassLoader());
    }

    public static final Creator<MechPeopleBean> CREATOR = new Creator<MechPeopleBean>() {
        @Override
        public MechPeopleBean createFromParcel(Parcel in) {
            return new MechPeopleBean(in);
        }

        @Override
        public MechPeopleBean[] newArray(int size) {
            return new MechPeopleBean[size];
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

    public static class BodyBean implements Parcelable{
        /**
         * pageNo : 1
         * pageSize : 2
         * count : 74
         * list : [{"id":"92bc395d3fbd4035b651ed883acae00b","remarks":"","createBy":"","createDate":"","updateDate":"","evaluation":"","categoryId":"","area":{"id":"7","name":"锡林浩特市","sort":30,"parentId":"0"},"town":{"id":"","name":"","sort":30,"parentId":"0"},"agencyId":"02a29ea393064915a0ebea6f41213889","agencyName":"锡林浩特市城市管理综合执法局","agencyAddress":"暂无","agencyPhone":"0479-6981113","personName":"穆博文","imageUrl":"/userfiles/default/user.png","coordinate":"暂无","hasPerson":-1,"evaluate":0,"no":"152502101067","type":"暂无","zipCode":"暂无","email":"暂无","introduction":"暂无","sex":"男","ethnic":"汉族","education":"高中","age":"35","politicalFace":"群众","practisingYear":"2","roleId":"暂无","worktime":"暂无","teamSize":"暂无","birthday":"1984-02-23 00:00:00","isMongolian":"暂无","licenseForm":"暂无","businessExpertise":"暂无","mediatorType":"暂无","idCard":"152502198402230513","fax":"暂无","scopeOfBussess":"暂无","practisingTime":"2017-01-01","mainOrgans":"","status":"暂无","isAidLawyer":"暂无","officeId":"","loginStatus":"0","loginStatusTime":"2022-12-31","organization":"其他事业编","certificateType":"执法证"},{"id":"92bc395d3fbd4035b651ed883acae00b","remarks":"","createBy":"","createDate":"","updateDate":"","evaluation":"","categoryId":"","area":{"id":"7","name":"锡林浩特市","sort":30,"parentId":"0"},"town":{"id":"","name":"","sort":30,"parentId":"0"},"agencyId":"0c361852f754495fb772e59ba7a94459","agencyName":"锡林浩特市城市管理综合执法局","agencyAddress":"暂无","agencyPhone":"0479-6981113","personName":"苏依拉","imageUrl":"/userfiles/default/user.png","coordinate":"暂无","hasPerson":-1,"evaluate":0,"no":"152502101172","type":"暂无","zipCode":"暂无","email":"暂无","introduction":"暂无","sex":"男","ethnic":"暂无","education":"大专","age":"36","politicalFace":"群众","practisingYear":"2","roleId":"暂无","worktime":"暂无","teamSize":"暂无","birthday":"1983-03-14 00:00:00","isMongolian":"暂无","licenseForm":"暂无","businessExpertise":"暂无","mediatorType":"暂无","idCard":"152502198303140539","fax":"暂无","scopeOfBussess":"暂无","practisingTime":"2017-01-01","mainOrgans":"","status":"暂无","isAidLawyer":"暂无","officeId":"","loginStatus":"0","loginStatusTime":"2022-12-31","organization":"其他事业编","certificateType":"执法证"}]
         */

        private int pageNo;
        private int pageSize;
        private int count;
        private List<ListBean> list;

        protected BodyBean(Parcel in) {
            pageNo = in.readInt();
            pageSize = in.readInt();
            count = in.readInt();
            list = in.createTypedArrayList(ListBean.CREATOR);
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(pageNo);
            dest.writeInt(pageSize);
            dest.writeInt(count);
            dest.writeTypedList(list);
        }

        public static class ListBean  implements Parcelable{
            /**
             * id : 92bc395d3fbd4035b651ed883acae00b
             * remarks :
             * createBy :
             * createDate :
             * updateDate :
             * evaluation :
             * categoryId :
             * area : {"id":"7","name":"锡林浩特市","sort":30,"parentId":"0"}
             * town : {"id":"","name":"","sort":30,"parentId":"0"}
             * agencyId : 02a29ea393064915a0ebea6f41213889
             * agencyName : 锡林浩特市城市管理综合执法局
             * agencyAddress : 暂无
             * agencyPhone : 0479-6981113
             * personName : 穆博文
             * imageUrl : /userfiles/default/user.png
             * coordinate : 暂无
             * hasPerson : -1
             * evaluate : 0
             * no : 152502101067
             * type : 暂无
             * zipCode : 暂无
             * email : 暂无
             * introduction : 暂无
             * sex : 男
             * ethnic : 汉族
             * education : 高中
             * age : 35
             * politicalFace : 群众
             * practisingYear : 2
             * roleId : 暂无
             * worktime : 暂无
             * teamSize : 暂无
             * birthday : 1984-02-23 00:00:00
             * isMongolian : 暂无
             * licenseForm : 暂无
             * businessExpertise : 暂无
             * mediatorType : 暂无
             * idCard : 152502198402230513
             * fax : 暂无
             * scopeOfBussess : 暂无
             * practisingTime : 2017-01-01
             * mainOrgans :
             * status : 暂无
             * isAidLawyer : 暂无
             * officeId :
             * loginStatus : 0
             * loginStatusTime : 2022-12-31
             * organization : 其他事业编
             * certificateType : 执法证
             */

            private String id;
            private String remarks;
            private String createBy;
            private String createDate;
            private String updateDate;
            private String evaluation;
            private String categoryId;
            private AreaBean area;
            private TownBean town;
            private String agencyId;
            private String agencyName;
            private String agencyAddress;
            private String agencyPhone;
            private String personName;
            private String imageUrl;
            private String coordinate;
            private int hasPerson;
            private int evaluate;
            private String no;
            private String type;
            private String zipCode;
            private String email;
            private String introduction;
            private String sex;
            private String ethnic;
            private String education;
            private String age;
            private String politicalFace;
            private String practisingYear;
            private String roleId;
            private String worktime;
            private String teamSize;
            private String birthday;
            private String isMongolian;
            private String licenseForm;
            private String businessExpertise;
            private String mediatorType;
            private String idCard;
            private String fax;
            private String scopeOfBussess;
            private String practisingTime;
            private String mainOrgans;
            private String status;
            private String isAidLawyer;
            private String officeId;
            private String loginStatus;
            private String loginStatusTime;
            private String organization;
            private String certificateType;

            protected ListBean(Parcel in) {
                id = in.readString();
                remarks = in.readString();
                createBy = in.readString();
                createDate = in.readString();
                updateDate = in.readString();
                evaluation = in.readString();
                categoryId = in.readString();
                agencyId = in.readString();
                agencyName = in.readString();
                agencyAddress = in.readString();
                agencyPhone = in.readString();
                personName = in.readString();
                imageUrl = in.readString();
                coordinate = in.readString();
                hasPerson = in.readInt();
                evaluate = in.readInt();
                no = in.readString();
                type = in.readString();
                zipCode = in.readString();
                email = in.readString();
                introduction = in.readString();
                sex = in.readString();
                ethnic = in.readString();
                education = in.readString();
                age = in.readString();
                politicalFace = in.readString();
                practisingYear = in.readString();
                roleId = in.readString();
                worktime = in.readString();
                teamSize = in.readString();
                birthday = in.readString();
                isMongolian = in.readString();
                licenseForm = in.readString();
                businessExpertise = in.readString();
                mediatorType = in.readString();
                idCard = in.readString();
                fax = in.readString();
                scopeOfBussess = in.readString();
                practisingTime = in.readString();
                mainOrgans = in.readString();
                status = in.readString();
                isAidLawyer = in.readString();
                officeId = in.readString();
                loginStatus = in.readString();
                loginStatusTime = in.readString();
                organization = in.readString();
                certificateType = in.readString();
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

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
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

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public AreaBean getArea() {
                return area;
            }

            public void setArea(AreaBean area) {
                this.area = area;
            }

            public TownBean getTown() {
                return town;
            }

            public void setTown(TownBean town) {
                this.town = town;
            }

            public String getAgencyId() {
                return agencyId;
            }

            public void setAgencyId(String agencyId) {
                this.agencyId = agencyId;
            }

            public String getAgencyName() {
                return agencyName;
            }

            public void setAgencyName(String agencyName) {
                this.agencyName = agencyName;
            }

            public String getAgencyAddress() {
                return agencyAddress;
            }

            public void setAgencyAddress(String agencyAddress) {
                this.agencyAddress = agencyAddress;
            }

            public String getAgencyPhone() {
                return agencyPhone;
            }

            public void setAgencyPhone(String agencyPhone) {
                this.agencyPhone = agencyPhone;
            }

            public String getPersonName() {
                return personName;
            }

            public void setPersonName(String personName) {
                this.personName = personName;
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

            public int getHasPerson() {
                return hasPerson;
            }

            public void setHasPerson(int hasPerson) {
                this.hasPerson = hasPerson;
            }

            public int getEvaluate() {
                return evaluate;
            }

            public void setEvaluate(int evaluate) {
                this.evaluate = evaluate;
            }

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getZipCode() {
                return zipCode;
            }

            public void setZipCode(String zipCode) {
                this.zipCode = zipCode;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getEthnic() {
                return ethnic;
            }

            public void setEthnic(String ethnic) {
                this.ethnic = ethnic;
            }

            public String getEducation() {
                return education;
            }

            public void setEducation(String education) {
                this.education = education;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getPoliticalFace() {
                return politicalFace;
            }

            public void setPoliticalFace(String politicalFace) {
                this.politicalFace = politicalFace;
            }

            public String getPractisingYear() {
                return practisingYear;
            }

            public void setPractisingYear(String practisingYear) {
                this.practisingYear = practisingYear;
            }

            public String getRoleId() {
                return roleId;
            }

            public void setRoleId(String roleId) {
                this.roleId = roleId;
            }

            public String getWorktime() {
                return worktime;
            }

            public void setWorktime(String worktime) {
                this.worktime = worktime;
            }

            public String getTeamSize() {
                return teamSize;
            }

            public void setTeamSize(String teamSize) {
                this.teamSize = teamSize;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getIsMongolian() {
                return isMongolian;
            }

            public void setIsMongolian(String isMongolian) {
                this.isMongolian = isMongolian;
            }

            public String getLicenseForm() {
                return licenseForm;
            }

            public void setLicenseForm(String licenseForm) {
                this.licenseForm = licenseForm;
            }

            public String getBusinessExpertise() {
                return businessExpertise;
            }

            public void setBusinessExpertise(String businessExpertise) {
                this.businessExpertise = businessExpertise;
            }

            public String getMediatorType() {
                return mediatorType;
            }

            public void setMediatorType(String mediatorType) {
                this.mediatorType = mediatorType;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public String getFax() {
                return fax;
            }

            public void setFax(String fax) {
                this.fax = fax;
            }

            public String getScopeOfBussess() {
                return scopeOfBussess;
            }

            public void setScopeOfBussess(String scopeOfBussess) {
                this.scopeOfBussess = scopeOfBussess;
            }

            public String getPractisingTime() {
                return practisingTime;
            }

            public void setPractisingTime(String practisingTime) {
                this.practisingTime = practisingTime;
            }

            public String getMainOrgans() {
                return mainOrgans;
            }

            public void setMainOrgans(String mainOrgans) {
                this.mainOrgans = mainOrgans;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getIsAidLawyer() {
                return isAidLawyer;
            }

            public void setIsAidLawyer(String isAidLawyer) {
                this.isAidLawyer = isAidLawyer;
            }

            public String getOfficeId() {
                return officeId;
            }

            public void setOfficeId(String officeId) {
                this.officeId = officeId;
            }

            public String getLoginStatus() {
                return loginStatus;
            }

            public void setLoginStatus(String loginStatus) {
                this.loginStatus = loginStatus;
            }

            public String getLoginStatusTime() {
                return loginStatusTime;
            }

            public void setLoginStatusTime(String loginStatusTime) {
                this.loginStatusTime = loginStatusTime;
            }

            public String getOrganization() {
                return organization;
            }

            public void setOrganization(String organization) {
                this.organization = organization;
            }

            public String getCertificateType() {
                return certificateType;
            }

            public void setCertificateType(String certificateType) {
                this.certificateType = certificateType;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeString(remarks);
                dest.writeString(createBy);
                dest.writeString(createDate);
                dest.writeString(updateDate);
                dest.writeString(evaluation);
                dest.writeString(categoryId);
                dest.writeString(agencyId);
                dest.writeString(agencyName);
                dest.writeString(agencyAddress);
                dest.writeString(agencyPhone);
                dest.writeString(personName);
                dest.writeString(imageUrl);
                dest.writeString(coordinate);
                dest.writeInt(hasPerson);
                dest.writeInt(evaluate);
                dest.writeString(no);
                dest.writeString(type);
                dest.writeString(zipCode);
                dest.writeString(email);
                dest.writeString(introduction);
                dest.writeString(sex);
                dest.writeString(ethnic);
                dest.writeString(education);
                dest.writeString(age);
                dest.writeString(politicalFace);
                dest.writeString(practisingYear);
                dest.writeString(roleId);
                dest.writeString(worktime);
                dest.writeString(teamSize);
                dest.writeString(birthday);
                dest.writeString(isMongolian);
                dest.writeString(licenseForm);
                dest.writeString(businessExpertise);
                dest.writeString(mediatorType);
                dest.writeString(idCard);
                dest.writeString(fax);
                dest.writeString(scopeOfBussess);
                dest.writeString(practisingTime);
                dest.writeString(mainOrgans);
                dest.writeString(status);
                dest.writeString(isAidLawyer);
                dest.writeString(officeId);
                dest.writeString(loginStatus);
                dest.writeString(loginStatusTime);
                dest.writeString(organization);
                dest.writeString(certificateType);
            }

            public static class AreaBean implements Parcelable {
                /**
                 * id : 7
                 * name : 锡林浩特市
                 * sort : 30
                 * parentId : 0
                 */

                private String id;
                private String name;
                private String sort;
                private String parentId;

                protected AreaBean(Parcel in) {
                    id = in.readString();
                    name = in.readString();
                    sort = in.readString();
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

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
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
                    dest.writeString(sort);
                    dest.writeString(parentId);
                }
            }

            public static class TownBean implements  Parcelable {
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
        }
    }
}
