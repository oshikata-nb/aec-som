/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shipping;

import com.asahikaseieng.app.comboboxes.SelectShippingShippingStatus;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 一覧画面_検索結果表示用データ格納クラス.
 *
 * @author tosco
 */
public class ShippingList extends ShippingListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";

	/** COLUMNアノテーション unitDivision */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	/** COLUMNアノテーション carryName */
	public static final String carryName_COLUMN = "CARRY_NAME";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** 予定日(文字列) */
	private String strScheduledShippingDate;

	/** 出荷指図数量(カンマ編集) */
	private String strShippingInstruction;

	/** チェックフラグ */
	private boolean checkFlg;

	/** 納入先名称1 */
	private String deliveryName1;

	/** ロケーション名称 */
	private String locationName;

	/** 単位区分 */
	private String unitDivision;

	/** 単位名称 */
	private String unitName;

	/** 運送会社 */
	private String carryName;

	/** 得意先コード */
	private String venderCd;

	/**
	 * コンストラクタ.
	 */
	public ShippingList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 日付・数値の編集処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		setStrScheduledShippingDate(AecDateUtils.dateFormat(getScheduledShippingDate(), "yyyy/MM/dd"));
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
	 * ロケーション名称取得
	 * @return String ロケーション名称
	*/
	public String getLocationName() {
		return this.locationName;
	}

	/**
	 * ロケーション名称設定
	 * @param locationName ロケーション名称
	*/
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
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
	 * 単位名称
	 * @return String 単位名称
	*/
	public String getUnitName() {
		return this.unitName;
	}

	/**
	 * 単位名称
	 * @param unitName 単位名称
	*/
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 運送会社取得
	 * @return String 運送会社
	*/
	public String getCarryName() {
		return this.carryName;
	}

	/**
	 * 運送会社設定
	 * @param carryName 運送会社
	*/
	public void setCarryName(final String carryName) {
		this.carryName = carryName;
	}

	/**
	 * 出荷指図数量(カンマ編集)を取得します。
	 * @return String 出荷指図数量(カンマ編集)
	 */
	public String getStrShippingInstruction() {
		return strShippingInstruction;
	}

	/**
	 * 出荷指図数量(カンマ編集)を設定します。
	 * @param strShippingInstruction 出荷指図数量(カンマ編集)
	 */
	public void setStrShippingInstruction(final String strShippingInstruction) {
		this.strShippingInstruction = strShippingInstruction;
	}

	/**
	 * 予定日(文字列)を取得します。
	 * @return String 予定日(文字列)
	 */
	public String getStrScheduledShippingDate() {
		return strScheduledShippingDate;
	}

	/**
	 * 予定日(文字列)を設定します。
	 * @param strScheduledShippingDate 予定日(文字列)
	 */
	public void setStrScheduledShippingDate(final String strScheduledShippingDate) {
		this.strScheduledShippingDate = strScheduledShippingDate;
	}

	/**
	 * 出荷ステータス(文字列)を取得します。
	 * @return String 出荷ステータス(文字列)
	 */
	public String getStrShippingStatus() {
		return SelectShippingShippingStatus.getLabelName(getShippingStatus().toString());
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
