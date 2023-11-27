/*
 * Created on 2020/09/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.seasar.extension.jdbc.SqlLogRegistryLocator;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.checkdigit.NumberChkDigitCtl;
import com.asahikaseieng.app.order.OrderLogicException;
import com.asahikaseieng.app.unitprice.GetValidUnitpriceLogic;
import com.asahikaseieng.dao.entity.checklog.CheckLog;
import com.asahikaseieng.dao.entity.checklog.CheckLogDao;
import com.asahikaseieng.dao.entity.frstorderhead.FrstOrderHead;
import com.asahikaseieng.dao.entity.master.belong.Belong;
import com.asahikaseieng.dao.entity.master.belong.BelongDao;
import com.asahikaseieng.dao.entity.master.login.LoginDao;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.nonentity.autocomplete.item.order.OrderItemForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.item.order.OrderItemForAutoCompleteDao;
import com.asahikaseieng.dao.nonentity.master.deliveryinfo.DeliveryInfo;
import com.asahikaseieng.dao.nonentity.master.deliveryinfo.DeliveryInfoDao;
import com.asahikaseieng.dao.nonentity.orderdetailvenderdetail.OrderDetailVenderDetail;
import com.asahikaseieng.dao.nonentity.orderdetailvenderlist.OrderDetailVenderList;
import com.asahikaseieng.dao.nonentity.orderimportdetaillist.OrderImportDetailList;
import com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkList;
import com.asahikaseieng.dao.nonentity.procedurecall.ProCalcDateFromCalendarCallDto;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * 受注取込詳細 Actionクラス.
 * @author 
 */
public final class OrderImportDetailAction extends AbstractAction {

	/** 品目マスタ操作DAO */
	private OrderItemForAutoCompleteDao orderItemForAutoCompleteDao;
	//20210526 add S.Fujimaki 営業担当者表示
	/** 営業担当者名取得用DAO*/
	private LoginDao loginDao;
	//20210526 add S.Fujimaki 営業担当者表示
	
	private GetValidUnitpriceLogic getValidUnitpriceLogic;

	private OrderImportDetailLogic orderImportDetailLogic;
	
	private OrderImportCommonLogic orderImportCommonLogic;

	/** 得意先区分 */
	protected static final String VENDER_DIVISION = Constants.VENDER_DIVISION_TS;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 調査ログ用DAO */
	private CheckLogDao checkLogDao;
	
	/** Delivery関連取得用DAO*/
	private DeliveryInfoDao deliveryInfoDao;
	
	/** 担当者受注納入先区分取得用DAO*/
	private BelongDao belongDao;

	/** ログの区分 */
	public static final String LOG_DIVISION = "ORDER_IMPORT_DETAIL";

	protected static final short REMARK_LENGTH = 42;

	/** 休日考慮日付計算 休日考慮不可能エラー */
	private final BigDecimal CALC_DATE_ERROR_NO_CALENDAR = new BigDecimal(-1);

	/** 休日考慮日付計算 異常終了 */
	private final BigDecimal CALC_DATE_ERROR_ABORT = new BigDecimal(-9);
	
	// 数値チェックディジットの管理クラス
	private NumberChkDigitCtl numberChkDigitCtl = new NumberChkDigitCtl();

	/** 休日考慮日付計算用 計算対象となった日付の番号*/
	enum REPLACEMENT_NUMBER{
		//出荷予定日
		SCHEDULED_SHIPPING_DATE,
		//納入予定日
		DELIVERY_EXPECTED_DATE
	}

	/**
	 * コンストラクタ.
	 */
	public OrderImportDetailAction() {
		super();
	}

	/**
	 * 初期処理(一覧 リンク押下時)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "init", "初期処理　開始");
		OrderImportDetailForm frm = (OrderImportDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ORDERIMPORT,Constants.TAB_ID_ORDERIMPORT_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 受注区分コンボボックス
		frm.setOrderDivisionCombo(orderImportDetailLogic.getCreateOrderDivisionCombobox());

		// 運送会社コンボボックス
		frm.setCarryCombo(orderImportDetailLogic.getCreateCarryCombobox());
		
		// 得意先グループコンボボックス
		frm.setVenderGroupCombo(orderImportDetailLogic.createVenderGroupCombobox());
				
		try {
			/* 初期検索 */
			orderImportDetailLogic.getOrderImportData(frm, getLoginInfo(request).getTantoCd() , this.numberChkDigitCtl);
			// 競合登録中の行データの追加
			addConflictList(frm);
			
			
		} catch (NoDataException e) {
			// 2015/4/20 受注混雑調査の為ログ追加
			outputCheckLog(getLoginInfo(request).getTantoCd(), "init","初期処理　終了");
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}
		
		//登録フラグ 0:更新
		frm.setInsertFlg(0);
		
		// スクロールフラグ:0
		frm.setScrollFlg(0);
		
		if(frm.getBalanceCd() != null){
			// 得意先リスト情報取得
			getVenderList(frm);
			
			// 担当部署取得設定
			getVenderDetail(frm);
		}

		/* ログイン情報取得*/
		String tantoCd = getLoginInfo(request).getTantoCd();
		
		Belong getBean = belongDao.getEntity(null, tantoCd, null);
		
		/* 受注納入先区分を所属マスターから取得 2020/07/07 */
		if(getBean.getOrderDeliveryKbn() == null){
			frm.setDeliveryDivision("2");
		} else{
			frm.setDeliveryDivision(getBean.getOrderDeliveryKbn());
		}
		
		// ##############################################
		// #####変更不可、可能判定処理 ########
		// ##############################################
		// 受注区分が１在庫引当か４例外出荷の場合
		if (frm.getOrderDivision().equals("1")
				|| frm.getOrderDivision().equals("4")) {
			frm.setOrderCancelButtonEnableFlg(1);
			for (OrderImportDetailList detailListBean : frm.getOrderImportDetailList()) {
				
				// 削除レコードはチェックしない
				if(detailListBean.getDelFlg().intValue() == 1){
					continue;
				}
				
				if (detailListBean.getOrderStatus() == null && !AecStringUtils.isNotNullAndEmpty(frm.getOrderNo())) {
					frm.setOrderCancelButtonEnableFlg(0);
					continue;
				}
				if ( ( detailListBean.getConflictLine() != null && !detailListBean.getConflictLine() ) && detailListBean.getOrderStatus() != null && ( !(detailListBean.getOrderStatus().equals(new BigDecimal("99")) || detailListBean.getOrderStatus().equals(new BigDecimal("90"))))) {
					// ステータスに受注登録、先付受注以外のものがあれば変更不可
					frm.setOrderCancelButtonEnableFlg(0);
				}
			}
		}
		// 受注区分が３仕入直送品の場合
		if (frm.getOrderDivision().equals("3")) {
			frm.setOrderCancelButtonEnableFlg(1);
			for (OrderImportDetailList detailListBean : frm.getOrderImportDetailList()) {
				
				// 削除レコードはチェックしない
				if(detailListBean.getDelFlg().intValue() == 1){
					continue;
				}
				
				if (detailListBean.getPurchaseStatus() == null) {
					continue;
				}
				if ( detailListBean.getPurchaseStatus().intValue() != PurchaseStatus.NOT_ISSUE.intValue()) {
					// 購買ステータスが未発行以外の場合は変更不可
					frm.setOrderCancelButtonEnableFlg(0);
				}
			}
		}
		// 受注区分が２受注生産の場合
		if (frm.getOrderDivision().equals("2")) {
			frm.setOrderCancelButtonEnableFlg(1);
			for (OrderImportDetailList detailListBean : frm.getOrderImportDetailList()) {
				
				// 削除レコードはチェックしない
				if(detailListBean.getDelFlg().intValue() == 1){
					continue;
				}
				
				if (detailListBean.getAspStatus() != null
						&& detailListBean.getOrderStatus() != null) {
					if (detailListBean.getAspStatus().equals("B")
							|| detailListBean.getAspStatus().equals("D")
							|| !(detailListBean.getOrderStatus().equals("99") || detailListBean.getOrderStatus().equals("90"))) {
						frm.setOrderCancelButtonEnableFlg(0);
					}
				}
			}
		}
		
		// エラーが発生している場合、出力
		ActionMessages errorMsg = new ActionMessages();
		int rowNum = 0;
		boolean hasActiveOrder = false;
		for (OrderImportDetailList detailListBean : frm.getOrderImportDetailList()) {
			// 削除レコードはチェックしない
			if(detailListBean.getDelFlg().intValue() == 1){
				continue;
			}
			
			// キャンセルレコードはチェックしない
			if( detailListBean.getCancelFlg().intValue() == 1 ){
				rowNum++;
				continue;
			}
			
			rowNum++;
			String errMsg = detailListBean.getErrorMessage();
			if( AecStringUtils.isNotNullAndEmpty(errMsg ) ){
				//ログのエラーメッセージをActionMessagesに入れる
				errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.detail", String.valueOf( rowNum ) + "行目:" + errMsg));
			}
			hasActiveOrder = true;
		}
		
		if(!hasActiveOrder){
			frm.setOrderInvisibleButtonEnableFlg(1);
		}

		// 各行の合計を再計算
		orderImportDetailLogic.calcOrderAmout(frm , this.numberChkDigitCtl);
		// 品目リスト合計計算
		orderImportDetailLogic.calcTotalOrderAmount(frm, this.numberChkDigitCtl);
		
		if ( !errorMsg.isEmpty()){
			saveErrors(request, errorMsg);
		}
		
		numberChkDigitCtl.clear();

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "init", "初期処理　終了");

		return mapping.findForward("success");
	}

	/**
	 * 新規処理(一覧画面 新規ボタン押下時).
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}
		
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "newPage", "新規処理　開始");

		OrderImportDetailForm frm = (OrderImportDetailForm) form;
		
		// 検索条件をセット
		frm.setDeliveryDivision(frm.getDeliveryDivision());
		
		frm.clear();
		
		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ORDERIMPORT, Constants.TAB_ID_ORDERIMPORT_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 受注区分コンボボックス
		frm.setOrderDivisionCombo(orderImportDetailLogic.getCreateOrderDivisionCombobox());

		// 運送会社コンボボックス
		frm.setCarryCombo(orderImportDetailLogic.getCreateCarryCombobox());
		
		// 得意先グループコンボボックス
		frm.setVenderGroupCombo(orderImportDetailLogic.createVenderGroupCombobox());

		
		// 5行の入力行(ブランク)を作成
		frm.setOrderImportDetailListCount(5);
		frm.setOrderImportDetailList( new ArrayList<OrderImportDetailList>() );
		for(int i=0; i <= 4; i++){
			this.addSearchList(frm, i);
		}

		// 受注日のデフォルト値：今日
		frm.setOrderDate(AecDateUtils.formatStringFromTimestamp(
				AecDateUtils.getCurrentTimestamp(), "yyyy/MM/dd"));
		frm.setOrderDivision("1");

		// 受注日にシステム日時をセット
		// システム日時取得を取得し、日付を製造開始予定日時のデフォルトとしてセットする
		Calendar cal1 = Calendar.getInstance(); // システム日時を取得
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		frm.setOrderDate(df.format(cal1.getTime())); // yyyy/MM/ddの形でセット

		// 出荷予定日は受注日の翌営業日でセット
		ActionMessages messages = new ActionMessages();
		frm.setScheduledShippingDate(calcDateFromCalendar(frm.getOrderDate(), null, null, messages, REPLACEMENT_NUMBER.SCHEDULED_SHIPPING_DATE));
		if ( !messages.isEmpty()) {
			//日付の計算でエラーがある場合はここで登録
			saveErrors(request, messages);
		}

		// ここを通る場合は新規処理
		frm.setInsertFlg(1);

		/* ログイン情報取得*/
		String tantoCd = getLoginInfo(request).getTantoCd();
		
		Belong getBean = belongDao.getEntity(null,tantoCd,null);
		
		/* 受注納入先区分を所属マスターから取得 2020/07/07 */
		if(getBean.getOrderDeliveryKbn() == null){
			frm.setDeliveryDivision("2");
		}
		else{
			frm.setDeliveryDivision(getBean.getOrderDeliveryKbn());
		}
		
		// 合計に0をいれておく
		frm.setTotalOrderAmount("0");
		frm.setTotalQty("0");
		frm.setTotalWeight("0");
		frm.setCalcCarryFare("0");
		
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "newPage", "新規処理　終了");

		return mapping.findForward("success");
	}


	
	/**
	 * 新規・更新処理.（登録ボタンの押下処理）
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward regist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		ActionMessages errorMsg = new ActionMessages();
		
		if (getLog().isDebugEnabled()) {
			getLog().debug("regist");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "regist","新規・更新処理　開始");

		OrderImportDetailForm frm = (OrderImportDetailForm) form;
		
		String tantoCd = getLoginInfo(request).getTantoCd(); // ログインユーザ取得

		// スクロールフラグ:0
		frm.setScrollFlg(0);

		// 各行の合計を再計算
		orderImportDetailLogic.calcOrderAmout(frm , this.numberChkDigitCtl);
		// 品目リスト合計計算
		orderImportDetailLogic.calcTotalOrderAmount(frm, this.numberChkDigitCtl);
		
		int i = 1;
		// *****************************************************************************
		// 新規登録時は品目コードがうちこまれている場所のみ数量チェック & 品目コードが1行も入力されていなければエラー
		//******************************************************************************
		if(frm.getInsertFlg() == 1){ 
			int count = 0;
			//数量入力チェック
			for(OrderImportDetailList bean : frm.getOrderImportDetailList()){

				// 削除レコードはチェックしない
				if(bean.getDelFlg().intValue() == 1 && bean.getCancelFlg().intValue() == 1){
					continue;
				}
				
				//品目コードが空ではないかつ個数が0以下ならエラー
				if( AecStringUtils.isNotNullAndEmpty(bean.getItemCd()) && (AecNumberUtils.isBigDecimalZero(AecNumberUtils.convertBigDecimalNullToZeroFromString(bean.getOrderQty())))){
					errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.order.qty.row", i));
				}
				if( AecStringUtils.isNotNullAndEmpty(bean.getItemCd())){
					//品目コードが空ではない行があったらcountに1加算
					count++;
				}
				
				i++;
			}
			
			//countが0 = 一行も品目コードが入力されていなかったらエラー
			if(count <= 0){
				errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.insert.itemcd"));
			}
			
		} else{
			//数量入力チェック
			for(OrderImportDetailList bean : frm.getOrderImportDetailList()){
				
				if(bean.getDelFlg().intValue() == 1 && bean.getCancelFlg().intValue() == 1){
					continue;
				}
				
				// 数量のゼロ不可チェック
				if (AecNumberUtils.isBigDecimalZero(AecNumberUtils.convertBigDecimalNullToZeroFromString(bean.getOrderQty()))) {
					errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.order.qty.row", i));
				}
				i++;
			}
		}
		//エラーがあったら中断
		if(!errorMsg.isEmpty()){
			saveErrors(request, errorMsg);
			return mapping.findForward("success");
		}
		
		// 日付のチェック
		if (!isValidDate(frm, request)) {
			return mapping.findForward("success");
		}
		
		// マスターチェック
		if (!hasNotMasterError(frm, request)) {
			return mapping.findForward("success");
		}
		
		// 各行の合計を再計算
		orderImportDetailLogic.calcOrderAmout(frm , this.numberChkDigitCtl);
		// 品目リスト合計計算
		orderImportDetailLogic.calcTotalOrderAmount(frm, this.numberChkDigitCtl);

		// コンフリクト後、登録データが新規だった場合、初期画面に遷移
		boolean doForwardInit = doConflictForwardinit(frm.getConflictOrderHeadList());
		
		// ここから追加処理
		try {
			// 更新処理 内部で同一集約条件があるかチェックし、ない場合、登録する。ある場合、登録せず「既に登録されている受注と合わせて表示を行います。」のメッセージを返す。
			errorMsg = orderImportDetailLogic.regist(frm, tantoCd, true , frm.getFrstOrderHeadBean());
			if (!errorMsg.isEmpty()) {
				// 同一集約条件の別受注がある場合の処理。
				
				/* 処理エラーメッセージ */
				saveErrors(request, errorMsg);
				
				// 登録時に同一集約条件（同一受注区分・得意先・納入先・帳合・出荷予定日・納入予定日・運送会社・納入先宛先・納入先電話番号）が一致する場合、
				// 「既に登録されている受注と合わせて表示を行います。」と画面に表示し、集約して受注取込画面に表示する。表示後は再度登録が必要。
				if( frm.getReloadFlg() == 1 && frm.getConflictOrderImportDetailList() != null ){
					frm.setConflictOrderImportDetailList(new ArrayList<OrderImportDetailList>( frm.getOrderImportDetailList()));
					return mapping.findForward("conflict");// struts-configの抜粋。init処理を実行する。<forward name="conflict" path="/OrderImportDetail.do?op=init" />
				}
				
				return mapping.findForward("success");
			}
		}catch (OptimisticLockRuntimeException e) {
			// エラーメッセージを登録する // 他のユーザにより先に更新されました。
			saveError(request, "errors.optimisticlock.data");
			// 再検索しない
			return mapping.getInputForward();
		} catch (Exception e) {
			// 2015/4/20 受注混雑調査の為ログ追加
			outputCheckLog(getLoginInfo(request).getTantoCd(), "error",	e.getMessage());
			outputCheckLog(getLoginInfo(request).getTantoCd(), "error",	SqlLogRegistryLocator.getInstance().getLast().getCompleteSql());
		
			throw e;
		}
		
		boolean hasActiveOrder = false;
		for (OrderImportDetailList detailListBean : frm.getOrderImportDetailList()) {
			// 削除レコードはチェックしない
			if(detailListBean.getDelFlg().intValue() == 1){
				continue;
			}
			// キャンセルレコードはチェックしない
			if( detailListBean.getCancelFlg().intValue() == 1 ){
				continue;
			}
			hasActiveOrder = true;
		}
		
		if(!hasActiveOrder){
			frm.setOrderInvisibleButtonEnableFlg(1);
		}else{
			frm.setOrderInvisibleButtonEnableFlg(0);
		}
		
		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "regist", "登録処理　終了");

		
		if(frm.getInsertFlg() == 1 || doForwardInit){
			//新規登録ならnewPage
			return mapping.findForward("init");
		} else{
			//現在のデータベースの情報に画面更新
			try {
				/* 再検索 */
				orderImportDetailLogic.getOrderImportData(frm, tantoCd, this.numberChkDigitCtl);
			} catch (NoDataException e) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.deleted");
				return mapping.findForward("back");
			}
			//更新の場合は再表示
			return mapping.findForward("success");
		}
	}
	
	/**
	 * 確定処理(受注登録ボタン押下時).
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
		
		OrderImportDetailForm frm = (OrderImportDetailForm) form;
		
		String tantoCd = getLoginInfo(request).getTantoCd(); // ログインユーザコード
		String tantoOrgCd = getLoginInfo(request).getOrganizationCd(); // ログインユーザ部署コード
		
		// スクロールフラグ:0
		frm.setScrollFlg(0);

		// 各行の合計を再計算
		orderImportDetailLogic.calcOrderAmout(frm , this.numberChkDigitCtl);
		// 品目リスト合計計算
		orderImportDetailLogic.calcTotalOrderAmount(frm, this.numberChkDigitCtl);
		
		// 日付のチェック
		if (!this.isValidDate(frm, request)) {
			return mapping.findForward("success");
		}
		
		// マスターチェック
		if (!this.hasNotMasterError(frm, request)) {
			return mapping.findForward("success");
		}
		

		//現在のデータベースの情報に画面更新

		// 受注ヘッダ情報の取得
		FrstOrderHead frstHeadBean = frm.getFrstOrderHeadBean();
		try {
			/* 再検索 */
			orderImportDetailLogic.getOrderImportData(frm, tantoCd, this.numberChkDigitCtl);
		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}
		
		//数量チェック
		if(!hasNotOrderQuantityError(frm, request)){
			return mapping.findForward("success");
		}

		// 承認済みチェック
		if( !this.isApprovaled(frm, request)){
			return mapping.findForward("success");
		}
		
		// 全キャンセルチェック
		if( this.isAllCanceled( frm , request ) ){
			return mapping.findForward("success");
		}
		
		try {
			// 更新処理 内部で同一集約条件があるかチェックし、ない場合、登録する。ある場合、登録せず「既に登録されている受注と合わせて表示を行います。」のメッセージを返す。
			ActionMessages errMsg = orderImportDetailLogic.fix(frm, tantoCd, tantoOrgCd , true , frstHeadBean);
			if(!errMsg.isEmpty()){
				// 登録処理で、同一集約条件の受注があった場合、メッセージを表示し、品目明細を集約して表示する。
				saveErrors(request, errMsg);
				
				// 登録時に同一集約条件（同一受注区分・得意先・納入先・帳合・出荷予定日・納入予定日・運送会社・納入先宛先・納入先電話番号）が一致する場合、
				// 「既に登録されている受注と合わせて表示を行います。」と画面に表示し、集約して受注取込画面に表示する。表示後は再度登録が必要。
				if( frm.getReloadFlg() == 1 && frm.getConflictOrderImportDetailList() != null ){
					frm.setConflictOrderImportDetailList(new ArrayList<OrderImportDetailList>( frm.getOrderImportDetailList()));
					return mapping.findForward("conflict");// struts-configの抜粋。init処理を実行する。<forward name="conflict" path="/OrderImportDetail.do?op=init" />
				}
				
				return mapping.findForward("success");
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
				orderImportDetailLogic.outPutErrorLog(e.getModuleCd(), e.getInsideErrCd(), e.getInsideErrMsg(), tantoCd);
			}
			
			outputCheckLog(getLoginInfo(request).getTantoCd(), "fix","確定処理　終了");
			return mapping.getInputForward();
		}
		
		
		//更新処理を実行しました。
		saveMessage(request, "message.complete.update");
		
		//現在のデータベースの情報に画面更新
		try {
			//変更フラグ1:変更可
			frm.setOrderCancelButtonEnableFlg(1);
			/* 再検索 */
			orderImportDetailLogic.getOrderImportData(frm, tantoCd, this.numberChkDigitCtl);
		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}
		outputCheckLog(getLoginInfo(request).getTantoCd(), "fix", "確定処理　終了");
		
		return mapping.findForward("success");
	}
	
	/**
	 * 入力承認処理（入力承認ボタン押下時）
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approval(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("approval.");
		}
		
		OrderImportDetailForm frm = (OrderImportDetailForm) form;
		
		String tantoCd = getLoginInfo(request).getTantoCd(); // ログインユーザコード
		String tantoOrgCd = getLoginInfo(request).getOrganizationCd(); // ログインユーザ部署コード
		
		// スクロールフラグ:0
		frm.setScrollFlg(0);

		// 日付のチェック
		if (!isValidDate(frm, request)) {
			return mapping.findForward("success");
		}
		
		// マスターチェック
		if (!hasNotMasterError(frm, request)) {
			return mapping.findForward("success");
		}
		
		//現在のデータベースの情報に画面更新
		try {
			/* 再検索 */
			orderImportDetailLogic.getOrderImportData(frm, tantoCd, this.numberChkDigitCtl);
		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}
		
		try {
			// 更新処理を実行
			ActionMessages errMsg = orderImportDetailLogic.approval(frm, tantoCd, tantoOrgCd);
			if(!errMsg.isEmpty()){
				saveErrors(request, errMsg);
				return mapping.findForward("success");
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
				orderImportDetailLogic.outPutErrorLog(e.getModuleCd(), e.getInsideErrCd(), e.getInsideErrMsg(), tantoCd);
			}
			
			return mapping.getInputForward();
		}
		
		outputCheckLog(getLoginInfo(request).getTantoCd(), "approval", "入力承認　終了");
		
		//更新処理を実行しました。
		saveMessage(request, "message.complete.update");
		
		//現在のデータベースの情報に画面更新
		try {
			//変更フラグ1:変更可
			frm.setOrderCancelButtonEnableFlg(1);
			/* 再検索 */
			orderImportDetailLogic.getOrderImportData(frm, tantoCd, this.numberChkDigitCtl);
		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}
		
		return mapping.findForward("back");
	}
	
	/**
	 * 確定取消処理(確定取消ボタン押下時).
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward cancel(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("cancel.");
		}
		
		OrderImportDetailForm frm = (OrderImportDetailForm) form;
		
		String tantoCd = getLoginInfo(request).getTantoCd();
		// スクロールフラグ:0
		frm.setScrollFlg(0);
		// 20220513 add S.Fujimaki　排他制御追加
		// 再検索前に変更前の受注を退避
		List<OrderImportDetailList> beforeList = frm.getBeforeOrderImportDetailList();
		// 20220513 add end S.Fujimaki
		//現在のデータベースの情報に画面更新
		try {
			/* 再検索 */
			orderImportDetailLogic.getOrderImportData(frm, tantoCd, this.numberChkDigitCtl);
		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		// 20220513 add S.Fujimaki 排他制御追加
		// 再検索後に変更前の受注をセット
		frm.setBeforeOrderImportDetailList(beforeList);
		// 20220513 add end S.Fujimaki
		
		try {
			// 更新処理を実行
			ActionMessages errMsg = orderImportDetailLogic.cancel(frm, tantoCd);
			if(!errMsg.isEmpty()){
				saveErrors(request, errMsg);
				return mapping.findForward("success");
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
				orderImportDetailLogic.outPutErrorLog(e.getModuleCd(), e.getInsideErrCd(), e.getInsideErrMsg(), tantoCd);
			}
			
			outputCheckLog(getLoginInfo(request).getTantoCd(), "cancel",
				"確定取消処理　終了");
			return mapping.getInputForward();
		}
		
		outputCheckLog(getLoginInfo(request).getTantoCd(), "cancel", "確定取消処理　終了");
		
		//更新処理を実行しました。
		saveMessage(request, "message.complete.update");
		
		//現在のデータベースの情報に画面更新
		try {
			/* 再検索 */
			orderImportDetailLogic.getOrderImportData(frm, tantoCd, this.numberChkDigitCtl);
		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}
		
		return mapping.findForward("success");
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
	public ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("delete.");
		}

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "delete", "削除処理　開始");

		// formを仕入一覧 Formクラスにキャスト
		OrderImportDetailForm frm = (OrderImportDetailForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd(); // ログインユーザ取得
		// スクロールフラグ:0
		frm.setScrollFlg(0);
		try {
			orderImportDetailLogic.delete(frm, tantoCd);
		} catch (NoDataException e) {
			// エラーメッセージを登録する
			saveError(request, "errors.nodata.deleted");
			// 2015/4/20 受注混雑調査の為ログ追加
			outputCheckLog(getLoginInfo(request).getTantoCd(), "delete",
				"削除処理　終了");
			return mapping.getInputForward();
		} catch (OptimisticLockRuntimeException e) {
			// エラーメッセージを登録する // 他のユーザにより先に更新されました。
			saveError(request, "errors.optimisticlock.data");
			// 再検索しない
			return mapping.getInputForward();
		} catch (Exception e) {
			/* 処理エラーメッセージ */
			outputCheckLog(getLoginInfo(request).getTantoCd(), "error",
				e.getMessage());
			
			outputCheckLog(getLoginInfo(request).getTantoCd(), "error",
				SqlLogRegistryLocator.getInstance().getLast().getCompleteSql());

			throw e;
		}

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "delete", "削除処理　終了");

		return mapping.findForward("back");
	}

	/**
	 * 非表示処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward invisible(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("delete.");
		}

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "invisible", "非表示処理　開始");

		// formを仕入一覧 Formクラスにキャスト
		OrderImportDetailForm frm = (OrderImportDetailForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd(); // ログインユーザ取得
		// スクロールフラグ:0
		frm.setScrollFlg(0);
		try {
			orderImportDetailLogic.invisible(frm, tantoCd);
		} catch (NoDataException e) {
			// エラーメッセージを登録する
			saveError(request, "errors.nodata.deleted");
			// 2015/4/20 受注混雑調査の為ログ追加
			outputCheckLog(getLoginInfo(request).getTantoCd(), "invisible",
				"非表示処理　終了");
			return mapping.getInputForward();
		} catch (OptimisticLockRuntimeException e) {
			// エラーメッセージを登録する // 他のユーザにより先に更新されました。
			saveError(request, "errors.optimisticlock.data");
			// 再検索しない
			return mapping.getInputForward();
		} catch (Exception e) {
			/* 処理エラーメッセージ */
			outputCheckLog(getLoginInfo(request).getTantoCd(), "error",
				e.getMessage());
			
			outputCheckLog(getLoginInfo(request).getTantoCd(), "error",
				SqlLogRegistryLocator.getInstance().getLast().getCompleteSql());

			throw e;
		}

		/* メッセージ */
		saveMessage(request, "message.complete.invisible");

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "invisible", "非表示処理　終了");

		return mapping.findForward("back");
	}

	
	/**
	 * 戻る処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "back", "戻る処理　開始");
		outputCheckLog(getLoginInfo(request).getTantoCd(), "back", "戻る処理　終了");

		return mapping.findForward("back");
	}
	

	/**
	 * 納入先オートコンプリート・ボタン選択後処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getDeliveryRelatedData(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("getDeliveryRelatedData.");
		}
		
		OrderImportDetailForm frm = (OrderImportDetailForm) form;
		// スクロールフラグ:0
		frm.setScrollFlg(0);
		//登録済み項目の初期化
		frm.setPrintSummery(null);
		frm.setDeliverySlipSummery(null);
		frm.setOrderSummery(null);
		frm.setSalesTantoCd(null);
		// 20210909 Asclab Saita 納期連絡表専用備考追加対応
		frm.setDeliverydateContactSummery(null);
		//20210526 add S.Fujimaki　営業担当者名表示
		frm.setSalesTantoName(null);
		//20210526 add S.Fujimaki　営業担当者名表示		
		DeliveryInfo deliveryRelatedData = deliveryInfoDao.getEntity(frm.getDeliveryCd());
		//住所＝納入先住所1＋納入先住所2＋納入先住所3
		StringBuffer buf = new StringBuffer("");
		if (deliveryRelatedData.getAddress1() != null) {
			buf.append(deliveryRelatedData.getAddress1());
		}
		if (deliveryRelatedData.getAddress2() != null) {
			buf.append(deliveryRelatedData.getAddress2());
		}
		if (deliveryRelatedData.getAddress3() != null) {
			buf.append(deliveryRelatedData.getAddress3());
		}
		
		//住所
		frm.setAddress(buf.toString());
		//納入先電話番号
		frm.setDeliveryTelNo(deliveryRelatedData.getTelNo());
		//リードタイム
		frm.setLeadTime(AecNumberUtils.convertStringNullToOneFromBigDecimal(deliveryRelatedData.getLeadTime()));
		//運送会社コード
		frm.setCarryCd(deliveryRelatedData.getCarryCd());
		//納入時刻
		frm.setDeliveryExpectedTime(deliveryRelatedData.getDeliveryTime());
		//営業担当者コード
		frm.setSalesTantoCd(deliveryRelatedData.getTantoCd());
		//20210526 add S.Fujimaki　営業担当者名表示
		//営業担当者名
		frm.setSalesTantoName(loginDao.getEntity(deliveryRelatedData.getTantoCd()).getTantoNm());
		//20210526 add S.Fujimaki　営業担当者名表示
		
		if(AecNumberUtils.convertNullToZero(deliveryRelatedData.getFareClaimExistence()).equals(BigDecimal.ONE)){
			frm.setBlnCarryInvoiceFlag(Boolean.TRUE);
		}else{
			frm.setBlnCarryInvoiceFlag(Boolean.FALSE);
		}
		
		//得意先グループコード
		if(deliveryRelatedData.getVenderGroupCd() == null){
			//得意先グループコードが無かった場合99999：設定なしにする
			frm.setVenderGroupCd("99999");
		} else{
			//ある場合はセット
			frm.setVenderGroupCd(deliveryRelatedData.getVenderGroupCd());
		}

		ActionMessages messages = new ActionMessages();
		String tmpStrDate = null;
		//出荷予定日が入力されていない状態の時は休日考慮して受注日から計算する
		if(StringUtils.isEmpty(frm.getScheduledShippingDate())){
			//出荷予定日を計算してセットする
			tmpStrDate = calcDateFromCalendar(frm.getOrderDate(), null, null, messages, REPLACEMENT_NUMBER.SCHEDULED_SHIPPING_DATE);
			frm.setScheduledShippingDate(tmpStrDate);
		}

		//納入予定日を計算してセットする
		tmpStrDate = null;
		tmpStrDate = calcDateFromCalendar(frm.getScheduledShippingDate(), 
			AecNumberUtils.convertBigDecimalNullToZeroFromString(frm.getLeadTime()), null, messages, REPLACEMENT_NUMBER.DELIVERY_EXPECTED_DATE);
		frm.setDeliveryExpectedDate(tmpStrDate);
		
		//　エラー状態がエラーの時には品目行のリセットは行わない(客先品目情報などが消えてしまう恐れがあるため)
		if((frm.getErrorStatus() != null && !frm.getErrorStatus().equals("RED")) || frm.getInsertFlg() == 1){
			// 元の行数保持
			int count = frm.getOrderImportDetailListCount();
			// 全て削除
			frm.setOrderImportDetailList(new ArrayList<OrderImportDetailList>());
			
			// 元々存在してた行の分空白行を追記
			for(int i = 0; i < count; i++){
				this.addSearchList(frm, i);
			}
			
			// その他初期化
			frm.setOrderDetailVenderList(new ArrayList<OrderDetailVenderList>());
			frm.setOrganizationCd(null);
			frm.setOrganizationName(null);
			frm.setBalanceCd(null);
	
			frm.setOrderImportDetailListCount(frm.getOrderImportDetailList().size());
		}else{
			// リセットを行わない場合、帳合コードを再検索
			String balanceCd = null;
			if( frm.getOrderImportDetailList() != null && AecStringUtils.isNotNullAndEmpty( frm.getDeliveryCd())){
				for( OrderImportDetailList detailList : frm.getOrderImportDetailList() ){

					if( AecStringUtils.isNotNullAndEmpty( detailList.getItemCd() ) ){
						List<OrderItemForAutoComplete> list = orderItemForAutoCompleteDao
								.getSearchList(detailList.getItemCd() , frm.getDeliveryCd(), frm.getOrderDivision(), null,
									Constants.AUTOCOMPLETTE_ROW_LIMIT);
						
						if(list.size() > 0 && AecStringUtils.isNotNullAndEmpty( list.get(0).getBalanceCd() ) ){
							balanceCd = list.get(0).getBalanceCd();
							break;
						}
					}
					
				}
			}
			
			frm.setBalanceCd(balanceCd);
			
			if(frm.getBalanceCd() != null){
				// 得意先リスト情報取得
				getVenderList(frm);
				
				// 担当部署取得設定
				getVenderDetail(frm);
			}
		}

		// 各行の合計を再計算
		orderImportDetailLogic.calcOrderAmout(frm , this.numberChkDigitCtl);
		// 品目リスト合計計算
		orderImportDetailLogic.calcTotalOrderAmount(frm, this.numberChkDigitCtl);
		
		if (!messages.isEmpty()) {
			saveErrors(request, messages);
		}
		
		frm.setCursor("deliveryCd");
		
		return mapping.findForward("success");
	
	}

	/**
	 * 受注日変更時処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward afterChangeOrderDate(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("afterChangeOrderDate.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "afterChangeOrderDate", "受注日変更時処理　開始");

		OrderImportDetailForm frm = (OrderImportDetailForm) form;
		// スクロールフラグ:0
		frm.setScrollFlg(0);
		ActionMessages messages = new ActionMessages();
		String tmpStrDate = null;

		//出荷予定日を計算してセットする
		tmpStrDate = calcDateFromCalendar(frm.getOrderDate(), null, null, messages, REPLACEMENT_NUMBER.SCHEDULED_SHIPPING_DATE);
		frm.setScheduledShippingDate(tmpStrDate);

		//リードタイムがnullでない場合は納入予定日を更新する
		if(frm.getLeadTime() != null){
			tmpStrDate = null;
			//納入予定日を計算してセットする
			tmpStrDate = calcDateFromCalendar(frm.getScheduledShippingDate(), 
				AecNumberUtils.convertBigDecimalNullToZeroFromString(frm.getLeadTime()), null, messages, REPLACEMENT_NUMBER.DELIVERY_EXPECTED_DATE);
			frm.setDeliveryExpectedDate(tmpStrDate);
		}
		
		if (!messages.isEmpty()) {
			//エラーがある場合はここで登録
			saveErrors(request, messages);
		}

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "afterChangeOrderDate", "受注日変更時処理　終了");
		return mapping.findForward("success");
	}

	/**
	 * 出荷予定日変更時処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward afterChangeScheduledShippingDate(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("afterChangeScheduledShippingDate.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "afterChangeScheduledShippingDate",
			"出荷予定日変更時処理　開始");

		OrderImportDetailForm frm = (OrderImportDetailForm) form;
		// スクロールフラグ:0
		frm.setScrollFlg(0);
		ActionMessages messages = new ActionMessages();
		String tmpStrDate = null;
		//リードタイムがnullでない場合は納入予定日を更新する
		if(frm.getLeadTime() != null){
			//納入予定日を計算してセットする
			tmpStrDate = calcDateFromCalendar(frm.getScheduledShippingDate(), 
				AecNumberUtils.convertBigDecimalNullToZeroFromString(frm.getLeadTime()), null, messages, REPLACEMENT_NUMBER.DELIVERY_EXPECTED_DATE);
			frm.setDeliveryExpectedDate(tmpStrDate);
		}

		if (!messages.isEmpty()) {
			//エラーがある場合はここで登録
			saveErrors(request, messages);
		}

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "afterChangeScheduledShippingDate",
			"出荷予定日変更時処理　終了");
		return mapping.findForward("success");
	}
	
	/**
	 * 運賃計算処理(詳細画面の運賃計算ボタン押下時)
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward calcCarryFare(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("calcCarryFare.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "calcCarryFare",
			"運賃計算処理　開始");
		/* formをOrderImportDetailFormにキャスト */
		OrderImportDetailForm frm = (OrderImportDetailForm) form;

		/* ログイン情報取得*/
		String tantoCd = getLoginInfo(request).getTantoCd();

		// 各行の合計を再計算
		orderImportDetailLogic.calcOrderAmout(frm , this.numberChkDigitCtl);
		// 品目リスト合計計算
		orderImportDetailLogic.calcTotalOrderAmount(frm, this.numberChkDigitCtl);
		
		// 自動計算運賃
		BigDecimal calcCarryFare = BigDecimal.ZERO;
		
		try {
			// 運賃計算
			calcCarryFare = orderImportDetailLogic.calcCarryFare( frm.getDeliveryCd(), frm, null , tantoCd);
		} catch (NoDataException ex) {
			// 計算運賃に異常値をセット
			frm.setCalcCarryFare("9,999,999");
			// 画面上部にメッセージセット
			saveError(request, "errors.orderimport.carryfare.nodata");
			// 2015/4/20 受注混雑調査の為ログ追加
			outputCheckLog(getLoginInfo(request).getTantoCd(), "calcCarryFare", "運賃計算処理　終了");
			return mapping.findForward("success");
		}
		// 桁数チェック
		String strCalcCarryFare = checkDigitUtilsLogic.format( numberChkDigitCtl.getCheckDigitDetail(checkDigitUtilsLogic,Constants.URKINGAKU, VENDER_DIVISION, frm.getVenderCd()), calcCarryFare);
		// 画面表示項目にセット
		frm.setCalcCarryFare(strCalcCarryFare);

		frm.setCarryFare(strCalcCarryFare);
		
		// 画面上部にメッセージセット
		saveMessage(request, "message.orderimport.carryfare.calc");

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "calcCarryFare",
			"運賃計算処理　終了");

		frm.setDirtyFlg(true);
		frm.setFareCalcFlg(true);
		
		frm.setCursor("carryFare");
		// スクロールフラグ:1
		frm.setScrollFlg(1);
		
		return mapping.findForward("success");
	}

	/**
	 * 備考検索処理(詳細画面の備考取得ボタン押下時)
	 *
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getRemarkList(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("getRemarkList.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "getRemarkList",
			"備考検索処理　開始");

		/* formをPurchaseDetailFormにキャスト */
		OrderImportDetailForm frm = (OrderImportDetailForm) form;

		List<OrderRemarkList> remarkList = null;

		// 各行の合計を再計算
		orderImportDetailLogic.calcOrderAmout(frm , this.numberChkDigitCtl);
		// 品目リスト合計計算
		orderImportDetailLogic.calcTotalOrderAmount(frm, this.numberChkDigitCtl);

		try {
			// 備考の検索
			remarkList = orderImportDetailLogic.getRemarkList(frm);
		} catch (NoDataException ex) {
			// 画面上部にメッセージセット
			saveMessage(request, "message.order.getremark.nodata");
			// 2015/4/20 受注混雑調査の為ログ追加
			outputCheckLog(getLoginInfo(request).getTantoCd(), "getRemarkList",
				"備考検索処理　終了");
			return mapping.findForward("success");
		}

		// *******************************
		// 取得した備考をセット
		// *******************************
		StringBuffer sbfRemark = new StringBuffer();
		if (frm.getOrderSummery().length() != 0) {
			sbfRemark.append(frm.getOrderSummery());
		}
		// 取得した備考を全てセット、区切りとして改行を追加
		for (int i = 0; i < remarkList.size(); i++) {

			if (remarkList.get(i).getRemark01() != null) {
				if (sbfRemark.length() != 0) {
					sbfRemark.append(System.getProperty("line.separator"));
				}
				sbfRemark.append(remarkList.get(i).getRemark01());
			}
		}

		// 画面表示項目にセット
		frm.setOrderSummery(sbfRemark.toString());
		// 画面上部にメッセージセット
		saveMessage(request, "message.order.getremark.found");

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "getRemarkList",
			"備考検索処理　終了");

		frm.setCursor("printSummery");
		
		// スクロールフラグ:1
		frm.setScrollFlg(1);
		return mapping.findForward("success");
	}

	/**
	 * 行追加処理.addlist
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward addlist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("addlist.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "addlist", "行追加処理　開始");

		OrderImportDetailForm frm = (OrderImportDetailForm) form;

		// 2021/12/16 数量チェックなしで行追加できるように修正
//		//数量が入力されていない行があるかチェック、あればエラー
//		for(OrderImportDetailList bean : frm.getOrderImportDetailList()){
//			if(!AecStringUtils.isNotNullAndEmpty(bean.getOrderQty())){
//				// 行を追加するには数量が入力されていなければなりません。
//				saveError(request, "errors.orderimport.nodata.quantity");
//
//				return mapping.findForward("success");
//			}
//		}
		
		// 行追加後のカーソル位置設定用
		int row = 0;
			
		for (int i = 0; i < frm.getOrderImportDetailListCount(); i++) {
			OrderImportDetailList bean = frm.getOrderImportDetailList().get(i);
			row = i;

			// チェックされてる行がある場合
			if (bean.getCheckline()) {
				// チェックされた行の位置に新規行追加
				addSearchList(frm, i);
				bean.setCheckline(false);
				break;
			}
			
			//最後までチェックがなければ最終行に追加
			if(row == frm.getOrderImportDetailList().size() - 1){
				addSearchList(frm, frm.getOrderImportDetailList().size());
			}
		}
		
		frm.setOrderImportDetailListCount(frm.getOrderImportDetailList().size());
		// スクロールフラグ:3
		frm.setScrollFlg(3);

		return mapping.findForward("success");
	}

	/**
	 * 行削除処理.dellist
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward dellist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("dellist.");
		}
		
		int delFlg = 1;
		int cancelFlg = 0;

		OrderImportDetailForm frm = (OrderImportDetailForm) form;

		delcancelAction(delFlg, cancelFlg, frm);

		boolean existsAliveRow = false;
		// 未削除の行がないか走査
		for(int i = 0; i < frm.getOrderImportDetailList().size(); i++){
			if(!existsAliveRow && frm.getOrderImportDetailList().get(i).getDelFlg().intValue() == 0){
				existsAliveRow = true;
				break;
			}
		}
		
		// 全ての行が削除されていれば、空白行を追加
		if(!existsAliveRow){
			addSearchList(frm, frm.getOrderImportDetailList().size());
			frm.setTotalOrderAmount("0");
		}

		
		frm.setOrderImportDetailListCount(frm.getOrderImportDetailList().size());

		
		// 行番号の再設定
		for(int i = 0; i < frm.getOrderImportDetailList().size(); i++){
			frm.getOrderImportDetailList().get(i).setViewSeq(new BigDecimal(i + 1)); // 表示順
		}
		
		frm.setCursor("itemCd" + 0);
		// スクロールフラグ:2
		frm.setScrollFlg(2);

		
		return mapping.findForward("success");
	}
	/**
	 * 行キャンセル処理.cancellist
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward cancellist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("cancellist.");
		}
		
		int delFlg = 0;
		int cancelFlg = 1;

		OrderImportDetailForm frm = (OrderImportDetailForm) form;


		delcancelAction(delFlg, cancelFlg, frm);
		
		frm.setOrderImportDetailListCount(frm.getOrderImportDetailList().size());
		
		
		// スクロールフラグ:2
		frm.setScrollFlg(2);
		
		return mapping.findForward("success");
	}

	/**
	 * 削除/キャンセルボタン押下時のアクション処理
	 * @param delFlg
	 * @param cancelFlg
	 * @param frm
	 * @throws NoDataException
	 */
	private void delcancelAction(int delFlg, int cancelFlg,
			OrderImportDetailForm frm) throws NoDataException {
		for (int i = frm.getOrderImportDetailList().size() - 1; i >= 0; i--) {
			OrderImportDetailList bean = frm.getOrderImportDetailList().get(i);
			if (bean.getCheckline()) {
				//すでにDBに登録済みの行の場合、行削除リストに追加する
				if( bean.getInsertFlg() == 1 ){
					delSearchList(frm, i);
				}else if( delFlg == 1 && bean.getFrstOrderNo() != null && bean.getFrstOrderRowNo() != null){
					bean.setDelFlg(BigDecimal.ONE);
					bean.setOrderStatusName("削除");
				}else if( cancelFlg == 1 && bean.getFrstOrderNo() != null && bean.getFrstOrderRowNo() != null){
					bean.setCancelFlg(BigDecimal.ONE);
					bean.setOrderStatusName("キャンセル");
				}else{
					delSearchList(frm, i);
				}
				bean.setCheckline(false);
			}
		}

		/* 行番号の再設定 */
		if (frm.getOrderImportDetailList().size() > 0) {
			for (int i = 0; i <= frm.getOrderImportDetailList().size() - 1; i++) {
				frm.getOrderImportDetailList().get(i).setViewSeq(new BigDecimal(i + 1)); // 表示順
			}
			
			// 各行の合計を再計算
			orderImportDetailLogic.calcOrderAmout(frm , this.numberChkDigitCtl);

		}

		/* すべて削除したら空白行を追記 */
		if (frm.getOrderImportDetailList().size() == 0) {
			addSearchList(frm, 0);
			frm.setTotalOrderAmount("0");
		}
		
		// 各行の合計を再計算
		orderImportDetailLogic.calcOrderAmout(frm , this.numberChkDigitCtl);
		// 品目リスト合計計算
		orderImportDetailLogic.calcTotalOrderAmount(frm, this.numberChkDigitCtl);
	}

	/**
	 * 仮単価化
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward changeTmpUnitprice(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		
		OrderImportDetailForm frm = (OrderImportDetailForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();


		try{
			ActionMessages errMsg = orderImportDetailLogic.changeTmpUnitprice(frm, tantoCd , this.numberChkDigitCtl);
			if(!errMsg.isEmpty()){
				saveErrors(request, errMsg);
				return mapping.findForward("success");
			}
		} catch (OptimisticLockRuntimeException e) {
			// エラーメッセージを登録する // 他のユーザにより先に更新されました。
			saveError(request, "errors.optimisticlock.data");
			// 再検索しない
			return mapping.getInputForward();
		} catch (Exception e) {
			outputCheckLog(getLoginInfo(request).getTantoCd(), "error",
				e.getMessage());
			
			outputCheckLog(getLoginInfo(request).getTantoCd(), "error",
				SqlLogRegistryLocator.getInstance().getLast().getCompleteSql());
			throw e;
		}
		

		//		
//		//現在のデータベースの情報に画面更新
//		try {
//			/* 再検索 */
//			orderImportDetailLogic.getOrderImportData(frm, tantoCd, this.numberChkDigitCtl);
//		} catch (NoDataException e) {
//			// 2015/4/20 受注混雑調査の為ログ追加
//			outputCheckLog(getLoginInfo(request).getTantoCd(), "init","初期処理　終了");
//			/* エラーメッセージ */
//			saveError(request, "errors.nodata.deleted");
//			return mapping.findForward("back");
//		}
//		
		
		// 各行の合計を再計算
		orderImportDetailLogic.calcOrderAmout(frm , this.numberChkDigitCtl);
		// 品目リスト合計計算
		orderImportDetailLogic.calcTotalOrderAmount(frm, this.numberChkDigitCtl);
		
		// スクロールフラグ:2
		frm.setScrollFlg(2);
		return mapping.findForward("success");
	}
	
	/**
	 * addSearchList.searchList追加
	 *
	 * @param frm
	 *            OrderImportDetailForm
	 */
	public void addSearchList(final OrderImportDetailForm frm, final int row) {
		OrderImportDetailList bean = new OrderImportDetailList();
		List<OrderImportDetailList> list = frm.getOrderImportDetailList();
		
		// 登録済みデータの中で最大の行番号を取得
		int rowNo = 1;
		for( OrderImportDetailList rowData : list ){
			if( rowNo < rowData.getFrstOrderRowNo().intValue() ){
				rowNo = rowData.getFrstOrderRowNo().intValue();
			}
		}
		
		
		bean.setFrstOrderNo(frm.getFrstOrderNo()); /* 受注番号 */
		bean.setFrstOrderRowNo(new BigDecimal(rowNo+1)); // 連番
		bean.setOrderNo(frm.getOrderNo());
		bean.setRowNo(new BigDecimal(rowNo+1));
		bean.setTmpUnitpriceFlg("1");
		bean.setValidLine(false);
		bean.setInsertFlg(1);
		bean.setDelFlg(BigDecimal.ZERO);
		bean.setCancelFlg(BigDecimal.ZERO);
		list.add(row, bean);
		frm.setOrderImportDetailList(list);

		// 行番号の再設定
		for(int i = 0; i < frm.getOrderImportDetailList().size(); i++){
			frm.getOrderImportDetailList().get(i).setViewSeq(new BigDecimal(i + 1)); // 表示順
		}
	}

	/**
	 * addConflictList.onflictOrderImportDetailList追加
	 *
	 * @param frm
	 *            OrderImportDetailForm
	 */
	public void addConflictList(final OrderImportDetailForm frm ) {
		List<OrderImportDetailList> list = frm.getOrderImportDetailList();

		// 登録対象がない場合、終了
		if( frm.getConflictOrderHeadList() == null || frm.getConflictOrderImportDetailList() == null){
			return;
		}
		
		// 詳細リストの追加
		int rowNo = 1;
		for( OrderImportDetailList rowData : list ){
			if( rowNo < rowData.getFrstOrderRowNo().intValue() ){
				rowNo = rowData.getFrstOrderRowNo().intValue();
			}
		}
		
		
		int row = list.size();
		for (OrderImportDetailList bean: frm.getConflictOrderImportDetailList() ) {
			if( AecStringUtils.isNotNullAndEmpty(bean.getItemCd()) ){
				bean.setConflictLine(Boolean.TRUE);
				bean.setFrstOrderNo(frm.getFrstOrderNo());
				bean.setFrstOrderRowNo(new BigDecimal(rowNo+1)); // 連番
				bean.setErrorMessage("");
				bean.setInsertFlg(1);
				list.add(row, bean);
				row++;
				rowNo++;
			}
		} 
		frm.setOrderImportDetailList(list);
		frm.setOrderImportDetailListCount(frm.getOrderImportDetailList().size());
		
		// ヘッダ備考の追加
		for ( FrstOrderHead confOrderHead :  frm.getConflictOrderHeadList()){
			if( !AecStringUtils.isNotNullAndEmpty( frm.getCarryFare()) && confOrderHead.getCarryFare() != null ){
				frm.setCarryFare(confOrderHead.getCarryFare().toString());
			}
			
			String orderSummery = "";
			String printSummery = "";
			String deliverySlipSummery = "";
			// 20210909 Asclab Saita 納期連絡表専用備考追加対応
			String deliverydateContactSummery = "";
			
			if(AecStringUtils.isNotNullAndEmpty( frm.getOrderSummery())){
				orderSummery = frm.getOrderSummery() + "/";
			}
			if(AecStringUtils.isNotNullAndEmpty( frm.getPrintSummery())){
				printSummery = frm.getPrintSummery() + "/";
			}
			if(AecStringUtils.isNotNullAndEmpty( frm.getDeliverySlipSummery())){
				deliverySlipSummery = frm.getDeliverySlipSummery() + "/";
			}			
			// 20210909 Asclab Saita 納期連絡表専用備考追加対応
			if(AecStringUtils.isNotNullAndEmpty( frm.getDeliverydateContactSummery())){
				deliverydateContactSummery = frm.getDeliverydateContactSummery() + "/";
			}
			
			if(AecStringUtils.isNotNullAndEmpty( confOrderHead.getOrderSummery())){
				frm.setOrderSummery( orderSummery + confOrderHead.getOrderSummery());
			}
			if(AecStringUtils.isNotNullAndEmpty( confOrderHead.getPrintSummery())){
				frm.setPrintSummery(printSummery + confOrderHead.getPrintSummery());
			}
			if(AecStringUtils.isNotNullAndEmpty( confOrderHead.getDeliverySlipSummery())){
				frm.setDeliverySlipSummery(deliverySlipSummery + confOrderHead.getDeliverySlipSummery());
			}			
			// 20210909 Asclab Saita 納期連絡表専用備考追加対応
			if(AecStringUtils.isNotNullAndEmpty( confOrderHead.getDeliverydateContactSummery())){
				frm.setDeliverydateContactSummery( deliverydateContactSummery + confOrderHead.getDeliverydateContactSummery());
			}
		}
		
		// 行番号の再設定
		for(int i = 0; i < frm.getOrderImportDetailList().size(); i++){
			frm.getOrderImportDetailList().get(i).setViewSeq(new BigDecimal(i + 1)); // 表示順
		}
		
	}
	
	/**
	 * delSearchList.searchList削除
	 *
	 * @param frm
	 *            OrderDetailForm
	 * @param i
	 *            削除するリストインデックス
	 */
	public void delSearchList(final OrderImportDetailForm frm, final int i) {
		List<OrderImportDetailList> list = frm.getOrderImportDetailList();
		list.remove(i);
		
		frm.setOrderImportDetailList(list);
	}

	/**
	 * 得意先情報取得処理
	 * @param frm 受注詳細画面FORM
	 * @return OrderImportDetailForm
	 * @throws NoDataException データが存在しない例外
	 */
	private OrderImportDetailForm getVenderList(final OrderImportDetailForm frm)
			throws NoDataException {
		// 帳合コードより得意先一覧を取得
		if (StringUtils.isNotEmpty(frm.getBalanceCd())) {
			List<OrderDetailVenderList> list = orderImportDetailLogic.getVenderList(frm.getBalanceCd());
			if ((list == null) || (list.size() == 0)) {
				frm.setOrderDetailVenderList(null);
			} else {
				frm.setOrderDetailVenderList(list);
				frm.setVenderCd(list.get(0).getVenderCd());
			}
		} else{
			frm.setOrderDetailVenderList(null);
		}
		
		return frm;
	}

	/**
	 * 取引先詳細情報取得処理
	 * @param frm 受注詳細画面FORM
	 * @return OrderImportDetailForm
	 * @throws NoDataException データが存在しない例外
	 */
	private OrderImportDetailForm getVenderDetail(final OrderImportDetailForm frm)
			throws NoDataException {

		String venderCd = new String();
		if (!frm.getOrderDetailVenderList().isEmpty()) {
			venderCd = frm.getOrderDetailVenderList().get(0).getVenderCd();
		}

		if (StringUtils.isNotEmpty(venderCd)) {
			OrderDetailVenderDetail entity = orderImportDetailLogic.getVenderDetail(venderCd);

			frm.setOrganizationCd(entity.getOrganizationCd());
			frm.setOrganizationName(entity.getOrganizationName());
		}
		return frm;
	}

	/**
	 * 備考取得処理(品目選択時処理)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getRemark(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		// ★
		outputCheckLog(getLoginInfo(request).getTantoCd(), "getRemark",
			"品目リフレッシュ　開始");
		
		OrderImportDetailForm frm = (OrderImportDetailForm) form;
		int procRow = frm.getVaridUnitpriceRow();


		OrderImportDetailList detailBean = frm.getOrderImportDetailList().get(procRow); // オートコンプリート選択行
		
		// 単価の設定
		orderImportDetailLogic.setUnitprice(frm, detailBean, this.numberChkDigitCtl);

		
		// 重量取得
		detailBean.setAllUpWeight(orderImportDetailLogic.getAllUpWeight(detailBean.getItemCd()));
		// 得意先リスト取得
		getVenderList(frm);
		// 担当部署取得設定
		getVenderDetail(frm);
		

		// 詳細List件数を設定
		frm.setOrderImportDetailListCount(frm.getOrderImportDetailList().size());
		// 各行の合計を再計算
		orderImportDetailLogic.calcOrderAmout(frm , this.numberChkDigitCtl);
		// 品目リスト合計計算
		orderImportDetailLogic.calcTotalOrderAmount(frm, this.numberChkDigitCtl);
		// 2016/9/15 品目決定時の備考取得処理を追加 ->
		List<OrderRemarkList> remarkList = null;
		
		// 検証完了
		detailBean.setValidLine(true);
		try {
			// 備考の検索
			remarkList = orderImportDetailLogic.getRemarkList(frm);
		} catch (NoDataException ex) {
			// 画面上部にメッセージセット
			saveMessage(request, "message.order.getremark.nodata");
			// 2015/4/20 受注混雑調査の為ログ追加
			outputCheckLog(getLoginInfo(request).getTantoCd(), "getRemarkList",
				"備考検索処理　終了");

			frm.setCursor("otherCompanyCd1" + String.valueOf(procRow));

			return mapping.findForward("success");
		}

		// *******************************
		// 取得した備考をセット
		// *******************************
		StringBuffer sbfRemark = new StringBuffer();

		// 取得した備考を全てセット、区切りとして改行を追加
		for (int i = 0; i < remarkList.size(); i++) {

			if (remarkList.get(i).getRemark16() != null) {
				if (sbfRemark.length() != 0) {
					sbfRemark.append(" ");
				}
				sbfRemark.append(remarkList.get(i).getRemark16());
			}
		}
		// 画面表示項目にセット
		frm.setDeliverySlipSummery(sbfRemark.toString());
		// 2016/9/15 品目決定時の備考取得処理を追加 <-
		
		frm.setCursor("otherCompanyCd1" + String.valueOf(procRow));

		// ★
		outputCheckLog(getLoginInfo(request).getTantoCd(), "getRemark",
			"品目リフレッシュ　終了");

		
		// スクロールフラグ:2
		frm.setScrollFlg(2);
		
		return mapping.findForward("success");
	}

	/**
	 * 合計金額更新処理(数量・増付変更時処理)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward setTotal(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception{
		if (getLog().isDebugEnabled()) {
			getLog().debug("setTotal.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "setTotal",
			"合計金額更新処理　開始");

		OrderImportDetailForm frm = (OrderImportDetailForm) form;

		// 各行の合計を再計算
		orderImportDetailLogic.calcOrderAmout(frm , this.numberChkDigitCtl);
		// 品目リスト合計計算
		orderImportDetailLogic.calcTotalOrderAmount(frm, this.numberChkDigitCtl);
		
		// スクロールフラグ:2
		frm.setScrollFlg(2);

		return mapping.findForward("success");
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
	
	/**
	 * 登録時マスタチェック処理
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return boolean チェック結果 true:OK false:NG
	 */
	private boolean hasNotMasterError(final OrderImportDetailForm frm, final HttpServletRequest request) {

		ActionMessages messages = new ActionMessages();
		// 帳合コード
		orderImportCommonLogic.checkBalance(frm.getBalanceCd(), messages);
		// 取引先コード
		orderImportCommonLogic.checkVender(frm.getVenderCd(), messages);
		// 品目マスタ
		orderImportCommonLogic.checkItem(frm.getOrderImportDetailList() , frm.getInsertFlg(), messages);
		// 販売条件マスタ
		orderImportCommonLogic.checkSalesTerms(frm.getOrderImportDetailList() , frm.getBalanceCd() , frm.getDeliveryCd() , frm.getInsertFlg() , messages);
		// 受注区分で品目チェック
		orderImportCommonLogic.checkItemTypeDivision( frm.getOrderImportDetailList() , frm.getOrderDivision() , frm.getInsertFlg(),  messages);

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return false;
		}

		return true;
	}
	
	/**
	 * 承認済みチェック処理
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return boolean チェック結果 true:OK false:NG
	 */
	private boolean isApprovaled(final OrderImportDetailForm frm, final HttpServletRequest request) {
		ActionMessages messages = new ActionMessages();

		boolean hasNotApprovaled = false;
		
		// 受注データをループして、削除、キャンセルまたは未承認の情報がないかを確認
		for (OrderImportDetailList bean : frm.getOrderImportDetailList()) {
			int approvalFlg = bean.getInputApprovalFlg().intValue();
			int deleteFlg = bean.getDelFlg().intValue();
			int cancelFlg = bean.getCancelFlg().intValue();
			
			if( deleteFlg == 1 || cancelFlg == 1 || approvalFlg == 1 ){
				continue;
			}else{
				hasNotApprovaled = true;
				break;
			}
				
		}

		if (hasNotApprovaled) {
			messages.add("", new ActionMessage("errors.order.order.notapprovaled"));
			saveErrors(request, messages);
			return false;
		}
		return true;
	}
	
	/**
	 * 全てキャンセル済みのチェック処理
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return boolean チェック結果 true:OK false:NG
	 */
	private boolean isAllCanceled(final OrderImportDetailForm frm, final HttpServletRequest request) {
		ActionMessages messages = new ActionMessages();

		boolean hasActiveOrder = false;
		
		// 受注データをループして、削除、キャンセルまたは未承認の情報がないかを確認
		for (OrderImportDetailList bean : frm.getOrderImportDetailList()) {
			int approvalFlg = bean.getInputApprovalFlg().intValue();
			int deleteFlg = bean.getDelFlg().intValue();
			int cancelFlg = bean.getCancelFlg().intValue();
			
			if( deleteFlg == 1 || cancelFlg == 1 ){
				continue;
			}else{
				hasActiveOrder = true;
				break;
			}
				
		}

		if (!hasActiveOrder) {
			messages.add("", new ActionMessage("errors.order.order.allcanceled"));
			saveErrors(request, messages);
			return true;
		}
		return false;
	}
		
	
	/**
	 * DateをStringに変換設定
	 * @param OrderDetailForm
	 */
	private boolean isValidDate(final OrderImportDetailForm frm,
			final HttpServletRequest request) throws NoDataException {

		String orderDate = frm.getOrderDate();
		String scheduledShippingDate = frm.getScheduledShippingDate();
		String deliveryExpectedDate = frm.getDeliveryExpectedDate();

		ActionMessages messages = this.orderImportCommonLogic.checkValidDate(frm.getInsertFlg(), orderDate,
			scheduledShippingDate, deliveryExpectedDate);

		if (messages.size() > 0) {
			saveErrors(request, messages);
			return false;
		}else{
			return true;
		}
		
	}

	
	/**
	 * 数量チェック
	 * @param frm チェック対象フォーム
	 * @param request HttpServletRequest
	 * @return boolean OK：true NG:false
	 */
	private boolean hasNotOrderQuantityError(final OrderImportDetailForm frm,
			final HttpServletRequest request) {

		//
		String orderDivision = frm.getOrderDivision();
		List<OrderImportDetailList> orderImportDetailList = frm.getOrderImportDetailList();
		String frstOrderNo = frm.getFrstOrderNo();

		// 数量のチェック
		ActionMessages messages = orderImportCommonLogic.checkOrderQuantity(orderDivision,
			orderImportDetailList, frstOrderNo);

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return false;
		}
		return true;
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
	 * 休日を考慮した出荷予定日などの日付を計算
	 * @param strDate     日付(文字列)
	 * @param leadTime    リードタイム(nullの時はプロシージャ内でデフォルト値にセットされる)
	 * @param calCd       カレンダーコード(nullの時はプロシージャ内でデフォルト値にセットされる)
	 * @param messages    エラーメッセージ
	 * @param replacementNumber    対象とする日付の番号
	 * @return String 日付計算結果
	 */
	private String calcDateFromCalendar(final String strDate,
			final BigDecimal leadTime, final String calCd, final ActionMessages messages, final REPLACEMENT_NUMBER replacementNumber) {
		 ProCalcDateFromCalendarCallDto resultDto = new ProCalcDateFromCalendarCallDto();
		 String replacement = null;
		 ResourceBundle rb = ResourceBundle
					.getBundle(Constants.APPLICATION_PROPERTIES);
		 switch(replacementNumber){
		 case SCHEDULED_SHIPPING_DATE:
			 replacement = rb.getString("item.order.str.scheduled.shipping.date");
			 break;
		 case DELIVERY_EXPECTED_DATE:
			 replacement = rb.getString("item.order.str.delivery.expected.date");
			 break;
		 default:
			 return null;
		 }

		// 日付計算プロシージャ(PL/SQL)呼出
		resultDto = orderImportDetailLogic.callProCalcDateFromCalendar(strDate, leadTime, calCd);
		if(CALC_DATE_ERROR_NO_CALENDAR.compareTo(resultDto.getPNumRet()) == 0){
			 //カレンダーマスタにカレンダーコードが存在しないなどの理由で休日考慮できなかった場合でもエラーメッセージを出す
			 messages.add("", new ActionMessage("errors.order.date.nocalendar", replacement));
		}else if(CALC_DATE_ERROR_ABORT.compareTo(resultDto.getPNumRet()) == 0){
			//異常終了
			messages.add("", new ActionMessage("errors.order.date.error", replacement));
		}
		if (getLog().isDebugEnabled() && StringUtils.isNotEmpty(resultDto.getPStrErrorMsg())) {
			//プロシージャからのエラーメッセージがある場合はコンソールに出力する
			getLog().debug(resultDto.getPStrErrorMsg());
		}

		 return resultDto.getpStrResultDate();
	}

	/**
	 * 更新処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward fixedOrderUpdate(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "update", "受注強制更新処理　開始");
		ActionMessages errorMsg = new ActionMessages();

		OrderImportDetailForm frm = (OrderImportDetailForm) form;
		
		String tantoCd = getLoginInfo(request).getTantoCd(); // ログインユーザコード
		String tantoOrgCd = getLoginInfo(request).getOrganizationCd(); // ログインユーザ部署コード
		
		// 各行の合計を再計算
		orderImportDetailLogic.calcOrderAmout(frm , this.numberChkDigitCtl);
		// 品目リスト合計計算
		orderImportDetailLogic.calcTotalOrderAmount(frm, this.numberChkDigitCtl);
		// 受注ヘッダ情報の取得
		FrstOrderHead frstHeadBean = frm.getFrstOrderHeadBean();
		
		int rowNum = 1;
		//数量入力チェック
		for(OrderImportDetailList bean : frm.getOrderImportDetailList()){

			// 削除レコードはチェックしない
			if(bean.getDelFlg().intValue() == 1){
				continue;
			}

			// 数量のゼロ不可チェック
			if (AecNumberUtils.isBigDecimalZero(AecNumberUtils
					.convertBigDecimalNullToZeroFromString(bean.getOrderQty()))) {
				errorMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.order.qty.row", rowNum));
			}
			rowNum++;
		}
			
		//エラーがあったら中断
		if(!errorMsg.isEmpty()){
			saveErrors(request, errorMsg);
			return mapping.findForward("success");
		}
					
		
		// 日付のチェック
		if (!this.isValidDate(frm, request)) {
			return mapping.findForward("success");
		}
		
		// マスターチェック
		if (!this.hasNotMasterError(frm, request)) {
			return mapping.findForward("success");
		}
		
		//数量チェック
		if(!hasNotOrderQuantityError(frm, request)){
			return mapping.findForward("success");
		}

		
		// コンフリクト後、登録データが新規だった場合、初期画面に遷移
		boolean doForwardInit = doConflictForwardinit(frm.getConflictOrderHeadList());

		
		// ここから追加処理
		try {
			/** 更新処理 */
			errorMsg = orderImportDetailLogic.regist(frm, tantoCd,false,  frstHeadBean);

			// 受注ヘッダ情報の取得
			frstHeadBean = frm.getFrstOrderHeadBean();

			if (!errorMsg.isEmpty()) {
				/* 処理エラーメッセージ */
				saveErrors(request, errorMsg);
				
				if( frm.getReloadFlg() == 1 && frm.getConflictOrderImportDetailList() != null ){
					frm.setConflictOrderImportDetailList(new ArrayList<OrderImportDetailList>( frm.getOrderImportDetailList()));
					return mapping.findForward("conflict");
				}
				
				return mapping.findForward("success");
			}
		}catch (OptimisticLockRuntimeException e) {
			// エラーメッセージを登録する // 他のユーザにより先に更新されました。
			saveError(request, "errors.optimisticlock.data");
			// 再検索しない
			return mapping.getInputForward();
		} catch (Exception e) {
			outputCheckLog(getLoginInfo(request).getTantoCd(), "error",
				e.getMessage());
			
			outputCheckLog(getLoginInfo(request).getTantoCd(), "error",
				SqlLogRegistryLocator.getInstance().getLast().getCompleteSql());

			throw e;
		}
		
		try {
			// 更新処理を実行
			ActionMessages errMsg = orderImportDetailLogic.fix(frm, tantoCd, tantoOrgCd,false,frstHeadBean);
			if(!errMsg.isEmpty()){
				saveErrors(request, errMsg);
				return mapping.findForward("success");
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
				orderImportDetailLogic.outPutErrorLog(e.getModuleCd(), e.getInsideErrCd(), e.getInsideErrMsg(), tantoCd);
			}
			
			outputCheckLog(getLoginInfo(request).getTantoCd(), "fix",
				"確定処理　終了");
			return mapping.getInputForward();
		}
				
		// メッセージ
		saveMessage(request, "message.complete.update");

		//現在のデータベースの情報に画面更新
		try {
			//変更フラグ1:変更可
			frm.setOrderCancelButtonEnableFlg(1);
			/* 再検索 */
			orderImportDetailLogic.getOrderImportData(frm, tantoCd, this.numberChkDigitCtl);
		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}
		
		
		boolean hasActiveOrder = false;
		for (OrderImportDetailList detailListBean : frm.getOrderImportDetailList()) {
			// 削除レコードはチェックしない
			if(detailListBean.getDelFlg().intValue() == 1){
				continue;
			}
			// キャンセルレコードはチェックしない
			if( detailListBean.getCancelFlg().intValue() == 1 ){
				continue;
			}
			hasActiveOrder = true;
		}
		
		if(!hasActiveOrder){
			frm.setOrderInvisibleButtonEnableFlg(1);
		}else{
			frm.setOrderInvisibleButtonEnableFlg(0);
		}
		
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "update", "更新処理　終了");

		
		if( doForwardInit ){
			return mapping.findForward("init");
		}else{
			return mapping.findForward("success");
		}
		
	}

	/**
	 *  コンフリクト後、登録データが新規だった場合、初期画面に遷移
	 * @param conflictedList
	 * @return
	 */
	public boolean doConflictForwardinit(List<FrstOrderHead> conflictedList) {
		boolean doForwardInit = false;
		
		if( conflictedList != null ){
			for( FrstOrderHead frstOrderHead : conflictedList ){
				if( !AecStringUtils.isNotNullAndEmpty(frstOrderHead.getFrstOrderNo() ) ){
					doForwardInit = true;
				}
 			}
		}
		return doForwardInit;
	}
	
	/* -------------------- setter -------------------- */
	/**
	 * orderImportDetailLogicを設定します。
	 * @param orderImportDetailLogic orderImportDetailLogic
	 */
	public void setOrderDetailLogic(final OrderImportDetailLogic orderImportDetailLogic) {
		this.orderImportDetailLogic = orderImportDetailLogic;
	}

	/**
	 * 数値桁数チェック用ロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * checkLogDaoを設定します。
	 * @param checkLogDao checkLogDao
	 */
	public void setCheckLogDao(final CheckLogDao checkLogDao) {
		this.checkLogDao = checkLogDao;
	}
	//20210526 add S.Fujimaki 営業担当者表示
	/**
	 * LoginDaoを設定します
	 * @param loginDao loginDao
	 */
	public void setLoginDao(final LoginDao loginDao){
		this.loginDao = loginDao;
	}
	//20210526 add S.Fujimaki 営業担当者表示

	/**
	 * deliveryInfoDaoを設定します。
	 * @param deliveryInfoDao deliveryInfoDao
	 */
	public void setDeliveryInfoDao(DeliveryInfoDao deliveryInfoDao) {
		this.deliveryInfoDao = deliveryInfoDao;
	}

	/**
	 * BelongDaoを設定します
	 * @param belongDao belongDao
	 */
	public void setBelongDao(final BelongDao belongDao){
		this.belongDao = belongDao;
	}

	/**
	 * getValidUnitpriceLogicを取得します。
	 * @return getValidUnitpriceLogic
	 */
	public GetValidUnitpriceLogic getGetValidUnitpriceLogic() {
		return getValidUnitpriceLogic;
	}

	/**
	 * getValidUnitpriceLogicを設定します。
	 * @param getValidUnitpriceLogic getValidUnitpriceLogic
	 */
	public void setGetValidUnitpriceLogic(
			GetValidUnitpriceLogic getValidUnitpriceLogic) {
		this.getValidUnitpriceLogic = getValidUnitpriceLogic;
	}

	/**
	 * orderImportCommonLogicを取得します。
	 * @return orderImportCommonLogic
	 */
	public OrderImportCommonLogic getOrderImportCommonLogic() {
		return orderImportCommonLogic;
	}

	/**
	 * orderImportCommonLogicを設定します。
	 * @param orderImportCommonLogic orderImportCommonLogic
	 */
	public void setOrderImportCommonLogic(
			OrderImportCommonLogic orderImportCommonLogic) {
		this.orderImportCommonLogic = orderImportCommonLogic;
	}

	/**
	 * orderItemForAutoCompleteDaoを取得します。
	 * @return orderItemForAutoCompleteDao
	 */
	public OrderItemForAutoCompleteDao getOrderItemForAutoCompleteDao() {
		return orderItemForAutoCompleteDao;
	}

	/**
	 * orderItemForAutoCompleteDaoを設定します。
	 * @param orderItemForAutoCompleteDao orderItemForAutoCompleteDao
	 */
	public void setOrderItemForAutoCompleteDao(
			OrderItemForAutoCompleteDao orderItemForAutoCompleteDao) {
		this.orderItemForAutoCompleteDao = orderItemForAutoCompleteDao;
	}
}