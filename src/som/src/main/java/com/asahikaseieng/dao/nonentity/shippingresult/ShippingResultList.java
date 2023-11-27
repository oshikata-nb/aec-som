/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shippingresult;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectShippingResultShippingStatus;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 一覧画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class ShippingResultList extends ShippingResultListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション searchKana */
	public static final String searchKana_COLUMN = "SEARCH_KANA";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション shippingInstructionSum */
	public static final String shippingInstructionSum_COLUMN = "SHIPPING_INSTRUCTION_SUM";

	/** COLUMNアノテーション shippingResultQuantitySum */
	public static final String shippingResultQuantitySum_COLUMN = "SHIPPING_RESULT_QUANTITY_SUM";

	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** チェックフラグ */
	private boolean checkFlg;

	/** 納入先名称1 */
	private String deliveryName1;

	/** 納入先略称 */
	private String searchKana;

	/** 品目名称 */
	private String itemName;

	/** 荷姿 */
	private String styleOfPacking;

	/** 出荷予定日(文字列) */
	private String strScheduledShippingDate;

	/** 出荷指図数量 */
	private BigDecimal shippingInstructionSum;

	/** 出荷指図数量(文字列) */
	private String strShippingInstructionSum;

	/** 出荷実績数 */
	private BigDecimal shippingResultQuantitySum;

	/** 出荷実績数(文字列) */
	private String strShippingResultQuantitySum;

	/** 出荷完了日(文字列) */
	private String strShippingResultDate;

	/** 単位区分 */
	private String unitDivision;

	/** 得意先コード */
	private String venderCd;

	/**
	 * コンストラクタ.
	 */
	public ShippingResultList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 日付・数値の編集処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		setStrScheduledShippingDate(AecDateUtils.dateFormat(
			getScheduledShippingDate(), "yyyy/MM/dd"));
		setStrShippingResultDate(AecDateUtils.dateFormat(
			getShippingResultDate(), "yyyy/MM/dd"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * チェックフラグを取得します。
	 * @return boolean チェックフラグ
	 */
	public boolean isCheckFlg() {
		return checkFlg;
	}

	/**
	 * チェックフラグを設定します。
	 * @param checkFlg チェックフラグ
	 */
	public void setCheckFlg(final boolean checkFlg) {
		this.checkFlg = checkFlg;
	}

	/**
	 * 納入先名称1取得
	 * @return String 納入先名称1
	 */
	public String getDeliveryName1() {
		return this.deliveryName1;
	}

	/**
	 * 納入先名称1設定
	 * @param deliveryName1 納入先名称1
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
	 * 出荷予定日(文字列)を取得します。
	 * @return String 出荷予定日(文字列)
	 */
	public String getStrScheduledShippingDate() {
		return strScheduledShippingDate;
	}

	/**
	 * 出荷予定日(文字列)を設定します。
	 * @param strScheduledShippingDate 出荷予定日(文字列)
	 */
	public void setStrScheduledShippingDate(
			final String strScheduledShippingDate) {
		this.strScheduledShippingDate = strScheduledShippingDate;
	}

	/**
	 * 出荷指図数量取得
	 * @return BigDecimal 出荷指図数量
	 */
	public BigDecimal getShippingInstructionSum() {
		return this.shippingInstructionSum;
	}

	/**
	 * 出荷指図数量設定
	 * @param shippingInstructionSum 出荷指図数量
	 */
	public void setShippingInstructionSum(
			final BigDecimal shippingInstructionSum) {
		this.shippingInstructionSum = shippingInstructionSum;
	}

	/**
	 * 出荷指図数量(文字列)を取得します。
	 * @return String 出荷指図数量(文字列)
	 */
	public String getStrShippingInstructionSum() {
		return strShippingInstructionSum;
	}

	/**
	 * 出荷指図数量(文字列)を設定します。
	 * @param strShippingInstructionSum 出荷指図数量(文字列)
	 */
	public void setStrShippingInstructionSum(
			final String strShippingInstructionSum) {
		this.strShippingInstructionSum = strShippingInstructionSum;
	}

	/**
	 * 出荷実績数取得
	 * @return BigDecimal 出荷実績数
	 */
	public BigDecimal getShippingResultQuantitySum() {
		return this.shippingResultQuantitySum;
	}

	/**
	 * 出荷実績数設定
	 * @param shippingResultQuantitySum 出荷実績数
	 */
	public void setShippingResultQuantitySum(
			final BigDecimal shippingResultQuantitySum) {
		this.shippingResultQuantitySum = shippingResultQuantitySum;
	}

	/**
	 * 出荷実績数(文字列)を取得します。
	 * @return String 出荷実績数(文字列)
	 */
	public String getStrShippingResultQuantitySum() {
		return strShippingResultQuantitySum;
	}

	/**
	 * 出荷実績数(文字列)を設定します。
	 * @param strShippingResultQuantitySum 出荷実績数(文字列)
	 */
	public void setStrShippingResultQuantitySum(
			final String strShippingResultQuantitySum) {
		this.strShippingResultQuantitySum = strShippingResultQuantitySum;
	}

	/**
	 * 出荷予定日(文字列)を取得します。
	 * @return String 出荷予定日(文字列)
	 */
	public String getStrShippingResultDate() {
		return strShippingResultDate;
	}

	/**
	 * 出荷予定日(文字列)を設定します。
	 * @param strShippingResultDate 出荷予定日(文字列)
	 */
	public void setStrShippingResultDate(final String strShippingResultDate) {
		this.strShippingResultDate = strShippingResultDate;
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
	 * 出荷ステータス(文字列)を取得します。
	 * @return String 出荷ステータス(文字列)
	 */
	public String getStrShippingStatus() {
		return SelectShippingResultShippingStatus
				.getLabelName(getShippingStatus().toString());
	}

	/**
	 * 得意先コードを設定します。
	 * @param venderCd 得意先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 得意先コードを取得します。
	 * @return venderCd 得意先コード
	 */
	public String getVenderCd() {
		return venderCd;
	}
}
