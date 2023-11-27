/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.slipshipping;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.ARRAY;

import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 出荷伝票ＥＸＣＥＬファイル作成用 interface.
 * @author t1344224
 */
public interface SlipShippingOrderListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param slipShippingNo 出荷伝票番号
	 * @param shippingNo 出荷番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final ArrayList<String> slipShippingNo,
			final ArrayList<String> shippingNo, final String tantoName,
			final Timestamp currentDate, final String ipAddress);

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param shippingNo 出荷番号
	 * @param tantoName 出力担当者
	 * @param organizationCd 部署コード
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReportRequest(final ArrayList<String> shippingNo,
			final String tantoName, final String organizationCd,
			final Timestamp currentDate, final String ipAddress);
	
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param shippingNo 出荷番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReportFare(final ArrayList<String> shippingNo,
			final String tantoName, final String organizationCd,
			final Timestamp currentDate, final String ipAddress);

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param shippingNo 出荷番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReportDirection(final ArrayList<String> shippingNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress);

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param shippingNo 出荷番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReportSchedule(final ArrayList<String> shippingNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress);

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param shippingNo 出荷番号
	 * @param tantoName 出力担当者
	 * @param organizationCd 部署コード
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReportTag(final ArrayList<String> shippingNo,
			final String tantoName, final String organizationCd,
			final Timestamp currentDate, final String ipAddress);
	
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param shippingNo 出荷番号
	 * @param tantoName 出力担当者
	 * @param organizationCd 部署コード
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	public FileDownloadInfo createReportDelivery(
			final ArrayList<String> shippingNo, final String tantoName,
			final String organizationCd, final Timestamp currentDate,
			final String ipAddress , final String loginUserId) throws NoDataException;
	
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param shippingNo 出荷番号
	 * @param tantoName 出力担当者
	 * @param postalClientCd 郵政依頼主
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReportPostal(final ArrayList<String> shippingNo,
			final String tantoName, final String postalClientCd,
			final Timestamp currentDate, final String ipAddress)throws IOException;
	
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param shippingNo 出荷番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReportNewTag(final ArrayList<String> shippingNoList,
			final String tantoName,
			final Timestamp currentDate, final String ipAddress)throws IOException;

	/**
	 * {@inheritDoc}
	 * 運送会社連携ファイル
	 * @throws IOException 
	 */
	public FileDownloadInfo createCarryFile(
			final String carryCd ,
			final List<String> shippingNoList, final String tantoName,
			final String postalClientCd, final Timestamp currentDate,
			final String ipAddress) throws IOException , SQLException , SlipShippingLogicException;

}
