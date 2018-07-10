
package com.huan.lottery.system.controllers;

import org.apache.ibatis.ognl.OgnlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * BaseController.
 */
@RestController
public class BaseController {
	// 跳转
    protected static final String REDIRECT = "redirect:";
    public static final String FIELD_USER_ID = "userId";
    public static final String SALES_PERSON_ID = "salesPersonId";
  
    protected static final String DEFAULT_VIEW_HOME = "";

    protected String getViewPath() {
       
        return DEFAULT_VIEW_HOME;
    }


    private Logger logger = LoggerFactory.getLogger(BaseController.class);


    /**
     * 找到是哪一个父类定义了属性.
     * 
     * @param fromClass
     *            从哪一个类开始查找
     * @param fieldName
     *            属性名
     * @return 定义了指定属性的类,找不到的话,返回fromClass
     */
    private Class findDeclareClass(Class fromClass, String fieldName) {
        Class clazz = fromClass;
        while (clazz.getSuperclass() != null) {
            try {
                clazz.getDeclaredField(fieldName);
                return clazz;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return fromClass;
    }

    private Throwable getRootCause(Throwable throwable) {
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }
        if (throwable instanceof OgnlException && ((OgnlException) throwable).getReason() != null) {
            return getRootCause(((OgnlException) throwable).getReason());
        }
        return throwable;
    }

//    /**
//     * 返回用户ID.
//     *
//     * @param request
//     *            HttpServletRequest
//     * @return userId
//     */
//    protected Long getUserId(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        return (Long) session.getAttribute(User.FIELD_USER_ID);
//    }

   


}
