/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.item.sales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SalesItemListForAutoCompleteクラス.
 * @author tosco
 */
public class SalesItemForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesItemForAutoCompleteBase() {
	}

	// 定数

	/** TABLEアノテーション.*/
	public static final String TABLE = "ITEM";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション itemType */
	public static final String itemType_COLUMN = "ITEM_TYPE";

	/** COLUMNアノテーション activeDate */
	public static final String activeDate_COLUMN = "ACTIVE_DATE";

	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション itemSubName */
	public static final String itemSubName_COLUMN = "ITEM_SUB_NAME";

	/** COLUMNアノテーション productDivision */
	public static final String productDivision_COLUMN = "PRODUCT_DIVISION";

	/** COLUMNアノテーション articleDivision */
	public static final String articleDivision_COLUMN = "ARTICLE_DIVISION";

	/** COLUMNアノテーション purchaseDivision */
	public static final String purchaseDivision_COLUMN = "PURCHASE_DIVISION";

	/** COLUMNアノテーション parentItemCd */
	public static final String parentItemCd_COLUMN = "PARENT_ITEM_CD";

	/** COLUMNアノテーション itemCategory */
	public static final String itemCategory_COLUMN = "ITEM_CATEGORY";

	/** COLUMNアノテーション phantomDivision */
	public static final String phantomDivision_COLUMN = "PHANTOM_DIVISION";

	/** COLUMNアノテーション spotDivision */
	public static final String spotDivision_COLUMN = "SPOT_DIVISION";

	/** COLUMNアノテーション stockDivision */
	public static final String stockDivision_COLUMN = "STOCK_DIVISION";

	/** COLUMNアノテーション bulkDivision */
	public static final String bulkDivision_COLUMN = "BULK_DIVISION";

	/** COLUMNアノテーション defaultLocation */
	public static final String defaultLocation_COLUMN = "DEFAULT_LOCATION";

	/** COLUMNアノテーション costDivision */
	public static final String costDivision_COLUMN = "COST_DIVISION";

	/** COLUMNアノテーション lotDivision */
	public static final String lotDivision_COLUMN = "LOT_DIVISION";

	/** COLUMNアノテーション newFlg */
	public static final String newFlg_COLUMN = "NEW_FLG";

	/** COLUMNアノテーション researchItem */
	public static final String researchItem_COLUMN = "RESEARCH_ITEM";

	/** COLUMNアノテーション shipperDivision */
	public static final String shipperDivision_COLUMN = "SHIPPER_DIVISION";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション orderLocation */
	public static final String orderLocation_COLUMN = "ORDER_LOCATION";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション numberOfInsertions */
	public static final String numberOfInsertions_COLUMN = "NUMBER_OF_INSERTIONS";

	/** COLUMNアノテーション allUpWeight */
	public static final String allUpWeight_COLUMN = "ALL_UP_WEIGHT";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション unitOfStockControl */
	public static final String unitOfStockControl_COLUMN = "UNIT_OF_STOCK_CONTROL";

	/** COLUMNアノテーション kgOfFractionManagement */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション unitOfFractionManagement */
	public static final String unitOfFractionManagement_COLUMN = "UNIT_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション kgConversionCoefficient */
	public static final String kgConversionCoefficient_COLUMN = "KG_CONVERSION_COEFFICIENT";

	/** COLUMNアノテーション waterDivision */
	public static final String waterDivision_COLUMN = "WATER_DIVISION";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション developmentRequestNo */
	public static final String developmentRequestNo_COLUMN = "DEVELOPMENT_REQUEST_NO";

	//
	// インスタンスフィールド
	//

	/** 品目コード */
	private String itemCd;

	/** バージョン */
	private BigDecimal version;

	/** 品目タイプ */
	private BigDecimal itemType;

	/** 有効日時 */
	private Timestamp activeDate;

	/** ステータス|LIST(1:入力中,2:承認依頼中,3:承認済み） */
	private BigDecimal status;

	/** 品目名称 */
	private String itemName;

	/** 品目サブ名称 */
	private String itemSubName;

	/** 製造品区分|0:該当なし,1:製品,2:中間品,3:充填品,4:応用試作品 */
	private BigDecimal productDivision;

	/** 販売品区分|0:該当なし,1:商品,2:仕入商品,3:受託品 */
	private BigDecimal articleDivision;

	/** 購入品区分|0:該当なし,1:原料,2:材料,3:副資材,4:部品,5:設備 */
	private BigDecimal purchaseDivision;

	/** 親品目コード */
	private String parentItemCd;

	/** 品目分類コード */
	private String itemCategory;

	/** ファントム区分|0:通常,1:ファントム */
	private BigDecimal phantomDivision;

	/** スポット区分|0:通常,1:スポット */
	private BigDecimal spotDivision;

	/** 在庫管理区分|0:通常,1:在庫表除外,2:更新除外 */
	private BigDecimal stockDivision;

	/** バルク区分|0:通常,1:バルク出庫 */
	private BigDecimal bulkDivision;

	/** 基準保管場所 */
	private String defaultLocation;

	/** 原価区分 */
	private BigDecimal costDivision;

	/** ロット管理区分|0:しない,1:する */
	private BigDecimal lotDivision;

	/** 新規取扱フラグ｜0:新規 */
	private BigDecimal newFlg;

	/** 研究用品目|0:研究用以外 1:研究用 */
	private BigDecimal researchItem;

	/** 荷主|0:自社,1:花王,2:OEM,3:油脂 */
	private BigDecimal shipperDivision;

	/** 種別|0:製品,1:原料,2:包材,3:中間品,4:仕入商品,5:外注品,9:その他 */
	private BigDecimal typeDivision;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 発注まとめ場所 */
	private String orderLocation;

	/** 荷姿 */
	private String styleOfPacking;

	/** 入数 */
	private BigDecimal numberOfInsertions;

	/** 総重量 */
	private BigDecimal allUpWeight;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/** 在庫管理単位 */
	private String unitOfStockControl;

	/** Kg換算係数（在庫） */
	private BigDecimal kgOfFractionManagement;

	/** 端数管理単位 */
	private String unitOfFractionManagement;

	/** Kg換算係数（端数） */
	private BigDecimal kgConversionCoefficient;

	/** 水区分|0:水以外 1:水 */
	private BigDecimal waterDivision;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者コード */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者コード */
	private String updatorCd;

	/** 開発依頼NO */
	private String developmentRequestNo;

	//
	// インスタンスメソッド
	//

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
	 * バージョン取得
	 * @return BigDecimal バージョン
	*/
	public BigDecimal getVersion() {
		return this.version;
	}

	/**
	 * バージョン設定
	 * @param version バージョン
	*/
	public void setVersion(final BigDecimal version) {
		this.version = version;
	}

	/**
	 * 品目タイプ取得
	 * @return BigDecimal 品目タイプ
	*/
	public BigDecimal getItemType() {
		return this.itemType;
	}

	/**
	 * 品目タイプ設定
	 * @param itemType 品目タイプ
	*/
	public void setItemType(final BigDecimal itemType) {
		this.itemType = itemType;
	}

	/**
	 * 有効日時取得
	 * @return Timestamp 有効日時
	*/
	public Timestamp getActiveDate() {
		return this.activeDate;
	}

	/**
	 * 有効日時設定
	 * @param activeDate 有効日時
	*/
	public void setActiveDate(final Timestamp activeDate) {
		this.activeDate = activeDate;
	}

	/**
	 * ステータス|LIST(1:入力中,2:承認依頼中,3:承認済み）取得
	 * @return BigDecimal ステータス|LIST(1:入力中,2:承認依頼中,3:承認済み）
	*/
	public BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * ステータス|LIST(1:入力中,2:承認依頼中,3:承認済み）設定
	 * @param status ステータス|LIST(1:入力中,2:承認依頼中,3:承認済み）
	*/
	public void setStatus(final BigDecimal status) {
		this.status = status;
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
	 * 品目サブ名称取得
	 * @return String 品目サブ名称
	*/
	public String getItemSubName() {
		return this.itemSubName;
	}

	/**
	 * 品目サブ名称設定
	 * @param itemSubName 品目サブ名称
	*/
	public void setItemSubName(final String itemSubName) {
		this.itemSubName = itemSubName;
	}

	/**
	 * 製造品区分|0:該当なし,1:製品,2:中間品,3:充填品,4:応用試作品取得
	 * @return BigDecimal 製造品区分|0:該当なし,1:製品,2:中間品,3:充填品,4:応用試作品
	*/
	public BigDecimal getProductDivision() {
		return this.productDivision;
	}

	/**
	 * 製造品区分|0:該当なし,1:製品,2:中間品,3:充填品,4:応用試作品設定
	 * @param productDivision 製造品区分|0:該当なし,1:製品,2:中間品,3:充填品,4:応用試作品
	*/
	public void setProductDivision(final BigDecimal productDivision) {
		this.productDivision = productDivision;
	}

	/**
	 * 販売品区分|0:該当なし,1:商品,2:仕入商品,3:受託品取得
	 * @return BigDecimal 販売品区分|0:該当なし,1:商品,2:仕入商品,3:受託品
	*/
	public BigDecimal getArticleDivision() {
		return this.articleDivision;
	}

	/**
	 * 販売品区分|0:該当なし,1:商品,2:仕入商品,3:受託品設定
	 * @param articleDivision 販売品区分|0:該当なし,1:商品,2:仕入商品,3:受託品
	*/
	public void setArticleDivision(final BigDecimal articleDivision) {
		this.articleDivision = articleDivision;
	}

	/**
	 * 購入品区分|0:該当なし,1:原料,2:材料,3:副資材,4:部品,5:設備取得
	 * @return BigDecimal 購入品区分|0:該当なし,1:原料,2:材料,3:副資材,4:部品,5:設備
	*/
	public BigDecimal getPurchaseDivision() {
		return this.purchaseDivision;
	}

	/**
	 * 購入品区分|0:該当なし,1:原料,2:材料,3:副資材,4:部品,5:設備設定
	 * @param purchaseDivision 購入品区分|0:該当なし,1:原料,2:材料,3:副資材,4:部品,5:設備
	*/
	public void setPurchaseDivision(final BigDecimal purchaseDivision) {
		this.purchaseDivision = purchaseDivision;
	}

	/**
	 * 親品目コード取得
	 * @return String 親品目コード
	*/
	public String getParentItemCd() {
		return this.parentItemCd;
	}

	/**
	 * 親品目コード設定
	 * @param parentItemCd 親品目コード
	*/
	public void setParentItemCd(final String parentItemCd) {
		this.parentItemCd = parentItemCd;
	}

	/**
	 * 品目分類コード取得
	 * @return String 品目分類コード
	*/
	public String getItemCategory() {
		return this.itemCategory;
	}

	/**
	 * 品目分類コード設定
	 * @param itemCategory 品目分類コード
	*/
	public void setItemCategory(final String itemCategory) {
		this.itemCategory = itemCategory;
	}

	/**
	 * ファントム区分|0:通常,1:ファントム取得
	 * @return BigDecimal ファントム区分|0:通常,1:ファントム
	*/
	public BigDecimal getPhantomDivision() {
		return this.phantomDivision;
	}

	/**
	 * ファントム区分|0:通常,1:ファントム設定
	 * @param phantomDivision ファントム区分|0:通常,1:ファントム
	*/
	public void setPhantomDivision(final BigDecimal phantomDivision) {
		this.phantomDivision = phantomDivision;
	}

	/**
	 * スポット区分|0:通常,1:スポット取得
	 * @return BigDecimal スポット区分|0:通常,1:スポット
	*/
	public BigDecimal getSpotDivision() {
		return this.spotDivision;
	}

	/**
	 * スポット区分|0:通常,1:スポット設定
	 * @param spotDivision スポット区分|0:通常,1:スポット
	*/
	public void setSpotDivision(final BigDecimal spotDivision) {
		this.spotDivision = spotDivision;
	}

	/**
	 * 在庫管理区分|0:通常,1:在庫表除外,2:更新除外取得
	 * @return BigDecimal 在庫管理区分|0:通常,1:在庫表除外,2:更新除外
	*/
	public BigDecimal getStockDivision() {
		return this.stockDivision;
	}

	/**
	 * 在庫管理区分|0:通常,1:在庫表除外,2:更新除外設定
	 * @param stockDivision 在庫管理区分|0:通常,1:在庫表除外,2:更新除外
	*/
	public void setStockDivision(final BigDecimal stockDivision) {
		this.stockDivision = stockDivision;
	}

	/**
	 * バルク区分|0:通常,1:バルク出庫取得
	 * @return BigDecimal バルク区分|0:通常,1:バルク出庫
	*/
	public BigDecimal getBulkDivision() {
		return this.bulkDivision;
	}

	/**
	 * バルク区分|0:通常,1:バルク出庫設定
	 * @param bulkDivision バルク区分|0:通常,1:バルク出庫
	*/
	public void setBulkDivision(final BigDecimal bulkDivision) {
		this.bulkDivision = bulkDivision;
	}

	/**
	 * 基準保管場所取得
	 * @return String 基準保管場所
	*/
	public String getDefaultLocation() {
		return this.defaultLocation;
	}

	/**
	 * 基準保管場所設定
	 * @param defaultLocation 基準保管場所
	*/
	public void setDefaultLocation(final String defaultLocation) {
		this.defaultLocation = defaultLocation;
	}

	/**
	 * 原価区分取得
	 * @return BigDecimal 原価区分
	*/
	public BigDecimal getCostDivision() {
		return this.costDivision;
	}

	/**
	 * 原価区分設定
	 * @param costDivision 原価区分
	*/
	public void setCostDivision(final BigDecimal costDivision) {
		this.costDivision = costDivision;
	}

	/**
	 * ロット管理区分|0:しない,1:する取得
	 * @return BigDecimal ロット管理区分|0:しない,1:する
	*/
	public BigDecimal getLotDivision() {
		return this.lotDivision;
	}

	/**
	 * ロット管理区分|0:しない,1:する設定
	 * @param lotDivision ロット管理区分|0:しない,1:する
	*/
	public void setLotDivision(final BigDecimal lotDivision) {
		this.lotDivision = lotDivision;
	}

	/**
	 * 新規取扱フラグ｜0:新規取得
	 * @return BigDecimal 新規取扱フラグ｜0:新規
	*/
	public BigDecimal getNewFlg() {
		return this.newFlg;
	}

	/**
	 * 新規取扱フラグ｜0:新規設定
	 * @param newFlg 新規取扱フラグ｜0:新規
	*/
	public void setNewFlg(final BigDecimal newFlg) {
		this.newFlg = newFlg;
	}

	/**
	 * 研究用品目|0:研究用以外 1:研究用取得
	 * @return BigDecimal 研究用品目|0:研究用以外 1:研究用
	*/
	public BigDecimal getResearchItem() {
		return this.researchItem;
	}

	/**
	 * 研究用品目|0:研究用以外 1:研究用設定
	 * @param researchItem 研究用品目|0:研究用以外 1:研究用
	*/
	public void setResearchItem(final BigDecimal researchItem) {
		this.researchItem = researchItem;
	}

	/**
	 * 荷主|0:自社,1:花王,2:OEM,3:油脂取得
	 * @return BigDecimal 荷主|0:自社,1:花王,2:OEM,3:油脂
	*/
	public BigDecimal getShipperDivision() {
		return this.shipperDivision;
	}

	/**
	 * 荷主|0:自社,1:花王,2:OEM,3:油脂設定
	 * @param shipperDivision 荷主|0:自社,1:花王,2:OEM,3:油脂
	*/
	public void setShipperDivision(final BigDecimal shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

	/**
	 * 種別|0:製品,1:原料,2:包材,3:中間品,4:仕入商品,5:外注品,9:その他取得
	 * @return BigDecimal 種別|0:製品,1:原料,2:包材,3:中間品,4:仕入商品,5:外注品,9:その他
	*/
	public BigDecimal getTypeDivision() {
		return this.typeDivision;
	}

	/**
	 * 種別|0:製品,1:原料,2:包材,3:中間品,4:仕入商品,5:外注品,9:その他設定
	 * @param typeDivision 種別|0:製品,1:原料,2:包材,3:中間品,4:仕入商品,5:外注品,9:その他
	*/
	public void setTypeDivision(final BigDecimal typeDivision) {
		this.typeDivision = typeDivision;
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
	 * 発注まとめ場所取得
	 * @return String 発注まとめ場所
	*/
	public String getOrderLocation() {
		return this.orderLocation;
	}

	/**
	 * 発注まとめ場所設定
	 * @param orderLocation 発注まとめ場所
	*/
	public void setOrderLocation(final String orderLocation) {
		this.orderLocation = orderLocation;
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
	 * 入数取得
	 * @return BigDecimal 入数
	*/
	public BigDecimal getNumberOfInsertions() {
		return this.numberOfInsertions;
	}

	/**
	 * 入数設定
	 * @param numberOfInsertions 入数
	*/
	public void setNumberOfInsertions(final BigDecimal numberOfInsertions) {
		this.numberOfInsertions = numberOfInsertions;
	}

	/**
	 * 総重量取得
	 * @return BigDecimal 総重量
	*/
	public BigDecimal getAllUpWeight() {
		return this.allUpWeight;
	}

	/**
	 * 総重量設定
	 * @param allUpWeight 総重量
	*/
	public void setAllUpWeight(final BigDecimal allUpWeight) {
		this.allUpWeight = allUpWeight;
	}

	/**
	 * 運用管理単位取得
	 * @return String 運用管理単位
	*/
	public String getUnitOfOperationManagement() {
		return this.unitOfOperationManagement;
	}

	/**
	 * 運用管理単位設定
	 * @param unitOfOperationManagement 運用管理単位
	*/
	public void setUnitOfOperationManagement(final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * 在庫管理単位取得
	 * @return String 在庫管理単位
	*/
	public String getUnitOfStockControl() {
		return this.unitOfStockControl;
	}

	/**
	 * 在庫管理単位設定
	 * @param unitOfStockControl 在庫管理単位
	*/
	public void setUnitOfStockControl(final String unitOfStockControl) {
		this.unitOfStockControl = unitOfStockControl;
	}

	/**
	 * Kg換算係数（在庫）取得
	 * @return BigDecimal Kg換算係数（在庫）
	*/
	public BigDecimal getKgOfFractionManagement() {
		return this.kgOfFractionManagement;
	}

	/**
	 * Kg換算係数（在庫）設定
	 * @param kgOfFractionManagement Kg換算係数（在庫）
	*/
	public void setKgOfFractionManagement(final BigDecimal kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * 端数管理単位取得
	 * @return String 端数管理単位
	*/
	public String getUnitOfFractionManagement() {
		return this.unitOfFractionManagement;
	}

	/**
	 * 端数管理単位設定
	 * @param unitOfFractionManagement 端数管理単位
	*/
	public void setUnitOfFractionManagement(final String unitOfFractionManagement) {
		this.unitOfFractionManagement = unitOfFractionManagement;
	}

	/**
	 * Kg換算係数（端数）取得
	 * @return BigDecimal Kg換算係数（端数）
	*/
	public BigDecimal getKgConversionCoefficient() {
		return this.kgConversionCoefficient;
	}

	/**
	 * Kg換算係数（端数）設定
	 * @param kgConversionCoefficient Kg換算係数（端数）
	*/
	public void setKgConversionCoefficient(final BigDecimal kgConversionCoefficient) {
		this.kgConversionCoefficient = kgConversionCoefficient;
	}

	/**
	 * 水区分|0:水以外 1:水取得
	 * @return BigDecimal 水区分|0:水以外 1:水
	*/
	public BigDecimal getWaterDivision() {
		return this.waterDivision;
	}

	/**
	 * 水区分|0:水以外 1:水設定
	 * @param waterDivision 水区分|0:水以外 1:水
	*/
	public void setWaterDivision(final BigDecimal waterDivision) {
		this.waterDivision = waterDivision;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	*/
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定
	 * @param inputDate 登録日時
	*/
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者コード取得
	 * @return String 登録者コード
	*/
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者コード設定
	 * @param inputorCd 登録者コード
	*/
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	*/
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	*/
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者コード取得
	 * @return String 更新者コード
	*/
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者コード設定
	 * @param updatorCd 更新者コード
	*/
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * 開発依頼NO取得
	 * @return String 開発依頼NO
	*/
	public String getDevelopmentRequestNo() {
		return this.developmentRequestNo;
	}

	/**
	 * 開発依頼NO設定
	 * @param developmentRequestNo 開発依頼NO
	*/
	public void setDevelopmentRequestNo(final String developmentRequestNo) {
		this.developmentRequestNo = developmentRequestNo;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
