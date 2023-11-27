/*
 * Created on 2009/02/02
 *
 * $copyright$
 * 出荷指図
 */
package com.asahikaseieng.app.shipping;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.shipping.ShippingList;
import com.asahikaseieng.dao.nonentity.shipping.ShippingListPagerCondition;
import com.asahikaseieng.dao.nonentity.shippingforreport.ShippingListConditionForReport;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 出荷指図一覧 Formクラス.
 * @author tosco
 * 
 */
public final class ShippingListForm extends AbstractSearchForm {

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
	// 検索用.出荷指図
	//

	/** 検索入力：出荷番号 */
	private String srhShippingNo;

	/** 検索入力：出荷予定日FROM */
	private String srhScheduledShippingDateFrom;

	/** 検索入力：出荷予定日TO */
	private String srhScheduledShippingDateTo;

	/** 検索入力：受注番号FROM */
	private String srhOrderNoFrom;

	/** 検索入力：受注番号TO */
	private String srhOrderNoTo;

	/** 検索入力：取引先コード */
	private String srhVenderCd;

	/** 検索入力：取引先名称 */
	private String srhVenderName1;

	/** 検索入力：納入先コード */
	private String srhDeliveryCd;

	/** 検索入力：納入先名称 */
	private String srhDeliveryName1;

	/** 検索入力：出荷ステータス */
	private String srhShippingStatus;

	/** 検索入力：運送会社コード */
	private String srhCarryCd;

	/** 検索入力：ロケーションコード */
	private String srhLocationCd;

	/** 検索入力：ロケーション名称 */
	private String srhLocationName;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：品目名称 */
	private String srhItemName;

	/** 検索入力：他社コード1 */
	private String srhOtherCompanyCd1;

	/** 運送会社コンボボックス */
	private List<ComboBoxItems> carryCombo;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** 帳票Excel出力用検索条件 */
	private ShippingListConditionForReport reportCondition;

	//
	// インスタンスフィールド.出荷指図
	//

	/** リスト */
	private List<ShippingList> searchList;

	/**
	 * コンストラクタ.役職マスタ
	 */
	public ShippingListForm() {
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
		return ShippingListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		} else if ("fixCancel".equals(getOp())) {
			clearCheck();
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
			setSearchList(new ArrayList<ShippingList>());
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 初期化.出荷指図
	 */
	public void clear() {

		/** 検索リストのクリア */
		setSearchList(new ArrayList<ShippingList>());

		/** 検索入力：出荷伝票番号 */
		setSrhShippingNo(null);
		/** 検索入力：出荷予定日FROM */
		setSrhScheduledShippingDateFrom(null);
		/** 検索入力：出荷予定日TO */
		setSrhScheduledShippingDateTo(null);
		/** 検索入力：受注番号FROM */
		setSrhOrderNoFrom(null);
		/** 検索入力：受注番号TO */
		setSrhOrderNoTo(null);
		/** 検索入力：取引先コード */
		setSrhVenderCd(null);
		/** 検索入力：取引先名称 */
		setSrhVenderName1(null);
		/** 検索入力：納入先コード */
		setSrhDeliveryCd(null);
		/** 検索入力：納入先名称 */
		setSrhDeliveryName1(null);
		/** 検索入力：出荷ステータス */
		setSrhShippingStatus(ShippingConst.COMBO_ALL_VALUE);
		/** 検索入力：運送会社コード */
		setSrhCarryCd(ShippingConst.COMBO_ALL_VALUE);
		/** 検索入力：ロケーションコード */
		setSrhLocationCd(null);
		/** 検索入力：ロケーション名称 */
		setSrhLocationName(null);
		/** 検索入力：品目コード */
		setSrhItemCd(null);
		/** 検索入力：品目名称 */
		setSrhItemName(null);
		/** 検索入力：他社コード1 */
		setSrhOtherCompanyCd1(null);
		/** ダウンロードフラグを倒す */
		setExcelDownloadFlg(false);
		/** 帳票Excel検索条件 */
		setReportCondition(null);

	}

	/**
	 * 出荷指図：searchListを取得します。
	 * @return searchList
	 */
	public List<ShippingList> getSearchList() {
		return searchList;
	}

	/**
	 * 出荷指図： searchListを設定します。
	 * @param searchList searchList
	 */
	public void setSearchList(final List<ShippingList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.出荷指図
	//

	/**
	 * 検索入力：出荷番号取得.
	 * @return String 出荷番号
	 */
	public String getSrhShippingNo() {
		return this.srhShippingNo;
	}

	/**
	 * 検索入力：出荷番号設定.
	 * @param srhShippingNo 出荷番号
	 */
	public void setSrhShippingNo(final String srhShippingNo) {
		this.srhShippingNo = srhShippingNo;
	}

	/**
	 * 検索入力：出荷予定日FROM取得.
	 * @return String 出荷予定日FROM
	 */
	public String getSrhScheduledShippingDateFrom() {
		return this.srhScheduledShippingDateFrom;
	}

	/**
	 * 検索入力：出荷予定日FROM設定.
	 * @param srhScheduledShippingDateFrom 出荷予定日FROM
	 */
	public void setSrhScheduledShippingDateFrom(
			final String srhScheduledShippingDateFrom) {
		this.srhScheduledShippingDateFrom = srhScheduledShippingDateFrom;
	}

	/**
	 * 検索入力：出荷予定日TO取得.
	 * @return String 出荷予定日TO
	 */
	public String getSrhScheduledShippingDateTo() {
		return this.srhScheduledShippingDateTo;
	}

	/**
	 * 検索入力：出荷予定日TO設定.
	 * @param srhScheduledShippingDateTo 出荷予定日TO
	 */
	public void setSrhScheduledShippingDateTo(
			final String srhScheduledShippingDateTo) {
		this.srhScheduledShippingDateTo = srhScheduledShippingDateTo;
	}

	/**
	 * 検索入力：受注番号FROM取得.
	 * @return String 受注番号FROM
	 */
	public String getSrhOrderNoFrom() {
		return this.srhOrderNoFrom;
	}

	/**
	 * 検索入力：受注番号FROM設定.
	 * @param srhOrderNoFrom 受注番号FROM
	 */
	public void setSrhOrderNoFrom(final String srhOrderNoFrom) {
		this.srhOrderNoFrom = srhOrderNoFrom;
	}

	/**
	 * 検索入力：受注番号TO取得.
	 * @return String 受注番号TO
	 */
	public String getSrhOrderNoTo() {
		return this.srhOrderNoTo;
	}

	/**
	 * 検索入力：受注番号TO設定.
	 * @param srhOrderNoTo 受注番号TO
	 */
	public void setSrhOrderNoTo(final String srhOrderNoTo) {
		this.srhOrderNoTo = srhOrderNoTo;
	}

	/**
	 * 検索入力：取引先コード取得.
	 * @return String 取引先コード
	 */
	public String getSrhVenderCd() {
		return this.srhVenderCd;
	}

	/**
	 * 検索入力：取引先コード設定.
	 * @param srhVenderCd 取引先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * 検索入力：取引先名称取得.
	 * @return String 取引先名称
	 */
	public String getSrhVenderName1() {
		return this.srhVenderName1;
	}

	/**
	 * 検索入力：取引先名称設定.
	 * @param srhVenderName1 取引先名称
	 */
	public void setSrhVenderName1(final String srhVenderName1) {
		this.srhVenderName1 = srhVenderName1;
	}

	/**
	 * 検索入力：納入先コード取得.
	 * @return String 納入先コード
	 */
	public String getSrhDeliveryCd() {
		return this.srhDeliveryCd;
	}

	/**
	 * 検索入力：納入先コード設定.
	 * @param srhDeliveryCd 納入先コード
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = srhDeliveryCd;
	}

	/**
	 * 検索入力：納入先名称取得.
	 * @return String 納入先名称
	 */
	public String getSrhDeliveryName1() {
		return this.srhDeliveryName1;
	}

	/**
	 * 検索入力：納入先名称設定.
	 * @param srhDeliveryName1 納入先名称
	 */
	public void setSrhDeliveryName1(final String srhDeliveryName1) {
		this.srhDeliveryName1 = srhDeliveryName1;
	}

	/**
	 * 検索入力：出荷ステータス取得.
	 * @return String 出荷ステータス
	 */
	public String getSrhShippingStatus() {
		return this.srhShippingStatus;
	}

	/**
	 * 検索入力：出荷ステータス設定.
	 * @param srhShippingStatus 出荷ステータス
	 */
	public void setSrhShippingStatus(final String srhShippingStatus) {
		this.srhShippingStatus = srhShippingStatus;
	}

	/**
	 * 検索入力：運送会社コード取得.
	 * @return String 運送会社コード
	 */
	public String getSrhCarryCd() {
		return this.srhCarryCd;
	}

	/**
	 * 検索入力：運送会社コード設定.
	 * @param srhCarryCd 運送会社コード
	 */
	public void setSrhCarryCd(final String srhCarryCd) {
		this.srhCarryCd = srhCarryCd;
	}

	/**
	 * 検索入力：ロケーションコード取得.
	 * @return String ロケーションコード
	 */
	public String getSrhLocationCd() {
		return this.srhLocationCd;
	}

	/**
	 * 検索入力：ロケーションコード設定.
	 * @param srhLocationCd ロケーションコード
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = srhLocationCd;
	}

	/**
	 * 検索入力：ロケーション名称取得.
	 * @return String ロケーション名称
	 */
	public String getSrhLocationName() {
		return this.srhLocationName;
	}

	/**
	 * 検索入力：ロケーション名称設定.
	 * @param srhLocationName ロケーション名称
	 */
	public void setSrhLocationName(final String srhLocationName) {
		this.srhLocationName = srhLocationName;
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
	 * 検索入力：品目名称取得.
	 * @return String 品目名称
	 */
	public String getSrhItemName() {
		return this.srhItemName;
	}

	/**
	 * 検索入力：品目名称設定.
	 * @param srhItemName 品目名称
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * 検索入力：他社コード1取得.
	 * @return String 他社コード1
	 */
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 検索入力：他社コード1設定.
	 * @param srhOtherCompanyCd1 他社コード1
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * 運送会社コンボボックスを取得します。
	 * @return 運送会社コンボボックス
	 */
	public List<ComboBoxItems> getCarryCombo() {
		return carryCombo;
	}

	/**
	 * 運送会社コンボボックスを設定します。
	 * @param carryCombo 運送会社コンボボックス
	 */
	public void setCarryCombo(final List<ComboBoxItems> carryCombo) {
		this.carryCombo = carryCombo;
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
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getSearchList() != null) {
			for (ShippingList bean : getSearchList()) {
				bean.setCheckFlg(Boolean.FALSE);
			}
		}
	}

	/**
	 * 帳票Excel検索条件を取得します。
	 * @return excelDownloadFlg 帳票Excel検索条件
	 */
	public ShippingListConditionForReport getReportCondition() {
		return reportCondition;
	}

	/**
	 * 帳票Excel検索条件を設定します。
	 * @param reportCondition 帳票Excel検索条件
	 */
	public void setReportCondition(
			final ShippingListConditionForReport reportCondition) {
		this.reportCondition = reportCondition;
	}

	/**
	 * Excelダウンロードフラグを取得します。
	 * @return boolean Excelダウンロードフラグ
	 */
	public boolean isExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * Excelダウンロードフラグを設定します。
	 * @param excelDownloadFlg Excelダウンロードフラグ
	 */
	public void setExcelDownloadFlg(final boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
	}
}
