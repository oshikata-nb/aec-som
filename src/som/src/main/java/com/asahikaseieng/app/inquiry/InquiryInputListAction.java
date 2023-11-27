/*
 * Created on 2008/02/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inquiry;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.asahikaseieng.app.convinventory.ConvInventoryLogic;
import com.asahikaseieng.app.convinventory.ConvInventoryResult;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.inquiryinputlist.InquiryInputList;
import com.asahikaseieng.dao.nonentity.inquiryinputlist.InquiryInputListPagerCondition;
import com.asahikaseieng.dao.nonentity.inquiryinputlistforreport.InquiryInputListConditionForReport;
import com.asahikaseieng.dao.nonentity.inquiryinputlistforreport.InquiryInputListForReport;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 棚卸入力 Actionクラス.
 * @author tanaka
 */
public final class InquiryInputListAction extends AbstractSearchAction {

	private InquiryInputListLogic inquiryInputListLogic;

	private InquiryInputListExcelDecorator inquiryInputListExcelDecorator;

	private ConvInventoryLogic convInventoryLogic;

	/**
	 * コンストラクタ.
	 */
	public InquiryInputListAction() {
		super();
	}

	/**
	 * 初期処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		InquiryInputListForm frm = (InquiryInputListForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_INQUIRY_INPUT,
			Constants.TAB_ID_INQUIRY_INPUT_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		/* 棚卸区分セット */
		setCountDivisionCombobox(frm);

		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * 棚卸リスト取得
	 * @param frm 画面データ
	 */
	public void setCountDivisionCombobox(final InquiryInputListForm frm) {
		/* ラベルマスタの区分データを取得 */
		List<NamesListForComboboxes> list = inquiryInputListLogic
				.getCountDivisionList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}

		frm.setSrhCountDivisionLabels(labels);
		frm.setSrhCountDivisionValues(values);
	}

	/**
	 * 検索処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		InquiryInputListForm frm = (InquiryInputListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<InquiryInputList>());

		String link = frm.getSrhLink();

		if (StringUtils.isEmpty(link)) {
			link = "";
		}

		/* リンク先から呼ばれた場合 */
		if (link.equals("1")) {
			/* 棚卸区分セット */
			setCountDivisionCombobox(frm);

			/* ロケーション検索 */
			LocationDetail beanLocation = inquiryInputListLogic
					.getLocationEntity(frm.getSrhLocationCd());

			if (beanLocation != null) {
				frm.setSrhLocationName(beanLocation.getLocationName());
			}

			/* 品目検索 */
			ItemDetail beanItem = inquiryInputListLogic.getItemEntity(frm
					.getSrhItemCd());

			if (beanItem != null) {
				frm.setSrhItemName(beanItem.getItemName());
			}
		}

		frm.setSrhCountDate(AecDateUtils.getTimestampYmdFormat(frm
				.getStrSrhCountDate()));

		/* 検索条件を取得 */
		InquiryInputListPagerCondition condition = (InquiryInputListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhCountDate(frm.getSrhCountDate());
		condition.setSrhLocationCd(frm.getSrhLocationCd());
		condition.setSrhCountDivision(frm.getSrhCountDivision());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());
		condition.setSrhAliasLotNo(frm.getSrhAliasLotNo());

		/* 帳票(Excel)用に検索条件を保持 */
		InquiryInputListConditionForReport reportCondition = new InquiryInputListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(inquiryInputListLogic.getList(condition));

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 数値文字列に変換 */
			frm.getSearchList().get(i).setStrCountQty(
				checker.format(frm.getSearchList().get(i)
						.getUnitOfOperationManagement(), frm.getSearchList()
						.get(i).getCountQty()));
			frm.getSearchList().get(i).setStrFractionQty(
				checker.format(frm.getSearchList().get(i)
						.getUnitOfFractionManagement(), frm.getSearchList()
						.get(i).getFractionQty()));
			frm.getSearchList().get(i).setStrInputQty(
				checker.format(frm.getSearchList().get(i)
						.getUnitOfOperationManagement(), frm.getSearchList()
						.get(i).getInputQty()));
			frm.getSearchList().get(i).setStrInputfraction(
				checker.format(frm.getSearchList().get(i)
						.getUnitOfFractionManagement(), frm.getSearchList()
						.get(i).getInputfraction()));

			/* リンクフラグセット */
			frm.getSearchList().get(i).setSrhLink(frm.getSrhLink());
		}

		return mapping.findForward("success");
	}

	/**
	 * クリア処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward clear(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("clear.");
		}

		InquiryInputListForm frm = (InquiryInputListForm) form;

		/* クリア */
		frm.clear();

		/* 棚卸区分セット */
		setCountDivisionCombobox(frm);

		/* 初期画面へ */
		return mapping.findForward("success");
	}

	/**
	 * EXCEL作成処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		InquiryInputListForm frm = (InquiryInputListForm) form;

		/* 明細取得 */
		List<InquiryInputListForReport> list = inquiryInputListLogic
				.getListForReport(frm.getReportCondition());

		/* Excel作成 */
		FileDownloadInfo info = inquiryInputListExcelDecorator.createReport(
			list, getLoginInfo(request).getTantoNm(), AecDateUtils
					.getCurrentTimestamp());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

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
			getLog().debug("update.");
		}

		InquiryInputListForm frm = (InquiryInputListForm) form;

		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 文字--->数値に変換 */
			BigDecimal inputQty = AecNumberUtils.convertBigDecimal(frm
					.getSearchList().get(i).getStrInputQty()); /* 荷姿数 */
			BigDecimal inputfraction = AecNumberUtils.convertBigDecimal(frm
					.getSearchList().get(i).getStrInputfraction()); /* 端数 */

			/* null--->ゼロに変換 */
			inputQty = AecNumberUtils.convertNullToZero(inputQty);
			inputfraction = AecNumberUtils.convertNullToZero(inputfraction);

			/* 数値桁数チェック部品呼び出し */
			// CheckDigitUtilsLogic check = CheckDigitUtil
			// .getCheckDigitUtils(request);
			//
			// /* 演算結果を丸める */
			// if (!StringUtils.isEmpty(frm.getSearchList().get(i)
			// .getUnitOfOperationManagement())) {
			// inputQty = check.round(frm.getSearchList().get(i)
			// .getUnitOfOperationManagement(), null, null, inputQty);
			// }
			//
			// if (!StringUtils.isEmpty(frm.getSearchList().get(i)
			// .getUnitOfFractionManagement())) {
			// inputfraction = check.round(frm.getSearchList().get(i)
			// .getUnitOfOperationManagement(), null, null,
			// inputfraction);
			// }
			/* 端数入力チェック */
			ConvInventoryResult resultFraction = convInventoryLogic
					.checkInputFraction(frm.getSearchList().get(i).getItemCd(),
						inputfraction);

			if (resultFraction.getCode().equals(new BigDecimal("2"))) {
				/* エラーメッセージ */
				saveErrorWithArgs(request,
					"errors.conv.inventory.input.fraction.row", i + 1);
				return mapping.findForward("success");
			}

			/* 在庫数量計算 */
			ConvInventoryResult result = convInventoryLogic.packToInventory(frm
					.getSearchList().get(i).getItemCd(), inputQty,
				inputfraction);

			if (result.getCode().equals(new BigDecimal("1"))) {
				/* エラーメッセージ */
				saveErrorWithArgs(request, "errors.conv.inventory.calc.row",
					i + 1);
				return mapping.findForward("success");
			}

			frm.getSearchList().get(i).setInputQty(inputQty);
			frm.getSearchList().get(i).setInputfraction(inputfraction);
		}

		/* 更新処理を実行 */
		inquiryInputListLogic.update(frm, getLoginInfo(request).getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");

	}

	/* -------------------- setter -------------------- */

	/**
	 * inquiryInputListExcelDecoratorを設定します。
	 * @param inquiryInputListExcelDecorator inquiryInputListExcelDecorator
	 */
	public void setInquiryInputListExcelDecorator(
			final InquiryInputListExcelDecorator inquiryInputListExcelDecorator) {
		this.inquiryInputListExcelDecorator = inquiryInputListExcelDecorator;
	}

	/**
	 * inquiryInputListLogicを設定します。
	 * @param inquiryInputListLogic inquiryInputListLogic
	 */
	public void setInquiryInputListLogic(
			final InquiryInputListLogic inquiryInputListLogic) {
		this.inquiryInputListLogic = inquiryInputListLogic;
	}

	/**
	 * convInventoryLogicを設定します。
	 * @param convInventoryLogic convInventoryLogic
	 */
	public void setConvInventoryLogic(
			final ConvInventoryLogic convInventoryLogic) {
		this.convInventoryLogic = convInventoryLogic;
	}
}
