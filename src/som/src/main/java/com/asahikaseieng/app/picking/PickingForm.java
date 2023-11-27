/*
 * Created on 2007/12/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.picking;

import javax.servlet.http.HttpServletRequest;

import com.asahikaseieng.struts.AbstractForm;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 * 出荷実績詳細 Formクラス.
 * @author jbd
 */
public final class PickingForm extends AbstractForm { // AbstractSearchForm {

	private static final long serialVersionUID = 1L;

//	/* ページの明細行数 */
//	private static final int PAGE_ROW;
//
//	/* 最大データ数 */
//	private static final int DATA_ROW;

//	static {
//		ResourceBundle rb = ResourceBundle
//				.getBundle(Constants.SYSTEM_PROPERTIES);
//
//		PAGE_ROW = Integer.parseInt(rb.getString("linage.sample"));
//		DATA_ROW = Integer.parseInt(rb.getString("threshold.sample"));
//	}


	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public PickingForm() {
	}

//	/**
//	 * {@inheritDoc}
//	 */
//	protected int getLimit() {
//		return PAGE_ROW;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	protected int getThreshold() {
//		return DATA_ROW;
//	}

//	/**
//	 * {@inheritDoc}
//	 */
//	protected Class getPagerConditionClass() {
//		return PickingPagerCondition.class;
//	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);
		if ("report".equals(getOp())) {
			clear();
		}
//		/* ダウンロードフラグを倒す */
//		setExcelDownloadFlg(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		return errors;
	}

	/**
	 * 初期化.
	 */
	private void clear() {
		/** EXCELダウンロードフラグ */
		setExcelDownloadFlg(false);
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

}
