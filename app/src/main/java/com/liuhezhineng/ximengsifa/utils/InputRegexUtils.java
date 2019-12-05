package com.liuhezhineng.ximengsifa.utils;

import android.text.TextUtils;

/**
 * @author AIqinfeng
 * @date 2018/7/6
 */

public class InputRegexUtils {

	/**
	 * 判断字符串是否符合手机号码格式
	 * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
	 * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
	 * 电信号段: 133,149,153,170,173,177,180,181,189
	 *
	 * @param mobileNums 手机号码
	 * @return 待检测的字符串
	 */
	public static boolean isMobileNO(String mobileNums) {
		// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
		return !TextUtils.isEmpty(mobileNums) && mobileNums.matches(telRegex);
	}

	public static boolean isIdCardNO(String idCardNO) {
		String idCardRegex = "( ^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)";
		return !TextUtils.isEmpty(idCardNO) && idCardNO.matches(idCardRegex);
	}

}
