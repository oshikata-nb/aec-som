/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import java.math.BigDecimal;
import java.util.List;

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
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailCompanyEntity;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailCompanyList;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 出荷指図詳細(自社ロット) Actionクラス.
 * @author tosco
 */
public final class ShippingDetailCompanyAction extends AbstractAction {

	/** ステータス 新規登録 */
	private static final String SHIPPING_STATUS_NEW = "受注登録";

	/** 出荷指図共通ロジッククラス */
	private ShippingCommonsLogic shippingCommonsLogic;

	/** 出荷指図詳細(自社ロット)ロジッククラス */
	private ShippingDetailCompanyLogic shippingDetailCompanyLogic;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/** 攝津倉庫 */
	protected static final BigDecimal SOM_LOCATION = new BigDecimal(1);

	/** 外部倉庫 */
	protected static final BigDecimal OTHER_LOCATION = new BigDecimal(2);

	/**
	 * コンストラクタ.
	 */
	public ShippingDetailCompanyAction() {
		super();
	}

	/**
	 * 出荷指図検索処理(一覧画面の出荷番号(自社ロット)リンク押下時)
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

		ShippingDetailCompanyForm frm = (ShippingDetailCompanyForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_SHIPPING,
			Constants.TAB_ID_SHIPPING_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.setInsertFlg(0);

		try {
			// 初期検索
			ShippingDetailCompanyEntity bean = shippingDetailCompanyLogic
					.getEntity(frm.getShippingNo());
			// BeanをFormにコピーする
			setShippingData(frm, bean);
			frm.setShippingBean(bean);
			// 運送会社コンボボックス
			frm.setCarryCombo(shippingCommonsLogic.createCarryCombobox());

			// 出荷指図詳細情報を取得する
			List<ShippingDetailCompanyList> list = shippingDetailCompanyLogic
					.getDetailList(frm.getShippingNo(), bean.getUnitDivision(),
						ShippingConst.VENDER_DIVISION_TS, frm.getVenderCd());
			frm.setDetailList(list);
			frm.setDirtyFlg(null);

		} catch (NoDataException e) {
			// エラーメッセージ
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");

	}

	/**
	 * 新規登録画面表示処理(一覧画面の自社ロットボタン押下時)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initNew(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initNew.");
		}

		ShippingDetailCompanyForm frm = (ShippingDetailCompanyForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_SHIPPING,
			Constants.TAB_ID_SHIPPING_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 新規とする
		frm.setInsertFlg(1);

		// ステータスを"受注登録"とする
		frm.setShippingStatusName(SHIPPING_STATUS_NEW);

		// 出荷予定日のデフォルトを現在日時とする
		frm.setStrShippingInstructDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));

		// 運送会社コンボボックス
		frm.setCarryCombo(shippingCommonsLogic.createCarryCombobox());
		frm.setDirtyFlg(null);

		return mapping.findForward("success");
	}

	/**
	 * 新規登録画面表示処理(一覧画面の自社ロットボタン押下時)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initContinue(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initContinue.");
		}

		ShippingDetailCompanyForm frm = (ShippingDetailCompanyForm) form;

		// 新規とする
		frm.setInsertFlg(1);

		// ステータスを"受注登録"とする
		frm.setShippingStatusName(SHIPPING_STATUS_NEW);

		// 出荷予定日のデフォルトを現在日時とする
		frm.setStrShippingInstructDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));

		// 運送会社コンボボックス
		frm.setCarryCombo(shippingCommonsLogic.createCarryCombobox());
		frm.setDirtyFlg(null);

		return mapping.findForward("success");
	}

	/**
	 * 行追加処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward addRow(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("addRow.");
		}

		ShippingDetailCompanyForm frm = (ShippingDetailCompanyForm) form;

		List<ShippingDetailCompanyList> detailList = frm.getDetailList();
		int len = detailList.size();
		boolean addFlg = false;
		for (int i = 0; i < len; i++) {
			ShippingDetailCompanyList bean = detailList.get(i);
			if (bean.isCheckFlg()) {
				// チェックされている場合、その行の上に追加
				detailList.add(i, getNewDetailLine());
				addFlg = true;
				break;
			}
		}
		// 行が追加されていない場合、最後に追加
		if (!addFlg) {
			detailList.add(getNewDetailLine());
		}

		// 行番号振りなおし
		for (int i = 0; i < detailList.size(); i++) {
			ShippingDetailCompanyList detail = detailList.get(i);
			detail.setShippingRowNo(new BigDecimal(i + 1));
		}

		// 納入先住所取得
		if (frm.getDeliveryAllAddress() == null
				|| frm.getDeliveryAllAddress().equals("")) {
			frm.setDeliveryAllAddress(shippingCommonsLogic
					.getDeliveryAddress(frm.getDeliveryCd()));
		}
		// チェックボックスクリア
		clearCheckFlg(frm);

		return mapping.findForward("success");
	}

	/**
	 * 行削除処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delRow(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("delRow.");
		}

		ShippingDetailCompanyForm frm = (ShippingDetailCompanyForm) form;

		List<ShippingDetailCompanyList> detailList = frm.getDetailList();
		int len = detailList.size();
		for (int i = len - 1; i >= 0; i--) {
			ShippingDetailCompanyList bean = detailList.get(i);
			if (bean.isCheckFlg()) {
				// 削除対象行
				detailList.remove(i);
			}
		}
		// 行番号振りなおし
		for (int i = 0; i < detailList.size(); i++) {
			ShippingDetailCompanyList detail = detailList.get(i);
			detail.setShippingRowNo(new BigDecimal(i + 1));
		}

		// チェックボックスクリア
		clearCheckFlg(frm);

		return mapping.findForward("success");
	}

	/**
	 * 出荷指図新規登録処理(詳細画面の登録ボタン押下時)
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
			getLog().debug("insert.");
		}
		ShippingDetailCompanyForm frm = (ShippingDetailCompanyForm) form;

		// 攝津倉庫と外部倉庫の混在チェック
		if (!isLocationCheck(frm, request)) {
			return mapping.findForward("success");
		}

		// ロケーションコードとロット番号の重複チェック
		if (!isOtherLotLocation(frm, request)) {
			return mapping.findForward("success");
		}

		// 出荷指図量累計チェック
		if (!isShippingInstructionCheck(frm, request)) {
			return mapping.findForward("success");
		}

		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 登録処理を実行
			shippingDetailCompanyLogic.insert(frm, tantoCd);
		} catch (ShippingLogicException e) {
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
				shippingCommonsLogic.outPutErrorLog(e.getModuleCd(), e
						.getInsideErrCd(), e.getInsideErrMsg(), tantoCd);
			}
			return mapping.getInputForward();
		}

		frm.setInsertFlg(0);

		// メッセージ
		saveMessage(request, "message.complete.insert");

		return mapping.findForward("initContinue");
	}

	/**
	 * 出荷指図更新処理(詳細画面の更新ボタン押下時)
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
			getLog().debug("update.");
		}
		ShippingDetailCompanyForm frm = (ShippingDetailCompanyForm) form;

		// 攝津倉庫と外部倉庫の混在チェック
		if (!isLocationCheck(frm, request)) {
			return mapping.findForward("success");
		}

		// ロケーションコードとロット番号の重複チェック
		if (!isOtherLotLocation(frm, request)) {
			return mapping.findForward("success");
		}

		// 出荷指図量累計チェック
		if (!isShippingInstructionCheck(frm, request)) {
			return mapping.findForward("success");
		}

		// ログイン担当者コード取得
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 更新処理を実行
			shippingDetailCompanyLogic.update(frm, tantoCd);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (ShippingLogicException e) {
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
				shippingCommonsLogic.outPutErrorLog(e.getModuleCd(), e
						.getInsideErrCd(), e.getInsideErrMsg(), tantoCd);
			}
			return mapping.getInputForward();
		}

		// メッセージ
		saveMessage(request, "message.complete.update");

		return mapping.findForward("back");
	}

	/**
	 * 出荷指図更新処理(詳細画面の削除ボタン押下時)
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
		ShippingDetailCompanyForm frm = (ShippingDetailCompanyForm) form;

		// ログイン担当者コード取得
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 削除処理を実行
			shippingDetailCompanyLogic.delete(frm, tantoCd);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (ShippingLogicException e) {
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
				shippingCommonsLogic.outPutErrorLog(e.getModuleCd(), e
						.getInsideErrCd(), e.getInsideErrMsg(), tantoCd);
			}
			return mapping.getInputForward();
		}

		// メッセージ
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 戻る処理(詳細画面の戻るボタン押下時)
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

		return mapping.findForward("back");
	}

	/**
	 * 重複ロケ、ロットがないかチェック
	 * @param frm チェック対象フォーム
	 * @param response resource取得用のresponse
	 * @return boolean 全ての項目がマスタにある:true<br>
	 *         マスタにないものがある:false
	 */
	private boolean isOtherLotLocation(final ShippingDetailCompanyForm frm,
			final HttpServletRequest request) {
		boolean res = true;

		// 品目ループ
		// for (ShippingDetailCompanyList itemList : frm.getDetailList()) {
		int intLineNo = 0;

		// ロケーションループ
		for (ShippingDetailCompanyList detailList : frm.getDetailList()) {
			intLineNo++;
			if (intLineNo > 1
					&& !isOtherLotLocationOne(detailList, frm.getDetailList())) {
				ActionMessages messages = new ActionMessages();
				messages.add("", new ActionMessage(
						"errors.shipping.same.lot.and.location", Integer
								.toString(intLineNo)));
				addErrors(request, messages);
				res = false;
				return res;

			}
		}

		// }
		return res;

	}

	/**
	 * 重複ロケ、ロットがないかチェック
	 * @param detail 比較対象レコード
	 * @param detailList 出荷明細
	 * @return ShippingDetailOtherForm
	 */
	private boolean isOtherLotLocationOne(
			final ShippingDetailCompanyList detail,
			final List<ShippingDetailCompanyList> detailList) {

		BigDecimal count = new BigDecimal(0);
		// ロケーションループ
		for (ShippingDetailCompanyList ref : detailList) {
			// 同一ロット、同一ロケーションを検索
			if (ref.getLocationCd().equals(detail.getLocationCd())
					&& ref.getLotNo().equals(detail.getLotNo())) {
				count = count.add(BigDecimal.ONE);

				// 複数件ある場合
				if (count.intValue() > 1) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 出荷指図検索結果を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param bean 出荷指図検索結果
	 * @return ShippingDetailCompanyForm
	 */
	private ShippingDetailCompanyForm setShippingData(
			final ShippingDetailCompanyForm form,
			final ShippingDetailCompanyEntity bean) {
		form.setShippingNo(bean.getShippingNo()); // 出荷番号
		form.setStrShippingInstructDate(bean.getStrShippingInstructDate()); // 出荷指図日
		form.setOrderNo(bean.getOrderNo()); // 受注番号
		form.setOrderRowNo(getBigDecimalString(bean.getOrderRowNo())); // 行番号(受注)
		form.setDeliveryCd(bean.getDeliveryCd()); // 納入先コード
		form.setDeliveryName1(bean.getDeliveryName()); // 納入先名称
		form.setDeliveryAddress(bean.getDeliveryAddress()); // 納入先宛先
		form.setVenderCd(bean.getVenderCd()); // 取引先コード
		form.setItemCd(bean.getItemCd()); // 品目コード
		form.setItemName(bean.getItemName()); // 品目名称
		form.setBalanceCd(bean.getBalanceCd()); // 帳合コード
		form.setOtherCompanyCd1(bean.getOtherCompanyCd1()); // 他社コード1
		form.setStyleOfPacking(bean.getStyleOfPacking()); // 荷姿
		form.setUnitDivision(bean.getUnitDivision()); // 単位
		form.setShippingStatus(getBigDecimalString(bean.getShippingStatus())); // 出荷ステータス
		form.setShippingStatusName(bean.getStrShippingStatus()); // 出荷ステータス名称
		form.setStrScheduledShippingDate(bean.getStrScheduledShippingDate()); // 出荷予定日
		form.setCarryCd(bean.getCarryCd()); // 運送会社コード
		form.setStrSuggestedDeliverlimit(bean.getStrSuggestedDeliverlimit()); // 希望納期
		form.setStrCarryFare(checker.format(ShippingConst.UNIT_DIVISION_UNTIN,
			ShippingConst.VENDER_DIVISION_TS, bean.getVenderCd(), bean
					.getCarryFare())); // 運賃
		form.setStrOrderQty(checker.format(bean.getUnitDivision(),
			ShippingConst.VENDER_DIVISION_TS, bean.getVenderCd(), bean
					.getOrderQty())); // 受注量
		form.setStrMatss(checker.format(bean.getUnitDivision(),
			ShippingConst.VENDER_DIVISION_TS, bean.getVenderCd(), bean
					.getMatss())); // 増付量
		form
				.setStrUpdateDateOrderHead(bean.getUpdateDateOrderHead()
						.toString()); // 受注ヘッダ更新日時
		form.setStrUpdateDateOrderDetail(bean.getUpdateDateOrderDetail()
				.toString()); // 受注詳細更新日時
		setCheckDigit(form);

		// 前回の受注情報
		form.setOrderNoPrev(bean.getOrderNo()); // 受注番号
		form.setOrderRowNoPrev(bean.getOrderRowNo().toString()); // 行番号(受注)
		form.setStrUpdateDateOrderHeadPrev(bean.getUpdateDateOrderHead()
				.toString()); // 受注ヘッダ更新日時
		form.setStrUpdateDateOrderDetailPrev(bean.getUpdateDateOrderDetail()
				.toString()); // 受注詳細更新日時

		if (bean.getDeliveryExpectedDate() != null) {
			form.setStrDeliveryExpectedDate(AecDateUtils.dateFormat(bean
					.getDeliveryExpectedDate(), "yyyy/MM/dd"));

		}

		form.setDeliveryAllAddress(bean.getDeliveryAllAddress());
		return form;
	}

	/**
	 * BigDecimalの文字列表現を取得する BigDecimal=null時はnullを返す
	 * @param dec BigDecimal
	 * @return BigDecimalの文字列表現
	 */
	private String getBigDecimalString(final BigDecimal dec) {
		String res = null;
		if (dec != null) {
			res = dec.toString();
		}
		return res;
	}

	/**
	 * 外部倉庫と攝津堺倉庫の混在チェック
	 * @param frm マスタチェック対象フォーム
	 * @param request HttpServletRequest
	 * @return boolean 外部倉庫と攝津堺倉庫が混在の場合:false
	 */
	private boolean isLocationCheck(final ShippingDetailCompanyForm frm,
			final HttpServletRequest request) {
		ActionMessages messages = new ActionMessages();

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"errors.shipping.other.location"));

		// ロケーション区分
		BigDecimal division = new BigDecimal(0);
		BigDecimal count = new BigDecimal(0);
		String upperLocation = null;

		for (ShippingDetailCompanyList detail : frm.getDetailList()) {

			try {
				upperLocation = shippingDetailCompanyLogic
						.getUpperLocation(detail.getLocationCd());
			} catch (NoDataException e) {
				saveErrors(request, messages);
				return false;
			}

			// 上位ロケーションが取得できなかった場合
			if (upperLocation == null) {
				saveErrors(request, messages);
				return false;
			}
			// 倉庫がまだ決まっていない場合
			if (count.equals(BigDecimal.ZERO)) {

				// 倉庫によって区分を変更
				if ("K".equals(upperLocation)) {
					division = SOM_LOCATION;
				} else {
					division = OTHER_LOCATION;
				}
				// 
			} else {
				// 倉庫によって区分を変更
				if ("K".equals(upperLocation)) {
					if (!division.equals(SOM_LOCATION)) {
						saveErrors(request, messages);
						return false;
					}
				} else {
					if (!division.equals(OTHER_LOCATION)) {
						saveErrors(request, messages);
						return false;
					}
				}

			}
			count = count.add(BigDecimal.ONE);
		}

		return true;
	}

	/**
	 * 出荷指図量計算
	 * @param frm マスタチェック対象フォーム
	 * @param request HttpServletRequest
	 * @return boolean 出荷指図量の合計 ≠ 受注量 ＋ 増付数 の場合:false
	 */
	private boolean isShippingInstructionCheck(
			final ShippingDetailCompanyForm frm,
			final HttpServletRequest request) {
		ActionMessages messages = new ActionMessages();

		// 出荷指図量の累計を算出
		BigDecimal sum = new BigDecimal(0);
		for (ShippingDetailCompanyList detail : frm.getDetailList()) {
			sum = sum.add(new BigDecimal(StringUtils.replace(detail
					.getStrShippingInstruction(), ",", "")));
		}
		// 受注量 ＝ 受注量 ＋ 増付数
		BigDecimal order = new BigDecimal(0);
		order = order.add(
			new BigDecimal(StringUtils.replace(frm.getStrOrderQty(), ",", "")))
				.add(
					new BigDecimal(StringUtils.replace(frm.getStrMatss(), ",",
						"")));

		// 出荷指図量の合計 ≠ 受注量 ＋ 増付数 の場合、エラーとする
		if (!sum.equals(order)) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.shipping.shipping.instruction.sum"));
			saveErrors(request, messages);
			return false;
		}

		return true;
	}

	/**
	 * 新規明細行を取得する
	 * @return ShippingShippingDetail
	 */
	private ShippingDetailCompanyList getNewDetailLine() throws Exception {
		ShippingDetailCompanyList data = new ShippingDetailCompanyList();
		data.setCheckFlg(false); // チェックフラグ
		data.setShippingNo(null); // 出荷伝票番号
		data.setShippingRowNo(new BigDecimal(0)); // 行番号(出荷)
		data.setRowNo(null); // 行番号(未使用)
		data.setLocationCd(null); // ロケーションコード
		data.setLocationName(null); // ロケーション名称
		data.setLotNo(null); // ロット番号
		data.setShippingInstruction(null); // 指図量
		return data;
	}

	/**
	 * チェックボックスクリア処理
	 * @param form 画面のForm
	 * @return ShippingDetailCompanyForm 画面のForm
	 */
	private ShippingDetailCompanyForm clearCheckFlg(
			final ShippingDetailCompanyForm form) {
		// チェックボックスをクリアする
		for (ShippingDetailCompanyList detail : form.getDetailList()) {
			detail.setCheckFlg(Boolean.FALSE);
		}

		return form;
	}

	/**
	 * 数値桁数チェック部品からjavascriptでの桁数丸め用に必要となる値を取得
	 * @param frm 自社ロット他用画面Form
	 */
	private void setCheckDigit(final ShippingDetailCompanyForm frm) {
		NumberChkDisitDetail detail = checker.getCheckDigit(frm
				.getUnitDivision(), ShippingConst.VENDER_DIVISION_TS, frm
				.getVenderCd());
		// 小数点桁数
		if (detail.getSmallnumLength() != null) {
			frm.setDecimalPoint(detail.getSmallnumLength().toString());
		}
		// 端数区分
		if (detail.getRoundDivision() != null) {
			frm.setRound(detail.getRoundDivision().toString());
		}
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
	 * 出荷指図共通ロジッククラスを設定します。
	 * @param shippingCommonsLogic 出荷指図共通ロジッククラス
	 */
	public void setShippingCommonLogic(
			final ShippingCommonsLogic shippingCommonsLogic) {
		this.shippingCommonsLogic = shippingCommonsLogic;
	}

	/**
	 * 出荷指図詳細(自社ロット)ロジッククラスを設定します。
	 * @param shippingDetailCompanyLogic 出荷指図詳細(自社ロット)ロジッククラス
	 */
	public void setShippingDetailCompanyLogic(
			final ShippingDetailCompanyLogic shippingDetailCompanyLogic) {
		this.shippingDetailCompanyLogic = shippingDetailCompanyLogic;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

}
