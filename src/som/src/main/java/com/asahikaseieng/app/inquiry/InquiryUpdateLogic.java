/*
 * Created on 2008/02/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inquiry;

import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.inquiryinventorycount.InquiryInventoryCount;
import com.asahikaseieng.dao.nonentity.inquiryupdatelist.InquiryUpdateList;

/**
 * 棚卸更新処理 ロジック interface.
 * @author tanaka
 */
public interface InquiryUpdateLogic {
	/**
	 * 棚卸区分取得
	 * @return List<NamesListForComboboxes>
	 */
	List<NamesListForComboboxes> getCountDivisionList();

	/**
	 * 棚卸更新検索
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

	/**
	 * 棚卸更新検索
	 * @param countDate 棚卸準備処理日
	 * @param countDivision 棚卸区分
	 * @param countLocation ロケーションコード
	 * @param itemCd 品目コード
	 * @param lotNo ロット番号
	 * @return List<InquiryUpdateList>
	 */
	List<InquiryUpdateList> getList(final Timestamp countDate,
			final String countDivision, final String countLocation,
			final String itemCd, final String lotNo);

	/**
	 * 棚卸更新検索
	 * @param countDate 棚卸準備処理日
	 * @param countDivision 棚卸区分
	 * @return List<InquiryUpdateList>
	 */
	List<InquiryUpdateList> getList(final Timestamp countDate,
			final String countDivision);

	/**
	 * 在庫更新処理
	 * @param frm InquiryUpdateForm
	 * @param tantoCd 担当者コード
	 */
	void stockUpdate(final InquiryUpdateForm frm, final String tantoCd);
}
