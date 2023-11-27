/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carryfile;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.carryfilelist.CarryFileList;
import com.asahikaseieng.dao.nonentity.master.carryfilelist.CarryFileListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.carryfilelistforreport.CarryFileListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 運送会社一覧 Formクラス.
 * @author a1053739
 */
public final class CarryFileListForm extends AbstractSearchForm {

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

	/* 運送会社コード */
	private String srhCarryCd;

	/* 運送会社名称 */
	private String srhCarryName;

	/* 運送会社連携ファイルマスタ設定有無 */
	private String srhExistsSetting;
	
	/* 運送会社リスト */
	private List<CarryFileList> searchList;

	/* 帳票Excel用検索条件 */
	private CarryFileListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public CarryFileListForm() {
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
		return CarryFileListPagerCondition.class;
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
			setSearchList(new ArrayList<CarryFileList>());
		}

		if ("search".equals(getOp()) || "update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		setSrhCarryCd(null);
		setSrhCarryName(null);
		setSearchList(new ArrayList<CarryFileList>());
		setCondition(null);
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
	public List<CarryFileList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<CarryFileList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhCarryCdを取得します。
	 * @return srhCarryCd
	 */
	public String getSrhCarryCd() {
		return srhCarryCd;
	}

	/**
	 * srhCarryCdを設定します。
	 * @param srhCarryCd srhCarryCd
	 */
	public void setSrhCarryCd(final String srhCarryCd) {
		this.srhCarryCd = srhCarryCd;
	}

	/**
	 * srhCarryNameを取得します。
	 * @return srhCarryName
	 */
	public String getSrhCarryName() {
		return srhCarryName;
	}

	/**
	 * srhCarryNameを設定します。
	 * @param srhCarryName srhCarryName
	 */
	public void setSrhCarryName(final String srhCarryName) {
		this.srhCarryName = srhCarryName;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public CarryFileListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final CarryFileListConditionForReport condition) {
		this.condition = condition;
	}

	/**
	 * srhExistsSettingを取得します。
	 * @return srhExistsSetting
	 */
	public String getSrhExistsSetting() {
		return srhExistsSetting;
	}

	/**
	 * srhExistsSettingを設定します。
	 * @param srhExistsSetting srhExistsSetting
	 */
	public void setSrhExistsSetting(String srhExistsSetting) {
		this.srhExistsSetting = srhExistsSetting;
	}

}
