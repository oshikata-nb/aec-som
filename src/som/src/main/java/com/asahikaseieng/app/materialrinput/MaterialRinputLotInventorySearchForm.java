/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.materialrinput;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputLotInventorySearchList;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputLotInventorySearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 外注原材料投入実績入力画面_ロット検索ポップアップ画面 Formクラス.
 * @author tosco
 */
public final class MaterialRinputLotInventorySearchForm extends
		AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** 区分 配合 */
	public static final String UNIT_DIVISION_HAIGO = "HAIGO";

	private String dirtyFlg; /* 変更フラグ */

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	/** 検索品目コード */
	private String srhItemCd;

	/** 品目名称 */
	private String srhItemName;

	/** 使用量 */
	private String strSumUseQty;

	/** total量 */
	private String totalQty;

	/** 発注番号 */
	private String srhBuySubcontractOrderNo;

	/** レシピインデックス */
	private String srhRecipeId;

	/** STEP_NO **/
	private String srhStepNo;

	/** LINE_NO **/
	private String srhLineNo;

	/** 検索結果リスト */
	private List<MaterialRinputLotInventorySearchList> searchList;

	/** 登録対象リスト */
	private List<MaterialRinputLotInventorySearchList> registList;

	/** 行番号 */
	private String line;

	/** 小数点桁数(配合) */
	private String decimalPointHaigo;

	/** 端数区分(配合) */
	private String roundHaigo;

	static {
		PAGE_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("linage.common"));
		DATA_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("threshold.common"));
	}

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputLotInventorySearchForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected Class getPagerConditionClass() {
		return MaterialRinputLotInventorySearchListPagerCondition.class;
	}

	/**
	 * 入力データの検証
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("regist".equals(getOp())) {
			errors = new ActionErrors();
			validateSearchListUPD(errors, request);
		}
		return errors;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
	}

	/**
	 * クリア処理.
	 */
	private void clear() {
		// 品目コード
		setSrhItemCd(null);
		// 品目名称
		setSrhItemName(null);
		// 使用量
		setStrSumUseQty(null);
		// total量
		setTotalQty(null);
		// 行番号
		setLine(null);
		// 小数点桁数(配合)
		setDecimalPointHaigo(null);
		// 端数区分(配合)
		setRoundHaigo(null);
		// 検索リストのクリア
		setSearchList(new ArrayList<MaterialRinputLotInventorySearchList>());
		// 登録リストのクリア
		setRegistList(new ArrayList<MaterialRinputLotInventorySearchList>());
		// 発注番号
		this.setSrhBuySubcontractOrderNo(null);
		// レシピインデックス
		this.setSrhRecipeId(null);
		// LINE_NO
		this.setSrhLineNo(null);
		// STEP_NO
		this.setSrhStepNo(null);
	}

	//
	// インスタンスメソッド
	//

	/**
	 * 変更フラグを取得します。
	 * @return String 変更フラグ
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * 変更フラグを設定します。
	 * @param dirtyFlg 変更フラグ
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 検索品目コード取得.
	 * @return String 検索品目コード
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * 検索品目コード設定.
	 * @param srhItemCd 検索品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * 品目名称取得.
	 * @return String 品目名称
	 */
	public String getSrhItemName() {
		return srhItemName;
	}

	/**
	 * 品目名称設定.
	 * @param srhItemName 品目名称
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * 検索結果リストを取得します。
	 * @return searchList 検索結果リスト
	 */
	public List<MaterialRinputLotInventorySearchList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchList 検索結果リスト
	 */
	public void setSearchList(
			final List<MaterialRinputLotInventorySearchList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * 登録対象リストを取得します。
	 * @return registList 登録対象リスト
	 */
	public List<MaterialRinputLotInventorySearchList> getRegistList() {
		return registList;
	}

	/**
	 * 登録対象リストを設定します。
	 * @param registList 登録対象リスト
	 */
	public void setRegistList(
			final List<MaterialRinputLotInventorySearchList> registList) {
		this.registList = registList;
	}

	/**
	 * 使用量を取得します。
	 * @return strSumUseQty
	 */
	public String getStrSumUseQty() {
		return strSumUseQty;
	}

	/**
	 * 使用量を設定します。
	 * @param strSumUseQty 使用量
	 */
	public void setStrSumUseQty(final String strSumUseQty) {
		this.strSumUseQty = strSumUseQty;
	}

	/**
	 * total量を取得します。
	 * @return totalQty
	 */
	public String getTotalQty() {
		return totalQty;
	}

	/**
	 * total量を設定します。
	 * @param totalQty total量
	 */
	public void setTotalQty(final String totalQty) {
		this.totalQty = totalQty;
	}

	/**
	 * 行番号取得
	 * @return String 行番号
	 */
	public String getLine() {
		return line;
	}

	/**
	 * 行番号設定
	 * @param line 行番号
	 */
	public void setLine(final String line) {
		this.line = line;
	}

	/**
	 * 小数点桁数(配合)取得
	 * @return String 小数点桁数(配合)
	 */
	public String getDecimalPointHaigo() {
		return decimalPointHaigo;
	}

	/**
	 * 小数点桁数(配合)設定
	 * @param decimalPointHaigo 小数点桁数(配合)
	 */
	public void setDecimalPointHaigo(final String decimalPointHaigo) {
		this.decimalPointHaigo = decimalPointHaigo;
	}

	/**
	 * 端数区分(配合)取得
	 * @return String 端数区分(配合)
	 */
	public String getRoundHaigo() {
		return roundHaigo;
	}

	/**
	 * 端数区分(配合)設定
	 * @param roundHaigo 端数区分(配合)
	 */
	public void setRoundHaigo(final String roundHaigo) {
		this.roundHaigo = roundHaigo;
	}

	/**
	 * 明細行数を取得する。
	 * @return 明細行数
	 */
	public int getCount() {
		int count = 0;
		if (searchList != null) {
			count = searchList.size();
		}
		return count;
	}

	/**
	 * 発注番号取得
	 * @return String 発注番号
	 */
	public String getSrhBuySubcontractOrderNo() {
		return srhBuySubcontractOrderNo;
	}

	/**
	 * 発注番号設定
	 * @param srhBuySubcontractOrderNo 発注番号
	 */
	public void setSrhBuySubcontractOrderNo(final String srhBuySubcontractOrderNo) {
		this.srhBuySubcontractOrderNo = srhBuySubcontractOrderNo;
	}

	/**
	 * LINE_NO取得
	 * @return String LINE_NO
	 */
	public String getSrhLineNo() {
		return srhLineNo;
	}

	/**
	 * LINE_NO設定
	 * @param srhLineNo LINE_NO
	 */
	public void setSrhLineNo(final String srhLineNo) {
		this.srhLineNo = srhLineNo;
	}

	/**
	 * レシピインデックス取得
	 * @return String レシピインデックス
	 */
	public String getSrhRecipeId() {
		return srhRecipeId;
	}

	/**
	 * レシピインデックス設定
	 * @param srhRecipeId レシピインデックス
	 */
	public void setSrhRecipeId(final String srhRecipeId) {
		this.srhRecipeId = srhRecipeId;
	}

	/**
	 * STEP_NO取得
	 * @return String STEP_NO
	 */
	public String getSrhStepNo() {
		return srhStepNo;
	}

	/**
	 * STEP_NO設定
	 * @param srhStepNo STEP_NO
	 */
	public void setSrhStepNo(final String srhStepNo) {
		this.srhStepNo = srhStepNo;
	}

	/**
	 * 登録時に一覧の入力項目(品目コード、配合量)の入力チェックを行う。
	 * @param errors エラー内容
	 * @param request リクエスト
	 */
	private void validateSearchListUPD(final ActionErrors errors,
			final HttpServletRequest request) {
		String resultQty = getStrSumUseQty();

		// 使用量 ＝ total量でない場合、エラー
		if (!resultQty.equals(getTotalQty())) {
			errors.add("", new ActionMessage(
					"errors.materialrinput.total.equals.result"));
		}
		this.getRegistList().clear();
		List<MaterialRinputLotInventorySearchList> list = this.getSearchList();
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);
		int index = 0;
		BigDecimal bdResultQty = null;
		BigDecimal bdInventoryQty = null;
		for (MaterialRinputLotInventorySearchList bean : list) {
			index++;

			// 使用数(数値桁数チェック)
			if (!StringUtils.isEmpty(bean.getStrResultQty())) {
				bdResultQty = AecNumberUtils.convertBigDecimal(bean.getStrResultQty());
				if (BigDecimal.ZERO.compareTo(bdResultQty) == 0) {
					continue;
				}
				this.getRegistList().add(bean);
				CheckDigitUtilsLogic check = CheckDigitUtil
						.getCheckDigitUtils(request);
				ActionMessage message = check.checkDigitMessage(
					UNIT_DIVISION_HAIGO, null, null, bean.getStrResultQty(), rb
							.getString("item.materialrinput.str.sum.use.qty"));
				if (message != null) {
					errors.add("", message);
				}

				// 「使用数 > 在庫数」の場合、エラー
				bdInventoryQty = AecNumberUtils.convertBigDecimal(bean.getStrInventoryQty());
				if (bdResultQty.compareTo(bdInventoryQty) == 1) {
					errors.add("", new ActionMessage(
							"errors.materialrinput.over.use.sum.qty.row", Integer
									.toString(index)));
				}
			}
		}
		// 入力なし
		if (this.getRegistList().isEmpty()) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.materialrinput.not.input.use.qty"));
		}
	}

}
