/*
 * Created on 2022/05/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.common.pdfreportdecorator;

import java.math.BigDecimal;

/**
 * PdfReportDecoratorDaoクラス
 * @author S.Fujimaki
 */
public interface PdfReportDecoratorDao {

	/** BEANアノテーション */
	Class<PdfReportDecorator> BEAN = com.asahikaseieng.dao.nonentity.common.pdfreportdecorator.PdfReportDecorator.class;

	/**
	 * 連番取得
	 * @return シーケンス
	 */
	BigDecimal getPdfReportSeq();
}
