/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;

/**
 * ヘッダー情報－新規登録処理
 * @author tosco
 */
public class GrecipeHeaderNewRegisterLogicImpl
				extends	AbstractGrecipeHeaderRegisterLogic
				implements GrecipeHeaderNewRegisterLogic {

	/**
	 * コンストラクタ
	 */
	public GrecipeHeaderNewRegisterLogicImpl() {
	}

	/**
	 * 新規登録処理
	 * @param form ヘッダー情報画面Form
	 * @return GrecipeRecipeHeaderList
	 */
	protected GrecipeRecipeHeaderList updateData(final GrecipeHeaderForm form) {
		//処方ヘッダーを更新
		GrecipeRecipeHeaderList header = insertRecipeHeader(form);
		//処方フォーミュラに仕上げデータを挿入
		insertRecipeFormula(header);
		//工程パターン
		long patternRecipeId = form.getOperationPattern();
		if (patternRecipeId != GrecipeHeaderAction.OPERATION_PATTERN_ALL_LONG_VALUE) {
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
	private GrecipeRecipeHeaderList insertRecipeHeader(final GrecipeHeaderForm form) {
		//処方ヘッダーを更新
		GrecipeRecipeHeaderList bean = getInsertBean(form);
		//新規時収率は固定で100.0000
		bean.setProcessLoss(new BigDecimal(GrecipeHeaderAction.PROCESS_LOSS_INIT));

		//挿入実行
		grecipeHeaderLogic.insert(bean);

		//処方ヘッダーを再検索（発番されたrecipeIdを取得するため)
		GrecipeRecipeHeaderList header = getRecipeHeader(form.getRecipeCd(),
			form.getRecipeVersion(), GrecipeConst.RECIPE_TYPE_GENERAL);
		return header;
	}
	/**
	 * 処方フォーミュラ挿入処理
	 * @param header 処方ヘッダ挿入データ
	 */
	private void insertRecipeFormula(final GrecipeRecipeHeaderList header) {
		//更新データ作成
		GrecipeRecipeFormulaList bean = createRecipeFormula(header);
		//処方フォーミュラに挿入
		grecipeCommonsLogic.insertFormula(bean);
	}
	/**
	 * 処方ヘッダー挿入データ作成
	 * @param header 処方ヘッダ挿入データ
	 * @return 処方ヘッダー挿入データ
	 */
	private GrecipeRecipeFormulaList createRecipeFormula(final GrecipeRecipeHeaderList header) {
		GrecipeRecipeFormulaList bean = new GrecipeRecipeFormulaList();

		bean.setRecipeId(header.getRecipeId());		//レシピインデックス
		bean.setStepNo(new BigDecimal(GrecipeRecipeFormulaListDao.STEP_NO));	//step_no
		bean.setLineNo(new BigDecimal(GrecipeRecipeFormulaListDao.LINE_NO));	//line_no
		bean.setSeq(new BigDecimal(GrecipeRecipeFormulaListDao.SEQ));			//seq
		//line_type
		if (header.getRecipeUse().toString().equals(SelectRecipeUse.RECIPE_USE_PRODUCTION)) {
			//製造時
			bean.setLineType(new BigDecimal(GrecipeRecipeFormulaListDao.LINE_TYPE_PRODUCTION));
		} else {
			//包装時
			bean.setLineType(new BigDecimal(GrecipeRecipeFormulaListDao.LINE_TYPE_PACKING));
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
