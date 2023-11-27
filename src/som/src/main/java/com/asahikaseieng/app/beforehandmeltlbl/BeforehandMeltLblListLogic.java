/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.beforehandmeltlbl;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.entity.labelyobiyokai.LabelYobiyokai;
import com.asahikaseieng.dao.nonentity.beforehandmeltlbl.BeforehandMeltLblList;
import com.asahikaseieng.dao.nonentity.beforehandmeltlbl.BeforehandMeltLblPagerCondition;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblDetailListForReport;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblFormulaListForReport;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblInspectionListForReport;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblListConditionForReport;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblListForReport;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblProcedureListForReport;
import com.asahikaseieng.dao.nonentity.comboboxes.beforehandmeltlbl.BeforehandMeltLblLineForComboboxes;
import com.asahikaseieng.exception.NoDataException;

/**
 * 予備溶解ラベル発行画面 interface
 * @author tosco
 */
public interface BeforehandMeltLblListLogic {

	/**
	 * 予備溶解ラベル発行画面一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<BeforehandMeltLblList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<BeforehandMeltLblList> getSearchList(
			final BeforehandMeltLblPagerCondition condition)
			throws NoDataException;

	/**
	 * 予備溶解ラベル発行処理
	 * @param bean 予備溶解ラベル発行一覧データ
	 * @param loginUserId ログインユーザ
	 * @throws Exception 例外
	 * @return List<LabelYobiyokai> 登録用予備溶解ラベルリスト
	 */
	List<LabelYobiyokai> insertLabelYobiyokai(final BeforehandMeltLblList bean,
			final String loginUserId) throws Exception;

	/**
	 * 計装I/Fテーブルの登録処理を行う. : 予備溶解ラベル
	 * @param list 登録予備溶解ラベルリスト
	 * @param directionNo 製造指図番号
	 * @throws BeforehandMeltLblLogicException 更新エラー
	 */
	void insertIfTable(final List<LabelYobiyokai> list, final String directionNo)
			throws BeforehandMeltLblLogicException;

	/**
	 * 生産ラインを全件取得する
	 * @return List<BeforehandMeltLblForComboboxes>
	 */
	List<BeforehandMeltLblLineForComboboxes> getAllLines();

	/**
	 * 生産ラインコンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createLineCombobox(final boolean zero);

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<BeforehandMeltLblListForReport> 検索結果リスト
	 */
	List<BeforehandMeltLblListForReport> getHeaderReportList(
			final BeforehandMeltLblListConditionForReport condition);

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<BeforehandMeltLblDetailListForReport> 検索結果リスト
	 */
	List<BeforehandMeltLblDetailListForReport> getDetailReportList(
			final BeforehandMeltLblListConditionForReport condition);

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<BeforehandMeltLblDetailListForReport> 検索結果リスト
	 */
	List<BeforehandMeltLblProcedureListForReport> getProcedureReportList(
			final BeforehandMeltLblListConditionForReport condition);

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<BeforehandMeltLblFormulaListForReport> 検索結果リスト
	 */
	List<BeforehandMeltLblFormulaListForReport> getFormulaReportList(
			final BeforehandMeltLblListConditionForReport condition);

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<BeforehandMeltLblInspectionListForReport> 検索結果リスト
	 */
	List<BeforehandMeltLblInspectionListForReport> getInspectionReportList(
			final BeforehandMeltLblListConditionForReport condition);

}
