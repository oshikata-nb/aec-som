/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.estimate;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.entity.estimate.Estimate;
import com.asahikaseieng.dao.nonentity.estimatebalancelist.EstimateBalanceList;
import com.asahikaseieng.dao.nonentity.estimatedetaillist.EstimateDetailList;
import com.asahikaseieng.dao.nonentity.estimateduplicatelist.EstimateDuplicateList;
import com.asahikaseieng.dao.nonentity.master.balancedetail.BalanceDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 見積/単価詳細ロジック interface.
 * @author t0011036
 */
public interface EstimateDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param estimateNo estimateNo
	 * @param consecutiveNo consecutiveNo
	 * @throws NoDataException NoDataException
	 * @return Estimate
	 */
	Estimate getEntity(final String estimateNo, final BigDecimal consecutiveNo)
			throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param estimateNo estimateNo
	 * @return List<EstimateDetailList>
	 */
	List<EstimateDetailList> getList(final String estimateNo);

	/**
	 * 帳合一覧検索処理を行う.
	 * @param balanceCd balanceCd
	 * @return List<EstimateBalanceList>
	 */
	List<EstimateBalanceList> getBalanceList(final String balanceCd);

	/**
	 * 帳合検索処理を行う.
	 * @param balanceCd 帳合コード
	 * @return BalanceDetail
	 */
	BalanceDetail getBalanceEntity(final String balanceCd);

	/**
	 * 品目検索処理を行う.
	 * @param itemCd 品目コード
	 * @return ItemDetail
	 */
	ItemDetail getItemEntity(final String itemCd);

	/**
	 * 帳合＆品目重複チェック
	 * @param estimateNo 見積番号
	 * @param balanceCd 帳合コード
	 * @param itemCd 品目コード
	 * @param strEstimateValidDateFrom 見積有効期限(FROM)
	 * @param strEstimateValidDateTo 見積有効期限(TO)
	 * @return List<EstimateDuplicate>
	 */
	List<EstimateDuplicateList> getDuplicate(final String estimateNo,
			final String balanceCd, final String itemCd,
			final String strEstimateValidDateFrom,
			final String strEstimateValidDateTo);

	/**
	 * 見積番号取得
	 * @return EstimateNo
	 * @throws NoDataException NoDataEsception
	 */
	String getEstimateNo() throws NoDataException;

	/**
	 * 見積/単価一括削除を行う.
	 * @param estimateNo estimateNo
	 * @param consecutiveNo consecutiveNo
	 * @return 削除件数
	 */
	// int deleteEstimateList(final String estimateNo);
	int deleteEstimateList(final String estimateNo,
			final BigDecimal consecutiveNo);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Estimate bean);

	/**
	 * 追加処理を行う.
	 * @param frm 追加対象データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws NoDataException NoDataException
	 */
	void insert(final EstimateDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException,
			NoDataException;

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Estimate bean) throws NoDataException;

	/**
	 * ステータス更新
	 * @param frm 更新対象データ
	 * @param status ステータス
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	void statusUpdate(final EstimateDetailForm frm, final BigDecimal status,
			final String tantoCd) throws NoDataException;
}
