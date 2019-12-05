package com.liuhezhineng.ximengsifa.constant;

import android.os.Environment;

/**
 * @author AIqinfeng
 * @date 2018/4/19
 * @description 常量
 */

public interface Constant {

    int STATE_REFRESH = 0X11;

    int PHONE_LENGHT = 11;

    String VIDYO_RESPONSE = "vidyo_response";

    /**
     * SharedPreference
     */

    interface SP {

        String USER_INFO = "user_info";
        String IS_NORMAL_USER = "is_normal_user";
        String IS_LOGIN = "is_login";
    }

    String TODO_TYPE = "todo_type";

    int DEFAULT_PAGE_SIZE = 20;

    /**
     * operate 进行的操作
     *     //目前涉及到验证码的操作有：
     *     //用户注册
     *     //用户修改密码
     *     //用户找回密码
     *     //用户修改手机号
     */
    interface SMS {

        String SMS_TOKEN = "smsToken";
        String PWD = "pwd";
        String CODE = "code";

        String OPERATE = "operate";
        String MOBILE = "mobile";
        String REGISTER = "用户注册";
        String MODIFY_PWD = "用户修改密码";
        String FIND_PWD = "用户找回密码";
        String MODIFY_PHONE_NUM = "用户修改手机号";
    }

	String USERNAME = "username";
	String PASSWORD = "password";
	String TOKEN = "token";

	String BASE_FILE_PATH = "/WisdomJustice";
	String AUDIO_PATH = BASE_FILE_PATH + "/Audio/";
	String VIDEO_PATH = BASE_FILE_PATH + "/Video/";

	interface Video {
		String FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.VIDEO_PATH;;
		String FILE_NAME = "personnel_analysis_" + System.currentTimeMillis() + ".mp4";
	}

	String CATEGORY_ID = "categoryId";

	interface Draft {

		String LEGAL_AID = "legal";
		String PEOPLE_MEDIATION = "mediation";
		String NOTICE_DEFENSE = "defense";
	}

	String END = "end";

	String QUERY = "query";
	int REQUEST_USER = 0;
	int AGENT_USER = 1;
	String PICKER_NO_DATA = "-1";
	String LAWYER = "1";
	String AID_LAWYER = "9";
	String AID_CENTER = "5";
	String LEGAL_PERSON = "13";
	String LAWYER_OFFICE = "2";
	String LEGAL_OFFICE = "8";
	String COMMITTEE = "10";
	String MEDIATOR = "11";
	String DEAL_BUSINESS = "deal_business";
	int DEAL_BUSINESS_CODE = 0;
	String UPLOAD_AVATAR = "upload_avatar";
	int UPLOAD_AVATAR_CODE = 0;
	String MALE = "1";
	String FEMALE = "2";
	String NOT_EVALUATED = "0";
	String EVALUATED = "1";
	String CONSULTING = "1";
	String COMPLAINT = "2";
	String LEGAL_AID = "3";
	String MEDIATION = "4";
	String FILE_DATA = "fileData";
	String PAGE_NO = "pageNo";
	String PAGE_SIZE = "pageSize";
	// filter
	String BEGIN_DATE = "beginDate";
	String END_DATE = "endDate";
	String TITLE = "title";
	String ACCUSER = "accuser";
	String DEFENDANT = "defendant";
	String CASE = "case";
	int HEIGHT = 170;
	String POLICY_RELEASE = "1";
	String SERVICE_DYNAMICS = "2";
	String LAWS_AND_REGULATIONS = "3";
	String SERVICE_GUIDE = "4";
	String PAPER_FORMS = "5";
	int SUCCESS = 0;
	String HAVE_DONE = "已办";
	String UP_COMING = "待办";
	String YES = "yes";
	String NO = "no";
	int NORMAL_CODE = 1;
	String BEAN = "bean";
	String STRING = "string";
	String ID = "id";
	String AREA_ID = "areaId";
	int QA = 0;
	String SERVICE_APP_VO = "serverAppVo";
	String ADVISORY = "AdvisoryBean";
	String CASE_SOURCE = "mediate_case_Source";
	String CASE_RANK = "case_rank";
	String DISPUTE_TYPE = "dispute_type";
	int REGISTER = 20;
	int LOGIN = 40;
	int LOGOUT = 50;
	String PHONE_NUM = "phone_num";
	String USER = "user";
	int IMAGE_PICKER = 0;
	int TYPE_LOAD_MORE = 1;
	int TYPE_ITEM = 2;
	String MODULE_TITLE = "module_title";
	int INIT_DATA = 0;
	int LOAD_MORE = 1;
	int REFRESH = 2;
	int COUNT_DOWN_TIME = 1000 * 90;
	int COUNT_DOWN_INTERVAL = 1000;
	int LOCATION_PERMISSION = 0;
	String STATUS = "status";
	String MSG = "msg";
	String BODY = "body";
	String FEEDBACK_CONTENT = "feedback_content";
	String FEEDBACK_CONTACT_WAY = "feedback_contact_way";
	String LOGIN_SUCCESS = "login_success";
	String KEY = "key";
	String ETHNIC = "ethnic";
	String PROXY_TYPE = "legal_aid_proxy_type";
	String CAST_TYPE = "case_type";
	String AID_CATEGORY = "legal_aid_category";
	String CASE_NATURE = "case_nature";
	String ACT_TYPE = "act_type";
	String CASE_CLASSIFY = "oa_case_classify";
	String EDUCATION = "education";
	String PERSONNEL_CATEGORY = "oa_personnel_category";
	String CASE_INVOLVING = "oa_case_involving";
	String CASE_GUILT = "oa_caseGuilt";
	String INFORM_REASON = "oa_informReson";
	String LITIGATION_PHASE = "oa_litigation_phase";
	String BUSINESS = "BusinessBean";
	String PROC_INS_ID = "procInsId";
	String PROC_DEF_ID = "procDefId";
	String PROC_DEF_KEY = "procDefKey";
	String TASK_ID = "taskId";
	String TASK_NAME = "taskName";
	String TASK_DEF_KEY = "taskDefKey";
	String BUSINESS_DATA = "businessData";

	interface DictKey {
        String PARENT_ID = "parentId";

		/**
		 * 案件受理方式
		 */
		String AID_WAY = "fast_case_source";
		String CASE_MONEY = "oa_case_money";

		/**
		 * 人员类别
		 */
		String PEOPLE_TYPE = "oa_personnel_category";

		String CASE_GUILT = "oa_caseGuilt";

		String NATIONALITY = "oa_internation";

		/**
		 * 通知原因
		 */
		String INFORM_REASON = "oa_informReson";
		String CASES_STAGE = "oa_litigation_phase";
		String MODALITY = "oa_modalities";

		String HANDLE_CHANNEL = "oa_handle_channels";
		String CASE_STATE = "oa_act_progress";

        /**
         * 监管场所类型
         */
        String SUPERVISION_PLACE = "supervision_place";
	}

    /**
     * legal_aid 法援中心
     *      *             people_mediation	人民调解
     *      *             apply_lawyer	申请律师
     *      *             legal_service_office基层法律服所
     *      *             apply_appraise	申请鉴定
     *      *             apply_notarization	申请公证
     */
    interface Business {

        // 0: jigou, 1: jigou and renyuan
        String IS_USER = "1";
        String IS_INS = "0";

		String LEGAL_DEFKEY = "reconsider_apply";
		String LEGAL_AID = "legal_aid";
		String PEOPLE_MEDIATION = "people_mediation";
		String FAST_LEGAL = "fast_legal";
		String NOTIFICATION_DEFENSE = "notification_defense";
        String APPLY_LAWYER = "apply_lawyer";
        String LEGAL_SERVICE_OFFICE = "legal_service_office";
        String APPLY_APPRAISE = "apply_appraise";
        String APPLY_NOTARIZATION = "apply_notarization";
	}

	interface Request {

		int SUCCESS = 0;
		int NOT_LOGIN = 403000;
	}

	interface Login {

		int USERNAME_OR_PWD_ERROR = 403001;
	}

	interface Mediation {

		String APPLY = "mediation_shenhe";
		String ACCEPT = "mediation_dengji";
		String INVESTIGATION = "mediation_diaocha";
		String RECORD = "mediation_tiaojie";
		String AGREEMENT = "mediation_xieyi";
		String RETURN_VISIT = "mediation_huifang";
		String ARCHIVE = "mediation_juanzong";
	}

	interface Notice {

		String START = "defense_start";
		String APPROVE = "defense_approve";
		String AID_DIRECTOR = "defense_fyzhuren";
		String UPDATE = "defense_update";
		String ASSIGN_LAWYER = "defense_lszhuren";
		String AID_CENTER_CONFIRM = "defense_confirm";
		String DEAL = "defense_chengbanren_banli";
		String EVALUATE = "defense_pingjia";
		String SUBSIDY = "defense_chengbanren_butie";
	}

	interface FastLegal {

		String START = "fast_start";
		String REVIEW = "fast_shouli";
		String ASSIGN_PEOPLE = "fast_zhiding";
		String DEAL = "fast_banli";
	}
}
