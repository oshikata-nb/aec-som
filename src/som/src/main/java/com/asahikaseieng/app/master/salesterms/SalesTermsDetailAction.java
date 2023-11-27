/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salesterms;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.carry.Carry;
import com.asahikaseieng.dao.entity.master.carry.CarryDao;
import com.asahikaseieng.dao.entity.master.delivery.Delivery;
import com.asahikaseieng.dao.entity.master.delivery.DeliveryDao;
import com.asahikaseieng.dao.nonentity.master.balancedetail.BalanceDetail;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.salestermsdetaillist.SalesTermsDetailList;
import com.asahikaseieng.dao.nonentity.master.salestermsduplicatelist.SalesTermsDuplicateList;
import com.asahikaseieng.dao.nonentity.master.salestermslastseq.SalesTermsLastSeq;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 販売条件詳細 Actionクラス.
 * @author t0011036
 */
public final class SalesTermsDetailAction extends AbstractAction {

	private SalesTermsDetailLogic salesTermsDetailLogic;

	private DeliveryDao deliveryDao;

	private CarryDao carryDao;

	/**
	 * コンストラクタ.
	 */
	public SalesTermsDetailAction() {
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

		SalesTermsDetailForm frm = (SalesTermsDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_SALES_TERMS,
			Constants.TAB_ID_SALES_TERMS_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		List<SalesTermsDetailList> bean = salesTermsDetailLogic.getList(frm
				.getDeliveryCd(), frm.getBalanceCd());

		/* BeanをFormにコピーする */
		frm.setSearchSalesTermsDetailList(bean);

		frm.setDeliveryCd(frm.getSearchSalesTermsDetailList().get(0)
				.getDeliveryCd());
		frm.setDeliveryName1(frm.getSearchSalesTermsDetailList().get(0)
				.getDeliveryName1());
		frm.setBalanceCd(frm.getSearchSalesTermsDetailList().get(0)
				.getBalanceCd());
		frm.setBalanceType(frm.getSearchSalesTermsDetailList().get(0)
				.getBalanceType());
		frm.setBalanceTypeName(frm.getSearchSalesTermsDetailList().get(0)
				.getBalanceTypeName());
		frm.setVenderCd(frm.getSearchSalesTermsDetailList().get(0)
				.getVenderCd());
		frm.setVenderName1(frm.getSearchSalesTermsDetailList().get(0)
				.getVenderName1());
		frm.setTantoCd(frm.getSearchSalesTermsDetailList().get(0).getTantoCd());
		frm.setTantoNm(frm.getSearchSalesTermsDetailList().get(0).getTantoNm());

		// 2015/08/18 画面に項目を追加
		frm.setLeadTime(frm.getSearchSalesTermsDetailList().get(0)
				.getLeadTime());
		frm.setDeliveryTime(frm.getSearchSalesTermsDetailList().get(0)
				.getDeliveryTime());
		frm.setCarryName(frm.getSearchSalesTermsDetailList().get(0)
				.getCarryName());

		/* 明細取得 */
		frm.setSearchSalesTermsBalanceList(salesTermsDetailLogic
				.getBalanceList(frm.getBalanceCd()));

		// 備考取得
		frm.setRemark(this.getRemark(frm));

		// 返信時備考を取得
		frm.setOrderAutoRemark(this.getRemarkRe(frm));

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
		/* formをPurchaseDetailFormにキャスト */
		SalesTermsDetailForm frm = (SalesTermsDetailForm) form;

		// 受注時備考を取得
		try {
			// 備考の検索
			frm.setRemark(salesTermsDetailLogic.getRemarkList(frm));
		} catch (NoDataException ex) {
			// 画面上部にメッセージセット
			saveMessage(request, "message.order.getremark.nodata");
			return mapping.findForward("success");
		}

		// 受注時返信時備考を取得
		try {
			// 備考の検索
			frm.setOrderAutoRemark(salesTermsDetailLogic.getRemarkListRe(frm));
		} catch (NoDataException ex) {
			// 画面上部にメッセージセット
			saveMessage(request, "message.order.getremark.nodata");
			return mapping.findForward("success");
		}

		// 画面上部にメッセージセット
		saveMessage(request, "message.order.getremark.found");

		return mapping.findForward("success");
	}

	/**
	 * 備考検索処理(詳細画面の備考取得ボタン押下時)
	 * 
	 * @param form ActionForm
	 * @return 備考
	 */
	private String getRemark(final SalesTermsDetailForm frm) {
		try {
			// 備考の検索
			return salesTermsDetailLogic.getRemarkList(frm);
		} catch (NoDataException ex) {
			return "";
		}
	}

	/**
	 * 返信時備考検索処理(詳細画面の備考取得ボタン押下時)
	 * 
	 * @param form ActionForm
	 * @return 備考
	 */
	private String getRemarkRe(final SalesTermsDetailForm frm) {
		try {
			// 備考の検索
			return salesTermsDetailLogic.getRemarkListRe(frm);
		} catch (NoDataException ex) {
			return "";
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward getBalance(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("getBalance.");
		}

		SalesTermsDetailForm frm = (SalesTermsDetailForm) form;

		/* 明細取得 */
		frm.setSearchSalesTermsBalanceList(salesTermsDetailLogic
				.getBalanceList(frm.getBalanceCd()));

		if (frm.getDeliveryCd() != null) {
			Delivery delivery = deliveryDao.getEntity(frm.getDeliveryCd());

			if (delivery != null) {
				if (delivery.getLeadTime() != null) {
					frm.setLeadTime(delivery.getLeadTime().toString());
				}
				frm.setDeliveryTime(delivery.getDeliveryTime());

				Carry carry = carryDao.getEntity(delivery.getCarryCd());
				if (carry != null) {
					String carryName = null;
					if (carry.getCarryName1() != null) {
						carryName = carry.getCarryName1();
					}
					if (carry.getCarryName2() != null) {
						carryName += carry.getCarryName2();
					}
					frm.setCarryName(carryName);
				}
			}
		}

		// 備考取得処理
		this.getRemarkList(mapping, form, request, response);

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

		SalesTermsDetailForm frm = (SalesTermsDetailForm) form;

		if (!StringUtils.isEmpty(frm.getDeliveryCd())) {
			/* 納入先コードチェック */
			DeliveryDetail beanDelivery = salesTermsDetailLogic
					.getDeliveryEntity(frm.getDeliveryCd());

			if (beanDelivery == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.sales.terms.delivery.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getBalanceCd())) {
			/* 帳合コードチェック */
			BalanceDetail beanBalance = salesTermsDetailLogic
					.getBalanceEntity(frm.getBalanceCd());

			if (beanBalance == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.sales.terms.balance.cd");
				return mapping.findForward("success");
			}
		}

		if (frm.getSearchSalesTermsDetailList().size() == 0) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.sales.terms.item.cd");
			return mapping.findForward("success");
		}

		for (int i = 0; i < frm.getSearchSalesTermsDetailList().size(); i++) {
			if (!StringUtils.isEmpty(frm.getSearchSalesTermsDetailList().get(i)
					.getItemCd())) {
				/* 品目コードチェック */
				ItemQueueLastVersion beanItemQueue = salesTermsDetailLogic
						.getItemQueueEntity(frm.getSearchSalesTermsDetailList()
								.get(i).getItemCd());

				if (beanItemQueue == null) {
					/* エラーメッセージ */
					saveErrorWithArgs(request,
						"errors.nodata.sales.terms.item.cd.row", i + 1);
					return mapping.findForward("success");
				}
			}

			/* 納入先＆品目重複チェック */
			List<SalesTermsDuplicateList> listDuplicate = salesTermsDetailLogic
					.getDuplicate(frm.getDeliveryCd(), frm.getBalanceCd(), frm
							.getSearchSalesTermsDetailList().get(i).getItemCd());

			if (0 < listDuplicate.size()) {
				/* エラーメッセージ */
				saveErrorWithArgs(request, "errors.sales.terms.duplicate.row",
					i + 1);
				return mapping.findForward("success");
			}

			int cnt = 0;

			/* 重複品目数をカウント */
			for (int j = 0; j < frm.getSearchSalesTermsDetailList().size(); j++) {
				if (frm.getSearchSalesTermsDetailList().get(i).getItemCd()
						.endsWith(
							frm.getSearchSalesTermsDetailList().get(j)
									.getItemCd())) {
					cnt += 1;
				}
			}

			/* 納入先＆帳合＆品目重複チェック */
			List<SalesTermsDuplicateList> listDuplicateAll = salesTermsDetailLogic
					.getDuplicateAll(frm.getDeliveryCd(), frm.getBalanceCd(),
						frm.getSearchSalesTermsDetailList().get(i).getItemCd());

			if (StringUtils.isEmpty(frm.getNewFlg())) {
				if (1 < cnt || 1 < listDuplicateAll.size()) {
					/* エラーメッセージ */
					saveErrorWithArgs(request,
						"errors.sales.terms.duplicateall.row", i + 1);
					return mapping.findForward("success");
				}
			} else {
				if (1 < cnt || 1 <= listDuplicateAll.size()) {
					/* エラーメッセージ */
					saveErrorWithArgs(request,
						"errors.sales.terms.duplicateall.row", i + 1);
					return mapping.findForward("success");
				}
			}
		}

		/* 納入先&帳合重複チェック */
		if (frm.getNewFlg().equals("true")) {
			List<SalesTermsDetailList> list = salesTermsDetailLogic.getList(frm
					.getDeliveryCd(), frm.getBalanceCd());

			if (0 < list.size()) {
				/* 納入先&帳合で最終行を取得 */
				SalesTermsLastSeq bean = salesTermsDetailLogic.getLastSeq(frm
						.getDeliveryCd(), frm.getBalanceCd());

				BigDecimal lastSeq = new BigDecimal("1");

				if (bean != null) {
					lastSeq = bean.getLastSeq();
				}

				/* 追加登録処理を実行 */
				salesTermsDetailLogic.insert(frm, lastSeq,
					getLoginInfo(request).getTantoCd());
			} else {
				/* 登録処理を実行 */
				salesTermsDetailLogic.regist(frm, getLoginInfo(request)
						.getTantoCd());
			}
		} else {
			/* 登録処理を実行 */
			salesTermsDetailLogic.regist(frm, getLoginInfo(request)
					.getTantoCd());
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

		SalesTermsDetailForm frm = (SalesTermsDetailForm) form;

		/* 削除処理 */
		salesTermsDetailLogic.deleteSalesTermsList(frm.getDeliveryCd(), frm
				.getBalanceCd());

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

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

		SalesTermsDetailForm frm = (SalesTermsDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_SALES_TERMS,
			Constants.TAB_ID_SALES_TERMS_DETAIL);

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

		SalesTermsDetailForm frm = (SalesTermsDetailForm) form;

		Boolean isAddList = false;

		for (int i = 0; i < frm.getSearchSalesTermsDetailList().size(); i++) {
			if (frm.getSearchSalesTermsDetailList().get(i).getChecked()) {
				SalesTermsDetailList bean = new SalesTermsDetailList();
				frm.getSearchSalesTermsDetailList().add(i, bean);
				frm.getSearchSalesTermsDetailList().get(i).setChecked(false);
				isAddList = true;
				break;
			}
		}

		/* 最終行へ追加 */
		if (!isAddList) {
			SalesTermsDetailList bean = new SalesTermsDetailList();
			frm.getSearchSalesTermsDetailList().add(bean);
			frm.getSearchSalesTermsDetailList().get(
				frm.getSearchSalesTermsDetailList().size() - 1).setChecked(
				false);
		}

		/* 行番を振り直し */
		setSeq(frm);

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

		SalesTermsDetailForm frm = (SalesTermsDetailForm) form;

		for (int i = frm.getSearchSalesTermsDetailList().size() - 1; i >= 0; i--) {
			if (frm.getSearchSalesTermsDetailList().get(i).getChecked()) {
				frm.getSearchSalesTermsDetailList().remove(i);
			}
		}

		/* 行番を振り直し */
		setSeq(frm);

		return mapping.findForward("success");
	}

	/**
	 * 行番を振り直し
	 * @param frm 画面データ
	 */
	private void setSeq(final SalesTermsDetailForm frm) {
		/* 行番を振り直し */
		for (int i = 0; i < frm.getSearchSalesTermsDetailList().size(); i++) {
			frm.getSearchSalesTermsDetailList().get(i).setSeq(
				new BigDecimal(i + 1));
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * salesTermsDetailLogicを設定します。
	 * @param salesTermsDetailLogic salesTermsDetailLogic
	 */
	public void setSalesTermsDetailLogic(
			final SalesTermsDetailLogic salesTermsDetailLogic) {
		this.salesTermsDetailLogic = salesTermsDetailLogic;
	}

	/**
	 * carryDaoを設定します。
	 * @param carryDao carryDao
	 */
	public void setCarryDao(final CarryDao carryDao) {
		this.carryDao = carryDao;
	}

	/**
	 * deliveryDaoを設定します。
	 * @param deliveryDao deliveryDao
	 */
	public void setDeliveryDao(final DeliveryDao deliveryDao) {
		this.deliveryDao = deliveryDao;
	}
}
