package com.asahikaseieng.app.master.salestermsandestimate;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelist.SalestermsAndEstimateList;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelist.SalestermsAndEstimateListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.SalestermsAndEstimateListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 販売条件・見積単価 コピー作成・削除一覧 Formクラス.
 * @author t0011036
 */
public final class SalestermsAndEstimateListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	/* 入力日From */
	private String strSrhInputDateFrom;
	
	/* 入力日To */
	private String strSrhInputDateTo;
	
	/* 入力者コード */
	private String srhTantoCd;

	/* 入力者名 */
	private String srhTantoName;
	
	/* 処理区分名称 */
	private String srhProcessDivision;
	
	private String[] srhProcessDivisionValues;

	private String[] srhProcessDivisionLabels;
	
	/* ステータス名称 */
	private String srhStatus;
	
	private String[] srhStatusValues;

	private String[] srhStatusLabels;

	
	/* コピー元・削除品目コード */
	private String srhItemCdFrom;
	
	/* コピー元・削除品目名称 */
	private String srhItemNameFrom;
	
	/* コピー先品目コード */
	private String srhItemCdTo;
	
	/* コピー先品目名称 */
	private String srhItemNameTo;
	
	/* 販売条件リスト */
	private List<SalestermsAndEstimateList> searchList;
	
	/* 帳票Excel用検索条件 */
	private SalestermsAndEstimateListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** ログ出力用エラーコード */
	private String errorCd;

	/** ログ出力用エラーメッセージ */
	private String errorMsg;
	/* 確定・確定取消判別フラグ */
	private String confirmFlg;

	/**
	 * コンストラクタ.
	 */
	public SalestermsAndEstimateListForm() {
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
		return SalestermsAndEstimateListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);
		if ("init".equals(getOp())) {
			/* 初期化 */
			clear();
			clearSelectedCheck();
		}
		
		if ("reFresh".equals(getOp())) {
			clearSelectedCheck();
		}

		/* ダウンロードフラグを倒す */
		setExcelDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchList(new ArrayList<SalestermsAndEstimateList>());
		}

		if ("search".equals(getOp()) || "update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}
	
	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearSelectedCheck() {
		if (getSearchList() != null) {
			for (SalestermsAndEstimateList bean : getSearchList()) {
				bean.setSalestermsAndEstimateCheckBox(Boolean.FALSE);
			}
		}
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setSearchList(new ArrayList<SalestermsAndEstimateList>());
		setCondition(null);
		setStrSrhInputDateFrom(null);
		setStrSrhInputDateTo(null);
		setSrhTantoCd(null);
		setSrhTantoName(null);
		setSrhProcessDivision(null);
		setSrhProcessDivisionValues(null);
		setSrhProcessDivisionLabels(null);
		setSrhStatus(null);
		setSrhStatusValues(null);
		setSrhStatusLabels(null);
		setSrhItemCdFrom(null);
		setSrhItemNameFrom(null);
		setSrhItemCdTo(null);
		setSrhItemNameTo(null);
	}

	/**
	 * excelDownloadFlgを取得します。
	 * @return excelDownloadFlg
	 */
	public boolean isExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * excelDownloadFlgを設定します。
	 * @param excelDownloadFlg excelDownloadFlg
	 */
	public void setExcelDownloadFlg(final boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
	}

	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<SalestermsAndEstimateList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<SalestermsAndEstimateList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public SalestermsAndEstimateListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final SalestermsAndEstimateListConditionForReport condition) {
		this.condition = condition;
	}

	/**
	 * strSrhInputDateFromを取得します。
	 * @return strSrhInputDateFrom
	 */
	public String getStrSrhInputDateFrom() {
		return strSrhInputDateFrom;
	}

	/**
	 * strSrhInputDateFromを設定します。
	 * @param strSrhInputDateFrom strSrhInputDateFrom
	 */
	public void setStrSrhInputDateFrom(final String strSrhInputDateFrom) {
		this.strSrhInputDateFrom = strSrhInputDateFrom;
	}

	/**
	 * strSrhInputDateToを取得します。
	 * @return strSrhInputDateTo
	 */
	public String getStrSrhInputDateTo() {
		return strSrhInputDateTo;
	}

	/**
	 * strSrhInputDateToを設定します。
	 * @param strSrhInputDateTo strSrhInputDateTo
	 */
	public void setStrSrhInputDateTo(final String strSrhInputDateTo) {
		this.strSrhInputDateTo = strSrhInputDateTo;
	}
	
	/**
	 * srhTantoCdを取得します。
	 * @return srhTantoCd
	 */
	public String getSrhTantoCd() {
		return srhTantoCd;
	}

	/**
	 * srhTantoCdを設定します。
	 * @param srhTantoCd srhTantoCd
	 */
	public void setSrhTantoCd(String srhTantoCd) {
		this.srhTantoCd = srhTantoCd;
	}
	
	/**
	 * srhProcessDivisionを取得します。
	 * @return srhProcessDivision
	 */
	public String getSrhProcessDivision() {
		return srhProcessDivision;
	}

	/**
	 * srhProcessDivisionを設定します。
	 * @param srhProcessDivision srhProcessDivision
	 */
	public void setSrhProcessDivision(final String srhProcessDivision) {
		this.srhProcessDivision = srhProcessDivision;
	}

	/**
	 * srhProcessDivisionValuesを取得します。
	 * @return srhProcessDivisionValues
	 */
	public String[] getSrhProcessDivisionValues() {
		return srhProcessDivisionValues;
	}

	/**
	 * srhProcessDivisionValuesを設定します。
	 * @param srhProcessDivisionValues srhProcessDivisionValues
	 */
	public void setSrhProcessDivisionValues(final String[] srhProcessDivisionValues) {
		this.srhProcessDivisionValues = srhProcessDivisionValues;
	}

	/**
	 * srhProcessDivisionLabelsを取得します。
	 * @return srhProcessDivisionLabels
	 */
	public String[] getSrhProcessDivisionLabels() {
		return srhProcessDivisionLabels;
	}

	/**
	 * srhProcessDivisionLabelsを設定します。
	 * @param srhProcessDivisionLabels srhProcessDivisionLabels
	 */
	public void setSrhProcessDivisionLabels(final String[] srhProcessDivisionLabels) {
		this.srhProcessDivisionLabels = srhProcessDivisionLabels;
	}

	/**
	 * srhStatusを取得します。
	 * @return srhStatus
	 */
	public String getSrhStatus() {
		return srhStatus;
	}

	/**
	 * srhStatusを設定します。
	 * @param srhStatus srhStatus
	 */
	public void setSrhStatus(final String srhStatus) {
		this.srhStatus = srhStatus;
	}
	
	/**
	 * srhStatusValuesを取得します。
	 * @return srhStatusValues
	 */
	public String[] getSrhStatusValues() {
		return srhStatusValues;
	}

	/**
	 * srhStatusValuesを設定します。
	 * @param srhStatusValues srhStatusValues
	 */
	public void setSrhStatusValues(final String[] srhStatusValues) {
		this.srhStatusValues = srhStatusValues;
	}

	/**
	 * srhStatusLabelsを取得します。
	 * @return srhStatusLabels
	 */
	public String[] getSrhStatusLabels() {
		return srhStatusLabels;
	}

	/**
	 * srhStatusLabelsを設定します。
	 * @param srhStatusLabels srhStatusLabels
	 */
	public void setSrhStatusLabels(final String[] srhStatusLabels) {
		this.srhStatusLabels = srhStatusLabels;
	}

	/**
	 * srhItemCdFromを取得します。
	 * @return srhItemCdFrom
	 */
	public String getSrhItemCdFrom() {
		return srhItemCdFrom;
	}

	/**
	 * srhItemCdFromを設定します。
	 * @param srhItemCdFrom srhItemCdFrom
	 */
	public void setSrhItemCdFrom(final String srhItemCdFrom) {
		this.srhItemCdFrom = srhItemCdFrom;
	}

	/**
	 * srhItemNameFromを取得します。
	 * @return srhItemNameFrom
	 */
	public String getSrhItemNameFrom() {
		return srhItemNameFrom;
	}

	/**
	 * srhItemNameFromを設定します。
	 * @param srhItemNameFrom srhItemNameFrom
	 */
	public void setSrhItemNameFrom(final String srhItemNameFrom) {
		this.srhItemNameFrom = srhItemNameFrom;
	}

	/**
	 * srhItemCdToを取得します。
	 * @return srhItemCdTo
	 */
	public String getSrhItemCdTo() {
		return srhItemCdTo;
	}

	/**
	 * srhItemCdToを設定します。
	 * @param srhItemCdTo srhItemCdTo
	 */
	public void setSrhItemCdTo(final String srhItemCdTo) {
		this.srhItemCdTo = srhItemCdTo;
	}

	/**
	 * srhItemNameToを取得します。
	 * @return srhItemNameTo
	 */
	public String getSrhItemNameTo() {
		return srhItemNameTo;
	}

	/**
	 * srhItemNameToを設定します。
	 * @param srhItemNameTo srhItemNameTo
	 */
	public void setSrhItemNameTo(final String srhItemNameTo) {
		this.srhItemNameTo = srhItemNameTo;
	}

	/**
	 * srhTantoNameを取得します。
	 * @return srhTantoName
	 */
	public String getSrhTantoName() {
		return srhTantoName;
	}

	/**
	 * srhTantoNameを設定します。
	 * @param srhTantoName srhTantoName
	 */
	public void setSrhTantoName(final String srhTantoName) {
		this.srhTantoName = srhTantoName;
	}

	/**
	 * errorCdを取得します。
	 * @return errorCd
	 */
	public String getErrorCd() {
		return errorCd;
	}

	/**
	 * errorCdを設定します。
	 * @param errorCd errorCd
	 */
	public void setErrorCd(String errorCd) {
		this.errorCd = errorCd;
	}

	/**
	 * errorMsgを取得します。
	 * @return errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * errorMsgを設定します。
	 * @param errorMsg errorMsg
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * confirmFlgを取得します。
	 * @return confirmFlg
	 */
	public String getConfirmFlg() {
		return confirmFlg;
	}

	/**
	 * confirmFlgを設定します。
	 * @param confirmFlg confirmFlg
	 */
	public void setConfirmFlg(String confirmFlg) {
		this.confirmFlg = confirmFlg;
	}

}

