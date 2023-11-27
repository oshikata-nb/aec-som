/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.master.numberchkdisit;

import java.util.List;

/**
 * 数値桁数チェックマスタメンテ 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface NumberChkDisitDetailDao {
	/** 区分コードに属する全て取得時のSA/TS、取引先のキーコード */
	String ALL = " ";

	/** BEANアノテーション */
	Class<NumberChkDisitDetail> BEAN = NumberChkDisitDetail.class;

	// インスタンスメソッド

	/** ARGSアノテーション findByPrimaryKey(). */
	String findByPrimaryKey_ARGS = "unitDivision,venderDivision,venderCd";

	/**
	 * 主キーで検索(VENDER_DIVISION、VENDER_CD=" "(半角スペース)の代表データも一緒に取得)
	 * @param unitDivision 区分
	 * @param venderDivision SI/TS    自社の場合は半角1桁
	 * @param venderCd 取引先  自社の場合は半角1桁
	 * @return List<NumberChkDisitDetail>
	 */
	List<NumberChkDisitDetail> findByPrimaryKey(String unitDivision, String venderDivision, String venderCd);

}
