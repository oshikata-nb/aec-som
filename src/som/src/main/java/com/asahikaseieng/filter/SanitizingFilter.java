/*
 * Created on 2007/04/30
 *
 * $Id$
 * $copyright$
 *
 */
package com.asahikaseieng.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * 不正な文字をサニタイズするFilter.
 * @author jbd
 */
public class SanitizingFilter implements Filter {

	/**
	 * コンストラクタ.
	 */
	public SanitizingFilter() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public final void init(final FilterConfig filterConfig)
			throws ServletException {
	}

	/**
	 * {@inheritDoc}
	 */
	public final void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		chain.doFilter(new SanitizedRequestWrapper(
						(HttpServletRequest) request), response);
	}

	/**
	 * {@inheritDoc}
	 */
	public void destroy() {
	}
}
