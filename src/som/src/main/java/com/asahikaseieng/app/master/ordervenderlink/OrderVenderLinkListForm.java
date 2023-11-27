/*
 * Created on 2020/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.ordervenderlink;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.master.ordervenderlinklistforreport.OrderVenderLinkListConditionForReport;
import com.asahikaseieng.dao.nonentity.orderrecieve.ordervendermaster.OrderVenderMasterList;
import com.asahikaseieng.dao.nonentity.orderrecieve.ordervendermaster.OrderVenderMasterListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 取引先グループ一覧 Formクラス.
 * @author 
 */
public final class OrderVenderLinkListForm extends AbstractSearchForm {

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
	/* 得意先グループコード */
	private String srhVenderGroupCd;
	
	/* 納入先設定*/
	private BigDecimal srhDeliveryConf;
	
	/* 品目設定*/
	private BigDecimal srhItemConf;
	
	/* 得意先グループコンボボックス */
	private List<ComboBoxItems> venderGroupCombo;

	/* 取引先リスト */
	private List<OrderVenderMasterList> searchList;

	/* 帳票Excel用検索条件 */
	private OrderVenderLinkListConditionForReport condition;

	/* EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;
	
	/* EXCEL アップロードフラグ*/
	private boolean excelUploadFlg;
	
	/** アップロード ファイル名称 */
	private FormFile uploadFile;

	/**
	 * コンストラクタ.
	 */
	public OrderVenderLinkListForm() {
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
	protected Class<OrderVenderMasterListPagerCondition> getPagerConditionClass() {
		return OrderVenderMasterListPagerCondition.class;
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
			setSearchList(new ArrayList<OrderVenderMasterList>());
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
		setSearchList(new ArrayList<OrderVenderMasterList>());
		setCondition(null);
		setSrhVenderGroupCd(null);
		setSrhDeliveryConf(null);
		setSrhItemConf(null);
		setUploadFile(null);
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
	 * excelUploadFlgを取得します。
	 * @return excelUploadFlg
	 */
	public boolean isExcelUploadFlg() {
		return excelUploadFlg;
	}

	/**
	 * excelUploadFlgを設定します。
	 * @param excelUploadFlg excelUploadFlg
	 */
	public void setExcelUploadFlg(final boolean excelUploadFlg) {
		this.excelUploadFlg = excelUploadFlg;
	}
	
	/**
	 * srhVenderGroupCdを取得します。
	 * @return srhVenderGroupCd
	 */
	public String getSrhVenderGroupCd() {
		return srhVenderGroupCd;
	}

	/**
	 * srhVenderGroupCdを設定します。
	 * @param srhVenderGroupCd srhVenderGroupCd
	 */
	public void setSrhVenderGroupCd(final String srhVenderGroupCd) {
		this.srhVenderGroupCd = srhVenderGroupCd;
	}
	
	/**
	 * srhDeliveryConfを取得します。
	 * @return srhDeliveryConf
	 */
	public BigDecimal getSrhDeliveryConf() {
		return srhDeliveryConf;
	}

	/**
	 * srhDeliveryConfを設定します。
	 * @param srhDeliveryConf srhDeliveryConf
	 */
	public void setSrhDeliveryConf(final BigDecimal srhDeliveryConf) {
		this.srhDeliveryConf = srhDeliveryConf;
	}
	
	/**
	 * srhItemConfを取得します。
	 * @return srhItemConf
	 */
	public BigDecimal getSrhItemConf() {
		return srhItemConf;
	}

	/**
	 * srhItemConfを設定します。
	 * @param srhItemConf srhItemConf
	 */
	public void setSrhItemConf(final BigDecimal srhItemConf) {
		this.srhItemConf = srhItemConf;
	}
	
	/**
	 * 得意先グループコンボボックスを取得します。
	 * @return 得意先グループコンボボックス
	 */
	public List<ComboBoxItems> getVenderGroupCombo() {
		return venderGroupCombo;
	}

	/**
	 * 得意先グループコンボボックスを設定します。
	 * @param venderGroupCombo 得意先グループコンボボックス
	 */
	public void setVenderGroupCombo(final List<ComboBoxItems> venderGroupCombo) {
		this.venderGroupCombo = venderGroupCombo;
	}


	/**
	 * searchListを取得します。
	 * @return searchList
	 */
	public List<OrderVenderMasterList> getSearchList() {
		return searchList;
	}

	/**
	 * searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<OrderVenderMasterList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * conditionを取得します。
	 * @return condition
	 */
	public OrderVenderLinkListConditionForReport getCondition() {
		return condition;
	}

	/**
	 * conditionを設定します。
	 * @param condition condition
	 */
	public void setCondition(final OrderVenderLinkListConditionForReport condition) {
		this.condition = condition;
	}

	/**
	 * uploadFileを取得します。
	 * @return uploadFile
	 */
	public FormFile getUploadFile() {
		return uploadFile;
	}

	/**
	 * uploadFileを設定します。
	 * @param uploadFile uploadFile
	 */
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

}
