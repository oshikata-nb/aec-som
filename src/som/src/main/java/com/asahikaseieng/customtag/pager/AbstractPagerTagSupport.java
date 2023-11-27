/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.customtag.pager;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.asahikaseieng.pager.Pager;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.taglib.nested.NestedNameSupport;
import org.apache.struts.taglib.nested.NestedPropertyHelper;

/**
 * Pager用TagSupportの抽象クラス.
 * @author jbd
 */
public abstract class AbstractPagerTagSupport extends TagSupport implements
		NestedNameSupport {

	private static final long serialVersionUID = 1L;

	private String name;

	private String property;

	private String originalName;

	private String originalProperty;

	/**
	 * コンストラクタ.
	 */
	public AbstractPagerTagSupport() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setProperty(final String property) {
		this.property = property;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * {@inheritDoc}
	 */
	public int doStartTag() throws JspException {
		// get the original properties
		originalName = getName();
		originalProperty = getProperty();

		// request
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		// set the properties
		NestedPropertyHelper.setNestedProperties(request, this);
		return super.doStartTag();
	}

	/**
	 * {@inheritDoc}
	 */
	public int doEndTag() {
		// reset the properties
		setName(originalName);
		setProperty(originalProperty);
		return EVAL_PAGE;
	}

	/**
	 * {@inheritDoc}
	 */
	public void release() {
		super.release();
		name = null;
		property = null;
		originalName = null;
		originalProperty = null;
	}

	/**
	 * FormからPagerを取得する。
	 * @return Pager
	 * @throws JspException JspException
	 */
	protected Pager getPager() throws JspException {
		Object variable = null;
		// beanから値を探す
		if (name != null) {
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			Object bean = request.getSession(false).getAttribute(name);
			if (property != null) {
				if (bean == null) {
					throw new JspException("No bean found under attribute key "
							+ name);
				}
				try {
					variable = PropertyUtils.getProperty(bean, property);
				} catch (InvocationTargetException e) {
					Throwable t = e.getTargetException();
					if (t == null) {
						t = e;
					}
					throw new JspException("Exception accessing property "
							+ property + " for bean " + name + " : "
							+ t.toString());
				} catch (NoSuchMethodException e) {
					throw new JspException(e);
				} catch (IllegalAccessException e) {
					throw new JspException(e);
				}
			} else {
				variable = bean;
			}
		} else {
			throw new JspException("No selector attribute (name) was specified");
		}

		if (variable == null) {
			throw new JspException("No pager");
		}
		return (Pager) variable;
	}
}
