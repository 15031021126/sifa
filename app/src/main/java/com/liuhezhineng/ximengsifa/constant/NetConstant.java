package com.liuhezhineng.ximengsifa.constant;

/**
 * @author AIqinfeng
 * @date 2018/4/17
 * @description 切换 ip port 的时候别忘了切换下面的视频 ip port, 文件 ip port.
 */

public interface NetConstant {

    /**
     * 正式环境 www.xlgl12348.gov.cn
     */
    String BASE_URL = "http://61.134.97.201/WisdomJustice";
    String FILE_URL = "http://61.134.97.201";
    /**
     * 测试环境
     */
//    String BASE_URL = "http://61.134.97.201:8109/WisdomJustice";
//    String FILE_URL = "http://61.134.97.201:8109";

    String INTEGRATE_QUERY = BASE_URL + "/api/200/640/10";
    String STATISTICAL_ANALYSIS = BASE_URL + "/api/200/640/20";
    String GET_SERVICE = BASE_URL + "/api/100/8030/10";
    String GET_SERVICES = BASE_URL + "/api/100/8030/30";
    String GET_PERSON_INSTITUTIONS_CATEGORY = BASE_URL + "/api/100/8030/70";
    String GET_HOME_SERVICES = BASE_URL + "/api/100/8030/80";
    String GET_SERVICE_BY_CATEGORY_TYPE = BASE_URL + "/api/100/300/50";
    String GET_VERIFICATION_CODE = BASE_URL + "/api/100/400/10";
    String REGISTER = BASE_URL + "/api/100/400/20";
    String FORGET_PWD = BASE_URL + "/api/100/400/30";
    String LOGIN = BASE_URL + "/api/100/400/40";
    String GET_USER_INFO = BASE_URL + "/api/100/400/50";
    /**
     * 查询人员或机构
     */
    String POST_PEOPLE = BASE_URL + "/api/100/500/50";
    //复议申请  /api/200/730/10
    String GET_FUYI = BASE_URL + "/api/200/730/10";
    String GET_TOWN = BASE_URL + "/api/100/530/140";

    //    行政复议审核同意申请
    String POST_AGREE = BASE_URL + "/api/200/730/20";
    String POST_NOAGREE = BASE_URL + "/api/200/730/30";
    //    accept
    String POST_ACCEPT = BASE_URL + "/api/200/730/40";
    ///api/200/730/50
//    accept
    String POST_NOACCEPT = BASE_URL + "/api/200/730/50";

    String GET_INSTITUTIONS = BASE_URL + "/api/100/500/20";
    String GET_PEOPLE = BASE_URL + "/api/100/530/100";
    String GET_CITY = BASE_URL + "/api/100/500/40";
    String GET_TYPE = BASE_URL + "/api/100/510/10";
    String GET_SUB_TYPE = BASE_URL + "/api/100/510/20";
    String REQUEST_LAW_HELP = BASE_URL + "/api/200/520/10";
    String LEGAL_AID_WORKFLOW = BASE_URL + "/api/200/520/20";
    String LEGAL_AID_WORKFLOW_INFO = BASE_URL + "/api/200/520/50";
    String GET_OTHER_BUSINESS_WORKFLOW = BASE_URL + "/api/100/8030/60";
    String APP_UPDATE = BASE_URL + "/api/100/8030/90";
    String GET_UPCOMING = BASE_URL + "/api/200/530/10";
    String GET_HAVE_DONE = BASE_URL + "/api/200/530/20";
    String GET_LEGAL_AID_WORKFLOW = BASE_URL + "/api/200/530/40";
    String GET_LAWYER = BASE_URL + "/api/200/530/50";
    String GET_LEGAL_PERSON = BASE_URL + "/api/200/530/60";
    String GET_COMMIT_BUSINESS = BASE_URL + "/api/200/530/110";
    /**
     * 获取法援律师
     */
    String GET_AID_LAWYER = BASE_URL + "/api/200/530/130";
    String GET_USE_INFO_VIA_USER_ID = BASE_URL + "/api/200/700/70";
    String GET_LOCAL_MSG = BASE_URL + "/api/200/950/10";
    String GET_LOCAL_MSG_DETAILS = BASE_URL + "/api/200/950/20";
    /**
     * 申请
     */
    String APPLY_FOR_PEOPLES_MEDIATION = BASE_URL + "/api/200/540/20";
    /**
     * 司法局审核，科员审核，重新提交
     */
    String REVIEW = BASE_URL + "/api/200/540/30";
    /**
     * 登记
     */
    String REGISTER_FROM = BASE_URL + "/api/200/540/21";
    /**
     * 调查
     */
    String RECORD_FORM = BASE_URL + "/api/200/540/22";
    /**
     * 调解
     */
    String MEDIATE_RECORD_FORM = BASE_URL + "/api/200/540/23";
    /**
     * 协议
     */
    String AGREEMENT_FORM = BASE_URL + "/api/200/540/24";
    /**
     * 回访
     */
    String RETURN_VISIT_FORM = BASE_URL + "/api/200/540/25";
    /**
     * 卷宗
     */
    String DOSSIER_FORM = BASE_URL + "/api/200/540/26";
    String SAVE_MEDIATION_REQUEST = BASE_URL + "/api/200/540/10";
    String SAVE_REGISTER = BASE_URL + "/api/200/540/11";
    String SAVE_RECORD = BASE_URL + "/api/200/540/12";
    String SAVE_MEDIATION_RECORD = BASE_URL + "/api/200/540/13";
    String SAVE_AGREEMENT = BASE_URL + "/api/200/540/14";
    String SAVE_RETURN_VISIT = BASE_URL + "/api/200/540/15";
    String SAVE_DOSSIER = BASE_URL + "/api/200/540/16";
    String GET_PEOPLES_MEDIATION_WORKFLOW = BASE_URL + "/api/200/540/40";
    String GET_WORKFLOW_INFO = BASE_URL + "/api/200/540/50";
    String MEDIATION_WORKFLOW_INFO = BASE_URL + "/api/200/540/51";
    String GET_MY_DRAFT = BASE_URL + "/api/200/540/80";
    String GET_DRAFT_DATA = BASE_URL + "/api/200/540/90";
    String GET_ADVISORY_MESSAGES_LIST = BASE_URL + "/api/100/600/10";
    String ADD_ADVISORY_MESSAGES = BASE_URL + "/api/200/600/20";
    String GET_ADVISORY_DETAILS_BY_ID = BASE_URL + "/api/100/600/30";
    String THUMBS_UP = BASE_URL + "/api/200/600/50";
    String ADD_EVALUATION = BASE_URL + "/api/200/600/60";
    String GET_ADVISORY_LIST = BASE_URL + "/api/200/600/70";
    String GET_MY_QA = BASE_URL + "/api/200/600/80";
    String GET_EVALUATION = BASE_URL + "/api/100/600/90";
    String COMMIT_MY_EVALUATE = BASE_URL + "/api/200/600/100";
    String GET_ARTICLE = BASE_URL + "/api/100/601/10";
    String GET_LIST_BY_CATEGORY_ID = BASE_URL + "/api/100/601/20";
    String GET_ARTICLE_DETAILS = BASE_URL + "/api/100/601/30";
    String ADD_ARTICLE_EVALUATION = BASE_URL + "/api/100/601/50";
    String GET_COMPLAINTS_LIST = BASE_URL + "/api/200/610/10";
    String ADD_COMPLAINTS = BASE_URL + "/api/200/610/30";
    String GET_COMPLAINTS_DETAILS = BASE_URL + "/api/100/610/50";
    String COMMIT_FAST_AID = BASE_URL + "/api/100/630/10";
    String COMMIT_FAST_AID_UPDATA = BASE_URL + "/api/100/630/30";
    String UPLOAD_AVATAR = BASE_URL + "/api/200/700/10";
    String UPLOAD_FILE = BASE_URL + "/api/200/700/20";
    String GET_INSTITUTIONS_BY_AREA = BASE_URL + "/api/100/700/50";
    String MODIFY_USER_INFO = BASE_URL + "/api/200/700/60";
    String GET_WEATHER_INFO = BASE_URL + "/thirdapi/100/8010/20";
    String ZHSK_12348_GOV_CN = "http://zhsk.12348.gov.cn/qa.wap/";
    String A_L_K = "http://alk.12348.gov.cn/";
    String JPUSH = BASE_URL + "/api/100/400/45";

    String GET_ALL_JUDICIAL = BASE_URL + "/api/100/500/60";
    String COMMIT_REMOTE_VISIT_FORM = BASE_URL + "/api/200/1200/10";
    /**
     * 我的咨询，获取已回复/未回复的留言
     */
    String GET_MY_COMMENT = BASE_URL + "/api/200/600/70";
    long DEFAULT_MILLISECONDS = 60000;
    String TOKEN = "token";
    String TAG = "tag";
    String TIMESTAMP = "timestamp";
    String QUERY = "query";
    String USERNAME = "username";
    String PASSWORD = "password";
    String NAME = "name";
    String PAPER_NUM = "papernum";
    String PAGE_NO = "pageNo";
    String PAGE_SIZE = "pageSize";
    String CATEGORY_TYPE = "categoryType";
    String ARTICLE_ID = "articleId";
    int NO_PAGE_SIZE = -1;
    int DEFAULT_PAGE_SIZE = 20;
    String HOT = "1";
    String ARTICLE = "2";
    String FILE = "file";
    long CACHE_TIME = 60 * 60 * 1000;
    String SMS_TOKEN = "smsToken";
    String PWD = "pwd";
    String CODE = "code";
    String LOCATION = "location";
    String CATEGORY_ID = "categoryId";
    String ID = "id";
    String AREA_ID = "areaId";
    String TOWN_ID = "townId";

    interface RemoteVisit {
        String REJECT_OR_PASS = BASE_URL + "/api/200/1200/20";
        String GET_LIST = BASE_URL + "/api/200/1200/30";
        String GET_TO_DO_LIST = BASE_URL + "/api/200/1200/40";
        String GET_DETAILS = BASE_URL + "/api/200/1200/50";
    }

    interface Vidyo {
        String BASE_URL = "http://61.134.97.201:8086/wj_vidyo";
        String CREATE_TEMP_ROOM = BASE_URL + "/mobile/createTempRoom";
        String DELETE_TEMP_ROOM = BASE_URL + "/mobile/deleteTempRoom";
        /**
         * 这里固定到 12348
         */
        String INVITE_CLIENT = BASE_URL + "/vidyo/conference";

        /**
         * 获取 wp 这个用户的所有好友
         */
        String GET_ALL_12348 = BASE_URL + "/vidyo/contacts";

        /**
         * 根据房间分机号获取房间信息
         */
        String GET_ROOM_BY_EXTENSION = BASE_URL + "/mobile/getRoomByExtension";
    }

    interface Notice {

        String REQUEST = BASE_URL + "/api/200/360/10";
        String FLOW = BASE_URL + "/api/100/360/20";
        String NODE = BASE_URL + "/api/200/360/50";
        String LOAD_OFFICE_OR_USER_BY_AREA_AND_TYPE = BASE_URL + "/api/200/360/60";
        String DEPRECATE_BUSINESS = BASE_URL + "/api/200/360/70";
    }

    interface FastLegal {

        String COMMIT_FLOW = BASE_URL + "/api/200/630/20";
        String LOAD_FLOW_INFO = BASE_URL + "/api/200/630/40";
        String GET_INS_OR_PERSON = BASE_URL + "/api/200/530/100";
    }

    interface Video {
        String GET_RECTIFICATION_PERSONNEL = BASE_URL + "/api/100/650/10";
        String UPLOAD_VIDEO_ANALYZE = BASE_URL + "/api/100/650/20";
        String GET_ANALYSIS_RESULT = BASE_URL + "/api/100/680/30";
        String GET_USER_INFO_VIA_ID_CARD = BASE_URL + "/api/100/650/30";
        String GET_ANALYZE_HISTORY = BASE_URL + "/api/100/680/20";
    }

    interface ConsultingComplaint {
        // 获取留言列表
        String GET_ADVISORY_LIST = BASE_URL + "/api/200/600/70";
        // 获取待处理的留言咨询
        String GET_MY_TODO_CONSULTING_LIST = BASE_URL + "/api/200/600/110";
        // 获取投诉列表
        String GET_COMPLAINTS_LIST = BASE_URL + "/api/200/610/10";
        // 获取待处理的投诉列表
        String GET_MY_TODO_COMPLAINT_LIST = BASE_URL + "/api/200/610/60";

        // 留言追问 追答（via commentType）
        String ADD_ADVISORY_QA = BASE_URL + "/api/200/600/40";
        // 留言回复
        String ADD_ADVISORY_REPLY = BASE_URL + "/api/200/600/120";
        // 投诉追问 追答
        String ADD_COMPLAINTS_QA = BASE_URL + "/api/200/610/40";
        // 投诉回复
        String ADD_COMPLAINTS_REPLY = BASE_URL + "/api/200/610/70";

        /* 获取要分配的机构 */
        String GET_DISTRIBUTE_ORGANIZED = BASE_URL + "/api/100/700/80";

        String DISTRIBUTE = BASE_URL + "/api/200/610/90";
    }

    interface Order {
        /**
         * api/100/910/10 申请预约接口
         */
        String REQUEST_ORDER = BASE_URL + "/api/100/910/10";
        /**
         * 获取我的预约列表
         */
        String GET_MY_ORDER_LIST = BASE_URL + "/api/100/910/20";
        /**
         * 获取我的待处理预约列表
         */
        String GET_MY_TODO_ORDER_LIST = BASE_URL + "/api/100/910/30";
        /**
         * 被预约人处理预约
         */
        String RESPONSE_ORDER = BASE_URL + "/api/100/910/40";
    }
}
