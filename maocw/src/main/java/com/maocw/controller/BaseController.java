package com.maocw.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;



/**
 * 
 * <br>
 * <b>类描述:</b>
 * 
 * <pre>
 * 所有Controller的基类
 * </pre>
 * 
 * @see
 * @since
 */
public class BaseController {

		protected static final String ERROR_MSG_KEY = "errorMsg";
		
		//@Autowired
		//SessionRegistry sessionRegistry;

		@InitBinder
		private void initBinder(WebDataBinder binder) {
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
			binder.registerCustomEditor(Date.class, dateEditor);
			binder.registerCustomEditor(Timestamp.class, dateEditor);
			StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(
					Boolean.TRUE);
			binder.registerCustomEditor(String.class, stringTrimmerEditor);
		}
		
		/**
		 * 获取保存在Session中的对象
		 * 
		 * @param request
		 * @return
		 */
		public Object getSessionObject(HttpServletRequest request, String key) {
			return (Object) request.getSession().getAttribute(key);
		}

		
		/**
		 * 获取基于应用程序的url绝对路径
		 * 
		 * @param request
		 * @param url
		 *            以"/"打头的URL地址
		 * @return 基于应用程序的url绝对路径
		 */
		public final String getAppbaseUrl(HttpServletRequest request, String url) {
			Assert.hasLength(url, "url不能为空");
			Assert.isTrue(url.startsWith("/"), "必须以/打头");
			return request.getContextPath() + url;
		}
			    
		@ModelAttribute("numUsers")
		public int getNumberOfUsers() {
		   //return sessionRegistry.getAllPrincipals().size();
		   return new Integer(100);
		}
}
