/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectPkgDirectionDirectionStatus;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 包装指図－検索画面データ格納クラス.
 * 
 * @author tosco
 */
public class PkgDirectionList extends PkgDirectionListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション statusName */
	public static final String statusName_COLUMN = "STATUS_NAME";

	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション paletteProducts */
	public static final String paletteProducts_COLUMN = "PALETTE_PRODUCTS";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_DIV";

	/** COLUMNアノテーション shipperDivision */
	public static final String shipperDivision_COLUMN = "SHIPPER_DIVISION";

	/** COLUMNアノテーション jissekiFlag */
	public static final String jissekiFlag_COLUMN = "JISSEKI_FLAG";

	/** COLUMNアノテーション aspOrderNo */
	public static final String aspOrderNo_COLUMN = "ASP_ORDER_NO";

	/** チェックフラグ */
	private boolean checkFlg;

	/** 品目名称 */
	private String itemName;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 生産工場名 */
	private String productionLineName;

	/** 単位名 */
	private String unitName;

	/** 荷姿 */
	private String styleOfPacking;

	/** ラベル枚数 */
	private String labelCount;

	/** パレット上製品数 */
	private BigDecimal paletteProducts;

	/** 運用管理単位(区分) */
	private String unitDiv;

	/** 生産予定数量(カンマ編集) */
	private String strPlanedQty;

	/** 荷主 */
	private BigDecimal shipperDivision;

	/** 製品入出庫実績データ存在フラグ(0:無 1:有) */
	private String jissekiFlag;

	/** ASPオーダーコード */
	private String aspOrderNo;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		// ラベル数
		initLabelCount();
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/**
	 * ラベル数の初期値設定
	 */
	private void initLabelCount() {

		if (getPaletteProducts() != null
				&& !getPaletteProducts().equals(BigDecimal.ZERO)) {

			if (this.getPlanedQty() != null) {
				// 予定生産量 / パレット上製品数
				setLabelCount(getPlanedQty().divide(getPaletteProducts(), 0,
					BigDecimal.ROUND_UP).toString());
			}
		}
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * チェックフラグを取得する
	 * @return boolean チェックフラグ
	 */
	public boolean isCheckFlg() {
		return checkFlg;
	}

	/**
	 * チェックフラグを設定する
	 * @param checkFlg チェックフラグ
	 */
	public void setCheckFlg(final boolean checkFlg) {
		this.checkFlg = checkFlg;
	}

	/**
	 * 品目名称を取得します。
	 * @return 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称を設定します。
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 他社コード１を取得します。
	 * @return 品目名称
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード１を設定します。
	 * @param otherCompanyCd1 品目名称
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 生産工場名を取得します。
	 * @return 生産工場名
	 */
	public String getProductionLineName() {
		return productionLineName;
	}

	/**
	 * 生産工場名を設定します。
	 * @param productionLineName 生産工場名
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * ステータス名を取得します。
	 * @return ステータス名
	 */
	public String getStatusName() {
		String statusName = "";
		BigDecimal directionStatus = this.getDirectionStatus();
		if (directionStatus != null) {
			statusName = SelectPkgDirectionDirectionStatus
					.getLabelName(directionStatus.toString());
		}
		return statusName;
	}

	/**
	 * 単位名を取得します。
	 * @return 単位名
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位名を設定します。
	 * @param unitName 単位名
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
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
	 * ラベル枚数取得.
	 * @return ラベル枚数
	 */
	public String getLabelCount() {
		return labelCount;
	}

	/**
	 * ラベル枚数設定.
	 * @param labelCount ラベル枚数
	 */
	public void setLabelCount(final String labelCount) {
		this.labelCount = labelCount;
	}

	/**
	 * ラベル発行ステータス名取得.
	 * @return ラベル発行ステータス名
	 */
	public String getLabeIssueStatus() {
		String statusName = "未";
		if (this.getProductLabelFlag() != null) {
			if (this.getProductLabelFlag().intValue() == 1) {
				statusName = "済";
			}
		}
		return statusName;
	}

	/**
	 * 包装開始予定日時取得.
	 * @return 包装開始予定日時
	 */
	public String getPackPlanSdate() {
		String strDate = "";
		if (this.getPlanedSdate() != null) {
			strDate = AecDateUtils.dateFormat(this.getPlanedSdate(),
				"yyyy/MM/dd HH:mm");
		}
		return strDate;
	}

	/**
	 * 包装終了予定日時取得.
	 * @return 包装終了予定日時
	 */
	public String getPackPlanEdate() {
		String strDate = "";
		if (this.getPlanedSdate() != null) {
			strDate = AecDateUtils.dateFormat(this.getPlanedEdate(),
				"yyyy/MM/dd HH:mm");
		}
		return strDate;
	}

	/**
	 * パレット上製品数取得.
	 * @return BigDecimal パレット上製品数
	 */
	public BigDecimal getPaletteProducts() {
		return paletteProducts;
	}

	/**
	 * パレット上製品数設定.
	 * @param paletteProducts パレット上製品数
	 */
	public void setPaletteProducts(final BigDecimal paletteProducts) {
		this.paletteProducts = paletteProducts;
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
	 * 生産予定数量(カンマ編集)取得.
	 * @return String 生産予定数量(カンマ編集)
	 */
	public String getStrPlanedQty() {
		return strPlanedQty;
	}

	/**
	 * 生産予定数量(カンマ編集)設定.
	 * @param strPlanedQty 生産予定数量(カンマ編集)
	 */
	public void setStrPlanedQty(final String strPlanedQty) {
		this.strPlanedQty = strPlanedQty;
	}

	/**
	 * 荷主取得.
	 * @return BigDecimal 荷主
	 */
	public BigDecimal getShipperDivision() {
		return shipperDivision;
	}

	/**
	 * 荷主設定.
	 * @param shipperDivision 荷主
	 */
	public void setShipperDivision(final BigDecimal shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

	/**
	 * 製品入出庫実績データ存在フラグ(0:無 1:有)取得.
	 * @return ラベル枚数
	 */
	public String getJissekiFlag() {
		return jissekiFlag;
	}

	/**
	 * 製品入出庫実績データ存在フラグ(0:無 1:有)設定.
	 * @param jissekiFlag 製品入出庫実績データ存在フラグ
	 */
	public void setJissekiFlag(final String jissekiFlag) {
		this.jissekiFlag = jissekiFlag;
	}

	/**
	 * aspOrderNo取得.
	 * @return aspOrderNo
	 */
	public String getAspOrderNo() {
		return aspOrderNo;
	}

	/**
	 * aspOrderNo設定.
	 * @param aspOrderNo aspOrderNo
	 */
	public void setAspOrderNo(final String aspOrderNo) {
		this.aspOrderNo = aspOrderNo;
	}

}
