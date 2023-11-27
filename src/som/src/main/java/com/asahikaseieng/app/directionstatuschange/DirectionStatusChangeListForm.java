/*
 * Created on 2009/05/28
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.directionstatuschange;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.directionstatuschange.DirectionStatusChangeList;
import com.asahikaseieng.dao.nonentity.directionstatuschange.DirectionStatusChangePagerCondition;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 製造指図ステータス変更 Formクラス.
 * @author tosco
 * 
 */
public final class DirectionStatusChangeListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** 変更フラグ */
	private String dirtyFlg;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	//
	// 検索用.
	//

	/** 検索入力：指図番号 */
	private String srhDirectionNo;

	/** 検索入力：生産ライン */
	private String srhProductionLine;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：品目名称 */
	private String srhItemName;

	/** 検索入力：他社コード１ */
	private String srhOtherCompanyCd1;

	/** 検索入力：指図ステータス */
	private String srhDirectionStatus;

	/** 生産工場コンボボックス */
	private List<ComboBoxItems> lineCombo;

	/** 帳票EXCELダウンロードフラグ */
	private boolean excelReportDownloadFlg;

	/** 帳票Excel検索用 */
	private DirectionStatusChangeListConditionForReport reportCondition;

	//
	// インスタンスフィールド.
	//

	/** リスト */
	private List<DirectionStatusChangeList> searchList;

	/**
	 * コンストラクタ
	 */
	public DirectionStatusChangeListForm() {
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
		return DirectionStatusChangePagerCondition.class;
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

		if ("update".equals(getOp())) {
			clearCheck();
		}
		setExcelReportDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			// イレギュラーだけど、ここでリストをクリア
			setSearchList(new ArrayList<DirectionStatusChangeList>());
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}

		if ("update".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
			// validateメソッドによる入力チェック
			validateUpdateList(errors);
		}
		return errors;
	}

	/**
	 * 更新処理の入力チェック
	 * @param errors エラー内容
	 */
	private void validateUpdateList(final ActionErrors errors) {

	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getSearchList() != null) {
			for (DirectionStatusChangeList bean : getSearchList()) {
				bean.setSelectedCheck(Boolean.FALSE);
			}
		}
	}

	/**
	 * 初期化.
	 */
	public void clear() {

		// 検索リストのクリア
		setSearchList(new ArrayList<DirectionStatusChangeList>());

		// 検索入力：指図番号
		setSrhDirectionNo(null);
		// 検索入力：生産ライン
		setSrhProductionLine(null);
		// 検索入力：主要製品コード
		setSrhItemCd(null);
		// 検索入力：主要製品名称
		setSrhItemName(null);
		// 検索入力：他社コード１
		setSrhOtherCompanyCd1(null);
		// 検索入力：指図ステータス
		setSrhDirectionStatus(null);
		// 生産工場コンボボックス
		setLineCombo(null);
		setExcelReportDownloadFlg(false);
		setReportCondition(null);
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<DirectionStatusChangeList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<DirectionStatusChangeList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.
	//

	/**
	 * 検索入力：指図番号取得.
	 * @return String 指図番号
	 */
	public String getSrhDirectionNo() {
		return this.srhDirectionNo;
	}

	/**
	 * 検索入力：指図番号設定.
	 * @param srhDirectionNo 指図番号
	 */
	public void setSrhDirectionNo(final String srhDirectionNo) {
		this.srhDirectionNo = srhDirectionNo;
	}

	/**
	 * 検索入力：生産ライン取得.
	 * @return String 生産ライン
	 */
	public String getSrhProductionLine() {
		return this.srhProductionLine;
	}

	/**
	 * 検索入力：生産ライン設定.
	 * @param srhProductionLine 生産ライン
	 */
	public void setSrhProductionLine(final String srhProductionLine) {
		this.srhProductionLine = srhProductionLine;
	}

	/**
	 * 検索入力：品目コード取得.
	 * @return String 品目コード
	 */
	public String getSrhItemCd() {
		return this.srhItemCd;
	}

	/**
	 * 検索入力：品目コード設定.
	 * @param srhItemCd 品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * 検索入力：他社コード１取得.
	 * @return String 他社コード１
	 */
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 検索入力：他社コード１.
	 * @param srhOtherCompanyCd1 他社コード１
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * 検索入力：指図ステータスを取得します。
	 * @return srhDirectionStatus
	 */
	public String getSrhDirectionStatus() {
		return srhDirectionStatus;
	}

	/**
	 * 検索入力：指図ステータスを設定します。
	 * @param srhDirectionStatus 検索入力：指図ステータス
	 */
	public void setSrhDirectionStatus(final String srhDirectionStatus) {
		this.srhDirectionStatus = srhDirectionStatus;
	}

	/**
	 * 生産工場コンボボックスを取得します。
	 * @return List<ComboBoxItems> 生産工場コンボボックス
	 */
	public List<ComboBoxItems> getLineCombo() {
		return lineCombo;
	}

	/**
	 * 生産工場コンボボックスを設定します。
	 * @param lineCombo 生産工場コンボボックス
	 */
	public void setLineCombo(final List<ComboBoxItems> lineCombo) {
		this.lineCombo = lineCombo;
	}

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
	 * 品目名称を取得します。
	 * @return srhItemName
	 */
	public String getSrhItemName() {
		return srhItemName;
	}

	/**
	 * 品目名称を設定します。
	 * @param srhItemName 品目名称
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * 検索結果数を取得する。
	 * @return 検索結果数
	 */
	public int getCount() {
		int res = 0;
		if (searchList != null) {
			res = searchList.size();
		}
		return res;
	}

	/**
	 * excelReportDownloadFlgを取得します。
	 * @return excelReportDownloadFlg
	 */
	public boolean isExcelReportDownloadFlg() {
		return excelReportDownloadFlg;
	}

	/**
	 * excelReportDownloadFlgを設定します。
	 * @param excelReportDownloadFlg excelReportDownloadFlg
	 */
	public void setExcelReportDownloadFlg(final boolean excelReportDownloadFlg) {
		this.excelReportDownloadFlg = excelReportDownloadFlg;
	}

	/**
	 * reportConditionを取得します。
	 * @return reportCondition
	 */
	public DirectionStatusChangeListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * reportConditionを設定します。
	 * @param reportCondition reportCondition
	 */
	public void setReportCondition(
			final DirectionStatusChangeListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}
}
