package com.zhengaobin.cms.comon;

/**
 * @author 郑奥斌
 *
 * 2019年10月25日
 */
public class CmsExceptionView extends RuntimeException{
	
	private static final long serialVersionUID = -4458498503701201939L;

	public CmsExceptionView(String msg) {
		super(msg);
	}
	
}
