/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe.MgrecipeLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe.RecipeProcedureSetpNoForComboboxes;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeBumonDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeItemQueueDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeLabelList;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeNameList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeInspectionList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeRemarkList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 基本処方検索 ロジッククラス interface.
 * @author tosco
 */
public interface MgrecipeCommonsLogic {

	/**
	 * 生産ラインを全件取得する
	 * @return List<MgrecipeLine>
	 */
	List<MgrecipeLineForComboboxes> getAllLines();

	/**
	 * レシピステータスを全件取得する
	 * @return List<MgrecipeNameList>
	 */
	List<MgrecipeNameList> getAllStatus();

	/**
	 * レシピステータスコンボボックス配列を取得する
	 * @param isNewSearch true:原処方から新規作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> getStatusComboList(final Boolean isNewSearch);

	/**
	 * 生産ラインコンボボックス作成
	 * @param newFlg true:新規入力
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createLineCombobox(final Boolean newFlg);

	/**
	 * ステータスコンボボックス(すべて有)作成
	 * @param isNewSearch true:原処方から新規作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createStatusAllCombobox(final Boolean isNewSearch);

	/**
	 * 品目マスタキューから指定した品目コードの最新バージョンのデータを取得する。
	 * @param itemCd 品目コード
	 * @return MgrecipeItemQueueDetail
	 * @throws NoDataException データが存在しない場合
	 */
	MgrecipeItemQueueDetail getMaxVersionItemQueue(String itemCd)
			throws NoDataException;

	/**
	 * 会計部門検索
	 * @param sectionCd 会計部門コード
	 * @return MgrecipeBumonDetail
	 * @throws NoDataException データが存在しない場合
	 */
	MgrecipeBumonDetail getBumonEntity(String sectionCd) throws NoDataException;

	/**
	 * 処方フォーミュラに登録処理を行う
	 * @param bean RecipeFormulaList
	 */
	void insertFormula(RecipeFormulaList bean);

	/**
	 * 処方フォーミュラに更新処理を行う
	 * @param bean RecipeFormulaList
	 */
	void updateFormula(RecipeFormulaList bean);

	/**
	 * 処方プロシージャからレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	List<RecipeProcedureList> getProcedure(String recipeId)
			throws NoDataException;

	/**
	 * 処方プロシージャに登録処理を行う
	 * @param bean RecipeFormulaList
	 */
	void insertProcedure(RecipeProcedureList bean);

	/**
	 * 処方処方検査からレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	List<RecipeInspectionList> getInspection(String recipeId)
			throws NoDataException;

	/**
	 * 処方検査に登録処理を行う
	 * @param bean RecipeFormulaList
	 */
	void insertInspection(RecipeInspectionList bean);

	/**
	 * 処方フォーミュラからレシピIDに紐づくデータをすべて取得
	 * @param recipeId レシピインデックス
	 * @return List<RecipeFormulaList>
	 */
	List<RecipeFormulaList> findFormulaByRecipeId(String recipeId);

	/**
	 * 処方フォーミュラに一括登録処理を行う
	 * @param list List<RecipeFormulaList>
	 */
	void insertFormulaList(List<RecipeFormulaList> list);

	/**
	 * 処方フォーミュラに一括更新処理を行う
	 * @param list List<RecipeFormulaList>
	 */
	void updateFormulaList(List<RecipeFormulaList> list);

	/**
	 * 処方ASPROVAからレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	List<RecipeAsprovaList> findAsprovaByRecipeId(String recipeId)
			throws NoDataException;

	/**
	 * 処方ASPROVAに登録処理を行う
	 * @param bean RecipeFormulaList
	 */
	void insertAsprova(RecipeAsprovaList bean);

	/**
	 * 処方その他からレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	List<RecipeRemarkList> findRemarkByRecipeId(String recipeId)
			throws NoDataException;

	/**
	 * 処方その他に登録処理を行う
	 * @param bean RecipeFormulaList
	 */
	void insertRemark(RecipeRemarkList bean);

	/**
	 * 共通ヘッダー部のデータを処方ヘッダーから取得する。
	 * @param recipeId レシピインデックス
	 * @return RecipeHeaderList
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	RecipeHeaderList getCommonHeader(String recipeId) throws NoDataException;

	/**
	 * 指定したラベルコードに一致するデータを帳票・ラベルマスタから取得する
	 * @param labelCd ラベルコード（レシピインデックス）
	 * @return List<MgrecipeLabelList>
	 * @throws NoDataException データが存在しない場合発生
	 */
	List<MgrecipeLabelList> getLabelList(String labelCd) throws NoDataException;

	/**
	 * 帳票・ラベルマスタに登録処理を行う
	 * @param bean MgrecipeLabelList
	 */
	void insertLabel(MgrecipeLabelList bean);

	/**
	 * 工程順序リストを取得する
	 * @param recipeId レシピインデックス
	 * @return List<MgrecipeNameList>
	 */
	List<RecipeProcedureSetpNoForComboboxes> getProcedureSetpNoList(
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
