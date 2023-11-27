/*
 * Created on 2009/03/01
 * AECS佐藤 所属マスタ詳細画面へ受注納入先区分追加 2020/01/21
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belong;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.belongdetail.BelongDetail;
import com.asahikaseieng.dao.nonentity.master.belongdetail.BelongDetailDao;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.postdetail.PostDetail;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 所属詳細 Actionクラス.
 * @author t0011036
 */
public final class BelongDetailAction extends AbstractAction {

	/* 所属区分 主所属 */
	private static final BigDecimal BELONG_KBN_MAIN = new BigDecimal("1");

	/* 所属区分 兼務所属 */
	private static final BigDecimal BELONG_KBN_KENMU = new BigDecimal("2");

	private BelongDetailLogic belongDetailLogic;
	
	/* AECS佐藤 受注納入先区分コンボボックス追加のため 2020/01/21 */
	/** 所属マスタ用Dao */
	private BelongDetailDao belongDetailDao;

	/**
	 * コンストラクタ.
	 */
	public BelongDetailAction() {
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

		BelongDetailForm frm = (BelongDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_BELONG,
			Constants.TAB_ID_BELONG_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		BelongDetail bean = belongDetailLogic.getDetailEntity(frm
				.getOrganizationCd(), frm.getTantoCd(), frm.getPostId(), frm
				.getBelongKbn());

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);
		
		/* AECS佐藤 受注納入先区分コンボボックス追加のため 2020/01/21 */
		/* 情報取得 */
		String tantoCd = frm.getTantoCd();
		
		/* 受注納入先区分を所属マスターから取得 */
		String OrderDeliveryKbn;
		OrderDeliveryKbn = belongDetailDao.getOrderDeliveryKbn(tantoCd);
		
		if(OrderDeliveryKbn == null){
			OrderDeliveryKbn = "0";
		}
		
		BigDecimal OrderDeliveryKbn_BigDecimal = new BigDecimal(OrderDeliveryKbn);
			
		/** 検索入力：受注納入先区分 */
		frm.setOrderDeliveryKbn(OrderDeliveryKbn_BigDecimal);

		/* 退避用 */
		frm.setSavPostId(frm.getPostId());
		frm.setSavBelongKbn(frm.getBelongKbn());
		frm.setSavOrganizationCd(frm.getOrganizationCd());
		frm.setSavOrderDeliveryKbn(frm.getOrderDeliveryKbn());

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

		BelongDetailForm frm = (BelongDetailForm) form;

		if (!StringUtils.isEmpty(frm.getOrganizationCd())) {
			/* 部署コードチェック */
			OrganizationDetail beanOrganization = belongDetailLogic
					.getOrganizationEntity(frm.getOrganizationCd());

			if (beanOrganization == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.belong.organization.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getTantoCd())) {
			/* 担当者コードチェック */
			LoginDetail beanLogin = belongDetailLogic.getLoginEntity(frm
					.getTantoCd());

			if (beanLogin == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.belong.tanto.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getPostId())) {
			/* 役職コードチェック */
			PostDetail beanPost = belongDetailLogic.getPostEntity(frm
					.getPostId());

			if (beanPost == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.belong.post.id");
				return mapping.findForward("success");
			}
		}

		/* 主所属件数取得 */
		int count = belongDetailLogic.getCountMainBelong(frm.getTantoCd());

		if (frm.getNewFlg().equals("true")) {
			/* 主所属データあり かつ 主所属データ２件目登録 */
			if (0 < count && frm.getBelongKbn().equals(BELONG_KBN_MAIN)) {
				/* 主所属のデータが既に存在しています。 */
				saveError(request, "errors.exist.belong.belong.kbn");
				return mapping.getInputForward();
			}

			/* 主所属データなし かつ 兼務所属データ登録 */
			if (count == 0 && frm.getBelongKbn().equals(BELONG_KBN_KENMU)) {
				/* 主所属のデータが１件必要です。 */
				saveError(request, "errors.nodata.belong.belong.kbn");
				return mapping.getInputForward();
			}
		} else {
			if (0 < count) {
				BigDecimal savBelongKbn = frm.getSavBelongKbn();

				if (savBelongKbn == null) {
					savBelongKbn = BigDecimal.ONE; /* 主所属を初期値に設定 */
				}

				/* 主所属データを兼務所属データに変更の場合 */
				if (savBelongKbn.equals(BELONG_KBN_MAIN)
						&& frm.getBelongKbn().equals(BELONG_KBN_KENMU)) {
					/* 主所属のデータが１件必要です。 */
					saveError(request, "errors.nodata.belong.belong.kbn");
					return mapping.getInputForward();
				}

				/* 主所属データ２件目登録 */
				if (savBelongKbn.equals(BELONG_KBN_KENMU)
						&& frm.getBelongKbn().equals(BELONG_KBN_MAIN)) {
					/* 主所属のデータが既に存在しています。 */
					saveError(request, "errors.exist.belong.belong.kbn");
					return mapping.getInputForward();
				}

				/* 主所属データの所属を変更した場合 */
				// if (savBelongKbn.equals(BELONG_KBN_MAIN)
				// && frm.getBelongKbn().equals(BELONG_KBN_MAIN)
				// && !frm.getPostId().equals(frm.getSavPostId())) {
				// /* 主所属のデータが１件必要です。 */
				// saveError(request, "errors.nodata.belong.belong.kbn");
				// return mapping.getInputForward();
				// }
			}
		}

		/* 重複チェック */
		if (frm.getNewFlg().equals("true")) {
			BelongDetail beanDetail = belongDetailLogic.getDetailEntity(frm
					.getOrganizationCd(), frm.getTantoCd(), frm.getPostId(),
				frm.getBelongKbn());

			if (beanDetail != null) {
				/* エラーメッセージ */
				saveError(request, "errors.duplicate.data");
				return mapping.findForward("success");
			}
		} else {
			/* 所属か区分が変更された場合 */
			if (!frm.getPostId().equals(frm.getSavPostId())
					|| !frm.getBelongKbn().equals(frm.getSavBelongKbn())) {
				BelongDetail beanDetail = belongDetailLogic.getDetailEntity(frm
						.getOrganizationCd(), frm.getTantoCd(),
					frm.getPostId(), frm.getBelongKbn());

				if (beanDetail != null) {
					/* エラーメッセージ */
					saveError(request, "errors.duplicate.data");
					return mapping.findForward("success");
				}
			}
		}

		if (frm.getNewFlg().equals("true")) {
			/* 追加処理を実行 */
			belongDetailLogic.insert(insertBelong(frm, getLoginInfo(request)
					.getTantoCd()));
		} else {
			/* 更新処理を実行 */
			belongDetailLogic.update(updateBelong(frm, getLoginInfo(request)
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

		BelongDetailForm frm = (BelongDetailForm) form;

		/* 削除対象データが主所属の場合 */
		if (frm.getSavBelongKbn().equals(BELONG_KBN_MAIN)) {
			/* 兼務所属件数取得 */
			int count = belongDetailLogic.getCountKenmuBelong(frm.getTantoCd());

			/* 兼務所属データありの場合 */
			if (0 < count) {
				/* 兼務データが存在すると削除できません。 */
				saveError(request, "errors.notdel.belong");
				return mapping.getInputForward();
			}
		}

		/* 削除処理を実行 */
		belongDetailLogic.delete(deleteBelong(frm, getLoginInfo(request)
				.getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 追加処理用のBelongを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Belong
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private BelongDetail insertBelong(final BelongDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		BelongDetail newBean = new BelongDetail();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);
		
		/* AECS佐藤 受注納入先区分コンボボックスの値判定用 2020/01/21 */
		if(newBean.getOrderDeliveryKbn().compareTo(BigDecimal.ZERO) == 0){
			newBean.setOrderDeliveryKbn(null);
		}
		return newBean;
	}

	/**
	 * 更新処理用のBelongDetailを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return BelongDetail
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private BelongDetail updateBelong(final BelongDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		BelongDetail newBean = new BelongDetail();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);
		
		/* AECS佐藤 受注納入先区分コンボボックスの値判定用 2020/01/21 */
		if(newBean.getOrderDeliveryKbn().compareTo(BigDecimal.ZERO) == 0){
			newBean.setOrderDeliveryKbn(null);
		}
		return newBean;
	}

	/**
	 * 削除処理用のBelongDetailを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return BelongDetail
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private BelongDetail deleteBelong(final BelongDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		BelongDetail newBean = new BelongDetail();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setPostId(new BigDecimal(frm.getSavPostId()));
		newBean.setOrganizationCd(frm.getSavOrganizationCd());

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

		BelongDetailForm frm = (BelongDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_BELONG,
			Constants.TAB_ID_BELONG_DETAIL);

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

	
	/* AECS佐藤 受注納入先区分コンボボックス追加のためDaoを設定 2020/01/21 */
	/**
	 * belongDetailDaoを設定します。
	 * @param belongDetailDao belongDetailDao
	 */
	public void setBelongDetailDao(final BelongDetailDao belongDetailDao) {
		this.belongDetailDao = belongDetailDao;
	}
	
	/**
	 * belongDetailLogicを設定します。
	 * @param belongDetailLogic belongDetailLogic
	 */
	public void setBelongDetailLogic(final BelongDetailLogic belongDetailLogic) {
		this.belongDetailLogic = belongDetailLogic;
	}
}
