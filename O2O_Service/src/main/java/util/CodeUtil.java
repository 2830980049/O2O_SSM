package util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/29 12:42
 */
public class CodeUtil {
    public static boolean check_code(HttpServletRequest request){
        // 获取 生成的验证码
        String code = (String) request.getSession()
                .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String code_true = HttpServletRequestUtil.getString(request, "check_code");
        System.out.println(code+" "+code_true);
        if (code_true == null || !code.equalsIgnoreCase(code_true))
            return false;
        return true;
    }
}
