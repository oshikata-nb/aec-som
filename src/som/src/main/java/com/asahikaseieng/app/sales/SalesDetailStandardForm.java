/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;

/**
 * 売上詳細(標準) Formクラス.
 * @author tosco
 */
public final class SalesDetailStandardForm extends AbstractSalesDetailForm {

	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド

	/** 売上区分コンボボックス */
	private List<ComboBoxItems> categoryCombo;

	/** 分類コードリスト */
	private String[] categoryDivisionList;

	/** 仕訳反転フラグリスト */
	private String[] reversalFlgList;

	/** 仕訳反転フラグ前回値 */
	private String preReversalFlg;

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

	/** 前受金区分 2011/7/11追加 */
	private BigDecimal arDivision;

	/**
	 * コンストラクタ.出荷指図詳細（花王・その他）
	 */
	public SalesDetailStandardForm() {
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
		} else if ("initNew".equals(getOp())) {
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

		if ("insert".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			// 明細のチェック
			validateDetail(errors, request);
		} else if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			// 明細のチェック
			validateDetail(errors, request);
		} else if ("initContinue".equals(getOp())) {
			/* イレギュラーだけど、ここでフォームをクリア */
			this.clearNew();

		}

		return errors;
	}

	/**
	 * 入力データ（詳細情報リスト）の検証（更新時）
	 * 
	 * @param errors 検証エラー内容
	 * @param request HttpServletRequest
	 */
	private void validateDetail(final ActionErrors errors,
			final HttpServletRequest request) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		// 品目の数値チェック
		if (StringUtils.isNotEmpty(getItemCd())) {
			validateSalesQuantity(getStrSalesQuantity(), getUnitDivision(),
				getVenderCd(), rb.getString("item.sales.keep.sales.quantity"),
				check, errors);
		}
	}

	/**
	 * クリア処理.
	 */
	protected void clear() {
		super.clear();
		// 分類コードリスト
		setCategoryDivisionList(null);
		// 仕訳反転フラグリスト
		setReversalFlgList(null);
		// 仕訳反転フラグ前回値
		setPreReversalFlg(null);
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

	}

	/**
	 * 新規登録時のクリア処理（得意先関連データを残す)
	 */
	protected void clearNew() {

		// 画面ﾎｰﾙﾄﾞの場合仕分が変わるので前売上の区分が2:返品か3:値引きの場合
		// 借方に貸方のデータをセット
		if (getCategoryDivision().equals("2")
				|| getCategoryDivision().equals("3")
				|| getCategoryDivision().equals("12")
				|| getCategoryDivision().equals("13")) {

			/** 会計部門借方部門コード */
			setAccountDebitSectionCd(getAccountCreditSectionCd());
			/** 会計部門借方部門名称 */
			setAccountDebitSectionName(getAccountCreditSectionName());
			/** 借方科目コード */
			setDebitTitleCd(getCreditTitleCd());
			/** 借方科目名称 */
			setDebitTitleName(getCreditTitleName());

		}

		/** 会計部門貸方部門コード */
		setAccountCreditSectionCd(null);
		/** 会計部門貸方部門名称 */
		setAccountCreditSectionName(null);
		/** 貸方科目コード */
		setCreditTitleCd(null);
		/** 貸方科目名称 */
		setCreditTitleName(null);

		/** 売上日付(文字列) */
		// setStrSalesDate(null);
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
		// setStrAccountYears(null);
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
		// setVenderCd(null);
		/** 得意先名称 */
		// setVenderName1(null);
		// 得意先略称
		// setVenderShortedName(null);
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
		// setChargeOrganizationCd(null);
		/** 担当部署名称 */
		// setChargeOrganizationName(null);
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
		// /** 月次更新済みフラグ */
		setMonthlyUpdateDivision(0);
		/** 取消元データフラグ */
		setCancelOriginFlag(0);
		/** 売上トランザクション更新日付 */
		setUpdateDate(null);
		/** 売上トランザクション更新日付(取消元) */
		setUpdateDateOrigin(null);
		// 分類コードリスト
		setCategoryDivisionList(null);
		// 仕訳反転フラグリスト
		setReversalFlgList(null);
		// 仕訳反転フラグ前回値
		setPreReversalFlg(null);
		/** 備考(摘要) */
		setSummary(null);
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

		setArDivision(null);

		// 2014/2/3 新消費税対応 ->
		setStrTaxAmount(null);

		setStrTaxDivision(null);

		// setStrTaxRatio(null);
		// 2014/2/3 新消費税対応 <-
		// /** 在庫管理区分 */
		// setStockDivision(null);
	}

	//
	// インスタンスメソッド
	//
	/**
	 * 売上区分コンボボックスを取得します。
	 * @return 売上区分コンボボックス
	 */
	public List<ComboBoxItems> getCategoryCombo() {
		return categoryCombo;
	}

	/**
	 * 売上区分コンボボックスを設定します。
	 * @param categoryCombo 売上区分コンボボックス
	 */
	public void setCategoryCombo(final List<ComboBoxItems> categoryCombo) {
		this.categoryCombo = categoryCombo;
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
	 * @param categoryDivisionList 分類コードリスト
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
	 * arDivisionを取得します。
	 * @return arDivision
	 */
	public BigDecimal getArDivision() {
		return arDivision;
	}

	/**
	 * arDivisionを設定します。
	 * @param arDivision arDivision
	 */
	public void setArDivision(final BigDecimal arDivision) {
		this.arDivision = arDivision;
	}

	/**
	 * 売上区分(売上)を取得する。
	 * @return 売上区分(売上)
	 */
	public String getCategoryDevisionSales() {
		return SalesConst.CATEGOTRY_DIVISION_SALES;
	}

	/**
	 * 売上区分(返品)を取得する。
	 * @return 売上区分(返品)
	 */
	public String getCategoryDevisionReturns() {
		return SalesConst.CATEGOTRY_DIVISION_RETURNS;
	}

	/**
	 * 売上区分(売上)を取得する。
	 * @return 売上区分(売上)
	 */
	public String getCategoryDevisionSalesAdvance() {
		return SalesConst.CATEGOTRY_DIVISION_SALES_ADVANCE;
	}

	/**
	 * 売上区分(返品)を取得する。
	 * @return 売上区分(返品)
	 */
	public String getCategoryDevisionReturnsAdvance() {
		return SalesConst.CATEGOTRY_DIVISION_RETURNS_ADVANCE;
	}

}
