/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salestermsandestimate;

import java.util.List;

import com.asahikaseieng.dao.entity.master.salestermsandestimate.SalestermsAndEstimate;
import com.asahikaseieng.dao.nonentity.comboboxes.salestermsandestimate.SalestermsandestimateNamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatedetail.SalestermsAndEstimateDetail;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimateitemcheck.SalestermsAndEstimateItemCheck;
import com.asahikaseieng.exception.NoDataException;

/**
 * 販売条件・見積単価 コピー作成・削除詳細ロジック interface.
 * @author t0011036
 */
public interface SalestermsAndEstimateDetailLogic {
	/**
	 * 検索処理を行う（詳細画面遷移用）.
	 * @param pkNo pkNo
	 * @return SalestermsAndEstimateDetail
	 */
	SalestermsAndEstimateDetail getDetailEntity(final String pkNo);
	
	/**
	 * 品目検索処理を行う.
	 * @param itemCd 品目コード
	 * @return ItemDetail
	 */
	ItemDetail getItemEntity(final String itemCd);

	/**
	 * 品目検索処理を行う.
	 * @param itemCd 品目コード
	 * @return ItemQueueLastVersion
	 */
	ItemQueueLastVersion getItemQueueEntity(final String itemCd);
	
	/**
	 * 販売条件マスタの該当品目有無確認用.
	 * @param itemCd 品目コード
	 * @return SalestermsAndEstimateItemCheck
	 */
	SalestermsAndEstimateItemCheck getSalestermsItemCount(final String itemCd);
	
	/**
	 * 見積ファイルの該当品目有無確認用.
	 * @param itemCd 品目コード
	 * @return SalestermsAndEstimateItemCheck
	 */
	SalestermsAndEstimateItemCheck getEstimateItemCount(final String itemCd);

	/**
	 * 更新処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final SalestermsAndEstimate bean);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void insert(final SalestermsAndEstimate bean);
	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final SalestermsAndEstimate bean) throws NoDataException;
	
	/**
	 * 検索処理を行う（更新登録用）.
	 * @param pkNo pkNo
	 * @throws NoDataException NoDataException
	 * @return SalestermsAndEstimate
	 */
	SalestermsAndEstimate getEntity(final String pkNo) throws NoDataException;
	
	/**
	 * 販売条件・見積単価コピー・削除確定or確定取消処理を行う(詳細画面の「確定」or「確定取消」ボタン押下時).
	 * @param frm 販売条件・見積単価コピー・削除詳細画面フォーム
	 * @param tantoCd ログイン者コード
	 */
	void conSalestermsAndEstimate(final SalestermsAndEstimateDetailForm frm, final String tantoCd);
	
	/**
	 * エラーログ出力処理
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final String strErrorCd, final String strErrorMsg,
			final String tantoCd) throws Exception;
	
	/**
	 * 処理区分取得
	 * @return List<SalestermsandestimateNamesListForComboboxes>
	 */
	List<SalestermsandestimateNamesListForComboboxes> getProcessDivisionList();

}
