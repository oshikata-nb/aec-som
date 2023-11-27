/*
 * Created on 2008/02/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inquiry;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.inquiryinventorycount.InquiryInventoryCount;
import com.asahikaseieng.dao.nonentity.inquirylocationcount.InquiryLocationCount;
import com.asahikaseieng.dao.nonentity.inquirypreparationlist.InquiryPreparationList;

/**
 * 棚卸準備処理 ロジック interface.
 * @author tanaka
 */
public interface InquiryPreparationLogic {
	/**
	 * 登録処理
	 * @param frm 登録対象データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	void regist(final InquiryPreparationForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 棚卸区分取得
	 * @return List<NamesListForComboboxes>
	 */
	List<NamesListForComboboxes> getCountDivisionList();

	/**
	 * ロケーション棚卸検索
	 * @param countDivision 棚卸区分
	 * @return InquiryLocationCount
	 */
	InquiryLocationCount getLocationCount(final String countDivision);

	/**
	 * 棚卸準備検索
	 * @param countDate 棚卸準備処理日
	 * @param countDivision 棚卸区分
	 * @return List<InquiryPreparationList>
	 */
	List<InquiryPreparationList> getList(final Timestamp countDate,
			final String countDivision);

	/**
	 * 棚卸準備検索
	 * @param countDate 棚卸準備処理日
	 * @param countDivision 棚卸区分
	 * @param countLocation ロケーションコード
	 * @param itemCd 品目コード
	 * @param lotNo ロット番号
	 * @param countUpdateDate 棚卸更新日
	 * @return InquiryInventoryCount
	 */
	InquiryInventoryCount getInventoryCount(final Timestamp countDate,
			final String countDivision, final String countLocation,
			final String itemCd, final String lotNo,
			final Timestamp countUpdateDate);
}
