/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.beforehandmeltlbl;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 予備溶解ラベル発行画面_検索結果表示用データ格納クラス.
 *
 * @author tosco
 */
public class BeforehandMeltLblList extends BeforehandMeltLblListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** 未発行 */
	private static final String NOT_ISSUE = "未";

	/** 発行済み */
	private static final String ISSUE = "済";

	/**
	 * コンストラクタ.
	 */
	public BeforehandMeltLblList() {
		super();
	}

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	/** COLUMNアノテーション mainStreamCount */
	public static final String mainStreamCount_COLUMN = "MAIN_STREAM_COUNT";

	/** チェックボックス */
	private boolean beforehandMeltLblCheckBox;

	/** 品目名称 */
	private String itemName;

	/** 生産ライン名称 */
	private String productionLineName;

	/** 予備溶解数 */
	private String mainStreamCount;

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */

	/**
	 * チェックボックス取得
	 * @return beforehandMeltLblCheckBox
	 */
	public boolean isBeforehandMeltLblCheckBox() {
		return beforehandMeltLblCheckBox;
	}

	/**
	 * チェックボックス設定
	 * @param beforehandMeltLblCheckBox チェックボックス
	 */
	public void setBeforehandMeltLblCheckBox(final boolean beforehandMeltLblCheckBox) {
		this.beforehandMeltLblCheckBox = beforehandMeltLblCheckBox;
	}

	/**
	 * 品目名称を取得します。
	 * @return itemName
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
	 * 生産ライン名称を取得します。
	 * @return productionLineName
	 */
	public String getProductionLineName() {
		return productionLineName;
	}

	/**
	 * 生産ライン名称を設定します。
	 * @param productionLineName 生産ライン名称
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * 予備溶解数を取得します。
	 * @return mainStreamCount
	 */
	public String getMainStreamCount() {
		return mainStreamCount;
	}

	/**
	 * 予備溶解数を設定します。
	 * @param mainStreamCount 予備溶解数
	 */
	public void setMainStreamCount(final String mainStreamCount) {
		this.mainStreamCount = mainStreamCount;
	}

	/**
	 * 予備溶解ラベル発行フラグ名称を取得します。
	 * @return mainStreamCount
	 */
	public String getStrStockdissLabelFlag() {
		//発行フラグがnullじゃなかったら
		if (getStockdissLabelFlag() != null) {
			//発行フラグが0であれば、未発行
			if (getStockdissLabelFlag().equals(new BigDecimal(0))) {
				return NOT_ISSUE;

			//発行フラグが1であれば、発行済み
			} else if (getStockdissLabelFlag().equals(new BigDecimal(1))) {
				return ISSUE;
			}
		}
		return "";
	}

	/**
	 * 製造予定日(String)を取得します。
	 * @return mainStreamCount
	 */
	public String getStrPlanedSdate() {
		//取得した製造予定日をyyyy/MM/ddに変換
		return AecDateUtils.dateFormat(getPlanedSdate(), "yyyy/MM/dd");
	}

}
