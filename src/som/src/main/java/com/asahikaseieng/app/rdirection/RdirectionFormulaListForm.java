/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

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
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionFormulaList;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 製造実績－配合Form
 * @author tosco
 */
public class RdirectionFormulaListForm extends AbstractRdirectionForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 工程順序コンボボックス */
	private List<ComboBoxItems> seqCombo;

	/** 工程順序 */
	private String procStepNo;

	/** TEMP 工程順序 */
	private String tempProcStepNo;

	// /** 指図量計 */
	// private String totalQty;

	/** 検索結果リスト */
	private List<RdirectionDirectionFormulaList> searchFormList;

	/** 配合検索行番号 */
	private String line;

	/** 行削除リスト */
	private List<RdirectionDirectionFormulaList> delFormList;

	/** 小数点桁数(HAIGO) */
	private String decimalPointHaigo;

	/** 端数区分(HAIGO) */
	private String roundDivisionHaigo;

	/** 小数点桁数(HAIGO_ADJ) */
	private String decimalPointAdj;

	/** 端数区分(HAIGO_ADJ) */
	private String roundDivisionAdj;

	/**
	 * コンストラクタ
	 */
	public RdirectionFormulaListForm() {
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	@Override
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		} else if ("addlist".equals(getOp())) {
			clearCheck();
		} else if ("dellist".equals(getOp())) {
			clearCheck();
		}
	}

	/**
	 * 入力データの検証
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	@Override
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("search".equals(getOp())) {
			// 一覧の初期化
			setSearchFormList(new ArrayList<RdirectionDirectionFormulaList>());
			setDelFormList(new ArrayList<RdirectionDirectionFormulaList>());
		}
		if ("regist".equals(getOp())) {
			errors = new ActionErrors();
			// 品目コード、数値チェック
			validateSearchListUPD(errors, request);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	@Override
	protected void clear() {
		super.clear();
		// 工程順序コンボボックス
		setSeqCombo(null);
		// 工程順序
		setProcStepNo(null);
		// TEMP 工程順序
		setTempProcStepNo(null);
		// // 配合量計
		// setTotalQty(null);
		// 検索結果リスト
		setSearchFormList(null);
		// 配合検索行番号
		setLine(null);
		// 行削除リスト
		setDelFormList(null);
		// 小数点桁数(HAIGO)
		setDecimalPointHaigo(null);
		// 端数区分(HAIGO)
		setRoundDivisionHaigo(null);
		// 小数点桁数(HAIGO_ADJ)
		setDecimalPointAdj(null);
		// 端数区分(HAIGO_ADJ)
		setRoundDivisionAdj(null);
	}

	//
	// インスタンスメソッド
	//

	// getter,setter
	/**
	 * 工程順序コンボボックスを取得します。
	 * @return 工程順序コンボボックス
	 */
	public List<ComboBoxItems> getSeqCombo() {
		return seqCombo;
	}

	/**
	 * 工程順序コンボボックスを設定します。
	 * @param seqCombo 工程順序コンボボックス
	 */
	public void setSeqCombo(final List<ComboBoxItems> seqCombo) {
		this.seqCombo = seqCombo;
	}

	/**
	 * 工程順序を取得します。
	 * @return procStepNo
	 */
	public String getProcStepNo() {
		return procStepNo;
	}

	/**
	 * 工程順序を設定します。
	 * @param procStepNo 工程順序
	 */
	public void setProcStepNo(final String procStepNo) {
		this.procStepNo = procStepNo;
	}

	/**
	 * TEMP 工程順序を取得します。
	 * @return tempProcStepNo
	 */
	public String getTempProcStepNo() {
		return tempProcStepNo;
	}

	/**
	 * TEMP 工程順序を設定します。
	 * @param tempProcStepNo TEMP 工程順序
	 */
	public void setTempProcStepNo(final String tempProcStepNo) {
		this.tempProcStepNo = tempProcStepNo;
	}

	// /**
	// * 配合量計を取得します。
	// * @return 配合量計
	// */
	// public String getTotalQty() {
	// return totalQty;
	// }
	//
	// /**
	// * 配合量計を設定します。
	// * @param totalQty 配合量計
	// */
	// public void setTotalQty(final String totalQty) {
	// this.totalQty = totalQty;
	// }
	//
	/**
	 * 検索結果リストを取得します。
	 * @return 検索結果リスト
	 */
	public List<RdirectionDirectionFormulaList> getSearchFormList() {
		return searchFormList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchFormList 検索結果リスト
	 */
	public void setSearchFormList(
			final List<RdirectionDirectionFormulaList> searchFormList) {
		this.searchFormList = searchFormList;
	}

	/**
	 * 工程検索行番号取得
	 * @return String 工程検索行番号
	 */
	public String getLine() {
		return line;
	}

	/**
	 * 工程検索行番号設定
	 * @param line 工程検索行番号
	 */
	public void setLine(final String line) {
		this.line = line;
	}

	/**
	 * 行削除リストを取得します。
	 * @return delFormList
	 */
	public List<RdirectionDirectionFormulaList> getDelFormList() {
		return delFormList;
	}

	/**
	 * 行削除リストを設定します。
	 * @param delFormList 行削除リスト
	 */
	public void setDelFormList(
			final List<RdirectionDirectionFormulaList> delFormList) {
		this.delFormList = delFormList;
	}

	/**
	 * 小数点桁数(HAIGO) を取得します。
	 * @return decimalPointHaigo
	 */
	public String getDecimalPointHaigo() {
		return decimalPointHaigo;
	}

	/**
	 * 小数点桁数(HAIGO) を設定します。
	 * @param decimalPointHaigo 小数点桁数(HAIGO)
	 */
	public void setDecimalPointHaigo(final String decimalPointHaigo) {
		this.decimalPointHaigo = decimalPointHaigo;
	}

	/**
	 * 端数区分(HAIGO) を取得します。
	 * @return roundDivisionHaigo
	 */
	public String getRoundDivisionHaigo() {
		return roundDivisionHaigo;
	}

	/**
	 * 端数区分(HAIGO) を設定します。
	 * @param roundDivisionHaigo 端数区分(HAIGO)
	 */
	public void setRoundDivisionHaigo(final String roundDivisionHaigo) {
		this.roundDivisionHaigo = roundDivisionHaigo;
	}

	/**
	 * 小数点桁数(HAIGO_ADJ)を取得します。
	 * @return decimalPointAdj
	 */
	public String getDecimalPointAdj() {
		return decimalPointAdj;
	}

	/**
	 * 小数点桁数(HAIGO_ADJ)を設定します。
	 * @param decimalPointAdj 小数点桁数(HAIGO_ADJ)
	 */
	public void setDecimalPointAdj(final String decimalPointAdj) {
		this.decimalPointAdj = decimalPointAdj;
	}

	/**
	 * 端数区分(HAIGO_ADJ) を取得します。
	 * @return roundDivisionAdj
	 */
	public String getRoundDivisionAdj() {
		return roundDivisionAdj;
	}

	/**
	 * 端数区分(HAIGO_ADJ) を設定します。
	 * @param roundDivisionAdj 端数区分(HAIGO_ADJ)
	 */
	public void setRoundDivisionAdj(final String roundDivisionAdj) {
		this.roundDivisionAdj = roundDivisionAdj;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getSearchFormList() != null) {
			for (RdirectionDirectionFormulaList bean : getSearchFormList()) {
				bean.setCheckFlg(Boolean.FALSE);
			}
		}
	}

	/**
	 * 検索結果の件数を取得する
	 * @return 件数
	 */
	public int getCount() {
		int count = 0;
		if (searchFormList != null) {
			count = searchFormList.size();
		}
		return count;
	}

	/**
	 * 登録時に一覧の入力項目の入力チェックを行う。
	 * @param errors エラー内容
	 * @param request リクエスト
	 */
	private void validateSearchListUPD(final ActionErrors errors,
			final HttpServletRequest request) {
		List<RdirectionDirectionFormulaList> list = this.getSearchFormList();
		int index = 1;

		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		// 検索されていない状態での行削除処理はエラー
		if (list == null) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.rdirection.no.search"));
		} else {

			for (RdirectionDirectionFormulaList bean : list) {

				// 品目コード
				// 必須チェック
				if (StringUtils.isEmpty(bean.getItemCd())) {
					errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("errors.required.row", rb
								.getString("item.rdirection.item.cd"), Integer
								.toString(index)));
				}

				// 実績数量(必須チェック、数値桁数チェック)
				if (StringUtils.isEmpty(bean.getStrResultQty())) {
					errors
							.add(
								ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage(
										"errors.required.row",
										rb
												.getString("item.rdirection.formula.result.qty"),
										Integer.toString(index)));
				} else {
					ActionMessage message = check.checkDigitMessage(
						RdirectionConst.UNIT_DIV_HAIGO, null, null, bean
								.getStrResultQty(),
						rb.getString("item.rdirection.formula.result.qty"));
					if (message != null) {
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					}
					// 実績数量がゼロ以上なら入荷ロット必須
					BigDecimal dd = bean.getLotDivision();
					if (dd != null && dd.equals(new BigDecimal(2))) {
						BigDecimal bd = AecNumberUtils.convertBigDecimal(bean
								.getStrResultQty());
						if (bd.compareTo(BigDecimal.ZERO) > 0) {
							if (StringUtils.isEmpty(bean.getLotNo())) {
								errors
										.add(
											ActionMessages.GLOBAL_MESSAGE,
											new ActionMessage(
													"errors.required.row",
													rb
															.getString("item.rdirection.formula.lot.no"),
													Integer.toString(index)));

							}
						}
					}
				}

				// ロス数量(数値桁数チェック)
				if (!StringUtils.isEmpty(bean.getStrLossQty())) {
					ActionMessage message = check.checkDigitMessage(
						RdirectionConst.UNIT_DIV_HAIGO, null, null, bean
								.getStrLossQty(), rb
								.getString("item.rdirection.formula.loss.qty"));
					if (message != null) {
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					}
				}

				// 調整数量(数値桁数チェック)
				if (!StringUtils.isEmpty(bean.getStrAdjustQty())) {
					ActionMessage message = check.checkDigitMessage(
						RdirectionConst.UNIT_DIV_HAIGO_ADJ, null, null, bean
								.getStrAdjustQty(),
						rb.getString("item.rdirection.formula.adjust.qty"));
					if (message != null) {
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					}
				}

				index++;
			}

			List<RdirectionDirectionFormulaList> delList = this
					.getDelFormList();
			// 更新の必要なし
			if (list.isEmpty() && (delList == null || delList.isEmpty())) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.nodata"));
			}
		}

	}

}
