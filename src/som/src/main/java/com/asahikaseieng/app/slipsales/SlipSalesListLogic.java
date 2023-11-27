/*
 * Created on 2009/02/27
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.slipsales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.entity.master.mailtemplate.MailTemplate;
import com.asahikaseieng.dao.nonentity.slipsales.SlipSalesList;
import com.asahikaseieng.dao.nonentity.slipsales.SlipSalesListPagerCondition;
import com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesDetailListForReport;
import com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesListConditionForReport;
import com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 売上伝票出力画面 ロジッククラス interface.
 * @author tosco
 */
public interface SlipSalesListLogic {

	/**
	 * 検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<SlipSalesList> データリスト
	 * 
	 * @throws NoDataException データが存在しない例外
	 * 
	 */
	List<SlipSalesList> getSearchList(
			final SlipSalesListPagerCondition condition) throws NoDataException;

	/**
	 * 帳票Excelヘッダ 検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<SlipSalesListForReport> 検索結果リスト
	 * 
	 */
	List<SlipSalesListForReport> getReportHeaderList(
			final SlipSalesListConditionForReport condition);

	/**
	 * 帳票Excel 詳細検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<SlipSalesDetailListForReport> 検索結果リスト
	 * 
	 */
	List<SlipSalesDetailListForReport> getReportDetailList(
			final SlipSalesListConditionForReport condition);

	/**
	 * 売上伝票番号の付番を行う.
	 * @param salesNo 伝票発行出荷番号
	 * @param loginUserId ログインユーザー
	 * @return ArrayList<String> 発行伝票番号
	 * @throws NoDataException データが存在しない例外
	 */
	ArrayList<String> getAddSlipSalesNo(final ArrayList<String> salesNo,
			final String loginUserId, final List<SlipSalesList> salesSearchList) throws NoDataException;

	/**
	 * 選択した売上伝票の取り消し処理を行う
	 * @param frm 出荷帳票画面フォーム
	 * @param loginUserId ログインユーザ
	 * @throws Exception 例外
	 */
	void canselSlipPublish(final SlipSalesListForm frm, final String loginUserId)
			throws Exception;
	
	/**
	 * 売上伝票返信処理
	 * 
	 * @param frm
	 * @param tantoCd
	 * @param updateStatus
	 * @param locale
	 * @param url
	 * @return
	 * @throws MessagingException
	 */
	ActionMessages sendMail(final String salesNo, final String slipSalesNo,
			final List<String> filePathList, final int mailRowNo, final String tantoCd, final BigDecimal actionSeq) throws MessagingException;
	
	/**
	 * メールテンプレート取得
	 * @param mailFormatId String
	 * @return MailTemplate
	 */
	MailTemplate getMailTemplate(final String mailFormatId);

	/**
	 * 売上区分コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createCategoryDivisionCombobox();

	/**
	 * 運送会社コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> getCreateCarryCombobox();

	/**
	 * 伝票発行済みフラグ更新 20230302add
	 * @param salesNo String
	 * @param loginUserId String
	 * @throws NoDataException データが存在しない例外
	 * */
	void updateSlipPublishComp(String salesNo, String loginUserId)throws NoDataException;

}
