/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.stockinout;

/**
 * 在庫・受払ソースを操作するクラス(ShipResult用) <BR>
 * **LogicImplから呼ぶこと。<BR>
 * コンストラクタでprocedureCallDao(PLSQL実行DAO)を設定すること。<BR>
 * ＤＢデータを使って、出荷実績に関する在庫・受払ソース操作（入力、取消）を行う。<BR>
 * 処理コードPLngOpno=2(出荷実績）受払区分PLngInoutdivision=4(出荷払い出し)で処理を行います。
 * @author a7710658
 */
class StockinoutForShipResult {

	/* ,orderHeadDao(受注ヘッダDAO),orderDetailDao(受注詳細DAO),itemInventryDao(品目在庫DAO)<BR> */

	/**
	 * コンストラクタ
	 * @param procedureCallDao PLSQL実行DAO
	 */
	public StockinoutForShipResult() {
	}

}
