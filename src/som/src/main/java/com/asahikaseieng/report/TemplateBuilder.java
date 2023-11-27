/*
 * Created on 2007/04/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.report;

import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * TemplateBuilderインタフェース.
 * @author jbd
 */
public interface TemplateBuilder {

	/**
	 * JasperPrintを作成する.
	 * @param parameters パラメータ
	 * @return JasperPrint
	 * @throws JRException JRException
	 */
	JasperPrint createJasperPrint(Map parameters) throws JRException;
}
