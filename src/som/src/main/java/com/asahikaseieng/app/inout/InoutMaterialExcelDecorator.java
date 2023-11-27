/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inoutmaterial.InoutMaterial;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 原材料別入出庫一覧ＥＸＣＥＬファイル作成用 interface.
 * @author t1344224
 */
public interface InoutMaterialExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param dateFrom 開始月
	 * @param dateTo 終了月
	 * @param list 出力データ
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final String dateFrom, final String dateTo,
			final List<InoutMaterial> list);
}
