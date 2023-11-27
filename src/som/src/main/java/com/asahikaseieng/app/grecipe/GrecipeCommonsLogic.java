/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.grecipe.GrecipeRecipeProcedureSetpNoForComboboxes;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeItemQueueDetail;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeLabelList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeNameList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeRemarkList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 原処方-共通ロジッククラス interface.
 * @author tosco
 */
public interface GrecipeCommonsLogic {

	/**
	 * レシピステータスを全件取得する
	 * @return List<MgrecipeNameList>
	 */
	List<GrecipeNameList> getAllStatus();

	/**
	 * レシピステータスコンボボックス配列を取得する
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> getStatusComboList();

	/**
	 * ステータスコンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createStatusAllCombobox();

	/**
	 * 品目マスタキューから指定した品目コードの最新バージョンのデータを取得する。
	 * @param itemCd 品目コード
	 * @return GrecipeItemQueueDetail
	 * @throws NoDataException データが存在しない場合
	 */
	GrecipeItemQueueDetail getMaxVersionItemQueue(String itemCd)
			throws NoDataException;

	/**
	 * 処方フォーミュラに登録処理を行う
	 * @param bean GrecipeRecipeFormulaList
	 */
	void insertFormula(GrecipeRecipeFormulaList bean);

	/**
	 * 処方フォーミュラに更新処理を行う
	 * @param bean GrecipeRecipeFormulaList
	 */
	void updateFormula(GrecipeRecipeFormulaList bean);

	/**
	 * 処方プロシージャからレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<GrecipeRecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	List<GrecipeRecipeProcedureList> getProcedure(String recipeId)
			throws NoDataException;

	/**
	 * 処方プロシージャに登録処理を行う
	 * @param bean GrecipeRecipeFormulaList
	 */
	void insertProcedure(GrecipeRecipeProcedureList bean);

	/**
	 * 処方処方検査からレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<GrecipeRecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	List<GrecipeRecipeInspectionList> getInspection(String recipeId)
			throws NoDataException;

	/**
	 * 処方検査に登録処理を行う
	 * @param bean GrecipeRecipeFormulaList
	 */
	void insertInspection(GrecipeRecipeInspectionList bean);

	/**
	 * 処方フォーミュラからレシピIDに紐づくデータをすべて取得
	 * @param recipeId レシピインデックス
	 * @return List<GrecipeRecipeFormulaList>
	 */
	List<GrecipeRecipeFormulaList> findFormulaByRecipeId(String recipeId);

	/**
	 * 処方フォーミュラに一括登録処理を行う
	 * @param list List<GrecipeRecipeFormulaList>
	 */
	void insertFormulaList(List<GrecipeRecipeFormulaList> list);

	/**
	 * 処方フォーミュラに一括更新処理を行う
	 * @param list List<GrecipeRecipeFormulaList>
	 */
	void updateFormulaList(List<GrecipeRecipeFormulaList> list);

	/**
	 * 処方その他からレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<GrecipeRecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	List<GrecipeRecipeRemarkList> findRemarkByRecipeId(String recipeId)
			throws NoDataException;

	/**
	 * 処方その他に登録処理を行う
	 * @param bean GrecipeRecipeFormulaList
	 */
	void insertRemark(GrecipeRecipeRemarkList bean);

	/**
	 * 共通ヘッダー部のデータを処方ヘッダーから取得する。
	 * @param recipeId レシピインデックス
	 * @return GrecipeRecipeHeaderList
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	GrecipeRecipeHeaderList getCommonHeader(String recipeId)
			throws NoDataException;

	/**
	 * 指定したラベルコードに一致するデータを帳票・ラベルマスタから取得する
	 * @param labelCd ラベルコード（レシピインデックス）
	 * @return List<GrecipeLabelList>
	 * @throws NoDataException データが存在しない場合発生
	 */
	List<GrecipeLabelList> getLabelList(String labelCd) throws NoDataException;

	/**
	 * 帳票・ラベルマスタに登録処理を行う
	 * @param bean GrecipeLabelList
	 */
	void insertLabel(GrecipeLabelList bean);

	/**
	 * 工程順序リストを取得する
	 * @param recipeId レシピインデックス
	 * @return List<MgrecipeNameList>
	 */
	List<GrecipeRecipeProcedureSetpNoForComboboxes> getProcedureSetpNoList(
			final BigDecimal recipeId);

	/**
	 * 工程順序コンボボックス作成
	 * @param recipeId レシピインデックス
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createProcedureSetpNoCombobox(final String recipeId,
			final boolean zero);
}
