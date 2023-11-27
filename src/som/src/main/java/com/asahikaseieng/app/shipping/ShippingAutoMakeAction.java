/*
 * Created on 2009/02/25
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

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.shipping.ShippingAutoMake;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 出荷指図指図自動作成 Actionクラス.
 * @author tosco
 */
public final class ShippingAutoMakeAction extends AbstractAction {

	/** 出荷指図詳細(自社ロット)ロジッククラス */
	private ShippingAutoMakeLogic shippingAutoMakeLogic;

	/** 出荷指図自動作成 正常終了 */
	protected static final BigDecimal SHIPPING_AUTO_MAKE_NOMAL_END = new BigDecimal(
			0);

	/** 出荷指図自動作成 在庫異常 */
	protected static final BigDecimal SHIPPING_AUTO_MAKE_STOCK_ERROR = new BigDecimal(
			-1);

	/** 出荷指図自動作成 異常 */
	protected static final BigDecimal SHIPPING_AUTO_MAKE_ERROR = new BigDecimal(
			-9);

	/**
	 * コンストラクタ.
	 */
	public ShippingAutoMakeAction() {
		super();
	}

	/**
	 * 出荷指図指図自動作成初期表示処理(一覧画面の指図自動作成ボタン押下時)
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

		ShippingAutoMakeForm frm = (ShippingAutoMakeForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_SHIPPING,
			Constants.TAB_ID_SHIPPING_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 出荷予定日のデフォルトを現在日時とする
		frm.setScheduledShippingDateFrom(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));
		frm.setScheduledShippingDateTo(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));

		return mapping.findForward("success");

	}

	/**
	 * 出荷指図指図自動作成処理(指図自動作成の指図自動作成ボタン押下時)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward autoMake(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("autoMake.");
		}
		ShippingAutoMakeForm frm = (ShippingAutoMakeForm) form;

		String tantoCd = getLoginInfo(request).getTantoCd();

		List<ShippingAutoMake> list;

		// 出荷指図自動作成件数カウント
		BigDecimal count = BigDecimal.ZERO;

		list = shippingAutoMakeLogic.getShippingAutoMakeList(frm);
		for (ShippingAutoMake auto : list) {

			try {

				// 指図自動作成処理
				boolean ret = shippingAutoMakeLogic.shippingAutoMake(auto, frm
						.getScheduledShippingDateFrom(), frm
						.getScheduledShippingDateTo(), tantoCd);

				// 出荷指図自動作成した場合のみカウントアップ
				if (ret) {
					count = count.add(BigDecimal.ONE);
				}
			} catch (ShippingLogicException e) {
				if (StringUtils.isNotEmpty(e.getModuleCd())) {
					// エラーログに出力する
					shippingAutoMakeLogic.outPutErrorLog(e.getInsideErrCd(), e
							.getInsideErrMsg(), tantoCd);
					addError(request, e.getKey(), e.getInsideErrMsg());
				}
				// return mapping.getInputForward();
			}
		}

		// 画面に処理結果数を表示する
		frm.setProcNum(count.toString());

		// メッセージ
		saveMessage(request, "message.shipping.complete.auto.make");

		return mapping.findForward("success");
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
	 * 指図自動作成ロジッククラスを設定します。
	 * @param shippingAutoMakeLogic 出荷指図共通ロジッククラス
	 */
	public void setShippingAutoMakeLogic(
			final ShippingAutoMakeLogic shippingAutoMakeLogic) {
		this.shippingAutoMakeLogic = shippingAutoMakeLogic;
	}

}
