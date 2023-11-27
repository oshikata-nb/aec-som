/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repproductionplanforreport;

import java.util.List;

/**
 * RepProductionPlanCalendarDaoクラス
 * @author kanri-user
 */
public interface RepProductionPlanCalendarDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanCalendar.class;

	/** ARGSアノテーション getCalendarData */
	String getCalendarData_ARGS = "procDate";

	/**
	 * RepProductionPlanCalendarメソッド
	 * 
	 * @param procDate procDate
	 * @return List<RepProductionPlanCalendar>
	 */
	List<RepProductionPlanCalendar> getCalendarData(final String procDate);
}
