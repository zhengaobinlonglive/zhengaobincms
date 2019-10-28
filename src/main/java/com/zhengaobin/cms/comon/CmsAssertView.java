package com.zhengaobin.cms.comon;

/**
 * @author 郑奥斌
 *
 * 2019年10月25日
 */
public class CmsAssertView {
	
	/**
	 *  断言处理
	 * @param expression
	 * @param msg
	 */
	public static void Assert(boolean expression,String msg) {
		if(!expression)
			throw new CmsExceptionView(msg);
	}
	
}
