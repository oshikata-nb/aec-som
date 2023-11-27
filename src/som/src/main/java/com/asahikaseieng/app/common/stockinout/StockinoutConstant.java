/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.stockinout;

import java.math.BigDecimal;

/**
 * 在庫・受払処理共通の定数.
 * @author a7710658
 */
public final class StockinoutConstant {
	private StockinoutConstant() {
	}

	/** 入出庫区分 0:入庫 */
	static final BigDecimal IN_DIV = new BigDecimal(0);

	/** 入出庫区分 1:出庫 */
	static final BigDecimal OUT_DIV = new BigDecimal(1);

	/** 1:仕入 */
	static final BigDecimal FUNC_SHIIRE = new BigDecimal(1);

	/** 2:売上 */
	static final BigDecimal FUNC_URI = new BigDecimal(2);

	/** 3:例外 */
	static final BigDecimal FUNC_REIGAI = new BigDecimal(3);

	/** 4:製造 */
	static final BigDecimal FUNC_SEIZO = new BigDecimal(4);

	/** 5:支給 */
	static final BigDecimal FUNC_SIKYU = new BigDecimal(5);

	/** 6:移動 */
	static final BigDecimal FUNC_IDO = new BigDecimal(6);

	/** 7:廃棄 */
	static final BigDecimal FUNC_HAIKI = new BigDecimal(7);

	/** 8:棚卸調整 */
	static final BigDecimal FUNC_TANASA = new BigDecimal(8);

	/** 9:出荷 */
	static final BigDecimal FUNC_SHIPPING = new BigDecimal(9);

	/** 51:仕入返品 */
	static final BigDecimal FUNC_SHIIRE_R = new BigDecimal(51);

	/** 52:売上返品 */
	static final BigDecimal FUNC_URI_R = new BigDecimal(52);

	/** 54:製造戻し */
	static final BigDecimal FUNC_SEIZO_R = new BigDecimal(54);

	/** 55:支給返納 */
	static final BigDecimal FUNC_SIKYU_R = new BigDecimal(55);

	/** 59:出荷返品 */
	static final BigDecimal FUNC_SHIPPING_R = new BigDecimal(59);

	/** 81:仕入取消 */
	static final BigDecimal FUNC_SHIIRE_C = new BigDecimal(81);

	/** 82:売上取消 */
	static final BigDecimal FUNC_URI_C = new BigDecimal(82);

	/** 83:例外取消 */
	static final BigDecimal FUNC_REIGAI_C = new BigDecimal(83);

	/** 84:製造取消 */
	static final BigDecimal FUNC_SEIZOU_C = new BigDecimal(84);

	/** 85:支給取消 */
	static final BigDecimal FUNC_SIKYU_C = new BigDecimal(85);

	/** 86:移動取消 */
	static final BigDecimal FUNC_IDO_C = new BigDecimal(86);

	/** 87:廃棄取消 */
	static final BigDecimal FUNC_HAIKI_C = new BigDecimal(87);

	/** 88:棚調取消 */
	static final BigDecimal FUNC_TANASA_C = new BigDecimal(88);

	/** 89:出荷取消 */
	static final BigDecimal FUNC_SHIPPING_C = new BigDecimal(89);

	/** 91:仕返取消 */
	static final BigDecimal FUNC_SHIIRE_RC = new BigDecimal(91);

	/** 92:売返取消 */
	static final BigDecimal FUNC_URI_RC = new BigDecimal(92);

	/** 95:支返取消 */
	static final BigDecimal FUNC_SIKYU_RC = new BigDecimal(95);

	/** 99:出返取消 */
	static final BigDecimal FUNC_SHIPPING_RC = new BigDecimal(99);

	/** 正常完了文字 */
	static final String STR_COMPLETE = "COMPLETE";

	/** 入力処理 */
	static final BigDecimal BD_NYURYOKU_FLG = new BigDecimal(1);

	/** 入力取消処理 */
	static final BigDecimal BD_CANCEL_NYURYOKU_FLG = new BigDecimal(2);

	/** 入力処理 */
	static final BigDecimal BD_KAKUTEI_FLG = new BigDecimal(3);

	/** 入力取消処理 */
	static final BigDecimal BD_CANCEL_KAKUTEI_FLG = new BigDecimal(4);

	/** 入力処理 */
	static final BigDecimal BD_RESULT_FLG = new BigDecimal(3);

	/** 入力取消処理 */
	static final BigDecimal BD_CANCEL_RESULT_FLG = new BigDecimal(4);

	/** 完了処理 */
	static final BigDecimal BD_COMPLETE_FLG = new BigDecimal(5);

	/** 仕上処理 */
	static final BigDecimal BD_FINISH_FLG = new BigDecimal(1);

	/** 仕上取消処理 */
	static final BigDecimal BD_CANCEL_FINISH_FLG = new BigDecimal(2);

	/** 検査処理 */
	static final BigDecimal BD_INSPECTION_FLG = new BigDecimal(3);

	/** 検査取消処理 */
	static final BigDecimal BD_CANCEL_INSPECTION_FLG = new BigDecimal(4);

	/** 製品処理 */
	static final BigDecimal BD_GRADE_FLG = new BigDecimal(6);

	/** 製品取消処理 */
	static final BigDecimal BD_CANCEL_GRADE_FLG = new BigDecimal(7);

	/** 在庫入庫 */
	static final BigDecimal PNO_INVENTRY_IN = new BigDecimal(9);

	/** 在庫出庫 */
	static final BigDecimal PNO_INVENTRY_OUT = new BigDecimal(10);

	/** 棚卸調整入庫 */
	static final BigDecimal PNO_TANA_IN = new BigDecimal(13);

	/** 棚卸調整出庫 */
	static final BigDecimal PNO_TANA_OUT = new BigDecimal(14);

	/** 返品入庫 */
	static final BigDecimal PNO_HENPIN_IN = new BigDecimal(16);

	/** 預品 */
	static final BigDecimal PNO_AZUKARI = new BigDecimal(17);

	/** 在庫 */
	static final BigDecimal INOUTDIV_ZAIKO = new BigDecimal(10);

	/** 棚卸 */
	static final BigDecimal INOUTDIV_TANA = new BigDecimal(11);

	/** 返品 */
	static final BigDecimal INOUTDIV_HENPIN = new BigDecimal(12);

	/** 預品 */
	static final BigDecimal INOUTDIV_AZUKARI = new BigDecimal(13);

}
