/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * ヘッダー情報 Formクラス.
 * @author tosco
 */
public final class MgrecipeHeaderForm extends AbstractMgrecipeForm {

	private static final long serialVersionUID = 1L;

	/** 編集モードフラグ * */
	private int mode;

	/** 注釈 */
	private String recipeDescription;

	/** 備考 */
	private String recipeMemo;

	/** 優先度 */
	private String recipePriority;

	/** 原処方レシピインデックス */
	private String originalRecipeId;

	/** 生産工場 */
	private String inputProductionLine;

	/** 品目コード */
	private String inputProduct;

	/** 品目名 */
	private String inputProductName;

	/** 最小生産量 */
	private String minQty;

	/** 最大生産量 */
	private String maxQty;

	/** 標準生産量 */
	private String stdQty;

	/** 単位生産量 */
	private String unitQty;

	/** ロス率 */
	private String processLoss;

	/** 会計部門コード */
	private String sectionCd;

	/** 会計部門名称 */
	private String sectionName;

	/** 有効開始日 */
	private String startDate;

	/** 有効終了日 */
	private String endDate;

	/** 基本処方名称 */
	private String inputRecipeName;

	/** 生産工場コンボボックス */
	private List<ComboBoxItems> lineCombo;

	/** ステータスコンボボックス */
	private List<ComboBoxItems> statusCombo;

	/** 工程パターンコンボボックス */
	private List<ComboBoxItems> operationCombo;

	/** 配合再計算チェックボックス */
	private boolean calc;

	/** 工程パターン */
	private long operationPattern;

	/** 単位名称 */
	private String unitName;

	/** 配合量 */
	private String sumQty;

	/** 洗浄水絶対重量計 */
	private String waterWeight;

	/** 荷姿 */
	private String styleOfPacking;

	/** 変更前－標準生産量 */
	private BigDecimal orgStdQty;

	/** 検索時処方ヘッダーデータ */
	private RecipeHeaderList header;

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
	public MgrecipeHeaderForm() {
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

		// 配合再計算チェックボックスをクリア
		setCalc(false);
		/** Excelダウンロードフラグ */
		setExcelDownloadFlg(false);
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
			// 単位生産量≦最低生産量≦標準生産量≦最高生産量チェック
			additionalCheck(errors);
		}
		if ("insert".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
			// 単位生産量≦最低生産量≦標準生産量≦最高生産量チェック
			additionalCheck(errors);
		}
		return errors;
	}

	/**
	 * 数量の範囲チェック
	 * @param errors ActionErrors
	 * @return ActionErrors
	 */
	private ActionErrors additionalCheck(final ActionErrors errors) {
		if (errors.isEmpty()) {
			// 単位生産量≦最低生産量≦標準生産量≦最高生産量チェック
			BigDecimal min = AecNumberUtils.convertBigDecimal(getMinQty());
			BigDecimal max = AecNumberUtils.convertBigDecimal(getMaxQty());
			BigDecimal std = AecNumberUtils.convertBigDecimal(getStdQty());
			BigDecimal unit = AecNumberUtils.convertBigDecimal(getUnitQty());
			boolean error = false;
			if (unit.compareTo(min) > 0) {
				// 単位生産量>最低生産量
				error = true;
			}
			if (min.compareTo(std) > 0) {
				// 最低生産量>標準生産量
				error = true;
			}
			if (std.compareTo(max) > 0) {
				// 標準生産量>最高生産量
				error = true;
			}
			if (error) {
				ActionMessage message = new ActionMessage(
						"errors.mgrecipe.qty.cope");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			}
			ResourceBundle rb = ResourceBundle
					.getBundle(Constants.APPLICATION_PROPERTIES);
			BigDecimal k = std.divideToIntegralValue(unit);
			if (k.multiply(unit).compareTo(std) != 0) {
				ActionMessage message = new ActionMessage(
						"errors.mgrecipe.unitqty", rb
								.getString("item.mgrecipe.std.qty"));
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			}
			k = min.divideToIntegralValue(unit);
			if (k.multiply(unit).compareTo(min) != 0) {
				ActionMessage message = new ActionMessage(
						"errors.mgrecipe.unitqty", rb
								.getString("item.mgrecipe.min.qty"));
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			}
			k = max.divideToIntegralValue(unit);
			if (k.multiply(unit).compareTo(max) != 0) {
				ActionMessage message = new ActionMessage(
						"errors.mgrecipe.unitqty", rb
								.getString("item.mgrecipe.max.qty"));
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			}

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
		// 優先度
		setRecipePriority(null);
		// 原処方レシピインデックス
		setOriginalRecipeId(null);
		// PR生産ライン
		setInputProductionLine(null);
		// 主要製品コード(品目コード）
		setInputProduct(null);
		// 最小生産量
		setMinQty(null);
		// 最大生産量
		setMaxQty(null);
		// 標準生産量
		setStdQty(null);
		// 単位生産量
		setUnitQty(null);
		// ロス率
		setProcessLoss(null);
		// 会計部門コード
		setSectionCd(null);
		// 会計部門名称
		setSectionName(null);
		// 有効開始日
		setStartDate(null);
		// 有効終了日
		setEndDate(null);
		// 使いますか？
		// 基本処方名称
		setInputRecipeName(null);
		// 入力品目名
		setInputProductName(null);
		// 生産ラインコンボボックス
		setLineCombo(null);
		// ステータスコンボボックス
		setStatusCombo(null);
		// 工程パターンコンボボックス
		setOperationCombo(null);
		// 配合再計算チェックボックスをクリア
		setCalc(false);
		// 工程パターン
		setOperationPattern(0);
		// 単位名称
		setUnitName(null);
		// 配合量
		setSumQty(null);
		// 洗浄す絶対重量計
		setWaterWeight(null);
		// 荷姿
		setStyleOfPacking(null);
		// 変更前標準生産量
		setOrgStdQty(new BigDecimal("-1"));
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
	 * 優先度取得.
	 * @return String 優先度
	 */
	public String getRecipePriority() {
		return this.recipePriority;
	}

	/**
	 * 優先度設定.
	 * @param recipePriority 優先度
	 */
	public void setRecipePriority(final String recipePriority) {
		this.recipePriority = recipePriority;
	}

	/**
	 * 原処方レシピインデックス取得.
	 * @return String 原処方レシピインデックス
	 */
	public String getOriginalRecipeId() {
		return this.originalRecipeId;
	}

	/**
	 * 原処方レシピインデックス設定.
	 * @param originalRecipeId 原処方レシピインデックス
	 */
	public void setOriginalRecipeId(final String originalRecipeId) {
		this.originalRecipeId = originalRecipeId;
	}

	/**
	 * 生産工場取得.
	 * @return String 生産工場
	 */
	public String getInputProductionLine() {
		return this.inputProductionLine;
	}

	/**
	 * 生産工場設定.
	 * @param inputProductionLine 生産工場
	 */
	public void setInputProductionLine(final String inputProductionLine) {
		this.inputProductionLine = inputProductionLine;
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
	 * 最小生産量取得.
	 * @return String 最小生産量
	 */
	public String getMinQty() {
		return this.minQty;
	}

	/**
	 * 最小生産量設定.
	 * @param minQty 最小生産量
	 */
	public void setMinQty(final String minQty) {
		this.minQty = minQty;
	}

	/**
	 * 最大生産量取得.
	 * @return String 最大生産量
	 */
	public String getMaxQty() {
		return this.maxQty;
	}

	/**
	 * 最大生産量設定.
	 * @param maxQty 最大生産量
	 */
	public void setMaxQty(final String maxQty) {
		this.maxQty = maxQty;
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
	 * 単位生産量取得.
	 * @return String 単位生産量
	 */
	public String getUnitQty() {
		return this.unitQty;
	}

	/**
	 * 単位生産量設定.
	 * @param unitQty 単位生産量
	 */
	public void setUnitQty(final String unitQty) {
		this.unitQty = unitQty;
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
	 * 基本処方名称取得.
	 * @return String 基本処方名称
	 */
	public String getInputRecipeName() {
		return this.inputRecipeName;
	}

	/**
	 * 基本処方名称設定.
	 * @param inputRecipeName 基本処方名称
	 */
	public void setInputRecipeName(final String inputRecipeName) {
		this.inputRecipeName = inputRecipeName;
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
	 * 生産工場コンボボックスを取得します。
	 * @return 生産工場コンボボックス
	 */
	public List<ComboBoxItems> getLineCombo() {
		return lineCombo;
	}

	/**
	 * 生産工場コンボボックスを設定します。
	 * @param lineCombo 生産工場コンボボックス
	 */
	public void setLineCombo(final List<ComboBoxItems> lineCombo) {
		this.lineCombo = lineCombo;
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
	 * 配合再計算チェックボックスを取得します。
	 * @return 配合再計算チェックボックス
	 */
	public boolean isCalc() {
		return calc;
	}

	/**
	 * 配合再計算チェックボックスを設定します。
	 * @param calc 配合再計算チェックボックス
	 */
	public void setCalc(final boolean calc) {
		this.calc = calc;
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
	 * 洗浄水絶対重量計を取得します。
	 * @return 洗浄水絶対重量計
	 */
	public String getWaterWeight() {
		return waterWeight;
	}

	/**
	 * 洗浄水絶対重量計を設定します。
	 * @param waterWeight 洗浄水絶対重量計
	 */
	public void setWaterWeight(final String waterWeight) {
		this.waterWeight = waterWeight;
	}

	/**
	 * 荷姿を取得します。
	 * @return 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿を設定します。
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 変更前－標準生産量を取得します。
	 * @return 変更前－標準生産量
	 */
	public BigDecimal getOrgStdQty() {
		return orgStdQty;
	}

	/**
	 * 変更前－標準生産量を設定します。
	 * @param orgStdQty 変更前－標準生産量
	 */
	public void setOrgStdQty(final BigDecimal orgStdQty) {
		this.orgStdQty = orgStdQty;
	}

	/**
	 * 検索時処方ヘッダーデータを取得します。
	 * @return 検索時処方ヘッダーデータ
	 */
	public RecipeHeaderList getHeader() {
		return header;
	}

	/**
	 * 検索時処方ヘッダーデータを設定します。
	 * @param header 検索時処方ヘッダーデータ
	 */
	public void setHeader(final RecipeHeaderList header) {
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
	 * sectionCdを取得します。
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * sectionCdを設定します。
	 * @param sectionCd sectionCd
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * sectionNameを取得します。
	 * @return sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * sectionNameを設定します。
	 * @param sectionName sectionName
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
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
