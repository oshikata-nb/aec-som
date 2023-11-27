/*
 * Created on 2007/04/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.report;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * WebappDataSource.
 * @author jbd
 */
public final class WebappDataSource implements JRDataSource {

	private boolean isNext;

	/**
	 * コンストラクタ.
	 */
	public WebappDataSource() {
		// 初期化
		isNext = false;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean next() throws JRException {
		if (!isNext) {
			isNext = true;
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getFieldValue(final JRField arg0) throws JRException {
		return null;
	}

}
