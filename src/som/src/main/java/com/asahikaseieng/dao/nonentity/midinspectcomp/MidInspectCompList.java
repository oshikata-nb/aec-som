/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.midinspectcomp;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectMidInspectCompDirectionStatus;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 中間品検査完了入力画面_検索結果表示用データ格納クラス.
 *
 * @author tosco
 */
public class MidInspectCompList extends MidInspectCompListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MidInspectCompList() {
		super();
	}

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	/** COLUMNアノテーション mainStreamCount */
	public static final String mainStreamCount_COLUMN = "MAIN_STREAM_COUNT";

	/** COLUMNアノテーション orderPublishFlg */
	public static final String orderPublishFlg_COLUMN = "ORDER_PUBLISH_FLG";

	/** チェックボックス */
	private boolean midInspectCompCheckBox;

	/** 品目名称 */
	private String itemName;

	/** 生産ライン名称 */
	private String productionLineName;

	/** 工程名称 */
	private String operationName;

	/** 指図書発行有無フラグ 1:あり 2:なし */
	private BigDecimal orderPublishFlg;

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
	 * 指図ステータス取得
	 * @return String 指図ステータス
	 */
	public String getStrDirectionStatus() {
		String ret = null;
		if (getDirectionStatus() != null) {
			ret = SelectMidInspectCompDirectionStatus.getLabelName(getDirectionStatus().toString());
		}
		return ret;
	}

	/**
	 * チェックボックス取得
	 * @return midInspectCompCheckBox
	 */
	public boolean isMidInspectCompCheckBox() {
		return midInspectCompCheckBox;
	}

	/**
	 * チェックボックス設定
	 * @param midInspectCompCheckBox チェックボックス
	 */
	public void setMidInspectCompCheckBox(final boolean midInspectCompCheckBox) {
		this.midInspectCompCheckBox = midInspectCompCheckBox;
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
	 * 工程名称を取得します。
	 * @return operationName
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * 工程名称を設定します。
	 * @param operationName 工程名称
	 */
	public void setOperationName(final String operationName) {
		this.operationName = operationName;
	}

	/**
	 * 開始予定日(String)を取得します。
	 * @return strPlanedSdate
	 */
	public String getStrPlanedSdate() {
		//取得した製造予定日をyyyy/MM/ddに変換
		return AecDateUtils.dateFormat(getPlanedSdate()	, "yyyy/MM/dd");
	}

	/**
	 * 指図書発行有無フラグを取得します。
	 * @return orderPublishFlg 指図書発行有無フラグ 1:あり 2:なし
	 */
	public BigDecimal getOrderPublishFlg() {
		return orderPublishFlg;
	}

	/**
	 * 指図書発行有無フラグを設定します。
	 * @param orderPublishFlg 指図書発行有無フラグ 1:あり 2:なし
	 */
	public void setOrderPublishFlg(final BigDecimal orderPublishFlg) {
		this.orderPublishFlg = orderPublishFlg;
	}

}
