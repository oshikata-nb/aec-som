/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shippingresult;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitResult;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.shipping.ShippingConst;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultDetailList;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 出荷実績詳細 Formクラス.
 * @author tosco
 */
public final class ShippingResultDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/** 入力チェック用：実績量の最小値 */
	private static final BigDecimal SHIPPING_RESULT_QUANTITY_MIN = BigDecimal.ZERO;

	//
	// インスタンスフィールド

	/** 変更フラグ * */
	private String dirtyFlg;

	/** 出荷番号 */
	private String shippingNo;

	/** 受注番号 */
	private String orderNo;

	/** 行番号(受注) */
	private String orderRowNo;

	/** 出荷ステータス */
	private String shippingStatus;

	/** 出荷ステータス名称 */
	private String shippingStatusName;

	/** 出荷指図日 */
	private String strShippingInstructDate;

	/** 納入先コード */
	private String deliveryCd;

	/** 納入先名称 */
	private String deliveryName1;

	/** 納入先略称 */
	private String searchKana;

	/** 納入先宛先 */
	private String deliveryAddress;

	/** 品名コード */
	private String itemCd;

	/** 品名名称 */
	private String itemName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 帳合コード */
	private String balanceCd;

	/** 希望納期(文字列) */
	private String strSuggestedDeliverlimit;

	/** 出荷予定日(文字列) */
	private String strScheduledShippingDate;

	/** 運送会社コード */
	private String carryCd;

	/** 運送会社名称 */
	private String carryName;

	/** 運賃(文字列) */
	private String strCarryFare;

	/** 出荷完了日(文字列) */
	private String strShippingResultDate;

	/** 受注数量(文字列) */
	private String strOrderQty;

	/** 増付数(文字列) */
	private String strMatss;

	/** 指図量累計(文字列) */
	private String strShippingInstructionSum;

	/** 実績量累計(文字列) */
	private String strShippingResultQuantitySum;

	/** 取引先コード */
	private String venderCd;

	/** ロケーション選択行インデックス */
	private int lineIndex;

	/** 単位区分 */
	private String unitDivision;

	/** 小数点桁数 */
	private String decimalPoint;

	/** 端数区分 */
	private String round;

	/** 単位区分 */
	private String unitDivisionUntin;

	/** 小数点桁数 */
	private String decimalPointUntin;

	/** 端数区分 */
	private String roundUntin;

	/** 出荷実績詳細リスト */
	private List<ShippingResultDetailList> detailList;

	/** 出荷ヘッダー更新日付 */
	private Timestamp updateDate;

	/** ログ出力用エラーコード */
	private String errorCd;

	/** ログ出力用エラーメッセージ */
	private String errorMsg;

	/** 納入先住所 */
	private String deliveryAllAddress;

	/** 納入予定日 */
	private String strDeliveryExpectedDate;

	/**
	 * コンストラクタ.出荷実績詳細（花王・その他）
	 */
	public ShippingResultDetailForm() {
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
		} else if ("addRow".equals(getOp())) {
			clearCheck();
		} else if ("delRow".equals(getOp())) {
			clearCheck();
		} else if ("update".equals(getOp())) {
			clearCheck();
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
			// Validatorによる判定
			errors = super.validate(mapping, request);
			// 明細のチェック
			validateDetail(errors, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	protected void clear() {
		// 変更フラグ
		setDirtyFlg(null);
		// 出荷伝票番号
		setShippingNo(null);
		// 受注番号
		setOrderNo(null);
		// 行番号(受注)
		setOrderRowNo(null);
		// 出荷ステータス
		setShippingStatus(null);
		// 出荷ステータス名称
		setShippingStatusName(null);
		// 出荷指図日
		setStrShippingInstructDate(null);
		// 納入先コード
		setDeliveryCd(null);
		// 納入先名称
		setDeliveryName1(null);
		// 納入先略称
		setSearchKana(null);
		// 納入先宛先
		setDeliveryAddress(null);
		// 品名コード
		setItemCd(null);
		// 品名名称
		setItemName(null);
		// 荷姿
		setStyleOfPacking(null);
		// 他社コード1
		setOtherCompanyCd1(null);
		// 帳合コード
		setBalanceCd(null);
		// 希望納期(文字列)
		setStrSuggestedDeliverlimit(null);
		// 出荷予定日(文字列)
		setStrScheduledShippingDate(null);
		// 運送会社コード
		setCarryCd(null);
		// 運送会社名称
		setCarryName(null);
		// 運賃(文字列)
		setStrCarryFare(null);
		// 出荷完了日(文字列)
		setStrShippingResultDate(null);
		// 受注数量(文字列)
		setStrOrderQty(null);
		// 増付数(文字列)
		setStrMatss(null);
		// 指図量累計(文字列)
		setStrShippingInstructionSum(null);
		// 実績量累計(文字列)
		setStrShippingResultQuantitySum(null);
		// 詳細情報リストのクリア
		setDetailList(new ArrayList<ShippingResultDetailList>());
		// ロケーション選択行インデックス
		setLineIndex(-1);
		// 取引先コード
		setVenderCd(null);
		// 単位区分
		setUnitDivision(null);
		// 小数点桁数
		setDecimalPoint(null);
		// 端数区分
		setRound(null);
		// 単位区分(運賃)
		setUnitDivisionUntin(null);
		// 小数点桁数(運賃)
		setDecimalPointUntin(null);
		// 端数区分(運賃)
		setRoundUntin(null);
		// 出荷ヘッダー更新日付
		setUpdateDate(null);
		// エラーコード
		setErrorCd(null);
		// エラーメッセージ
		setErrorMsg(null);
		// 納入先住所
		setDeliveryAllAddress(null);

		// 納入予定日
		setStrDeliveryExpectedDate(null);
	}

	//
	// インスタンスメソッド
	//

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
	 * 出荷番号取得.
	 * @return String 出荷番号
	 */
	public String getShippingNo() {
		return this.shippingNo;
	}

	/**
	 * 出荷番号設定.
	 * @param shippingNo 出荷番号
	 */
	public void setShippingNo(final String shippingNo) {
		this.shippingNo = shippingNo;
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
	 * 行番号(受注)取得.
	 * @return String 行番号(受注)
	 */
	public String getOrderRowNo() {
		return this.orderRowNo;
	}

	/**
	 * 行番号(受注)設定.
	 * @param orderRowNo 行番号(受注)
	 */
	public void setOrderRowNo(final String orderRowNo) {
		this.orderRowNo = orderRowNo;
	}

	/**
	 * 出荷ステータス取得.
	 * @return String 出荷ステータス
	 */
	public String getShippingStatus() {
		return this.shippingStatus;
	}

	/**
	 * 出荷ステータス設定.
	 * @param shippingStatus 出荷ステータス
	 */
	public void setShippingStatus(final String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	/**
	 * 出荷ステータス名称取得.
	 * @return String 出荷ステータス名称
	 */
	public String getShippingStatusName() {
		return this.shippingStatusName;
	}

	/**
	 * 出荷ステータス名称設定.
	 * @param shippingStatusName 出荷ステータス名称
	 */
	public void setShippingStatusName(final String shippingStatusName) {
		this.shippingStatusName = shippingStatusName;
	}

	/**
	 * 出荷指図日取得.
	 * @return String 出荷指図日
	 */
	public String getStrShippingInstructDate() {
		return this.strShippingInstructDate;
	}

	/**
	 * 出荷指図日設定.
	 * @param strShippingInstructDate 出荷指図日
	 */
	public void setStrShippingInstructDate(final String strShippingInstructDate) {
		this.strShippingInstructDate = strShippingInstructDate;
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
	 * 納入先宛先取得
	 * @return String 納入先宛先
	 */
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	/**
	 * 納入先宛先設定
	 * @param deliveryAddress 納入先宛先
	 */
	public void setDeliveryAddress(final String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * 品目コード取得
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称取得
	 * @return String 品目名称
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 品目名称設定
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
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
	 * 希望納期(文字列)取得.
	 * @return String 希望納期(文字列)
	 */
	public String getStrSuggestedDeliverlimit() {
		return this.strSuggestedDeliverlimit;
	}

	/**
	 * 希望納期(文字列)設定.
	 * @param strSuggestedDeliverlimit 希望納期(文字列)
	 */
	public void setStrSuggestedDeliverlimit(
			final String strSuggestedDeliverlimit) {
		this.strSuggestedDeliverlimit = strSuggestedDeliverlimit;
	}

	/**
	 * 出荷予定日(文字列)取得.
	 * @return String 出荷予定日(文字列)
	 */
	public String getStrScheduledShippingDate() {
		return this.strScheduledShippingDate;
	}

	/**
	 * 出荷予定日(文字列)設定.
	 * @param strScheduledShippingDate 出荷予定日(文字列)
	 */
	public void setStrScheduledShippingDate(
			final String strScheduledShippingDate) {
		this.strScheduledShippingDate = strScheduledShippingDate;
	}

	/**
	 * 運送会社コード取得.
	 * @return String 運送会社コード
	 */
	public String getCarryCd() {
		return this.carryCd;
	}

	/**
	 * 運送会社コード設定.
	 * @param carryCd 運送会社コード
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * 運送会社名称取得.
	 * @return String 運送会社名称
	 */
	public String getCarryName() {
		return this.carryName;
	}

	/**
	 * 運送会社名称設定.
	 * @param carryName 運送会社名称
	 */
	public void setCarryName(final String carryName) {
		this.carryName = carryName;
	}

	/**
	 * 運賃(文字列)取得
	 * @return String 運賃(文字列)
	 */
	public String getStrCarryFare() {
		return this.strCarryFare;
	}

	/**
	 * 運賃(文字列)設定
	 * @param strCarryFare 運賃(文字列)
	 */
	public void setStrCarryFare(final String strCarryFare) {
		this.strCarryFare = strCarryFare;
	}

	/**
	 * 出荷完了日(文字列)取得.
	 * @return String 出荷完了日(文字列)
	 */
	public String getStrShippingResultDate() {
		return this.strShippingResultDate;
	}

	/**
	 * 出荷完了日(文字列)設定.
	 * @param strShippingResultDate 出荷完了日(文字列)
	 */
	public void setStrShippingResultDate(final String strShippingResultDate) {
		this.strShippingResultDate = strShippingResultDate;
	}

	/**
	 * 受注数量(文字列)を取得します。
	 * @return String 受注数量(文字列)
	 */
	public String getStrOrderQty() {
		return strOrderQty;
	}

	/**
	 * 受注数量(文字列)を設定します。
	 * @param strOrderQty 受注数量(文字列)
	 */
	public void setStrOrderQty(final String strOrderQty) {
		this.strOrderQty = strOrderQty;
	}

	/**
	 * 増付数(文字列)を取得します。
	 * @return String 増付数(文字列)
	 */
	public String getStrMatss() {
		return strMatss;
	}

	/**
	 * 増付数(文字列)を設定します。
	 * @param strMatss 増付数(文字列)
	 */
	public void setStrMatss(final String strMatss) {
		this.strMatss = strMatss;
	}

	/**
	 * 指図量累計(文字列)取得
	 * @return BigDecimal 指図量累計(文字列)
	 */
	public String getStrShippingInstructionSum() {
		return this.strShippingInstructionSum;
	}

	/**
	 * 指図量累計(文字列)設定
	 * @param strShippingInstructionSum 指図量累計(文字列)
	 */
	public void setStrShippingInstructionSum(
			final String strShippingInstructionSum) {
		this.strShippingInstructionSum = strShippingInstructionSum;
	}

	/**
	 * 実績量累計(文字列)取得
	 * @return BigDecimal 実績量累計(文字列)
	 */
	public String getStrShippingResultQuantitySum() {
		return this.strShippingResultQuantitySum;
	}

	/**
	 * 実績量累計(文字列)設定
	 * @param strShippingResultQuantitySum 実績量累計(文字列)
	 */
	public void setStrShippingResultQuantitySum(
			final String strShippingResultQuantitySum) {
		this.strShippingResultQuantitySum = strShippingResultQuantitySum;
	}

	/**
	 * 取引先コード取得
	 * @return String 取引先コード
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 取引先コード設定
	 * @param venderCd 取引先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * ロケーション選択インデックスを取得します。
	 * @return int ロケーション選択インデックス
	 */
	public int getLineIndex() {
		return lineIndex;
	}

	/**
	 * ロケーション選択インデックスを設定します。
	 * @param lineIndex ロケーション選択インデックス
	 */
	public void setLineIndex(final int lineIndex) {
		this.lineIndex = lineIndex;
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
	 * 単位区分(運賃)
	 * @return String 単位区分(運賃)
	 */
	public String getUnitDivisionUntin() {
		return this.unitDivisionUntin;
	}

	/**
	 * 単位区分(運賃)
	 * @param unitDivisionUntin 単位区分(運賃)
	 */
	public void setUnitDivisionUntin(final String unitDivisionUntin) {
		this.unitDivisionUntin = unitDivisionUntin;
	}

	/**
	 * 小数点桁数(運賃)取得
	 * @return String 小数点桁数(運賃)
	 */
	public String getDecimalPointUntin() {
		return decimalPointUntin;
	}

	/**
	 * 小数点桁数(運賃)設定
	 * @param decimalPointUntin 小数点桁数(運賃)
	 */
	public void setDecimalPointUntin(final String decimalPointUntin) {
		this.decimalPointUntin = decimalPointUntin;
	}

	/**
	 * 端数区分(運賃)取得
	 * @return String 端数区分(運賃)
	 */
	public String getRoundUntin() {
		return roundUntin;
	}

	/**
	 * 端数区分(運賃)設定
	 * @param roundUntin 端数区分(運賃)
	 */
	public void setRoundUntin(final String roundUntin) {
		this.round = roundUntin;
	}

	/**
	 * 出荷実績詳細：detailListを取得します。
	 * @return itemList
	 */
	public List<ShippingResultDetailList> getDetailList() {
		return detailList;
	}

	/**
	 * 出荷実績詳細：detailListを設定します。
	 * 
	 * @param detailList detailList
	 */
	public void setDetailList(final List<ShippingResultDetailList> detailList) {
		this.detailList = detailList;
	}

	/**
	 * 出荷ヘッダー更新日付取得.
	 * @return Timestamp 出荷ヘッダー更新日付
	 */
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 出荷ヘッダー更新日付設定.
	 * @param updateDate 出荷ヘッダー更新日付
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 詳細データ数を取得する。
	 * @return 詳細データ数
	 */
	public int getDetailCount() {
		int count = 0;
		if (detailList != null) {
			count = detailList.size();
		}
		return count;
	}

	/**
	 * エラーコードの取得.
	 * @return String エラーコード
	 */
	public String getErrorCd() {
		return errorCd;
	}

	/**
	 * エラーコードの設定.
	 * @param errorCd エラーコード
	 */
	public void setErrorCd(final String errorCd) {
		this.errorCd = errorCd;
	}

	/**
	 * エラーメッセージ取得.
	 * @return String エラーメッセージ
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * エラーメッセージ設定
	 * @param errorMsg エラーメッセージ
	 */
	public void setErrorMsg(final String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * deliveryAllAddress取得
	 * @return deliveryAllAddress deliveryAllAddress
	 */
	public String getDeliveryAllAddress() {
		return deliveryAllAddress;
	}

	/**
	 * deliveryAllAddress設定
	 * @param deliveryAllAddress deliveryAllAddress
	 */
	public void setDeliveryAllAddress(final String deliveryAllAddress) {
		this.deliveryAllAddress = deliveryAllAddress;
	}

	/**
	 * 納入予定日取得
	 * @return String 納入予定日
	 */
	public String getStrDeliveryExpectedDate() {
		return strDeliveryExpectedDate;
	}

	/**
	 * 納入予定日設定
	 * @param strDeliveryExpectedDate 納入予定日
	 */
	public void setStrDeliveryExpectedDate(final String strDeliveryExpectedDate) {
		this.strDeliveryExpectedDate = strDeliveryExpectedDate;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getDetailList() != null) {
			for (ShippingResultDetailList detail : getDetailList()) {
				detail.setCheckFlg(Boolean.FALSE);
				if (ShippingResultConst.SHIPPING_STATUS_COMPLETE.toString()
						.equals(getShippingStatus())) {
					// 出荷完了の場合、完納とする
					detail.setDeliveryCompFlag(Boolean.TRUE);
				} else {
					detail.setDeliveryCompFlag(Boolean.FALSE);
				}
			}
		}
	}

	/**
	 * 入力データ（詳細情報リスト）の検証（更新時）
	 * 
	 * @param errors 検証エラー内容
	 * @param request HttpServletRequest
	 */
	private void validateDetail(final ActionErrors errors,
			final HttpServletRequest request) {
		// 出荷ステータスが「5:出荷完了」ではない場合のみ、チェックする
		if (!ShippingResultConst.SHIPPING_STATUS_COMPLETE.toString().equals(
			getShippingStatus())) {
			// 明細リスト
			List<ShippingResultDetailList> detailList = getDetailList();

			if (detailList == null || detailList.size() == 0) {
				// 明細が1件も存在しない場合、エラーとする
				errors.add("", new ActionMessage(
						"errors.shippingresult.required.detail"));
			} else {
				// 明細数分チェック
				int detailLineNo = 0;
				for (ShippingResultDetailList detailBean : detailList) {
					detailLineNo++;
					// ロケーションコード
					validateLocationCd(detailBean.getLocationCd(),
						detailLineNo, errors);
					// 実績量
					validateShippingResultQuantity(detailBean,
						getUnitDivision(), ShippingConst.VENDER_DIVISION_TS,
						getVenderCd(), detailLineNo, request, errors);
					// 出荷日
					validateShippingResultDate(detailBean
							.getStrShippingResultDate(), detailBean
							.getStrShippingResultQuantity(), detailLineNo,
						errors);
				}
			}
		}
	}

	/**
	 * ロケーションの検証(行番号指定)
	 * @param locationCd ロケーションコード
	 * @param lineNo 行番号
	 * @param errors 検証エラー内容
	 */
	private void validateLocationCd(final String locationCd, final int lineNo,
			final ActionErrors errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		// 必須チェック
		if (StringUtils.isEmpty(locationCd)) {
			errors.add("", new ActionMessage("errors.required.row", rb
					.getString("item.shippingresult.location"), Integer
					.toString(lineNo)));
		}
	}

	/**
	 * 実績量の検証
	 * @param detail 出荷実績詳細データ
	 * @param unitDivision 単位区分
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @param message 置換文字列
	 * @param request HttpServletRequest
	 * @param errors 検証エラー内容
	 */
	private void validateShippingResultQuantity(
			final ShippingResultDetailList detail, final String unitDivision,
			final String venderDivision, final String venderCd,
			final int lineNo, final HttpServletRequest request,
			final ActionErrors errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);
		String strValue = detail.getStrShippingResultQuantity();
		// 必須チェック(数量がない場合チェック処理無し
		if (StringUtils.isEmpty(strValue)) {
			// errors.add("", new ActionMessage("errors.shipping.required.row",
			// rb
			// .getString("item.shippingresult.shipping.result.quantity"),
			// Integer.toString(lineNo)));
			return;
		}
		String value = StringUtils.replace(strValue, ",", "");
		// 数値チェック
		if (!StringUtils.isEmpty(strValue)) {
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);
			CheckDigitResult result = check.checkDigit(unitDivision,
				venderDivision, venderCd, value);
			int code = result.getCode();
			if (code != CheckDigitUtilsLogic.SUCCESS) {
				NumberChkDisitDetail detailDisit = result.getDetail();
				switch (code) {
				case CheckDigitUtilsLogic.ERROR_NUMBER_FORMAT:
					errors
							.add(
								"",
								new ActionMessage(
										"errors.digit.number.row",
										rb
												.getString("item.shippingresult.shipping.result.quantity"),
										Integer.toString(lineNo)));
					break;
				case CheckDigitUtilsLogic.ERROR_NUMBER_MAX_LENGTH:
					errors
							.add(
								"",
								new ActionMessage(
										"errors.digit.maxlength.row",
										rb
												.getString("item.shippingresult.shipping.result.quantity"),
										detailDisit.getMaxLength().toString(),
										Integer.toString(lineNo)));
					break;
				case CheckDigitUtilsLogic.ERROR_NUMBER_INTEGER_LENGTH:
					errors
							.add(
								"",
								new ActionMessage(
										"errors.digit.integer.row",
										rb
												.getString("item.shippingresult.shipping.result.quantity"),
										detailDisit.getIntegerLength()
												.toString(), Integer
												.toString(lineNo)));
					break;
				case CheckDigitUtilsLogic.ERROR_NUMBER_SMALLNUM_LENGTH:
					errors
							.add(
								"",
								new ActionMessage(
										"errors.digit.decimal.row",
										rb
												.getString("item.shippingresult.shipping.result.quantity"),
										detailDisit.getSmallnumLength()
												.toString(), Integer
												.toString(lineNo)));
					break;
				case CheckDigitUtilsLogic.ERROR_NUMBER_RANGE:
					errors
							.add(
								"",
								new ActionMessage(
										"errors.sales.rang.quantity.row",
										rb
												.getString("item.shippingresult.shipping.result.quantity"),
										check
												.format(
													unitDivision,
													ShippingResultConst.VENDER_DIVISION_TS,
													venderCd,
													SHIPPING_RESULT_QUANTITY_MIN),
										detailDisit.getUpperLimit().toString(),
										Integer.toString(lineNo)));
					break;
				default:
					break;
				}
			} else {
				// 入力可能数範囲チェック
				// 範囲チェック(0より大きい値となっているかチェック)
				BigDecimal val = AecNumberUtils.convertBigDecimal(value);
				if (val.compareTo(SHIPPING_RESULT_QUANTITY_MIN) < 0) {
					NumberChkDisitDetail checkDetail = check.getCheckDigit(
						unitDivision, venderDivision, venderCd);
					errors.add("", new ActionMessage(
							"errors.shippingresult.digit.rang.row",
							check.format(unitDivision, venderDivision,
								venderCd, SHIPPING_RESULT_QUANTITY_MIN),
							checkDetail.getUpperLimit().toString(), Integer
									.toString(lineNo)));
				}

				// 出荷実績可能数量 = 手持在庫
				BigDecimal inventoryQty = AecNumberUtils
						.convertNullToZero(AecNumberUtils
								.convertBigDecimal(detail.getStrInventoryQty()));
				// 変更の場合、元の実績量分を加算する
				BigDecimal prev = AecNumberUtils
						.convertNullToZero(AecNumberUtils
								.convertBigDecimal(detail
										.getStrShippingResultQuantityPrev()));
				BigDecimal possible = inventoryQty.add(prev);
				// 丸め処理
				BigDecimal possibleRd = check.round(unitDivision,
					venderDivision, venderCd, possible);

				// 範囲チェック(可能数以下となっているかチェック)
				if (val.compareTo(possibleRd) > 0) {
					errors.add("", new ActionMessage(
							"errors.shippingresult.rang.max.row", Integer
									.toString(lineNo)));
				}
			}
		}
	}

	/**
	 * 出荷日の検証(行番号指定)
	 * @param shippingResultDate 出荷日
	 * @param shippingRresultQty 出荷実績数量
	 * @param lineNo 行番号
	 * @param errors 検証エラー内容
	 */
	private void validateShippingResultDate(final String shippingResultDate,
			final String shippingRresultQty, final int lineNo,
			final ActionErrors errors) {

		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		// 出荷日がNullでは無い場合 入力データチェック NULLの場合はチェック無し
		if (!StringUtils.isEmpty(shippingResultDate)) {
			Timestamp ymd = AecDateUtils
					.getTimestampYmdFormat(shippingResultDate);
			if (ymd == null) {
				errors
						.add(
							"",
							new ActionMessage(
									"errors.shippingresult.date.row",
									rb
											.getString("item.shippingresult.shipping.result.date"),
									Integer.toString(lineNo)));
			}
		}

		// 出荷実績数量が0では無い場合出荷日のＮｕｌｌチェック
		if (!StringUtils.isEmpty(shippingRresultQty)) {
			String value = StringUtils.replace(shippingRresultQty, ",", "");
			BigDecimal val = AecNumberUtils.convertBigDecimal(value);
			// 実績量０以外の場合
			if (val == null || val.compareTo(SHIPPING_RESULT_QUANTITY_MIN) != 0) {

				// 出荷日のＮｕｌｌチェック
				if (StringUtils.isEmpty(shippingResultDate)) {
					// 出荷日
					errors
							.add(
								"",
								new ActionMessage(
										"errors.required.row",
										rb
												.getString("item.shippingresult.shipping.result.date"),
										Integer.toString(lineNo)));
				}

			}
		}
	}
}
