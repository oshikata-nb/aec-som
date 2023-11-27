package com.asahikaseieng.app.monthlyvender;

import java.util.List;

import com.asahikaseieng.dao.nonentity.monthlyvenderforreport.RepMonthlyVender;
import com.asahikaseieng.dao.nonentity.monthlyvenderforreport.RepMonthlyVenderDao;

/**
 * 得意先別管理月報 ロジック実装クラス
 * @author t1344224
 */
public class MonthlyVenderLogicImpl implements MonthlyVenderLogic {

	private RepMonthlyVenderDao repMonthlyVenderDao;

	/**
	 * コンストラクタ.
	 */
	public MonthlyVenderLogicImpl() {
	}

	/**
	 * Listメソッド
	 * 
	 * @param organizationCd 部署コード
	 * @param dateFrom 開始月
	 * @param dateTo 終了月
	 * @return List<InoutRecordList>
	 */
	public List<RepMonthlyVender> getList(final String organizationCd,
			final String dateFrom, final String dateTo) {

		List<RepMonthlyVender> list = repMonthlyVenderDao.getListForReport(
			organizationCd, dateFrom, dateTo);

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 得意先別管理月報検索用Dao
	 * @param repMonthlyVenderDao 得意先別管理月報検索用Dao
	 */
	public void setRepMonthlyVenderDao(
			final RepMonthlyVenderDao repMonthlyVenderDao) {
		this.repMonthlyVenderDao = repMonthlyVenderDao;
	}

}
