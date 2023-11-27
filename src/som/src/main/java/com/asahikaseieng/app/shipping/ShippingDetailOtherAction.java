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
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailOtherEntity;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailOtherList;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 出荷指図詳細(花王・その他) Actionクラス.
 * @author tosco
 */
public final class ShippingDetailOtherAction extends AbstractAction {

	/** ステータス 新規登録 */
	private static final String SHIPPING_STATUS_NEW = "新規登録";

	/** 出荷指図共通ロジッククラス */
	private ShippingCommonsLogic shippingCommonsLogic;

	/** 出荷指図詳細(花王・その他)ロジッククラス */
	private ShippingDetailOtherLogic shippingDetailOtherLogic;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/**
	 * コンストラクタ.
	 */
	public ShippingDetailOtherAction() {
		super();
	}

	/**
	 * 出荷指図検索処理(一覧画面の出荷番号(花王・その他)リンク押下時)
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

		ShippingDetailOtherForm frm = (ShippingDetailOtherForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_SHIPPING,
			Constants.TAB_ID_SHIPPING_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		try {
			// 初期検索
			ShippingDetailOtherEntity bean = shippingDetailOtherLogic
					.getEntity(frm.getShippingNo());
			// BeanをFormにコピーする
			setShippingData(frm, bean);
			// 品目リストを１件作成
			setItemData(frm, bean);
			frm.setShippingBean(bean);
			// 運送会社コンボボックス
			frm.setCarryCombo(shippingCommonsLogic.createCarryCombobox());

			// 出荷指図詳細情報を取得する
			List<ShippingDetailOtherList> list = shippingDetailOtherLogic
					.getDetailList(frm.getShippingNo(), bean.getUnitDivision());
			frm.getItemList().get(0).setDetailList(list);

			frm.setInsertFlg(0);
			frm.setDirtyFlg(null);
		} catch (NoDataException e) {
			// エラーメッセージ
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");

	}

	/**
	 * 新規登録画面表示処理(一覧画面の花王・その他ボタン押下時)
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

		ShippingDetailOtherForm frm = (ShippingDetailOtherForm) form;

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

		// ステータスを"新規登録"とする
		frm.setShippingStatusName(SHIPPING_STATUS_NEW);

		// 出荷指図日のデフォルトを現在日時とする
		frm.setStrShippingInstructDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));

		// 出荷予定日のデフォルトを現在日時とする
		frm.setStrScheduledShippingDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));

		// 運送会社コンボボックス
		frm.setCarryCombo(shippingCommonsLogic.createCarryCombobox());

		// 品目を１件追加
		frm.getItemList().add(getNewItemLine());
		frm.setDirtyFlg(null);

		return mapping.findForward("success");
	}

	/**
	 * 品目追加処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward addItemRow(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("addRow.");
		}

		ShippingDetailOtherForm frm = (ShippingDetailOtherForm) form;
		List<ShippingDetailOtherItemBean> itemList = frm.getItemList();
		int len = itemList.size();
		boolean addFlg = false;
		for (int i = 0; i < len; i++) {
			ShippingDetailOtherItemBean bean = itemList.get(i);
			if (bean.isCheckFlg()) {
				// チェックがついていた場合は、その品目の上に追加
				itemList.add(i, getNewItemLine());
				addFlg = true;
				break;
			}
		}

		// 品目追加が行われなかった場合、最後に追加
		if (!addFlg) {
			itemList.add(getNewItemLine());
		}

		// チェックボックスクリア
		clearCheckFlg(frm);

		return mapping.findForward("success");
	}

	/**
	 * 品目削除処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delItemRow(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("delRow.");
		}

		ShippingDetailOtherForm frm = (ShippingDetailOtherForm) form;
		List<ShippingDetailOtherItemBean> itemList = frm.getItemList();
		int len = itemList.size();
		for (int i = len - 1; i >= 0; i--) {
			ShippingDetailOtherItemBean bean = itemList.get(i);
			if (bean.isCheckFlg()) {
				// 削除対象行
				itemList.remove(i);
			}
		}
		if (itemList.size() <= 0) {
			// 明細行が空になった場合は、新規行を1行追加する
			itemList.add(getNewItemLine());
		}

		// チェックボックスクリア
		clearCheckFlg(frm);

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

		ShippingDetailOtherForm frm = (ShippingDetailOtherForm) form;

		// 編集中の品目情報を取得する
		int itemIndex = frm.getItemIndex();
		List<ShippingDetailOtherList> detailList = frm.getItemList().get(
			itemIndex).getDetailList();

		// 最初に行を追加する場合のみチェックする
		if (detailList.size() < 1) {
			// 品目情報取得
			if (!getItemInfo(frm, request)) {
				frm.setFocusId(null);
				return mapping.findForward("success");
			}
		}

		// 行を追加する
		int len = detailList.size();
		boolean addFlg = false;
		for (int i = 0; i < len; i++) {
			ShippingDetailOtherList bean = detailList.get(i);
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
			ShippingDetailOtherList detail = detailList.get(i);
			detail.setShippingRowNo(new BigDecimal(i + 1));
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

		ShippingDetailOtherForm frm = (ShippingDetailOtherForm) form;

		// 編集中の品目情報を取得する

		int itemIndex = frm.getItemIndex();
		List<ShippingDetailOtherList> detailList = frm.getItemList().get(
			itemIndex).getDetailList();
		int len = detailList.size();
		for (int i = len - 1; i >= 0; i--) {
			ShippingDetailOtherList bean = detailList.get(i);
			if (bean.isCheckFlg()) {
				// 削除対象行
				detailList.remove(i);
			}
		}
		// 行番号振りなおし
		for (int i = 0; i < detailList.size(); i++) {
			ShippingDetailOtherList detail = detailList.get(i);
			detail.setShippingRowNo(new BigDecimal(i + 1));
		}

		// チェックボックスクリア
		clearCheckFlg(frm);

		return mapping.findForward("success");
	}

	/**
	 * 納入先を選択
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward selectedDelivery(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("selectedDelivery.");
		}
		ShippingDetailOtherForm frm = (ShippingDetailOtherForm) form;

		ActionMessages messages = new ActionMessages();
		shippingDetailOtherLogic.checkCarry(frm.getTempCarryCd(), messages);

		if (!messages.isEmpty()) {
			saveErrors(request, messages);
		}

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
		ShippingDetailOtherForm frm = (ShippingDetailOtherForm) form;

		// 入力された各コードのマスタ存在チェック
		if (!isMasterCheck(frm, request)) {
			return mapping.findForward("success");
		}

		// ロケーションコードとロット番号の重複チェック
		if (!isOtherLotLocation(frm, request)) {
			return mapping.findForward("success");
		}

		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 登録処理を実行
			shippingDetailOtherLogic.insert(frm, tantoCd);
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

		return mapping.findForward("init");
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
		ShippingDetailOtherForm frm = (ShippingDetailOtherForm) form;

		// 入力された各コードのマスタ存在チェック
		if (!isMasterCheck(frm, request)) {
			return mapping.findForward("success");
		}

		// ロケーションコードとロット番号の重複チェック
		if (!isOtherLotLocation(frm, request)) {
			return mapping.findForward("success");
		}

		// ログイン担当者コード取得
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 更新処理を実行
			shippingDetailOtherLogic.update(frm, tantoCd);
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
		ShippingDetailOtherForm frm = (ShippingDetailOtherForm) form;

		// ログイン担当者コード取得
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 削除処理を実行
			shippingDetailOtherLogic.delete(frm, tantoCd);
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
	 * 出荷指図検索結果を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param bean 出荷指図検索結果
	 * @return ShippingDetailOtherForm
	 */
	private ShippingDetailOtherForm setShippingData(
			final ShippingDetailOtherForm form,
			final ShippingDetailOtherEntity bean) {
		form.setShippingNo(bean.getShippingNo()); // 出荷番号
		form.setStrShippingInstructDate(bean.getStrShippingInstructDate()); // 出荷指図日
		form.setOrderNo(bean.getOrderNo()); // 受注番号
		form.setOrderRowNo(getBigDecimalString(bean.getOrderRowNo())); // 行番号(受注)
		form.setDeliveryCd(bean.getDeliveryCd()); // 納入先コード
		form.setDeliveryName1(bean.getDeliveryName()); // 納入先名称
		form.setShippingStatus(getBigDecimalString(bean.getShippingStatus())); // 出荷ステータス
		form.setShippingStatusName(bean.getStrShippingStatus()); // 出荷ステータス名称
		form.setStrScheduledShippingDate(bean.getStrScheduledShippingDate()); // 出荷予定日
		form.setCarryCd(bean.getCarryCd()); // 運送会社コード
		form.setStrSuggestedDeliverlimit(bean.getStrSuggestedDeliverlimit()); // 希望納期

		return form;
	}

	/**
	 * 出荷指図検索結果を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param bean 出荷指図検索結果
	 * @return ShippingDetailOtherForm
	 */
	private ShippingDetailOtherForm setItemData(
			final ShippingDetailOtherForm form,
			final ShippingDetailOtherEntity bean) {
		ShippingDetailOtherItemBean itemBean = new ShippingDetailOtherItemBean();
		itemBean.clear();
		itemBean.setItemCd(bean.getItemCd()); // 品目コード
		itemBean.setItemName(bean.getItemName()); // 品目名称
		itemBean.setOtherCompanyCd1(bean.getOtherCompanyCd1()); // 他社コード1
		itemBean.setStyleOfPacking(bean.getStyleOfPacking()); // 荷姿
		itemBean.setUnitDivision(bean.getUnitDivision()); // 単位
		setCheckDigit(itemBean);
		form.getItemList().add(itemBean);

		return form;
	}

	/**
	 * BigDecimalの文字列表現を取得する
	 * 
	 * BigDecimal=null時はnullを返す
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
	 * 重複ロケ、ロットがないかチェック
	 * @param frm マスタチェック対象フォーム
	 * @param response resource取得用のresponse
	 * @return boolean 全ての項目がマスタにある:true<br>
	 *         マスタにないものがある:false
	 */
	private boolean isOtherLotLocation(final ShippingDetailOtherForm frm,
			final HttpServletRequest request) {
		boolean res = true;

		// 品目ループ
		for (ShippingDetailOtherItemBean itemList : frm.getItemList()) {

			int intLineNo = 0;

			// ロケーションループ
			for (ShippingDetailOtherList detailList : itemList.getDetailList()) {
				intLineNo++;
				if (intLineNo > 1
						&& !isOtherLotLocationOne(detailList, itemList
								.getDetailList())) {
					ActionMessages messages = new ActionMessages();
					messages.add("", new ActionMessage(
							"errors.shipping.same.lot.and.location", Integer
									.toString(intLineNo)));
					addErrors(request, messages);
					res = false;
					return res;

				}
			}

		}
		return res;

	}

	/**
	 * 出荷指図検索結果を画面のFormに設定する。
	 * @param form 画面のForm
	 * @param bean 出荷指図検索結果
	 * @return ShippingDetailOtherForm
	 */
	private boolean isOtherLotLocationOne(final ShippingDetailOtherList detail,
			final List<ShippingDetailOtherList> detailList) {

		BigDecimal count = new BigDecimal(0);
		// ロケーションループ
		for (ShippingDetailOtherList ref : detailList) {
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
	 * 品目マスタ存在チェック.
	 * @param frm マスタチェック対象フォーム
	 * @param response resource取得用のresponse
	 * @return boolean 全ての項目がマスタにある:true<br>
	 *         マスタにないものがある:false
	 */
	private boolean getItemInfo(final ShippingDetailOtherForm frm,
			final HttpServletRequest request) {
		boolean res = true;
		int itemIndex = frm.getItemIndex();
		ShippingDetailOtherItemBean item = frm.getItemList().get(itemIndex);
		// 品目マスタ存在チェック
		if (StringUtils.isNotEmpty(item.getItemCd())) {
			try {
				ShippingDetailOtherEntity detail = shippingDetailOtherLogic
						.getItem(item.getItemCd());

				if (ShippingConst.KEEP_DIVISION_KEEP.equals(detail
						.getKeepDivision())) {
					// 預かり品の場合、品目の情報をセットする
					item.setItemName(detail.getItemName());
					item.setOtherCompanyCd1(detail.getOtherCompanyCd1());
					item.setStyleOfPacking(detail.getStyleOfPacking());
					item.setUnitDivision(detail.getUnitDivision());
					setCheckDigit(item);
				} else {
					// 預かり品でない場合、エラーとする
					ActionMessages messages = new ActionMessages();
					messages.add("",
						new ActionMessage(
								"errors.shipping.keep.division.not.keep",
								getMessageResource(request,
									"item.shipping.item.cd")));
					addErrors(request, messages);
					res = false;
				}
			} catch (NoDataException e) {
				// 品目マスタに存在しないのでエラーとする
				ActionMessages messages = new ActionMessages();
				messages.add("", new ActionMessage("errors.nodata.master",
						getMessageResource(request, "item.shipping.item.cd")));
				addErrors(request, messages);
				res = false;
			}
		}

		return res;
	}

	/**
	 * マスタ存在チェック.
	 * @param frm マスタチェック対象フォーム
	 * @param response resource取得用のresponse
	 * @return boolean 全ての項目がマスタにある:true<br>
	 *         マスタにないものがある:false
	 */
	private boolean isMasterCheck(final ShippingDetailOtherForm frm,
			final HttpServletRequest request) {
		ActionMessages messages = new ActionMessages();
		MessageResources resource = getResources(request);

		// 納入先マスタ存在チェック
		if (StringUtils.isNotEmpty(frm.getDeliveryCd())) {
			if (!shippingDetailOtherLogic.isExistsDelivery(frm.getDeliveryCd())) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.nodata.master", resource
								.getMessage("item.shipping.delivery.cd")));
			}
		}

		// 1つでもマスタに存在しないものがあればエラーとする
		if (!messages.isEmpty()) {
			saveErrors(request, messages);
			return false;
		}

		return true;
	}

	/**
	 * 新規明細行を取得する
	 * @return ShippingShippingNewItemBean
	 */
	private ShippingDetailOtherItemBean getNewItemLine() throws Exception {
		ShippingDetailOtherItemBean data = new ShippingDetailOtherItemBean();
		data.clear();

		return data;
	}

	/**
	 * 新規明細行を取得する
	 * @return ShippingShippingDetail
	 */
	private ShippingDetailOtherList getNewDetailLine() throws Exception {
		ShippingDetailOtherList data = new ShippingDetailOtherList();
		data.setCheckFlg(false); // チェックフラグ
		data.setShippingNo(null); // 出荷伝票番号
		data.setShippingRowNo(new BigDecimal(0)); // 行番号(出荷)
		data.setRowNo(null); // 行番号(未使用)
		data.setLocationCd(null); // ロケーションコード
		data.setLocationName(null); // ロケーション名称
		data.setLotNo(null); // ロット番号
		data.setShippingInstruction(null); // 指図量
		data.setShippingResultDate(null); // 出荷完了日
		data.setShippingResultQuantity(null); // 出荷実績数
		data.setShippingStatus(null); // 出荷ステータス
		data.setSummery(null); // 摘要:未使用
		data.setDeliveryComp(null); // 完納区分
		return data;
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
	 * チェックボックスクリア処理
	 * @param form 画面のForm
	 * @return ShippingDetailOtherForm 画面のForm
	 */
	private ShippingDetailOtherForm clearCheckFlg(
			final ShippingDetailOtherForm form) {
		// チェックボックスをクリアする
		for (ShippingDetailOtherItemBean bean : form.getItemList()) {
			bean.setCheckFlg(Boolean.FALSE);
			for (ShippingDetailOtherList detail : bean.getDetailList()) {
				detail.setCheckFlg(Boolean.FALSE);
			}
		}

		return form;
	}

	/**
	 * 数値桁数チェック部品からjavascriptでの桁数丸め用に必要となる値を取得
	 * @param bean 花王・その他用新規画面_品目情報表示用データBean
	 */
	private void setCheckDigit(final ShippingDetailOtherItemBean bean) {
		NumberChkDisitDetail detail = checker.getCheckDigit(bean
				.getUnitDivision(), null, null);
		// 小数点桁数
		if (detail.getSmallnumLength() != null) {
			bean.setDecimalPoint(detail.getSmallnumLength().toString());
		}
		// 端数区分
		if (detail.getRoundDivision() != null) {
			bean.setRound(detail.getRoundDivision().toString());
		}
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
	 * 出荷指図詳細(花王・その他)ロジッククラスを設定します。
	 * @param shippingDetailOtherLogic 出荷指図詳細(花王・その他)ロジッククラス
	 */
	public void setShippingDetailOtherLogic(
			final ShippingDetailOtherLogic shippingDetailOtherLogic) {
		this.shippingDetailOtherLogic = shippingDetailOtherLogic;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

}
