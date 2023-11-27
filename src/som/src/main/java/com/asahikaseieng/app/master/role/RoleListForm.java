/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.role;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.rolelist.RoleList;
import com.asahikaseieng.dao.nonentity.master.rolelist.RoleListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.rolelistforreport.RoleListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * ロール一覧 Formクラス
 * @author t0011036
 */
public final class RoleListForm extends AbstractSearchForm {

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

	/* ロールコード */
	private String srhRoleId;

	/* ロール名称 */
	private String srhRoleName;

	/* ロールリスト */
	private List<RoleList> searchList;

	/* 帳票Excel用検索条件 */
	private RoleListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.ロールマスタ
	 */
	public RoleListForm() {
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
		return RoleListPagerCondition.class;
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
			setSearchList(new ArrayList<RoleList>());
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
		setSrhRoleId(null);
		setSrhRoleName(null);
		setSearchList(new ArrayList<RoleList>());
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
	public List<RoleList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<RoleList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * srhRoleIdを取得します。
	 * @return srhRoleId
	 */
	public String getSrhRoleId() {
		return srhRoleId;
	}

	/**
	 * srhRoleIdを設定します。
	 * @param srhRoleId srhRoleId
	 */
	public void setSrhRoleId(final String srhRoleId) {
		this.srhRoleId = srhRoleId;
	}

	/**
	 * srhRoleNameを取得します。
	 * @return srhRoleName
	 */
	public String getSrhRoleName() {
		return srhRoleName;
	}

	/**
	 * srhRoleNameを設定します。
	 * @param srhRoleName srhRoleName
	 */
	public void setSrhRoleName(final String srhRoleName) {
		this.srhRoleName = srhRoleName;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public RoleListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final RoleListConditionForReport condition) {
		this.condition = condition;
	}
}
