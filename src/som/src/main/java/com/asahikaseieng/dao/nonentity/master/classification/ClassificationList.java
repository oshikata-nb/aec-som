/*
 * Created on 2008/07/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.classification;

import java.math.BigDecimal;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 分類マスタ一覧　Beanクラス.
 * @author tosco
 */
public class ClassificationList extends ClassificationListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** ﾃﾞｰﾀ種別 */
	private String strDataType;

	/** ﾃﾞｰﾀ集計区分 */
	private String strDataTotalDivision;

	/** Stringの'1' */
	private static final String STR_ONE		= "1";
	/** Stringの'2' */
	private static final String STR_TWO		= "2";
	/** Stringの'3' */
	private static final String STR_THREE 	= "3";
	/** Stringの'4' */
	private static final String STR_FOUR 		= "4";
	/** Stringの'5' */
	private static final String STR_FIVE 		= "5";
	/** Stringの'9' */
	private static final String STR_NINE 		= "9";

	/** 文字列「売上」 */
	private static final String STR_COLON 		= "：";
	/** 文字列「売上」 */
	private static final String STR_SALES 		= "売上";
	/** 文字列入金「入金」 */
	private static final String STR_CREDIT 		= "入金";
	/** 文字列仕入「仕入」 */
	private static final String STR_STOCKING 		= "仕入";
	/** 文字列支払「支払」 */
	private static final String STR_PAYMENT 		= "支払";
	/** 文字列「グループ間相殺」 */
	private static final String STR_GROUP_OFFSET 	= "グループ間相殺";
	/** 文字列 */
	private static final String STR_RETURNED		= "返品";
	/** 文字列 */
	private static final String STR_DISCOUNT		= "値引";
	/** 文字列 */
	private static final String STR_OFFSET		= "相殺";
	/** 文字列 */
	private static final String STR_OTHER			= "その他";

	/**
	 * コンストラクタ.分類マスタ一覧
	 */
	public ClassificationList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		// 明細部分表示用
		BigDecimal numDataType = getDataType();
		BigDecimal numDataTotalDivision = getDataTotalDivision();
		if ((numDataType != null) && (numDataTotalDivision != null)) {
			String[] data = setText(numDataType.toPlainString(),
						numDataTotalDivision.toEngineeringString());
			setStrDataType(data[0]);
			setStrDataTotalDivision(data[1]);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/**
	 * ﾃﾞｰﾀ種別、ﾃﾞｰﾀ集計区分を表示用に設定する
	 * @param dataType
	 * @param dataTotalDivision
	 */
	private String[] setText(final String dataType, final String dataTotalDivision) {

		String[] data = new String[2];
		if (dataType.equals(STR_ONE)) {
			data[0] = STR_ONE.concat(STR_COLON).concat(STR_SALES);
			if (dataTotalDivision.equals(STR_ONE)) {
				data[1] = STR_ONE.concat(STR_COLON).concat(STR_SALES);
			} else if (dataTotalDivision.equals(STR_TWO)) {
				data[1] = STR_TWO.concat(STR_COLON).concat(STR_RETURNED);
			} else if (dataTotalDivision.equals(STR_THREE)) {
				data[1] = STR_THREE.concat(STR_COLON).concat(STR_DISCOUNT);
			} else if (dataTotalDivision.equals(STR_NINE)) {
				data[1] = STR_NINE.concat(STR_COLON).concat(STR_OTHER);
			}
		} else if (dataType.equals(STR_TWO)) {
			data[0] = STR_TWO.concat(STR_COLON).concat(STR_CREDIT);
			if (dataTotalDivision.equals(STR_ONE)) {
				data[1] = STR_ONE.concat(STR_COLON).concat(STR_CREDIT);
			} else if (dataTotalDivision.equals(STR_TWO)) {
				data[1] = STR_TWO.concat(STR_COLON).concat(STR_OFFSET);
			} else if (dataTotalDivision.equals(STR_NINE)) {
				data[1] = STR_NINE.concat(STR_COLON).concat(STR_OTHER);
			}
		} else if (dataType.equals(STR_THREE)) {
			data[0] = STR_THREE.concat(STR_COLON).concat(STR_STOCKING);
			if (dataTotalDivision.equals(STR_ONE)) {
				data[1] = STR_ONE.concat(STR_COLON).concat(STR_STOCKING);
			} else if (dataTotalDivision.equals(STR_TWO)) {
				data[1] = STR_TWO.concat(STR_COLON).concat(STR_RETURNED);
			} else if (dataTotalDivision.equals(STR_THREE)) {
				data[1] = STR_THREE.concat(STR_COLON).concat(STR_DISCOUNT);
			} else if (dataTotalDivision.equals(STR_NINE)) {
				data[1] = STR_NINE.concat(STR_COLON).concat(STR_OTHER);
			}
		} else if (dataType.equals(STR_FOUR)) {
			data[0] = STR_FOUR.concat(STR_COLON).concat(STR_PAYMENT);
			if (dataTotalDivision.equals(STR_ONE)) {
				data[1] = STR_ONE.concat(STR_COLON).concat(STR_PAYMENT);
			} else if (dataTotalDivision.equals(STR_TWO)) {
				data[1] = STR_TWO.concat(STR_COLON).concat(STR_OFFSET);
			} else if (dataTotalDivision.equals(STR_NINE)) {
				data[1] = STR_NINE.concat(STR_COLON).concat(STR_OTHER);
			}
		} else if (dataType.equals(STR_FIVE)) {
			data[0] = STR_FIVE.concat(STR_COLON).concat(STR_GROUP_OFFSET);
			if (dataTotalDivision.equals(STR_ONE)) {
				data[1] = STR_ONE.concat(STR_COLON).concat(STR_OFFSET);
			} else if (dataTotalDivision.equals(STR_NINE)) {
				data[1] = STR_NINE.concat(STR_COLON).concat(STR_OTHER);
			}
		}
		return data;
	}
	/* ---------- getter ,setter ---------- */

	/**
	 * データ種別取得.
	 * @return String データ種別
	 */
	public String getStrDataType() {
		return strDataType;
	}

	/**
	 * データ種別設定.
	 * @param strDataType データ種別
	 */
	public void setStrDataType(final String strDataType) {
		this.strDataType = strDataType;
	}

	/**
	 * データ集計区分取得.
	 * @return String データ集計区分

	 */
	public String getStrDataTotalDivision() {
		return strDataTotalDivision;
	}

	/**
	 * データ集計区分設定.
	 * @param strDataTotalDivision データ集計区分

	 */
	public void setStrDataTotalDivision(final String strDataTotalDivision) {
		this.strDataTotalDivision = strDataTotalDivision;
	}

}

