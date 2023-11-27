/*
 * Created on 2009/01/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * ヘッダー情報－原処方から作成登録処理
 * @author tosco
 */
public class MgrecipeHeaderOriginalRegisterLogicImpl extends
		AbstractMgrecipeHeaderRegisterLogic implements
		MgrecipeHeaderOriginalRegisterLogic {

	/** 単位区分 "HAIGO" */
	private static final String UNIT_DIVISION_HAIGO = "HAIGO";

	/**
	 * コンストラクタ
	 */
	public MgrecipeHeaderOriginalRegisterLogicImpl() {
	}

	/**
	 * 原処方から処方登録処理
	 * @param form ヘッダー情報画面Form
	 * @return RecipeHeaderList
	 */
	protected RecipeHeaderList updateData(final MgrecipeHeaderForm form) {
		// コピー元のフォームミュラを全て取得->配合量・収率の計算に必要になるので前もって取得
		List<RecipeFormulaList> fomulaList = mgrecipeCommonsLogic
				.findFormulaByRecipeId(form.getRecipeId());

		// 処方ヘッダーを更新
		RecipeHeaderList header = insertRecipeHeader(form, fomulaList);
		// 処方フォーミュラに仕上げデータを挿入
		insertRecipeFormula(header, fomulaList);
		// フォーミュラに挿入したら用は無いのでクリア
		fomulaList.clear();

		// 処方プロシージャをコピー
		copyProcedure(form.getRecipeId(), header);
		// 処方検査をコピー
		copyInspection(form.getRecipeId(), header);
		// その他をコピー
		copyRemark(form.getRecipeId(), header);

		return header;
	}

	/**
	 * 処方ヘッダーを更新
	 * @param form ヘッダー情報画面Form
	 * @return 挿入結果の処方ヘッダーデータ
	 */
	private RecipeHeaderList insertRecipeHeader(final MgrecipeHeaderForm form,
			final List<RecipeFormulaList> fomulaList) {
		// 処方ヘッダーを更新
		RecipeHeaderList bean = getInsertBean(form);
		// 原処方レシピインデックスにコピー元を設定
		bean.setOriginalRecipeId(new BigDecimal(form.getRecipeId()));
		// 標準生産量(入力値)
		BigDecimal stdQty = AecNumberUtils.convertBigDecimal(form.getStdQty());

		// 数値桁数マスタの設定を取得
		NumberChkDisitDetail checkDetail = checkDigitUtilsLogic.getCheckDigit(
			UNIT_DIVISION_HAIGO, null, null);
		// 端数区分からBigDecimalの丸めモードに変換
		RoundingMode round = checkDigitUtilsLogic.getRoundingMode(checkDetail
				.getRoundDivision().intValue());
		int decimalPoint = checkDetail.getSmallnumLength().intValue();

		// 配合再計算
		calcCombine(true, fomulaList, form.getOrgStdQty(), stdQty,
			decimalPoint, round);
		// 仕上げ再計算
		calcFinish(fomulaList, stdQty, decimalPoint, round);
		// 収率は標準生産量÷（formula.lineType=-1のqtyをsumする)
		// bean.setProcessLoss(calcProcessLoss(fomulaList, stdQty, decimalPoint,
		// round));
		bean.setProcessLoss(calcProcessLoss(fomulaList, stdQty, 4,
			RoundingMode.HALF_UP));

		// 挿入実行
		mgrecipeHeaderLogic.insert(bean);

		// 処方ヘッダーを再検索（発番されたrecipeIdを取得するため)
		RecipeHeaderList header = getRecipeHeader(form.getRecipeCd(), form
				.getRecipeVersion(), MgrecipeConst.RECIPE_TYPE_MASTER);
		return header;
	}

	/**
	 * 仕上げ再計算 入力された標準生産量と変更前の値で仕上げを計算
	 * @param list フォーミュラデータ
	 * @param stdQty 標準生産量
	 * @param scale 小数点桁数
	 * @param roundingMode 丸めモード
	 */
	protected void calcFinish(final List<RecipeFormulaList> list,
			final BigDecimal stdQty, final int scale,
			final RoundingMode roundingMode) {
		// LINE_NO=1001のデータを探す->原処方の標準生産量割合とその他の割合を求めるため
		BigDecimal baseQty = null;
		for (RecipeFormulaList bean : list) {
			if (STANDERD_QTY_LINE_NO.equals(bean.getLineNo())) {
				// LINE_NO=1001
				baseQty = bean.getQty();
				break;
			}
		}

		// 原処方は割合(%)が設定されているので、仕上げQty=標準生産量X(仕上げ.数量% ÷ 仕上げ(1001)数量%）
		for (RecipeFormulaList bean : list) {
			if (!bean.getLineType().equals(LINE_COMBINE)) {
				// 仕上げの場合
				if (STANDERD_QTY_LINE_NO.equals(bean.getLineNo())) {
					// 1001には標準生産量を設定
					bean.setQty(stdQty);
				} else {
					// それ以外の仕上げの数量を計算（仕上げQty=標準生産量X(仕上げ.数量% ÷ 仕上げ(1001)数量%））
					BigDecimal calc = stdQty.multiply(bean.getQty().divide(
						baseQty, scale, roundingMode));
					bean.setQty(checkDigitUtilsLogic.round("HAIGO", null, null,
						calc));
				}
			}
		}
	}
}
