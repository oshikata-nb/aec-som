/*
 * Created on 2008/04/18
 *
 * $copyright$
 * tosco:相殺グループマスタ
 */
package com.asahikaseieng.app.master.offsetgroup;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplist.OffsetGroupList;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplist.OffsetGroupListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplistforreport.OffsetGroupListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 相殺グループマスタ一覧 Formクラス
 * @author tosco
 */
public final class OffsetGroupListForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

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

	//
	// 検索用.相殺グループマスタ
	//

	/** 検索入力：相殺グループコード */
	private String srhOffsetGroupCd;

	/** 検索入力：相殺グループ名称 */
	private String srhOffsetGroupName;

	/** 検索入力：請求先／支払先コード */
	private String srhVenderCd;

	/** 帳票Excel用検索条件 */
	private OffsetGroupListConditionForReport condition;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	//
	// インスタンスフィールド.相殺グループマスタ
	//

	/** リスト */
	private List<OffsetGroupList> searchList;

	/**
	 * コンストラクタ.相殺グループマスタ
	 */
	public OffsetGroupListForm() {
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
		return OffsetGroupListPagerCondition.class;
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
			setSearchList(new ArrayList<OffsetGroupList>());
		}

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.相殺グループマスタ
	 */
	public void clear() {

		/* 検索リストのクリア */
		setSearchList(new ArrayList<OffsetGroupList>());

		/* 検索入力：相殺グループコード */
		setSrhOffsetGroupCd(null);
		/* 検索入力：相殺グループ名称 */
		setSrhOffsetGroupName(null);
		/* 検索入力：請求先／支払先 */
		setSrhVenderCd(null);
		/* 検索条件 */
		setCondition(null);
	}

	/**
	 * dirtyFlgを取得します。
	 * 
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * 
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 相殺グループマスタ：searchListを取得します。
	 * 
	 * @return searchList
	 */
	public List<OffsetGroupList> getSearchList() {
		return searchList;
	}

	/**
	 * 相殺グループマスタ： searchListを設定します。
	 * 
	 * @param searchList searchList
	 */
	public void setSearchList(final List<OffsetGroupList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.相殺グループマスタ
	//

	/**
	 * 検索入力：相殺グループコードを取得します。
	 * @return srhOffsetGroupCd
	 */
	public String getSrhOffsetGroupCd() {
		return srhOffsetGroupCd;
	}

	/**
	 * 検索入力：相殺グループコードを設定します。
	 * @param srhOffsetGroupCd 検索入力：相殺グループコード
	 */
	public void setSrhOffsetGroupCd(final String srhOffsetGroupCd) {
		this.srhOffsetGroupCd = srhOffsetGroupCd;
	}

	/**
	 * 検索入力：相殺グループ名称を取得します。
	 * @return srhOffsetGroupName
	 */
	public String getSrhOffsetGroupName() {
		return srhOffsetGroupName;
	}

	/**
	 * 検索入力：相殺グループ名称を設定します。
	 * @param srhOffsetGroupName 検索入力：相殺グループ名称
	 */
	public void setSrhOffsetGroupName(final String srhOffsetGroupName) {
		this.srhOffsetGroupName = srhOffsetGroupName;
	}

	/**
	 * 検索入力：請求先／支払先コードを取得します。
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * 検索入力：請求先／支払先コードを設定します。
	 * @param srhVenderCd 検索入力：請求先／支払先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
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
	 * conditionを取得します。
	 * @return condition
	 */
	public OffsetGroupListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final OffsetGroupListConditionForReport condition) {
		this.condition = condition;
	}
}
