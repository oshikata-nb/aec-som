/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.unitprice;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.master.unitprice.Unitprice;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.unitpricedetaillist.UnitpriceDetailList;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 仕入先別単価詳細 Actionクラス.
 * @author t0011036
 */
public final class UnitpriceDetailAction extends AbstractAction {

	private UnitpriceDetailLogic unitpriceDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public UnitpriceDetailAction() {
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

		UnitpriceDetailForm frm = (UnitpriceDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_UNITPRICE,
			Constants.TAB_ID_UNITPRICE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		List<UnitpriceDetailList> list = unitpriceDetailLogic.getList(frm
				.getVenderDivision(), frm.getVenderCd(), frm.getItemCd(), frm
				.getVersion());

		/* BeanをFormにコピーする */
		frm.setSearchUnitpriceDetailList(list);

		frm.setVenderName1(frm.getSearchUnitpriceDetailList().get(0)
				.getVenderName1());
		frm
				.setItemName(frm.getSearchUnitpriceDetailList().get(0)
						.getItemName());
		frm.setOtherCompanyCd1(frm.getSearchUnitpriceDetailList().get(0)
				.getOtherCompanyCd1());
		frm.setStyleOfPacking(frm.getSearchUnitpriceDetailList().get(0)
				.getStyleOfPacking());
		frm.setMaterialMakerName(frm.getSearchUnitpriceDetailList().get(0)
				.getMaterialMakerName());
		frm.setUnitpriceDivision(frm.getSearchUnitpriceDetailList().get(0)
				.getUnitpriceDivision());
		frm.setValidDate(frm.getSearchUnitpriceDetailList().get(0)
				.getValidDate());
		frm.setStrValidDate(AecDateUtils.dateFormat(frm
				.getSearchUnitpriceDetailList().get(0).getValidDate(),
			"yyyy/MM/dd"));
		frm.setRemarks(frm.getSearchUnitpriceDetailList().get(0).getRemarks());
		frm.setUnitOfOperationManagement(frm.getSearchUnitpriceDetailList()
				.get(0).getUnitOfOperationManagement());
		frm.setUnitDivision(frm.getSearchUnitpriceDetailList().get(0)
				.getUnitDivisionSitanka());
		frm.setRemarks(frm.getSearchUnitpriceDetailList().get(0).getRemarks());
		frm.setSmallnumLength(frm.getSearchUnitpriceDetailList().get(0)
				.getSmallnumLength());
		frm.setRoundDivision(frm.getSearchUnitpriceDetailList().get(0)
				.getRoundDivision());

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < frm.getSearchUnitpriceDetailList().size(); i++) {
			/* 数値文字列に変換 */
			frm.getSearchUnitpriceDetailList().get(i).setStrQuantityFrom(
				checker.format(frm.getSearchUnitpriceDetailList().get(i)
						.getUnitOfOperationManagement(), frm
						.getSearchUnitpriceDetailList().get(i)
						.getQuantityFrom()));
			frm.getSearchUnitpriceDetailList().get(i)
					.setStrQuantityTo(
						checker.format(frm.getSearchUnitpriceDetailList()
								.get(i).getUnitOfOperationManagement(), frm
								.getSearchUnitpriceDetailList().get(i)
								.getQuantityTo()));
			frm.getSearchUnitpriceDetailList().get(i).setStrUnitprice(
				checker.format(frm.getSearchUnitpriceDetailList().get(i)
						.getUnitDivisionSitanka(), frm
						.getSearchUnitpriceDetailList().get(i).getUnitprice()));
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

		UnitpriceDetailForm frm = (UnitpriceDetailForm) form;

		if (!StringUtils.isEmpty(frm.getVenderCd())) {
			/* 仕入先コードチェック */
			VenderDetail beanVender = unitpriceDetailLogic.getVenderEntity(frm
					.getVenderDivision(), frm.getVenderCd());

			if (beanVender == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.unitprice.vender.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getItemCd())) {
			/* 品目コードチェック */
			ItemQueueLastVersion beanItemQueue = unitpriceDetailLogic
					.getItemQueueEntity(frm.getItemCd());

			if (beanItemQueue == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.unitprice.item.cd");
				return mapping.findForward("success");
			}
		}

		if (frm.getSearchUnitpriceDetailList().size() == 0) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.unitprice.unit.price");
			return mapping.findForward("success");
		}

		for (int i = 0; i < frm.getSearchUnitpriceDetailList().size(); i++) {
			BigDecimal quantityFrom = AecNumberUtils
					.convertBigDecimal(frm.getSearchUnitpriceDetailList()
							.get(i).getStrQuantityFrom());
			BigDecimal quantityTo = AecNumberUtils.convertBigDecimal(frm
					.getSearchUnitpriceDetailList().get(i).getStrQuantityTo());

			/* 数量の大小チェック */
			if (0 <= quantityFrom.compareTo(quantityTo)) {
				/* エラーメッセージ */
				saveErrorWithArgs(request, "errors.compare.row",
					"unitprice.quantity.from", "unitprice.quantity.to", i + 1);
				return mapping.findForward("success");
			}
		}

		frm.setValidDate(AecDateUtils.getTimestampYmdFormat(frm
				.getStrValidDate()));

		for (int i = 0; i < frm.getSearchUnitpriceDetailList().size(); i++) {
			frm.getSearchUnitpriceDetailList().get(i).setQuantityFrom(
				AecNumberUtils.convertBigDecimal(frm
						.getSearchUnitpriceDetailList().get(i)
						.getStrQuantityFrom()));
			frm.getSearchUnitpriceDetailList().get(i).setQuantityTo(
				AecNumberUtils.convertBigDecimal(frm
						.getSearchUnitpriceDetailList().get(i)
						.getStrQuantityTo()));
			frm.getSearchUnitpriceDetailList().get(i).setUnitprice(
				AecNumberUtils.convertBigDecimal(frm
						.getSearchUnitpriceDetailList().get(i)
						.getStrUnitprice()));
		}

		/* 新規登録の場合は登録チェックを行う */
		if (frm.getNewFlg().equals("true")) {
			Unitprice bean = unitpriceDetailLogic.getEntity(frm
					.getVenderDivision(), frm.getVenderCd(), frm.getItemCd(),
				frm.getVersion(), new BigDecimal("1"));

			if (bean != null) {
				/* エラーメッセージ */
				saveError(request, "errors.duplicate.data");
				return mapping.findForward("success");
			}
		}

		/* 登録処理を実行 */
		unitpriceDetailLogic.insert(frm, getLoginInfo(request).getTantoCd());

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

		UnitpriceDetailForm frm = (UnitpriceDetailForm) form;

		/* 一括削除処理を実行 */
		unitpriceDetailLogic.deleteUnitpriceList(frm.getVenderDivision(), frm
				.getVenderCd(), frm.getItemCd(), frm.getVersion());

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

		UnitpriceDetailForm frm = (UnitpriceDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_UNITPRICE,
			Constants.TAB_ID_UNITPRICE_DETAIL);

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

		UnitpriceDetailForm frm = (UnitpriceDetailForm) form;

		if (0 < frm.getSearchUnitpriceDetailList().size()) {
			/* 最終行の数量(TO)が未入力の場合は行追加できない */
			if (StringUtils.isEmpty(frm.getSearchUnitpriceDetailList().get(
				frm.getSearchUnitpriceDetailList().size() - 1)
					.getStrQuantityTo())) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.unitprice.quantity.to");
				return mapping.findForward("success");
			}

			/* 最終行の単価が未入力の場合は行追加できない */
			if (StringUtils.isEmpty(frm.getSearchUnitpriceDetailList().get(
				frm.getSearchUnitpriceDetailList().size() - 1)
					.getStrUnitprice())) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.unitprice.unitprice");
				return mapping.findForward("success");
			}

			BigDecimal quantityFrom = AecNumberUtils.convertBigDecimal(frm
					.getSearchUnitpriceDetailList().get(
						frm.getSearchUnitpriceDetailList().size() - 1)
					.getStrQuantityFrom());
			BigDecimal quantityTo = AecNumberUtils.convertBigDecimal(frm
					.getSearchUnitpriceDetailList().get(
						frm.getSearchUnitpriceDetailList().size() - 1)
					.getStrQuantityTo());

			/* 数量の大小チェック */
			if (0 <= quantityFrom.compareTo(quantityTo)) {
				/* エラーメッセージ */
				saveErrorWithArgs(request, "errors.compare",
					"unitprice.quantity.from", "unitprice.quantity.to");
				return mapping.findForward("success");
			}
		}

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		/* 最終行へ追加 */
		UnitpriceDetailList bean = new UnitpriceDetailList();
		frm.getSearchUnitpriceDetailList().add(bean);
		frm.getSearchUnitpriceDetailList().get(
			frm.getSearchUnitpriceDetailList().size() - 1).setConsecutiveNo(
			new BigDecimal(frm.getSearchUnitpriceDetailList().size()));

		frm.getSearchUnitpriceDetailList().get(
			frm.getSearchUnitpriceDetailList().size() - 1).setQuantityFrom(
			new BigDecimal("0"));
		frm.getSearchUnitpriceDetailList().get(
			frm.getSearchUnitpriceDetailList().size() - 1).setQuantityTo(
			new BigDecimal("0"));
		frm.getSearchUnitpriceDetailList().get(
			frm.getSearchUnitpriceDetailList().size() - 1).setUnitprice(
			new BigDecimal("0"));
		frm.getSearchUnitpriceDetailList().get(
			frm.getSearchUnitpriceDetailList().size() - 1)
				.setUnitDivisionSitanka(frm.getUnitDivision());
		frm.getSearchUnitpriceDetailList().get(
			frm.getSearchUnitpriceDetailList().size() - 1)
				.setUnitOfOperationManagement(
					frm.getUnitOfOperationManagement());
		frm.getSearchUnitpriceDetailList().get(
			frm.getSearchUnitpriceDetailList().size() - 1).setSmallnumLength(
			frm.getSmallnumLength());
		frm.getSearchUnitpriceDetailList().get(
			frm.getSearchUnitpriceDetailList().size() - 1).setRoundDivision(
			frm.getRoundDivision());

		if (frm.getSearchUnitpriceDetailList().size() == 1) {
			frm.getSearchUnitpriceDetailList().get(
				frm.getSearchUnitpriceDetailList().size() - 1)
					.setStrQuantityFrom(
						checker.format(frm.getUnitOfOperationManagement(),
							new BigDecimal("0")));
		} else {
			frm
					.getSearchUnitpriceDetailList()
					.get(frm.getSearchUnitpriceDetailList().size() - 1)
					.setStrQuantityFrom(
						checker
								.format(
									frm.getUnitOfOperationManagement(),
									AecNumberUtils
											.convertBigDecimal(frm
													.getSearchUnitpriceDetailList()
													.get(
														frm
																.getSearchUnitpriceDetailList()
																.size() - 2)
													.getStrQuantityTo())));
		}

		if (frm.getUnitpriceDivision().equals("1")) {
			frm.getSearchUnitpriceDetailList().get(
				frm.getSearchUnitpriceDetailList().size() - 1)
					.setUnitpriceDivisionName("円/個");
		} else if (frm.getUnitpriceDivision().equals("2")) {
			frm.getSearchUnitpriceDetailList().get(
				frm.getSearchUnitpriceDetailList().size() - 1)
					.setUnitpriceDivisionName("円/Kg");
		}

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

		UnitpriceDetailForm frm = (UnitpriceDetailForm) form;

		if (0 < frm.getSearchUnitpriceDetailList().size()) {
			frm.getSearchUnitpriceDetailList().remove(
				frm.getSearchUnitpriceDetailList().size() - 1);
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * unitpriceDetailLogicを設定します。
	 * @param unitpriceDetailLogic unitpriceDetailLogic
	 */
	public void setUnitpriceDetailLogic(
			final UnitpriceDetailLogic unitpriceDetailLogic) {
		this.unitpriceDetailLogic = unitpriceDetailLogic;
	}
}
