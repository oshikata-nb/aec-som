/*
 * Created on 2009/01/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaListDao;

/**
 * 基本処方用ユーティリティ
 * @author tosco
 */
public final class MgrecipeUtil {

	/** 用途-配合時のLINE_TYPE */
	private static final BigDecimal LINE_COMBINE = new BigDecimal(
			RecipeFormulaListDao.LINE_TYPE_COMBINE);

	/**
	 * コンストラクタ
	 */
	private MgrecipeUtil() {
	}

	/**
	 * メッセージを追加する
	 * 
	 * @param errors ActionMessages
	 * @param key リソースのキー
	 * @param objects オブジェクト
	 * @return ActionMessages メッセージ
	 */
	public static ActionMessages addError(final ActionMessages errors,
			final String key, final Object... objects) {
		ActionMessages tmpMsg = errors;
		if (tmpMsg == null) {
			tmpMsg = new ActionMessages();
		}
		tmpMsg.add("", new ActionMessage(key, objects));
		return tmpMsg;
	}

	/**
	 * 収率計算：収率=標準生産量÷配合量計
	 * @param list フォーミュラデータ
	 * @param stdQty 標準生産量
	 * @param scale 小数点桁数
	 * @param roundingMode 丸めモード
	 * @return 収率
	 */
	public static BigDecimal calcProcessLoss(
			final List<RecipeFormulaList> list, final BigDecimal stdQty,
			final int scale, final RoundingMode roundingMode) {
		BigDecimal res = null;
		BigDecimal sumQty = new BigDecimal(0);
		for (RecipeFormulaList bean : list) {
			if (bean.getLineType().equals(LINE_COMBINE)) {
				// 原材料の場合
				sumQty = sumQty.add(bean.getQty());
			}
		}
		// 収率=標準生産量÷配合量計X100%
		if (sumQty.compareTo(BigDecimal.ZERO) == 0) {
			// 0割
			res = new BigDecimal(100);
		} else {
			// res = stdQty.multiply(new BigDecimal(100).divide(sumQty, scale,
			// roundingMode));
			res = stdQty.multiply(new BigDecimal("100")).divide(sumQty, scale,
				roundingMode);
			res = res.divide(new BigDecimal("1"), scale, roundingMode);
		}

		return res;
	}
}
