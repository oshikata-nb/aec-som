/*
 * Created on 2008/02/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inquiry;

import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.inventorycountdetail.InventoryCountDetail;

/**
 * 棚卸準備キャンセル処理 ロジック interface.
 * @author tanaka
 */
public interface InquiryPreparationCancelLogic {
	/**
	 * 一括削除処理
	 * @param frm 削除データ
	 * @return 削除件数
	 */
	int deleteInventoryCountList(final InquiryPreparationCancelForm frm);

	/**
	 * 棚卸区分取得
	 * @return List<NamesListForComboboxes>
	 */
	List<NamesListForComboboxes> getCountDivisionList();

	/**
	 * 棚卸検索
	 * @param countDate 棚卸準備処理日
	 * @param countDivision 棚卸区分
	 * @return InventoryCountDetail
	 */
	InventoryCountDetail getEntity(final java.sql.Timestamp countDate,
			final String countDivision);
}
