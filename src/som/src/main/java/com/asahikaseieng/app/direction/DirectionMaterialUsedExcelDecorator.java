/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.nonentity.directionmaterialusedforreport.RepDirectionMaterialUsed;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 製品別原材料消費量リストＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface DirectionMaterialUsedExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param list list
	 * @param dateFrom 開始月
	 * @param dateTo 終了月
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final List<RepDirectionMaterialUsed> list,
			final String dateFrom, final String dateTo, final String tantoName,
			final Timestamp currentDate);
}
