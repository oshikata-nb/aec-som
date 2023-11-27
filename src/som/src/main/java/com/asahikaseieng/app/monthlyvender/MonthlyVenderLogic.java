/*
 * Created on 2009/04/06
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.monthlyvender;

import java.util.List;

import com.asahikaseieng.dao.nonentity.monthlyvenderforreport.RepMonthlyVender;

/**
 * 得意先別管理月報 ロジッククラス interface.
 * @author t1344224
 */
public interface MonthlyVenderLogic {

	/**
	 * Listメソッド
	 * 
	 * @param organizationCd 部署コード
	 * @param dateFrom 開始月
	 * @param dateTo 終了月
	 * @return List<InoutRecordList>
	 */
	List<RepMonthlyVender> getList(final String organizationCd,
			final String dateFrom, final String dateTo);

}
