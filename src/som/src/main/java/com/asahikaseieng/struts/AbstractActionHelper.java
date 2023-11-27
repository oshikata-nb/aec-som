/*
 * Created on 2007/03/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;

import com.asahikaseieng.pager.Pager;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionServletWrapper;
import org.apache.struts.util.LabelValueBean;
import org.seasar.dao.pager.PagerCondition;

/**
 * AbstractActionの機能を助けるヘルパークラス.
 * @author jbd
 */
public final class AbstractActionHelper {

	/**
	 * コンストラクタ.
	 */
	private AbstractActionHelper() {
	}

	/**
	 * オブジェクトのプロパティを取得し、それぞれをcallbackメソッドの引数に渡す.
	 * @param object Object
	 * @throws Exception 例外
	 */
	public static void propertyCheck(final Object object) throws Exception {
		PropertyDescriptor[] dsc = PropertyUtils.getPropertyDescriptors(object);
		for (int i = 0; i < dsc.length; i++) {
			try {
				callback(PropertyUtils.getProperty(object, dsc[i].getName()));
			} catch (IllegalAccessException e) {
				continue;
			} catch (InvocationTargetException e) {
				continue;
			} catch (NoSuchMethodException e) {
				continue;
			}
		}
	}

	/**
	 * callbackを実行する.
	 * @param object Object
	 * @throws Exception 例外
	 */
	public static void callback(final Object object) throws Exception {

		if (object == null) {
			return;
		}

		if (object instanceof PropertyTransferCallbacker) {

			((PropertyTransferCallbacker) object).callback();

//			if (CustomLog.isDebugEnabled()) {
//				CustomLog.debug("callback : " + object.getClass().getName());
//			}

			// s2daoを利用している以上当該interfaceを実装した構造のネストはない
			return;

		} else if (object instanceof Collection) {

			// リスト内の全オブジェクトを再帰的にチェックする
			Collection col = (Collection) object;

			for (Iterator ite = col.iterator(); ite.hasNext();) {
				// LabelValueBeanを発見したら、そのListはbreak
				// スピードアップの為!
				Object o = ite.next();
				if (o instanceof LabelValueBean) {
					break;
				}

				callback(o);
			}

			return;

		} else if (object instanceof String) {
			return;
		} else if (object instanceof Class) {
			return;
		} else if (object instanceof Comparable) {
			return;
		} else if (object instanceof Boolean) {
			return;
		} else if (object instanceof Pager) {
			return;
		} else if (object instanceof PagerCondition) {
			return;
		} else if (object instanceof ActionServletWrapper) {
			return;
		}

//		if (CustomLog.isDebugEnabled()) {
//			CustomLog.debug("recursive propertyCheck:" + object.getClass());
//		}

		// プロパティを再帰的にチェック
		propertyCheck(object);
	}
}
