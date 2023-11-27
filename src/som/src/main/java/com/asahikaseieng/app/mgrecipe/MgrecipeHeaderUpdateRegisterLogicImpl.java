/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * ヘッダー情報－更新登録処理
 * @author tosco
 */
public class MgrecipeHeaderUpdateRegisterLogicImpl extends
		AbstractMgrecipeHeaderRegisterLogic implements
		MgrecipeHeaderUpdateRegisterLogic {

	/** 単位区分 "HAIGO" */
	private static final String UNIT_DIVISION_HAIGO = "HAIGO";

	/**
	 * コンストラクタ
	 */
	public MgrecipeHeaderUpdateRegisterLogicImpl() {
	}

	/**
	 * ヘッダー情報更新処理
	 * @param form ヘッダー情報画面Form
	 * @throws MgrecipeLogicException 処理例外発生時
	 */
	@Override
	public void update(final MgrecipeHeaderForm form)
			throws MgrecipeLogicException {
		if (!StringUtils.isEmpty(form.getSectionCd())) {
			// 会計部門コードマスタチェック
			isExistsBumon(form.getSectionCd());
		}

		// 標準生産量変更チェック
		checkDirtyStdQty(form.getStdQty(), form.getOrgStdQty(), form.isCalc());

		// 更新処理
		updateData(form);
	}

	/**
	 * 更新登録処理
	 * @param form ヘッダー情報画面Form
	 * @return RecipeHeaderList
	 * @throws MgrecipeLogicException エラー発生時
	 */
	protected RecipeHeaderList updateData(final MgrecipeHeaderForm form)
			throws MgrecipeLogicException {
		// フォームミュラを全て取得->収率の計算に必要になるので前もって取得
		List<RecipeFormulaList> fomulaList = mgrecipeCommonsLogic
				.findFormulaByRecipeId(form.getRecipeId());

		// 処方ヘッダーを更新
		RecipeHeaderList header = updateRecipeHeader(form, fomulaList);

		// 処方フォーミュラに仕上げデータを挿入
		updateRecipeFormula(form.isCalc(), header, fomulaList);
		// フォーミュラに挿入したら用は無いのでクリア
		fomulaList.clear();

		return header;
	}

	/**
	 * 処方ヘッダーを更新
	 * @param form ヘッダー情報画面Form
	 * @return 挿入結果の処方ヘッダーデータ
	 * @throws MgrecipeLogicException 更新対象データが存在しない場合
	 */
	private RecipeHeaderList updateRecipeHeader(final MgrecipeHeaderForm form,
			final List<RecipeFormulaList> fomulaList)
			throws MgrecipeLogicException {
		// 処方ヘッダから処方を再検索
		// 画面表示時の処方ヘッダー情報を取得
		RecipeHeaderList header = form.getHeader();

		// 画面の入力値を設定
		setUpdateBean(header, form);

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
		calcCombine(form.isCalc(), fomulaList, form.getOrgStdQty(), stdQty,
			decimalPoint, round);
		// 収率は標準生産量÷（formula.lineType!=-1のqtyをsumする)
		// header.setProcessLoss(calcProcessLoss(fomulaList, stdQty,
		// decimalPoint, round));
		header.setProcessLoss(calcProcessLoss(fomulaList, stdQty, 4,
			RoundingMode.HALF_UP));

		// 更新実行
		try {
			mgrecipeHeaderLogic.update(header);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// DBのデータが表示時とデータが変更されている場合
			throw new MgrecipeLogicException("errors.optimisticlock.data", "");
		}

		return header;
	}

	/**
	 * 挿入時の処方ヘッダーに設定する共通項目を設定する。
	 * @param headerForm MgrecipeHeaderForm
	 * @param bean RecipeHeaderList
	 * @return 処方ヘッダー更新データ
	 */
	protected RecipeHeaderList setUpdateBean(final RecipeHeaderList bean,
			final MgrecipeHeaderForm headerForm) {

		// 画面の入力項目を設定
		getRecipeHeaderBean(headerForm, bean);

		String tantoCd = headerForm.getUpdatorCd(); // 更新者IDにログインユーザIDを設定
		bean.setUpdatorCd(tantoCd); // 更新者ID
		return bean;
	}

	/**
	 * 処方フォーミュラ更新処理
	 * @param isCalc 配合再計算フラグ
	 * @param header 処方ヘッダ更新データ
	 * @param list 処方フォーミュラリスト
	 */
	protected void updateRecipeFormula(final boolean isCalc,
			final RecipeHeaderList header, final List<RecipeFormulaList> list) {
		List<RecipeFormulaList> targetList = new ArrayList<RecipeFormulaList>();
		// 更新データ作成
		for (RecipeFormulaList bean : list) {
			bean.setUpdatorCd(header.getUpdatorCd()); // 更新者
			if (STANDERD_QTY_LINE_NO.equals(bean.getLineNo())) {
				// LINE_NO="1001"のデータには標準生産量を設定
				bean.setQty(header.getStdQty());
				// 更新対象
				targetList.add(bean);
			} else if (isCalc) {
				// 配合再計算を行っている場合は更新対象
				targetList.add(bean);
			}
		}
		// 処方フォーミュラに更新
		mgrecipeCommonsLogic.updateFormulaList(targetList);
	}

}
