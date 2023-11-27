/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.pdfReport.PdfReportDecorator;
import com.asahikaseieng.app.order.OrderLogicException;
import com.asahikaseieng.dao.entity.checklog.CheckLog;
import com.asahikaseieng.dao.entity.checklog.CheckLogDao;
import com.asahikaseieng.dao.entity.frstorderdetail.FrstOrderDetail;
import com.asahikaseieng.dao.entity.frstorderhead.FrstOrderHead;
import com.asahikaseieng.dao.entity.master.mailtemplate.MailTemplate;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.nonentity.common.pdfreportdecorator.PdfReportDecoratorDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.master.orderstatuslist.OrderStatusList;
import com.asahikaseieng.dao.nonentity.orderimportlist.OrderImportList;
import com.asahikaseieng.dao.nonentity.orderimportlist.OrderImportListPagerCondition;
import com.asahikaseieng.dao.nonentity.orderimportlistforreport.OrderImportListConditionForReport;
import com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipConditionForReport;
import com.asahikaseieng.dao.nonentity.ordernameslist.OrderNamesList;
import com.asahikaseieng.dao.entity.slipsalesactionlog.SlipSalesActionLog;
import com.asahikaseieng.dao.entity.slipsalesactionlog.SlipSalesActionLogDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 受注取込一覧 Action クラス
 * @author 
 */
public final class OrderImportListAction extends AbstractSearchAction {

	private OrderImportListLogic orderImportListLogic;

	private OrderImportListExcelDecorator orderImportListExcelDecorator;

	/** 調査ログ用DAO */
	private CheckLogDao checkLogDao;
	
	private CheckDigitUtilsLogic checkDigitUtilsLogic;
	
	private PdfReportDecorator pdfReportDecorator;
	
	private PdfReportDecoratorDao pdfReportDecoratorDao;
	
	private SlipSalesActionLogDao slipSalesActionLogDao;
	
	/** ログの区分 */
	public static final String LOG_DIVISION = "ORDER_IMPORT";
	
	//登録確認用クラス
	public static class CheckRegist{
		ActionMessages errorMsg = new ActionMessages(); //エラーメッセージ
		int updateNums = 0; //登録件数
	}

	/**
	 * コンストラクタ.
	 */
	public OrderImportListAction() {
		super();
	}

	/**
	 * 初期化
	 * {@inheritDoc}
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		LoginInfo loginInfo = getLoginInfo(request);
		outputCheckLog(loginInfo.getTantoCd(), "init", "初期処理　開始");

		OrderImportListForm frm = (OrderImportListForm) form;
		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ORDERIMPORT,Constants.TAB_ID_ORDERIMPORT_DETAIL);

		/* 受注区分コンボボックス作成用 */
		setOrderDivisionCombobox(frm);

		/* 受注ステータスコンボボックス作成用 */
		setOrderStatusCombobox(frm);
		
		// 得意先グループコンボボックス
		frm.setVenderGroupCombo(orderImportListLogic.createVenderGroupAllCombobox());

		// 運送会社コンボボックス
		frm.setCarryCombo(orderImportListLogic.createCarryAllCombobox());
		
		// 担当部署を設定
		frm.setSrhOrganizationCd(loginInfo.getOrganizationCd());
		frm.setSrhOrganizationName(loginInfo.getOrganizationName());

		frm.setSendCompFlg(0);
		
		outputCheckLog(loginInfo.getTantoCd(), "init", "初期処理　終了");
		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * 再検索処理（一覧再描画する時の処理用）<br>
	 * ページ番号はpagerが保持する"page"より取得すること<br>
	 * "page"が存在しない場合はpage=1として処理すること.
	 * @param mapping ActionMapping
	 * @param form AbstractSearchForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception システム例外
	 */
	public final ActionForward reSearch(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		OrderImportListForm frm = (OrderImportListForm) form;
		
		//検索条件が1つも変更されていなかったら受注日に当日をセット
		if(!frm.getDefaultSearchDirtyFlg()){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			
			frm.setSrhOrderDateFrom( dateFormat.format( AecDateUtils.getCurrentDate() ) );
			frm.setSrhOrderDateTo( dateFormat.format( AecDateUtils.getCurrentDate() ) );
			LoginInfo loginInfo = getLoginInfo(request);
			frm.setSrhInputTantoCd( loginInfo.getTantoCd() );
			frm.setSrhInputTantoName( loginInfo.getTantoNm() );
		}
		
		frm.setSendCompFlg(0);

		return super.searchProcess(mapping, request, (AbstractSearchForm) form);
	}
	
	/**
	 * 検索ボタン押下時イベント
	 * {@inheritDoc}
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "search", "検索処理　開始");

		OrderImportListForm frm = (OrderImportListForm) form;
		
		//検索条件が1つも変更されていなかったらエラー
		if(!frm.getSearchDirtyFlg()){
			//1つ以上、検索条件を入力して下さい。
			saveError(request, "errors.search.nochange");
			return mapping.findForward("success");
		}

		// 検索処理の実施
		search(frm);
		
		outputCheckLog(getLoginInfo(request).getTantoCd(), "search", "検索処理　終了");

		return mapping.findForward("success");
	}

	/**
	 * 検索処理の実施
	 * @param frm
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoDataException
	 */
	private void search(OrderImportListForm frm) throws IllegalAccessException,
			InvocationTargetException, NoDataException {
		/* 検索条件を取得 */
		OrderImportListPagerCondition condition = (OrderImportListPagerCondition) frm.getPager().getPagerCondition();

		/* Formの検索条件をコピー */
		IgnoreCaseBeanUtils.copyProperties(condition, frm);
		
		// 得意先へのファイル返信がOKであるか判定するフラグを設定する
		if(!frm.getSrhVenderGroupCd().equals("0")){
			frm.setFileSendOk(Boolean.TRUE);
		}else{
			frm.setFileSendOk(Boolean.FALSE);
		}
		
		/* クリア */
		frm.setSearchList(new ArrayList<OrderImportList>());

		/* 明細取得 */
		frm.setSearchList(orderImportListLogic.getList(frm, condition, frm.getSrhVenderCd()));
		
		for(OrderImportList bean : frm.getSearchList()){
			// 受注数量のJSPで行う数値桁数の設定を標準機能から取得する
			NumberChkDisitDetail checkData = checkDigitUtilsLogic.getCheckDigit(
					bean.getUnitOfOperationManagement(), null, null);
	
			bean.setSmallnumLengthNum(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData.getSmallnumLength())); // 小数点位置
			bean.setRoundDivisionNum(AecNumberUtils
					.convertStringNullToZeroFromBigDecimal(checkData.getRoundDivision())); // 丸め区分
		}

		/* 帳票(Excel)用に検索条件を保持 */
		OrderImportListConditionForReport reportCondition = new OrderImportListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);
		
		/* 受注一覧表用に検索条件を保持 */
		RepOrderImportSlipConditionForReport slipCondition = new RepOrderImportSlipConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(slipCondition, condition);
		frm.setSlipCondition(slipCondition);
	}

	/**
	 * クリア処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward clear(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("clear.");
		}
		
		outputCheckLog(getLoginInfo(request).getTantoCd(), "clear", "クリア処理　開始");

		OrderImportListForm frm = (OrderImportListForm) form;

		/* クリア */
		frm.clear();

		
		outputCheckLog(getLoginInfo(request).getTantoCd(), "clear", "クリア処理　終了");

		return mapping.findForward("success");
	}

	/**
	 * 新規処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}
		outputCheckLog(getLoginInfo(request).getTantoCd(), "newPage", "新規　開始");

		OrderImportListForm frm = (OrderImportListForm)form;
		/* 検索条件を取得 */
		OrderImportListPagerCondition condition = (OrderImportListPagerCondition) frm.getPager().getPagerCondition();
		/* Formの検索条件をコピー */
		IgnoreCaseBeanUtils.copyProperties(condition, frm);
		
		outputCheckLog(getLoginInfo(request).getTantoCd(), "newPage", "新規　終了");

		return mapping.findForward("newPage");
	}
	
	
	/**
	 * 確定処理(確定ボタン押下時).
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward fix(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("fix.");
		}
		
		CheckRegist checkRegist = new CheckRegist();
		
		OrderImportListForm frm = (OrderImportListForm) form;
		
		String tantoCd = getLoginInfo(request).getTantoCd();
		String tantoOrgCd = getLoginInfo(request).getOrganizationCd();

		try {
			// 更新処理を実行
			checkRegist = orderImportListLogic.fix(frm, tantoCd, tantoOrgCd);
			if(!checkRegist.errorMsg.isEmpty()){
				saveErrors(request, checkRegist.errorMsg);
			}
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (OrderLogicException e) {
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
			if (StringUtils.isNotEmpty(e.getModuleCd())) {
				// エラーログに出力する
				orderImportListLogic.outPutErrorLog(e.getModuleCd(), e.getInsideErrCd(), e.getInsideErrMsg(), tantoCd);
			}
			
			outputCheckLog(getLoginInfo(request).getTantoCd(), "fix", "確定処理　終了");
			return mapping.getInputForward();
		}
		
		outputCheckLog(getLoginInfo(request).getTantoCd(), "fix", "確定処理　終了");
		
		if(checkRegist.updateNums > 0){
			//更新処理を実行しました。
			saveMessage(request, "message.complete.update");
		}else{
			//対象がありません。
			saveMessage(request, "erors.nodata");
		}

		
		return mapping.findForward("research");
	}
	
	/**
	 * 削除処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward orderRelease(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("delete.");
		}
		CheckRegist checkRegist = new CheckRegist();
		
		OrderImportListForm frm = (OrderImportListForm) form;
		
		String tantoCd = getLoginInfo(request).getTantoCd();
		
		try {
			// 引当解除を実行
			checkRegist = orderImportListLogic.orderRelease(frm, tantoCd);
			if(!checkRegist.errorMsg.isEmpty()){
				saveErrors(request, checkRegist.errorMsg);
			}
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (OrderLogicException e) {
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
			if (StringUtils.isNotEmpty(e.getModuleCd())) {
				// エラーログに出力する
				orderImportListLogic.outPutErrorLog(e.getModuleCd(), e.getInsideErrCd(), e.getInsideErrMsg(), tantoCd);
			}
			
			outputCheckLog(getLoginInfo(request).getTantoCd(), "delete", "削除処理　終了");
			return mapping.getInputForward();
		}
		
		outputCheckLog(getLoginInfo(request).getTantoCd(), "delete", "削除処理　終了");
		
		if(checkRegist.updateNums > 0){
			//レコードを削除しました。
			saveMessage(request, "message.complete.delete");
		}else{
			//対象がありません。
			saveMessage(request, "erors.nodata");
		}

		return mapping.findForward("research");
	}
	
	/**
	 * EXCEL作成処理.
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
		OrderImportListForm frm = (OrderImportListForm) form;
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "report",
			"帳票Excel　開始");

		/* Excel作成 */
		FileDownloadInfo info = orderImportListExcelDecorator.createReport(frm.getReportCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY, info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "report", "帳票Excel　終了");

		return mapping.findForward("success");
	}
	
	/**
	 * 納期連絡表出力
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 *             Exception
	 * @return ActionForward
	 */
	public ActionForward createContactTable(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("createContactTable.");
		}
		// 操作シーケンスを作成
		BigDecimal actionSeq = this.slipSalesActionLogDao.getActionSeq();
		
		//　ログ登録処理 20221219 S.Fujimaki add
		insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"createContactTable","start");
		
		OrderImportListForm frm = (OrderImportListForm) form;
		
		frm.setExcelDownloadFlg(false);
		
		//メールを送る時の情報の基準にされた行番号の保存用変数(エラー用)
		ActionMessages errorMessages = new ActionMessages();
		
		// チェックされている検索結果リスト
		List<OrderImportList> checkedList = new ArrayList<OrderImportList>();
		int rowNum = 0;

		for(OrderImportList bean : frm.getSearchList()){
			rowNum++;

			if (!bean.isOrderImportCheckBox()) {
				continue;
			}
			
			// 送信内容のエラーチェック
			CheckRegist checkPreOrder = checkDelDateOutputOrderData(bean,rowNum);
			
			// エラーがあればファイル出力しない
			if( !checkPreOrder.errorMsg.isEmpty() ){
				errorMessages.add(checkPreOrder.errorMsg);
			}else{
				//　ログ登録処理 20221219 S.Fujimaki add
				insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"createContactTable",bean.getFrstOrderNo());
				checkedList.add(bean);
			}
		}
		
		if( checkedList.size() > 0 ){
			List<String> checkedFrstOrderNoList = new ArrayList<String>();
			FileDownloadInfo info = orderImportListExcelDecorator.createDeliveryDateContact(checkedList,getLoginInfo(request).getTantoCd() , checkedFrstOrderNoList);
			
			if( info != null ){
				orderImportListExcelDecorator.updateDelDateSender(getLoginInfo(request).getTantoCd(), checkedFrstOrderNoList);
				
				/* セッションにセット */
				request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY, info);

				//　ログ登録処理 20221219 S.Fujimaki add
				insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"report:file", info.getPath());
				
				frm.setExcelDownloadFlg(true);
			}
			
		}
		
		if (!errorMessages.isEmpty()) {
			// メール通知に失敗した場合はメッセージを出力
			addErrors(request, errorMessages);
		}
		
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "createContactTable", "納期連絡表出力　終了");

		//　ログ登録処理 20221219 S.Fujimaki add
		insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"createContactTable","end");
		
		return mapping.findForward("success");
	}
	
	/**
	 * 受注納期連絡表メールFAX送信処理
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 *             Exception
	 * @return ActionForward
	 */
	public ActionForward sendMail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (super.getLog().isDebugEnabled()) {
			super.getLog().debug(".");
		}
		// 操作シーケンスを作成
		BigDecimal actionSeq = this.slipSalesActionLogDao.getActionSeq();
		//　ログ登録処理 20221219 S.Fujimaki add
		insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"sendMail","start");
		
		CheckRegist checkRegist = new CheckRegist();
		int updateNum = 0;
		
		OrderImportListForm frm = (OrderImportListForm) form;
		
		
		// ログイン情報の取得
		LoginInfo loginInfo = (LoginInfo) request.getSession(false)
				.getAttribute(Constants.LOGIN_KEY);
		
		String tantoCd = getLoginInfo(request).getTantoCd();
		outputCheckLog(getLoginInfo(request).getTantoCd(), "sendMail", "納期連絡表送信　開始");
		
		//メールを送る時の情報の基準にされた行番号の保存用変数(エラー用)
		ActionMessages errorMessages = new ActionMessages();

		int rowNum = 0;
		HashMap<String , OrderImportDeliveryDateDataModel > dmMap = new HashMap<String , OrderImportDeliveryDateDataModel > ();
		
		for(OrderImportList bean : frm.getSearchList()){
			
			rowNum++;
			
			if(bean.isOrderImportCheckBox()){ //チェックがあればメールを送信

				//納期連絡表KEYを取得
				String key = orderImportListLogic.getContactTableKey(bean.getFrstOrderNo());
				
				// Key情報がない場合、次
				if( !AecStringUtils.isNotNullAndEmpty(key) ){
					continue;
				}
				
				// 送信内容のエラーチェック
				CheckRegist checkPreOrder = checkDelDateSendOrderData(bean,rowNum);
				
				// エラーがあればメール送信しない
				if( !checkPreOrder.errorMsg.isEmpty() ){
					errorMessages.add(checkPreOrder.errorMsg);
				}else{
					//　ログ登録処理 20221219 S.Fujimaki add
					insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"sendMail",bean.getFrstOrderNo());
					
					// 受注のFAXかメールの送信設定が送信するの場合、送信リストに追加
					if(bean.getOrderFaxOutput().equals("1") || bean.getOrderMailOutput().equals("1")){
						// 後で一括で送信するようにHashMapにデータを追加
						orderImportListLogic.addOrderImportDeliveryDateDataModel(dmMap, bean,  rowNum);
					}else{
						// FAX送信しない場合、その先付受注番号は全て送信済とする
						List<String> checkedFrstOrderNoList = new ArrayList<String>();
						checkedFrstOrderNoList.add(bean.getFrstOrderNo());
						orderImportListExcelDecorator.updateDelDateSender(getLoginInfo(request).getTantoCd(), checkedFrstOrderNoList);
						
					}
				}
			}
		}

		// 取得した情報を元に、取引先ごとにメールを送信
		for( Entry<String , OrderImportDeliveryDateDataModel > entry : dmMap.entrySet() ){

			//PDFシーケンスを取得 20220516 add S.Fujimaki
			BigDecimal seq = this.pdfReportDecoratorDao.getPdfReportSeq();
			//PDFシーケンスを取得 20220516 add end  S.Fujimaki
			
			// Excelの作成
			List<String> checkedFrstOrderNoList = new ArrayList<String>();
			FileDownloadInfo info = orderImportListExcelDecorator.createDeliveryDateContact(entry.getValue().getCheckedList(),getLoginInfo(request).getTantoCd() , checkedFrstOrderNoList, seq);
			//20220516　S.Fujimaki　Add
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHmmss");
			String now = sdf.format(new Date());
			String fileName = "deliverydate_contact" + "_" + now;
			//20220516　S.Fujimaki　Add　end
			if( info != null ){
			
				//20220516　S.Fujimaki　Add
				//　ExcelのPDF化
				List<String> filePathList = createContactPDF(request, tantoCd, fileName, info, "O_10001", seq);
				//20220516　S.Fujimaki　Add end
				try{
					
					// メールを送信
					checkRegist = orderImportListLogic.sendMail(entry.getValue(), filePathList, entry.getKey(), tantoCd, actionSeq);
					
					if (!checkRegist.errorMsg.isEmpty()) {
						// メール通知に失敗した場合はメッセージを出力
						errorMessages.add(checkRegist.errorMsg);
					}else {
						updateNum = checkRegist.updateNums;
						orderImportListExcelDecorator.updateDelDateSender(getLoginInfo(request).getTantoCd(), checkedFrstOrderNoList);
					}
				}catch(IllegalArgumentException e){
					insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"sendMail","メール送信処理に失敗しました。　"+e.getMessage());
					throw e;
				}
			
			}

		}


		
		if (!errorMessages.isEmpty()) {
			//　ログ登録処理 20221219 S.Fujimaki add
			insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"sendMail","mailError end");
			// メール通知に失敗した場合はメッセージを出力
			saveError(request, "errors.mail.notification", "");
			addErrors(request, errorMessages);
		}
		
		// 更新件数が1件以上なら完了メッセージ表示
		if(updateNum > 0){
			saveMessage(request, "message.complete.mail");
		}else{
			//　ログ登録処理 20221219 S.Fujimaki add
			insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"sendMail","nodata end");
			saveMessage(request, "errors.nodata");
		}
		frm.setDirtyFlg(false);

		// 検索処理の実施
		search(frm);
		frm.setSendCompFlg(1);

		
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "sendMail", "納期連絡表送信　終了");
		//　ログ登録処理 20221219 S.Fujimaki add
		insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"sendMail","end");
		
		return mapping.findForward("success");
	}

	/**
	 * 受注納期連絡表の送信前チェック
	 * @param checkedList
	 * @param mailRowNo
	 * @return
	 */
	public CheckRegist checkDelDateSendOrderData(
			OrderImportList checkedList , int rowCount) {
		CheckRegist checkPreOrder = new CheckRegist();
		// 先付け受注と納期確定前の仕入れ直送品は送信エラー
		int orderStatus = checkedList.getOrderStatus().intValue();
		int orderDivision = checkedList.getOrderDivision().intValue();
		boolean orderChecked = checkedList.getInputApprovaledCheck();
				
		if( orderDivision == 3 && !( orderStatus == 4 || orderStatus == 5 || orderStatus == 6 ) ){
			// 納期確定前の仕入れ直送品はエラー
			checkPreOrder.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.mail.notfixeddeldate.output", rowCount));
		}else if( !orderChecked ){
			// 未チェックの受注の場合、エラー
			checkPreOrder.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.mail.preorder.nocheck", rowCount));
		}
			
		return checkPreOrder;
	}

	/**
	 * 受注納期連絡表の送信前チェック
	 * @param checkedList
	 * @param mailRowNo
	 * @return
	 */
	public CheckRegist checkDelDateOutputOrderData(
			OrderImportList checkedList , int rowCount) {
		CheckRegist checkPreOrder = new CheckRegist();
		// 先付け受注と納期確定前の仕入れ直送品は送信エラー
		boolean orderChecked = checkedList.getInputApprovaledCheck();
		
		 if( !orderChecked ){
			// 先付け受注の場合、エラー
			checkPreOrder.errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.mail.preorder.nocheck", rowCount));
		}
			
		return checkPreOrder;
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
		MailTemplate mailTemplate = orderImportListLogic.getMailTemplate(mailFormatId);
		
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
	 * 取込ファイルに対してAP21の状態を返信する処理
	 * 
	 * @param mapping            ActionMapping
	 * @param form            ActionForm
	 * @param request            HttpServletRequest
	 * @param response           HttpServletResponse
	 * @throws Exception            Exception
	 * @return ActionForward
	 */
	public ActionForward sendFileToMail(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {

		if (super.getLog().isDebugEnabled()) {
			super.getLog().debug("sendFileToMail");
		}
		
		// 操作シーケンスを作成
		BigDecimal actionSeq = this.slipSalesActionLogDao.getActionSeq();
		//　ログ登録処理 20221219 S.Fujimaki add
		insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"sendFileToMail","start");
		
		CheckRegist checkRegist = new CheckRegist();
		
		OrderImportListForm frm = (OrderImportListForm) form;
		
		// ログイン情報の取得
		LoginInfo loginInfo = (LoginInfo) request.getSession(false).getAttribute(Constants.LOGIN_KEY);
		String tantoCd = loginInfo.getTantoCd();

		// =======================================================================================================
		// 処理前チェック
		// =======================================================================================================
		// 得意先グループでの検索がない場合エラーとする
		if(!frm.getFileSendOk()){
			saveError(request, "errors.orderimport.sendfile.check");
			frm.setDirtyFlg(false);
			return mapping.findForward("success");
			
		}

		//　ログ登録処理 20221219 S.Fujimaki add
		for(OrderImportList bean : frm.getSearchList()){
			// チェックボックスがONか判断し、ON明細のリストを作成する	
			if (bean.isOrderImportCheckBox() ) {
				insertLog(actionSeq,tantoCd,"sendFileToMail",bean.getFrstOrderNo());
			}
		}
		try{
			// ファイルを作成し、メールを送信
			checkRegist = orderImportListLogic.sendFileToMail(frm, tantoCd, actionSeq);
		
			if (!checkRegist.errorMsg.isEmpty()) {
				// メール通知に失敗した場合はメッセージを出力
				saveError(request, "errors.mail.notification", "");
				addErrors(request, checkRegist.errorMsg);
				frm.setDirtyFlg(false);
				return mapping.findForward("success");
			}
			
			if(checkRegist.updateNums > 0){
				saveMessage(request, "message.complete.mail");
			}

		}catch(IllegalArgumentException e){
			insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"sendMail","メール送信処理に失敗しました。　"+e.getMessage());
			throw e;
		}
		
		// 検索処理の実施
		search(frm);
		frm.setSendCompFlg(1);
		
		//　ログ登録処理 20221219 S.Fujimaki add
		insertLog(actionSeq,getLoginInfo(request).getTantoCd(),"sendFileToMail","end");
		
		return mapping.findForward("success");
	}
	
	/**
	 * 受注帳票作成処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward orderReport(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("orderReport.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "orderReport", "受注一覧表　開始");

		OrderImportListForm frm = (OrderImportListForm) form;
		
		
		/* Excel作成 */
		FileDownloadInfo info = orderImportListExcelDecorator.createOrderReport(frm.getSlipCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY, info);

		/* ダウンロードフラグをONに */
		frm.setExcelSlipDownloadFlg(true);

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "orderReport", "受注一覧表　終了");

		return mapping.findForward("success");
	}
	
	/**
	 * 受注区分リスト取得
	 * @param frm 画面データ
	 */
	public void setOrderDivisionCombobox(final OrderImportListForm frm) {
		/* ラベルマスタの区分データを取得 */
		List<OrderNamesList> list = orderImportListLogic.getOrderDivisionList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}

		frm.setOrderDivisionLabels(labels);
		frm.setOrderDivisionValues(values);
	}

	/**
	 * 受注ステータスリスト取得
	 * @param frm 画面データ
	 */
	public void setOrderStatusCombobox(final OrderImportListForm frm) {
		/* ラベルマスタの区分データを取得 */
		List<OrderStatusList> list = orderImportListLogic.getOrderStatusList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}

		frm.setOrderStatusLabels(labels);
		frm.setOrderStatusValues(values);
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
	 * 
	 * 調査用ログ出力処理
	 * @param tantoCd
	 * @param function
	 * @param message
	 */
	private void outputCheckLog(final String tantoCd, final String function,
			final String message) {
		CheckLog log = new CheckLog();
		log.setModuleCd(LOG_DIVISION);
		log.setClient(tantoCd);
		log.setErrorDate(AecDateUtils.getCurrentTimestamp());
		log.setErrorMes(function);
		log.setSqlStr(message);
		checkLogDao.insert(log);
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
	private void addError(final HttpServletRequest request, final String key,
			final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
	}

	/* -------------------- setter -------------------- */

	/**
	 * orderListLogicを設定します。
	 * @param orderImportListLogic orderImportListLogic
	 */
	public void setOrderImportListLogic(final OrderImportListLogic orderImportListLogic) {
		this.orderImportListLogic = orderImportListLogic;
	}

	/**
	 * orderListExcelDecoratorを設定します。
	 * @param orderImportListExcelDecorator orderImportListExcelDecorator
	 */
	public void setOrderListExcelDecorator(
			final OrderImportListExcelDecorator orderImportListExcelDecorator) {
		this.orderImportListExcelDecorator = orderImportListExcelDecorator;
	}

	/**
	 * pdfReportDecoratorを設定します。
	 * @param pdfReportDecorator pdfReportDecorator
	 */
	public void setPdfReportDecorator(PdfReportDecorator pdfReportDecorator) {
		this.pdfReportDecorator = pdfReportDecorator;
	}


	/**
	 * checkLogDaoを設定します。
	 * @param checkLogDao checkLogDao
	 */
	public void setCheckLogDao(final CheckLogDao checkLogDao) {
		this.checkLogDao = checkLogDao;
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
	 * checkDigitUtilsLogicを設定します。
	 * @param checkDigitUtilsLogic checkDigitUtilsLogic
	 */
	public void setCheckDigitUtilsLogic(CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}
	/**
	 * slipSalesActionLogDaoを設定します。
	 * @param salesDao salesDao
	 */
	public void setSlipSalesActionLogDao(SlipSalesActionLogDao slipSalesActionLogDao) {
		this.slipSalesActionLogDao = slipSalesActionLogDao;
	}
}
