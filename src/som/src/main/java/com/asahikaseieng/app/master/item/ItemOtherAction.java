/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.itemremark.ItemRemark;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemremarkdetail.ItemRemarkDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * その他 Actionクラス.
 * @author t0011036
 */
public final class ItemOtherAction extends AbstractAction {

	private ItemOtherLogic itemOtherLogic;

	/**
	 * コンストラクタ.
	 */
	public ItemOtherAction() {
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

		ItemOtherForm frm = (ItemOtherForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ITEM,
			Constants.TAB_ID_ITEM_OTHER);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* ヘッダー検索 */
		ItemQueueHeader beanHeader = itemOtherLogic.getHeaderEntity(frm
				.getItemCd(), frm.getVersion());

		/* 日付文字列に変換 */
		beanHeader.setStrHeadActiveDate(AecDateUtils.dateFormat(beanHeader
				.getHeadActiveDate(), "yyyy/MM/dd"));

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, beanHeader);

		/* 初期検索 */
		ItemRemarkDetail beanRemark = itemOtherLogic.getRemarkEntity(frm
				.getItemCd(), frm.getVersion());

		if (beanRemark != null) {
			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, beanRemark);
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

		ItemOtherForm frm = (ItemOtherForm) form;

		if (frm.getUpdateDate() == null) {
			/* 追加処理を実行 */
			itemOtherLogic.insert(insertItemRemark(frm, getLoginInfo(request)
					.getTantoCd()));
		} else {
			/* 更新処理を実行 */
			itemOtherLogic.update(updateItemRemark(frm, getLoginInfo(request)
					.getTantoCd()));
		}

		frm.setUpdateDate(AecDateUtils.getCurrentTimestamp());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 追加処理用のItemRemark.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return ItemRemark
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private ItemRemark insertItemRemark(final ItemOtherForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		ItemRemark newBean = new ItemRemark();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用のItemRemark.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return ItemRemark
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private ItemRemark updateItemRemark(final ItemOtherForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		ItemRemark newBean = new ItemRemark();

		try {
			newBean = itemOtherLogic.getEntity(frm.getHeadItemCd(), frm
					.getHeadVersion());
		} catch (NoDataException e) {
			return null;
		}

		Timestamp updateDate = newBean.getUpdateDate();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setUpdatorCd(tantoCd);
		newBean.setUpdateDate(updateDate);

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

	/* -------------------- setter -------------------- */

	/**
	 * itemOtherLogicを設定します。
	 * @param itemOtherLogic itemOtherLogic
	 */
	public void setItemOtherLogic(final ItemOtherLogic itemOtherLogic) {
		this.itemOtherLogic = itemOtherLogic;
	}
}
