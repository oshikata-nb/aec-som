/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.remark;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.remark.Remark;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.remarkdetail.RemarkDetail;
import com.asahikaseieng.dao.nonentity.master.remarkdetailcheck.RemarkDetailCheck;
import com.asahikaseieng.dao.nonentity.master.remarkdetailgetmaxno.RemarkDetailGetMaxRemarkNo;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 備考詳細 Actionクラス.
 * @author t0011036
 */
public final class RemarkDetailAction extends AbstractAction {

	private RemarkDetailLogic remarkDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public RemarkDetailAction() {
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

		RemarkDetailForm frm = (RemarkDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_REMARK,
			Constants.TAB_ID_REMARK_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		RemarkDetail bean = remarkDetailLogic
				.getDetailEntity(frm.getRemarkNo());

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

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

		RemarkDetailForm frm = (RemarkDetailForm) form;

		if (!StringUtils.isEmpty(frm.getVenderCd())) {
			/* 取引先コードチェック */
			VenderDetail beanVender = remarkDetailLogic.getVenderEntity(frm
					.getVenderDivision(), frm.getVenderCd());

			if (beanVender == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.remark.vender.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getDeliveryCd())) {
			/* 納入先コードチェック */
			DeliveryDetail beanDelivery = remarkDetailLogic
					.getDeliveryEntity(frm.getDeliveryCd());

			if (beanDelivery == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.remark.delivery.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getItemCd())) {
			/* 品目コードチェック */
			ItemQueueLastVersion beanItemQueue = remarkDetailLogic
					.getItemQueueEntity(frm.getItemCd());

			if (beanItemQueue == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.remark.item.cd");
				return mapping.findForward("success");
			}
		}

		/* 未入力チェック */
		if (StringUtils.isEmpty(frm.getVenderCd())
				&& StringUtils.isEmpty(frm.getDeliveryCd())
				&& StringUtils.isEmpty(frm.getItemCd())) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.remark.vender.delivery.item.cd");
			return mapping.findForward("success");
		}

		if (frm.getUpdateDate() == null) {
			/* 重複チェック */
			RemarkDetailCheck beanCheck = remarkDetailLogic
					.getRemarkDetailCheck(frm.getVenderDivision(), frm
							.getVenderCd(), frm.getDeliveryCd(), frm
							.getItemCd());

			if (beanCheck != null) {
				/* エラーメッセージ */
				saveError(request, "errors.remark.duplicate.cd");
				return mapping.findForward("success");
			}

			/* 追加処理を実行 */
			remarkDetailLogic.insert(insertRemark(frm, getLoginInfo(request)
					.getTantoCd()));
		} else {
			/* 更新処理を実行 */
			remarkDetailLogic.update(updateRemark(frm, getLoginInfo(request)
					.getTantoCd()));
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");
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

		RemarkDetailForm frm = (RemarkDetailForm) form;

		/* 削除処理を実行 */
		remarkDetailLogic.delete(deleteRemark(frm, getLoginInfo(request)
				.getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用のRemarkを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Remark
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Remark updateRemark(final RemarkDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Remark newBean = new Remark();

		try {
			newBean = remarkDetailLogic.getEntity(frm.getRemarkNo());
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		if (StringUtils.isEmpty(frm.getVenderCd())) {
			newBean.setVenderDivision(null);
		}

		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のRemarkを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Remark
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Remark insertRemark(final RemarkDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Remark newBean = new Remark();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* 最大備考番号取得 */
		RemarkDetailGetMaxRemarkNo bean = remarkDetailLogic.getMaxRemarkNo();

		BigDecimal remarkNo = null;

		if (bean == null) {
			remarkNo = new BigDecimal("1");
		} else {
			remarkNo = bean.getMax().add(new BigDecimal("1"));
		}

		/* コピーしきれなかった分は手で */
		if (StringUtils.isEmpty(frm.getVenderCd())) {
			newBean.setVenderDivision(null);
		}

		newBean.setRemarkNo(remarkNo);
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 削除処理用のRemarkを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Remark
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Remark deleteRemark(final RemarkDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Remark newBean = new Remark();

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

		RemarkDetailForm frm = (RemarkDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_REMARK,
			Constants.TAB_ID_REMARK_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * remarkDetailLogicを設定します。
	 * @param remarkDetailLogic remarkDetailLogic
	 */
	public void setRemarkDetailLogic(final RemarkDetailLogic remarkDetailLogic) {
		this.remarkDetailLogic = remarkDetailLogic;
	}
}
