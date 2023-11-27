/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.seasar.aop.interceptors;

import java.util.Iterator;
import java.util.List;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

/**
 * プロパティーを編集処理する為の移送を行なうInterceptor.
 * @author jbd
 */
public class PropertyTransferInitInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -1654693477093057726L;

	/* オリジナルのInterceptor */
	private MethodInterceptor interceptor;

	/**
	 * コンストラクタ.
	 * @param interceptor インタセプタ
	 */
	public PropertyTransferInitInterceptor(final MethodInterceptor interceptor) {
		this.interceptor = interceptor;
	}

	/**
	 * Logオブジェクトを返す.
	 * @return Logオブジェクト
	 */
	protected final Log getLog() {
		return LogFactory.getLog(this.getClass());
	}

	/**
	 * {@inheritDoc}
	 */
	public Object invoke(final MethodInvocation invocation) throws Throwable {
		Object ret = interceptor.invoke(invocation);
		callInit(ret);
		return ret;
	}

	/**
	 * 初期化を実行する.
	 * @param object Object
	 * @throws Throwable 例外
	 */
	protected void callInit(final Object object) throws Throwable {
		if (object == null) {
			return;
		}
		if (object instanceof PropertyTransferCallbacker) {
			((PropertyTransferCallbacker) object).init();
			if (getLog().isDebugEnabled()) {
				getLog().debug("init : " + object.getClass().getName());
			}
		} else if (object instanceof List) {

			List l = (List) object;

			// s2daoの特性上１番目がProper～なら後も全て同じ
			if (!l.isEmpty() && l.get(0) instanceof PropertyTransferCallbacker) {
				for (Iterator i = l.iterator(); i.hasNext();) {
					Object o = i.next();
					((PropertyTransferCallbacker) o).init();
					if (getLog().isDebugEnabled()) {
						getLog().debug("init : " + o.getClass().getName());
					}
				}
			}
		}
	}
}
