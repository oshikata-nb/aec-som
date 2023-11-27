/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeLabelList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * ヘッダー情報－コピー作成登録処理
 * @author tosco
 */
public class MgrecipeHeaderCopyRegisterLogicImpl
				extends	AbstractMgrecipeHeaderRegisterLogic
				implements MgrecipeHeaderCopyRegisterLogic {


	/** 単位区分 "HAIGO" */
	private static final String UNIT_DIVISION_HAIGO = "HAIGO";

	/**
	 * コンストラクタ
	 */
	public MgrecipeHeaderCopyRegisterLogicImpl() {
	}

	/**
	 * コピー処方登録処理
	 * @param form ヘッダー情報画面Form
	 * @return RecipeHeaderList
	 */
	protected RecipeHeaderList updateData(final MgrecipeHeaderForm form) {
		//コピー元のフォームミュラを全て取得->収率の計算に必要になるので前もって取得
		List<RecipeFormulaList> fomulaList = mgrecipeCommonsLogic.findFormulaByRecipeId(form.getRecipeId());

		//処方ヘッダーを更新
		RecipeHeaderList header = insertRecipeHeader(form, fomulaList);
		//処方フォーミュラに仕上げデータを挿入
		insertRecipeFormula(header, fomulaList);
		//フォーミュラに挿入したら用は無いのでクリア
		fomulaList.clear();

		//処方プロシージャをコピー
		copyProcedure(form.getRecipeId(), header);
		//処方検査をコピー
		copyInspection(form.getRecipeId(), header);
		//Aspravaをコピー
		copyAsprova(form.getRecipeId(), header);
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
	private RecipeHeaderList insertRecipeHeader(
			final MgrecipeHeaderForm form, final List<RecipeFormulaList> fomulaList) {
		//処方ヘッダーを更新
		RecipeHeaderList bean = getInsertBean(form);
		//標準生産量(入力値)
		BigDecimal stdQty = AecNumberUtils.convertBigDecimal(form.getStdQty());

		//数値桁数マスタの設定を取得
		NumberChkDisitDetail checkDetail = checkDigitUtilsLogic.getCheckDigit(
			UNIT_DIVISION_HAIGO, null, null);
		//端数区分からBigDecimalの丸めモードに変換
		RoundingMode round = checkDigitUtilsLogic.getRoundingMode(checkDetail.getRoundDivision().intValue());
		int decimalPoint = checkDetail.getSmallnumLength().intValue();
		//配合再計算
		calcCombine(form.isCalc(), fomulaList, form.getOrgStdQty(), stdQty, decimalPoint, round);
		//収率は標準生産量÷（formula.lineType=-1のqtyをsumする)
//		bean.setProcessLoss(calcProcessLoss(fomulaList, stdQty, decimalPoint, round));
		bean.setProcessLoss(calcProcessLoss(fomulaList, stdQty, 4, RoundingMode.HALF_UP));

		//原処方レシピインデックスが設定されている場合は引き継ぐ
		String origin = form.getOriginalRecipeId();
		if (StringUtils.isNotEmpty(origin)) {
			//原処方レシピインデックスが設定されているので引き継ぐ
			bean.setOriginalRecipeId(new BigDecimal(origin));
		}

		//挿入実行
		mgrecipeHeaderLogic.insert(bean);

		//処方ヘッダーを再検索（発番されたrecipeIdを取得するため)
		RecipeHeaderList header = getRecipeHeader(form.getRecipeCd(),
			form.getRecipeVersion(), MgrecipeConst.RECIPE_TYPE_MASTER);
		return header;
	}
	/**
	 * コピー元の処方ASPROVAからコピーする
	 * @param orgRecipeId コピー元レシピインデックス
	 * @param header コピー先のヘッダー情報
	 */
	private void copyAsprova(final String orgRecipeId, final RecipeHeaderList header) {
		try {
			List<RecipeAsprovaList> list = mgrecipeCommonsLogic.findAsprovaByRecipeId(orgRecipeId);
			BigDecimal recipeId = header.getRecipeId();
			String tantoCd = header.getUpdatorCd();
			Timestamp time = header.getUpdateDate();
			for (RecipeAsprovaList bean : list) {
				bean.setRecipeId(recipeId);		//レシピインデックス
				bean.setInputDate(time);		//登録日
				bean.setUpdateDate(time);		//更新日
				bean.setInputorCd(tantoCd);		//登録者
				bean.setUpdatorCd(tantoCd);		//更新者
				//処方処方検査に挿入
				mgrecipeCommonsLogic.insertAsprova(bean);
			}
		} catch (NoDataException e) {
			log().debug("処方ASPROVAにデータがないので、コピー処理を行わない");
		}
	}
	/**
	 * コピー元の処方詳細からコピーする
	 * @param orgRecipeId コピー元レシピインデックス
	 * @param header コピー先のヘッダー情報
	 */
	private void copyDetail(final String orgRecipeId, final RecipeHeaderList header) {
		try {
			//帳票・ラベルマスタから一括取得
			List<MgrecipeLabelList> list = mgrecipeCommonsLogic.getLabelList(orgRecipeId);
			String recipeId = header.getRecipeId().toString();
			String tantoCd = header.getUpdatorCd();
			Timestamp time = header.getUpdateDate();
			for (MgrecipeLabelList bean : list) {
				bean.setLabelCd(recipeId);		//レシピインデックス
				bean.setInputDate(time);		//登録日
				bean.setUpdateDate(time);		//更新日
				bean.setInputorCd(tantoCd);		//登録者
				bean.setUpdatorCd(tantoCd);		//更新者
				//帳票・ラベルマスタに挿入
				mgrecipeCommonsLogic.insertLabel(bean);
			}
		} catch (NoDataException e) {
			log().debug("帳票・ラベルマスタにデータがないので、コピー処理を行わない");
		}
	}
}
