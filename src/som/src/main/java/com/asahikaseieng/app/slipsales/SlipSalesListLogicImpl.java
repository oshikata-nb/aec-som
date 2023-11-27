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

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.common.SendMailConstants;
import com.asahikaseieng.app.common.SendMailLogic;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.dao.entity.master.mailtemplate.MailTemplate;
import com.asahikaseieng.dao.entity.master.mailtemplate.MailTemplateDao;
import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.entity.master.organization.OrganizationDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.sales.Sales;
import com.asahikaseieng.dao.entity.sales.SalesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.slipsales.SlipSalesCarryListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.slipsales.SlipSalesCarryListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.slipsales.SlipSalesCategoryDivisionForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.slipsales.SlipSalesCategoryDivisionForComboboxesDao;
import com.asahikaseieng.dao.nonentity.repsalesslipforreport.RepSalesSlipListForReport;
import com.asahikaseieng.dao.nonentity.repsalesslipforreport.RepSalesSlipListForReportDao;
import com.asahikaseieng.dao.nonentity.slipsales.SlipSalesList;
import com.asahikaseieng.dao.nonentity.slipsales.SlipSalesListDao;
import com.asahikaseieng.dao.nonentity.slipsales.SlipSalesListPagerCondition;
import com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesDetailListForReport;
import com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesListConditionForReport;
import com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesListForReport;
import com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesListForReportDao;
import com.asahikaseieng.dao.nonentity.vmailvalueentity.VMailValueEntity;
import com.asahikaseieng.dao.nonentity.vmailvalueentity.VMailValueEntityDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 売上伝票出力画面 ロジック実装クラス
 * @author tosco
 */
public class SlipSalesListLogicImpl implements SlipSalesListLogic {

	private SlipSalesListDao slipSalesListDao;

	private SlipSalesCategoryDivisionForComboboxesDao slipSalesCategoryDivisionForComboboxesDao;

	private SlipSalesCarryListForComboboxesDao slipSalesCarryListForComboboxesDao;

	private SalesDao salesDao;

	private RepSalesSlipListForReportDao repSalesSlipListForReportDao;

	private GetNumberLogic getNumberLogic;

	private SlipSalesListForReportDao slipSalesListForReportDao;

	private SlipSalesDetailListForReportDao slipSalesDetailListForReportDao;
	
	private VenderDao venderDao;
	
	private MailTemplateDao mailTemplateDao;
	
	private SendMailLogic sendMailLogic;
	
	private VMailValueEntityDao vMailValueEntityDao;
	
	private OrganizationDao organizationDao;

	/**
	 * コンストラクタ
	 */
	public SlipSalesListLogicImpl() {
	}

	/**
	 * 売上伝票出力画面 検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<SlipSalesList> 検索結果リスト
	 * 
	 * @throws NoDataException データが存在しない例外
	 * 
	 */
	public List<SlipSalesList> getSearchList(
			final SlipSalesListPagerCondition condition) throws NoDataException {
		checkParams(condition);
		List<SlipSalesList> list = slipSalesListDao.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 帳票Excelヘッダ 検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<SlipSalesListForReport> 検索結果リスト
	 * 
	 */
	public List<SlipSalesListForReport> getReportHeaderList(
			final SlipSalesListConditionForReport condition) {
		List<SlipSalesListForReport> list = slipSalesListForReportDao
				.getSearchList(condition);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel 詳細検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<SlipSalesDetailListForReport> 検索結果リスト
	 * 
	 */
	public List<SlipSalesDetailListForReport> getReportDetailList(
			final SlipSalesListConditionForReport condition) {
		List<SlipSalesDetailListForReport> list = slipSalesDetailListForReportDao
				.getDetailSearchList(condition);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 売上伝票番号付番処理
	 * 
	 * @param salesNo 出荷番号
	 * @param loginUserId ログインユーザ
	 * @param salesSearchList 画面上の検索結果 
	 * @return ArrayList<String> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public ArrayList<String> getAddSlipSalesNo(final ArrayList<String> salesNo,
			final String loginUserId, final List<SlipSalesList> salesSearchList) throws NoDataException {

		long index = 0;
		boolean startSetShippngSlipNo = false;

		checkSlipSalesParams(salesNo);

		ArrayList<String> salesNoList = new ArrayList<String>();

		// 売上番号で帳票出力する売上トランザクションを取得
		List<RepSalesSlipListForReport> list = repSalesSlipListForReportDao
				.getSalesList(salesNo);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		//　20221216 add S.Fujimaki
		// 売上付番処理を行うリストの売上番号、更新日付での一致の確認する。
		for(RepSalesSlipListForReport bean : list){
			for(SlipSalesList bean2 : salesSearchList){
				if(bean.getSalesNo().equals(bean2.getSalesNo())){
					if(!bean.getUpdateDate().equals(bean2.getUpdateDate())){
						// 更新日付が一件でも不一致であった場合 上位に例外をスロー
						throw new OptimisticLockRuntimeException();
					}
				}
			}
		}

		// 前の検索結果を保持
		RepSalesSlipListForReport beforBean = new RepSalesSlipListForReport();

		// 売上伝票番号を保持
		String slipSalesNo = new String();

		// 検索結果ループ
		for (RepSalesSlipListForReport bean : list) {

			// 更新データ取得処理
			Sales updateBean = salesDao.getEntity(bean.getSalesNo());

			// 売上伝票番号がＮＵＬＬでは無い場合（すでに発行済み）
			if (bean.getSalesSlipNo() != null) {
				// 出荷番号を保持
				slipSalesNo = bean.getSalesSlipNo();
				salesNoList.add(slipSalesNo);

				// 売上伝票番号がＮｕｌｌかつ売上伝票番号発行開始フラグがＯＦＦの場合
			} else if (bean.getSalesSlipNo() == null && !startSetShippngSlipNo) {
				startSetShippngSlipNo = true; // 売上伝票番号発行開始フラグ ON

				// 新規に売上伝票番号を付番号
				slipSalesNo = getNumberLogic.getSlipSalesNo();

				// 売上番号を保持
				salesNoList.add(slipSalesNo);
				index = 1;
				updateBean.setSalesSlipRowNo(new BigDecimal(index));

				// 集約単位が切り替わったかチェック
			} else if (checkSlipSalesParams(beforBean, bean)) {
				// 新規に売上伝票番号を付番号
				slipSalesNo = getNumberLogic.getSlipSalesNo();

				// 出荷番号を保持
				salesNoList.add(slipSalesNo);
				index = 1;
				updateBean.setSalesSlipRowNo(new BigDecimal(index));
			} else {
				updateBean.setSalesSlipRowNo(new BigDecimal(++index));
			}

			// 売上伝票番号設定
			updateBean.setSalesSlipNo(slipSalesNo);
			// 更新者設定
			updateBean.setUpdatorCd(loginUserId);
			// 排他用に更新日時設定
			updateBean.setUpdateDate(bean.getUpdateDate());
			
			try {
				// 出荷番号をキーに更新を行う
				salesDao.update(updateBean);
				// 更新時に、すでに更新されていた場合
			} catch (NotSingleRowUpdatedRuntimeException e) {
				throw new OptimisticLockRuntimeException();
			}

			// 以前のデータを保持
			beforBean = bean;
		}

		return salesNoList;
	}
	/**　20230302 S.Fujimaki Add
	 * 伝票発行済みフラグ更新処理
	 * 
	 * @param salesNo 出荷番号
	 * @param loginUserId ログインユーザ
	 * @param updateDate 排他用更新日付 
	 * @throws NoDataException データが存在しない例外
	 */
	public void updateSlipPublishComp(final String salesNo, final String loginUserId)throws NoDataException{
		
		// 売上番号から更新対象を読み込み
		Sales updateBean = salesDao.getEntity(salesNo);
		// 更新者設定
		updateBean.setUpdatorCd(loginUserId);
		// 発行済みフラグＯＮ
		updateBean.setSlipPublishComp(new BigDecimal(1));
		// 伝票発行日設定
		updateBean.setSlipPublishDate(AecDateUtils.getCurrentTimestamp());
		
		try {
			// 出荷番号をキーに更新を行う
			salesDao.update(updateBean);
			// 更新時に、すでに更新されていた場合
		} catch (NotSingleRowUpdatedRuntimeException e) {
			throw new OptimisticLockRuntimeException();
		}
	}
	
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
	public ActionMessages sendMail(final String salesNo, final String slipSalesNo,
			final List<String> filePathList, final int mailRowNo, final String tantoCd,final BigDecimal acetionSeq) throws MessagingException {
		ActionMessages messages = new ActionMessages();
		
		// 更新対象データを取得する
		String mailFormatId = null;
		List<String> subjectParamCsv = createMailSubjectParam(slipSalesNo, tantoCd);
		
		//取引先コード取得
		Sales bean = salesDao.getEntity(salesNo);
		//取引先から売上メールと売上FAXの送信区分を取得
		Vender venderBean = venderDao.getEntity(bean.getVenderCd(), "TS");
		
		// =====================================================================================================================================
		// 売上【メール】の送信区分が1:送信するの場合メール送信
		// =====================================================================================================================================
		if(AecNumberUtils.convertBigDecimalNullToZeroFromBigDecimal(venderBean.getSalesMailOutput()).compareTo(BigDecimal.ONE) == 0){
			mailFormatId = "S_10001";
			List<String> bodyParamCsv = createMailBodyParamCsv(mailFormatId, slipSalesNo , tantoCd);
			// メール送信
			boolean sendMail = sendMailLogic.sendMailVender("TS", bean.getVenderCd(), mailFormatId, subjectParamCsv, 
				bodyParamCsv, filePathList, tantoCd, SendMailConstants.SEND_MODE_MAIL_ONLY, SendMailConstants.GET_SALES_INFO,acetionSeq);
			//メール送信失敗時はエラーメッセージ追加
			if(!sendMail){
				//{0}行目:メール送信時に問題が生じました。
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.mail.send.row", mailRowNo));
			}
		}
		AecNumberUtils.convertBigDecimalNullToZeroFromBigDecimal(venderBean.getOrderMailOutput());
		// =====================================================================================================================================
		// 売上【FAX】の送信区分が1:送信するの場合FAX送信
		// =====================================================================================================================================
		if(AecNumberUtils.convertBigDecimalNullToZeroFromBigDecimal(venderBean.getSalesFaxOutput()).compareTo(BigDecimal.ONE) == 0){
			mailFormatId = "S_20001";
			List<String> bodyParamCsv = createMailBodyParamCsv(mailFormatId, slipSalesNo , tantoCd);
			// メール送信
			boolean sendMail = sendMailLogic.sendMailVender("TS", bean.getVenderCd(), mailFormatId, subjectParamCsv, 
				bodyParamCsv, filePathList, tantoCd, SendMailConstants.SEND_MODE_AUTO_FAX, SendMailConstants.GET_SALES_INFO,acetionSeq);
			//FAX送信失敗時はエラーメッセージ追加
			if(!sendMail){
				//{0}行目:FAX送信時に問題が生じました。
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.fax.send.row", mailRowNo));
			}
		}
		
		return messages;
	}
	
	/**
	 * 通知メール用の件名パラメータリスト作成
	 * 
	 * @param updateStatus
	 * @param bean
	 * @param tantoCd
	 * @return List<String>
	 */
	private List<String> createMailSubjectParam(final String salesSlipNo, final String tantoCd) {

		List<String> subjectParamCsv = new ArrayList<String>();
		
		return subjectParamCsv;
	}

	/**
	 * 通知メール用の本文パラメータリスト作成
	 * 
	 * @param updateStatus
	 * @param purchaseMode
	 * @param bean
	 * @param tantoCd
	 * @param locale
	 * @param url
	 * @return List<String>
	 */
	private List<String> createMailBodyParamCsv(final String mailFormatId, final String salesSlipNo, final String tantoCd) {

		List<String> bodyParamCsv = new ArrayList<String>();

		// パラメータになる値を取得
		List<VMailValueEntity> mailValue = vMailValueEntityDao.getParam(mailFormatId, salesSlipNo, null, null, null, null);
		
		for(VMailValueEntity bean : mailValue){
			bodyParamCsv.add(bean.getVals());
		}
		
		return bodyParamCsv;
	}

	/**
	 * 集約単位変更チェック
	 * @param beforBean 出荷番号
	 * @param bean 出荷番号
	 * @return boolean True：集約単位切り替わり
	 */
	private boolean checkSlipSalesParams(
			final RepSalesSlipListForReport beforBean,
			final RepSalesSlipListForReport bean) {

		// 得意先が同一か？
		if (beforBean.getVenderCd().equals(bean.getVenderCd())) {

			// 納入先が同一か？
			if (beforBean.getDeliveryCd().equals(bean.getDeliveryCd())) {

				// 出荷予定日が同一か？
				if (beforBean.getScheduledShippingDate().equals(
					bean.getScheduledShippingDate())) {

					// 発注番号が同一か？
					if (beforBean.getBuySubcontractOrderNo().equals(
						bean.getBuySubcontractOrderNo())) {

						// 運送会社が同一か？
						if (beforBean.getCarryCd().equals(bean.getCarryCd())) {

							// 運送会社が同一か？
							if (beforBean.getCarryCd()
									.equals(bean.getCarryCd())) {

								// 上位ロケが同一か？
								if (beforBean.getUpperLocationCd().equals(
									bean.getUpperLocationCd())) {

									// 受注番号が同一か？
									if (beforBean.getOrderNo().equals(
										bean.getOrderNo())) {

										// 入力区分が同一か？
										if (beforBean
												.getInputDivision()
												.equals(bean.getInputDivision())) {

											// 預り品フラグが同一か？
											if (beforBean.getKeepFlag().equals(
												bean.getKeepFlag())) {

												// 売上区分が同一か？
												if (beforBean.getKeepFlag()
														.equals(
															bean.getKeepFlag())) {

													// 勘定年月が同一か？
													if (beforBean
															.getAccountYears()
															.equals(
																bean
																		.getAccountYears())) {

														// 分類
														if (beforBean
																.getCategoryDivision()
																.equals(
																	bean
																			.getCategoryDivision())) {

															// 売上日
															if (beforBean
																	.getSalesDate()
																	.equals(
																		bean
																				.getSalesDate())) {

																return false;
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return true;
	}

	/**
	 * パラメータチェック
	 * @param shippingNo 出荷番号
	 */
	private void checkSlipSalesParams(final ArrayList<String> shippingNo) {

		if (shippingNo == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 選択した売上伝票の取り消し処理を行う
	 * @param frm 出荷帳票画面フォーム
	 * @param loginUserId ログインユーザ
	 * @throws Exception 例外
	 */
	public void canselSlipPublish(final SlipSalesListForm frm,
			final String loginUserId) throws Exception {
		List<SlipSalesList> srarchList = frm.getSearchList();

		for (SlipSalesList bean : srarchList) {
			// チェックのないものは読み飛ばす
			if (!bean.isSlipSalesCheckBox()) {
				continue;
			}

			// 更新データ格納用Bean
			Sales updateBean = new Sales();
			// 取得
			updateBean = salesDao.getEntity(bean.getSalesNo());

			// 売上伝票番号
			updateBean.setSalesSlipNo(null);
			// 売上伝票行番号
			updateBean.setSalesSlipRowNo(null);
			// 伝票発行済区分
			updateBean.setSlipPublishComp(new BigDecimal(0));
			// 伝票発行日
			updateBean.setSlipPublishDate(null);

			// 更新者セット
			updateBean.setUpdatorCd(loginUserId);
			// 排他用に更新日時セット
			updateBean.setUpdateDate(bean.getUpdateDate());

			try {
				// 売上番号番号をキーに更新を行う
				salesDao.update(updateBean);
				// 更新時に、すでに更新されていた場合
			} catch (NotSingleRowUpdatedRuntimeException e) {
				throw new OptimisticLockRuntimeException();
			}
		}
	}

	/**
	 * 売上区分一覧取得
	 * @return List<SlipSalesCategoryDivisionForComboboxes>
	 */
	public List<SlipSalesCategoryDivisionForComboboxes> getCategoryDivisionList() {
		return slipSalesCategoryDivisionForComboboxesDao
				.getCategoryDivisionList();
	}
	
	/**
	 * メールテンプレート取得
	 * @param mailFormatId String
	 * @return MailTemplate
	 */
	public MailTemplate getMailTemplate(final String mailFormatId){
		
		MailTemplate bean = mailTemplateDao.getTemplate(mailFormatId);
		
		return bean;
	}

	/**
	 * 売上区分コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createCategoryDivisionCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 全てを追加
		ComboBoxItems allItem = new ComboBoxItems();
		allItem.setValues("0");
		allItem.setLabales("すべて");
		res.add(allItem);

		// 売上区分検索
		List<SlipSalesCategoryDivisionForComboboxes> lineList = getCategoryDivisionList();
		for (SlipSalesCategoryDivisionForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getCategoryDivision());
			item.setLabales(bean.getCategoryName());
			res.add(item);
		}
		return res;
	}

	/**
	 * 運送会社を全件取得する
	 * @return List<SlipSalesCarryListForComboboxes>
	 */
	public List<SlipSalesCarryListForComboboxes> getAllCarry() {
		List<SlipSalesCarryListForComboboxes> list = slipSalesCarryListForComboboxesDao
				.getListForComboboxes();
		return list;
	}

	/**
	 * 運送会社コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> getCreateCarryCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 運送会社全件検索
		List<SlipSalesCarryListForComboboxes> lineList = getAllCarry();

		// すべてを先頭に設定
		if (true) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues("0");
			item.setLabales("すべて");
			res.add(item);
		}

		for (SlipSalesCarryListForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			if (bean.getCarryName1() != null) {
				if (bean.getCarryName2() != null) {
					item.setLabales(bean.getCarryName1() + " "
							+ bean.getCarryName2());
					item.setValues(bean.getCarryCd());
				} else {
					item.setLabales(bean.getCarryName1());
					item.setValues(bean.getCarryCd());
				}
			}
			res.add(item);
		}
		return res;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final SlipSalesListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * slipSalesListDaoを設定します。
	 * @param slipSalesListDao slipSalesListDao
	 */
	public void setSlipSalesListDao(final SlipSalesListDao slipSalesListDao) {
		this.slipSalesListDao = slipSalesListDao;

	}

	/**
	 * slipSalesCategoryDivisionForComboboxesDaoを取得します。
	 * @return slipSalesCategoryDivisionForComboboxesDao
	 */
	public SlipSalesCategoryDivisionForComboboxesDao getSlipSalesCategoryDivisionForComboboxesDao() {
		return slipSalesCategoryDivisionForComboboxesDao;
	}

	/**
	 * slipSalesCategoryDivisionForComboboxesDaoを設定します。
	 * @param slipSalesCategoryDivisionForComboboxesDao
	 *            slipSalesCategoryDivisionForComboboxesDao
	 */
	public void setSlipSalesCategoryDivisionForComboboxesDao(
			final SlipSalesCategoryDivisionForComboboxesDao slipSalesCategoryDivisionForComboboxesDao) {
		this.slipSalesCategoryDivisionForComboboxesDao = slipSalesCategoryDivisionForComboboxesDao;
	}

	/**
	 * slipSalesCarryListForComboboxesDaoを設定します。
	 * @param slipSalesCarryListForComboboxesDao
	 *            slipSalesCarryListForComboboxesDao
	 */
	public void setSlipSalesCarryListForComboboxesDao(
			final SlipSalesCarryListForComboboxesDao slipSalesCarryListForComboboxesDao) {
		this.slipSalesCarryListForComboboxesDao = slipSalesCarryListForComboboxesDao;
	}

	/**
	 * salesDaoを設定します。
	 * @param salesDao salesDao
	 */
	public void setSalesDao(final SalesDao salesDao) {
		this.salesDao = salesDao;
	}

	/**
	 * repSalesSlipListForReportDaoを設定します。
	 * @param repSalesSlipListForReportDao repSalesSlipListForReportDao
	 */
	public void setRepSalesSlipListForReportDao(
			final RepSalesSlipListForReportDao repSalesSlipListForReportDao) {
		this.repSalesSlipListForReportDao = repSalesSlipListForReportDao;
	}

	/**
	 * getNumberLogicを設定します。
	 * @param getNumberLogic getNumberLogic
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * slipSalesListForReportDaoを設定します。
	 * @param slipSalesListForReportDao slipSalesListForReportDao
	 */
	public void setSlipSalesListForReportDao(
			final SlipSalesListForReportDao slipSalesListForReportDao) {
		this.slipSalesListForReportDao = slipSalesListForReportDao;
	}

	/**
	 * slipSalesDetailListForReportDaoを設定します。
	 * @param slipSalesDetailListForReportDao slipSalesDetailListForReportDao
	 */
	public void setSlipSalesDetailListForReportDao(
			final SlipSalesDetailListForReportDao slipSalesDetailListForReportDao) {
		this.slipSalesDetailListForReportDao = slipSalesDetailListForReportDao;
	}

	/**
	 * venderDaoを設定します。
	 * @param venderDao venderDao
	 */
	public void setVenderDao(VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * mailTemplateDaoを設定します。
	 * @param mailTemplateDao mailTemplateDao
	 */
	public void setMailTemplateDao(MailTemplateDao mailTemplateDao) {
		this.mailTemplateDao = mailTemplateDao;
	}

	/**
	 * sendMailLogicを設定します。
	 * @param sendMailLogic sendMailLogic
	 */
	public void setSendMailLogic(SendMailLogic sendMailLogic) {
		this.sendMailLogic = sendMailLogic;
	}

	/**
	 * vMailValueS10001EntityDaoを設定します。
	 * @param vMailValueS10001EntityDao vMailValueS10001EntityDao
	 */
	public void setvMailValueS10001EntityDao(
			VMailValueEntityDao vMailValueEntityDao) {
		this.vMailValueEntityDao = vMailValueEntityDao;
	}

	/**
	 * organizationDaoを設定します。
	 * @param organizationDao organizationDao
	 */
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
	
}
