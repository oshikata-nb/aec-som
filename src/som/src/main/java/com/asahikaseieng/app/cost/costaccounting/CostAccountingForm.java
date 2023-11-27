/*
 * Created on 2009/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.cost.costaccounting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 原価計算データ送信 Formクラス.
 * @author t0011036
 */
public class CostAccountingForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 対象年月 */
	private String srhStrInputDate;

	/* マスタ送信区分 */
	private BigDecimal srhSendDivision;

	/* 取込用インデックス */
	private int index;

	/* リスト */
	private List<CostAccountingBean> executeList;

	/* 取込リスト */
	private List<CostAccountingBean> importList;

	/**
	 * コンストラクタ.
	 */
	public CostAccountingForm() {
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
		}

		if ("update".equals(getOp())) {
			/* チェックボックスクリア */
			clearCheck();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp()) || "importData".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setSrhStrInputDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM"));
		setSrhSendDivision(BigDecimal.ONE);
		setIndex(0);
		setExecuteList(new ArrayList<CostAccountingBean>());
		setImportList(new ArrayList<CostAccountingBean>());

		/* 必要行数分だけ追加 */
		for (int i = 0; i < 11; i++) {
			CostAccountingBean bean = new CostAccountingBean();
			bean.setChecked(true);
			bean.setTableName(getTableName(i));
			bean.setCnt(BigDecimal.ZERO);
			bean.setStrCnt("0");
			bean.setResult(null);
			getExecuteList().add(bean);
		}

		for (int i = 0; i < 1; i++) {
			CostAccountingBean bean = new CostAccountingBean();
			bean.setTableName(getImportTableName(i));
			bean.setCnt(BigDecimal.ZERO);
			bean.setStrCnt("0");
			bean.setResult(null);
			getImportList().add(bean);
		}
	}

	/**
	 * テーブル名取得
	 * @param i インデックス
	 * @return テーブル名
	 */
	private String getTableName(final int i) {
		String tableName = null;

		switch (i) {
		case CostAccountingConst.OPERATION_IDX:
			tableName = CostAccountingConst.OPERATION_NAME;
			break;
		case CostAccountingConst.ITEM_IDX:
			tableName = CostAccountingConst.ITEM_NAME;
			break;
		case CostAccountingConst.PROCEDURE_IDX:
			tableName = CostAccountingConst.PROCEDURE_NAME;
			break;
		case CostAccountingConst.FORMATION_IDX:
			tableName = CostAccountingConst.FORMATION_NAME;
			break;
		case CostAccountingConst.ORDER_IDX:
			tableName = CostAccountingConst.ORDER_NAME;
			break;
		case CostAccountingConst.REPORT_IDX:
			tableName = CostAccountingConst.REPORT_NAME;
			break;
		case CostAccountingConst.SALES_ORDER_IDX:
			tableName = CostAccountingConst.SALES_ORDER_NAME;
			break;
		case CostAccountingConst.STOCKING_IDX:
			tableName = CostAccountingConst.STOCKING_NAME;
			break;
		case CostAccountingConst.SALES_IDX:
			tableName = CostAccountingConst.SALES_NAME;
			break;
		case CostAccountingConst.RECEIPT_IDX:
			tableName = CostAccountingConst.RECEIPT_NAME;
			break;
		case CostAccountingConst.LEDGER_IDX:
			tableName = CostAccountingConst.LEDGER_NAME;
			break;
		default:
			break;
		}

		return tableName;
	}

	/**
	 * 取込テーブル名取得
	 * @param i インデックス
	 * @return テーブル名
	 */
	private String getImportTableName(final int i) {
		String tableName = null;

		switch (i) {
		case CostAccountingConst.IMPORT_LEDGER_IDX:
			tableName = CostAccountingConst.IMPORT_LEDGER_NAME;
			break;
		default:
			break;
		}

		return tableName;
	}

	/**
	 * チェックボックスクリア
	 */
	private void clearCheck() {
		if (getExecuteList() != null) {
			for (CostAccountingBean list : getExecuteList()) {
				list.setChecked(false);
			}
		}
	}

	/**
	 * srhStrInputDateを取得します。
	 * @return srhStrInputDate
	 */
	public String getSrhStrInputDate() {
		return srhStrInputDate;
	}

	/**
	 * srhStrInputDateを設定します。
	 * @param srhStrInputDate srhStrInputDate
	 */
	public void setSrhStrInputDate(final String srhStrInputDate) {
		this.srhStrInputDate = srhStrInputDate;
	}

	/**
	 * executeListを取得します。
	 * @return executeList
	 */
	public List<CostAccountingBean> getExecuteList() {
		return executeList;
	}

	/**
	 * executeListを設定します。
	 * @param executeList executeList
	 */
	public void setExecuteList(final List<CostAccountingBean> executeList) {
		this.executeList = executeList;
	}

	/**
	 * importListを取得します。
	 * @return importList
	 */
	public List<CostAccountingBean> getImportList() {
		return importList;
	}

	/**
	 * importListを設定します。
	 * @param importList importList
	 */
	public void setImportList(final List<CostAccountingBean> importList) {
		this.importList = importList;
	}

	/**
	 * indexを取得します。
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * indexを設定します。
	 * @param index index
	 */
	public void setIndex(final int index) {
		this.index = index;
	}

	/**
	 * srhSendDivisionを取得します。
	 * @return srhSendDivision
	 */
	public BigDecimal getSrhSendDivision() {
		return srhSendDivision;
	}

	/**
	 * srhSendDivisionを設定します。
	 * @param srhSendDivision srhSendDivision
	 */
	public void setSrhSendDivision(final BigDecimal srhSendDivision) {
		this.srhSendDivision = srhSendDivision;
	}
}
