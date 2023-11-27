/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.accept;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectStockingStatus;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 受入仕入検索画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class AcceptList extends AcceptListBase implements
		PropertyTransferCallbacker {

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション kbn */
	public static final String kbn_COLUMN = "KBN";

	/** COLUMNアノテーション nyukaFlg */
	public static final String nyukaFlg_COLUMN = "NYUKA_FLG";

	/** COLUMNアノテーション itemQueueName */
	public static final String itemQueueName_COLUMN = "ITEM_QUEUE_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション kgOfFractionManagement */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_DIV";

	/** COLUMNアノテーション minStatus */
	public static final String minStatus_COLUMN = "MIN_STATUS";

	/**
	 * 区分(A:原材料(自社)ローリー以外,B:原材料(自社)ローリー,C:原材料(外注),D:外注製品(直送)
	 * ,E:外注製品(非直送),F:仕入直送品,G:スポット品,H:仕入在庫品)
	 */
	private String kbn;

	/** 入荷処理対象フラグ(0:入荷処理対象外,1:入荷処理対象) */
	private String nyukaFlg;

	/** 品目名称 */
	private String itemQueueName;

	/** Kg換算係数(在庫) */
	private BigDecimal kgOfFractionManagement;

	/** 仕入先 */
	private String venderName1;

	/** 仕入先略称 */
	private String venderShortedName;

	/** 運用管理単位(区分) */
	private String unitDiv;

	/** 最小購買ステータス */
	private BigDecimal minStatus;

	/** 受入日付(文字列) */
	private String strAcceptDate;

	/** 納品希望日(文字列) */
	private String strSuggestedDeliverlimitDate;

	/** 発注数量(カンマ編集) */
	private String strOrderQuantity;

	/** 受入数量 */
	private String strAcceptQuantity;

	/** 荷姿 */
	private String styleOfPacking;

	/** 重量(カンマ編集) */
	private String strOrderConvertQuantity;

	/**
	 * コンストラクタ.
	 */
	public AcceptList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 日付の編集処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		setStrAcceptDate(AecDateUtils.dateFormat(getAcceptDate(), "yyyy/MM/dd"));
		setStrSuggestedDeliverlimitDate(AecDateUtils.dateFormat(
			getSuggestedDeliverlimitDate(), "yyyy/MM/dd"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

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
	 * Kg換算係数(在庫)取得
	 * @return String Kg換算係数(在庫)
	 */
	public BigDecimal getKgOfFractionManagement() {
		return kgOfFractionManagement;
	}

	/**
	 * Kg換算係数(在庫)設定
	 * @param kgOfFractionManagement Kg換算係数(在庫)
	 */
	public void setKgOfFractionManagement(
			final BigDecimal kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * 仕入先取得
	 * @return String 仕入先
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * 仕入先設定
	 * @param venderName1 仕入先
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
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
	 * 最小購買ステータス取得
	 * @return BigDecimal 最小購買ステータス
	 */
	public BigDecimal getMinStatus() {
		return minStatus;
	}

	/**
	 * 最小購買ステータス設定
	 * @param minStatus 最小購買ステータス
	 */
	public void setMinStatus(final BigDecimal minStatus) {
		this.minStatus = minStatus;
	}

	/**
	 * 受入日付(文字列)を取得します。
	 * @return String 受入日付(文字列)
	 */
	public String getStrAcceptDate() {
		return strAcceptDate;
	}

	/**
	 * 受入日付(文字列)を設定します。
	 * @param strAcceptDate 受入日付(文字列)
	 */
	public void setStrAcceptDate(final String strAcceptDate) {
		this.strAcceptDate = strAcceptDate;
	}

	/**
	 * 納品希望日(文字列)を取得します。
	 * @return String 納品希望日(文字列)
	 */
	public String getStrSuggestedDeliverlimitDate() {
		return strSuggestedDeliverlimitDate;
	}

	/**
	 * 納品希望日(文字列)を設定します。
	 * @param strSuggestedDeliverlimitDate 納品希望日(文字列)
	 */
	public void setStrSuggestedDeliverlimitDate(
			final String strSuggestedDeliverlimitDate) {
		this.strSuggestedDeliverlimitDate = strSuggestedDeliverlimitDate;
	}

	/**
	 * 発注数量(カンマ編集)を取得します。
	 * @return String 発注数量(カンマ編集)
	 */
	public String getStrOrderQuantity() {
		return strOrderQuantity;
	}

	/**
	 * 発注数量(カンマ編集)を設定します。
	 * @param strOrderQuantity 発注数量(カンマ編集)
	 */
	public void setStrOrderQuantity(final String strOrderQuantity) {
		this.strOrderQuantity = strOrderQuantity;
	}

	/**
	 * 受入数量(カンマ編集)を取得します。
	 * @return String 受入数量(カンマ編集)
	 */
	public String getStrAcceptQuantity() {
		return strAcceptQuantity;
	}

	/**
	 * 受入数量(カンマ編集)を設定します。
	 * @param strAcceptQuantity 受入数量(カンマ編集)
	 */
	public void setStrAcceptQuantity(final String strAcceptQuantity) {
		this.strAcceptQuantity = strAcceptQuantity;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 重量(カンマ編集)を取得します。
	 * @return String 重量(カンマ編集)
	 */
	public String getStrOrderConvertQuantity() {
		return strOrderConvertQuantity;
	}

	/**
	 * 重量(カンマ編集)を設定します。
	 * @param strOrderConvertQuantity 重量(カンマ編集)
	 */
	public void setStrOrderConvertQuantity(final String strOrderConvertQuantity) {
		this.strOrderConvertQuantity = strOrderConvertQuantity;
	}

	/**
	 * 購買ステータス(名称)を取得します。
	 * @return String 購買ステータス(名称)
	 */
	public String getStrStatus() {
		String ret = null;
		if (getStatus() != null) {
			ret = PurchaseStatus.getName(getStatus());
		}
		return ret;
	}

	/**
	 * 仕入ステータス(名称)を取得します。
	 * @return String 仕入ステータス(名称)
	 */
	public String getStrStatus2() {
		String ret = null;
		if (getStatus2() != null) {
			ret = SelectStockingStatus.getLabelName(getStatus2().toString());
		}
		return ret;
	}

	/**
	 * 分納区分(文字列)を取得します。
	 * @return String 分納区分(文字列)
	 */
	public String getStrReplyContentsDivision() {
		String ret = "";
		if (getReplyContentsDivision() != null) {
			if (getReplyContentsDivision().equals(BigDecimal.ZERO)) {
				ret = "無";
			}
			if (getReplyContentsDivision().equals(BigDecimal.ONE)) {
				ret = "有";
			}
		}
		return ret;
	}

}
