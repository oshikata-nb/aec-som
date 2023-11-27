/*
 * Created on Tue Apr 21 14:40:20 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tdataseihinnyuka;

import java.sql.Timestamp;

/**
 * TDataSeihinNyukaクラス.
 * @author a7710658
 */
public class TDataSeihinNyuka extends TDataSeihinNyukaBase {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TDataSeihinNyuka() {
		super();
	}

	/**
	 * nonyuyoteibi取得.
	 * @return nonyuyoteibi
	 */
	public java.sql.Timestamp getTsNonyuyoteibi() {
		String dt = getNonyuyoteibi();
		Timestamp ts = Timestamp.valueOf(dt);
		return ts;
	}

	/**
	 * nonyuyoteibi設定.
	 * @param nonyuyoteibi nonyuyoteibi
	 */
	public void setTsNonyuyoteibi(final java.sql.Timestamp nonyuyoteibi) {
		String dt = nonyuyoteibi.toString();
		setNonyuyoteibi(dt);
	}
	//
	// /**
	// * strNonyuyoteibi取得.
	// * @return nonyuyoteibi
	// */
	// public String getStrNonyuyoteibi() {
	// SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
	// Date dt = getNonyuyoteibi();
	// if (dt == null) {
	// return null;
	// }
	// return df.format(dt);
	// }
	//
	// /**
	// * StrNonyuyoteibi設定.
	// * @param strNonyuyoteibi strNonyuyoteibi
	// */
	// public void setStrNonyuyoteibi(final String strNonyuyoteibi) {
	// setTsNonyuyoteibi(AecDateUtils.getTimestampYmdFormat(strNonyuyoteibi));
	// }
}
