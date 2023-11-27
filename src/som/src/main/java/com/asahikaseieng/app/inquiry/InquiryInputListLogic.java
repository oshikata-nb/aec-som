/*
 * Created on 2008/02/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inquiry;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.inquiryinputlist.InquiryInputList;
import com.asahikaseieng.dao.nonentity.inquiryinputlist.InquiryInputListPagerCondition;
import com.asahikaseieng.dao.nonentity.inquiryinputlistforreport.InquiryInputListConditionForReport;
import com.asahikaseieng.dao.nonentity.inquiryinputlistforreport.InquiryInputListForReport;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 棚卸入力 ロジック interface.
 * @author FPC
 */
public interface InquiryInputListLogic {
	/**
	 * 棚卸一覧検索
	 * @param condition 検索条件
	 * @return List<InquiryInputList>
	 * @throws NoDataException NoDataException
	 */
	List<InquiryInputList> getList(
			final InquiryInputListPagerCondition condition)
			throws NoDataException;

	/**
	 * 棚卸一覧検索（帳票用）
	 * @param condition condition
	 * @return List<InquiryInputListForReport>
	 */
	List<InquiryInputListForReport> getListForReport(
			final InquiryInputListConditionForReport condition);

	/**
	 * 更新処理
	 * @param frm 登録データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	void update(final InquiryInputListForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 棚卸区分取得
	 * @return List<NamesListForComboboxes>
	 */
	List<NamesListForComboboxes> getCountDivisionList();

	/**
	 * 品目検索処理を行う.
	 * @param itemCd 品目コード
	 * @return ItemDetail
	 */
	ItemDetail getItemEntity(final String itemCd);

	/**
	 * ロケーション検索処理を行う.
	 * @param locationCd ロケーションコード
	 * @return LocationDetail
	 */
	LocationDetail getLocationEntity(final String locationCd);
}
