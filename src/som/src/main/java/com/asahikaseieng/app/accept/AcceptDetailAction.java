/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.accept;

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
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.performancelog.PerformanceLog;
import com.asahikaseieng.dao.entity.performancelog.PerformanceLogDao;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.nonentity.accept.AcceptDetailList;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.exception.LogicException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 受入入力詳細 Actionクラス.
 * @author tosco
 */
public final class AcceptDetailAction extends AbstractAction {

	/** 最終受入データ 最終受入データ以外 */
	private static final String LASTDATAFLG_NOTLAST = "0";

	/** 最終受入データ 最終受入データ */
	private static final String LASTDATAFLG_LAST = "1";

	/** 入荷対象フラグ：対象 */
	private static final String NYUKA_FLG_NOTAISHO = "0";

	/** 受入入力詳細ロジッククラス */
	private AcceptDetailLogic acceptDetailLogic;

	/** パフォーマンスログ出力用Dao */
	private PerformanceLogDao performanceLogDao;

	/**
	 * コンストラクタ.
	 */
	public AcceptDetailAction() {
		super();
	}

	/**
	 * 検索処理(一覧画面の受入ボタン押下時)
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

		AcceptDetailForm frm = (AcceptDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ACCEPT,
			Constants.TAB_ID_ACCEPT_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		try {
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);

			// 仕入区分コンボボックスの作成
			frm.setStockinDivisionCombo(acceptDetailLogic
					.createAcceptStockingDivisionCombobox());

			/* 初期検索 */
			List<AcceptDetailList> beanList = acceptDetailLogic.getEntity(frm
					.getPurchaseNo(), check, frm.getKbn());

			// 共通情報セット
			setCommonInfo(frm, beanList);
			// 行番号振りなおし
			if (frm.getNyukaFlg().equals("2")) {
				for (int i = 0; i < beanList.size(); i++) {
					AcceptDetailList db = beanList.get(i);
					if (db.getRowNo() != null) {
						int rowno = db.getRowNo().intValue() / 1000;
						rowno = rowno * 1000 + i + 1;
						db.setStrRowNo(Integer.toString(rowno));
						db.setRowNo(new BigDecimal(rowno));
					}
				}
			}
			// ロット分割データセット
			frm.setDetailList(beanList);

			// 自データ以外の受入数量合計値取得
			BigDecimal sumAcceptQty = acceptDetailLogic.getSumAcceptQty(frm
					.getBuySubcontractOrderNo(), null);
			// BigDecimal sumAll = new BigDecimal(0);
			// for (int i = 0; i < beanList.size(); i++) {
			// if (beanList.get(i).getAcceptQuantity() != null) {
			// sumAll = sumAll.add(beanList.get(i).getAcceptQuantity());
			// }
			// }
			// sumAcceptQty = sumAcceptQty.subtract(sumAll);
			frm.setSumAcceptQuantity(sumAcceptQty.toString());

			// 最終受入データフラグ設定
			frm.setLastDataFlg(LASTDATAFLG_NOTLAST);
			if (!NYUKA_FLG_NOTAISHO.equals(frm.getNyukaFlg())) {
				// 未受入データ件数取得
				BigDecimal count = acceptDetailLogic.getCountNotAccept(frm
						.getBuySubcontractOrderNo());
				// 未受入データ件数＝１件 の場合
				if (BigDecimal.ONE.equals(count)) {
					frm.setLastDataFlg(LASTDATAFLG_LAST);
				}
			}

			// リストにデータが存在する場合
			if (!beanList.isEmpty()) {
				if (beanList.get(0).getDeliveryComp().equals(BigDecimal.ONE)) {
					frm.setDeliveryComp(Boolean.TRUE);
				} else {
					frm.setDeliveryComp(Boolean.FALSE);
				}
			}

			// javascriptでの桁数丸め用に必要となる値取得
			getCheckDigit(frm, check, beanList.get(0));

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

		AcceptDetailForm frm = (AcceptDetailForm) form;
		List<AcceptDetailList> detailList = frm.getDetailList();
		String kbn = detailList.get(0).getKbn();

		// リスト追加ループ
		for (int i = 0; i < detailList.size(); i++) {
			AcceptDetailList detailBean = detailList.get(i);
			// チェックボックスがチェックされていた場合
			if (detailBean.isCheckFlg()) {
				// 新しい要素を追加
				detailList.add(i, getNewLine(i + 1, kbn));
				break;
			}

			// チェックがない場合最後尾に追加
			if (i == detailList.size() - 1) {
				// 新しい要素を追加
				detailList.add(getNewLine(i + 1, kbn));
				break;
			}
		}

		// 追加データの後の行番号を設定
		for (int i = 0; i < detailList.size(); i++) {
			AcceptDetailList detailBean = detailList.get(i);
			detailBean.setCheckFlg(false);
			detailBean.setRowNo(new BigDecimal(i + 1)); // 行番号
			detailBean.setStrRowNo(Integer.toString(i + 1)); // 行番号
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

		AcceptDetailForm frm = (AcceptDetailForm) form;
		List<AcceptDetailList> detailList = frm.getDetailList();
		String kbn = detailList.get(0).getKbn();
		int len = detailList.size();

		for (int i = len - 1; i >= 0; i--) {
			AcceptDetailList bean = detailList.get(i);
			if (bean.isCheckFlg()) {
				// 削除対象行
				detailList.remove(i);
			}
		}
		if (detailList.size() <= 0) {
			// 明細行が空になった場合は、新規行を1行追加する
			detailList.add(getNewLine(1, kbn));
		} else {
			// 行番号振りなおし
			int index = 1;
			for (AcceptDetailList bean : detailList) {
				bean.setRowNo(new BigDecimal(index)); // 行番号
				bean.setStrRowNo(Integer.toString(index)); // 行番号
				index++;
			}
		}

		return mapping.findForward("success");
	}

	/**
	 * ロケ追加処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward addLoc(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("addLoc.");
		}

		AcceptDetailForm frm = (AcceptDetailForm) form;
		List<AcceptDetailList> detailList = frm.getDetailList();

		// リスト追加ループ
		for (int i = 0; i < detailList.size(); i++) {
			AcceptDetailList detailBean = detailList.get(i);
			// チェックボックスがチェックされていた場合
			if (detailBean.isCheckFlg()) {
				// 新しい要素を追加
				detailList.add(i + 1, getNewLoc(detailBean));
				break;
			}
		}

		// 追加データの後の行番号を設定
		int row = 1000;
		int base = 0;
		int ct = 0;
		for (int i = 0; i < detailList.size(); i++) {
			AcceptDetailList detailBean = detailList.get(i);
			if (detailBean.getRowNo() != null) {
				row = detailBean.getRowNo().intValue();
			}
			int newrow = row / 1000;
			if (base == newrow) {
				ct++;
			} else {
				base = newrow;
				ct = 1;
			}
			newrow = base * 1000 + ct;
			detailBean.setCheckFlg(false);
			detailBean.setRowNo(new BigDecimal(newrow)); // 行番号
			detailBean.setStrRowNo(Integer.toString(newrow)); // 行番号
		}

		return mapping.findForward("success");
	}

	/**
	 * ロケ削除処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delLoc(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("delRow.");
		}

		AcceptDetailForm frm = (AcceptDetailForm) form;
		List<AcceptDetailList> detailList = frm.getDetailList();
		String kbn = detailList.get(0).getKbn();
		int len = detailList.size();

		for (int i = len - 1; i >= 0; i--) {
			AcceptDetailList bean = detailList.get(i);
			if (bean.isCheckFlg()) {
				// 削除対象行
				detailList.remove(i);
			}
		}
		if (detailList.size() <= 0) {
			// 明細行が空になった場合は、新規行を1行追加する
			detailList.add(getNewLine(1001, kbn));
		} else {
			// 行番号振りなおし
			int row = 1000;
			int base = 0;
			int ct = 0;
			for (int i = 0; i < detailList.size(); i++) {
				AcceptDetailList detailBean = detailList.get(i);
				if (detailBean.getRowNo() != null) {
					row = detailBean.getRowNo().intValue();
				}
				int newrow = row / 1000;
				if (base == newrow) {
					ct++;
				} else {
					base = newrow;
					ct = 1;
				}
				newrow = base * 1000 + ct;
				detailBean.setCheckFlg(false);
				detailBean.setRowNo(new BigDecimal(newrow)); // 行番号
				detailBean.setStrRowNo(Integer.toString(newrow)); // 行番号
			}
		}

		return mapping.findForward("success");
	}

	/**
	 * 登録処理(受入入力画面の登録ボタン押下時)(入荷対象外)
	 *
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

		AcceptDetailForm frm = (AcceptDetailForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 更新前チェックを行う
			ActionMessages errors = acceptDetailLogic.checkForRegist(frm);
			if (!errors.isEmpty()) {
				// エラーがあった場合
				super.saveErrors(request, errors);
				return mapping.findForward("error");
			}

			// 購買外注データ検索
			PurchaseSubcontract bean = acceptDetailLogic.getEntity(frm
					.getPurchaseNo());

			// 登録処理(DELETE/INSERT)を実行
			LoginInfo login = (LoginInfo) request.getSession(false)
					.getAttribute(Constants.LOGIN_KEY);
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);
			acceptDetailLogic.insert(frm, login.getOrganizationCd(), tantoCd,
				bean, check);
			// 購買外注データ検索
			acceptDetailLogic.setCodeNyukalot(frm);

		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (LogicException e) {
			saveError(request, "errors.accept.sales.insert");
			return mapping.getInputForward();
		} catch (LogicExceptionEx e) {
			// 受入重量計算エラー
			if ("errors.conv.inventory.calc".equals(e.getMessage())) {
				saveError(request, e.getMessage());
				// 在庫更新に失敗
			} else if ("errors.accept.stock.update".equals(e.getMessage())) {
				saveError(request, e.getMessage());
			} else {
				throw e;
			}
			return mapping.getInputForward();
		}
		frm.setIfUpdateStatus("1");

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("success");
	}

	/**
	 * 更新処理(受入入力画面の登録ボタン押下時)(入荷対象)
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

		AcceptDetailForm frm = (AcceptDetailForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 登録処理(DELETE/INSERT)を実行
			LoginInfo login = (LoginInfo) request.getSession(false)
					.getAttribute(Constants.LOGIN_KEY);

			PerformanceLog log = new PerformanceLog();

			log.setModuleCd("ACCEPT_TEST");
			log.setClient(login.getTantoCd());
			log.setErrorDate(AecDateUtils.getCurrentTimestamp());

			log.setErrorDate(AecDateUtils.getCurrentTimestamp());
			log.setErrorMes("1");
			log.setSqlStr("AP側更新前チェック処理 開始");
			performanceLogDao.insert(log);

			// 更新前チェックを行う
			ActionMessages errors = acceptDetailLogic.checkForRegist(frm);
			if (!errors.isEmpty()) {
				// エラーがあった場合
				super.saveErrors(request, errors);
				return mapping.findForward("error");
			}

			log.setErrorDate(AecDateUtils.getCurrentTimestamp());
			log.setErrorMes("2");
			log.setSqlStr("AP側更新前チェック処理 終了");
			performanceLogDao.insert(log);

			log.setErrorDate(AecDateUtils.getCurrentTimestamp());
			log.setSqlStr("AP_IF検索処理 開始");
			log.setErrorMes("3");
			performanceLogDao.insert(log);

			// 購買外注データ検索
			PurchaseSubcontract bean = acceptDetailLogic.getEntity(frm
					.getPurchaseNo());
			log.setErrorDate(AecDateUtils.getCurrentTimestamp());
			log.setErrorMes("4");
			log.setSqlStr("AP_IF検索処理 終了");
			performanceLogDao.insert(log);

			if (bean == null) {
				throw new NoDataException();
			}

			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);

			log.setErrorDate(AecDateUtils.getCurrentTimestamp());
			log.setErrorMes("5");
			log.setSqlStr("AP更新処理 開始");
			performanceLogDao.insert(log);

			// 更新処理を実行
			acceptDetailLogic.update(frm, tantoCd, bean, check);

			log.setErrorDate(AecDateUtils.getCurrentTimestamp());
			log.setSqlStr("AP更新処理 終了");
			log.setErrorMes("8");
			performanceLogDao.insert(log);

			log.setErrorDate(AecDateUtils.getCurrentTimestamp());
			log.setSqlStr("IF側処理 開始");
			log.setErrorMes("9");
			performanceLogDao.insert(log);

			// ロット番号が発番されているもののみ対象とする
			if (StringUtils.isNotEmpty(bean.getLotNo())
					&& !bean.getLotNo().equals(Constants.LOTNO_WITHOUT_LOT)) {

				// 受入登録済み以外の場合IFテーブルを更新する
				if (!PurchaseStatus.ACCEPTED.equals(bean.getStatus())) {
					// PRO_IF_MATERIAL_IMPORT_RESULTを実行
					acceptDetailLogic.callProIf(frm, tantoCd);
				}

			}
			log.setErrorDate(AecDateUtils.getCurrentTimestamp());
			log.setSqlStr("IF側処理 終了");
			log.setErrorMes("10");
			performanceLogDao.insert(log);

		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (AcceptLogicException e) {
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
				acceptDetailLogic.outPutErrorLog(e.getModuleCd(), e
						.getInsideErrCd(), e.getInsideErrMsg(), tantoCd);
				// IFテーブル更新エラーの場合
				if (AcceptDetailLogicImpl.PRO_IF_MATERIAL_IMPORT_RESULT
						.equals(e.getModuleCd())) {
					frm.setIfUpdateStatus("1");
				}
			}
			return mapping.getInputForward();
		}
		frm.setIfUpdateStatus("1");
		frm.setStatus(PurchaseStatus.ACCEPTED.toString());

		/* メッセージ */
		saveMessage(request, "message.complete.update");
		return mapping.findForward("success");

	}

	/**
	 * 削除処理(受入入力画面の削除ボタン押下時)(入荷対象)
	 *
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

		AcceptDetailForm frm = (AcceptDetailForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 購買外注データ検索
			PurchaseSubcontract bean = acceptDetailLogic.getEntity(frm
					.getPurchaseNo());

			// 更新処理を実行
			acceptDetailLogic.delete(frm, tantoCd, bean);

		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		} catch (AcceptLogicException e) {
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
			return mapping.getInputForward();
		}
		/* メッセージ */
		saveMessage(request, "message.complete.delete");
		return mapping.findForward("back");

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
	 * 共通情報をFORMへセット
	 * @param frm 受入入力FORM
	 * @param beanList 受入入力表示データ
	 */
	private void setCommonInfo(final AcceptDetailForm frm,
			final List<AcceptDetailList> beanList) {
		AcceptDetailList bean = beanList.get(0);
		frm.setPurchaseNo(bean.getPurchaseNo()); // 購買NO
		frm.setBuySubcontractOrderNo(bean.getBuySubcontractOrderNo()); // 発注番号
		frm.setStrOrderDate(bean.getStrOrderDate()); // 発注日
		frm.setSiOrderNo(bean.getSiOrderNo()); // 仕入先受注番号
		frm.setItemCd(bean.getItemCd()); // 品目コード
		frm.setItemQueueName(bean.getItemQueueName()); // 品目名称
		frm.setOtherCompanyCd1(bean.getOtherCompanyCd1()); // 他社コード１
		frm.setOrderQuantity(convertString(bean.getOrderQuantity())); // 発注数量
		frm.setStrOrderQuantity(bean.getStrOrderQuantity()); // 発注数量
		frm.setStyleOfPacking(bean.getStyleOfPacking()); // 荷姿
		frm.setStrOrderConvertQuantity(bean.getStrOrderConvertQuantity()); // 重量
		frm.setSlipNo(bean.getSlipNo()); // 仕入番号
		frm.setVenderCd(bean.getVenderCd()); // 仕入先コード
		frm.setVenderName(bean.getVenderName()); // 仕入先名称
		frm.setVenderShortedName(bean.getVenderShortedName()); // 仕入先略称
		frm.setLocationCd(bean.getLocationCd()); // 納入ロケーション
		frm.setLocationName(bean.getLocationName()); // 納入先名称
		frm.setOrganizationCd(bean.getOrganizationCd()); // 部署コード
		frm.setOrganizationName(bean.getOrganizationName()); // 部署名称
		frm.setStrSuggestedDeliverlimitDate(bean
				.getStrSuggestedDeliverlimitDate()); // 納品希望日
		frm.setCategoryDivision(bean.getCategoryDivision()); // 分類コード
		frm.setStatus(convertString(bean.getStatus())); // 購買ステータス
		frm.setOrderSheetNo(bean.getOrderSheetNo()); // 発注書NO
		frm.setNextDeliverlimitDate(bean.getStrNextDeliverlimitDate()); // 次回納品希望日
		frm.setNextDeliverlimitDateTime(bean.getStrNextDeliverlimitDateTime()); // 次回納品希望日(時間)
		frm.setChargeOrganizationName(bean.getChargeOrganizationName()); // 担当部署名称
		frm.setOrderSheetRemark2(bean.getOrderSheetRemark2()); // 発注書備考（入荷以降）
		frm.setRemark2(bean.getRemark2()); // 備考（入荷以降）
		frm.setOrderDivision(convertString(bean.getOrderDivision())); // オーダー区分
		frm.setUnit(bean.getUnit()); // 単位
		frm.setOrderDivideNo(bean.getOrderDivideNo()); // 発注番号分納枝番
		frm.setReducedTaxTargetFlg(bean.getReducedTaxTargetFlg()); // 免税計算対象フラグ
	}

	/**
	 * BigDecimalからStringへ型変換を行う
	 * @param decimal BigDecimal値
	 * @return String String型に変換した値
	 */
	private String convertString(final BigDecimal decimal) {
		String ret = null;
		if (decimal != null) {
			ret = decimal.toString();
		}
		return ret;
	}

	/**
	 * 新規明細行を取得する
	 * @param row 行番号
	 * @param kbn 区分
	 * @return CreditBean
	 */
	private AcceptDetailList getNewLine(final int row, final String kbn)
			throws Exception {
		AcceptDetailList data = new AcceptDetailList();
		String strAcceptDate = AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd");

		data.setCheckFlg(false); // チェックフラグ
		data.setRowNo(new BigDecimal(row)); // 行番号
		data.setStrRowNo(Integer.toString(row)); // 行番号
		data.setStrAcceptDate(strAcceptDate); // 現在日付を初期値として設定
		data.setKbn(kbn);

		return data;
	}

	/**
	 * 新規明細行を取得する
	 * @param row 行番号
	 * @param kbn 区分
	 * @return CreditBean
	 */
	private AcceptDetailList getNewLoc(final AcceptDetailList oya)
			throws Exception {
		AcceptDetailList data = new AcceptDetailList();
		String strAcceptDate = AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd");
		int row = 1000;
		if (oya.getRowNo() != null) {
			row = oya.getRowNo().intValue();
		}
		int base = row / 1000;
		int ct = row % 1000;
		ct++;
		base = base * 1000 + ct;
		IgnoreCaseBeanUtils.copyProperties(data, oya);
		data.setCheckFlg(false); // チェックフラグ
		data.setRowNo(new BigDecimal(base)); // 行番号
		data.setStrRowNo(Integer.toString(base)); // 行番号
		data.setStrAcceptDate(strAcceptDate); // 現在日付を初期値として設定
		data.setPurchaseNo(null);
		return data;
	}

	/**
	 * 数値桁数チェック部品からjavascriptでの桁数丸め用に必要となる値を取得
	 * @param frm 入荷入力画面Form
	 * @param check 数値項目用表示ロジッククラス
	 * @param bean 入荷入力Bean
	 */
	private void getCheckDigit(final AcceptDetailForm frm,
			final CheckDigitUtilsLogic check, final AcceptDetailList bean) {
		NumberChkDisitDetail detail = check.getCheckDigit(bean.getUnitDiv(),
			AcceptDetailLogicImpl.VENDER_DIV_SI, bean.getVenderCd());
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
	private void addError(final HttpServletRequest request, final String key,
			final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 受入入力詳細ロジッククラスを設定します。
	 * @param acceptDetailLogic 受入入力詳細ロジッククラス
	 */
	public void setAcceptDetailLogic(final AcceptDetailLogic acceptDetailLogic) {
		this.acceptDetailLogic = acceptDetailLogic;
	}

	/**
	 * performanceLogDaoを設定します。
	 * @param performanceLogDao performanceLogDao
	 */
	public void setPerformanceLogDao(final PerformanceLogDao performanceLogDao) {
		this.performanceLogDao = performanceLogDao;
	}
}
