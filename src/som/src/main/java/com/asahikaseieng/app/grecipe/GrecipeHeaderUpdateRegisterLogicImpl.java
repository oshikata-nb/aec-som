/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;

/**
 * ヘッダー情報－更新登録処理
 * @author tosco
 */
public class GrecipeHeaderUpdateRegisterLogicImpl
				extends AbstractGrecipeHeaderRegisterLogic
				implements GrecipeHeaderUpdateRegisterLogic {

	/**
	 * コンストラクタ
	 */
	public GrecipeHeaderUpdateRegisterLogicImpl() {
	}
	/**
	 * ヘッダー情報更新処理
	 * @param form ヘッダー情報画面Form
	 * @throws GrecipeLogicException 処理例外発生時
	 */
	@Override
	public void update(final GrecipeHeaderForm form) throws GrecipeLogicException {
		//更新処理
		updateData(form);
	}

	/**
	 * 更新登録処理
	 * @param form ヘッダー情報画面Form
	 * @return GrecipeRecipeHeaderList
	 * @throws GrecipeLogicException エラー発生時
	 */
	protected GrecipeRecipeHeaderList updateData(final GrecipeHeaderForm form) throws GrecipeLogicException {
		//フォームミュラを全て取得->収率の計算に必要になるので前もって取得
		List<GrecipeRecipeFormulaList> fomulaList =
			grecipeCommonsLogic.findFormulaByRecipeId(form.getRecipeId());

		//処方ヘッダーを更新
		GrecipeRecipeHeaderList header = updateRecipeHeader(form, fomulaList);

		//処方フォーミュラに仕上げデータを挿入
		updateRecipeFormula(header, fomulaList);
		//フォーミュラに挿入したら用は無いのでクリア
		fomulaList.clear();

		return header;
	}
	/**
	 * 処方ヘッダーを更新
	 * @param form ヘッダー情報画面Form
	 * @return 挿入結果の処方ヘッダーデータ
	 * @throws GrecipeLogicException 更新対象データが存在しない場合
	 */
	private GrecipeRecipeHeaderList updateRecipeHeader(
			final GrecipeHeaderForm form, final List<GrecipeRecipeFormulaList> fomulaList)
		throws GrecipeLogicException {

		//画面表示時の処方ヘッダー情報を取得
		GrecipeRecipeHeaderList header = form.getHeader();

		//画面の入力値を設定
		setUpdateBean(header, form);

		//標準生産量(入力値)
		BigDecimal stdQty = new BigDecimal(form.getStdQty());

		//収率は標準生産量÷（formula.lineType!=-1のqtyをsumする)
		header.setProcessLoss(calcProcessLoss(fomulaList, stdQty, 4, RoundingMode.HALF_UP));

		//更新実行
		try {
			grecipeHeaderLogic.update(header);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			//DBのデータが表示時とデータが変更されている場合
			throw new GrecipeLogicException("errors.optimisticlock.data", "");
		}

		return header;
	}
	/**
	 * 挿入時の処方ヘッダーに設定する共通項目を設定する。
	 * @param headerForm GrecipeHeaderForm
	 * @param bean GrecipeRecipeHeaderList
	 * @return 処方ヘッダー更新データ
	 */
	protected GrecipeRecipeHeaderList setUpdateBean(
			final GrecipeRecipeHeaderList bean, final GrecipeHeaderForm headerForm) {

		//画面の入力項目を設定
		getRecipeHeaderBean(headerForm, bean);

		String tantoCd = headerForm.getUpdatorCd();		//更新者IDにログインユーザIDを設定
		bean.setUpdatorCd(tantoCd);						//更新者ID
		return bean;
	}
	/**
	 * 処方フォーミュラ更新処理
	 * @param header 処方ヘッダ更新データ
	 * @param list 処方フォーミュラリスト
	 */
	protected void updateRecipeFormula(
			final GrecipeRecipeHeaderList header, final List<GrecipeRecipeFormulaList> list) {
		List<GrecipeRecipeFormulaList> targetList = new ArrayList<GrecipeRecipeFormulaList>();
		//更新データ作成
		for (GrecipeRecipeFormulaList bean : list) {
			if (STANDERD_QTY_LINE_NO.equals(bean.getLineNo())) {
				//LINE_NO="1001"のデータには標準生産量を設定
				bean.setQty(header.getStdQty());
				bean.setItemCd(header.getProduct());	//品目コード
				bean.setUpdatorCd(header.getUpdatorCd());	//更新者
				//更新対象
				targetList.add(bean);
			}
		}
		//処方フォーミュラに更新
		grecipeCommonsLogic.updateFormulaList(targetList);
	}

}
