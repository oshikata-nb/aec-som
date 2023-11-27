/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.util.List;
import java.util.Map;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.direction.DirectionLineForComboboxes;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderListPagerCondition;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderDetailListForReport;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderFormulaListForReport;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderInspectionListForReport;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderListConditionForReport;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderListForReport;
import com.asahikaseieng.dao.nonentity.directionorderforreport.DirectionOrderProcedureListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造指図検索 ロジッククラス interface.
 * @author tosco
 */
public interface DirectionListLogic {

	/**
	 * 検索一覧検索処理
	 * @param condition 検索条件
	 * @return List<MgrecipeList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<DirectionDirectionHeaderList> getSearchList(
			DirectionDirectionHeaderListPagerCondition condition)
			throws NoDataException;

	/**
	 * 生産ラインを全件取得する
	 * @return List<MgrecipeLine>
	 */
	List<DirectionLineForComboboxes> getAllLines();

	/**
	 * 生産ラインコンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createLineCombobox();

	/**
	 * 製造指図ヘッダの更新を行う。
	 * @param bean データ
	 * @throws DirectionLogicException 更新エラーの場合
	 */
	void updateDirectionHeader(final DirectionDirectionHeaderList bean)
			throws DirectionLogicException;

	/**
	 * 製造指図書発行
	 * @param list List<DirectionDirectionHeaderList>
	 * @param tantoCd 担当者コード
	 * @return [true:OK][false:NG]
	 * @throws DirectionLogicException エラーが発生した場合
	 */
	boolean issuance(List<DirectionDirectionHeaderList> list, String tantoCd)
			throws DirectionLogicException;

	/**
	 * 製造指図書発行
	 * @param bean 製造指図一覧データ
	 * @param tantoCd 担当者コード
	 * @return Map<String, List<String>> タンク情報
	 * @throws DirectionLogicException エラー発生時
	 */
	Map<String, List<String>> issuance(final DirectionDirectionHeaderList bean,
			final String tantoCd) throws DirectionLogicException;

	/**
	 * 計装I/Fテーブルの登録処理を行う. : 製造計画,製造指示
	 * @param bean 製造指図一覧データ
	 * @param map タンク情報
	 * @throws Exception 例外
	 */
	void insertIfTable(final DirectionDirectionHeaderList bean,
			final Map<String, List<String>> map) throws Exception;

	/**
	 * 指図発行時に手持ち在庫をチェックして不足なら警告を出す。
	 * @param directionNo 製造指図番号
	 * @return 在庫不足量警告メッセージ
	 * @throws DirectionLogicException 例外
	 */
	List<String> checkInventoryFormula(final String directionNo)
			throws DirectionLogicException;

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionOrderListForReport> 検索結果リスト
	 */
	List<DirectionOrderListForReport> getHeaderReportList(
			final DirectionOrderListConditionForReport condition);

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionOrderDetailListForReport> 検索結果リスト
	 */
	List<DirectionOrderDetailListForReport> getDetailReportList(
			final DirectionOrderListConditionForReport condition);

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionOrderDetailListForReport> 検索結果リスト
	 */
	List<DirectionOrderProcedureListForReport> getProcedureReportList(
			final DirectionOrderListConditionForReport condition);

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionOrderFormulaListForReport> 検索結果リスト
	 */
	List<DirectionOrderFormulaListForReport> getFormulaReportList(
			final DirectionOrderListConditionForReport condition);

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionOrderInspectionListForReport> 検索結果リスト
	 */
	List<DirectionOrderInspectionListForReport> getInspectionReportList(
			final DirectionOrderListConditionForReport condition);

}
