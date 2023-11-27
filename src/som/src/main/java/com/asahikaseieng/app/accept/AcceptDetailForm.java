/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.accept;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.accept.AcceptDetailList;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 受入入力詳細 Formクラス.
 * @author tosco
 */
public final class AcceptDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/** 最終受入データフラグ：最終受入データ */
	private static final String LAST_DATA_FLG_LAST = "1";

	/** 入力チェック用：半角数字 */
	private static final String PTN_NUMERIC_NOCONMA = "0123456789";

	private String dirtyFlg; /* 変更フラグ */

	//
	// インスタンスフィールド

	/** 購買NO */
	private String purchaseNo;

	/** 発注番号 */
	private String buySubcontractOrderNo;

	/** 発注日 */
	private String strOrderDate;

	/** 仕入先受注番号 */
	private String siOrderNo;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemQueueName;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 発注数量 */
	private String orderQuantity;

	/** 発注数量 */
	private String strOrderQuantity;

	/** 荷姿 */
	private String styleOfPacking;

	/** 重量 */
	private String strOrderConvertQuantity;

	/** 仕入番号 */
	private String slipNo;

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

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String organizationName;

	/** 納品希望日 */
	private String strSuggestedDeliverlimitDate;

	/** 発注残数量 */
	private String balanceOrderQuantity;

	/** 完納区分 */
	private boolean deliveryComp;

	/** 分類コード */
	private String categoryDivision;

	/** 仕入区分コンボボックス */
	private List<ComboBoxItems> stockinDivisionCombo;

	/** 購買ステータス */
	private String status;

	/** 発注書NO */
	private String orderSheetNo;

	/** 次回納品希望日 */
	private String nextDeliverlimitDate;

	/** 次回納品希望日(時間) */
	private String nextDeliverlimitDateTime;

	/** 担当部署名称 */
	private String chargeOrganizationName;

	/** 発注書備考（入荷以降） */
	private String orderSheetRemark2;

	/** 備考（入荷以降） */
	private String remark2;

	/** リスト */
	private List<AcceptDetailList> detailList;

	/** オーダー区分 */
	private String orderDivision;

	/** 受入数量合計(自身以外) */
	private String sumAcceptQuantity;

	/** 単位 */
	private String unit;

	/** 発注番号分納枝番 */
	private String orderDivideNo;

	/** 小数点桁数 */
	private String decimalPoint;

	/** 端数区分 */
	private String round;

	/**
	 * 区分(A:原材料(自社)ローリー以外,B:原材料(自社)ローリー,C:原材料(外注),D:外注製品(直送)
	 * ,E:外注製品(非直送),F:仕入直送品,G:スポット品,H:仕入在庫品)
	 */
	private String kbn;

	/** 入荷処理対象フラグ(0:入荷処理対象外,1:入荷処理対象) */
	private String nyukaFlg;

	/** 最終受入データフラグ(0:最終受入データでない,1:最終受入データ) */
	private String lastDataFlg;

	/** IFテーブル更新ステータス 0:正常 1:異常 */
	private String ifUpdateStatus;

	/** 免税計算対象フラグ */
	private String reducedTaxTargetFlg;

	/**
	 * コンストラクタ.詳細
	 */
	public AcceptDetailForm() {
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
		// if ("insert".equals(getOp()) || "update".equals(getOp())) {
		if ("insert".equals(getOp())) {
			setDeliveryComp(false);
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
			/* イレギュラーだけど、ここでリストをクリア */
			setDetailList(new ArrayList<AcceptDetailList>());
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		// 入荷対象外の場合
		if ("insert".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);

			if (errors.isEmpty()) {
				// 完納フラグ、次回納品希望日時 入力チェック
				requiredChk(errors);
			}
			if (errors.isEmpty()) {
				boolean firstflg = true;
				String dt = null;
				for (AcceptDetailList bean : getDetailList()) {
					if (firstflg) {
						dt = bean.getStrAcceptDate();
					}
					firstflg = false;
					if (!dt.equals(bean.getStrAcceptDate())) {
						errors.add("", new ActionMessage(
								"errors.accept.notequal.date"));
						break;
					}
				}
			}
		}

		// 入荷対象の場合
		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);

			if (errors.isEmpty()) {
				// 最終受入データの場合
				if (LAST_DATA_FLG_LAST.equals(this.getLastDataFlg())) {

					// 受入登録済みの場合完納フラグチェックと次回納品希望日チェックを行わない
					if (!this.getStatus().equals("6")) {

						// 完納フラグ必須チェック
						deliveryCompChk(errors);
					}

				}
			}
			chkSameLotLocation(errors);
		}
		if ("delLoc".equals(getOp())) {
			if (!this.getDetailList().isEmpty()) {
				if (this.getDetailList().get(0).isCheckFlg()) {
					errors = new ActionErrors();
					errors.add("", new ActionMessage(
							"errors.accept.notdelete.first"));
					this.getDetailList().get(0).setCheckFlg(false);
				}
			}
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {

		// 購買NO
		setPurchaseNo(null);
		// 発注番号
		setBuySubcontractOrderNo(null);
		// 発注日
		setStrOrderDate(null);
		// 仕入先受注番
		setSiOrderNo(null);
		// 品目コード
		setItemCd(null);
		// 品目名称
		setItemQueueName(null);
		// 他社コード１
		setOtherCompanyCd1(null);
		// 発注数量
		setOrderQuantity(null);
		setStrOrderQuantity(null);
		// 荷姿
		setStyleOfPacking(null);
		// 重量
		setStrOrderConvertQuantity(null);
		// 仕入番号
		setSlipNo(null);
		// 仕入先コード
		setVenderCd(null);
		// 仕入先名称
		setVenderName(null);
		// 仕入先略称
		setVenderShortedName(null);
		// 納入ロケーションコード
		setLocationCd(null);
		// 納入先名称
		setLocationName(null);
		// 部署コード
		setOrganizationCd(null);
		// 部署名称
		setOrganizationName(null);
		// 納品希望日
		setStrSuggestedDeliverlimitDate(null);
		// 発注残数量
		setBalanceOrderQuantity(null);
		// 完納区分
		setDeliveryComp(false);
		// 分類コード
		setCategoryDivision(null);
		// 仕入区分コンボボックス
		setStockinDivisionCombo(null);
		// 購買ステータス
		setStatus(null);
		// 発注書NO
		setOrderSheetNo(null);
		// 次回納品希望日
		setNextDeliverlimitDate(null);
		// 次回納品希望日(時間)
		setNextDeliverlimitDateTime(null);
		// 発注書備考（入荷以降）
		setOrderSheetRemark2(null);
		// 備考（入荷以降）
		setRemark2(null);
		// 検索リストのクリア
		setDetailList(new ArrayList<AcceptDetailList>());
		// オーダー区分
		setOrderDivision(null);
		// 受入数量合計(自身以外)
		setSumAcceptQuantity(null);
		// 単位
		setUnit(null);
		// 発注番号分納枝番
		setOrderDivideNo(null);
		// 小数点桁数
		setDecimalPoint(null);
		// 端数区分
		setRound(null);
		// 区分
		setKbn(null);
		// 入荷処理対象フラグ
		setNyukaFlg(null);
		// 最終受入データフラグ
		setLastDataFlg(null);
		// IFテーブル更新ステータス
		setIfUpdateStatus("0");
		// 免税計算対象フラグ
		setReducedTaxTargetFlg(null);
	}

	//
	// インスタンスメソッド
	//

	/**
	 * 購買NO取得
	 * @return String 購買NO
	 */
	public String getPurchaseNo() {
		return this.purchaseNo;
	}

	/**
	 * 購買NO設定
	 * @param purchaseNo 購買NO
	 */
	public void setPurchaseNo(final String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

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
	 * 仕入先受注番取得.
	 * @return String 仕入先受注番
	 */
	public String getSiOrderNo() {
		return this.siOrderNo;
	}

	/**
	 * 仕入先受注番設定.
	 * @param siOrderNo 仕入先受注番
	 */
	public void setSiOrderNo(final String siOrderNo) {
		this.siOrderNo = siOrderNo;
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
	 * 発注数量取得.
	 * @return String 発注数量
	 */
	public String getOrderQuantity() {
		return this.orderQuantity;
	}

	/**
	 * 発注数量設定.
	 * @param orderQuantity 発注数量
	 */
	public void setOrderQuantity(final String orderQuantity) {
		this.orderQuantity = orderQuantity;
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
	 * 発注残数量を取得します。
	 * @return String 発注残数量
	 */
	public String getBalanceOrderQuantity() {
		return balanceOrderQuantity;
	}

	/**
	 * 発注残数量を設定します。
	 * @param balanceOrderQuantity 発注残数量
	 */
	public void setBalanceOrderQuantity(final String balanceOrderQuantity) {
		this.balanceOrderQuantity = balanceOrderQuantity;
	}

	/**
	 * 完納区分を取得します。
	 * @return boolean 完納区分
	 */
	public boolean getDeliveryComp() {
		return deliveryComp;
	}

	/**
	 * 完納区分を設定します。
	 * @param deliveryComp 完納区分
	 */
	public void setDeliveryComp(final boolean deliveryComp) {
		this.deliveryComp = deliveryComp;
	}

	/**
	 * 分類コードを取得します。
	 * @return String 分類コード
	 */
	public String getCategoryDivision() {
		return categoryDivision;
	}

	/**
	 * 分類コードを設定します。
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
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
	 * 購買ステータス取得.
	 * @return String 購買ステータス
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * 購買ステータス設定.
	 * @param status 購買ステータス
	 */
	public void setStatus(final String status) {
		this.status = status;
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
	 * 次回納品希望日取得.
	 * @return String 次回納品希望日
	 */
	public String getNextDeliverlimitDate() {
		return this.nextDeliverlimitDate;
	}

	/**
	 * 次回納品希望日設定.
	 * @param nextDeliverlimitDate 次回納品希望日
	 */
	public void setNextDeliverlimitDate(final String nextDeliverlimitDate) {
		this.nextDeliverlimitDate = nextDeliverlimitDate;
	}

	/**
	 * 次回納品希望日(時間)取得.
	 * @return String 次回納品希望日(時間)
	 */
	public String getNextDeliverlimitDateTime() {
		return nextDeliverlimitDateTime;
	}

	/**
	 * 次回納品希望日(時間)設定.
	 * @param nextDeliverlimitDateTime 次回納品希望日(時間)
	 */
	public void setNextDeliverlimitDateTime(
			final String nextDeliverlimitDateTime) {
		this.nextDeliverlimitDateTime = nextDeliverlimitDateTime;
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
	 * 発注書備考（入荷以降）取得.
	 * @return String 発注書備考（入荷以降）
	 */
	public String getOrderSheetRemark2() {
		return this.orderSheetRemark2;
	}

	/**
	 * 発注書備考（入荷以降）設定.
	 * @param orderSheetRemark2 発注書備考（入荷以降）
	 */
	public void setOrderSheetRemark2(final String orderSheetRemark2) {
		this.orderSheetRemark2 = orderSheetRemark2;
	}

	/**
	 * 備考（入荷以降）取得.
	 * @return String 備考（入荷以降）
	 */
	public String getRemark2() {
		return this.remark2;
	}

	/**
	 * 備考（入荷以降）設定.
	 * @param remark2 備考（入荷以降）
	 */
	public void setRemark2(final String remark2) {
		this.remark2 = remark2;
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
	 * 詳細リスト(ロット分割)を取得します。
	 * @return String 詳細リスト(ロット分割)
	 */
	public List<AcceptDetailList> getDetailList() {
		return detailList;
	}

	/**
	 * 詳細リスト(ロット分割)を設定します。
	 * @param detailList 詳細リスト(ロット分割)
	 */
	public void setDetailList(final List<AcceptDetailList> detailList) {
		this.detailList = detailList;
	}

	/**
	 * オーダー区分取得.
	 * @return String オーダー区分
	 */
	public String getOrderDivision() {
		return this.orderDivision;
	}

	/**
	 * オーダー区分設定.
	 * @param orderDivision オーダー区分
	 */
	public void setOrderDivision(final String orderDivision) {
		this.orderDivision = orderDivision;
	}

	/**
	 * 受入数量合計(自身以外)取得.
	 * @return String 受入数量合計(自身以外)
	 */
	public String getSumAcceptQuantity() {
		return sumAcceptQuantity;
	}

	/**
	 * 受入数量合計(自身以外)設定.
	 * @param sumAcceptQuantity 受入数量合計(自身以外)
	 */
	public void setSumAcceptQuantity(final String sumAcceptQuantity) {
		this.sumAcceptQuantity = sumAcceptQuantity;
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
	 * 発注番号分納枝番取得
	 * @return String 発注番号分納枝番
	 */
	public String getOrderDivideNo() {
		return this.orderDivideNo;
	}

	/**
	 * 発注番号分納枝番設定
	 * @param orderDivideNo 発注番号分納枝番
	 */
	public void setOrderDivideNo(final String orderDivideNo) {
		this.orderDivideNo = orderDivideNo;
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
	 * 区分取得 (A:原材料(自社)ローリー以外,B:原材料(自社)ローリー,C:原材料(外注),D:外注製品(直送)
	 * ,E:外注製品(非直送),F:仕入直送品,G:スポット品,H:仕入在庫品)
	 * @return String 区分
	 */
	public String getKbn() {
		return kbn;
	}

	/**
	 * 区分設定 (A:原材料(自社)ローリー以外,B:原材料(自社)ローリー,C:原材料(外注),D:外注製品(直送)
	 * ,E:外注製品(非直送),F:仕入直送品,G:スポット品,H:仕入在庫品)
	 * @param kbn 区分
	 */
	public void setKbn(final String kbn) {
		this.kbn = kbn;
	}

	/**
	 * 入荷処理対象フラグ(0:入荷処理対象外,1:入荷処理対象)取得
	 * @return String 入荷処理対象フラグ(0:入荷処理対象外,1:入荷処理対象)
	 */
	public String getNyukaFlg() {
		return nyukaFlg;
	}

	/**
	 * 入荷処理対象フラグ(0:入荷処理対象外,1:入荷処理対象)設定
	 * @param nyukaFlg 入荷処理対象フラグ(0:入荷処理対象外,1:入荷処理対象)
	 */
	public void setNyukaFlg(final String nyukaFlg) {
		this.nyukaFlg = nyukaFlg;
	}

	/**
	 * 最終受入データフラグ取得
	 * @return String 最終受入データフラグ
	 */
	public String getLastDataFlg() {
		return lastDataFlg;
	}

	/**
	 * 最終受入データフラグ設定
	 * @param lastDataFlg 最終受入データフラグ
	 */
	public void setLastDataFlg(final String lastDataFlg) {
		this.lastDataFlg = lastDataFlg;
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
	 * IFテーブル更新ステータス取得
	 * @return String IFテーブル更新ステータス
	 */
	public String getIfUpdateStatus() {
		return ifUpdateStatus;
	}

	/**
	 * IFテーブル更新ステータス設定
	 * @param ifUpdateStatus IFテーブル更新ステータス
	 */
	public void setIfUpdateStatus(final String ifUpdateStatus) {
		this.ifUpdateStatus = ifUpdateStatus;
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
	 * 完納フラグ必須チェック
	 * @param errors 検証エラー内容
	 */
	private void deliveryCompChk(final ActionErrors errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		if (!this.getDeliveryComp()) {
			// チェック無の場合はエラー
			errors.add("", new ActionMessage("errors.required", rb
					.getString("item.accept.delivery.comp")));
		}
	}

	/**
	 * 同一ロット同一ロケーションに複数件の受入データがあってはならない。
	 * @param errors 検証エラーの内容
	 */
	private void chkSameLotLocation(final ActionErrors errors) {
		// 受入詳細を調査する
		List<AcceptDetailList> list = this.getDetailList();
		for (int i = 0; i < list.size() - 1; i++) {
			String dt = list.get(i).getHousingLocationCd()
					+ list.get(i).getLotNo();
			for (int j = i + 1; j < list.size(); j++) {
				String ht = list.get(j).getHousingLocationCd()
						+ list.get(j).getLotNo();
				if (dt.equals(ht)) {
					// 同一ロットが同じロケーションに入庫したらエラー
					errors.add("", new ActionMessage(
							"errors.accept.no.same.lotlocation", j + 1));
				}
			}
		}
	}

	/**
	 * 完納フラグ、次回納品希望日の入力チェック 発注残あり：完納フラグと次回納品希望日のどちらか一方のみ必須
	 * 発注残なし：完納フラグと次回納品希望日のどちらも必須
	 *
	 * @param errors 検証エラー内容
	 */
	private void requiredChk(final ActionErrors errors) {
		String errFlg = "0";
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);
		BigDecimal balance = AecNumberUtils.convertBigDecimal(this
				.getBalanceOrderQuantity());

		// 発注残数量＞０ の場合
		if (balance != null && balance.compareTo(new BigDecimal(0)) > 0) {

			// 完納フラグ、次回納品希望日のどちらも指定されていない場合
			if (!this.getDeliveryComp()
					&& (StringUtils.isEmpty(this.getNextDeliverlimitDate()))) {
				errors.add("", new ActionMessage("errors.accept.comp.nextdate",
						rb.getString("item.accept.delivery.comp"),
						rb.getString("item.accept.next.deliver.limit.date")));
				errFlg = "1";
			}

			// 完納フラグ、次回納品希望日のどちらも指定されている場合
			if (this.getDeliveryComp()
					&& StringUtils.isNotEmpty(this.getNextDeliverlimitDate())) {
				errors.add("", new ActionMessage("errors.accept.comp.nextdate",
						rb.getString("item.accept.delivery.comp"),
						rb.getString("item.accept.next.deliver.limit.date")));
				errFlg = "1";
			}

			// 次回納品希望日が指定されている場合
			if (errFlg.equals("0")
					&& StringUtils.isNotEmpty(this.getNextDeliverlimitDate())) {
				// 日付妥当性チェック
				dateFormatChk(errors);
			}
		} else {

			// 受入登録済みの場合完納フラグチェックと次回納品希望日チェックを行わない
			if (!this.getStatus().equals("6")) {
				// 完納フラグ必須チェック
				deliveryCompChk(errors);

				// 次回納品希望日必須チェック
				if (StringUtils.isNotEmpty(this.getNextDeliverlimitDate())
						|| StringUtils.isNotEmpty(this
								.getNextDeliverlimitDateTime())) {
					errors
							.add(
								"",
								new ActionMessage(
										"errors.accept.next.date.notempty",
										rb
												.getString("item.accept.next.deliver.limit.date")));
				}

			}

		}
	}

	/**
	 * 日付・時刻が正しいかどうかチェックする。
	 *
	 * @param errors 検証エラー内容
	 */
	private void dateFormatChk(final ActionErrors errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		String date = this.getNextDeliverlimitDate();
		String time = this.getNextDeliverlimitDateTime();
		if (StringUtils.isEmpty(time)) {
			time = "00:00";
		}
		date = date.replace("/", "");
		time = time.replace(":", "");

		if (date.length() != 8 || time.length() != 4) {
			errors.add("", new ActionMessage("errors.accept.datetime", rb
					.getString("item.accept.next.deliver.limit.date")));
		} else if (!isMojiChk(date, PTN_NUMERIC_NOCONMA)
				|| !isMojiChk(time, PTN_NUMERIC_NOCONMA)) {
			errors.add("", new ActionMessage("errors.accept.datetime", rb
					.getString("item.accept.next.deliver.limit.date")));
		} else {
			Calendar cal1 = Calendar.getInstance();
			cal1.setLenient(false);
			int year = Integer.parseInt(date.substring(0, 4));
			int month = Integer.parseInt(date.substring(4, 6)) - 1;
			int day = Integer.parseInt(date.substring(6));
			int hour = Integer.parseInt(time.substring(0, 2));
			int minute = Integer.parseInt(time.substring(2));
			cal1.set(year, month, day, hour, minute);

			try {
				cal1.getTime();
				// 存在しない日付のため例外がスローされます。
			} catch (IllegalArgumentException e) {
				errors.add("", new ActionMessage("errors.accept.datetime", rb
						.getString("item.accept.next.deliver.limit.date")));
			}
		}
	}

	/**
	 * 数字チェック<br>
	 * 引数の文字列が全て数字かどうか判定します。
	 * @param str 判定対象文字列
	 * @param ptn 判定パターン文字列
	 * @return 数字ならtrue
	 */
	private boolean isMojiChk(final String str, final String ptn) {

		if (StringUtils.isEmpty(str)) {
			return true;
		}

		for (int i = 0; i < str.length(); i++) {
			if (ptn.indexOf(str.charAt(i)) == -1) {
				return false;
			}
		}

		try {
			String targetStr = StringUtils.replace(str, ",", "");
			new BigDecimal(targetStr);
		} catch (NumberFormatException lexNfe) {
			// 入力された文字列が数値でない場合
			return false;
		}

		return true;
	}

}
