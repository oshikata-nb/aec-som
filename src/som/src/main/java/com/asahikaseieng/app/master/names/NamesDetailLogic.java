/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.names;

import com.asahikaseieng.dao.entity.master.names.Names;
import com.asahikaseieng.dao.entity.master.numberchkdisit.NumberChkdisit;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 名称詳細ロジック interface.
 * @author t0011036
 */
public interface NamesDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param nameDivision 名称区分
	 * @param nameCd 名称コード
	 * @throws NoDataException NoDataException
	 * @return Names
	 */
	Names getEntity(final String nameDivision, final String nameCd)
			throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param unitDivision 区分
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return NumberChkdisit
	 */
	NumberChkdisit getNumberEntity(final String unitDivision,
			final String venderDivision, final String venderCd);

	/**
	 * 検索処理を行う.
	 * @param nameDivision 名称区分
	 * @param nameCd 名称コード
	 * @return NamesDetail
	 */
	NamesDetail getDetailEntity(final String nameDivision, final String nameCd);

	/**
	 * 登録処理を行う.
	 * @param bean 追加対象データ
	 * @param beanNumber 追加対象データ(数値チェック)
	 */
	void regist(final Names bean, final NumberChkdisit beanNumber);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @param beanNumber 削除対象データ(数値チェック)
	 * @throws NoDataException NoDataException
	 */
	void delete(final Names bean, final NumberChkdisit beanNumber)
			throws NoDataException;
}
