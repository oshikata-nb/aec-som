/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.buying;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.buying.BuyingDetail;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 仕入詳細 Formクラス.
 * @author toscoj
 */
public final class BuyingDetailForm extends AbstractForm {

	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/** 新規用切り替えフラグ */
	private int insertFlg;

	/** 変更フラグ */
	private String dirtyFlg;	
	
	/** 税コード変更フラグ */
	private String taxCdChangeFlg;


	//
	// インスタンスフィールド
	//

	/** 購買NO */
	private String purchaseNo;

	/** 発注番号 */
	private String buySubcontractOrderNo;

	/** 担当部署 */
	private String chargeOrganizationCd;

	/** 部署コード */
	private String organizationCd;

	/** 担当者コード */
	private String tantoCd;

	/** 仕入先コード */
	private String venderCd;

	/** 品目コード */
	private String itemCd;

	/** 分類コード区分 */
	private String categoryDivision;

	/** 仕入日 */
	private String strStockingDate;

	/** 仕入番号 */
	private String slipNo;

	/** 入庫ロケーション */
	private String housingLocationCd;

	/** 入荷予定数量 */
	private String strStockingQuantity;

	/** 単価 */
	private String strHousingUnitprice;

	/** 金額 */
	private String strStockingAmount;

	/** 会計部門借方コード */
	private String accountDebitSectionCd;

	/** 会計部門借方名称 */
	private String accountDebitSectionName;

	/** 会計部門貸方コード */
	private String accountCreditSectionCd;

	/** 会計部門貸方名称 */
	private String accountCreditSectionName;

	/** 借方科目コード */
	private String debitTitleCd;

	/** 借方科目名称 */
	private String debitTitleName;

	/** 貸方科目コード */
	private String creditTitleCd;

	/** 貸方科目名称 */
	private String creditTitleName;

	/** 適用(備考) */
	private String remark2;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者 */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者 */
	private String updatorCd;

	/** 購買ステータス */
	private String status;

	/** 入庫ロケーション名称 */
	private String locationName;

	/** 担当者名称 */
	private String tantoNm;

	/** 品目名称 */
	private String itemMasterName;

	/** 担当部署名称 */
	private String tantoSectionName;

	/** 部署名称 */
	private String sectionName;

	/** 取引先名称(仕入先名称1) */
	private String venderName1;

	/** 取引先略称 */
	private String venderShortedName;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 購入数量単位名称 (画面表示) */
	private String stockingQuantityUnit;

	/** 単価の単位名称 (画面表示) */
	private String housingUnitpriceUnit;

	/** 仕入ステータス */
	private String status2;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/** BuyingDetail Bean(検索結果格納) */
	private BuyingDetail bean;

	/** 仕入区分コンボボックス */
	private List<ComboBoxItems> stockinDivisionCombo;

	/** 分類コードリスト */
	private String[] categoryDivisionList;

	/** 仕訳反転フラグリスト */
	private String[] reversalFlgList;

	/** 仕訳反転フラグ前回値 */
	private String preReversalFlg;

	/** スポット区分 (品目名称入力可／不可の判断) */
	private String spotDivision;

	/** 在庫管理区分(仕入返品時の受払選択有無の判断） */
	private String stockDivision;

	/** 単価の単位区分 (個あたり／Kgあたり、金額計算で使用) */
	private String unitpriceDivision;

	// /** 単価の単位名称 (画面表示)*/
	// private String unitpriceName;

	/** Kg換算係数 (Kgあたりの場合、金額計算で使用) */
	private String kgOfFractionManagement;

	/** 小数点位置：購入数量 (hidden) */
	private String strStockingQuantityDecimalPoint;

	/** 小数点位置：重量 (hidden) */
	private String strConvertQuantityDecimalPoint;

	/** 小数点位置：単価 (hidden) */
	private String strHousingUnitpriceDecimalPoint;

	/** 小数点位置：金額 (hidden) */
	private String strStockingAmountDecimalPoint;

	/** 端数区分：購入数量 (hidden) */
	private String strStockingQuantityRound;

	/** 端数区分：重量 (hidden) */
	private String strConvertQuantityRound;

	/** 端数区分：単価 (hidden) */
	private String strHousingUnitpriceRound;

	/** 端数区分：金額 (hidden) */
	private String strStockingAmountRound;

	/** フォーカス位置保存 (hidden) */
	private String focusPosition;

	/** 取消ボタンを表示するかどうかのフラグ (hidden) */
	private String cancelFlg;

	/** 仕入区分を入力可／不可のフラグ (hidden) */
	private String categoryFlg;

	/** 仕入区分名称 */
	private String categoryName;

	/** 仕入-取消 仕入番号 */
	private String cancelSlipNo;

	/** 消費税課税区分 */
	private String taxDivision;

	/** 取引先マスタ.算出区分 */
	private String calcDivision;

	/** 自社マスタ.消費税算出区分 */
	private String compCalcDivision;

	/** 自社マスタ.消費税率 */
	private String taxRatio;

	/** 支払先コード */
	private String paymentInvoiceCd;

	/** 受払番号 */
	private String inoutNo;

	/** 受払ロット番号 */
	private String inoutLotNo;

	/** 受払数量 */
	private String inoutQty;

	/** 受払重量 */
	private String inoutWeight;

	/** 受払番号日付 */
	private String inoutDate;

	/** 理由名称 */
	private String ryName;

	/** 登録者名 */
	private String inputorName;

	/** 画面読み込み時の分類コード区分 */
	private String beforeCategoryDivision;

	/** 画面読み込み時の仕入番号（返品時のみ使用） */
	private String beforeInoutNo;

	// 2014/1/28 新消費税対応 ->>
	private String strTaxAmount;

	private String strTaxRatio;

	private String strTaxDivision;

	/** 消費税率コンボボックス */
	private List<ComboBoxItems> taxCombo;

	/** 小数点位置：購入数量 (hidden) */
	private String strTaxAmountDecimalPoint;

	/** 端数区分：購入数量 (hidden) */
	private String strTaxAmountRound;

	// 2014/1/28 新消費税対応 <--

	//20190821 軽減税率対応
	/** 消費税 */
	private String taxCd;
	private String[] taxLabels;
	private String[] taxValues;
	private String[] taxKeys;

	/** 免税計算対象フラグ */
	private String reducedTaxTargetFlg;

	/** 軽減措置金額 */
	private String strInvoiceAmount;

	/** 税額控除割合 */
	private BigDecimal invoiceTaxRatio;

	/** 軽減措置消費税金額 */
	private String strInvoiceTaxAmount;

	/**
	 * コンストラクタ.詳細
	 */
	public BuyingDetailForm() {
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 *
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
		if ("initNew".equals(getOp())) {
			clear();
		}
	}

	/**
	 * 入力データの検証
	 *
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}
		if ("update".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}
		if ("insert".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}
		if ("cancel".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
			if (errors.isEmpty()) {
				// 仕入日 入力チェック
				requiredChk(errors);
			}
		}
		return errors;
	}

	/**
	 * 仕入日 入力チェック
	 *
	 * @param errors 検証エラー内容
	 */
	private void requiredChk(final ActionErrors errors) {
		String stockingDate = this.getStrStockingDate().replace("/", "")
				.substring(0, 6); // 仕入日を取得し/を取り除く
		String stockingDate2 = this.getBean().getStrStockingDate().replace("/",
			"").substring(0, 6);
		if (stockingDate.compareTo(stockingDate2) <= 0) {
			errors.add("", new ActionMessage("errors.buying.cancel.date"));

		}
	}

	/**
	 * クリア処理.
	 */
	private void clear() {
		// 購買NO
		setPurchaseNo(null);

		// 発注番号
		setBuySubcontractOrderNo(null);

		// 担当部署
		setChargeOrganizationCd(null);

		// 部署コード
		setOrganizationCd(null);

		// 担当者コード
		setTantoCd(null);

		// 仕入先コード
		setVenderCd(null);

		// 品目コード
		setItemCd(null);

		// 備考
		setRemark2(null);

		// 分類コード
		setCategoryDivision(null);

		// 仕入日
		setStrStockingDate(null);

		// 仕入番号
		setSlipNo(null);

		// 入庫ロケーション
		setHousingLocationCd(null);

		// 入荷予定数量
		setStrStockingQuantity(null);

		// 単価
		setStrHousingUnitprice(null);

		// 金額
		setStrStockingAmount(null);

		// 会計部門借方コード
		setAccountDebitSectionCd(null);

		// 会計部門借方名称
		setAccountDebitSectionName(null);

		// 会計部門貸方コード
		setAccountCreditSectionCd(null);

		// 会計部門貸方名称
		setAccountCreditSectionName(null);

		// 借方科目コード
		setDebitTitleCd(null);

		// 借方科目名称
		setDebitTitleName(null);

		// 貸方科目コード
		setCreditTitleCd(null);

		// 貸方科目名称
		setCreditTitleName(null);

		// 摘要
		setRemark2(null);

		// 更新日
		setUpdateDate(null);

		// 更新者
		setUpdatorCd(null);

		// 登録日
		setInputDate(null);

		// 登録者
		setInputorCd(null);

		// 購買ステータス
		setStatus(null);

		// 入庫ロケーション名称
		setLocationName(null);

		// 担当者名称
		setTantoNm(null);

		// 品目名称
		setItemMasterName(null);

		// 担当部署名称
		setTantoSectionName(null);

		// 部署名称
		setSectionName(null);

		// 取引先名称(仕入先名称1)
		setVenderName1(null);

		// 取引先略称
		setVenderShortedName(null);

		// 他社コード1
		setOtherCompanyCd1(null);

		// 仕入ステータス
		setStatus2(null);

		// 運用管理区分
		setUnitOfOperationManagement(null);

		// BuyingDetail
		setBean(null);

		// 仕入区分
		setStockinDivisionCombo(null);

		// 分類コードリスト
		setCategoryDivisionList(null);

		// 仕訳反転フラグリスト
		setReversalFlgList(null);

		// 仕訳反転フラグ前回値
		setPreReversalFlg(null);

		/** スポット区分 (品目名称入力可／不可の判断) */
		setSpotDivision(null);

		/** 単価の単位区分 (個あたり／Kgあたり、金額計算で使用) */
		setUnitpriceDivision(null);

		/** Kg換算係数 (Kgあたりの場合、金額計算で使用) */
		setKgOfFractionManagement(null);

		/** 小数点位置：購入数量 (hidden) */
		setStrStockingQuantityDecimalPoint(null);

		/** 小数点位置：重量 (hidden) */
		setStrConvertQuantityDecimalPoint(null);

		/** 小数点位置：単価 (hidden) */
		setStrHousingUnitpriceDecimalPoint(null);

		/** 小数点位置：金額 (hidden) */
		setStrStockingAmountDecimalPoint(null);

		/** 端数区分：購入数量 (hidden) */
		setStrStockingQuantityRound(null);

		/** 端数区分：重量 (hidden) */
		setStrConvertQuantityRound(null);

		/** 端数区分：単価 (hidden) */
		setStrHousingUnitpriceRound(null);

		/** 端数区分：金額 (hidden) */
		setStrStockingAmountRound(null);

		/** フォーカス位置保存 (hidden) */
		setFocusPosition(null);

		/** 取消ボタンを表示するかどうかのフラグ (hidden) */
		setCancelFlg(null);

		/** 仕入区分を入力可／不可のフラグ (hidden) */
		setCategoryFlg(null);

		/** 仕入区分名称 */
		setCategoryName(null);

		/** 仕入-取消 仕入番号 */
		setCancelSlipNo(null);

		/** 消費税課税区分 */
		setTaxDivision(null);

		/** 自社マスタ.消費税率 */
		setTaxRatio(null);

		/** 取引先マスタ.算出区分 */
		setCalcDivision(null);

		/** 自社マスタ.消費税算出区分 */
		setCompCalcDivision(null);

		/** 支払い先コード */
		setPaymentInvoiceCd(null);

		/** 受払番号 */
		setInoutNo(null);

		/** 受払ロット番号 */
		setInoutLotNo(null);

		/** 受払数量 */
		setInoutQty(null);

		/** 受払重量 */
		setInoutWeight(null);

		/** 受払日付 */
		setInoutDate(null);

		/** 理由名称 */
		setRyName(null);

		/** 登録者名 */
		setInputorName(null);

		/** 画面描画時の仕入区分 */
		setBeforeCategoryDivision(null);

		/** 画面描画時の受払番号 */
		setBeforeInoutNo(null);

		/** 在庫管理区分 */
		setStockDivision(null);

		// 2014/1/28 新消費税対応 ->>
		/** 消費税額 */
		setStrTaxAmount(null);
		/** 消費税率 */
		setStrTaxRatio(null);

		/** 消費税区分 */
		setStrTaxDivision(null);

		/** 消費税端数区分 */
		setStrTaxAmountRound(null);

		/** 消費税小数点位置 */
		setStrTaxAmountDecimalPoint(null);
		// 2014/1/28 新消費税対応 <--
		//軽減税率対応
		/** 消費税CD */
		setTaxCd(null);
		/** 消費税率LABLE */
		setTaxLabels(null);
		/** 消費税率名称 */
		setTaxValues(null);
		/** 消費税率CD */
		setTaxKeys(null);
		/** 税コード変更フラグ */
		setTaxCdChangeFlg("false");

		/** 免税計算対象フラグ */
		setReducedTaxTargetFlg(null);
		/** 軽減措置金額 */
		setStrInvoiceAmount(null);
		/** 税額控除割合 */
		setStrInvoiceTaxAmount(null);
		/** 軽減措置消費税金額 */
		setInvoiceTaxRatio(null);
	}

	//
	// インスタンスメソッド
	//
	/**
	 * 購買NO取得
	 * @return purchaseNo 購買NO
	 */
	public String getPurchaseNo() {
		return purchaseNo;
	}

	/**
	 * 購買NO設定
	 * @param purchaseNo 購買NO
	 */
	public void setPurchaseNo(final String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	/**
	 * 発注番号取得
	 * @return buySubcontractOrderNo
	 */
	public String getBuySubcontractOrderNo() {
		return buySubcontractOrderNo;
	}

	/**
	 * 発注番号設定
	 * @param buySubcontractOrderNo 発注番号
	 */
	public void setBuySubcontractOrderNo(final String buySubcontractOrderNo) {
		this.buySubcontractOrderNo = buySubcontractOrderNo;
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
	 * 部署コード取得.
	 * @return String 部署コード
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * 部署コード設定.
	 * @param organizationCd 部署コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 担当者コード取得.
	 * @return String 担当者コード
	 */
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * 担当者コード設定.
	 * @param tantoCd 担当者コード
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * 仕入先コード取得.
	 * @return String 仕入先コード
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 仕入先コード設定.
	 * @param venderCd 仕入先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
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
	 * 備考取得.
	 * @return String 備考
	 */
	public String getRemark2() {
		return this.remark2;
	}

	/**
	 * 備考設定.
	 * @param remark2 備考
	 */
	public void setRemark2(final String remark2) {
		this.remark2 = remark2;
	}

	/**
	 * 分類コード取得.
	 * @return String 分類コード
	 */
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * 分類コード設定.
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 仕入日取得.
	 * @return String 仕入日
	 */
	public String getStrStockingDate() {
		return this.strStockingDate;
	}

	/**
	 * 仕入日設定.
	 * @param strStockingDate 仕入日
	 */
	public void setStrStockingDate(final String strStockingDate) {
		this.strStockingDate = strStockingDate;
	}

	/**
	 * 仕入番号取得.
	 * @return String 仕入番号
	 */
	public String getSlipNo() {
		return this.slipNo;
	}

	/**
	 * 仕入番号設定.
	 * @param slipNo 仕入番号
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
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
	 * 入荷予定数量取得.
	 * @return String 入荷予定数量
	 */
	public String getStrStockingQuantity() {
		return this.strStockingQuantity;
	}

	/**
	 * 入荷予定数量設定.
	 * @param strStockingQuantity 入荷予定数量
	 */
	public void setStrStockingQuantity(final String strStockingQuantity) {
		this.strStockingQuantity = strStockingQuantity;
	}

	/**
	 * 単価取得.
	 * @return String 単価
	 */
	public String getStrHousingUnitprice() {
		return this.strHousingUnitprice;
	}

	/**
	 * 単価設定.
	 * @param strHousingUnitprice 単価
	 */
	public void setStrHousingUnitprice(final String strHousingUnitprice) {
		this.strHousingUnitprice = strHousingUnitprice;
	}

	/**
	 * 金額取得.
	 * @return String 金額
	 */
	public String getStrStockingAmount() {
		return this.strStockingAmount;
	}

	/**
	 * 金額設定.
	 * @param strStockingAmount 金額
	 */
	public void setStrStockingAmount(final String strStockingAmount) {
		this.strStockingAmount = strStockingAmount;
	}

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
	 * 登録日取得
	 * @return 登録日
	 */
	public Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * 登録日設定
	 * @param inputDate 登録日
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者取得
	 * @return 登録者
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * 登録者設定
	 * @param inputorCd 登録者
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日取得
	 * @return 更新日
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * 更新日設定
	 * @param updateDate 更新日
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者取得
	 * @return 更新者
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * 更新者設定
	 * @param updatorCd 更新者
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * 購買ステータス取得。
	 * @return 購買ステータス
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 購買ステータス設定
	 * @param status 購買ステータス
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * 取引先名称取得(仕入先名称)
	 * @return venderName1 取引先名称
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * 取引先名称設定(仕入先名称)
	 * @param venderName1 取引先名称
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
	 * 担当者名称取得
	 * @return tantoNm 担当者名称
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * 担当者名称設定
	 * @param tantoNm 担当者名称
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * 入庫ロケーション名称取得
	 * @return locationName 入庫ロケーション名称
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * 入庫ロケーション名称設定
	 * @param locationName 入庫ロケーション名称
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * 品目名称取得
	 * @return itemMasterName 品目名称
	 */
	public String getItemMasterName() {
		return itemMasterName;
	}

	/**
	 * 品目名称設定
	 * @param itemMasterName 品目名称
	 */
	public void setItemMasterName(final String itemMasterName) {
		this.itemMasterName = itemMasterName;
	}

	/**
	 * 他社コード１取得
	 * @return String 他社コード１
	 */
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * 他社コード１設定
	 * @param otherCompanyCd1 他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 購入数量単位取得
	 * @return stockingQuantityUnit
	 */
	public String getStockingQuantityUnit() {
		return stockingQuantityUnit;
	}

	/**
	 * 購入数量単位設定
	 * @param stockingQuantityUnit 購入数量単位
	 */
	public void setStockingQuantityUnit(final String stockingQuantityUnit) {
		this.stockingQuantityUnit = stockingQuantityUnit;
	}

	/**
	 * 単価単位取得
	 * @return housingUnitpriceUnit
	 */
	public String getHousingUnitpriceUnit() {
		return housingUnitpriceUnit;
	}

	/**
	 * 単価単位設定
	 * @param housingUnitpriceUnit 単価単位
	 */
	public void setHousingUnitpriceUnit(final String housingUnitpriceUnit) {
		this.housingUnitpriceUnit = housingUnitpriceUnit;
	}

	/**
	 * 仕入ステータス取得。
	 * @return 仕入ステータス
	 */
	public String getStatus2() {
		return status2;
	}

	/**
	 * 仕入買ステータス設定
	 * @param status2 仕入ステータス
	 */
	public void setStatus2(final String status2) {
		this.status2 = status2;
	}

	/**
	 * BuyingDetailを取得します。
	 * @return bean
	 */
	public BuyingDetail getBean() {
		return bean;
	}

	/**
	 * BuyingDetailを設定します。
	 * @param bean BuyingDetail
	 */
	public void setBean(final BuyingDetail bean) {
		this.bean = bean;
	}

	/**
	 * 部署名称取得
	 * @return sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * 部署名称設定
	 * @param sectionName 部署名称
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * 担当部署名称取得
	 * @return tantoSectionName
	 */
	public String getTantoSectionName() {
		return tantoSectionName;
	}

	/**
	 * 担当部署名称設定
	 * @param tantoSectionName 担当部署名称
	 */
	public void setTantoSectionName(final String tantoSectionName) {
		this.tantoSectionName = tantoSectionName;
	}

	/**
	 * 会計部門貸方コード取得
	 * @return accountCreditSectionCd
	 */
	public String getAccountCreditSectionCd() {
		return accountCreditSectionCd;
	}

	/**
	 * 会計部門貸方コード設定
	 * @param accountCreditSectionCd 会計部門貸方コード
	 */
	public void setAccountCreditSectionCd(final String accountCreditSectionCd) {
		this.accountCreditSectionCd = accountCreditSectionCd;
	}

	/**
	 * 会計部門貸方名称取得
	 * @return accountCreditSectionName
	 */
	public String getAccountCreditSectionName() {
		return accountCreditSectionName;
	}

	/**
	 * 会計部門貸方名称設定
	 * @param accountCreditSectionName 会計部門貸方名称
	 */
	public void setAccountCreditSectionName(
			final String accountCreditSectionName) {
		this.accountCreditSectionName = accountCreditSectionName;
	}

	/**
	 * 会計部門借方コード取得
	 * @return accountDebitSectionCd
	 */
	public String getAccountDebitSectionCd() {
		return accountDebitSectionCd;
	}

	/**
	 * 会計部門借方コード設定
	 * @param accountDebitSectionCd 会計部門借方コード
	 */
	public void setAccountDebitSectionCd(final String accountDebitSectionCd) {
		this.accountDebitSectionCd = accountDebitSectionCd;
	}

	/**
	 * 会計部門借方名称取得
	 * @return accountDebitSectionName
	 */
	public String getAccountDebitSectionName() {
		return accountDebitSectionName;
	}

	/**
	 * 会計部門借方名称設定
	 * @param accountDebitSectionName 会計部門借方名称
	 */
	public void setAccountDebitSectionName(final String accountDebitSectionName) {
		this.accountDebitSectionName = accountDebitSectionName;
	}

	/**
	 * 貸方科目コード取得
	 * @return creditTitleCd
	 */
	public String getCreditTitleCd() {
		return creditTitleCd;
	}

	/**
	 * 貸方科目コード設定
	 * @param creditTitleCd 貸方科目コード
	 */
	public void setCreditTitleCd(final String creditTitleCd) {
		this.creditTitleCd = creditTitleCd;
	}

	/**
	 * 貸方科目名称取得
	 * @return creditTitleName
	 */
	public String getCreditTitleName() {
		return creditTitleName;
	}

	/**
	 * 貸方科目名称設定
	 * @param creditTitleName 貸方科目名称
	 */
	public void setCreditTitleName(final String creditTitleName) {
		this.creditTitleName = creditTitleName;
	}

	/**
	 * 借方科目コード取得
	 * @return debitTitleCd
	 */
	public String getDebitTitleCd() {
		return debitTitleCd;
	}

	/**
	 * 借方科目コード設定
	 * @param debitTitleCd 借方科目コード
	 */
	public void setDebitTitleCd(final String debitTitleCd) {
		this.debitTitleCd = debitTitleCd;
	}

	/**
	 * 借方科目名称取得
	 * @return debitTitleName
	 */
	public String getDebitTitleName() {
		return debitTitleName;
	}

	/**
	 * 借方科目名称設定
	 * @param debitTitleName 借方科目名称
	 */
	public void setDebitTitleName(final String debitTitleName) {
		this.debitTitleName = debitTitleName;
	}

	/**
	 * 運用管理単位取得
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * 運用管理単位設定
	 * @param unitOfOperationManagement 運用管理単位
	 */
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * 仕入区分コンボボックス取得
	 * @return stockinDivisionCombo
	 */
	public List<ComboBoxItems> getStockinDivisionCombo() {
		return stockinDivisionCombo;
	}

	/**
	 * 仕入区分コンボボックス設定
	 * @param stockinDivisionCombo 仕入区分コンボボックス
	 */
	public void setStockinDivisionCombo(
			final List<ComboBoxItems> stockinDivisionCombo) {
		this.stockinDivisionCombo = stockinDivisionCombo;
	}

	/**
	 * 分類コードリスト取得
	 * @return String[] 分類コードリスト
	 */
	public String[] getCategoryDivisionList() {
		return categoryDivisionList;
	}

	/**
	 * 分類コードリスト設定
	 * @param categoryDivisionList categoryDivisionList
	 */
	public void setCategoryDivisionList(final String[] categoryDivisionList) {
		this.categoryDivisionList = categoryDivisionList;
	}

	/**
	 * 仕訳反転フラグリスト取得
	 * @return String[] 仕訳反転フラグリスト
	 */
	public String[] getReversalFlgList() {
		return reversalFlgList;
	}

	/**
	 * 仕訳反転フラグリスト設定
	 * @param reversalFlgList 仕訳反転フラグリスト
	 */
	public void setReversalFlgList(final String[] reversalFlgList) {
		this.reversalFlgList = reversalFlgList;
	}

	/**
	 * 仕訳反転フラグ前回値取得
	 * @return String 仕訳反転フラグ前回値
	 */
	public String getPreReversalFlg() {
		return preReversalFlg;
	}

	/**
	 * 仕訳反転フラグ前回値設定
	 * @param preReversalFlg 仕訳反転フラグ前回値
	 */
	public void setPreReversalFlg(final String preReversalFlg) {
		this.preReversalFlg = preReversalFlg;
	}

	/**
	 * Kg換算係数を取得します。
	 * @return kgOfFractionManagement
	 */
	public String getKgOfFractionManagement() {
		return kgOfFractionManagement;
	}

	/**
	 * Kg換算係数を設定します。
	 * @param kgOfFractionManagement Kg換算係数
	 */
	public void setKgOfFractionManagement(final String kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * スポット区分を取得します。
	 * @return spotDivision
	 */
	public String getSpotDivision() {
		return spotDivision;
	}

	/**
	 * スポット区分を設定します。
	 * @param spotDivision スポット区分
	 */
	public void setSpotDivision(final String spotDivision) {
		this.spotDivision = spotDivision;
	}

	/**
	 * 小数点位置：重量を取得します。
	 * @return strConvertQuantityDecimalPoint
	 */
	public String getStrConvertQuantityDecimalPoint() {
		return strConvertQuantityDecimalPoint;
	}

	/**
	 * 小数点位置：重量を設定します。
	 * @param strConvertQuantityDecimalPoint 小数点位置：重量
	 */
	public void setStrConvertQuantityDecimalPoint(
			final String strConvertQuantityDecimalPoint) {
		this.strConvertQuantityDecimalPoint = strConvertQuantityDecimalPoint;
	}

	/**
	 * 端数区分：重量を取得します。
	 * @return strConvertQuantityRound
	 */
	public String getStrConvertQuantityRound() {
		return strConvertQuantityRound;
	}

	/**
	 * 端数区分：重量を設定します。
	 * @param strConvertQuantityRound 端数区分：重量
	 */
	public void setStrConvertQuantityRound(final String strConvertQuantityRound) {
		this.strConvertQuantityRound = strConvertQuantityRound;
	}

	/**
	 * 小数点位置：単価を取得します。
	 * @return strHousingUnitpriceDecimalPoint
	 */
	public String getStrHousingUnitpriceDecimalPoint() {
		return strHousingUnitpriceDecimalPoint;
	}

	/**
	 * 小数点位置：単価を設定します。
	 * @param strHousingUnitpriceDecimalPoint 小数点位置：単価
	 */
	public void setStrHousingUnitpriceDecimalPoint(
			final String strHousingUnitpriceDecimalPoint) {
		this.strHousingUnitpriceDecimalPoint = strHousingUnitpriceDecimalPoint;
	}

	/**
	 * 端数区分：単価を取得します。
	 * @return strHousingUnitpriceRound
	 */
	public String getStrHousingUnitpriceRound() {
		return strHousingUnitpriceRound;
	}

	/**
	 * 端数区分：単価を設定します。
	 * @param strHousingUnitpriceRound 端数区分：単価
	 */
	public void setStrHousingUnitpriceRound(
			final String strHousingUnitpriceRound) {
		this.strHousingUnitpriceRound = strHousingUnitpriceRound;
	}

	/**
	 * 小数点位置：金額を取得します。
	 * @return strStockingAmountDecimalPoint
	 */
	public String getStrStockingAmountDecimalPoint() {
		return strStockingAmountDecimalPoint;
	}

	/**
	 * 小数点位置：金額を設定します。
	 * @param strStockingAmountDecimalPoint 小数点位置：金額
	 */
	public void setStrStockingAmountDecimalPoint(
			final String strStockingAmountDecimalPoint) {
		this.strStockingAmountDecimalPoint = strStockingAmountDecimalPoint;
	}

	/**
	 * 端数区分：金額を取得します。
	 * @return strStockingAmountRound
	 */
	public String getStrStockingAmountRound() {
		return strStockingAmountRound;
	}

	/**
	 * 端数区分：金額を設定します。
	 * @param strStockingAmountRound 端数区分：金額
	 */
	public void setStrStockingAmountRound(final String strStockingAmountRound) {
		this.strStockingAmountRound = strStockingAmountRound;
	}

	/**
	 * 小数点位置：購入数量を取得します。
	 * @return strStockingQuantityDecimalPoint
	 */
	public String getStrStockingQuantityDecimalPoint() {
		return strStockingQuantityDecimalPoint;
	}

	/**
	 * 小数点位置：購入数量を設定します。
	 * @param strStockingQuantityDecimalPoint 小数点位置：購入数量
	 */
	public void setStrStockingQuantityDecimalPoint(
			final String strStockingQuantityDecimalPoint) {
		this.strStockingQuantityDecimalPoint = strStockingQuantityDecimalPoint;
	}

	/**
	 * 端数区分：購入数量を取得します。
	 * @return strStockingQuantityRound
	 */
	public String getStrStockingQuantityRound() {
		return strStockingQuantityRound;
	}

	/**
	 * 端数区分：購入数量を設定します。
	 * @param strStockingQuantityRound 端数区分：購入数量
	 */
	public void setStrStockingQuantityRound(
			final String strStockingQuantityRound) {
		this.strStockingQuantityRound = strStockingQuantityRound;
	}

	/**
	 * 単価の単位区分を取得します。
	 * @return unitpriceDivision
	 */
	public String getUnitpriceDivision() {
		return unitpriceDivision;
	}

	/**
	 * 単価の単位区分を設定します。
	 * @param unitpriceDivision 単価の単位区分
	 */
	public void setUnitpriceDivision(final String unitpriceDivision) {
		this.unitpriceDivision = unitpriceDivision;
	}

	/**
	 * フォーカス位置保存を取得します。
	 * @return focusPosition
	 */
	public String getFocusPosition() {
		return focusPosition;
	}

	/**
	 * フォーカス位置保存を設定します。
	 * @param focusPosition フォーカス位置保存
	 */
	public void setFocusPosition(final String focusPosition) {
		this.focusPosition = focusPosition;
	}

	/**
	 * 取消ボタンを表示するかどうかのフラグを取得します。
	 * @return cancelFlg
	 */
	public String getCancelFlg() {
		return cancelFlg;
	}

	/**
	 * 取消ボタンを表示するかどうかのフラグを設定します。
	 * @param cancelFlg 取消ボタンを表示するかどうかのフラグ
	 */
	public void setCancelFlg(final String cancelFlg) {
		this.cancelFlg = cancelFlg;
	}

	/**
	 * 仕入区分を入力可／不可のフラグを取得します。
	 * @return categoryFlg
	 */
	public String getCategoryFlg() {
		return categoryFlg;
	}

	/**
	 * 仕入区分を入力可／不可のフラグを設定します。
	 * @param categoryFlg 仕入区分を入力可／不可のフラグ
	 */
	public void setCategoryFlg(final String categoryFlg) {
		this.categoryFlg = categoryFlg;
	}

	/**
	 * 仕入区分名称を取得します。
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 仕入区分名称を設定します。
	 * @param categoryName 仕入区分名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 仕入-取消 仕入番号取得
	 * @return String 仕入-取消 仕入番号
	 */
	public String getCancelSlipNo() {
		return this.cancelSlipNo;
	}

	/**
	 * 仕入-取消 仕入番号設定
	 * @param cancelSlipNo 仕入-取消 仕入番号
	 */
	public void setCancelSlipNo(final String cancelSlipNo) {
		this.cancelSlipNo = cancelSlipNo;
	}

	/**
	 * 消費税課税区分取得
	 * @return BigDecimal 消費税課税区分
	 */
	public String getTaxDivision() {
		return taxDivision;
	}

	/**
	 * 消費税課税区分設定
	 * @param taxDivision 消費税課税区分
	 */
	public void setTaxDivision(final String taxDivision) {
		this.taxDivision = taxDivision;
	}

	/**
	 * 取引先マスタ.算出区分取得
	 * @return BigDecimal 取引先マスタ.算出区分
	 */
	public String getCalcDivision() {
		return calcDivision;
	}

	/**
	 * 取引先マスタ.算出区分設定
	 * @param calcDivision 取引先マスタ.算出区分
	 */
	public void setCalcDivision(final String calcDivision) {
		this.calcDivision = calcDivision;
	}

	/**
	 * 自社マスタ.消費税算出区分取得
	 * @return BigDecimal 自社マスタ.消費税算出区分
	 */
	public String getCompCalcDivision() {
		return compCalcDivision;
	}

	/**
	 * 自社マスタ.消費税算出区分設定
	 * @param compCalcDivision 自社マスタ.消費税算出区分
	 */
	public void setCompCalcDivision(final String compCalcDivision) {
		this.compCalcDivision = compCalcDivision;
	}

	/**
	 * 自社マスタ.消費税率取得
	 * @return BigDecimal 自社マスタ.消費税率
	 */
	public String getTaxRatio() {
		return taxRatio;
	}

	/**
	 * 自社マスタ.消費税率設定
	 * @param taxRatio 自社マスタ.消費税率
	 */
	public void setTaxRatio(final String taxRatio) {
		this.taxRatio = taxRatio;
	}

	/**
	 * inoutNo取得.
	 * @return inoutNo
	 */
	public String getInoutNo() {
		return this.inoutNo;
	}

	/**
	 * inoutNo設定.
	 * @param inoutNo inoutNo
	 */
	public void setInoutNo(final String inoutNo) {
		this.inoutNo = inoutNo;
	}

	/**
	 * lotNo取得.
	 * @return lotNo
	 */
	public String getInoutLotNo() {
		return this.inoutLotNo;
	}

	/**
	 * lotNo設定.
	 * @param lotNo lotNo
	 */
	public void setInoutLotNo(final String lotNo) {
		this.inoutLotNo = lotNo;
	}

	/**
	 * inoutQty取得.
	 * @return inoutQty
	 */
	public String getInoutQty() {
		return this.inoutQty;
	}

	/**
	 * inoutQty設定.
	 * @param inoutQty inoutQty
	 */
	public void setInoutQty(final String inoutQty) {
		this.inoutQty = inoutQty;
	}

	/**
	 * inoutWeight取得.
	 * @return inoutWeight
	 */
	public String getInoutWeight() {
		return inoutWeight;
	}

	/**
	 * inoutWeight設定.
	 * @param inoutWeight inoutWeight
	 */
	public void setInoutWeight(final String inoutWeight) {
		this.inoutWeight = inoutWeight;
	}

	/**
	 * inoutDate取得.
	 * @return inoutDate
	 */
	public String getInoutDate() {
		return this.inoutDate;
	}

	/**
	 * inoutDate設定.
	 * @param inoutDate inoutDate
	 */
	public void setInoutDate(final String inoutDate) {
		this.inoutDate = inoutDate;
	}

	/**
	 * ryName取得.
	 * @return ryName
	 */
	public String getRyName() {
		return this.ryName;
	}

	/**
	 * ryName設定.
	 * @param ryName ryName
	 */
	public void setRyName(final String ryName) {
		this.ryName = ryName;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorName(final String inputorCd) {
		this.inputorName = inputorCd;
	}

	/**
	 * paymentInvoiceCdを取得します。
	 * @return paymentInvoiceCd
	 */
	public String getPaymentInvoiceCd() {
		return paymentInvoiceCd;
	}

	/**
	 * paymentInvoiceCdを設定します。
	 * @param paymentInvoiceCd paymentInvoiceCd
	 */
	public void setPaymentInvoiceCd(final String paymentInvoiceCd) {
		this.paymentInvoiceCd = paymentInvoiceCd;
	}

	/**
	 * beforeCategoryDivisionを取得します。
	 * @return beforeCategoryDivision
	 */
	public String getBeforeCategoryDivision() {
		return beforeCategoryDivision;
	}

	/**
	 * beforeCategoryDivisionを設定します。
	 * @param beforeCategoryDivision beforeCategoryDivision
	 */
	public void setBeforeCategoryDivision(final String beforeCategoryDivision) {
		this.beforeCategoryDivision = beforeCategoryDivision;
	}

	/**
	 * beforeInoutNoを取得します。
	 * @return beforeInoutNo
	 */
	public String getBeforeInoutNo() {
		return beforeInoutNo;
	}

	/**
	 * beforeInoutNoを設定します。
	 * @param beforeInoutNo beforeInoutNo
	 */
	public void setBeforeInoutNo(final String beforeInoutNo) {
		this.beforeInoutNo = beforeInoutNo;
	}

	/**
	 * stockDivisionを取得します。
	 * @return stockDivision
	 */
	public String getStockDivision() {
		return stockDivision;
	}

	/**
	 * stockDivisionを設定します。
	 * @param stockDivision stockDivision
	 */
	public void setStockDivision(final String stockDivision) {
		this.stockDivision = stockDivision;
	}

	/**
	 * 消費税率コンボボックスを取得します。
	 * @return carryCombo
	 */
	public List<ComboBoxItems> getTaxCombo() {
		return taxCombo;
	}

	/**
	 * 消費税率コンボボックスを設定します。
	 * @param carryCombo 消費税率コンボボックス
	 */
	public void setTaxCombo(final List<ComboBoxItems> carryCombo) {
		this.taxCombo = carryCombo;
	}

	/**
	 * 購買トランザクション消費税額取得.
	 * @return String 購買トランザクション消費税額
	 */
	public String getStrTaxAmount() {
		return strTaxAmount;
	}

	/**
	 * 購買トランザクション消費税額設定.
	 * @param strTaxAmount 購買トランザクション消費税額
	 */
	public void setStrTaxAmount(final String strTaxAmount) {
		this.strTaxAmount = strTaxAmount;
	}

	/**
	 * 購買トランザクション売上消費税区分取得.
	 * @return String 購買トランザクション売上消費税区分
	 */
	public String getStrTaxDivision() {
		return strTaxDivision;
	}

	/**
	 * 購買トランザクション売上消費税区分設定.
	 * @param strTaxDivision 売上消費税区分
	 */
	public void setStrTaxDivision(final String strTaxDivision) {
		this.strTaxDivision = strTaxDivision;
	}

	/**
	 * 購買トランザクション消費税率取得.
	 * @return String 消費税率
	 */
	public String getStrTaxRatio() {
		return strTaxRatio;
	}

	/**
	 * 購買トランザクション消費税率設定.
	 * @param strTaxRatio 消費税率
	 */
	public void setStrTaxRatio(final String strTaxRatio) {
		this.strTaxRatio = strTaxRatio;
	}

	/**
	 * strTaxAmountDecimalPoint取得.
	 * @return strTaxAmountDecimalPoint strTaxAmountDecimalPoint
	 */
	public String getStrTaxAmountDecimalPoint() {
		return strTaxAmountDecimalPoint;
	}

	/**
	 * strTaxAmountDecimalPoint設定.
	 * @param strTaxAmountDecimalPoint strTaxAmountDecimalPoint
	 */
	public void setStrTaxAmountDecimalPoint(
			final String strTaxAmountDecimalPoint) {
		this.strTaxAmountDecimalPoint = strTaxAmountDecimalPoint;
	}

	/**
	 * strTaxAmountRound取得.
	 * @return strTaxAmountRound strTaxAmountRound
	 */
	public String getStrTaxAmountRound() {
		return strTaxAmountRound;
	}

	/**
	 * strTaxAmountRound設定.
	 * @param strTaxAmountRound strTaxAmountRound
	 */
	public void setStrTaxAmountRound(final String strTaxAmountRound) {
		this.strTaxAmountRound = strTaxAmountRound;
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

	/**
	 * reducedTaxTargetFlgを取得します。
	 * @return reducedTaxTargetFlg
	 */
	public String getReducedTaxTargetFlg() {
		return reducedTaxTargetFlg;
	}

	/**
	 * reducedTaxTargetFlgを設定します。
	 * @param reducedTaxTargetFlg reducedTaxTargetFlg
	 */
	public void setReducedTaxTargetFlg(String reducedTaxTargetFlg) {
		this.reducedTaxTargetFlg = reducedTaxTargetFlg;
	}

	/**
	 * strInvoiceAmountを取得します。
	 * @return strInvoiceAmount
	 */
	public String getStrInvoiceAmount() {
		return strInvoiceAmount;
	}

	/**
	 * strInvoiceAmountを設定します。
	 * @param strInvoiceAmount strInvoiceAmount
	 */
	public void setStrInvoiceAmount(String strInvoiceAmount) {
		this.strInvoiceAmount = strInvoiceAmount;
	}

	/**
	 * invoiceTaxRatioを取得します。
	 * @return invoiceTaxRatio
	 */
	public BigDecimal getInvoiceTaxRatio() {
		return invoiceTaxRatio;
	}

	/**
	 * invoiceTaxRatioを設定します。
	 * @param invoiceTaxRatio invoiceTaxRatio
	 */
	public void setInvoiceTaxRatio(BigDecimal invoiceTaxRatio) {
		this.invoiceTaxRatio = invoiceTaxRatio;
	}

	/**
	 * strInvoiceTaxAmountを取得します。
	 * @return strInvoiceTaxAmount
	 */
	public String getStrInvoiceTaxAmount() {
		return strInvoiceTaxAmount;
	}

	/**
	 * strInvoiceTaxAmountを設定します。
	 * @param strInvoiceTaxAmount strInvoiceTaxAmount
	 */
	public void setStrInvoiceTaxAmount(String strInvoiceTaxAmount) {
		this.strInvoiceTaxAmount = strInvoiceTaxAmount;
	}
}
