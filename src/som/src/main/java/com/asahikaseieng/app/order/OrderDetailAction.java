/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
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

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.mgrecipe.MgrecipeConst;
import com.asahikaseieng.app.unitprice.GetValidUnitpriceLogic;
import com.asahikaseieng.dao.entity.checklog.CheckLog;
import com.asahikaseieng.dao.entity.checklog.CheckLogDao;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventory;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.entity.master.delivery.Delivery;
import com.asahikaseieng.dao.entity.master.delivery.DeliveryDao;
import com.asahikaseieng.dao.entity.master.login.LoginDao;
import com.asahikaseieng.dao.entity.master.belong.Belong;
import com.asahikaseieng.dao.entity.master.belong.BelongDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.master.varidunitprice.VaridUnitprice;
import com.asahikaseieng.dao.nonentity.orderdetailentity.OrderDetailEntity;
import com.asahikaseieng.dao.nonentity.orderdetaillist.OrderDetailList;
import com.asahikaseieng.dao.nonentity.orderdetailvenderdetail.OrderDetailVenderDetail;
import com.asahikaseieng.dao.nonentity.orderdetailvenderlist.OrderDetailVenderList;
import com.asahikaseieng.dao.nonentity.orderremarklist.OrderRemarkList;
import com.asahikaseieng.dao.nonentity.procedurecall.ProCalcDateFromCalendarCallDto;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 受注詳細 Actionクラス.
 * @author t1344224
 */
public final class OrderDetailAction extends AbstractAction {

	private OrderDetailLogic orderDetailLogic;

	private GetValidUnitpriceLogic getValidUnitpriceLogic;

	/** 単位区分 URKINGAKU */
	protected static final String UNIT_DIVISION_URKINGAKU = "URKINGAKU";

	/** 単位区分 URTANKA */
	protected static final String UNIT_DIVISION_URTANKA = "URTANKA";

	/** 得意先区分 */
	protected static final String VENDER_DIVISION = "TS";

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 調査ログ用DAO */
	private CheckLogDao checkLogDao;
	
	/** Delivery関連取得用DAO*/
	private DeliveryDao deliveryDao;
	
	/** 営業担当者名取得用DAO*/
	private LoginDao loginDao;
	
	/** 担当者受注納入先区分取得用DAO*/
	private BelongDao belongDao;
	
	/** ログの区分 */
	public static final String LOG_DIVISION = "ORDER_DETAIL";

	protected static final short REMARK_LENGTH = 42;

	/** 休日考慮日付計算 休日考慮不可能エラー */
	private final BigDecimal CALC_DATE_ERROR_NO_CALENDAR = new BigDecimal(-1);

	/** 休日考慮日付計算 異常終了 */
	private final BigDecimal CALC_DATE_ERROR_ABORT = new BigDecimal(-9);

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
	public OrderDetailAction() {
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
		OrderDetailForm frm = (OrderDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ORDER,
			Constants.TAB_ID_ORDER_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 受注区分コンボボックス
		frm.setOrderDivisionCombo(orderDetailLogic
				.getCreateOrderDivisionCombobox());

		// 運送会社コンボボックス
		frm.setCarryCombo(orderDetailLogic.getCreateCarryCombobox());

		try {
			/* 初期検索 */
			OrderDetailEntity bean = orderDetailLogic.getDetailEntity(frm
					.getOrderNo());
			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, bean);

			// 納品書備考を表示
			frm.setDeliverySlipSummeryDisp(AecStringUtils.split(frm
					.getDeliverySlipSummery(), REMARK_LENGTH));

			// 営業担当者をコピー
			frm.setEigyoTantoCd(bean.getSalesTantoCd());

			// 営業担当者名を取得
			frm.setEigyoTantoName(orderDetailLogic.getLoginName(frm
					.getEigyoTantoCd()));

			/* 受注明細品目リスト取得 */
			frm.setOrderDetailList(orderDetailLogic.getDetailList(frm
					.getOrderNo()));

			/* 初期表示時の品目リストを保持（更新時の数量チェックで使用する） */
			frm.setOrderDetailListOld(orderDetailLogic.getDetailList(frm
					.getOrderNo()));

			frm.setOrderDetailListCount(frm.getOrderDetailList().size());

			// 得意先リスト取得
			getVenderList(frm);
			
			/* ログイン情報取得*/
			String loginUserId = getLoginInfo(request).getTantoCd();
			Belong getBean = belongDao.getEntity(null,loginUserId,null);
			
			/* 受注納入先区分を所属マスターから取得 2020/07/07 */
			if(getBean.getOrderDeliveryKbn() == null){
				frm.setDeliveryDivision("2");
			}
			else{
				frm.setDeliveryDivision(getBean.getOrderDeliveryKbn());
			}

			// ##############################################
			// #####変更不可、可能判定処理 ########
			// ##############################################
			// 受注区分が１在庫引当か４例外出荷の場合
			if (frm.getOrderDivision().equals("1")
					|| frm.getOrderDivision().equals("4")) {
				frm.setUpdateFlg(0);
				for (OrderDetailList detailListBean : frm.getOrderDetailList()) {
					if (detailListBean.getStatusCd() == null) {
						frm.setUpdateFlg(1);
						continue;
					}
					if (!detailListBean.getStatusCd().equals("99")) {
						// ステータスに受注登録以外のものがあれば変更不可
						frm.setUpdateFlg(1);
					}
				}
			}
			// 受注区分が３仕入直送品の場合
			if (frm.getOrderDivision().equals("3")) {
				frm.setUpdateFlg(0);
				for (OrderDetailList detailListBean : frm.getOrderDetailList()) {
					if (detailListBean.getPurchaseStatus() == null) {
						frm.setUpdateFlg(1);
						continue;
					}
					if (!detailListBean.getPurchaseStatus().equals(
						PurchaseStatus.NOT_ISSUE)) {
						// 購買ステータスが未発行以外の場合は変更不可
						frm.setUpdateFlg(1);
					}
				}
			}
			// 受注区分が２受注生産の場合
			if (frm.getOrderDivision().equals("2")) {
				frm.setUpdateFlg(0);
				for (OrderDetailList detailListBean : frm.getOrderDetailList()) {
					if (detailListBean.getAspStatus() != null
							&& detailListBean.getStatusCd() != null) {
						if (detailListBean.getAspStatus().equals("B")
								|| detailListBean.getAspStatus().equals("D")
								|| !detailListBean.getStatusCd().equals("99")) {
							frm.setUpdateFlg(1);
						}
					}
				}
			}
			// ##############################################

			// 数値をStringに変換して設定
			setNumbersToStr(frm);
			// DateをStringに
			setDateToStr(frm);
		} catch (NoDataException e) {
			// 2015/4/20 受注混雑調査の為ログ追加
			outputCheckLog(getLoginInfo(request).getTantoCd(), "init",
				"初期処理　終了");
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}
		frm.setNewFlg(0); // 更新

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

		OrderDetailForm frm = (OrderDetailForm) form;
		
		// 検索条件をセット
		frm.setDeliveryDivision(frm.getDeliveryDivision());
		
		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ORDER,
			Constants.TAB_ID_ORDER_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();

		// 受注区分コンボボックス
		frm.setOrderDivisionCombo(orderDetailLogic
				.getCreateOrderDivisionCombobox());

		// 運送会社コンボボックス
		frm.setCarryCombo(orderDetailLogic.getCreateCarryCombobox());

		// 品目リストに空白行を追加
		OrderDetailList bean = new OrderDetailList();
		bean.setTmpUnitpriceFlg(new BigDecimal(0));
		bean.setCheckline(Boolean.FALSE);
		frm.getOrderDetailList().add(bean);
		frm.setOrderDetailListCount(frm.getOrderDetailList().size());

		// 受注日にシステム日時をセット
		// システム日時取得を取得し、日付を製造開始予定日時のデフォルトとしてセットする
		Calendar cal1 = Calendar.getInstance(); // システム日時を取得
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		frm.setStrOrderDate(df.format(cal1.getTime())); // yyyy/MM/ddの形でセット

		// 出荷予定日は受注日の翌営業日でセット
		ActionMessages messages = new ActionMessages();
		frm.setStrScheduledShippingDate(calcDateFromCalendar(frm.getStrOrderDate(), null, null, messages, REPLACEMENT_NUMBER.SCHEDULED_SHIPPING_DATE));
		if (!messages.isEmpty()) {
			//日付の計算でエラーがある場合はここで登録
			saveErrors(request, messages);
		}

		// ここを通る場合は新規処理
		frm.setNewFlg(1);

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "newPage", "新規処理　終了");

		/* ログイン情報取得*/
		String loginUserId = getLoginInfo(request).getTantoCd();
		
		Belong getBean = belongDao.getEntity(null,loginUserId,null);
		
		/* 受注納入先区分を所属マスターから取得 2020/07/07 */
		if(getBean.getOrderDeliveryKbn() == null){
			frm.setDeliveryDivision("2");
		}
		else{
			frm.setDeliveryDivision(getBean.getOrderDeliveryKbn());
		}
		return mapping.findForward("success");
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
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "update", "更新処理　開始");

		OrderDetailForm frm = (OrderDetailForm) form;

		// Stringの数値を数値へ変換
		setStrToNumber(frm);
		// StrDateをDateへ
		setStrToDate(frm);

		// マスタチェック
		if (!isCheckMaster(frm, request)) {
			return mapping.findForward("success");
		}

		// 数量チェック
		if (!isOrderQuantityCheck(frm, request)) {
			return mapping.findForward("success");
		}
		// 各行の合計を再計算
		calcOrderAmout(frm);
		// 品目リスト合計計算
		calcSumOrderAmount(frm);

		// ログイン担当者コード取得
		String loginUserId = getLoginInfo(request).getTantoCd(); // ログインユーザ取得
		String loginUserOrganizationCd = getLoginInfo(request)
				.getOrganizationCd(); // ログインユーザ部署コード

		try {
			// 更新処理を実行
			orderDetailLogic.update(frm, loginUserId, loginUserOrganizationCd);

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
				orderDetailLogic.outPutErrorLog(e.getModuleCd(), e
						.getInsideErrCd(), e.getInsideErrMsg(), loginUserId);
			}
			// 2015/4/20 受注混雑調査の為ログ追加
			outputCheckLog(getLoginInfo(request).getTantoCd(), "update",
				"更新処理　終了");
			return mapping.getInputForward();
		}

		// メッセージ
		saveMessage(request, "message.complete.update");

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "update", "更新処理　終了");

		return mapping.findForward("back");
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
	public ActionForward insert(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("insert");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "insert",
			"新規登録処理　開始");

		OrderDetailForm frm = (OrderDetailForm) form;
		
		// Stringの数値を数値へ変換
		setStrToNumber(frm);
		// StrDateをDateへ
		setStrToDate(frm);

		// 日付のチェック
		if (!checkDate(frm, request)) {
			return mapping.findForward("success");
		}
		// マスタチェック
		if (!isCheckMaster(frm, request)) {
			return mapping.findForward("success");
		}

		// 数量チェック
		if (!isOrderQuantityCheck(frm, request)) {
			return mapping.findForward("success");
		}

		// 各行の合計を再計算
		calcOrderAmout(frm);
		// 品目リスト合計計算
		calcSumOrderAmount(frm);

		// ここから追加処理
		String loginUserId = getLoginInfo(request).getTantoCd(); // ログインユーザ取得
		String loginUserOrganizationCd = getLoginInfo(request)
				.getOrganizationCd(); // ログインユーザ部署コード

		try {
			orderDetailLogic.insert(frm, loginUserId, loginUserOrganizationCd);

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
				orderDetailLogic.outPutErrorLog(e.getModuleCd(), e
						.getInsideErrCd(), e.getInsideErrMsg(), loginUserId);
			}
			// 2015/4/20 受注混雑調査の為ログ追加
			outputCheckLog(getLoginInfo(request).getTantoCd(), "insert",
				"新規登録処理　終了");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "insert",
			"新規登録処理　終了");

		return mapping.findForward("init");
		// return mapping.findForward("success");
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
			getLog().debug("delete");
		}

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "delete", "削除処理　開始");

		// formを仕入一覧 Formクラスにキャスト
		OrderDetailForm frm = (OrderDetailForm) form;
		String loginUserId = getLoginInfo(request).getTantoCd(); // ログインユーザ取得

		try {
			orderDetailLogic.delete(frm, loginUserId);
		} catch (NoDataException e) {
			// エラーメッセージを登録する
			saveError(request, "errors.nodata.deleted");
			// 2015/4/20 受注混雑調査の為ログ追加
			outputCheckLog(getLoginInfo(request).getTantoCd(), "delete",
				"削除処理　終了");
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
				orderDetailLogic.outPutErrorLog(e.getModuleCd(), e
						.getInsideErrCd(), e.getInsideErrMsg(), loginUserId);
			}
			// 2015/4/20 受注混雑調査の為ログ追加
			outputCheckLog(getLoginInfo(request).getTantoCd(), "delete",
				"削除処理　終了");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "delete", "削除処理　終了");

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
	 * 納入先オートコンプリート後処理　20200709
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
		
		OrderDetailForm frm = (OrderDetailForm) form;
		
		//登録済み項目の初期化
		frm.setPrintSummery(null);
		frm.setDeliverySlipSummery(null);
		frm.setDeliverySlipSummeryDisp(null);
		frm.setOrderSummery(null);
		frm.setUploadFile(null);
		
		Delivery deliveryRelatedData = deliveryDao.getEntity(frm.getDeliveryCd());
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
		//営業担当者コード
		frm.setEigyoTantoCd(deliveryRelatedData.getTantoCd());
		//営業担当者名
		frm.setEigyoTantoName(loginDao.getEntity(deliveryRelatedData.getTantoCd()).getTantoNm());
		//納入先電話番号
		frm.setTelNo(deliveryRelatedData.getTelNo());
		//リードタイム
		frm.setStrLeadTime(deliveryRelatedData.getLeadTime().toString());
		//運送会社コード
		frm.setCarryCd(deliveryRelatedData.getCarryCd());
		//納入予定時間
		frm.setDeliveryExpectedTime(deliveryRelatedData.getDeliveryTime());
		
		if(AecNumberUtils.convertNullToZero(deliveryRelatedData.getFareClaimExistence()).equals(BigDecimal.ONE)){
			frm.setCarryInvoiceFlag(Boolean.TRUE);
		}else{
			frm.setCarryInvoiceFlag(Boolean.FALSE);

		}


		ActionMessages messages = new ActionMessages();
		// Stringの数値を数値へ変換
		setStrToNumber(frm);
		String tmpStrDate = null;
		//出荷予定日が入力されていない状態の時は休日考慮して受注日から計算する
		if(StringUtils.isEmpty(frm.getStrScheduledShippingDate())){
			//出荷予定日を計算してセットする
			tmpStrDate = calcDateFromCalendar(frm.getStrOrderDate(), null, null, messages, REPLACEMENT_NUMBER.SCHEDULED_SHIPPING_DATE);
			frm.setStrScheduledShippingDate(tmpStrDate);
		}

		//納入予定日を計算してセットする
		tmpStrDate = null;
		tmpStrDate = calcDateFromCalendar(frm.getStrScheduledShippingDate(), frm.getLeadTime(), null, messages, REPLACEMENT_NUMBER.DELIVERY_EXPECTED_DATE);
		frm.setStrDeliveryExpectedDate(tmpStrDate);

		// StrDateをDateへ
		setStrToDate(frm);
	
		List<OrderDetailList> detailList = frm.getOrderDetailList();
		if (frm.getDelFormList() == null) {
			frm.setDelFormList(new ArrayList<OrderDetailList>());
		}

		for (int i = detailList.size() - 1; i >= 0; i--) {
			OrderDetailList bean = detailList.get(i);
			// 削除したものを保持しておく
			frm.getDelFormList().add(bean);
			detailList.remove(i);
		}

		/* すべて削除したら空白行を追記 */
		if (detailList.size() == 0) {
			OrderDetailList bean = new OrderDetailList();
			bean.setTmpUnitpriceFlg(new BigDecimal(0));
			detailList.add(bean);

			List<OrderDetailVenderList> venderList = new ArrayList<OrderDetailVenderList>();
			frm.setOrderDetailVenderList(venderList);

			frm.setOrganizationCd(null);
			frm.setOrganizationName(null);
			frm.setBalanceCd(null);

		}

		for (int i = 0; i < detailList.size(); i++) {
			OrderDetailList detailBean = detailList.get(i);
			detailBean.setCheckline(Boolean.FALSE);
		}

		frm.setOrderDetailListCount(frm.getOrderDetailList().size());

		// 各行の合計を再計算
		calcOrderAmout(frm);
		// 品目リスト合計計算
		calcSumOrderAmount(frm);

		// 数値項目をStringに変換して設定
		setNumbersToStr(frm);
		// Dateをstrへ
		setDateToStr(frm);

		orderDetailLogic.checkCarry(frm.getCarryCd(), messages);

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
		}


		
		frm.setCursor("deliveryCd");
		
		return mapping.findForward("success");
	
	}

	/**
	 * 納入先選択後処理(納入予定日計算・受注詳細リスト一括クリア処理)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward afterInputDeliveryCd(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("afterInputDeliveryCd.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "afterInputDeliveryCd",
			"納入先選択後処理　開始");

		// リストを取得
		OrderDetailForm frm = (OrderDetailForm) form;

		// Stringの数値を数値へ変換
		setStrToNumber(frm);

		ActionMessages messages = new ActionMessages();
		String tmpStrDate = null;
		//出荷予定日が入力されていない状態の時は休日考慮して受注日から計算する
		if(StringUtils.isEmpty(frm.getStrScheduledShippingDate())){
			//出荷予定日を計算してセットする
			tmpStrDate = calcDateFromCalendar(frm.getStrOrderDate(), null, null, messages, REPLACEMENT_NUMBER.SCHEDULED_SHIPPING_DATE);
			frm.setStrScheduledShippingDate(tmpStrDate);
		}

		//納入予定日を計算してセットする
		tmpStrDate = null;
		tmpStrDate = calcDateFromCalendar(frm.getStrScheduledShippingDate(), frm.getLeadTime(), null, messages, REPLACEMENT_NUMBER.DELIVERY_EXPECTED_DATE);
		frm.setStrDeliveryExpectedDate(tmpStrDate);

		// StrDateをDateへ
		setStrToDate(frm);

		List<OrderDetailList> detailList = frm.getOrderDetailList();
		if (frm.getDelFormList() == null) {
			frm.setDelFormList(new ArrayList<OrderDetailList>());
		}

		for (int i = detailList.size() - 1; i >= 0; i--) {
			OrderDetailList bean = detailList.get(i);
			// 削除したものを保持しておく
			frm.getDelFormList().add(bean);
			detailList.remove(i);
		}

		/* すべて削除したら空白行を追記 */
		if (detailList.size() == 0) {
			OrderDetailList bean = new OrderDetailList();
			bean.setTmpUnitpriceFlg(new BigDecimal(0));
			detailList.add(bean);

			List<OrderDetailVenderList> venderList = new ArrayList<OrderDetailVenderList>();
			frm.setOrderDetailVenderList(venderList);

			frm.setOrganizationCd(null);
			frm.setOrganizationName(null);
			frm.setBalanceCd(null);

		}

		for (int i = 0; i < detailList.size(); i++) {
			OrderDetailList detailBean = detailList.get(i);
			detailBean.setCheckline(Boolean.FALSE);
		}

		frm.setOrderDetailListCount(frm.getOrderDetailList().size());

		// 各行の合計を再計算
		calcOrderAmout(frm);
		// 品目リスト合計計算
		calcSumOrderAmount(frm);

		// 数値項目をStringに変換して設定
		setNumbersToStr(frm);
		// Dateをstrへ
		setDateToStr(frm);

		orderDetailLogic.checkCarry(frm.getTempCarryCd(), messages);

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "afterInputDeliveryCd",
			"納入先選択後処理　終了");

		// 受注の備考（印字用)は納入先決定のタイミングで取得していたが、品目決定のタイミングで取得する様処理を修正 2016/9/15->
		// OrderRemarkList remark = orderDetailLogic.getRemarkDeliveryOnly(frm);
		// if (remark != null) {
		// frm.setDeliverySlipSummery(remark.getRemark16());
		// frm.setDeliverySlipSummeryDisp(AecStringUtils.split(frm
		// .getDeliverySlipSummery(), REMARK_LENGTH));
		// }
		// 受注の備考（印字用)は納入先決定のタイミングで取得していたが、品目決定のタイミングで取得する様処理を修正 2016/9/15<-

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
		outputCheckLog(getLoginInfo(request).getTantoCd(), "afterChangeOrderDate",
			"受注日変更時処理　開始");

		OrderDetailForm frm = (OrderDetailForm) form;

		// Stringの数値を数値へ変換
		setStrToNumber(frm);

		ActionMessages messages = new ActionMessages();
		String tmpStrDate = null;

		//出荷予定日を計算してセットする
		tmpStrDate = calcDateFromCalendar(frm.getStrOrderDate(), null, null, messages, REPLACEMENT_NUMBER.SCHEDULED_SHIPPING_DATE);
		frm.setStrScheduledShippingDate(tmpStrDate);

		//リードタイムがnullでない場合は納入予定日を更新する
		if(frm.getLeadTime() != null){
			tmpStrDate = null;
			//納入予定日を計算してセットする
			tmpStrDate = calcDateFromCalendar(frm.getStrScheduledShippingDate(), frm.getLeadTime(), null, messages, REPLACEMENT_NUMBER.DELIVERY_EXPECTED_DATE);
			frm.setStrDeliveryExpectedDate(tmpStrDate);
		}

		// StrDateをDateへ
		setStrToDate(frm);

		// 出荷予定日付が入っているかつ帳合コードが入っている場合のみ有効単価取得処理
		if (frm.getScheduledShippingDate() != null && StringUtils.isNotEmpty(frm.getBalanceCd())) {
			getunitpriceall(frm);
		}

		if (!messages.isEmpty()) {
			//エラーがある場合はここで登録
			saveErrors(request, messages);
		}

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "afterChangeOrderDate",
			"受注日変更時処理　終了");
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

		OrderDetailForm frm = (OrderDetailForm) form;

		// Stringの数値を数値へ変換
		setStrToNumber(frm);

		ActionMessages messages = new ActionMessages();
		String tmpStrDate = null;
		//リードタイムがnullでない場合は納入予定日を更新する
		if(frm.getLeadTime() != null){
			//納入予定日を計算してセットする
			tmpStrDate = calcDateFromCalendar(frm.getStrScheduledShippingDate(), frm.getLeadTime(), null, messages, REPLACEMENT_NUMBER.DELIVERY_EXPECTED_DATE);
			frm.setStrDeliveryExpectedDate(tmpStrDate);
		}

		// StrDateをDateへ
		setStrToDate(frm);

		// 出荷予定日付が入っているかつ帳合コードが入っている場合のみ有効単価取得処理
		if (frm.getScheduledShippingDate() != null && StringUtils.isNotEmpty(frm.getBalanceCd())) {
			getunitpriceall(frm);
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
	 * 全ての有効単価取得処理
	 * @param frm OrderDetailForm
	 */
	private void getunitpriceall(OrderDetailForm frm)throws Exception{
		// 全ての有効単価取得処理
		for (OrderDetailList detailListBean : frm.getOrderDetailList()) {

			// 品目コードが存在する行のみ有効単価取得処理
			if (detailListBean.getItemCd() != null
					&& !detailListBean.getItemCd().equals("")) {

				/* 有効単価取得 */
				VaridUnitprice bean = getValidUnitpriceLogic
						.getValidUnitprice(frm.getBalanceCd(),
							detailListBean.getItemCd(), AecDateUtils
									.dateFormat(frm.getScheduledShippingDate(),
										"yyyy/MM/dd"));

				if (bean == null || bean.getTmpUnitpriceFlg().equals("1")) { /* 有効な単価が無い場合 */

					/* 単価、値引き、増付数を０ */
					detailListBean.setOrderUnitprice(new BigDecimal(0));
					if (bean == null) {
						detailListBean.setStandardUnitprice(new BigDecimal(
								0));

					} else {
						detailListBean.setStandardUnitprice(bean
								.getStandardUnitPrice());

					}
					detailListBean.setStandardDiscount(new BigDecimal(0));
					detailListBean.setSpecialDiscount(new BigDecimal(0));
					// 増付数
					detailListBean.setEstimateMatss(new BigDecimal(0));

					// 基準数量
					detailListBean
							.setEstimateStandardAmount(new BigDecimal(0));

					// 全件実行の場合増しつけ数を変更しない
					detailListBean
							.setMatss(AecNumberUtils
									.convertBigDecimal(detailListBean
											.getStrMatss()));

					/* 仮単価フラグON */
					detailListBean.setTmpUnitpriceFlg(new BigDecimal(1));

				} else { /* 有効な単価が在る場合 */
					// 更新日時を退避
					Timestamp updateDate = detailListBean.getUpdateDate();

					/* 単価データのコピー処理 */
					detailListBean.setOrderUnitprice(bean.getUnitprice());
					IgnoreCaseBeanUtils
							.copyProperties(detailListBean, bean);
					// 退避した更新日時を設定
					detailListBean.setUpdateDate(updateDate);

					// 増付数
					detailListBean.setEstimateMatss(bean.getMatss());

					// 基準数量
					detailListBean.setEstimateStandardAmount(bean
							.getStandardAmount());

					// 全件実行の場合増しつけ数を変更しない
					detailListBean
							.setMatss(AecNumberUtils
									.convertBigDecimal(detailListBean
											.getStrMatss()));

					/* 仮単価フラグOFF */
					detailListBean.setTmpUnitpriceFlg(new BigDecimal(0));
				}

				// 各行の合計を再計算
				calcOrderAmout(frm);

			}

		}
		// 品目リスト合計計算
		calcSumOrderAmount(frm);

		// 数値項目をStringに変換して設定
		setNumbersToStr(frm);
		// Dateをstrへ
		setDateToStr(frm);
	}

	/**
	 * 単価取得処理(品目選択時処理)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward getunitprice(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("getunitprice.");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "getunitprice",
			"単価取得処理処理　開始");

		OrderDetailForm frm = (OrderDetailForm) form;
		int procRow = frm.getVaridUnitpriceRow();

		// Stringの数値を数値へ変換
		setStrToNumber(frm);
		// StrDateをDateへ
		setStrToDate(frm);

		/* 有効単価取得 */
		VaridUnitprice bean = getValidUnitpriceLogic.getValidUnitprice(frm
				.getBalanceCd(), frm.getOrderDetailList().get(procRow)
				.getItemCd(), AecDateUtils.dateFormat(frm.getScheduledShippingDate(),
			"yyyy/MM/dd"));

		if (bean == null || bean.getTmpUnitpriceFlg().equals("1")) { /* 有効な単価が無い場合 */

			/* 単価、値引き、増付数を０ */
			frm.getOrderDetailList().get(procRow).setOrderUnitprice(
				new BigDecimal(0));
			if (bean == null) {
				frm.getOrderDetailList().get(procRow).setStandardUnitprice(
					new BigDecimal(0));

			} else {
				frm.getOrderDetailList().get(procRow).setStandardUnitprice(
					bean.getStandardUnitPrice());

			}
			frm.getOrderDetailList().get(procRow).setStandardDiscount(
				new BigDecimal(0));
			frm.getOrderDetailList().get(procRow).setSpecialDiscount(
				new BigDecimal(0));
			frm.getOrderDetailList().get(procRow).setMatss(new BigDecimal(0));
			// 増付数
			frm.getOrderDetailList().get(procRow).setEstimateMatss(
				new BigDecimal(0));

			// 基準数量
			frm.getOrderDetailList().get(procRow).setEstimateStandardAmount(
				new BigDecimal(0));

			/* 仮単価フラグON */
			frm.getOrderDetailList().get(procRow).setTmpUnitpriceFlg(
				new BigDecimal(1));

		} else { /* 有効な単価が在る場合 */
			// 更新日時を退避
			Timestamp updateDate = frm.getOrderDetailList().get(procRow)
					.getUpdateDate();
			/* 単価データのコピー処理 */
			frm.getOrderDetailList().get(procRow).setOrderUnitprice(
				bean.getUnitprice());
			IgnoreCaseBeanUtils.copyProperties(frm.getOrderDetailList().get(
				procRow), bean);
			// 退避した更新日時を設定
			frm.getOrderDetailList().get(procRow).setUpdateDate(updateDate);

			// 増付数
			frm.getOrderDetailList().get(procRow).setEstimateMatss(
				bean.getMatss());

			// 基準数量
			frm.getOrderDetailList().get(procRow).setEstimateStandardAmount(
				bean.getStandardAmount());

			/* 仮単価フラグOFF */
			frm.getOrderDetailList().get(procRow).setTmpUnitpriceFlg(
				new BigDecimal(0));
		}

		// 各行の合計を再計算
		calcOrderAmout(frm);

		// 詳細List件数を設定
		frm.setOrderDetailListCount(frm.getOrderDetailList().size());

		// 得意先リスト取得
		getVenderList(frm);

		// 担当部署取得設定
		getVenderDetail(frm);

		// 品目リスト合計計算
		calcSumOrderAmount(frm);

		// 数値項目をStringに変換して設定
		setNumbersToStr(frm);
		// Dateをstrへ
		setDateToStr(frm);

		// 2016/9/15 品目決定時の備考取得処理を追加 ->
		List<OrderRemarkList> remarkList = null;
		try {
			// 備考の検索
			remarkList = orderDetailLogic.getRemarkList(frm);
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

			if (remarkList.get(i).getRemark16() != null) {
				if (sbfRemark.length() != 0) {
					sbfRemark.append(" ");
				}
				sbfRemark.append(remarkList.get(i).getRemark16());
			}
		}
		// 画面表示項目にセット
		frm.setDeliverySlipSummery(sbfRemark.toString());
		frm.setDeliverySlipSummeryDisp(AecStringUtils.split(frm
				.getDeliverySlipSummery(), REMARK_LENGTH));
		// 2016/9/15 品目決定時の備考取得処理を追加 <-

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "getunitprice",
			"単価取得処理処理　終了");

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
		OrderDetailForm frm = (OrderDetailForm) form;

		List<OrderRemarkList> remarkList = null;

		// Stringの数値を数値へ変換
		setStrToNumber(frm);

		// 各行の合計を再計算
		calcOrderAmout(frm);

		// 品目リスト合計計算
		calcSumOrderAmount(frm);

		// 数値項目をStringに変換して設定
		setNumbersToStr(frm);

		try {
			// 備考の検索
			remarkList = orderDetailLogic.getRemarkList(frm);
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
		outputCheckLog(getLoginInfo(request).getTantoCd(), "addlist",
			"行追加処理　開始");

		// リストを取得
		OrderDetailForm frm = (OrderDetailForm) form;
		List<OrderDetailList> detailList = frm.getOrderDetailList();

		// Stringの数値を数値へ変換
		setStrToNumber(frm);

		// 追加用データ
		OrderDetailList bean = new OrderDetailList();
		bean.setTmpUnitpriceFlg(new BigDecimal(0));
		// 新しい要素を追加
		detailList.add(0, bean);

		for (int i = 0; i < detailList.size(); i++) {
			OrderDetailList detailBean = detailList.get(i);
			detailBean.setCheckline(Boolean.FALSE);
		}

		frm.setOrderDetailListCount(detailList.size());

		// 各行の合計を再計算
		calcOrderAmout(frm);
		// 品目リスト合計計算
		calcSumOrderAmount(frm);

		// 数値項目をStringに変換して設定
		setNumbersToStr(frm);

		frm.setCursor("1");
		frm.setCursorIndex("0");
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "addlist",
			"行追加処理　終了");

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
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "dellist",
			"行追加処理　開始");

		// リストを取得
		OrderDetailForm frm = (OrderDetailForm) form;
		List<OrderDetailList> detailList = frm.getOrderDetailList();
		if (frm.getDelFormList() == null) {
			frm.setDelFormList(new ArrayList<OrderDetailList>());
		}

		// Stringの数値を数値へ変換
		setStrToNumber(frm);

		for (int i = detailList.size() - 1; i >= 0; i--) {
			OrderDetailList bean = detailList.get(i);

			if (!bean.getCheckline()) {
				// チェック無は読み飛ばし
				continue;
			}
			// 削除したものを保持しておく
			frm.getDelFormList().add(bean);
			detailList.remove(i);
		}

		/* すべて削除したら空白行を追記 */
		if (detailList.size() == 0) {
			OrderDetailList bean = new OrderDetailList();
			bean.setTmpUnitpriceFlg(new BigDecimal(0));
			detailList.add(bean);

			List<OrderDetailVenderList> venderList = new ArrayList<OrderDetailVenderList>();
			frm.setOrderDetailVenderList(venderList);

			frm.setOrganizationCd(null);
			frm.setOrganizationName(null);
			frm.setBalanceCd(null);

		}

		for (int i = 0; i < detailList.size(); i++) {
			OrderDetailList detailBean = detailList.get(i);
			detailBean.setCheckline(Boolean.FALSE);
		}

		frm.setOrderDetailListCount(frm.getOrderDetailList().size());

		// 各行の合計を再計算
		calcOrderAmout(frm);
		// 品目リスト合計計算
		calcSumOrderAmount(frm);

		// 数値項目をStringに変換して設定
		setNumbersToStr(frm);

		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "dellist",
			"行追加処理　終了");

		return mapping.findForward("success");
	}

	/**
	 * ダウンロード
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward filedownload(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("filedownload");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "filedownload",
			"ファイルダウンロード処理　開始");

		OrderDetailForm frm = (OrderDetailForm) form;

		// ダウンロードパス取得
		String path = orderDetailLogic.getDownloadPath();

		// クライアント側の文字エンコーディングのままのファイル名
		String fileName = path + frm.getOrderPicture();
		File downloadFile = new File(fileName);

		if ((frm.getOrderPicture() != null)
				&& !(frm.getOrderPicture().equals("")) && downloadFile.exists()) {
			FileDownloadInfo info = FileDownloadInfo.createDownloadInfo(
				fileName, frm.getOrderPicture().substring(
					MgrecipeConst.START_FILE_NAME));

			/* Dispositionは常にattachmentを設定 */
			info.setDisposition(FileDownloadInfo.DISPOSITION_ATTACHMENT);

			/* sessionにくっつける */
			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);

			/* ダウンロードフラグを立てる */
			frm.setDownloadFlg(true);
		} else {
			/* メッセージ */
			saveError(request, "errors.file");
		}
		// 2015/4/20 受注混雑調査の為ログ追加
		outputCheckLog(getLoginInfo(request).getTantoCd(), "filedownload",
			"ファイルダウンロード処理　終了");

		return mapping.findForward("success");
	}

	/**
	 * 品目表に行削除
	 * @param frm OrderDetailForm
	 * @param i 削除するリストインデックス
	 */
	public void delOrderDetailList(final OrderDetailForm frm, final int i) {
		List<OrderDetailList> list = frm.getOrderDetailList();
		list.remove(i);
		frm.setOrderDetailList(list);
	}

	/**
	 * 得意先情報取得処理
	 * @param frm 受注詳細画面FORM
	 * @return OrderDetailForm
	 * @throws NoDataException データが存在しない例外
	 */
	private OrderDetailForm getVenderList(final OrderDetailForm frm)
			throws NoDataException {
		// 帳合コードより得意先一覧を取得
		if (StringUtils.isNotEmpty(frm.getBalanceCd())) {
			List<OrderDetailVenderList> list = orderDetailLogic
					.getVenderList(frm.getBalanceCd());
			frm.setOrderDetailVenderList(list);
		}
		return frm;
	}

	/**
	 * 取引先詳細情報取得処理
	 * @param frm 受注詳細画面FORM
	 * @return OrderDetailForm
	 * @throws NoDataException データが存在しない例外
	 */
	private OrderDetailForm getVenderDetail(final OrderDetailForm frm)
			throws NoDataException {

		String venderCd = new String();
		if (!frm.getOrderDetailVenderList().isEmpty()) {
			venderCd = frm.getOrderDetailVenderList().get(0).getVenderCd();
		}

		if (StringUtils.isNotEmpty(venderCd)) {
			OrderDetailVenderDetail entity = orderDetailLogic
					.getVenderDetail(venderCd);

			frm.setOrganizationCd(entity.getOrganizationCd());
			frm.setOrganizationName(entity.getOrganizationName());
		}
		return frm;
	}

	/**
	 * 数量等 To String 設定処理
	 * @param OrderDetailForm
	 * @return OrderDetailForm
	 */
	private OrderDetailForm setNumbersToStr(final OrderDetailForm frm)
			throws NoDataException {
		List<OrderDetailList> detailList = frm.getOrderDetailList();

		// 品目行に得意先を設定 数値チェック用
		String venderCd = null;
		List<OrderDetailVenderList> venderList = frm.getOrderDetailVenderList();
		if (venderList != null && venderList.size() != 0) {
			venderCd = venderList.get(0).getVenderCd();
		}

		// 単価用
		NumberChkDisitDetail uritanka = checkDigitUtilsLogic.getCheckDigit(
			UNIT_DIVISION_URTANKA, VENDER_DIVISION, frm.getVenderCd());
		// 小数点桁数
		if (uritanka.getSmallnumLength() != null) {
			frm.setDecimalPointUrTanka(uritanka.getSmallnumLength().toString());
		}

		for (OrderDetailList bean : detailList) {
			String unitDiv = bean.getUnitOfOperationManagement();

			if (bean.getItemCd() != null && !bean.getItemCd().equals("")) {

				// 受注数量
				if (bean.getOrderQty() != null) {
					bean.setStrOrderQty(checkDigitUtilsLogic.format(unitDiv,
						bean.getOrderQty()));
				}

				// 受注単価
				if (bean.getOrderUnitprice() != null) {
					bean.setStrOrderUnitprice(checkDigitUtilsLogic.format(
						UNIT_DIVISION_URTANKA, VENDER_DIVISION, venderCd, bean
								.getOrderUnitprice()));
				}

				// 標準単価
				if (bean.getStandardUnitprice() != null) {
					// bean.setStrStandardUnitprice(checkDigitUtilsLogic.format(
					// unitDiv, bean.getStandardUnitprice()));
					bean.setStrStandardUnitprice(checkDigitUtilsLogic.format(
						UNIT_DIVISION_URTANKA, VENDER_DIVISION, venderCd, bean
								.getStandardUnitprice()));
				}
				// 標準値引
				if (bean.getStandardDiscount() != null) {
					bean.setStrStandardDiscount(checkDigitUtilsLogic.format(
						UNIT_DIVISION_URTANKA, VENDER_DIVISION, venderCd, bean
								.getStandardDiscount()));
				}
				// 特別値引
				if (bean.getSpecialDiscount() != null) {
					bean.setStrSpecialDiscount(checkDigitUtilsLogic.format(
						UNIT_DIVISION_URTANKA, VENDER_DIVISION, venderCd,

						bean.getSpecialDiscount()));
				}
				// 金額
				if (bean.getOrderAmount() != null) {
					bean.setStrOrderAmount(checkDigitUtilsLogic.format(
						"URKINGAKU", VENDER_DIVISION, venderCd, bean
								.getOrderAmount()));
				}
				// 増付数
				if (bean.getMatss() != null) {
					bean.setStrMatss(checkDigitUtilsLogic.format(unitDiv, bean
							.getMatss()));
				}

				// 見積増付数
				if (bean.getEstimateMatss() != null) {
					bean.setStrEstimateMatss(checkDigitUtilsLogic.format(
						unitDiv, bean.getEstimateMatss()));
				}

				// 見積基準数量
				if (bean.getEstimateStandardAmount() != null) {
					bean.setStrEstimateStandardAmount(checkDigitUtilsLogic
							.format(unitDiv, bean.getEstimateStandardAmount()));
				}

				// 小数点桁数、端数区分取得、セット
				NumberChkDisitDetail checkDetail = checkDigitUtilsLogic
						.getCheckDigit(unitDiv, VENDER_DIVISION, venderCd);

				// 小数点桁数
				if (checkDetail.getSmallnumLength() != null) {
					bean.setItemSmallnumLength(checkDetail.getSmallnumLength()
							.toString());
				}
				// 端数区分
				if (checkDetail.getRoundDivision() != null) {
					bean.setItemRoundDivision(checkDetail.getRoundDivision()
							.toString());
				}
			}
			// 品目行に得意先を設定 数値チェック用
			bean.setVenderCd(venderCd);
		}

		// 合計金額
		if (frm.getOrderAmount() != null) {
			frm.setStrSumOrderAmount(checkDigitUtilsLogic.format("URKINGAKU",
				frm.getOrderAmount()));
		}

		// リードタイム
		if (frm.getLeadTime() != null) {
			frm.setStrLeadTime(checkDigitUtilsLogic.format("SONOTA", frm
					.getLeadTime()));
		}

		// 運賃
		if (frm.getCarryFare() != null) {
			frm.setStrCarryFare(checkDigitUtilsLogic.format("UNTIN", frm
					.getCarryFare()));
		}

		// 運賃
		NumberChkDisitDetail detail = checkDigitUtilsLogic.getCheckDigit(
			"UNTIN", VENDER_DIVISION, venderCd);
		// 単位区分(運賃)
		frm.setUnitDivisionUntin("UNTIN");
		// 小数点桁数(運賃)
		if (detail.getSmallnumLength() != null) {
			frm.setDecimalPointUntin(detail.getSmallnumLength().toString());
		}
		// 端数区分(運賃)
		if (detail.getRoundDivision() != null) {
			frm.setRoundUntin(detail.getRoundDivision().toString());
		}

		// 金額用
		detail = checkDigitUtilsLogic.getCheckDigit("URKINGAKU",
			VENDER_DIVISION, venderCd);
		// 小数点桁数
		if (detail.getSmallnumLength() != null) {
			frm.setDecimalPointUrKingaku(detail.getSmallnumLength().toString());
		}
		// 端数区分
		if (detail.getRoundDivision() != null) {
			frm.setRoundUrKingaku(detail.getRoundDivision().toString());
		}

		// リードタイム用
		detail = checkDigitUtilsLogic.getCheckDigit("SONOTA", VENDER_DIVISION,
			venderCd);
		// 小数点桁数
		if (detail.getSmallnumLength() != null) {
			frm.setDecimalPointSonota(detail.getSmallnumLength().toString());
		}
		// 端数区分
		if (detail.getRoundDivision() != null) {
			frm.setRoundSonota(detail.getRoundDivision().toString());
		}

		// venderCd設定 数値チェック
		frm.setVenderCd(venderCd);

		frm.setOrderDetailList(detailList);

		return frm;
	}

	/**
	 * Stringの数値をBigDecimalに 設定処理
	 * @param OrderDetailForm
	 * @return OrderDetailForm
	 */
	private OrderDetailForm setStrToNumber(final OrderDetailForm frm)
			throws NoDataException {
		List<OrderDetailList> detailList = frm.getOrderDetailList();

		for (OrderDetailList bean : detailList) {
			// 受注数量
			if (bean.getStrOrderQty() != null
					&& !bean.getStrOrderQty().equals("")) {
				bean.setOrderQty(AecNumberUtils.convertBigDecimal(bean
						.getStrOrderQty()));
			} else {
				bean.setOrderQty(BigDecimal.ZERO);
			}

			// 売上単価
			if (bean.getStrOrderUnitprice() != null
					&& !bean.getStrOrderUnitprice().equals("")) {
				bean.setOrderUnitprice(AecNumberUtils.convertBigDecimal(bean
						.getStrOrderUnitprice()));
			} else {
				bean.setOrderUnitprice(BigDecimal.ZERO);
			}

			// 標準単価
			if (bean.getStrStandardUnitprice() != null
					&& !bean.getStrStandardUnitprice().equals("")) {
				bean.setStandardUnitprice(AecNumberUtils.convertBigDecimal(bean
						.getStrStandardUnitprice()));
			} else {
				bean.setStandardUnitprice(BigDecimal.ZERO);
			}
			// 標準値引
			if (bean.getStrStandardDiscount() != null
					&& !bean.getStrStandardDiscount().equals("")) {
				bean.setStandardDiscount(AecNumberUtils.convertBigDecimal(bean
						.getStrStandardDiscount()));
			} else {
				bean.setStandardDiscount(BigDecimal.ZERO);
			}
			// 特別値引
			if (bean.getStrSpecialDiscount() != null
					&& !bean.getStrSpecialDiscount().equals("")) {
				bean.setSpecialDiscount(AecNumberUtils.convertBigDecimal(bean
						.getStrSpecialDiscount()));
			} else {
				bean.setSpecialDiscount(BigDecimal.ZERO);
			}
			// 金額
			if (bean.getStrOrderAmount() != null
					&& !bean.getStrOrderAmount().equals("")) {
				bean.setOrderAmount(AecNumberUtils.convertBigDecimal(bean
						.getStrOrderAmount()));
			} else {
				bean.setOrderAmount(BigDecimal.ZERO);
			}
			// 増付数
			if (bean.getStrMatss() != null && !bean.getStrMatss().equals("")) {
				bean.setMatss(AecNumberUtils.convertBigDecimal(bean
						.getStrMatss()));
			} else {
				bean.setMatss(BigDecimal.ZERO);
			}
		}

		// リードタイム
		if (frm.getStrLeadTime() != null && !frm.getStrLeadTime().equals("")) {
			frm.setLeadTime(AecNumberUtils.convertBigDecimal(frm
					.getStrLeadTime()));
		} else {
			frm.setLeadTime(BigDecimal.ZERO);
		}

		// 運賃
		if (frm.getStrCarryFare() != null && !frm.getStrCarryFare().equals("")) {
			frm.setCarryFare(AecNumberUtils.convertBigDecimal(frm
					.getStrCarryFare()));
		} else {
			frm.setCarryFare(BigDecimal.ZERO);
		}

		if (frm.getStrSumOrderAmount() != null
				&& !frm.getStrSumOrderAmount().equals("")) {
			frm.setOrderAmount(AecNumberUtils.convertBigDecimal(frm
					.getStrSumOrderAmount()));
		} else {
			frm.setOrderAmount(BigDecimal.ZERO);
		}

		frm.setOrderDetailList(detailList);

		return frm;
	}

	/**
	 * DateをStringに変換設定
	 * @param OrderDetailForm
	 */
	private void setDateToStr(final OrderDetailForm frm) throws NoDataException {

		frm.setStrOrderDate(AecDateUtils.dateFormat(frm.getOrderDate(),
			"yyyy/MM/dd")); // 受注日
		frm.setStrSuggestedDeliverlimit(AecDateUtils.dateFormat(frm
				.getSuggestedDeliverlimit(), "yyyy/MM/dd")); // 希望納期
		frm.setStrDeliveryExpectedDate(AecDateUtils.dateFormat(frm
				.getDeliveryExpectedDate(), "yyyy/MM/dd")); // 納入予定日
		frm.setStrScheduledShippingDate(AecDateUtils.dateFormat(frm
				.getScheduledShippingDate(), "yyyy/MM/dd")); // 出荷予定日

	}

	/**
	 * DateをStringに変換設定
	 * @param OrderDetailForm
	 */
	private boolean checkDate(final OrderDetailForm frm,
			final HttpServletRequest request) throws NoDataException {

		boolean ret = true;
		ActionMessages messages = new ActionMessages();

		// 今を取得
		String now = AecDateUtils.dateFormat(
			AecDateUtils.getCurrentTimestamp(), "yyyy/MM/dd");

		// 受注日
		if (now.compareTo(frm.getStrOrderDate()) > 0) {
			ret = false;
			messages.add("", new ActionMessage("errors.order.order.date"));
		}

		// 出荷予定日
		if (now.compareTo(frm.getStrScheduledShippingDate()) > 0) {
			ret = false;
			messages.add("", new ActionMessage(
					"errors.order.scheduled.shipping.date"));
		}
		// 納入予定日
		if (now.compareTo(frm.getStrDeliveryExpectedDate()) > 0) {
			ret = false;
			messages.add("", new ActionMessage(
					"errors.order.delivery.expected.date"));
		}

		if (!ret) {
			saveErrors(request, messages);
		}
		return ret;
	}

	/**
	 * DateをStringに変換設定
	 * @param OrderDetailForm
	 */
	private void setStrToDate(final OrderDetailForm frm) throws NoDataException {
		// 受注日
		frm.setOrderDate(AecDateUtils.getTimestampYmdHmFormat(frm
				.getStrOrderDate(), "yyyy/MM/dd"));
		// 希望納期
		frm.setSuggestedDeliverlimit(AecDateUtils.getTimestampYmdHmFormat(frm
				.getStrSuggestedDeliverlimit(), "yyyy/MM/dd"));
		// 納入予定日
		frm.setDeliveryExpectedDate(AecDateUtils.getTimestampYmdHmFormat(frm
				.getStrDeliveryExpectedDate(), "yyyy/MM/dd"));
		// 出荷予定日
		frm.setScheduledShippingDate(AecDateUtils.getTimestampYmdHmFormat(frm
				.getStrScheduledShippingDate(), "yyyy/MM/dd"));

	}

	/**
	 * 品目リスト各行の金額を計算
	 * @param OrderDetailForm
	 * @return OrderDetailForm
	 */
	private OrderDetailForm calcOrderAmout(final OrderDetailForm frm)
			throws NoDataException {
		List<OrderDetailList> detailList = frm.getOrderDetailList();

		for (OrderDetailList bean : detailList) {

			BigDecimal sumAmount = BigDecimal.ZERO;

			// 単価と数量が存在する場合は金額計算それ以外は金額０
			if (bean.getOrderUnitprice() != null && bean.getOrderQty() != null) {
				sumAmount = bean.getOrderUnitprice().multiply(
					bean.getOrderQty());
			}

			/* 金額 */
			bean.setOrderAmount(sumAmount);
		}

		frm.setOrderDetailList(detailList);
		return frm;
	}

	/**
	 * 品目リスト全体の金額合計を計算、設定
	 * @param OrderDetailForm
	 * @return OrderDetailForm
	 */
	private OrderDetailForm calcSumOrderAmount(final OrderDetailForm frm)
			throws NoDataException {

		BigDecimal sumAllAmount = BigDecimal.ZERO;

		List<OrderDetailList> detailList = frm.getOrderDetailList();

		for (OrderDetailList bean : detailList) {

			// 単価と数量が存在する場合計算
			if (bean.getOrderUnitprice() != null && bean.getOrderQty() != null) {
				BigDecimal sumAmount = bean.getOrderUnitprice().multiply(
					bean.getOrderQty());
				sumAllAmount = sumAllAmount.add(sumAmount);
			}

		}

		String strOrderAmount = "";

		strOrderAmount = checkDigitUtilsLogic.format("URKINGAKU",
			VENDER_DIVISION, frm.getVenderCd(), sumAllAmount);

		frm.setOrderAmount(AecNumberUtils.convertBigDecimal(strOrderAmount));

		// frm.setStrSumOrderAmount(strOrderAmount);

		return frm;
	}

	/**
	 * 数量チェック
	 * @param frm チェック対象フォーム
	 * @param request HttpServletRequest
	 * @return boolean OK：true NG:false
	 */
	private boolean isOrderQuantityCheck(final OrderDetailForm frm,
			final HttpServletRequest request) {
		ActionMessages messages = new ActionMessages();
		int index = 1;

		//
		if (!frm.getOrderDivision().equals("1")
				&& !frm.getOrderDivision().equals("4")) {
			return true;
		}

		for (OrderDetailList bean : frm.getOrderDetailList()) {
			String itemCd = bean.getItemCd();

			try {
				ItemInventory inventEntity = orderDetailLogic
						.getItemInventoryEntity(itemCd);

				double inventoryQty = inventEntity.getInventoryQty()
						.doubleValue();
				double assignQty = inventEntity.getAssignQty().doubleValue();
				double salesAssignQty = inventEntity.getSalesAssignQty()
						.doubleValue();
				BigDecimal noGood = orderDetailLogic.getNoGoodQty(itemCd);

				double noGoodQty = noGood.doubleValue();

				double ownQty = getOwnQty(bean.getItemCd(), frm);

				double qty = inventoryQty + assignQty + salesAssignQty
						- noGoodQty + ownQty;

				// 数量＋増付数が在庫よりも少ない場合エラー
				if (qty < (bean.getOrderQty().doubleValue() + bean.getMatss()
						.doubleValue())) {
					messages.add("", new ActionMessage(
							"errors.order.item.quantity.row", Integer
									.toString(index)));
				}
			} catch (NoDataException e) {
				messages.add("", new ActionMessage(
						"errors.order.item.quantity.row", Integer
								.toString(index)));
			}
			index++;
		}

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return false;
		}
		return true;
	}

	/**
	 * 登録時マスタチェック処理
	 * @param frm 受注詳細画面FORM
	 * @param itemCd 品目コード
	 * @return 品目数量
	 */
	private double getOwnQty(final String itemCd, final OrderDetailForm frm) {

		double ret = 0;

		// 品目ループ
		for (OrderDetailList bean : frm.getOrderDetailListOld()) {

			//
			if (bean.getItemCd().equals(itemCd)) {

				ret += bean.getOrderQty().doubleValue()
						+ bean.getMatss().doubleValue();
			}

		}
		return ret;
	}

	/**
	 * 登録時マスタチェック処理
	 * @param frm 受注詳細画面FORM
	 * @param errors ActionMessage
	 * @return boolean チェック結果 true:OK false:NG
	 */
	private boolean isCheckMaster(final OrderDetailForm frm,
			final HttpServletRequest request) {
		ActionMessages messages = new ActionMessages();

		// 帳合コード
		orderDetailLogic.checkBalance(frm, messages);

		// 取引先コード
		orderDetailLogic.checkVender(frm, messages);

		// 品目マスタ
		orderDetailLogic.checkItem(frm, messages);

		// 販売条件マスタ
		orderDetailLogic.checkSalesTerms(frm, messages);

		// 受注区分で品目チェック
		orderDetailLogic.checkItemTypeDivision(frm, messages);

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return false;
		}

		return true;
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
		 resultDto = orderDetailLogic.callProCalcDateFromCalendar(strDate, leadTime, calCd);
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

	/* -------------------- setter -------------------- */
	/**
	 * orderDetailLogicを設定します。
	 * @param orderDetailLogic orderDetailLogic
	 */
	public void setOrderDetailLogic(final OrderDetailLogic orderDetailLogic) {
		this.orderDetailLogic = orderDetailLogic;
	}

	/**
	 * getValidUnitpriceLogicを設定します。
	 * @param getValidUnitpriceLogic getValidUnitpriceLogic
	 */
	public void setGetValidUnitpriceLogic(
			final GetValidUnitpriceLogic getValidUnitpriceLogic) {
		this.getValidUnitpriceLogic = getValidUnitpriceLogic;
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
	
	/**
	 * DeliveryDaoを設定します
	 * @param deliveryDao deliveryDao
	 */
	public void setDeliveryDao(final DeliveryDao deliveryDao){
		this.deliveryDao = deliveryDao;
	}
	
	/**
	 * LoginDaoを設定します
	 * @param loginDao loginDao
	 */
	public void setLoginDao(final LoginDao loginDao){
		this.loginDao = loginDao;
	}
	
	/**
	 * BelongDaoを設定します
	 * @param belongDao belongDao
	 */
	public void setBelongDao(final BelongDao belongDao){
		this.belongDao = belongDao;
	}
}
