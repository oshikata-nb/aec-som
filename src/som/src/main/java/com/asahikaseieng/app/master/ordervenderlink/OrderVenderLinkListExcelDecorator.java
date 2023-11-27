/*
 * Created on 2020/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.ordervenderlink;

import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.受注データ得意先連携マスタ一覧
 * @author 
 */
public interface OrderVenderLinkListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param venderGroupCd 得意先グループ番号
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final String venderGroupCd);
}
