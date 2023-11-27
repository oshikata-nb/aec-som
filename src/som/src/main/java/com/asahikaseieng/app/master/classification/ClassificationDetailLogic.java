/*
 * Created on 2008/06/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.classification;

import java.math.BigDecimal;

import com.asahikaseieng.dao.entity.classification.Classification;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 分類マスタ詳細 ロジッククラス interface.
 * @author tosco
 */
public interface ClassificationDetailLogic {

	/**
	 * 検索処理を行う.分類マスタ
	 * @param dataType ﾃﾞｰﾀ種別
	 * @param dataTotalDivision データ集計区分
	 * @param categoryDivision 分類コード
	 * @throws NoDataException NoDataException
	 * @return Classification 詳細データ
	 */
	Classification getEntity(final BigDecimal dataType,
			final BigDecimal dataTotalDivision, final String categoryDivision)
			throws NoDataException;

	/**
	 * 更新処理を行う.
	 * @param bean 更新対象ビーン
	 * @throws NoDataException データ無し例外
	 */
	void update(final Classification bean) throws NoDataException;

	/**
	 * 登録処理を行う.
	 * @param bean 登録対象ビーン
	 */
	void insert(final Classification bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象ビーン
	 * @throws NoDataException データ無し例外
	 */
	void delete(final Classification bean) throws NoDataException;

	/**
	 * 検索処理を行う.分類マスタ
	 * @param dataType ﾃﾞｰﾀ種別
	 * @param categoryDivision 分類コード
	 * @return ClassificationDetail 詳細データ
	 * @throws NoDataException データ無し例外
	 */
	ClassificationDetail getDetailEntity(final BigDecimal dataType,
			final String categoryDivision) throws NoDataException;

}
