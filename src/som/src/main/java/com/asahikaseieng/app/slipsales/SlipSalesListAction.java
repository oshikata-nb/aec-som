/*
 * Created on 2009/02/27
 *
 * $copyright$
 * 売上トランザクション
 *
 */
package com.asahikaseieng.app.slipsales;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.common.pdfReport.PdfReportDecorator;
import com.asahikaseieng.app.slipshipping.SlipShippingLogicException;
import com.asahikaseieng.dao.entity.master.mailtemplate.MailTemplate;
import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.entity.master.organization.OrganizationDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.sales.Sales;
import com.asahikaseieng.dao.entity.sales.SalesDao;
import com.asahikaseieng.dao.entity.slipsalesactionlog.SlipSalesActionLog;
import com.asahikaseieng.dao.entity.slipsalesactionlog.SlipSalesActionLogDao;
import com.asahikaseieng.dao.nonentity.common.pdfreportdecorator.PdfReportDecoratorDao;
import com.asahikaseieng.dao.nonentity.slipsales.SlipSalesList;
import com.asahikaseieng.dao.nonentity.slipsales.SlipSalesListPagerCondition;
import com.asahikaseieng.dao.nonentity.slipsalesforreport.SlipSalesListConditionForReport;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 売上伝票出力画面 Actionクラス.
 * @author tosco
 * 
 */
public final class SlipSalesListAction extends AbstractSearchAction {

	private SlipSalesListLogic slipSalesListLogic;

	private SlipSalesListExcelDecorator slipSalesListExcelDecorator;

	private SlipSalesExcelDecorator slipSalesExcelDecorator;
	
	private PdfReportDecorator pdfReportDecorator;
	
	private PdfReportDecoratorDao pdfReportDecoratorDao;
	
	private VenderDao venderDao;

	private OrganizationDao organizationDao;

	private SalesDao salesDao;
	
	private SlipSalesActionLogDao slipSalesActionLogDao;

	/**
	 * コンストラクタ.
	 */
	public SlipSalesListAction() {
		super();
	}

	/**
	 * 初期処理(メニューから遷移時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		SlipSalesListForm listForm = (SlipSalesListForm) form;

		// 売上区分コンボボックス
		listForm.setCategoryCombo(slipSalesListLogic
				.createCategoryDivisionCombobox());
		//20210413  項番204初期設定部門コード部門名　開始
		/* 初期表示：部門 */
		listForm.setSrhChargeOrganizationCd(getLoginInfo(request).getOrganizationCd());
		listForm.setSrhChargeOrganizationName(getLoginInfo(request).getOrganizationName());
		//20210413 項番204初期設定部門コード部門名　終了
		// 運送店コンボボックス
		listForm.setCarryCombo(slipSalesListLogic.getCreateCarryCombobox());

		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * 売上伝票出力画面検索処理(検索ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		SlipSalesListForm frm = (SlipSalesListForm) form;

		// クリア
		if (!frm.getOp().equals("reFresh")) {
			frm.setExcelDownloadFlg(Boolean.FALSE);
		}

		// クリア
		frm.setSearchList(new ArrayList<SlipSalesList>());

		// 検索条件を取得
		SlipSalesListPagerCondition condition = (SlipSalesListPagerCondition) frm
				.getPager().getPagerCondition();
		// 検索条件をセット
		condition.setSrhSalesNo(frm.getSrhSalesNo());
		condition.setSrhCategoryDivision(frm.getSrhCategoryDivision());
		condition.setSrhCancelFlg(frm.getSrhCancelFlg());
		condition.setSrhOrderNoFrom(frm.getSrhOrderNoFrom());
		condition.setSrhOrderNoTo(frm.getSrhOrderNoTo());
		condition.setSrhSalesDateFrom(frm.getSrhSalesDateFrom());
		condition.setSrhSalesDateTo(frm.getSrhSalesDateTo());
		String years = StringUtils.replace(frm.getSrhAccountYears(), "/", "");
		condition.setSrhAccountYears(years);
		condition.setSrhTmpUnitpriceFlg(frm.getSrhTmpUnitpriceFlg());
		condition.setSrhKeepFlag(frm.getSrhKeepFlag());
		condition.setSrhMonthlyUpdateFlag(frm.getSrhMonthlyUpdateFlag());
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhChargeOrganizationCd(frm.getSrhChargeOrganizationCd());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());
		// 売上伝票発行済
		if (frm.getSrhSlipPublishComp() == Boolean.TRUE) {
			condition.setSrhSlipPublishComp(new BigDecimal(1));
		} else {
			condition.setSrhSlipPublishComp(new BigDecimal(0));
		}

		// 売上伝票NO
		condition.setSrhSalesSlipNo(frm.getSrhSalesSlipNo());
		// 出荷予定日
		condition
				.setSrhScheduledShippingDate(frm.getSrhScheduledShippingDate());
		// 納入予定日
		condition.setSrhDeliveryExpectedDate(frm.getSrhDeliveryExpectedDate());
		// 注文番号
		condition.setSrhCustomerOrderNo(frm.getSrhCustomerOrderNo());
		// 運送店コード
		condition.setSrhCarryCd(frm.getSrhCarryCd());
		// ロケーションコード
		condition.setSrhLocationCd(frm.getSrhLocationCd());
		// 納入先コード
		condition.setSrhDeliveryCd(frm.getSrhDeliveryCd());

		// 2015/9/7 自動FAX対応
		condition.setSrhSlipDateFrom(frm.getSrhSlipDateFrom());
		condition.setSrhSlipDateTo(frm.getSrhSlipDateTo());
		condition.setSrhSlipSendComp(frm.getSrhSlipSendComp());
		condition.setSrhFaxOutput(frm.getSrhFaxOutput());
		
		condition.setSrhSalesFaxOutput(frm.getSrhSalesFaxOutput());
		condition.setSrhSalesMailOutput(frm.getSrhSalesMailOutput());

		/* 帳票(Excel)用に検索条件を保持 */
		SlipSalesListConditionForReport reportCondition = new SlipSalesListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);
		

		// 検索
		frm.setSearchList(slipSalesListLogic.getSearchList(condition));
		return mapping.findForward("success");
	}

	/**
	 * 伝票取消処理(伝票取消ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward canselSlipPublish(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		SlipSalesListForm frm = (SlipSalesListForm) form;

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		try {
			// 伝票取消処理
			slipSalesListLogic.canselSlipPublish(frm, loginUserId);

		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}
		// 更新完了メッセージを登録
		saveMessage(request, "message.slipsales.complete.cancel");

		return mapping.findForward("reSearch");
	}

	/**
	 * 帳票処理(検索画面の帳票ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		SlipSalesListForm frm = (SlipSalesListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = slipSalesExcelDecorator.createReport(frm
				.getReportCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);

		return mapping.findForward("success");

	}

	/**
	 * 印刷処理(印刷ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward slipPublish(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		// 操作シーケンスを作成
		BigDecimal actionSeq = this.slipSalesActionLogDao.getActionSeq();
		
		//　ログ登録処理
		insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"slipPublish","start");
		
		SlipSalesListForm listForm = (SlipSalesListForm) form;
		ArrayList<String> salesNoList = new ArrayList<String>();
		//20221215 add S.Fujimaki start 検索結果と売上更新情報が、検索時の状態と一致するか確認する。
		List<SlipSalesList> salesSearchList = listForm.getSearchList();
		boolean isSelected = false;

		String forward = "success";

		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		try {
			for (SlipSalesList bean : listForm.getSearchList()) {
				if (!bean.isSlipSalesCheckBox()) {
					continue;
				} else {
					//　ログ登録処理
					insertLog(actionSeq,loginUserId,"slipPublish",bean.getSalesNo());
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					salesNoList.add(bean.getSalesNo());

				}

			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipsales.selected.checkbox");
				throw ex;
			}

			// 売上伝票番号付番処理
			ArrayList<String> salesSlipNoList = slipSalesListLogic
					.getAddSlipSalesNo(salesNoList, loginUserId, salesSearchList);
			FileDownloadInfo info = null;

			/* 売上伝票出力処理 */
			info = slipSalesListExcelDecorator.createReport(salesSlipNoList,
				salesNoList, loginUserId, null, request.getRemoteAddr(),
				"PRINT");
			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			insertLog(actionSeq,loginUserId,"report:file", info.getPath() );
			
			// 発行済みフラグの追加
			for(String sendSalesNo : salesNoList){
				slipSalesListLogic.updateSlipPublishComp(sendSalesNo, loginUserId);
			}
			
			// ExcelダウンロードフラグＯＮ
			listForm.setExcelDownloadFlg(true);

			forward = "reFresh";

		} catch (SlipShippingLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
		}
		insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"slipPublish","end");

		return mapping.findForward(forward);
	}
	/**
	 * insertLog
	 * 
	 * @param tantoCd String
	 * @param action String
	 * @param actionMsg String
	 */
	public void insertLog(BigDecimal seq,String tantoCd,String action,String actionMsg){

		SlipSalesActionLog bean = new SlipSalesActionLog();
		bean.setActionSeq(seq);
		bean.setLogDate(AecDateUtils.getCurrentTimestamp());
		bean.setTantoCd(tantoCd);
		bean.setAction(action);
		bean.setActionMsg(actionMsg);
		slipSalesActionLogDao.insert(bean);
	}

	/**
	 * 送信処理(送信ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward slipSend(final ActionMapping mapping,final ActionForm form, final HttpServletRequest request,final HttpServletResponse response) throws Exception {

		// 操作シーケンスを作成
		BigDecimal actionSeq = this.slipSalesActionLogDao.getActionSeq();
		//　ログ登録処理
		insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"slipSend","start");
		
		SlipSalesListForm listForm = (SlipSalesListForm) form;
		ArrayList<String> salesNoList = new ArrayList<String>();
		//20221215 add S.Fujimaki start 検索結果と売上更新情報が、検索時の状態と一致するか確認する。
		List<SlipSalesList> salesSearchList = listForm.getSearchList();
		boolean isSelected = false;

		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();
		int mailRowNo = 1;
		ActionMessages messages = new ActionMessages();
		
		// =========================================================================================================================================
		// 初期チェック
		// =========================================================================================================================================
		for (SlipSalesList bean : listForm.getSearchList()) {
			if (bean.isSlipSalesCheckBox()) {

				Vender venderBean = venderDao.getEntity(bean.getVenderCd(), "TS");

				// 売上メール・FAXの送信区分がどちらも1:送信するではない場合、エラー
				if(AecNumberUtils.convertBigDecimalNullToZeroFromBigDecimal(venderBean.getSalesFaxOutput()).compareTo(BigDecimal.ONE) != 0 &&
						AecNumberUtils.convertBigDecimalNullToZeroFromBigDecimal(venderBean.getSalesMailOutput()).compareTo(BigDecimal.ONE) != 0){
					// {0}行目:売上メール、売上FAXの送信区分がどちらも「送信しない」となっているため、送信できません。
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.slipsales.mail.fax.output", mailRowNo));
				}
				
				// メール送信設定がある場合、部署が設定されているか確認を行う。
				if(AecNumberUtils.convertBigDecimalNullToZeroFromBigDecimal(venderBean.getSalesMailOutput()).compareTo(BigDecimal.ONE) == 0){
					
					// 取引先マスタで部署が未設定である場合、送信時にエラーとする。
					if(venderBean.getMailOrganizationCd() != null && !venderBean.getMailOrganizationCd().isEmpty()){
						
						Organization org = organizationDao.getEntity(venderBean.getMailOrganizationCd());
						if(org==null){
							// 取引先マスタで設定した部署が存在しない場合、エラー
							// errors.slipsales.mail.non.organization={0}行目 取引先マスタの送信元部署が設定されていないため、メール送信ができません。
							messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.slipsales.mail.non.organization", mailRowNo));
						}else{
							// 取引先マスタで設定した部署が存在する場合、メールアドレスを設定しているか確認
							if(org.getFromMailAddress1() == null || org.getFromMailAddress1().isEmpty()){
								// errors.slipsales.mail.non.organization.from.mail={0}行目 取引先マスタの送信元部署でFromメールアドレスが設定されていないため、メール送信ができません。
								messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.slipsales.mail.non.organization.from.mail", mailRowNo));
							}
							
						}
					}else{
						// 取引先マスタで部署が設定されていない場合エラー
						// errors.slipsales.mail.non.organization={0}行目 取引先マスタの送信元部署が設定されていないため、メール送信ができません。
						messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.slipsales.mail.non.organization", mailRowNo));
					}
				}
			
			
			}
			mailRowNo++;
		}
		// エラーがある場合、メッセージを表示
		if(messages.size() != 0){
			addErrors(request, messages);
			return mapping.findForward("success");
			
		}

		//先に売上伝票番号のみ付番
		try {
			for (SlipSalesList bean : listForm.getSearchList()) {
				if (!bean.isSlipSalesCheckBox()) {
					continue;
				} else {
					//ログ登録処理
					insertLog(actionSeq,loginUserId,"slipSend",bean.getSalesNo());
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					salesNoList.add(bean.getSalesNo());

				}

			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipsales.selected.checkbox");
				throw ex;
			}

			// 売上伝票番号付番処理
			slipSalesListLogic.getAddSlipSalesNo(salesNoList, loginUserId, salesSearchList);

		} catch (SlipShippingLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
		}
		
		//////////////ここから送信処理
		//メールを送る時の情報の基準にされた行番号の保存用変数(エラー用)
		
		ArrayList<String> salesSlipNoList = new ArrayList<String>();
		salesNoList = new ArrayList<String>();
		
		// ====================================================================================================
		// 売上伝票番号を発番した後に、送信処理で売上伝票番号を使用する為、データに設定を行う。
		// ====================================================================================================
		for(SlipSalesList bean: listForm.getSearchList()){
			// チェックボックスがONの場合、処理
			if(bean.isSlipSalesCheckBox()){
				if(bean.getSalesSlipNo() == null || bean.getSalesSlipNo().isEmpty()){
					Sales salesData = salesDao.getEntity(bean.getSalesNo());
					if(salesData != null){
						bean.setSalesSlipNo(salesData.getSalesSlipNo());
					}
				}
			}			
		}
		// ====================================================================================================
		// 送信処理を実行
		// ====================================================================================================
		List<String> mailSendFileList = new ArrayList<String>();
		
		// チェックがONである売上番号のファイルを作成し、メールで送信する
		for(int i = 0 ; i < listForm.getSearchList().size();i++){
			
			// 売上伝票番号を保持
			SlipSalesList source = listForm.getSearchList().get(i);
			if(source.isSlipSalesCheckBox()){
				
				// リストをクリアし、売上伝票番号のリストを作成する
				salesSlipNoList = new ArrayList<String>();
				salesSlipNoList.add(source.getSalesSlipNo());
				
				// リストをクリアし、売上伝票番号の売上リストを作成する
				salesNoList = new ArrayList<String>();
				salesNoList.add(source.getSalesNo());

				// 同一伝票番号のデータを抽出
				for(int j = i + 1 ; j < listForm.getSearchList().size();j++){
					// 売上伝票番号が同じものを探索
					SlipSalesList target = listForm.getSearchList().get(j);

					if(target.isSlipSalesCheckBox()){

						if(source.getVenderCd().equals(target.getVenderCd())){
							// 売上伝票番号が同一である場合、
							
							salesSlipNoList.add(target.getSalesSlipNo());
							salesNoList.add(target.getSalesNo());
							target.setSlipSalesCheckBox(false); 	// 後続処理で処理をしない様にフラグ（チェックボックス）をクリアする
						}
					}
				}

				// メール添付用のファイル名を作成
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHmmss");
				String now = sdf.format(new Date());
				
				//PDFシーケンスを取得 20220516 add S.Fujimaki
				BigDecimal seq = this.pdfReportDecoratorDao.getPdfReportSeq();
				//PDFシーケンスを取得 20220516 add end  S.Fujimaki
				
				// 2021/3/12 セッツ様からの指示で取引先名称をコードに修正
//				String fileName = source.getVenderShortedName().replace("㈱", "（株）") + "_" + now;	// 特殊文字の㈱であると、文字化けするので置換を実施。
				String fileName = source.getVenderCd() + "_" + now;	// 特殊文字の㈱であると、文字化けするので置換を実施。
			
				// 売上伝票のExcelファイルを作成
				FileDownloadInfo info = slipSalesListExcelDecorator.createReport(salesSlipNoList, salesNoList, loginUserId, null, request.getRemoteAddr(), "SEND", seq);
				
				//ExcelのPDF化
				List<String> filePathList = createContactPDF(request, loginUserId, fileName, info, "S_10001", seq);
				
				// PDFを送信リストに追加
				for(String filePath:filePathList){
					mailSendFileList.add(filePath);				
				}
				
				try{
					// メールを送信
					ActionMessages errorMessages = slipSalesListLogic.sendMail(source.getSalesNo(), source.getSalesSlipNo(), mailSendFileList, i + 1, loginUserId, actionSeq);
					// メール送信のファイルリストをクリア
					mailSendFileList = new ArrayList<String>();
					
					if (!errorMessages.isEmpty()) {
						// メール通知に失敗した場合はメッセージを出力
						saveError(request, "errors.mail.notification", "");
						addErrors(request, errorMessages);
						insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"slipSend","mailError end");
						return mapping.findForward("reSearch");
					}else{
						//20230302add S.Fujimaki　伝票発行済みフラグはメール送信に成功したとき更新する。 
						//20230307　同一の送り先でまとめられるためリストより更新対象を取得する。
						for(String sendSalesNo : salesNoList){
							slipSalesListLogic.updateSlipPublishComp(sendSalesNo, loginUserId);
						}
					}
					

				}catch(IllegalArgumentException e){
					insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"sendMail","メール送信処理に失敗しました。　"+e.getMessage());
					throw e;
				}
			}
		}
		
		saveMessage(request, "message.complete.mail");
		
		insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"slipSend","end");

		return mapping.findForward("reSearch");
	}
	
	/**
	 * 帳票のPDF化
	 * 
	 * @param divSendList
	 * @param request
	 * @param listBean
	 * @param frm
	 * @param tantoCd
	 * @param buySubcontractOrderNoArray
	 * @param loginInfo
	 * @return
	 * @throws Exception
	 */
	public List<String> createContactPDF(final HttpServletRequest request, String tantoCd, 
			final String reportName, FileDownloadInfo info, String mailFormatId, BigDecimal seq) throws Exception {

		// ファイル名 deliverydate_contact
		String commonFileName = reportName;
		
		// メールテンプレート取得
		MailTemplate mailTemplate = slipSalesListLogic.getMailTemplate(mailFormatId);
		
		// PDF変換
		FileDownloadInfo info2 = pdfReportDecorator
				.convertPdfForExcel(reportName, tantoCd, commonFileName, info, mailTemplate, seq);
		
		// PDF変換後のファイルを添付
		String filePath = info2.getPath();
		
		// ファイルパスリストに追加
		List<String> filePathList = new ArrayList<String>();
		filePathList.add(filePath);
		
		return filePathList;
		
	}

	/**
	 * 印刷処理(印刷ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward slipPublishOld(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		SlipSalesListForm listForm = (SlipSalesListForm) form;
		ArrayList<String> salesNoList = new ArrayList<String>();
		//20221215 add S.Fujimaki start 検索結果と売上更新情報が、検索時の状態と一致するか確認する。
		List<SlipSalesList> salesSearchList = listForm.getSearchList();
		boolean isSelected = false;

		String forward = "success";

		// ExcelダウンロードフラグOFF
		listForm.setExcelDownloadFlg(false);

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		try {
			for (SlipSalesList bean : listForm.getSearchList()) {
				if (!bean.isSlipSalesCheckBox()) {
					continue;
				} else {
					isSelected = true;
					// 処理を行う出荷番号リストを作成
					salesNoList.add(bean.getSalesNo());

				}

			}
			if (!isSelected) {
				// 一つも選択されていない場合
				SlipShippingLogicException ex = new SlipShippingLogicException();
				ex.setKey("errors.slipsales.selected.checkbox");
				throw ex;
			}

			// 売上伝票番号付番処理
			ArrayList<String> salesSlipNoList = slipSalesListLogic
					.getAddSlipSalesNo(salesNoList, loginUserId, salesSearchList);
			FileDownloadInfo info = null;

			/* 売上伝票(旧)出力処理 */
			info = slipSalesListExcelDecorator.createReport(salesSlipNoList,
				salesNoList, loginUserId, null, request.getRemoteAddr(),
				"PRINT_OLD");
			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);

			// ExcelダウンロードフラグＯＮ
			listForm.setExcelDownloadFlg(true);

			forward = "reFresh";

		} catch (SlipShippingLogicException e) {
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			addError(request, e.getKey(), (Object[]) replacements);
		}

		return mapping.findForward(forward);
	}
	/**
	 * メッセージプロパティファイルから指定したkeyに対応する文字列を取得する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @return メッセージキーに対応するメッセージ文字列
	 */
	private String getMessageResource(final HttpServletRequest request,
			final String key) {
		MessageResources resource = getResources(request);
		return resource.getMessage(key);
	}

	/**
	 * エラーメッセージを追加する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param objects 置換パラメータ
	 */
	protected void addError(final HttpServletRequest request, final String key,
			final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 売上伝票出力画面ロジッククラスを設定します。
	 * @param slipSalesListLogic 売上伝票出力画面ロジッククラス
	 */
	public void setSlipSalesListLogic(
			final SlipSalesListLogic slipSalesListLogic) {
		this.slipSalesListLogic = slipSalesListLogic;
	}

	/**
	 * 売上伝票用エクセルクリエイターを設定します。
	 * @param slipSalesListExcelDecorator 売上伝票用エクセルクリエイター
	 */
	public void setSlipSalesListExcelDecorator(
			final SlipSalesListExcelDecorator slipSalesListExcelDecorator) {
		this.slipSalesListExcelDecorator = slipSalesListExcelDecorator;
	}

	/**
	 * /** 売上伝票用エクセルクリエイターを設定します。
	 * @param slipSalesExcelDecorator 売上伝票用エクセルクリエイター
	 */
	public void setSlipSalesExcelDecorator(
			final SlipSalesExcelDecorator slipSalesExcelDecorator) {
		this.slipSalesExcelDecorator = slipSalesExcelDecorator;
	}

	/**
	 * pdfReportDecoratorを設定します。
	 * @param pdfReportDecorator pdfReportDecorator
	 */
	public void setPdfReportDecorator(PdfReportDecorator pdfReportDecorator) {
		this.pdfReportDecorator = pdfReportDecorator;
	}

	/**
	 * venderDaoを設定します。
	 * @param venderDao venderDao
	 */
	public void setVenderDao(VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * organizationDaoを設定します。
	 * @param organizationDao organizationDao
	 */
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	/* -------------------- setter -------------------- */
	/**
	 * orderImportLogDaoを設定します。
	 * @param orderImportLogDao orderImportLogDao
	 */
	public void setPdfReportDecoratorDao(PdfReportDecoratorDao pdfReportDecoratorDao) {
		this.pdfReportDecoratorDao = pdfReportDecoratorDao;
	}
	
	/**
	 * salesDaoを設定します。
	 * @param salesDao salesDao
	 */
	public void setSalesDao(SalesDao salesDao) {
		this.salesDao = salesDao;
	}

	/**
	 * slipSalesActionLogDaoを設定します。
	 * @param salesDao salesDao
	 */
	public void setSlipSalesActionLogDao(SlipSalesActionLogDao slipSalesActionLogDao) {
		this.slipSalesActionLogDao = slipSalesActionLogDao;
	}

}
