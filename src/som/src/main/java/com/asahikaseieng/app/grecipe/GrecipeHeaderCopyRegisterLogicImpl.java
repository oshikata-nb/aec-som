/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeLabelList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * ヘッダー情報－コピー作成登録処理
 * @author tosco
 */
public class GrecipeHeaderCopyRegisterLogicImpl
				extends	AbstractGrecipeHeaderRegisterLogic
				implements GrecipeHeaderCopyRegisterLogic {
	/**
	 * コンストラクタ
	 */
	public GrecipeHeaderCopyRegisterLogicImpl() {
	}

	/**
	 * コピー処方登録処理
	 * @param form ヘッダー情報画面Form
	 * @return GrecipeRecipeHeaderList
	 */
	protected GrecipeRecipeHeaderList updateData(final GrecipeHeaderForm form) {
		//コピー元のフォームミュラを全て取得->収率の計算に必要になるので前もって取得
		List<GrecipeRecipeFormulaList> fomulaList =
			grecipeCommonsLogic.findFormulaByRecipeId(form.getRecipeId());

		//処方ヘッダーを更新
		GrecipeRecipeHeaderList header = insertRecipeHeader(form, fomulaList);
		//処方フォーミュラに仕上げデータを挿入
		insertRecipeFormula(header, fomulaList);
		//フォーミュラに挿入したら用は無いのでクリア
		fomulaList.clear();
		//処方プロシージャをコピー
		copyProcedure(form.getRecipeId(), header);
		//処方検査をコピー
		copyInspection(form.getRecipeId(), header);
		//その他をコピー
		copyRemark(form.getRecipeId(), header);
		//詳細をコピー
		copyDetail(form.getRecipeId(), header);

		return header;
	}
	/**
	 * 処方ヘッダーを更新
	 * @param form ヘッダー情報画面Form
	 * @return 挿入結果の処方ヘッダーデータ
	 */
	private GrecipeRecipeHeaderList insertRecipeHeader(
			final GrecipeHeaderForm form, final List<GrecipeRecipeFormulaList> fomulaList) {
		//処方ヘッダーを更新
		GrecipeRecipeHeaderList bean = getInsertBean(form);
		//標準生産量(入力値)
		BigDecimal stdQty = new BigDecimal(form.getStdQty());

		//収率は標準生産量÷（formula.lineType=-1のqtyをsumする)
		bean.setProcessLoss(calcProcessLoss(fomulaList, stdQty, 4, RoundingMode.HALF_UP));

		//挿入実行
		grecipeHeaderLogic.insert(bean);

		//処方ヘッダーを再検索（発番されたrecipeIdを取得するため)
		GrecipeRecipeHeaderList header = getRecipeHeader(form.getRecipeCd(),
			form.getRecipeVersion(), GrecipeConst.RECIPE_TYPE_GENERAL);
		return header;
	}
	/**
	 * コピー元の処方詳細からコピーする
	 * @param orgRecipeId コピー元レシピインデックス
	 * @param header コピー先のヘッダー情報
	 */
	private void copyDetail(final String orgRecipeId, final GrecipeRecipeHeaderList header) {
		try {
			//帳票・ラベルマスタから一括取得
			List<GrecipeLabelList> list = grecipeCommonsLogic.getLabelList(orgRecipeId);
			String recipeId = header.getRecipeId().toString();
			String tantoCd = header.getUpdatorCd();
			Timestamp time = header.getUpdateDate();
			for (GrecipeLabelList bean : list) {
				bean.setLabelCd(recipeId);		//レシピインデックス
				bean.setInputDate(time);		//登録日
				bean.setUpdateDate(time);		//更新日
				bean.setInputorCd(tantoCd);		//登録者
				bean.setUpdatorCd(tantoCd);		//更新者
				//帳票・ラベルマスタに挿入
				grecipeCommonsLogic.insertLabel(bean);
			}
		} catch (NoDataException e) {
			log().debug("帳票・ラベルマスタにデータがないので、コピー処理を行わない");
		}
	}
}
