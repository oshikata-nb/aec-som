/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

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
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaList;

/**
 * 包装指図－配合一覧画面 Formクラス
 * @author tosco
 */
public class PkgDirectionFormulaListForm extends AbstractPkgDirectionForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 工程順序コンボボックス */
	private List<ComboBoxItems> seqCombo;

	/** 工程順序 */
	private String procStepNo;

	/** TEMP 工程順序 */
	private String tempProcStepNo;

	/** 単位 */
	private String unitName;

	/** 単位区分 */
	private String headUnitDiv;

	/** 検索結果リスト */
	private List<PkgDirectionDirectionFormulaList> searchFormList;

	/** 配合検索行番号 */
	private String line;

	/** 行削除リスト */
	private List<PkgDirectionDirectionFormulaList> delFormList;

	/** 小数点桁数 */
	private String decimalPoint;

	/** 端数区分 */
	private String roundDivision;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionFormulaListForm() {
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
		ActionErrors errors = new ActionErrors();
		if ("search".equals(getOp())) {
			// 一覧の初期化
			setSearchFormList(new ArrayList<PkgDirectionDirectionFormulaList>());
			setDelFormList(new ArrayList<PkgDirectionDirectionFormulaList>());
		}
		if ("regist".equals(getOp())) {
			// 登録時チェック処理
			validateRegist(errors, request);
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
		// 単位
		setUnitName(null);
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

	/**
	 * 登録時チェック処理.
	 * @param request HttpServletRequest
	 * @param ActionErrors 検証エラー内容
	 */
	private void validateRegist(final ActionErrors errors, final HttpServletRequest request) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;

		if (this.searchFormList == null) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.pkgdirection.no.search"));
			return;
		}
		for (int i = 0; i < searchFormList.size(); i++) {
			PkgDirectionDirectionFormulaList bean = searchFormList.get(i);

			// 品目コード必須チェック
			if (StringUtils.isEmpty(bean.getItemCd())) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required.row"
					, rb.getString("item.pkgdirection.item.cd")
					, Integer.toString(i + 1)));
			}

			// 予定数量(数値桁数チェック)
			if (StringUtils.isEmpty(bean.getStrQty())) {
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required.row"
									, rb.getString("item.pkgdirection.formula.qty")
									, Integer.toString(i + 1)));
			} else {
				CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);
				ActionMessage errmsg
					= check.checkDigitMessage(PkgDirectionConst.UNIT_DIVISION_HAIGO, null, null,
						bean.getStrQty(),
						rb.getString("item.pkgdirection.formula.qty"));
				if (errmsg != null) {
					errors.add(ActionMessages.GLOBAL_MESSAGE, errmsg);
				}
			}
		}
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
	 * 単位を取得します。
	 * @return unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位を設定します。
	 * @param unitName 単位
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 単位区分を取得します。
	 * @return headUnitDiv
	 */
	public String getHeadUnitDiv() {
		return headUnitDiv;
	}

	/**
	 * 単位区分を設定します。
	 * @param headUnitDiv 単位区分
	 */
	public void setHeadUnitDiv(final String headUnitDiv) {
		this.headUnitDiv = headUnitDiv;
	}

	/**
	 * 検索結果リストを取得します。
	 * @return 検索結果リスト
	 */
	public List<PkgDirectionDirectionFormulaList> getSearchFormList() {
		return searchFormList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchFormList 検索結果リスト
	 */
	public void setSearchFormList(final List<PkgDirectionDirectionFormulaList> searchFormList) {
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
	public List<PkgDirectionDirectionFormulaList> getDelFormList() {
		return delFormList;
	}

	/**
	 * 行削除リストを設定します。
	 * @param delFormList 行削除リスト
	 */
	public void setDelFormList(final List<PkgDirectionDirectionFormulaList> delFormList) {
		this.delFormList = delFormList;
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
	 * 小数点桁数を取得します。
	 * @return decimalPoint 小数点桁数
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
			for (PkgDirectionDirectionFormulaList bean : getSearchFormList()) {
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
}
