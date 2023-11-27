/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.productinspectcomp;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.productinspectcomp.ProductInspectCompLineForComboboxes;
import com.asahikaseieng.dao.nonentity.productinspectcomp.ProductInspectCompList;
import com.asahikaseieng.dao.nonentity.productinspectcomp.ProductInspectCompPagerCondition;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompDetailListForReport;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompFormulaListForReport;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompHeaderListForReport;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompInspectionListForReport;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompListConditionForReport;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompProcedureListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製品検査完了入力一覧 ロジッククラス interface.
 * @author tosco
 */
public interface ProductInspectCompListLogic {

	/**
	 * 製品検査完了入力一覧 検索処理を行う
	 * @param condition 検索条件
	 * @return List<ProductInspectCompList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<ProductInspectCompList> getSearchList(
			final ProductInspectCompPagerCondition condition)
			throws NoDataException;

	/**
	 * 製品検査完了入力の登録処理を行う
	 * @param searchList 検索結果
	 * @param loginUserId ログインユーザ
	 * @throws Exception 例外
	 */
	void update(final List<ProductInspectCompList> searchList,
			final String loginUserId) throws Exception;

	/**
	 * 生産ラインを全件取得する
	 * @return List<ProductInspectCompLineForComboboxes>
	 */
	List<ProductInspectCompLineForComboboxes> getAllLines();

	/**
	 * 生産ラインコンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createLineCombobox(final boolean zero);

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<ProductInspectCompListForReport> 検索結果リスト
	 */
	List<ProductInspectCompHeaderListForReport> getHeaderReportList(
			final ProductInspectCompListConditionForReport condition);

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<ProductInspectCompDetailListForReport> 検索結果リスト
	 */
	List<ProductInspectCompDetailListForReport> getDetailReportList(
			final ProductInspectCompListConditionForReport condition);

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<ProductInspectCompDetailListForReport> 検索結果リスト
	 */
	List<ProductInspectCompProcedureListForReport> getProcedureReportList(
			final ProductInspectCompListConditionForReport condition);

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<ProductInspectCompFormulaListForReport> 検索結果リスト
	 */
	List<ProductInspectCompFormulaListForReport> getFormulaReportList(
			final ProductInspectCompListConditionForReport condition);

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<ProductInspectCompInspectionListForReport> 検索結果リスト
	 */
	List<ProductInspectCompInspectionListForReport> getInspectionReportList(
			final ProductInspectCompListConditionForReport condition);
}
