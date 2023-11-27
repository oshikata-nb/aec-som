/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;

/**
 * ヘッダー情報 Formクラス.
 * @author tosco
 */
public final class GrecipeHeaderForm extends AbstractGrecipeForm {

	private static final long serialVersionUID = 1L;

	/** 編集モードフラグ * */
	private int mode;

	/** 注釈 */
	private String recipeDescription;

	/** 備考 */
	private String recipeMemo;

	/** 品目コード */
	private String inputProduct;

	/** 品目名 */
	private String inputProductName;

	/** 標準生産量 */
	private String stdQty;

	/** ロス率 */
	private String processLoss;

	/** 有効開始日 */
	private String startDate;

	/** 有効終了日 */
	private String endDate;

	/** ステータスコンボボックス */
	private List<ComboBoxItems> statusCombo;

	/** 工程パターンコンボボックス */
	private List<ComboBoxItems> operationCombo;

	/** 工程パターン */
	private long operationPattern;

	/** 単位名称 */
	private String unitName;

	/** 配合量 */
	private String sumQty;

	/** 検索時処方ヘッダーデータ */
	private GrecipeRecipeHeaderList header;

	/** 単位区分 */
	private String unitDivision;

	/** 少数点桁数 */
	private String smallnumLength;

	/** 端数区分 */
	private String roundDivision;

	/** リンク */
	private String srhLink;

	private boolean excelDownloadFlg;

	/**
	 * コンストラクタ.
	 */
	public GrecipeHeaderForm() {
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	@Override
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);
	}

	/**
	 * 入力データの検証
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	@Override
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("update".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}
		if ("insert".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	@Override
	protected void clear() {
		super.clear();
		// 注釈
		setRecipeDescription(null);
		// 備考
		setRecipeMemo(null);
		// 主要製品コード(品目コード）
		setInputProduct(null);
		// 標準生産量
		setStdQty(null);
		// ロス率
		setProcessLoss(null);
		// 有効開始日
		setStartDate(null);
		// 有効終了日
		setEndDate(null);
		// 使いますか？
		// 入力品目名
		setInputProductName(null);
		// ステータスコンボボックス
		setStatusCombo(null);
		// 工程パターンコンボボックス
		setOperationCombo(null);
		// 工程パターン
		setOperationPattern(0);
		// 単位名称
		setUnitName(null);
		// 配合量
		setSumQty(null);
		// 検索時処方ヘッダーデータ
		setHeader(null);
		// 単位区分
		setUnitDivision(null);
		/** Excelダウンロードフラグ */
		setExcelDownloadFlg(false);
	}

	//
	// インスタンスメソッド
	//

	/**
	 * 注釈取得.
	 * @return String 注釈
	 */
	public String getRecipeDescription() {
		return this.recipeDescription;
	}

	/**
	 * 注釈設定.
	 * @param recipeDescription 注釈
	 */
	public void setRecipeDescription(final String recipeDescription) {
		this.recipeDescription = recipeDescription;
	}

	/**
	 * 備考取得.
	 * @return String 備考
	 */
	public String getRecipeMemo() {
		return this.recipeMemo;
	}

	/**
	 * 備考設定.
	 * @param recipeMemo 備考
	 */
	public void setRecipeMemo(final String recipeMemo) {
		this.recipeMemo = recipeMemo;
	}

	/**
	 * 主要製品コード(品目コード）取得.
	 * @return String 主要製品コード(品目コード）
	 */
	public String getInputProduct() {
		return this.inputProduct;
	}

	/**
	 * 主要製品コード(品目コード）設定.
	 * @param inputProduct 主要製品コード(品目コード）
	 */
	public void setInputProduct(final String inputProduct) {
		this.inputProduct = inputProduct;
	}

	/**
	 * 標準生産量取得.
	 * @return String 標準生産量
	 */
	public String getStdQty() {
		return this.stdQty;
	}

	/**
	 * 標準生産量設定.
	 * @param stdQty 標準生産量
	 */
	public void setStdQty(final String stdQty) {
		this.stdQty = stdQty;
	}

	/**
	 * ロス率取得.
	 * @return String ロス率
	 */
	public String getProcessLoss() {
		return this.processLoss;
	}

	/**
	 * ロス率設定.
	 * @param processLoss ロス率
	 */
	public void setProcessLoss(final String processLoss) {
		this.processLoss = processLoss;
	}

	/**
	 * 有効開始日取得.
	 * @return String 有効開始日
	 */
	public String getStartDate() {
		return this.startDate;
	}

	/**
	 * 有効開始日設定.
	 * @param startDate 有効開始日
	 */
	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 有効終了日取得.
	 * @return String 有効終了日
	 */
	public String getEndDate() {
		return this.endDate;
	}

	/**
	 * 有効終了日設定.
	 * @param endDate 有効終了日
	 */
	public void setEndDate(final String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 編集モードフラグを取得します。
	 * @return int 編集モードフラグ
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * 編集モードフラグを設定します。
	 * @param mode 編集モードフラグ
	 */
	public void setMode(final int mode) {
		this.mode = mode;
	}

	/**
	 * 品目名を取得します。
	 * @return 品目名
	 */
	public String getInputProductName() {
		return inputProductName;
	}

	/**
	 * 品目名を設定します。
	 * @param inputProductName 品目名
	 */
	public void setInputProductName(final String inputProductName) {
		this.inputProductName = inputProductName;
	}

	/**
	 * ステータスコンボボックスを取得します。
	 * @return ステータスコンボボックス
	 */
	public List<ComboBoxItems> getStatusCombo() {
		return statusCombo;
	}

	/**
	 * ステータスコンボボックスを設定します。
	 * @param statusCombo ステータスコンボボックス
	 */
	public void setStatusCombo(final List<ComboBoxItems> statusCombo) {
		this.statusCombo = statusCombo;
	}

	/**
	 * 工程パターンコンボボックスを取得します。
	 * @return 工程パターンコンボボックス
	 */
	public List<ComboBoxItems> getOperationCombo() {
		return operationCombo;
	}

	/**
	 * 工程パターンコンボボックスを設定します。
	 * @param operationCombo 工程パターンコンボボックス
	 */
	public void setOperationCombo(final List<ComboBoxItems> operationCombo) {
		this.operationCombo = operationCombo;
	}

	/**
	 * 工程パターンを取得します。
	 * @return 工程パターン
	 */
	public long getOperationPattern() {
		return operationPattern;
	}

	/**
	 * 工程パターンを設定します。
	 * @param operationPattern 工程パターン
	 */
	public void setOperationPattern(final long operationPattern) {
		this.operationPattern = operationPattern;
	}

	/**
	 * 単位名称を取得します。
	 * @return 単位名称
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位名称を設定します。
	 * @param unitName 単位名称
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 配合量を取得します。
	 * @return 配合量
	 */
	public String getSumQty() {
		return sumQty;
	}

	/**
	 * 配合量を設定します。
	 * @param sumQty 配合量
	 */
	public void setSumQty(final String sumQty) {
		this.sumQty = sumQty;
	}

	/**
	 * 検索時処方ヘッダーデータを取得します。
	 * @return 検索時処方ヘッダーデータ
	 */
	public GrecipeRecipeHeaderList getHeader() {
		return header;
	}

	/**
	 * 検索時処方ヘッダーデータを設定します。
	 * @param header 検索時処方ヘッダーデータ
	 */
	public void setHeader(final GrecipeRecipeHeaderList header) {
		this.header = header;
	}

	/**
	 * 単位区分を取得します。
	 * @return 単位区分
	 */
	public String getUnitDivision() {
		return unitDivision;
	}

	/**
	 * 単位区分を設定します。
	 * @param unitDivision 単位区分
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}

	/**
	 * 端数区分を取得します。
	 * @return roundDivision
	 */
	public String getRoundDivision() {
		return roundDivision;
	}

	/**
	 * 端数区分を設定します。
	 * @param roundDivision 端数区分
	 */
	public void setRoundDivision(final String roundDivision) {
		this.roundDivision = roundDivision;
	}

	/**
	 * 少数点桁数を取得します。
	 * @return smallnumLength
	 */
	public String getSmallnumLength() {
		return smallnumLength;
	}

	/**
	 * 少数点桁数を設定します。
	 * @param smallnumLength 少数点桁数
	 */
	public void setSmallnumLength(final String smallnumLength) {
		this.smallnumLength = smallnumLength;
	}

	/**
	 * srhLinkを取得します。
	 * @return srhLink
	 */
	public String getSrhLink() {
		return srhLink;
	}

	/**
	 * srhLinkを設定します。
	 * @param srhLink srhLink
	 */
	public void setSrhLink(final String srhLink) {
		this.srhLink = srhLink;
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
