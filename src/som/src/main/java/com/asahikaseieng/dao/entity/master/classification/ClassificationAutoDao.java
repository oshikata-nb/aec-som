/*
 * Created on 2008/07/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.classification;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * 分類オートコンプリート用Daoクラス
 * @author tosco
 */
public interface ClassificationAutoDao {

	/** BEANアノテーション. */
	Class BEAN = ClassificationAuto.class;

	/** ARGSアノテーション getEntity */
	String getAutoClassificationList_ARGS = "dataType,dataTotalDivision,categoryDivision";

	/**
	 * 分類リスト取得処理.
	 * @param dataType データ種別
	 * @param dataTotalDivision データ集計区分
	 * @param categoryDivision 分類コード
	 * @return 分類のリスト

	 */
	List<ClassificationAuto> getAutoClassificationList(BigDecimal dataType,
										BigDecimal dataTotalDivision,
										String categoryDivision);

}
