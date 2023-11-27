/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.arrival;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;

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
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.arrival.ArrivalDetailList;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 入荷入力詳細 Actionクラス.
 * @author tosco
 */
public final class ArrivalDetailAction extends AbstractAction {

	/** 入荷入力詳細ロジッククラス */
	private ArrivalDetailLogic arrivalDetailLogic;

	/** 製品ラベルＥＸＣＥＬファイル作成ロジッククラス */
	private ArrivalListExcelDecorator arrivalListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public ArrivalDetailAction() {
		super();
	}

	/**
	 * 検索処理(一覧画面の分納ボタン押下時)
	 * 
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

		ArrivalDetailForm frm = (ArrivalDetailForm) form;
		frm.setDirtyFlg(null);

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ARRIVAL,
			Constants.TAB_ID_ARRIVAL_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		try {
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);

			/* 初期検索 */
			List<ArrivalDetailList> beanList = arrivalDetailLogic.getEntity(frm
					.getPurchaseNo(), null, check);

			/* 1件目の共通項目をFormにコピー */
			ArrivalDetailList bean = beanList.get(0);

			/* 排他制御用更新日付を保持 */
			frm.setUpdateDate(bean.getUpdateDate());

			IgnoreCaseBeanUtils.copyProperties(frm, bean);
			if (bean.getLabelDate() == null) {
				frm.setLabelHakko("0");
			} else {
				frm.setLabelHakko("1");
			}

			// 外注製品（非直送）仕入在庫品を区別
			String nyukaFlg = "0";
			if (ArrivalListLogicImpl.ORDER_DIVISION3.equals(bean
					.getOrderDivision())
					|| ArrivalListLogicImpl.ORDER_DIVISION6.equals(bean
							.getOrderDivision())) {
				nyukaFlg = "2";
			}
			frm.setNyukaFlg(nyukaFlg);
			// 行番号付け直し
			for (int i = 0; i < beanList.size(); i++) {
				ArrivalDetailList bean2 = beanList.get(i);
				int rowNo = i + 1;
				if (nyukaFlg.equals("2")) {
					rowNo = rowNo * 1000 + 1;
				}
				bean2.setRowNo(new BigDecimal(rowNo));
				bean2.setStrRowNo(Integer.toString(rowNo));
			}
			// ロット分割データセット
			frm.setDetailList(beanList);

			// 入荷予定数量の合計値取得
			BigDecimal sumArrivalQty = arrivalDetailLogic.getSumArrivalQty(frm
					.getBuySubcontractOrderNo(), frm.getSlipNo());
			frm.setSumArrivalQuantity(sumArrivalQty.toString());

			// javascriptでの桁数丸め用に必要となる値取得
			getCheckDigit(frm, check, bean);

		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");

	}

	/**
	 * 再検索処理(詳細画面の登録ボタン押下時)
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward search(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		ArrivalDetailForm frm = (ArrivalDetailForm) form;
		frm.setDirtyFlg(null);
		try {
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);

			/* 初期検索 */
			List<ArrivalDetailList> beanList = arrivalDetailLogic.getEntity(
				null, frm.getSlipNo(), check);

			/* 1件目の共通項目をFormにコピー */
			ArrivalDetailList bean = beanList.get(0);
			IgnoreCaseBeanUtils.copyProperties(frm, bean);
			if (bean.getLabelDate() == null) {
				frm.setLabelHakko("0");
			} else {
				frm.setLabelHakko("1");
			}

			// 次回納品希望日を再設定
			frm.setNextDeliverlimitDate(AecDateUtils.dateFormat(bean
					.getNextDeliverlimitDate(), "yyyy/MM/dd"));
			String tmptime = AecDateUtils.dateFormat(bean
					.getNextDeliverlimitDate(), "HH:mm");
			if (StringUtils.isEmpty(tmptime) || tmptime.equals("00:00")) {
				frm.setNextDeliverlimitDateTime(null);
			} else {
				frm.setNextDeliverlimitDateTime(tmptime);
			}
			// 外注製品（非直送）仕入在庫品を区別
			String nyukaFlg = "0";
			if (ArrivalListLogicImpl.ORDER_DIVISION3.equals(bean
					.getOrderDivision())
					|| ArrivalListLogicImpl.ORDER_DIVISION6.equals(bean
							.getOrderDivision())) {
				nyukaFlg = "2";
			}
			frm.setNyukaFlg(nyukaFlg);
			// 行番号付け直し
			List<ArrivalDetailList> oBeanList = frm.getDetailList();
			for (int i = 0; i < beanList.size(); i++) {
				if (oBeanList.size() > i) {
					ArrivalDetailList oBean = oBeanList.get(i);
					if (oBean != null) {
						String labelCount = frm.getDetailList().get(i)
								.getLabelCount();
						beanList.get(i).setLabelCount(labelCount);
					}
				}
				ArrivalDetailList bean2 = beanList.get(i);
				int rowNo = i + 1;
				if (nyukaFlg.equals("2")) {
					rowNo = rowNo * 1000 + 1;
				}
				bean2.setRowNo(new BigDecimal(rowNo));
				bean2.setStrRowNo(Integer.toString(rowNo));
			}

			// ロット分割データセット
			frm.setDetailList(beanList);

			// 入荷予定数量の合計値取得
			BigDecimal sumArrivalQty = arrivalDetailLogic.getSumArrivalQty(frm
					.getBuySubcontractOrderNo(), frm.getSlipNo());
			if (sumArrivalQty == null) {
				sumArrivalQty = BigDecimal.ZERO;
			}
			frm.setSumArrivalQuantity(sumArrivalQty.toString());

			// javascriptでの桁数丸め用に必要となる値取得
			getCheckDigit(frm, check, bean);

		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

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

		ArrivalDetailForm frm = (ArrivalDetailForm) form;
		List<ArrivalDetailList> detailList = frm.getDetailList();

		// リスト追加ループ
		for (int i = 0; i < detailList.size(); i++) {
			ArrivalDetailList detailBean = detailList.get(i);
			// チェックボックスがチェックされていた場合
			if (detailBean.isCheckFlg()) {
				// 新しい要素を追加
				detailList.add(i, getNewLine(detailList.get(0), i + 1));
				break;
			}

			// チェックがない場合最後尾に追加
			if (i == detailList.size() - 1) {
				// 新しい要素を追加
				detailList.add(getNewLine(detailList.get(0), i + 1));
				break;
			}
		}

		// 追加データの後の行番号を設定
		for (int i = 0; i < detailList.size(); i++) {
			ArrivalDetailList bean2 = detailList.get(i);
			int rowNo = i + 1;
			if (frm.getNyukaFlg().equals("2")) {
				rowNo = rowNo * 1000 + 1;
			}
			bean2.setCheckFlg(false);
			bean2.setRowNo(new BigDecimal(rowNo));
			bean2.setStrRowNo(Integer.toString(rowNo));
		}
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

		ArrivalDetailForm frm = (ArrivalDetailForm) form;
		List<ArrivalDetailList> detailList = frm.getDetailList();
		ArrivalDetailList firstBean = detailList.get(0); // １件目データ
		int len = detailList.size();

		for (int i = len - 1; i >= 0; i--) {
			ArrivalDetailList bean = detailList.get(i);
			if (bean.isCheckFlg()) {
				// 削除対象行
				detailList.remove(i);
			}
		}
		if (detailList.size() <= 0) {
			// 明細行が空になった場合は、新規行を1行追加する
			detailList.add(getNewLine(firstBean, 1));
		} else {
			// 行番号振りなおし
			int index = 1;
			for (ArrivalDetailList bean : detailList) {
				int rowNo = index;
				if (frm.getNyukaFlg().equals("2")) {
					rowNo = index * 1000 + 1;
				}
				bean.setRowNo(new BigDecimal(rowNo)); // 行番号
				bean.setStrRowNo(Integer.toString(rowNo)); // 行番号
				index++;
			}
		}

		return mapping.findForward("success");
	}

	/**
	 * 更新処理(詳細画面の更新ボタン押下時)
	 * 
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

		ArrivalDetailForm frm = (ArrivalDetailForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			/* 更新処理を実行 */
			arrivalDetailLogic.update(frm, tantoCd);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (LogicException e) {
			// 発番に失敗した場合
			saveError(request, "errors.numbering");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("reSearch");

	}

	/**
	 * ラベル発行処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward issue(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("issue.");
		}

		ArrivalDetailForm frm = (ArrivalDetailForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);

			/* 更新処理を実行 */
			arrivalDetailLogic.issue(frm.getDetailList(), tantoCd, check);

			/* 計装IFテーブル更新 */
			arrivalDetailLogic.insertIfTable(frm.getDetailList(), check);
			// 製品ラベル作成
			createLabel(frm, request);

		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (DuplicateRuntimeException dre) {
			saveError(request, "errors.arrival.insert.if");
			return mapping.getInputForward();
		} catch (ArrivalLogicException e) {
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
			if (e.getKey().equals("errors.arrival.update.if.table.detail")) {
				frm.setIfUpdateStatus("1");
			}
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		// return mapping.findForward("back");
		return mapping.findForward("reSearch");
	}

	/**
	 * 製品ラベル作成
	 * @param frm ActionMapping
	 * @param response HttpServletResponse
	 */
	private void createLabel(final ArrivalDetailForm frm,
			final HttpServletRequest request) {
		String tantoNm = getLoginInfo(request).getTantoNm();
		FileDownloadInfo info = null;

		TreeMap<String, String> tMap = new TreeMap<String, String>();
		for (ArrivalDetailList bean : frm.getDetailList()) {

			// ロット番号とラベル枚数を保持する
			tMap.put(bean.getLotNo(), bean.getLabelCount());

		}

		// 製品ラベルを作成
		info = arrivalListExcelDecorator.createReport(tMap, tantoNm,
			AecDateUtils.getCurrentTimestamp(), request.getRemoteAddr());
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);
		frm.setExcelDownloadFlg(true);
	}

	/**
	 * 戻る処理(詳細画面または新規登録画面の戻るボタン押下時)
	 * 
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
	 * 新規明細行を取得する
	 * @param bean 詳細データリストの１件目Bean
	 * @param row 行番号
	 * @return CreditBean
	 */
	private ArrivalDetailList getNewLine(final ArrivalDetailList bean,
			final int row) throws Exception {
		ArrivalDetailList data = new ArrivalDetailList();
		IgnoreCaseBeanUtils.copyProperties(data, bean);
		data.setCheckFlg(false); // チェックフラグ
		data.setPurchaseNo(null); // 購買NO
		data.setSlipNo(null); // 仕入番号
		data.setRowNo(new BigDecimal(row)); // 行番号
		data.setStrRowNo(Integer.toString(row)); // 行番号
		data.setSupplierLotno(null); // メーカーロット番号
		data.setLotNo(null); // 入荷ロット番号
		data.setArrivalQuantity(null); // 入荷予定数量
		data.setStrArrivalQuantity(null); // 入荷予定数量(カンマ)
		data.setLabelCount(null); // ラベル数

		return data;
	}

	/**
	 * 数値桁数チェック部品からjavascriptでの桁数丸め用に必要となる値を取得
	 * @param frm 入荷入力画面Form
	 * @param check 数値項目用表示ロジッククラス
	 * @param bean 入荷入力Bean
	 */
	private void getCheckDigit(final ArrivalDetailForm frm,
			final CheckDigitUtilsLogic check, final ArrivalDetailList bean) {
		NumberChkDisitDetail detail = check.getCheckDigit(bean.getUnitDiv(),
			ArrivalDetailLogicImpl.VENDER_DIV_SI, bean.getVenderCd());
		if (detail.getSmallnumLength() != null) {
			frm.setDecimalPoint(detail.getSmallnumLength().toString()); // 小数点桁数
		}
		if (detail.getRoundDivision() != null) {
			frm.setRound(detail.getRoundDivision().toString()); // 端数区分
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
	protected void addError(final HttpServletRequest request, final String key,
			final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 入荷入力詳細ロジッククラスを設定します。
	 * @param arrivalDetailLogic 入荷入力詳細ロジッククラス
	 */
	public void setArrivalDetailLogic(
			final ArrivalDetailLogic arrivalDetailLogic) {
		this.arrivalDetailLogic = arrivalDetailLogic;
	}

	/**
	 * 製品・原材料ラベルＥＸＣＥＬファイル作成ロジッククラスを設定します。
	 * @param arrivalListExcelDecorator 製品・原材料ラベルＥＸＣＥＬファイル作成ロジッククラス
	 */
	public void setArrivalListExcelDecorator(
			final ArrivalListExcelDecorator arrivalListExcelDecorator) {
		this.arrivalListExcelDecorator = arrivalListExcelDecorator;
	}
}
