/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;

/**
 * 製造指図－配合Form
 * @author tosco
 */
public class DirectionFormulaListForm extends AbstractDirectionForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 工程順序コンボボックス */
	private List<ComboBoxItems> seqCombo;

	/** 工程順序 */
	private String procStepNo;

	/** TEMP 工程順序 */
	private String tempProcStepNo;

	/** 指図量計 */
	private String totalQty;

	/** 検索結果リスト */
	private List<DirectionDirectionFormulaList> searchFormList;

	/** 配合検索行番号 */
	private String line;

	/** 行削除リスト */
	private List<DirectionDirectionFormulaList> delFormList;

	/** 小数点桁数 */
	private String decimalPoint;

	/** 端数区分 */
	private String roundDivision;

	/**
	 * コンストラクタ
	 */
	public DirectionFormulaListForm() {
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
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchFormList(new ArrayList<DirectionDirectionFormulaList>());
			setDelFormList(new ArrayList<DirectionDirectionFormulaList>());
		}
		if ("regist".equals(getOp())) {
			errors = new ActionErrors();
			// 品目コード、指図量チェック
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
		// 指図量計
		setTotalQty(null);
		// 検索結果リスト
		setSearchFormList(null);
		// 配合検索行番号
		setLine(null);
		// 行削除リスト
		setDelFormList(null);
		// 小数点桁数
		setDecimalPoint(null);
		// 端数区分
		setRoundDivision(null);
	}

	//
	// インスタンスメソッド
	//

	//getter,setter
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

	/**
	 * 指図量計を取得します。
	 * @return 配合量計
	 */
	public String getTotalQty() {
		return totalQty;
	}

	/**
	 * 指図量計を設定します。
	 * @param totalQty 指図量計
	 */
	public void setTotalQty(final String totalQty) {
		this.totalQty = totalQty;
	}

	/**
	 * 検索結果リストを取得します。
	 * @return 検索結果リスト
	 */
	public List<DirectionDirectionFormulaList> getSearchFormList() {
		return searchFormList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchFormList 検索結果リスト
	 */
	public void setSearchFormList(final List<DirectionDirectionFormulaList> searchFormList) {
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
	public List<DirectionDirectionFormulaList> getDelFormList() {
		return delFormList;
	}

	/**
	 * 行削除リストを設定します。
	 * @param delFormList 行削除リスト
	 */
	public void setDelFormList(final List<DirectionDirectionFormulaList> delFormList) {
		this.delFormList = delFormList;
	}

	/**
	 * 小数点桁数を取得します。
	 * @return decimalPoint
	 */
	public String getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * 小数点桁数を設定します。
	 * @param decimalPoint 小数点桁数
	 */
	public void setDecimalPoint(final String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	/**
	 * 端数区分を取得します。
	 * @return roundDivision
	 */
	public String getRoundDivision() {
		return roundDivision;
	}

	/**
	 * 端数区分を設定します。
	 * @param roundDivision 端数区分
	 */
	public void setRoundDivision(final String roundDivision) {
		this.roundDivision = roundDivision;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getSearchFormList() != null) {
			for (DirectionDirectionFormulaList bean : getSearchFormList()) {
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
	 * 登録時に一覧の入力項目(品目コード、指図量)の入力チェックを行う。
	 * @param errors エラー内容
	 * @param request リクエスト
	 */
	private void validateSearchListUPD(final ActionErrors errors, final HttpServletRequest request) {
		List<DirectionDirectionFormulaList> list = this.getSearchFormList();
		int index = 1;

		ResourceBundle rb = ResourceBundle.getBundle(Constants.APPLICATION_PROPERTIES);

		// 検索されていない状態での行削除処理はエラー
		if (list == null) {
			errors.add("", new ActionMessage("errors.mgrecipe.no.search"));
		} else  {

			for (DirectionDirectionFormulaList bean : list) {

				// 品目コード
				// 必須チェック
				if (StringUtils.isEmpty(bean.getItemCd())) {
					errors.add("", new ActionMessage("errors.required.row"
									, rb.getString("item.direction.item.cd")
									, Integer.toString(index)));
				}

				// 指図量(数値桁数チェック)
				if (StringUtils.isEmpty(bean.getStrQty())) {
					errors.add("", new ActionMessage("errors.required.row"
									, rb.getString("item.mgrecipe.formula.qty")
									, Integer.toString(index)));
				} else {
					CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);
					ActionMessage message
						= check.checkDigitMessage(
									"HAIGO"
									, null
									, null
									, bean.getStrQty()
									, rb.getString("item.direction.formula.qty"));
					if (message != null) {
						errors.add("", message);
					}
				}

				index++;
			}

			List<DirectionDirectionFormulaList> delList = this.getDelFormList();
			// 更新の必要なし
			if (list.isEmpty() && (delList == null || delList.isEmpty())) {
				errors.add("", new ActionMessage("errors.nodata"));
			}
		}

	}

}
