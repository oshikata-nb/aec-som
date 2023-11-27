/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.arrival;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.nonentity.arrival.ArrivalDetailList;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 入荷入力詳細 Formクラス.
 * @author tosco
 */
public final class ArrivalDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/** 入力チェック用：半角数字＋カンマ */
	private static final String PTN_NUMERIC = "0123456789,";

	/** 入力チェック用：半角数字 */
	private static final String PTN_NUMERIC_NOCONMA = "0123456789";

	private String dirtyFlg; /* 変更フラグ */

	/** 入荷処理対象フラグ(0:入荷処理対象外,1:入荷処理対象) */
	private String nyukaFlg;

	//
	// インスタンスフィールド

	/** 購買NO */
	private String purchaseNo;

	/** 発注番号 */
	private String buySubcontractOrderNo;

	/** 仕入先受注番号 */
	private String siOrderNo;

	/** 発注日 */
	private String strOrderDate;

	/** 仕入先コード */
	private String venderCd;

	/** 品目コード */
	private String itemCd;

	/** 発注数量 */
	private String orderQuantity;

	/** 発注数量 */
	private String strOrderQuantity;

	/** 重量 */
	private String strOrderConvertQuantity;

	/** 納品希望日 */
	private String strSuggestedDeliverlimitDate;

	/** 納入ロケーションコード */
	private String locationCd;

	/** 購買ステータス */
	private String status;

	/** 発注書NO */
	private String orderSheetNo;

	/** 次回納品希望日 */
	private String nextDeliverlimitDate;

	/** 次回納品希望日(時間) */
	private String nextDeliverlimitDateTime;

	/** 仕入番号 */
	private String slipNo;

	/** 発注書備考（入荷以降） */
	private String orderSheetRemark2;

	/** 備考（入荷以降） */
	private String remark2;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 品目名称 */
	private String itemQueueName;

	/** 仕入先名称 */
	private String venderName;

	/** 仕入先略称 */
	private String venderShortedName;

	/** 納入先名称 */
	private String locationName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 発注残数量 */
	private String balanceOrderQuantity;

	/** 担当部署名称 */
	private String organizationName;

	/** リスト */
	private List<ArrivalDetailList> detailList;

	/** オーダー区分 */
	private String orderDivision;

	/** 入荷予定数量合計(自身以外) */
	private String sumArrivalQuantity;

	/** 単位 */
	private String unit;

	/** 発注番号分納枝番 */
	private String orderDivideNo;

	/** 小数点桁数 */
	private String decimalPoint;

	/** 端数区分 */
	private String round;

	/** IFテーブル更新ステータス 0:正常 1:異常 */
	private String ifUpdateStatus;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	/** ラベル発行ステータス */
	private String labelHakko;

	/** 排他制御用更新日付 */
	private Timestamp updateDate;

	/**
	 * コンストラクタ.詳細
	 */
	public ArrivalDetailForm() {
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
		if (!"search".equals(getOp())) {
			setExcelDownloadFlg(false);
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

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			if (errors.isEmpty()) {
				// 次回納品希望日時 入力チェック
				requiredChk(errors);
			}
		}

		if ("issue".equals(getOp())) {
			errors = new ActionErrors();
			// 入荷登録済チェック
			chkArrivalRegistered(errors);

			if (errors.isEmpty()) {
				// ラベル発行対象チェック
				chkLabelIssueTarget(errors);
			}

			if (errors.isEmpty()) {
				// ラベル枚数チェック
				validateSearchListISSUE(errors);
			}
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {

		/** 購買NO */
		setPurchaseNo(null);

		/** 発注番号 */
		setBuySubcontractOrderNo(null);

		/** 仕入先受注番 */
		setSiOrderNo(null);

		/** 発注日 */
		setStrOrderDate(null);

		/** 仕入先コード */
		setVenderCd(null);

		/** 品目コード */
		setItemCd(null);

		/** 発注数量 */
		setOrderQuantity(null);
		setStrOrderQuantity(null);

		/** 重量 */
		setStrOrderConvertQuantity(null);

		/** 納品希望日 */
		setStrSuggestedDeliverlimitDate(null);

		/** 納入ロケーションコード */
		setLocationCd(null);

		/** 購買ステータス */
		setStatus(null);

		/** 発注書NO */
		setOrderSheetNo(null);

		/** 次回納品希望日 */
		setNextDeliverlimitDate(null);

		/** 次回納品希望日(時間) */
		setNextDeliverlimitDateTime(null);

		/** 仕入番号 */
		setSlipNo(null);

		/** 発注書備考（入荷以降） */
		setOrderSheetRemark2(null);

		/** 備考（入荷以降） */
		setRemark2(null);

		/** 他社コード１ */
		setOtherCompanyCd1(null);

		/** 品目名称 */
		setItemQueueName(null);

		/** 仕入先名称 */
		setVenderName(null);

		/** 仕入先略称 */
		setVenderShortedName(null);

		/** 納入先名称 */
		setLocationName(null);

		/** 荷姿 */
		setStyleOfPacking(null);

		/** 発注残数量 */
		setBalanceOrderQuantity(null);

		/** 担当部署名称 */
		setOrganizationName(null);

		/** 検索リストのクリア */
		setDetailList(new ArrayList<ArrivalDetailList>());

		/** オーダー区分 */
		setOrderDivision(null);

		/** 入荷予定数量合計(自身以外) */
		setSumArrivalQuantity(null);

		/** 単位 */
		setUnit(null);

		/** 発注番号分納枝番 */
		setOrderDivideNo(null);

		/** 小数点桁数 */
		setDecimalPoint(null);

		/** 端数区分 */
		setRound(null);

		/** 変更フラグ */
		setDirtyFlg(null);

		/** IFテーブル更新ステータス */
		setIfUpdateStatus("0");

		/** EXCELダウンロードフラグ */
		setExcelDownloadFlg(Boolean.FALSE);

		/** 更新日付 */
		setUpdateDate(null);
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
	 * 担当部署名称を取得します。
	 * @return String 担当部署名称
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 担当部署名称を設定します。
	 * @param organizationName 担当部署名称
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
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
	public List<ArrivalDetailList> getDetailList() {
		return detailList;
	}

	/**
	 * 詳細リスト(ロット分割)を設定します。
	 * @param detailList 詳細リスト(ロット分割)
	 */
	public void setDetailList(final List<ArrivalDetailList> detailList) {
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
	 * 入荷予定数量合計(自身以外)取得.
	 * @return String 入荷予定数量合計(自身以外)
	 */
	public String getSumArrivalQuantity() {
		return sumArrivalQuantity;
	}

	/**
	 * 入荷予定数量合計(自身以外)設定.
	 * @param sumArrivalQuantity 入荷予定数量合計(自身以外)
	 */
	public void setSumArrivalQuantity(final String sumArrivalQuantity) {
		this.sumArrivalQuantity = sumArrivalQuantity;
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
	 * EXCELダウンロードフラグを取得します。
	 * @return boolean EXCELダウンロードフラグ
	 */
	public boolean isExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * EXCELダウンロードフラグを設定します。
	 * @param excelDownloadFlg EXCELダウンロードフラグ
	 */
	public void setExcelDownloadFlg(final boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
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
	 * 発注数量＞発注残数量の場合、次回納品希望日時を必須とする
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
			if (StringUtils.isEmpty(this.getNextDeliverlimitDate())) {
				errors.add("", new ActionMessage("errors.required", rb
						.getString("item.arrival.next.deliver.limit.date")));
				errFlg = "1";
			}

			if (errFlg.equals("0")) {
				// 日付妥当性チェック
				dateFormatChk(errors);
			}
		} else {
			if (StringUtils.isNotEmpty(this.getNextDeliverlimitDate())
					|| StringUtils.isNotEmpty(this
							.getNextDeliverlimitDateTime())) {
				errors.add("", new ActionMessage(
						"errors.arrival.next.date.notempty",
						rb.getString("item.arrival.next.deliver.limit.date")));
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
			errors.add("", new ActionMessage("errors.arrival.datetime", rb
					.getString("item.arrival.next.deliver.limit.date")));
		} else if (!isMojiChk(date, PTN_NUMERIC_NOCONMA)
				|| !isMojiChk(time, PTN_NUMERIC_NOCONMA)) {
			errors.add("", new ActionMessage("errors.arrival.datetime", rb
					.getString("item.arrival.next.deliver.limit.date")));
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
				errors.add("", new ActionMessage("errors.arrival.datetime", rb
						.getString("item.arrival.next.deliver.limit.date")));
			}
		}
	}

	/**
	 * ラベル発行時にラベル枚数の入力チェックを行う。
	 * @param errors エラー内容
	 */
	private void validateSearchListISSUE(final ActionErrors errors) {
		List<ArrivalDetailList> list = this.getDetailList();
		int index = 1;

		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		for (ArrivalDetailList bean : list) {
			// ラベル枚数
			// ■数値チェック
			if (!isMojiChk(bean.getLabelCount(), PTN_NUMERIC)) {
				errors.add("", new ActionMessage("errors.number.row", rb
						.getString("item.arrival.label.count"), Integer
						.toString(index)));
			} else {
				// ■数値範囲チェック
				if (!isNumKetaChk(bean.getLabelCount(), 4, 0)) {
					errors.add("", new ActionMessage("errors.rang.row", rb
							.getString("item.arrival.label.count"), "0",
							"9,999", Integer.toString(index)));
				}
			}

			index++;
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

	/**
	 * 数値桁数チェック<br>
	 * 引数の文字列がの整数部・小数部の桁数が、指定された桁数内かどうか判定します。
	 * @param str 判定対象文字列
	 * @param integerNum 整数部桁数
	 * @param point 小数部桁数
	 * @return 桁数範囲内ならtrue
	 */
	private boolean isNumKetaChk(final String str, final int integerNum,
			final int point) {

		if (StringUtils.isEmpty(str)) {
			return false;
		}

		String targetStr = StringUtils.replace(str, ",", "");
		if (point == 0) {
			// 小数点なし
			if (integerNum < targetStr.length()) {
				return false;
			}
		} else {
			int index = targetStr.indexOf(".");
			if (index > -1) {
				// 小数点あり
				String seisu = targetStr.substring(0, index);
				String shosu = targetStr.substring(index + 1);
				if (integerNum < seisu.length()) {
					return false;
				}
				if (point < shosu.length()) {
					return false;
				}
			} else {
				// 小数点なし
				if (integerNum < targetStr.length()) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 入荷登録済チェック
	 * 
	 * @param errors 検証エラー内容
	 */
	private void chkArrivalRegistered(final ActionErrors errors) {
		// 入荷登録済チェック
		if (!PurchaseStatus.ARRIVALED.toString().equals(this.getStatus())) {
			errors.add("", new ActionMessage("errors.arrival.no.regist"));
		}
	}

	/**
	 * ラベル発行対象チェック
	 * 
	 * @param errors 検証エラー内容
	 */
	private void chkLabelIssueTarget(final ActionErrors errors) {
		if ((this.detailList != null) && (!this.detailList.isEmpty())) {
			// ロット管理しないので、ラベル発行不可
			if (StringUtils.isEmpty(this.detailList.get(0).getLotNo())
					|| this.detailList.get(0).getLotNo().equals(
						Constants.LOTNO_WITHOUT_LOT)) {
				errors.add("", new ActionMessage(
						"errors.arrival.no.target.issue"));
			}
		}
	}

	/**
	 * labelHakkoを取得します。
	 * @return labelHakko
	 */
	public String getLabelHakko() {
		return labelHakko;
	}

	/**
	 * labelHakkoを設定します。
	 * @param labelHakko labelHakko
	 */
	public void setLabelHakko(final String labelHakko) {
		this.labelHakko = labelHakko;
	}

	/**
	 * updateDataを取得します。
	 * @return updateData
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDataを設定します。
	 * @param updateData updateData
	 */
	public void setUpdateDate(final Timestamp updateData) {
		this.updateDate = updateData;
	}

}
