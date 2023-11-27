/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitResult;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailEntity;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailList;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 出荷指図詳細-共通抽象 Formクラス.
 * @author tosco
 */
public abstract class AbstractShippingDetailForm extends AbstractForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 入力チェック用：指図量の最小値 */
	private static final BigDecimal SHIPPING_INSTRUCTION_MIN = BigDecimal.ZERO;

	/** 変更フラグ **/
	private String dirtyFlg;

	//
	// インスタンスフィールド

	/** 新規用切替フラグ */
	private int insertFlg;

	/** 出荷番号 */
	private String shippingNo;

	/** 出荷指図日 */
	private String strShippingInstructDate;

	/** 受注番号 */
	private String orderNo;

	/** 行番号(受注) */
	private String orderRowNo;

	/** 出荷ステータス */
	private String shippingStatus;

	/** 出荷ステータス名称 */
	private String shippingStatusName;

	/** 取引先コード */
	private String venderCd;

	/** 納入先コード */
	private String deliveryCd;

	/** 納入先名称 */
	private String deliveryName1;

	/** 品名コード */
	private String itemCd;

	/** 品名名称 */
	private String itemName;

	/** 出荷予定日(文字列) */
	private String strScheduledShippingDate;

	/** 運送会社コード */
	private String carryCd;

	/** 運賃(文字列) */
	private String strCarryFare;

	/** 希望納期(文字列) */
	private String strSuggestedDeliverlimit;

	/** 運送会社コンボボックス */
	private List<ComboBoxItems> carryCombo;

	/** ロケーション選択行インデックス */
	private int lineIndex;

	/** 出荷指図ヘッダ検索結果 */
	private ShippingDetailEntity shippingBean;

	/** フォーカスフィールドID */
	private String focusId;

	/** 納入先住所 */
	private String deliveryAllAddress;


	/**
	 * クリア処理.
	 */
	protected void clear() {
		/** 出荷伝票番号 */
		setShippingNo(null);

		/** 出荷指図日 */
		setStrShippingInstructDate(null);

		/** 受注番号 */
		setOrderNo(null);

		/** 行番号(受注) */
		setOrderRowNo(null);

		/** 取引先コード */
		setVenderCd(null);

		/** 納入先コード */
		setDeliveryCd(null);

		/** 納入先名称 */
		setDeliveryName1(null);

		/** 品名コード */
		setItemCd(null);

		/** 品名名称 */
		setItemName(null);

		/** 出荷ステータス */
		setShippingStatus(null);

		/** 出荷ステータス名称 */
		setShippingStatusName(null);

		/** 出荷予定日(文字列) */
		setStrScheduledShippingDate(null);

		/** 運送会社コード */
		setCarryCd(null);

		/** 運賃(文字列) */
		setStrCarryFare(null);

		/** 希望納期(文字列) */
		setStrSuggestedDeliverlimit(null);

		/** 出荷指図ヘッダ検索結果 */
		setShippingBean(new ShippingDetailEntity());

		/** ロケーション選択行インデックス */
		setLineIndex(-1);

		/** フォーカスフィールドID */
		setFocusId(null);

		/** 納入先住所 */
		setDeliveryAllAddress(null);

	}

	//
	// インスタンスメソッド
	//

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
	public void setStrScheduledShippingDate(final String strScheduledShippingDate) {
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
	public void setStrSuggestedDeliverlimit(final String strSuggestedDeliverlimit) {
		this.strSuggestedDeliverlimit = strSuggestedDeliverlimit;
	}

	/**
	 * 出荷指図ヘッダ検索結果取得.
	 * @return String 出荷指図ヘッダ検索結果
	 */
	public ShippingDetailEntity getShippingBean() {
		return this.shippingBean;
	}

	/**
	 * 出荷指図ヘッダ検索結果設定.
	 * @param shippingBean 出荷指図ヘッダ検索結果
	 */
	public void setShippingBean(final ShippingDetailEntity shippingBean) {
		this.shippingBean = shippingBean;
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
	 * フォーカスフィールドID取得.
	 * @return String フォーカスフィールドID結果
	 */
	public String getFocusId() {
		return this.focusId;
	}

	/**
	 * フォーカスフィールドID設定.
	 * @param focusId フォーカスフィールドID結果
	 */
	public void setFocusId(final String focusId) {
		this.focusId = focusId;
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
	 * ロケーションの検証(行番号指定)
	 * @param locationCd ロケーションコード
	 * @param lineNo 行番号
	 * @param errors 検証エラー内容
	 */
	protected void validateLocationCd(final String locationCd, final int lineNo,
			final ActionErrors errors) {
		ResourceBundle rb = ResourceBundle.getBundle(Constants.APPLICATION_PROPERTIES);

		// 必須チェック
		if (StringUtils.isEmpty(locationCd)) {
			errors.add(""
				, new ActionMessage("errors.required.row",
					rb.getString("item.shipping.location"), Integer.toString(lineNo)));
		}
	}

	/**
	 * 指図量の検証
	 * @param detail 出荷指図詳細データ
	 * @param unitDivision 単位区分
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @param message 置換文字列
	 * @param request HttpServletRequest
	 * @param errors 検証エラー内容
	 */
	protected void validateShippingInstruction(final ShippingDetailList detail,
			final String unitDivision, final String venderDivision, final String venderCd,
			final String message,
			final HttpServletRequest request, final ActionErrors errors) {
		ResourceBundle rb = ResourceBundle.getBundle(Constants.APPLICATION_PROPERTIES);
		String strShippingInstruction = detail.getStrShippingInstruction();
		// 必須チェック
		if (StringUtils.isEmpty(strShippingInstruction)) {
			errors.add(""
				, new ActionMessage("errors.shipping.required.row",
						rb.getString("item.shipping.shipping.instruction"),
						message));
		}
		String value = StringUtils.replace(strShippingInstruction, ",", "");
		// 数値チェック
		if (!StringUtils.isEmpty(strShippingInstruction)) {
			CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);
			CheckDigitResult result = check.checkDigit(unitDivision, venderDivision, venderCd, value);
			int code = result.getCode();
			if (code != CheckDigitUtilsLogic.SUCCESS) {
				NumberChkDisitDetail detailDisit = result.getDetail();
                switch(code) {
                case CheckDigitUtilsLogic.ERROR_NUMBER_FORMAT:
					errors.add(""
						, new ActionMessage("errors.shipping.digit.number.row",
							rb.getString("item.shipping.shipping.instruction"),
							message));
					break;
                case CheckDigitUtilsLogic.ERROR_NUMBER_MAX_LENGTH:
					errors.add(""
						, new ActionMessage("errors.shipping.digit.maxlength.row",
							rb.getString("item.shipping.shipping.instruction"),
							detailDisit.getMaxLength().toString(),
							message));
					break;
                case CheckDigitUtilsLogic.ERROR_NUMBER_INTEGER_LENGTH:
					errors.add(""
						, new ActionMessage("errors.shipping.digit.integer.row",
							rb.getString("item.shipping.shipping.instruction"),
							detailDisit.getIntegerLength().toString(),
							message));
					break;
				case CheckDigitUtilsLogic.ERROR_NUMBER_SMALLNUM_LENGTH:
					errors.add(""
						, new ActionMessage("errors.shipping.digit.decimal.row",
							rb.getString("item.shipping.shipping.instruction"),
							detailDisit.getSmallnumLength().toString(),
							message));
					break;
				case CheckDigitUtilsLogic.ERROR_NUMBER_RANGE:
					errors.add(""
						, new ActionMessage("errors.shipping.digit.rang.row",
							check.format(unitDivision,
								venderDivision, venderCd, SHIPPING_INSTRUCTION_MIN),
							detailDisit.getUpperLimit().toString(),
							message));
					break;
				default:
					break;
				}
			} else {
				//入力可能数範囲チェック
				//範囲チェック(0より大きい値となっているかチェック)
				BigDecimal val = AecNumberUtils.convertBigDecimal(value);
				if (val.compareTo(SHIPPING_INSTRUCTION_MIN) <= 0) {
					NumberChkDisitDetail checkDetail =
						check.getCheckDigit(unitDivision, venderDivision, venderCd);
					errors.add(""
						, new ActionMessage("errors.shipping.digit.rang.row"
							, check.format(unitDivision
											, venderDivision
											, venderCd
											, SHIPPING_INSTRUCTION_MIN),
								checkDetail.getUpperLimit().toString(),
							message));
				}
				//出荷指図可能数量 = 手持在庫 - 引当残
				BigDecimal inventoryQty = AecNumberUtils.convertNullToZero(
							AecNumberUtils.convertBigDecimal(detail.getStrInventoryQty()));
				BigDecimal assignQty = AecNumberUtils.convertNullToZero(
							AecNumberUtils.convertBigDecimal(detail.getStrAssignQty()));
				BigDecimal possible = inventoryQty.add(assignQty);
				//変更の場合、元の指図量分を加算する
				BigDecimal prev = AecNumberUtils.convertNullToZero(AecNumberUtils.convertBigDecimal(
								detail.getStrShippingInstructionPrev()));
				possible = possible.add(prev);
				//丸め処理
				BigDecimal possibleRd = check.round(unitDivision, venderDivision, venderCd, possible);

				//範囲チェック(可能数以下となっているかチェック)
				if (val.compareTo(possibleRd) > 0) {
					errors.add(""
						, new ActionMessage("errors.shipping.rang.shipping.max.row", message));
				}
			}
		}
	}

}
