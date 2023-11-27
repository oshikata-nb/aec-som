/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.delivery;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.master.delivery.Delivery;
import com.asahikaseieng.dao.nonentity.master.carrydetail.CarryDetail;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetail;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 納入先詳細 Actionクラス.
 * @author t0011036
 */
public final class DeliveryDetailAction extends AbstractAction {

	private DeliveryDetailLogic deliveryDetailLogic;

	// /** 納入時刻最大バイト数 */
	// private static final BigDecimal DELIVERY_TIME_MAX_BYTE = new BigDecimal(
	// "10");

	/**
	 * コンストラクタ.
	 */
	public DeliveryDetailAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		DeliveryDetailForm frm = (DeliveryDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_DELIVERY,
			Constants.TAB_ID_DELIVERY_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		DeliveryDetail bean = deliveryDetailLogic.getDetailEntity(frm
				.getDeliveryCd());

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		/* 数値文字列変換 */
		frm.setStrLeadTime(checker.format("SONOTA", frm.getLeadTime()));

		if (bean.getFareClaimExistence().equals(new BigDecimal("1"))) {
			frm.setFareClaimExistence(Boolean.TRUE);
		} else {
			frm.setFareClaimExistence(Boolean.FALSE);
		}

		return mapping.findForward("success");
	}

	/**
	 * 登録処理処理.
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

		DeliveryDetailForm frm = (DeliveryDetailForm) form;

		if (!StringUtils.isEmpty(frm.getCarryCd())) {
			/* 運送会社コードチェック */
			CarryDetail beanCarry = deliveryDetailLogic.getCarryEntity(frm
					.getCarryCd());

			if (beanCarry == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.delivery.carry.cd");
				return mapping.findForward("success");
			}
		}

		/* 納入先マスタの納入時刻は全角、半角10ケタとするよう処理を修正
		// 納入時刻のバイト数チェック
		// バイト数を返す
		if (getByteLen(frm.getDeliveryTime()).compareTo(DELIVERY_TIME_MAX_BYTE) > 0) {
			ResourceBundle rb = ResourceBundle
					.getBundle(Constants.APPLICATION_PROPERTIES);
			ActionMessages errors = new ActionMessages();

			errors.add("", new ActionMessage("errors.string.no.maxstringnum",
					rb.getString("delivery.delivery.time"), rb
							.getString("delivery.delivery.time.width.len"), rb
							.getString("delivery.delivery.time.half.len")));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}
*/
		if (!StringUtils.isEmpty(frm.getTantoCd())) {
			/* 営業担当者コードチェック */
			LoginDetail beanLogin = deliveryDetailLogic.getLoginEntity(frm
					.getTantoCd());

			if (beanLogin == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.delivery.tanto.cd");
				return mapping.findForward("success");
			}
		}

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		/* 数値変換 */
		frm.setLeadTime(check.round("SONOTA", null, null, strToNum(frm
				.getStrLeadTime())));

		if (frm.getUpdateDate() == null) {
			/* 追加処理を実行 */
			deliveryDetailLogic.insert(insertDelivery(frm,
				getLoginInfo(request).getTantoCd()));
		} else {
			/* 更新処理を実行 */
			deliveryDetailLogic.update(updateDelivery(frm,
				getLoginInfo(request).getTantoCd()));
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		if (StringUtils.isEmpty(frm.getCopyFlg())) {
			return mapping.findForward("back");
		} else {
			return mapping.findForward("backVender");
		}
	}

	/**
	 * 文字列を数値に変換
	 * @param value 変換元の文字列
	 * @return 変換後の数値
	 */
	private BigDecimal strToNum(final String value) {
		return AecNumberUtils.convertNullToZero(AecNumberUtils
				.convertBigDecimal(value));
	}

	/**
	 * 削除処理処理.
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

		DeliveryDetailForm frm = (DeliveryDetailForm) form;

		/* 削除処理を実行 */
		deliveryDetailLogic.delete(deleteDelivery(frm, getLoginInfo(request)
				.getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用のDeliveryを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Delivery
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Delivery updateDelivery(final DeliveryDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Delivery newBean = new Delivery();

		try {
			newBean = deliveryDetailLogic.getEntity(frm.getDeliveryCd());
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		if (frm.getFareClaimExistence() == Boolean.TRUE) {
			newBean.setFareClaimExistence(new BigDecimal("1"));
		} else {
			newBean.setFareClaimExistence(new BigDecimal("0"));
		}

		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のDeliveryを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Delivery
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Delivery insertDelivery(final DeliveryDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Delivery newBean = new Delivery();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		if (frm.getFareClaimExistence() == Boolean.TRUE) {
			newBean.setFareClaimExistence(new BigDecimal("1"));
		} else {
			newBean.setFareClaimExistence(new BigDecimal("0"));
		}

		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 削除処理用のDeliveryを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Delivery
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Delivery deleteDelivery(final DeliveryDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Delivery newBean = new Delivery();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		return newBean;
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

		return mapping.findForward("back");
	}

	/**
	 * 取引先へ戻る処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward backVender(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("backVender.");
		}

		return mapping.findForward("backVender");
	}

	/**
	 * 新規処理.
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

		DeliveryDetailForm frm = (DeliveryDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_DELIVERY,
			Constants.TAB_ID_DELIVERY_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();

		/* 新規処理時は納入先地区へ本州をセット */
		frm.setCarryFareCd("02");
		
		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		return mapping.findForward("success");
	}

	/**
	 * 取引先からコピー
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward copy(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("copy.");
		}

		DeliveryDetailForm frm = (DeliveryDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_DELIVERY,
			Constants.TAB_ID_DELIVERY_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		VenderDetail bean = deliveryDetailLogic.getVenderEntity(frm
				.getVenderDivision(), frm.getVenderCd());

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

		/* コピーしきれなかった分は手で */
		frm.setDeliveryCd(null);
		frm.setDeliveryName1(bean.getVenderName1());
		frm.setDeliveryName2(bean.getVenderName2());
		frm.setSearchKana(bean.getVenderShortedName());
		frm.setUpdateDate(null);

		/* 新規処理時は納入先地区へ本州をセット */
		frm.setCarryFareCd("02");
		
		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		return mapping.findForward("success");
	}

	/**
	 * バイト数を返す
	 * @param source 対象文字列
	 * @return 対象文字列のバイト数
	 */
	public static BigDecimal getByteLen(final String source) {
		BigDecimal res = BigDecimal.ZERO;

		if (StringUtils.isNotEmpty(source)) {
			int num = source.length();
			int length = 0;

			for (int i = 0; i < num; i++) {
				char c = source.charAt(i);

				if (AecStringUtils.isHankaku(c)) {
					length++; // 半角
				} else {
					length += 2; // 全角
				}
			}

			res = new BigDecimal(length);
		}

		return res;
	}

	/* -------------------- setter -------------------- */

	/**
	 * deliveryDetailLogicを設定します。
	 * @param deliveryDetailLogic deliveryDetailLogic
	 */
	public void setDeliveryDetailLogic(
			final DeliveryDetailLogic deliveryDetailLogic) {
		this.deliveryDetailLogic = deliveryDetailLogic;
	}
}
