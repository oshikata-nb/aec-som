/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 売上詳細-共通抽象 Formクラス.
 * @author tosco
 */
public abstract class AbstractSalesDetailForm extends AbstractForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 変更フラグ * */
	private String dirtyFlg;
	
	/** 税コード変更フラグ */
	private String taxCdChangeFlg;

	//
	// インスタンスフィールド

	/** 新規用切替フラグ */
	private int insertFlg;

	/** 売上日付(文字列) */
	private String strSalesDate;

	/** 売上番号 */
	private String salesNo;

	/** 売上-取消 売上番号 */
	private String cancelSalesNo;

	/** 伝票発行済区分名称 */
	private String slipPublishCompName;

	/** 受注番号 */
	private String orderNo;

	/** 売上区分名称 */
	private String categoryName;

	/** 勘定年月(文字列) */
	private String strAccountYears;

	/** 前の勘定年月(文字列) （取消時にチェックするため） */
	private String strBeforeAccountYears;

	/** 前の売上日(文字列) （取消時にチェックするため） */
	private String strBeforeSalesDate;

	/** 売上区分(分類コード) */
	private String categoryDivision;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 得意先コード */
	private String venderCd;

	/** 得意先名称 */
	private String venderName1;

	/** 得意先略称 */
	private String venderShortedName;

	/** 売上数量(文字列) */
	private String strSalesQuantity;

	/** 売上単価(文字列) */
	private String strSalesUnitprice;

	/** 標準単価 */
	private BigDecimal standardUnitprice;

	/** 標準値引 */
	private BigDecimal standardDiscount;

	/** 特別値引 */
	private BigDecimal specialDiscount;

	/** 標準単価(文字列) */
	private String strStandardUnitprice;

	/** 標準値引(文字列) */
	private String strStandardDiscount;

	/** 特別値引(文字列) */
	private String strSpecialDiscount;

	/** 売上金額 */
	private String strSalesAmount;

	/** 仮単価FLG */
	private boolean tmpUnitpriceFlg;

	/** 納入先コード */
	private String deliveryCd;

	/** 納入先名称 */
	private String deliveryName1;

	/** 納入先略称 */
	private String searchKana;

	/** 納入先住所 */
	private String address;

	/** 納入先電話番号 */
	private String telNo;

	/** 会計部門借方部門コード */
	private String accountDebitSectionCd;

	/** 会計部門借方部門名称 */
	private String accountDebitSectionName;

	/** 借方科目コード */
	private String debitTitleCd;

	/** 借方科目名称 */
	private String debitTitleName;

	/** 会計部門貸方部門コード */
	private String accountCreditSectionCd;

	/** 会計部門貸方部門名称 */
	private String accountCreditSectionName;

	/** 貸方科目コード */
	private String creditTitleCd;

	/** 貸方科目名称 */
	private String creditTitleName;

	/** 入庫ロケーション */
	private String housingLocationCd;

	/** 包装指図番号 */
	private String packageDirectionNo;

	/** 製品ロット番号 */
	private String productLotno;

	/** 理由コード */
	private String ryCd;

	/** 理由内容 */
	private String ryDescription;

	/** 備考(摘要) */
	private String remark;

	/** 担当部署 */
	private String chargeOrganizationCd;

	/** 担当部署名称 */
	private String chargeOrganizationName;

	/** 預り品フラグ */
	private BigDecimal keepFlag;

	/** 入庫ロケーションコンボボックス */
	private List<ComboBoxItems> housingLocationCombo;

	/** 荷姿 */
	private String styleOfPacking;

	/** 単位区分 */
	private String unitDivision;

	/** 小数点桁数 */
	private String decimalPoint;

	/** 端数区分 */
	private String round;

	/** 小数点桁数(URTANKA) */
	private String decimalPointUrTanka;

	/** 端数区分(URTANKA) */
	private String roundUrTanka;

	/** 小数点桁数(URKINGAKU) */
	private String decimalPointUrKingaku;

	/** 端数区分(URKINGAKU) */
	private String roundUrKingaku;

	/** 帳合コード */
	private String balanceCd;

	/** フォーカスフィールドID */
	private String focusId;

	/** 品目チェック済みフラグ */
	private boolean itemCheckedFlag;

	/** 月次更新済みフラグ */
	private int monthlyUpdateDivision;

	/** 取消元データフラグ */
	private int cancelOriginFlag;

	/** 売上トランザクション更新日付 */
	private Timestamp updateDate;

	/** 売上トランザクション更新日付(取消元) */
	private Timestamp updateDateOrigin;

	/** 適用（売上伝票に出力しない備考) */
	private String summary;

	// 2014/1/28 新消費税対応 ->>
	private String strTaxAmount;

	private String strTaxRatio;

	private String strTaxDivision;

	/** 運送会社コンボボックス */
	private List<ComboBoxItems> taxCombo;
	// 2014/1/28 新消費税対応 <--	
	
	//20190821 軽減税率対応
	/** 消費税 */
	private String taxCd;
	private String[] taxLabels;
	private String[] taxValues;
	private String[] taxKeys;
	private String taxRatio;

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		} else if ("insert".equals(getOp())) {
			clearCheck();
		} else if ("update".equals(getOp())) {
			clearCheck();
		}
	}

	/**
	 * クリア処理.
	 */
	protected void clear() {
		/** 売上日付(文字列) */
		setStrSalesDate(null);
		/** 売上番号 */
		setSalesNo(null);
		/** 売上番号 */
		setCancelSalesNo(null);
		/** 伝票発行済区分名称 */
		setSlipPublishCompName(null);
		/** 受注番号 */
		setOrderNo(null);
		/** 売上区分名称 */
		setCategoryName(null);
		/** 勘定年月(文字列) */
		setStrAccountYears(null);
		/** 勘定年月(文字列) */
		setStrBeforeAccountYears(null);
		/** 売上区分(分類コード) */
		setCategoryDivision(null);
		/** 品目コード */
		setItemCd(null);
		/** 品目名称 */
		setItemName(null);
		/** 他社コード1 */
		setOtherCompanyCd1(null);
		/** 得意先コード */
		setVenderCd(null);
		/** 得意先名称 */
		setVenderName1(null);
		// 得意先略称
		setVenderShortedName(null);
		/** 売上数量(文字列) */
		setStrSalesQuantity(null);
		/** 売上単価(文字列) */
		setStrSalesUnitprice(null);
		/** 標準単価 */
		setStandardUnitprice(BigDecimal.ZERO);
		/** 標準値引 */
		setStandardDiscount(BigDecimal.ZERO);
		/** 特別値引 */
		setSpecialDiscount(BigDecimal.ZERO);
		/** 標準単価(文字列) */
		setStrStandardUnitprice(null);
		/** 標準値引(文字列) */
		setStrStandardDiscount(null);
		/** 特別値引(文字列) */
		setStrSpecialDiscount(null);
		/** 売上金額 */
		setStrSalesAmount(null);
		/** 仮単価FLG */
		setTmpUnitpriceFlg(false);
		/** 納入先コード */
		setDeliveryCd(null);
		/** 納入先名称 */
		setDeliveryName1(null);
		// 納入先略称
		setSearchKana(null);
		/** 納入先住所 */
		setAddress(null);
		/** 納入先電話番号 */
		setTelNo(null);
		/** 会計部門借方部門コード */
		setAccountDebitSectionCd(null);
		/** 会計部門借方部門名称 */
		setAccountDebitSectionName(null);
		/** 借方科目コード */
		setDebitTitleCd(null);
		/** 借方科目名称 */
		setDebitTitleName(null);
		/** 会計部門貸方部門コード */
		setAccountCreditSectionCd(null);
		/** 会計部門貸方部門名称 */
		setAccountCreditSectionName(null);
		/** 貸方科目コード */
		setCreditTitleCd(null);
		/** 貸方科目名称 */
		setCreditTitleName(null);
		/** 入庫ロケーション */
		setHousingLocationCd(null);
		/** 包装指図番号 */
		setPackageDirectionNo(null);
		/** 製品ロット番号 */
		setProductLotno(null);
		/** 理由コード */
		setRyCd(null);
		/** 理由内容 */
		setRyDescription(null);
		/** 備考(摘要) */
		setRemark(null);
		/** 担当部署 */
		setChargeOrganizationCd(null);
		/** 担当部署名称 */
		setChargeOrganizationName(null);
		/** 預り品フラグ */
		setKeepFlag(null);
		/** 荷姿 */
		setStyleOfPacking(null);
		/** 単位区分 */
		setUnitDivision(null);
		/** 小数点桁数 */
		setDecimalPoint(null);
		/** 端数区分 */
		setRound(null);
		/** 小数点桁数(URTANKA) */
		setDecimalPointUrTanka(null);
		/** 端数区分(URTANKA) */
		setRoundUrTanka(null);
		/** 小数点桁数(URKINGAKU) */
		setDecimalPointUrKingaku(null);
		/** 端数区分(URKINGAKU) */
		setRoundUrKingaku(null);
		/** 帳合コード */
		setBalanceCd(null);
		/** フォーカスフィールドID */
		setFocusId(null);
		/** 品目チェック済みフラグ */
		setItemCheckedFlag(false);
		/** 月次更新済みフラグ */
		setMonthlyUpdateDivision(0);
		/** 取消元データフラグ */
		setCancelOriginFlag(0);
		/** 売上トランザクション更新日付 */
		setUpdateDate(null);
		/** 売上トランザクション更新日付(取消元) */
		setUpdateDateOrigin(null);
		/** 売上トランザクション適用 */
		setSummary(null);
		/** 前の売上日 */
		setStrBeforeSalesDate(null);

		// 2014/1/28 新消費税対応 ->>
		/** 消費税額 */
		setStrTaxAmount(null);
		/** 消費税率 */
		setStrTaxRatio(null);

		/** 消費税区分 */
		setStrTaxDivision(null);
		// 2014/1/28 新消費税対応 <--
		
		/** 消費税CD */
		setTaxCd(null);
		/** 消費税率LABLE */
		setTaxLabels(null);
		/** 消費税率名称 */
		setTaxValues(null);
		/** 消費税率CD */
		setTaxKeys(null);
		/** 消費税率 */
		setTaxRatio("0");
		/** 税コード変更フラグ */
		setTaxCdChangeFlg("false");
		
	}

	//
	// インスタンスメソッド
	//

	/**
	 * 新規用切替フラグを取得します。
	 * @return int 新規用切替フラグ
	 */
	public int getInsertFlg() {
		return insertFlg;
	}

	/**
	 * 新規用切替フラグを設定します。
	 * @param insertFlg 新規用切替フラグ
	 */
	public void setInsertFlg(final int insertFlg) {
		this.insertFlg = insertFlg;
	}

	/**
	 * 売上日付(文字列)取得.
	 * @return String 売上日付(文字列)
	 */
	public String getStrSalesDate() {
		return this.strSalesDate;
	}

	/**
	 * 売上日付(文字列)設定.
	 * @param strSalesDate 売上日付(文字列)
	 */
	public void setStrSalesDate(final String strSalesDate) {
		this.strSalesDate = strSalesDate;
	}

	/**
	 * 前の売上日付(文字列)取得.
	 * @return String 前の売上日付(文字列)
	 */
	public String getStrBeforeSalesDate() {
		return strBeforeSalesDate;
	}

	/**
	 * 前の売上日付(文字列)設定.
	 * @param strBeforeSalesDate 前の売上日付(文字列)
	 */
	public void setStrBeforeSalesDate(final String strBeforeSalesDate) {
		this.strBeforeSalesDate = strBeforeSalesDate;
	}

	/**
	 * 売上番号取得.
	 * @return String 売上番号
	 */
	public String getSalesNo() {
		return this.salesNo;
	}

	/**
	 * 売上番号設定.
	 * @param salesNo 売上番号
	 */
	public void setSalesNo(final String salesNo) {
		this.salesNo = salesNo;
	}

	/**
	 * 売上-取消 売上番号取得.
	 * @return String 売上-取消 売上番号
	 */
	public String getCancelSalesNo() {
		return this.cancelSalesNo;
	}

	/**
	 * 売上-取消 売上番号設定.
	 * @param cancelSalesNo 売上-取消 売上番号
	 */
	public void setCancelSalesNo(final String cancelSalesNo) {
		this.cancelSalesNo = cancelSalesNo;
	}

	/**
	 * 伝票発行済区分名称取得.
	 * @return String 伝票発行済区分名称
	 */
	public String getSlipPublishCompName() {
		return this.slipPublishCompName;
	}

	/**
	 * 伝票発行済区分名称設定.
	 * @param slipPublishCompName 伝票発行済区分名称
	 */
	public void setSlipPublishCompName(final String slipPublishCompName) {
		this.slipPublishCompName = slipPublishCompName;
	}

	/**
	 * 受注番号取得.
	 * @return String 受注番号
	 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * 受注番号設定.
	 * @param orderNo 受注番号
	 */
	public void setOrderNo(final String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 売上区分名称取得.
	 * @return String 売上区分名称
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * 売上区分名称設定.
	 * @param categoryName 売上区分名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 勘定年月(文字列)取得.
	 * @return String 勘定年月(文字列)
	 */
	public String getStrAccountYears() {
		return this.strAccountYears;
	}

	/**
	 * 勘定年月(文字列)設定.
	 * @param strAccountYears 勘定年月(文字列)
	 */
	public void setStrAccountYears(final String strAccountYears) {
		this.strAccountYears = strAccountYears;
	}

	/**
	 * 勘定年月(文字列)取得.
	 * @return String 勘定年月(文字列)
	 */
	public String getStrBeforeAccountYears() {
		return this.strBeforeAccountYears;
	}

	/**
	 * 勘定年月(文字列)設定.
	 * @param strBeforeAccountYears 勘定年月(文字列)
	 */
	public void setStrBeforeAccountYears(final String strBeforeAccountYears) {
		this.strBeforeAccountYears = strBeforeAccountYears;
	}

	/**
	 * 売上区分(分類コード)取得.
	 * @return String 売上区分(分類コード)
	 */
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * 売上区分(分類コード)設定.
	 * @param categoryDivision 売上区分(分類コード)
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 品目コード取得.
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定.
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称取得.
	 * @return String 品目名称
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 品目名称設定.
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 他社コード1取得
	 * @return String 他社コード1
	 */
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * 他社コード1設定
	 * @param otherCompanyCd1 他社コード1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 得意先コード取得.
	 * @return String 得意先コード
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 得意先コード設定.
	 * @param venderCd 得意先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 得意先名称取得.
	 * @return String 得意先名称
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * 得意先名称設定.
	 * @param venderName1 得意先名称
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderShortedNameを取得します。
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return venderShortedName;
	}

	/**
	 * venderShortedNameを設定します。
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}

	/**
	 * 売上数量(文字列) 取得.
	 * @return String 売上数量(文字列)
	 */
	public String getStrSalesQuantity() {
		return this.strSalesQuantity;
	}

	/**
	 * 売上数量(文字列) 設定.
	 * @param strSalesQuantity 売上数量(文字列)
	 */
	public void setStrSalesQuantity(final String strSalesQuantity) {
		this.strSalesQuantity = strSalesQuantity;
	}

	/**
	 * 売上単価取得.
	 * @return String 売上単価
	 */
	public String getStrSalesUnitprice() {
		return this.strSalesUnitprice;
	}

	/**
	 * 売上単価設定.
	 * @param strSalesUnitprice 売上単価
	 */
	public void setStrSalesUnitprice(final String strSalesUnitprice) {
		this.strSalesUnitprice = strSalesUnitprice;
	}

	/**
	 * 標準単価 取得.
	 * @return BigDecimal 標準単価
	 */
	public BigDecimal getStandardUnitprice() {
		return this.standardUnitprice;
	}

	/**
	 * 標準単価 設定.
	 * @param standardUnitprice 標準単価
	 */
	public void setStandardUnitprice(final BigDecimal standardUnitprice) {
		this.standardUnitprice = standardUnitprice;
	}

	/**
	 * 標準値引取得.
	 * @return BigDecimal 標準値引
	 */
	public BigDecimal getStandardDiscount() {
		return this.standardDiscount;
	}

	/**
	 * 標準値引 設定.
	 * @param standardDiscount 標準値引
	 */
	public void setStandardDiscount(final BigDecimal standardDiscount) {
		this.standardDiscount = standardDiscount;
	}

	/**
	 * 特別値引 取得.
	 * @return BigDecimal 特別値引
	 */
	public BigDecimal getSpecialDiscount() {
		return this.specialDiscount;
	}

	/**
	 * 特別値引 設定.
	 * @param specialDiscount 特別値引
	 */
	public void setSpecialDiscount(final BigDecimal specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	/**
	 * 標準単価(文字列) 取得.
	 * @return String 標準単価(文字列)
	 */
	public String getStrStandardUnitprice() {
		return this.strStandardUnitprice;
	}

	/**
	 * 標準単価(文字列) 設定.
	 * @param strStandardUnitprice 標準単価(文字列)
	 */
	public void setStrStandardUnitprice(final String strStandardUnitprice) {
		this.strStandardUnitprice = strStandardUnitprice;
	}

	/**
	 * 標準値引(文字列) 取得.
	 * @return String 標準値引(文字列)
	 */
	public String getStrStandardDiscount() {
		return this.strStandardDiscount;
	}

	/**
	 * 標準値引(文字列) 設定.
	 * @param strStandardDiscount 標準値引(文字列)
	 */
	public void setStrStandardDiscount(final String strStandardDiscount) {
		this.strStandardDiscount = strStandardDiscount;
	}

	/**
	 * 特別値引(文字列) 取得.
	 * @return String 特別値引(文字列)
	 */
	public String getStrSpecialDiscount() {
		return this.strSpecialDiscount;
	}

	/**
	 * 特別値引(文字列) 設定.
	 * @param strSpecialDiscount 特別値引(文字列)
	 */
	public void setStrSpecialDiscount(final String strSpecialDiscount) {
		this.strSpecialDiscount = strSpecialDiscount;
	}

	/**
	 * 売上金額(文字列) 取得.
	 * @return String 売上金額(文字列)
	 */
	public String getStrSalesAmount() {
		return this.strSalesAmount;
	}

	/**
	 * 売上金額(文字列) 設定.
	 * @param strSalesAmount 売上金額(文字列)
	 */
	public void setStrSalesAmount(final String strSalesAmount) {
		this.strSalesAmount = strSalesAmount;
	}

	/**
	 * 仮単価FLG取得.
	 * @return String 仮単価FLG
	 */
	public boolean getTmpUnitpriceFlg() {
		return this.tmpUnitpriceFlg;
	}

	/**
	 * 仮単価FLG設定.
	 * @param tmpUnitpriceFlg 仮単価FLG
	 */
	public void setTmpUnitpriceFlg(final boolean tmpUnitpriceFlg) {
		this.tmpUnitpriceFlg = tmpUnitpriceFlg;
	}

	/**
	 * 納入先コード取得.
	 * @return String 納入先コード
	 */
	public String getDeliveryCd() {
		return this.deliveryCd;
	}

	/**
	 * 納入先コード設定.
	 * @param deliveryCd 納入先コード
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * 納入先名称取得.
	 * @return String 納入先名称
	 */
	public String getDeliveryName1() {
		return this.deliveryName1;
	}

	/**
	 * 納入先名称設定.
	 * @param deliveryName1 納入先名称
	 */
	public void setDeliveryName1(final String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * searchKana取得
	 * @return searchKana searchKana
	 */
	public String getSearchKana() {
		return searchKana;
	}

	/**
	 * searchKana設定
	 * @param searchKana searchKana
	 */
	public void setSearchKana(final String searchKana) {
		this.searchKana = searchKana;
	}

	/**
	 * 納入先住所取得.
	 * @return String 納入先住所
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * 納入先住所設定.
	 * @param address 納入先住所
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * 納入先電話番号取得.
	 * @return String 納入先電話番号
	 */
	public String getTelNo() {
		return this.telNo;
	}

	/**
	 * 納入先電話番号設定.
	 * @param telNo 納入先電話番号
	 */
	public void setTelNo(final String telNo) {
		this.telNo = telNo;
	}

	/**
	 * 会計部門借方部門コード取得.
	 * @return String 会計部門借方部門コード
	 */
	public String getAccountDebitSectionCd() {
		return this.accountDebitSectionCd;
	}

	/**
	 * 会計部門借方部門コード設定.
	 * @param accountDebitSectionCd 会計部門借方部門コード
	 */
	public void setAccountDebitSectionCd(final String accountDebitSectionCd) {
		this.accountDebitSectionCd = accountDebitSectionCd;
	}

	/**
	 * 会計部門借方部門名称取得.
	 * @return String 会計部門借方部門名称
	 */
	public String getAccountDebitSectionName() {
		return this.accountDebitSectionName;
	}

	/**
	 * 会計部門借方部門名称設定.
	 * @param accountDebitSectionName 会計部門借方部門名称
	 */
	public void setAccountDebitSectionName(final String accountDebitSectionName) {
		this.accountDebitSectionName = accountDebitSectionName;
	}

	/**
	 * 借方科目コード取得.
	 * @return String 借方科目コード
	 */
	public String getDebitTitleCd() {
		return this.debitTitleCd;
	}

	/**
	 * 借方科目コード設定.
	 * @param debitTitleCd 借方科目コード
	 */
	public void setDebitTitleCd(final String debitTitleCd) {
		this.debitTitleCd = debitTitleCd;
	}

	/**
	 * 借方科目名称取得.
	 * @return String 借方科目名称
	 */
	public String getDebitTitleName() {
		return this.debitTitleName;
	}

	/**
	 * 借方科目名称設定.
	 * @param debitTitleName 借方科目名称
	 */
	public void setDebitTitleName(final String debitTitleName) {
		this.debitTitleName = debitTitleName;
	}

	/**
	 * 会計部門貸方部門コード取得.
	 * @return String 会計部門部門貸方コード
	 */
	public String getAccountCreditSectionCd() {
		return this.accountCreditSectionCd;
	}

	/**
	 * 会計部門貸方部門コード設定.
	 * @param accountCreditSectionCd 会計部門貸方部門コード
	 */
	public void setAccountCreditSectionCd(final String accountCreditSectionCd) {
		this.accountCreditSectionCd = accountCreditSectionCd;
	}

	/**
	 * 会計部門貸方部門名称取得.
	 * @return String 会計部門部門貸方名称
	 */
	public String getAccountCreditSectionName() {
		return this.accountCreditSectionName;
	}

	/**
	 * 会計部門貸方部門名称設定.
	 * @param accountCreditSectionName 会計部門貸方部門名称
	 */
	public void setAccountCreditSectionName(
			final String accountCreditSectionName) {
		this.accountCreditSectionName = accountCreditSectionName;
	}

	/**
	 * 貸方科目コード取得.
	 * @return String 貸方科目コード
	 */
	public String getCreditTitleCd() {
		return this.creditTitleCd;
	}

	/**
	 * 貸方科目コード設定.
	 * @param creditTitleCd 貸方科目コード
	 */
	public void setCreditTitleCd(final String creditTitleCd) {
		this.creditTitleCd = creditTitleCd;
	}

	/**
	 * 貸方科目名称取得.
	 * @return String 貸方科目名称
	 */
	public String getCreditTitleName() {
		return this.creditTitleName;
	}

	/**
	 * 貸方科目名称設定.
	 * @param creditTitleName 貸方科目名称
	 */
	public void setCreditTitleName(final String creditTitleName) {
		this.creditTitleName = creditTitleName;
	}

	/**
	 * 入庫ロケーション取得.
	 * @return String 入庫ロケーション
	 */
	public String getHousingLocationCd() {
		return this.housingLocationCd;
	}

	/**
	 * 入庫ロケーション設定.
	 * @param housingLocationCd 入庫ロケーション
	 */
	public void setHousingLocationCd(final String housingLocationCd) {
		this.housingLocationCd = housingLocationCd;
	}

	/**
	 * 包装指図番号取得.
	 * @return String 包装指図番号
	 */
	public String getPackageDirectionNo() {
		return this.packageDirectionNo;
	}

	/**
	 * 包装指図番号設定.
	 * @param packageDirectionNo 包装指図番号
	 */
	public void setPackageDirectionNo(final String packageDirectionNo) {
		this.packageDirectionNo = packageDirectionNo;
	}

	/**
	 * 製品ロット番号取得.
	 * @return String 製品ロット番号
	 */
	public String getProductLotno() {
		return this.productLotno;
	}

	/**
	 * 製品ロット番号設定.
	 * @param productLotno 製品ロット番号
	 */
	public void setProductLotno(final String productLotno) {
		this.productLotno = productLotno;
	}

	/**
	 * 理由コード取得.
	 * @return String 理由コード
	 */
	public String getRyCd() {
		return this.ryCd;
	}

	/**
	 * 理由コード設定.
	 * @param ryCd 理由コード
	 */
	public void setRyCd(final String ryCd) {
		this.ryCd = ryCd;
	}

	/**
	 * 理由内容取得.
	 * @return String 理由内容
	 */
	public String getRyDescription() {
		return this.ryDescription;
	}

	/**
	 * 理由内容設定.
	 * @param ryDescription 理由内容
	 */
	public void setRyDescription(final String ryDescription) {
		this.ryDescription = ryDescription;
	}

	/**
	 * 備考(摘要)取得.
	 * @return String 備考(摘要)
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 備考(摘要)設定.
	 * @param remark 備考(摘要)
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * 担当部署取得.
	 * @return String 担当部署
	 */
	public String getChargeOrganizationCd() {
		return this.chargeOrganizationCd;
	}

	/**
	 * 担当部署設定.
	 * @param chargeOrganizationCd 担当部署
	 */
	public void setChargeOrganizationCd(final String chargeOrganizationCd) {
		this.chargeOrganizationCd = chargeOrganizationCd;
	}

	/**
	 * 担当部署名称取得.
	 * @return String 担当部署名称
	 */
	public String getChargeOrganizationName() {
		return this.chargeOrganizationName;
	}

	/**
	 * 担当部署名称設定.
	 * @param chargeOrganizationName 担当部署名称
	 */
	public void setChargeOrganizationName(final String chargeOrganizationName) {
		this.chargeOrganizationName = chargeOrganizationName;
	}

	/**
	 * 預り品フラグ取得.
	 * @return BigDecimal 預り品フラグ
	 */
	public BigDecimal getKeepFlag() {
		return this.keepFlag;
	}

	/**
	 * 預り品フラグ設定.
	 * @param keepFlag 預り品フラグ
	 */
	public void setKeepFlag(final BigDecimal keepFlag) {
		this.keepFlag = keepFlag;
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
	 * 入庫ロケーションコンボボックスを取得します。
	 * @return 入庫ロケーションコンボボックス
	 */
	public List<ComboBoxItems> getHousingLocationCombo() {
		return housingLocationCombo;
	}

	/**
	 * 入庫ロケーションコンボボックスを設定します。
	 * @param housingLocationCombo 入庫ロケーションコンボボックス
	 */
	public void setHousingLocationCombo(
			final List<ComboBoxItems> housingLocationCombo) {
		this.housingLocationCombo = housingLocationCombo;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 単位区分
	 * @return String 単位区分
	 */
	public String getUnitDivision() {
		return this.unitDivision;
	}

	/**
	 * 単位区分
	 * @param unitDivision 単位区分
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}

	/**
	 * 小数点桁数取得
	 * @return String 小数点桁数
	 */
	public String getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * 小数点桁数設定
	 * @param decimalPoint 小数点桁数
	 */
	public void setDecimalPoint(final String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	/**
	 * 端数区分取得
	 * @return String 端数区分
	 */
	public String getRound() {
		return round;
	}

	/**
	 * 端数区分設定
	 * @param round 端数区分
	 */
	public void setRound(final String round) {
		this.round = round;
	}

	/**
	 * 単位区分(URTANKA)
	 * @return String 単位区分(URTANKA)
	 */
	public String getUnitDivisionUrTanka() {
		return SalesConst.UNIT_DIVISION_URTANKA;
	}

	/**
	 * 小数点桁数(URTANKA)取得
	 * @return String 小数点桁数(URTANKA)
	 */
	public String getDecimalPointUrTanka() {
		return decimalPointUrTanka;
	}

	/**
	 * 小数点桁数(URTANKA)設定
	 * @param decimalPointUrTanka 小数点桁数(URTANKA)
	 */
	public void setDecimalPointUrTanka(final String decimalPointUrTanka) {
		this.decimalPointUrTanka = decimalPointUrTanka;
	}

	/**
	 * 端数区分(URTANKA)取得
	 * @return String 端数区分(URTANKA)
	 */
	public String getRoundUrTanka() {
		return roundUrTanka;
	}

	/**
	 * 端数区分(URTANKA)設定
	 * @param roundUrTanka 端数区分(URTANKA)
	 */
	public void setRoundUrTanka(final String roundUrTanka) {
		this.roundUrTanka = roundUrTanka;
	}

	/**
	 * 単位区分(URKINGAKU)
	 * @return String 単位区分(URKINGAKU)
	 */
	public String getUnitDivisionUrKingaku() {
		return SalesConst.UNIT_DIVISION_URKINGAKU;
	}

	/**
	 * 小数点桁数(URKINGAKU)取得
	 * @return String 小数点桁数(URKINGAKU)
	 */
	public String getDecimalPointUrKingaku() {
		return decimalPointUrKingaku;
	}

	/**
	 * 小数点桁数(URKINGAKU)設定
	 * @param decimalPointUrKingaku 小数点桁数(URKINGAKU)
	 */
	public void setDecimalPointUrKingaku(final String decimalPointUrKingaku) {
		this.decimalPointUrKingaku = decimalPointUrKingaku;
	}

	/**
	 * 端数区分(URKINGAKU)取得
	 * @return String 端数区分(URKINGAKU)
	 */
	public String getRoundUrKingaku() {
		return roundUrKingaku;
	}

	/**
	 * 端数区分(URKINGAKU)設定
	 * @param roundUrKingaku 端数区分(URKINGAKU)
	 */
	public void setRoundUrKingaku(final String roundUrKingaku) {
		this.roundUrKingaku = roundUrKingaku;
	}

	/**
	 * 帳合コード取得
	 * @return String 帳合コード
	 */
	public String getBalanceCd() {
		return this.balanceCd;
	}

	/**
	 * 帳合コード設定
	 * @param balanceCd 帳合コード
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * フォーカスフィールドID取得.
	 * @return String フォーカスフィールドID
	 */
	public String getFocusId() {
		return this.focusId;
	}

	/**
	 * フォーカスフィールドID設定.
	 * @param focusId フォーカスフィールドID
	 */
	public void setFocusId(final String focusId) {
		this.focusId = focusId;
	}

	/**
	 * 品目チェック済みフラグ取得.
	 * @return String 品目チェック済みフラグ
	 */
	public boolean getItemCheckedFlag() {
		return this.itemCheckedFlag;
	}

	/**
	 * 品目チェック済みフラグ設定.
	 * @param itemCheckedFlag 品目チェック済みフラグ
	 */
	public void setItemCheckedFlag(final boolean itemCheckedFlag) {
		this.itemCheckedFlag = itemCheckedFlag;
	}

	/**
	 * 月次更新済みフラグ取得.
	 * @return String 月次更新済みフラグ
	 */
	public int getMonthlyUpdateDivision() {
		return this.monthlyUpdateDivision;
	}

	/**
	 * 月次更新済みフラグ設定.
	 * @param monthlyUpdateDivision 月次更新済みフラグ
	 */
	public void setMonthlyUpdateDivision(final int monthlyUpdateDivision) {
		this.monthlyUpdateDivision = monthlyUpdateDivision;
	}

	/**
	 * 取消元データフラグ取得.
	 * @return String 取消元データフラグ
	 */
	public int getCancelOriginFlag() {
		return this.cancelOriginFlag;
	}

	/**
	 * 取消元データフラグ設定.
	 * @param cancelOriginFlag 取消元データフラグ
	 */
	public void setCancelOriginFlag(final int cancelOriginFlag) {
		this.cancelOriginFlag = cancelOriginFlag;
	}

	/**
	 * 売上トランザクション更新日付取得.
	 * @return Timestamp 売上トランザクション更新日付
	 */
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 売上トランザクション更新日付設定.
	 * @param updateDate 売上トランザクション更新日付
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 売上トランザクション更新日付(取消元)取得.
	 * @return Timestamp 売上トランザクション更新日付(取消元)
	 */
	public Timestamp getUpdateDateOrigin() {
		return this.updateDateOrigin;
	}

	/**
	 * 売上トランザクション更新日付(取消元)設定.
	 * @param updateDateOrigin 売上トランザクション更新日付(取消元)
	 */
	public void setUpdateDateOrigin(final Timestamp updateDateOrigin) {
		this.updateDateOrigin = updateDateOrigin;
	}

	/**
	 * 売上トランザクション適用取得.
	 * @return String 売上トランザクション適用
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * 売上トランザクション適用設定.
	 * @param summary 売上トランザクション適用
	 */
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	/**
	 * 品目数量の検証
	 * @param inputValue 入力した値
	 * @param unitDivision 単位区分
	 * @param venderCd 取引先コード
	 * @param name 項目名
	 * @param check 数値桁数チェックロジッククラス
	 * @param errors 検証エラー内容
	 * @return boolean チェック結果 true:OK false:エラー
	 */
	protected boolean validateSalesQuantity(final String inputValue,
			final String unitDivision, final String venderCd,
			final String name, final CheckDigitUtilsLogic check,
			final ActionErrors errors) {
		boolean res = true;
		String value = StringUtils.replace(inputValue, ",", "");
		// 数値チェック
		if (!StringUtils.isEmpty(inputValue)) {
			ActionMessage message = check.checkDigitMessage(unitDivision,
				SalesConst.VENDER_DIVISION_TS, venderCd, value, name);
			if (message != null) {
				if ("errors.rang".equals(message.getKey())) {
					// 範囲エラーの場合、売上固有のメッセージにする
					NumberChkDisitDetail checkDetail = check.getCheckDigit(
						unitDivision, SalesConst.VENDER_DIVISION_TS, venderCd);
					message = new ActionMessage("errors.sales.rang.quantity",
							name, check.format(unitDivision,
								SalesConst.VENDER_DIVISION_TS, venderCd,
								SalesConst.QUANTITY_MIN), checkDetail
									.getUpperLimit().toString());
				}
				errors.add("", message);
				res = false;
			} else {
				// 0より大きい値となっているかチェック
				BigDecimal val = AecNumberUtils.convertBigDecimal(value);
				if (val.compareTo(SalesConst.QUANTITY_MIN) <= 0) {
					NumberChkDisitDetail checkDetail = check.getCheckDigit(
						unitDivision, SalesConst.VENDER_DIVISION_TS, venderCd);
					errors.add("", new ActionMessage(
							"errors.sales.rang.quantity", name, check.format(
								unitDivision, SalesConst.VENDER_DIVISION_TS,
								venderCd, SalesConst.QUANTITY_MIN), checkDetail
									.getUpperLimit().toString()));
					res = false;
				}
			}
		}
		return res;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		setTmpUnitpriceFlg(Boolean.FALSE);
	}

	/**
	 * StringからBigDecimalへ型変換を行う
	 * @param strVal String値
	 * @return BigDecimal BigDecimal型に変換した値
	 */
	protected BigDecimal convertBigDecimal(final String strVal) {
		BigDecimal val = AecNumberUtils.convertNullToZero(AecNumberUtils
				.convertBigDecimal(strVal));
		return val;
	}

	/**
	 * 運送会社コンボボックスを取得します。
	 * @return carryCombo
	 */
	public List<ComboBoxItems> getTaxCombo() {
		return taxCombo;
	}

	/**
	 * 運送会社コンボボックスを設定します。
	 * @param carryCombo 運送会社コンボボックス
	 */
	public void setTaxCombo(final List<ComboBoxItems> carryCombo) {
		this.taxCombo = carryCombo;
	}
	/**
	 * 売上トランザクション消費税額取得.
	 * @return String 売上トランザクション消費税額
	 */
	public String getStrTaxAmount() {
		return strTaxAmount;
	}

	/**
	 * 売上トランザクション消費税額設定.
	 * @param strTaxAmount 売上トランザクション消費税額
	 */
	public void setStrTaxAmount(final String strTaxAmount) {
		this.strTaxAmount = strTaxAmount;
	}

	/**
	 * 売上トランザクション売上消費税区分取得.
	 * @return String 売上トランザクション売上消費税区分
	 */
	public String getStrTaxDivision() {
		return strTaxDivision;
	}

	/**
	 * 売上トランザクション売上消費税区分設定.
	 * @param strTaxDivision 売上消費税区分
	 */
	public void setStrTaxDivision(final String strTaxDivision) {
		this.strTaxDivision = strTaxDivision;
	}

	/**
	 * 売上トランザクション消費税率取得.
	 * @return String 消費税率
	 */
	public String getStrTaxRatio() {
		return strTaxRatio;
	}

	/**
	 * 売上トランザクション消費税率設定.
	 * @param strTaxRatio 消費税率
	 */
	public void setStrTaxRatio(final String strTaxRatio) {
		this.strTaxRatio = strTaxRatio;
	}

	/**
	 * taxCdを取得します。
	 * @return taxCd
	 */
	public String getTaxCd() {
		return taxCd;
	}

	/**
	 * taxCdを設定します。
	 * @param taxCd taxCd
	 */
	public void setTaxCd(String taxCd) {
		this.taxCd = taxCd;
	}

	/**
	 * taxLabelsを取得します。
	 * @return taxLabels
	 */
	public String[] getTaxLabels() {
		return taxLabels;
	}

	/**
	 * taxLabelsを設定します。
	 * @param taxLabels taxLabels
	 */
	public void setTaxLabels(String[] taxLabels) {
		this.taxLabels = taxLabels;
	}

	/**
	 * taxValuesを取得します。
	 * @return taxValues
	 */
	public String[] getTaxValues() {
		return taxValues;
	}

	/**
	 * taxValuesを設定します。
	 * @param taxValues taxValues
	 */
	public void setTaxValues(String[] taxValues) {
		this.taxValues = taxValues;
	}

	/**
	 * taxRatioを取得します。
	 * @return taxRatio
	 */
	public String getTaxRatio() {
		return taxRatio;
	}

	/**
	 * taxRatioを設定します。
	 * @param taxRatio taxRatio
	 */
	public void setTaxRatio(String taxRatio) {
		this.taxRatio = taxRatio;
	}

	/**
	 * taxKeysを取得します。
	 * @return taxKeys
	 */
	public String[] getTaxKeys() {
		return taxKeys;
	}

	/**
	 * taxKeysを設定します。
	 * @param taxKeys taxKeys
	 */
	public void setTaxKeys(String[] taxKeys) {
		this.taxKeys = taxKeys;
	}

	/**
	 * taxCdChangeFlgを取得します。
	 * @return taxCdChangeFlg
	 */
	public String getTaxCdChangeFlg() {
		return taxCdChangeFlg;
	}

	/**
	 * taxCdChangeFlgを設定します。
	 * @param taxCdChangeFlg taxCdChangeFlg
	 */
	public void setTaxCdChangeFlg(String taxCdChangeFlg) {
		this.taxCdChangeFlg = taxCdChangeFlg;
	}
}
