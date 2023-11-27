/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.materialrinput;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputDetailList;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 外注原材料投入実績入力 Formクラス.
 * @author tosco
 */
public final class MaterialRinputDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	//
	// インスタンスフィールド

	/** 発注番号 */
	private String buySubcontractOrderNo;

	/** 発注書NO */
	private String orderSheetNo;

	/** 発注日 */
	private String strOrderDate;

	/** 受入日 */
	private String strAcceptDate;

	/** 納品希望日 */
	private String strSuggestedDeliverlimitDate;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemQueueName;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 仕入先コード */
	private String venderCd;

	/** 仕入先名称 */
	private String venderName;

	/** 仕入先略称 */
	private String venderShortedName;

	/** 納入ロケーションコード */
	private String locationCd;

	/** 納入先名称 */
	private String locationName;

	/** 担当部署コード */
	private String chargeOrganizationCd;

	/** 担当部署名称 */
	private String chargeOrganizationName;

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String organizationName;

	/** 発注者コード */
	private String tantoCd;

	/** 発注者名称 */
	private String tantoNm;

	/** 発注数量 */
	private String strOrderQuantity;

	/** 単位 */
	private String unit;

	/** 荷姿 */
	private String styleOfPacking;

	/** 重量 */
	private String strOrderConvertQuantity;

	/** 基本処方コード */
	private String recipeCdVersion;

	/** 基本処方名称 */
	private String recipeName;

	/** レシピインデックス */
	private String recipeId;

	/** レシピコード */
	private String recipeCd;

	/** レシピバージョン */
	private String recipeVersion;

	/** 生産出来高 */
	private String strGoodHousingSum;

	/** 運用管理単位(区分) */
	private String unitDiv;

	/** 小数点桁数 */
	private String decimalPoint;

	/** 端数区分 */
	private String round;

	/** 小数点桁数(配合) */
	private String decimalPointHaigo;

	/** 端数区分(配合) */
	private String roundHaigo;

	/** 小数点桁数(調整数量) */
	private String decimalPointHaigoAdj;

	/** 端数区分(調整数量) */
	private String roundHaigoAdj;

	/** 計算フラグ */
	private boolean calcFlg;

	/** リスト */
	private List<MaterialRinputDetailList> detailList;

	/** 在庫引落状態フラグ */
	private String stockpdStateFlg;

	/** 在庫引落取消行番号 */
	private String line;

	/**
	 * コンストラクタ.詳細
	 */
	public MaterialRinputDetailForm() {
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

		if ("init".equals(getOp())) {
			// リストのクリア
			setDetailList(new ArrayList<MaterialRinputDetailList>());
		}

		if ("calculate".equals(getOp())) {
			errors = new ActionErrors();
			// 基本処方コード、生産出来高チェック
			validateCalcChk(errors, request);
		}

		if ("insert".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			if (errors.isEmpty()) {
				validateSumQtyChk(errors, request);
			}
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {

		// 発注番号
		setBuySubcontractOrderNo(null);
		// 発注書NO
		setOrderSheetNo(null);
		// 発注日
		setStrOrderDate(null);
		// 納品希望日
		setStrSuggestedDeliverlimitDate(null);
		// 受入日
		setStrAcceptDate(null);
		// 品目コード
		setItemCd(null);
		// 品目名称
		setItemQueueName(null);
		// 他社コード１
		setOtherCompanyCd1(null);
		// 仕入先コード
		setVenderCd(null);
		// 仕入先名称
		setVenderName(null);
		// 納入ロケーションコード
		setLocationCd(null);
		// 納入先名称
		setLocationName(null);
		// 担当部署コード
		setChargeOrganizationCd(null);
		// 担当部署名称
		setChargeOrganizationName(null);
		// 部署コード
		setOrganizationCd(null);
		// 部署名称
		setOrganizationName(null);
		// 発注者コード
		setTantoCd(null);
		// 発注者名称
		setTantoNm(null);
		// 発注数量
		setStrOrderQuantity(null);
		// 単位
		setUnit(null);
		// 荷姿
		setStyleOfPacking(null);
		// 重量
		setStrOrderConvertQuantity(null);
		// 基本処方コード
		setRecipeCdVersion(null);
		// 基本処方名称
		setRecipeName(null);
		// レシピインデックス
		setRecipeId(null);
		// レシピコード
		setRecipeCd(null);
		// レシピバージョン
		setRecipeVersion(null);
		// 生産出来高
		setStrGoodHousingSum(null);
		// 運用管理単位(区分)
		setUnitDiv(null);
		// 小数点桁数
		setDecimalPoint(null);
		// 端数区分
		setRound(null);
		// 小数点桁数(配合)
		setDecimalPointHaigo(null);
		// 端数区分(配合)
		setRoundHaigo(null);
		// 小数点桁数(調整数量)
		setDecimalPointHaigoAdj(null);
		// 端数区分(調整数量)
		setRoundHaigoAdj(null);
		// 計算フラグ
		setCalcFlg(false);
		// 在庫引落存在フラグ
		setStockpdStateFlg("0");
		// 仕入先略称
		setVenderShortedName(null);
	}

	//
	// インスタンスメソッド
	//

	/**
	 * 発注番号取得.
	 * @return String 発注番号
	 */
	public String getBuySubcontractOrderNo() {
		return this.buySubcontractOrderNo;
	}

	/**
	 * 発注番号設定.
	 * @param buySubcontractOrderNo 発注番号
	 */
	public void setBuySubcontractOrderNo(final String buySubcontractOrderNo) {
		this.buySubcontractOrderNo = buySubcontractOrderNo;
	}

	/**
	 * 発注書NO取得.
	 * @return String 発注書NO
	 */
	public String getOrderSheetNo() {
		return this.orderSheetNo;
	}

	/**
	 * 発注書NO設定.
	 * @param orderSheetNo 発注書NO
	 */
	public void setOrderSheetNo(final String orderSheetNo) {
		this.orderSheetNo = orderSheetNo;
	}

	/**
	 * 発注日取得.
	 * @return String 発注日
	 */
	public String getStrOrderDate() {
		return this.strOrderDate;
	}

	/**
	 * 発注日設定.
	 * @param strOrderDate 発注日
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	/**
	 * 納品希望日取得.
	 * @return String 納品希望日
	 */
	public String getStrSuggestedDeliverlimitDate() {
		return this.strSuggestedDeliverlimitDate;
	}

	/**
	 * 納品希望日設定.
	 * @param strSuggestedDeliverlimitDate 納品希望日
	 */
	public void setStrSuggestedDeliverlimitDate(
			final String strSuggestedDeliverlimitDate) {
		this.strSuggestedDeliverlimitDate = strSuggestedDeliverlimitDate;
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
	 * 品目名称取得
	 * @return String 品目名称
	 */
	public String getItemQueueName() {
		return itemQueueName;
	}

	/**
	 * 品目名称設定
	 * @param itemQueueName 品目名称
	 */
	public void setItemQueueName(final String itemQueueName) {
		this.itemQueueName = itemQueueName;
	}

	/**
	 * 他社コード１を取得します。
	 * @return String 他社コード１
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード１を設定します。
	 * @param otherCompanyCd1 他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
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
	 * 仕入先名称を取得します。
	 * @return String 仕入先名称
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 仕入先名称を設定します。
	 * @param venderName 仕入先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * 納入ロケーションコード取得.
	 * @return String 納入ロケーションコード
	 */
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * 納入ロケーションコード設定.
	 * @param locationCd 納入ロケーションコード
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * 納入先名称を取得します。
	 * @return String 納入先名称
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * 納入先名称を設定します。
	 * @param locationName 納入先名称
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * 担当部署コード取得.
	 * @return String 担当部署コード
	 */
	public String getChargeOrganizationCd() {
		return chargeOrganizationCd;
	}

	/**
	 * 担当部署コード設定.
	 * @param chargeOrganizationCd 担当部署コード
	 */
	public void setChargeOrganizationCd(final String chargeOrganizationCd) {
		this.chargeOrganizationCd = chargeOrganizationCd;
	}

	/**
	 * 担当部署名称取得.
	 * @return String 担当部署名称
	 */
	public String getChargeOrganizationName() {
		return chargeOrganizationName;
	}

	/**
	 * 担当部署名称設定.
	 * @param chargeOrganizationName 担当部署名称
	 */
	public void setChargeOrganizationName(final String chargeOrganizationName) {
		this.chargeOrganizationName = chargeOrganizationName;
	}

	/**
	 * 部署コードを取得します。
	 * @return String 部署コード
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コードを設定します。
	 * @param organizationCd 部署コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 部署名称を取得します。
	 * @return String 部署名称
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 部署名称を設定します。
	 * @param organizationName 部署名称
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * 発注者コードを取得します。
	 * @return String 発注者コード
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * 発注者コードを設定します。
	 * @param tantoCd 発注者コード
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * 発注者名称を取得します。
	 * @return String 発注者名称
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * 発注者名称を設定します。
	 * @param tantoNm 発注者名称
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * 発注数量取得.
	 * @return String 発注数量
	 */
	public String getStrOrderQuantity() {
		return this.strOrderQuantity;
	}

	/**
	 * 発注数量設定.
	 * @param strOrderQuantity 発注数量
	 */
	public void setStrOrderQuantity(final String strOrderQuantity) {
		this.strOrderQuantity = strOrderQuantity;
	}

	/**
	 * 単位取得
	 * @return String 単位
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 単位設定
	 * @param unit 単位
	 */
	public void setUnit(final String unit) {
		this.unit = unit;
	}

	/**
	 * 荷姿を取得します。
	 * @return String 荷姿
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
	 * 重量取得.
	 * @return String 重量
	 */
	public String getStrOrderConvertQuantity() {
		return this.strOrderConvertQuantity;
	}

	/**
	 * 重量設定.
	 * @param strOrderConvertQuantity 重量
	 */
	public void setStrOrderConvertQuantity(final String strOrderConvertQuantity) {
		this.strOrderConvertQuantity = strOrderConvertQuantity;
	}

	/**
	 * 基本処方コード取得.
	 * @return String 基本処方コード
	 */
	public String getRecipeCdVersion() {
		return recipeCdVersion;
	}

	/**
	 * 基本処方コード設定.
	 * @param recipeCdVersion 基本処方コード
	 */
	public void setRecipeCdVersion(final String recipeCdVersion) {
		this.recipeCdVersion = recipeCdVersion;
	}

	/**
	 * 基本処方名称取得.
	 * @return String 基本処方名称
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * 基本処方名称設定.
	 * @param recipeName 基本処方名称
	 */
	public void setRecipeName(final String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * レシピインデックス取得.
	 * @return String レシピインデックス
	 */
	public String getRecipeId() {
		return recipeId;
	}

	/**
	 * レシピインデックス設定.
	 * @param recipeId レシピインデックス
	 */
	public void setRecipeId(final String recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * レシピコード取得.
	 * @return String レシピコード
	 */
	public String getRecipeCd() {
		return recipeCd;
	}

	/**
	 * レシピコード設定.
	 * @param recipeCd レシピコード
	 */
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = recipeCd;
	}

	/**
	 * レシピバージョン取得.
	 * @return String レシピバージョン
	 */
	public String getRecipeVersion() {
		return recipeVersion;
	}

	/**
	 * レシピバージョン設定.
	 * @param recipeVersion レシピバージョン
	 */
	public void setRecipeVersion(final String recipeVersion) {
		this.recipeVersion = recipeVersion;
	}

	/**
	 * 生産出来高取得.
	 * @return String 生産出来高
	 */
	public String getStrGoodHousingSum() {
		return strGoodHousingSum;
	}

	/**
	 * 生産出来高設定.
	 * @param strGoodHousingSum 生産出来高
	 */
	public void setStrGoodHousingSum(final String strGoodHousingSum) {
		this.strGoodHousingSum = strGoodHousingSum;
	}

	/**
	 * 運用管理単位(区分)取得
	 * @return String 運用管理単位(区分)
	 */
	public String getUnitDiv() {
		return unitDiv;
	}

	/**
	 * 運用管理単位(区分)設定
	 * @param unitDiv 運用管理単位(区分)
	 */
	public void setUnitDiv(final String unitDiv) {
		this.unitDiv = unitDiv;
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
	 * 小数点桁数(配合)取得
	 * @return String 小数点桁数(配合)
	 */
	public String getDecimalPointHaigo() {
		return decimalPointHaigo;
	}

	/**
	 * 小数点桁数(配合)設定
	 * @param decimalPointHaigo 小数点桁数(配合)
	 */
	public void setDecimalPointHaigo(final String decimalPointHaigo) {
		this.decimalPointHaigo = decimalPointHaigo;
	}

	/**
	 * 端数区分(配合)取得
	 * @return String 端数区分(配合)
	 */
	public String getRoundHaigo() {
		return roundHaigo;
	}

	/**
	 * 端数区分(配合)設定
	 * @param roundHaigo 端数区分(配合)
	 */
	public void setRoundHaigo(final String roundHaigo) {
		this.roundHaigo = roundHaigo;
	}

	/**
	 * 小数点桁数(調整数量)取得
	 * @return String 小数点桁数(調整数量)
	 */
	public String getDecimalPointHaigoAdj() {
		return decimalPointHaigoAdj;
	}

	/**
	 * 小数点桁数(調整数量)設定
	 * @param decimalPointHaigoAdj 小数点桁数(調整数量)
	 */
	public void setDecimalPointHaigoAdj(final String decimalPointHaigoAdj) {
		this.decimalPointHaigoAdj = decimalPointHaigoAdj;
	}

	/**
	 * 端数区分(調整数量)取得
	 * @return String 端数区分(調整数量)
	 */
	public String getRoundHaigoAdj() {
		return roundHaigoAdj;
	}

	/**
	 * 端数区分(調整数量)設定
	 * @param roundHaigoAdj 端数区分(調整数量)
	 */
	public void setRoundHaigoAdj(final String roundHaigoAdj) {
		this.roundHaigoAdj = roundHaigoAdj;
	}

	/**
	 * 計算フラグ取得
	 * @return boolean 計算フラグ
	 */
	public boolean isCalcFlg() {
		return calcFlg;
	}

	/**
	 * 計算フラグ設定
	 * @param calcFlg 計算フラグ
	 */
	public void setCalcFlg(final boolean calcFlg) {
		this.calcFlg = calcFlg;
	}

	/**
	 * 詳細リストを取得します。
	 * @return String 詳細リスト
	 */
	public List<MaterialRinputDetailList> getDetailList() {
		return detailList;
	}

	/**
	 * 詳細リストを設定します。
	 * @param detailList 詳細リスト
	 */
	public void setDetailList(final List<MaterialRinputDetailList> detailList) {
		this.detailList = detailList;
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
	 * 明細行数を取得する。
	 * @return 明細行数
	 */
	public int getDetailCount() {
		int count = 0;
		if (detailList != null) {
			count = detailList.size();
		}
		return count;
	}

	/**
	 * 在庫引落状態フラグ(0:なし 1:在庫引落入力中 2:在庫引落完了)取得
	 * @return String 在庫引落状態フラグ
	 */
	public String getStockpdStateFlg() {
		return stockpdStateFlg;
	}

	/**
	 * 在庫引落状態フラグ(0:なし 1:在庫引落入力中 2:在庫引落完了)取得
	 * @param stockpdStateFlg 在庫引落状態フラグ
	 */
	public void setStockpdStateFlg(final String stockpdStateFlg) {
		this.stockpdStateFlg = stockpdStateFlg;
	}

	/**
	 * 在庫引落取消行番号を取得します。
	 * @return line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * 在庫引落取消行番号を設定します。
	 * @param line 在庫引落取消行番号
	 */
	public void setLine(final String line) {
		this.line = line;
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
	 * 計算ボタン押下時に基本処方コード、生産出来高の入力チェックを行う。
	 * @param errors エラー内容
	 * @param request リクエスト
	 */
	private void validateCalcChk(final ActionErrors errors,
			final HttpServletRequest request) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		// 基本処方コード(必須チェック)
		if (StringUtils.isEmpty(this.getRecipeCdVersion())) {
			errors.add("", new ActionMessage("errors.required", rb
					.getString("item.materialrinput.recipe.cd.version")));
		}

		// 生産出来高(必須、数値チェック)
		if (StringUtils.isEmpty(this.getStrGoodHousingSum())) {
			errors.add("", new ActionMessage("errors.required", rb
					.getString("item.materialrinput.str.good.housing.sum")));
		} else {
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);
			ActionMessage message = check.checkDigitMessage(this.getUnitDiv(),
				MaterialRinputDetailLogicImpl.VENDER_DIV_SI,
				this.getVenderCd(), this.getStrGoodHousingSum(), rb
						.getString("item.materialrinput.str.good.housing.sum"));
			if (message != null) {
				errors.add("", message);
			}
		}
	}

	/**
	 * 登録時に使用量合計のマイナスチェックを行う。
	 * @param errors エラー内容
	 * @param request リクエスト
	 */
	private void validateSumQtyChk(final ActionErrors errors,
			final HttpServletRequest request) {
		List<MaterialRinputDetailList> list = this.getDetailList();
		int index = 1;

		for (MaterialRinputDetailList bean : list) {
			// 使用量合計
			BigDecimal sumUseQty = AecNumberUtils.convertBigDecimal(bean
					.getStrSumUseQty());
			if (sumUseQty.signum() == -1) {
				errors.add("", new ActionMessage(
						"errors.materialrinput.minus.sum.use.qty.row", Integer
								.toString(index)));
			}

			index++;
		}
	}

	/**
	 * strAcceptDateを取得します。
	 * @return strAcceptDate
	 */
	public String getStrAcceptDate() {
		return strAcceptDate;
	}

	/**
	 * strAcceptDateを設定します。
	 * @param strAcceptDate strAcceptDate
	 */
	public void setStrAcceptDate(final String strAcceptDate) {
		this.strAcceptDate = strAcceptDate;
	}

}
