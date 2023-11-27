/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;

/**
 * ヘッダー情報－空処方登録処理
 * @author tosco
 */
public class MgrecipeHeaderEmptyRegisterLogicImpl
				extends	AbstractMgrecipeHeaderRegisterLogic
				implements MgrecipeHeaderEmptyRegisterLogic {

	/**
	 * コンストラクタ
	 */
	public MgrecipeHeaderEmptyRegisterLogicImpl() {
	}

	/**
	 * 空処方登録処理
	 * @param form ヘッダー情報画面Form
	 * @return RecipeHeaderList
	 */
	protected RecipeHeaderList updateData(final MgrecipeHeaderForm form) {
		//処方ヘッダーを更新
		RecipeHeaderList header = insertRecipeHeader(form);
		//処方フォーミュラに仕上げデータを挿入
		insertRecipeFormula(header);
		//工程パターン
		long patternRecipeId = form.getOperationPattern();
		if (patternRecipeId != MgrecipeHeaderAction.OPERATION_PATTERN_ALL_LONG_VALUE) {
			//工程パターンが「パターンなし」以外の場合、工程パターンのデータをコピーする
			//工程パターンの処方プロシージャをコピー
			copyProcedure(String.valueOf(patternRecipeId), header);
			//工程パターンの処方検査をコピー
			copyInspection(String.valueOf(patternRecipeId), header);
		}
		return header;
	}
	/**
	 * 処方ヘッダーを更新
	 * @param form ヘッダー情報画面Form
	 * @return 挿入結果の処方ヘッダーデータ
	 */
	private RecipeHeaderList insertRecipeHeader(final MgrecipeHeaderForm form) {
		//処方ヘッダーを更新
		RecipeHeaderList bean = getInsertBean(form);
		//空処方時収率は固定で100.0000
		bean.setProcessLoss(new BigDecimal(MgrecipeHeaderAction.PROCESS_LOSS_INIT));

		//挿入実行
		mgrecipeHeaderLogic.insert(bean);

		//処方ヘッダーを再検索（発番されたrecipeIdを取得するため)
		RecipeHeaderList header = getRecipeHeader(form.getRecipeCd(),
			form.getRecipeVersion(), MgrecipeConst.RECIPE_TYPE_MASTER);
		return header;
	}
	/**
	 * 処方フォーミュラ挿入処理
	 * @param header 処方ヘッダ挿入データ
	 */
	private void insertRecipeFormula(final RecipeHeaderList header) {
		//更新データ作成
		RecipeFormulaList bean = createRecipeFormula(header);
		//処方フォーミュラに挿入
		mgrecipeCommonsLogic.insertFormula(bean);
	}
	/**
	 * 処方ヘッダー挿入データ作成
	 * @param header 処方ヘッダ挿入データ
	 * @return 処方ヘッダー挿入データ
	 */
	private RecipeFormulaList createRecipeFormula(final RecipeHeaderList header) {
		RecipeFormulaList bean = new RecipeFormulaList();

		bean.setRecipeId(header.getRecipeId());		//レシピインデックス
		bean.setStepNo(new BigDecimal(RecipeFormulaListDao.STEP_NO));	//step_no
		bean.setLineNo(new BigDecimal(RecipeFormulaListDao.LINE_NO));	//line_no
		bean.setSeq(new BigDecimal(RecipeFormulaListDao.SEQ));			//seq
		//line_type
		if (header.getRecipeUse().toString().equals(SelectRecipeUse.RECIPE_USE_PRODUCTION)) {
			//製造時
			bean.setLineType(new BigDecimal(RecipeFormulaListDao.LINE_TYPE_PRODUCTION));
		} else {
			//包装時
			bean.setLineType(new BigDecimal(RecipeFormulaListDao.LINE_TYPE_PACKING));
		}
		bean.setItemCd(header.getProduct());		//品目コード
		bean.setQty(header.getStdQty());			//数量
		bean.setInputDate(header.getInputDate());	//登録日
		bean.setUpdateDate(header.getUpdateDate());	//更新日
		bean.setInputorCd(header.getInputorCd());	//登録者
		bean.setUpdatorCd(header.getUpdatorCd());	//更新者
		return bean;
	}

}
