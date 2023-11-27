/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.vender;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitResult;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.companybank.CompanyBankListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetail;
import com.asahikaseieng.dao.nonentity.master.areadetail.AreaDetail;
import com.asahikaseieng.dao.nonentity.master.bankdetail.BankDetail;
import com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetail;
import com.asahikaseieng.dao.nonentity.master.caldetail.CalDetail;
import com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetail;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.venderclassdetail.VenderClassDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.sort.ClassificationComparator;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 取引先詳細 Actionクラス.
 * @author t0011036
 */
public final class VenderDetailAction extends AbstractAction {

	private VenderDetailLogic venderDetailLogic;

	/* 普通 */
	private static final BigDecimal ACCOUNT_DIVISION1 = new BigDecimal("1");

	/* 当座 */
	private static final BigDecimal ACCOUNT_DIVISION2 = new BigDecimal("2");

	/* その他 */
	private static final BigDecimal ACCOUNT_DIVISION3 = new BigDecimal("3");

	/* 普通 */
	private static final String ACCOUNT_DIVISION_NAME1 = "普通";

	/* 当座 */
	private static final String ACCOUNT_DIVISION_NAME2 = "当座";

	/* その他 */
	private static final String ACCOUNT_DIVISION_NAME3 = "その他";

	/* 得意先 */
	private static final String TS = "TS";

	/* 仕入先 */
	private static final String SI = "SI";

	/* インボイス番号 頭文字 */
	private static final String INVOICE_NO_HEAD = "T";

	/**
	 * コンストラクタ.
	 */
	public VenderDetailAction() {
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

		VenderDetailForm frm = (VenderDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_VENDER,
			Constants.TAB_ID_VENDER_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 自社銀行口座セット */
		setBankMasterCdCombobox(frm);

		/* 売上インボイスパターンセット */
		setTsInvoicePtnCombobox(frm);

		/* 仕入インボイスパターンセット */
		setSiInvoicePtnCombobox(frm);

		/* 初期検索 */
		VenderDetail bean = venderDetailLogic.getDetailEntity(frm
				.getVenderDivision(), frm.getVenderCd());

		/* サイト区分セット */
		setCategoryDivisionCombobox(frm, new BigDecimal("1"));
		setCategoryDivisionCombobox(frm, new BigDecimal("2"));

		if (bean.getSubcontractLaw().equals(new BigDecimal("2"))) {
			bean.setSubcontractLaw(new BigDecimal("1"));
		} else {
			bean.setSubcontractLaw(new BigDecimal("0"));
		}

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

		frm.setFaxOutput(bean.getFaxOutput());

		frm.setBankMasterCd(bean.getBankCd());
		frm.setDispBankMasterCd(bean.getBankCd());
		frm.setDispBankName(bean.getBankName());
		frm.setDispBranchName(bean.getBranchName());
		frm.setAccountDivision(bean.getAccountDivision());
		frm.setAccountNo(bean.getAccountNo());
		frm.setAccountStockhold(bean.getAccountStockhold());

		frm.setOtherBankMasterCd(bean.getOtherBankCd());
		frm.setOtherBankName(bean.getOtherBankName());
		frm.setOtherBranchName(bean.getOtherBranchName());
		frm.setOtherAccountDivision(bean.getOtherAccountDivision());
		frm.setOtherAccountNo(bean.getOtherAccountNo());
		frm.setOtherAccountStockhold(bean.getOtherAccountStockhold());

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		/* 数値文字列変換 */
		frm.setStrClosingDate(checker.format("SONOTA", frm.getClosingDate()));
		frm.setStrCreditLimitPrice(checker.format("URKINGAKU", frm
				.getCreditLimitPrice()));
		frm.setStrNoteSight3(checker.format("SONOTA", frm.getNoteSight3()));
		frm.setStrNoteSight4(checker.format("SONOTA", frm.getNoteSight4()));
		frm.setStrNoteSight5(checker.format("SONOTA", frm.getNoteSight5()));
		frm.setStrBoundAmount3(checker.format("URKINGAKU", frm
				.getBoundAmount3()));
		frm.setStrBoundAmount4(checker.format("URKINGAKU", frm
				.getBoundAmount4()));
		frm.setStrCreditScheduledDate1(checker.format("SONOTA", frm
				.getCreditScheduledDate1()));
		frm.setStrCreditScheduledDate2(checker.format("SONOTA", frm
				.getCreditScheduledDate2()));
		frm.setStrCreditScheduledDate3(checker.format("SONOTA", frm
				.getCreditScheduledDate3()));
		frm.setStrPurchaseDiscountDays1(checker.format("SONOTA", frm
				.getPurchaseDiscountDays1()));
		frm.setStrPurchaseDiscountDays2(checker.format("SONOTA", frm
				.getPurchaseDiscountDays2()));
		frm.setStrPurchaseDiscountDays3(checker.format("SONOTA", frm
				.getPurchaseDiscountDays3()));
		frm.setStrTaxRatio(checker.format("TAX_AMOUNT", frm.getTaxRatio()));

		// 得意先コード、請求先コードが一致する場合コンボボックスを活性にする
		frm.setTsInvoiceActiveFlg("false");
		if(StringUtils.equals(frm.getVenderCd(), frm.getPaymentInvoiceCd())){
			frm.setTsInvoiceActiveFlg("true");
		}

		return mapping.findForward("success");
	}

	/**
	 * 自社銀行口座リスト取得
	 * @param frm 画面データ
	 */
	public void setBankMasterCdCombobox(final VenderDetailForm frm) {
		/* 自社マスタ検索 */
		// CompanyDetail beanCompany = venderDetailLogic
		// .getCompanyEntity("000001");
		//
		// if (beanCompany == null) {
		// return;
		// }
		//
		// String[] values;
		// String[] labels;
		//
		// labels = new String[4];
		// values = new String[4];
		//
		// BankDetail beanBank = new BankDetail();
		//
		// /* 銀行マスタ検索 */
		// beanBank = venderDetailLogic.getBankEntity(beanCompany
		// .getBankMasterCd1());
		//
		// if (beanBank == null) {
		// labels[0] = "入金銀行1未登録";
		// values[0] = beanCompany.getBankMasterCd1();
		// } else {
		// labels[0] = beanBank.getBankMasterName();
		// values[0] = beanBank.getBankMasterCd();
		// }
		//
		// /* 銀行マスタ検索 */
		// beanBank = venderDetailLogic.getBankEntity(beanCompany
		// .getBankMasterCd2());
		//
		// if (beanBank == null) {
		// labels[1] = "入金銀行2未登録";
		// values[1] = beanCompany.getBankMasterCd2();
		// } else {
		// labels[1] = beanBank.getBankMasterName();
		// values[1] = beanBank.getBankMasterCd();
		// }
		//
		// /* 銀行マスタ検索 */
		// beanBank = venderDetailLogic.getBankEntity(beanCompany
		// .getBankMasterCd3());
		//
		// if (beanBank == null) {
		// labels[2] = "入金銀行3未登録";
		// values[2] = beanCompany.getBankMasterCd3();
		// } else {
		// labels[2] = beanBank.getBankMasterName();
		// values[2] = beanBank.getBankMasterCd();
		// }
		//
		// /* 銀行マスタ検索 */
		// beanBank = venderDetailLogic.getBankEntity(beanCompany
		// .getBankMasterCd4());
		//
		// if (beanBank == null) {
		// labels[3] = "入金銀行4未登録";
		// values[3] = beanCompany.getBankMasterCd4();
		// } else {
		// labels[3] = beanBank.getBankMasterName();
		// values[3] = beanBank.getBankMasterCd();
		// }
		//
		// frm.setBankMasterCdLabels(labels);
		// frm.setBankMasterCdValues(values);
		List<CompanyBankListForComboboxes> list = venderDetailLogic
				.getBankList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getBankMasterName();
			values[i] = list.get(i).getBankMasterCd();
		}

		frm.setBankMasterCdLabels(labels);
		frm.setBankMasterCdValues(values);
	}

	/**
	 * サイト区分リスト取得
	 * @param frm 画面データ
	 * @param advanceDivision 売掛対象区分
	 */
	@SuppressWarnings("unchecked")
	public void setCategoryDivisionCombobox(final VenderDetailForm frm,
			final BigDecimal advanceDivision) {
		BigDecimal dataType = new BigDecimal("0"); /* データ種別 */
		BigDecimal arDivision = new BigDecimal("0"); /* 売掛対象区分 */

		/* データ種別決定 */
		if (frm.getVenderDivision().equals("TS")) {
			dataType = new BigDecimal("2"); /* 入金 */

			/* 売掛対象区分決定 */
			if (advanceDivision.equals(new BigDecimal("1"))) {
				arDivision = new BigDecimal("1"); /* 前受金対象でない */
			} else {
				arDivision = new BigDecimal("0"); /* 前受金対象 */
			}
		} else {
			dataType = new BigDecimal("4"); /* 支払 */
		}

		/* 分類マスタ検索 */
		List<ClassificationListForComboboxes> list = venderDetailLogic
				.getClassificationList(dataType, arDivision);

		/* 分類コード順のソート */
		Collections.sort(list, new ClassificationComparator(
				ClassificationComparator.ASC));

		String[] values;
		String[] labels;

		labels = new String[list.size() + 1];
		values = new String[list.size() + 1];

		/* 初期選択項目セット */
		labels[0] = "選択して下さい";
		values[0] = "0";

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i + 1] = list.get(i).getCategoryName();
			values[i + 1] = list.get(i).getCategoryDivision();
		}

		if (frm.getVenderDivision().equals("TS")) {
			/* 得意先 */
			if (advanceDivision.equals(new BigDecimal("1"))) {
				/* 前受金対象でない */
				frm.setNotCreditDivisionLabels(labels);
				frm.setNotCreditDivisionValues(values);
			} else {
				/* 前受金対象 */
				frm.setCreditDivisionLabels(labels);
				frm.setCreditDivisionValues(values);
			}
		} else {
			/* 仕入先 */
			frm.setCreditDivisionLabels(labels);
			frm.setCreditDivisionValues(values);
		}
	}

	/**
	 * 売上インボイスパターンリスト取得
	 * @param frm 画面データ
	 */
	public void setTsInvoicePtnCombobox(final VenderDetailForm frm) {
		List<NamesListForComboboxes> list = venderDetailLogic.getTsInvoicePtnList();

		String[] values;
		String[] labels;

		labels = new String[list.size() + 1];
		values = new String[list.size() + 1];

		/* 初期選択項目セット */
		labels[0] = "";
		values[0] = "0";

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i + 1] = list.get(i).getName01();
			values[i + 1] = list.get(i).getNameCd();
		}

		frm.setTsInvoicePtnLabels(labels);
		frm.setTsInvoicePtnValues(values);
	}

	/**
	 * 仕入インボイスパターンリスト取得
	 * @param frm 画面データ
	 */
	public void setSiInvoicePtnCombobox(final VenderDetailForm frm) {
		List<NamesListForComboboxes> list = venderDetailLogic.getSiInvoicePtnList();

		String[] values;
		String[] labels;

		labels = new String[list.size() + 1];
		values = new String[list.size() + 1];

		/* 初期選択項目セット */
		labels[0] = "";
		values[0] = "0";

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i + 1] = list.get(i).getName01();
			values[i + 1] = list.get(i).getNameCd();
		}

		frm.setSiInvoicePtnLabels(labels);
		frm.setSiInvoicePtnValues(values);
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

		VenderDetailForm frm = (VenderDetailForm) form;

		if (!StringUtils.isEmpty(frm.getPaymentInvoiceCd())) {
			if (!frm.getVenderCd().equals(frm.getPaymentInvoiceCd())) {
				/* 請求(支払)先コードチェック */
				VenderDetail beanVender = venderDetailLogic.getDetailEntity(frm
						.getVenderDivision(), frm.getPaymentInvoiceCd());

				if (beanVender == null) {
					/* エラーメッセージ */
					if (frm.getVenderDivision().equals("TS")) {
						saveError(request,
							"errors.nodata.vender.payment.invoice.cd1");
					} else {
						saveError(request,
							"errors.nodata.vender.payment.invoice.cd2");
					}

					return mapping.findForward("success");
				}
			}
		}
		// 2023/08/09 梅澤さんからの要望により、担当部署のチェック処理を外す。
/*
		 担当部署が相方と同じかチェック 
		if (frm.getVenderDivision().equals(TS)) {
			 支払先コードチェック 
			VenderDetail bean = venderDetailLogic.getDetailEntity(SI, frm
					.getVenderCd());

		if (bean != null) {
				if (StringUtils.isNotEmpty(frm.getOrganizationCd())
						|| StringUtils.isNotEmpty(bean.getOrganizationCd())) {
					if (!frm.getOrganizationCd().equals(
						bean.getOrganizationCd())) {
						 仕入先と同じ担当部署を入力して下さい。 
						saveError(request,
							"errors.vender.si.different.organization.cd");
						return mapping.findForward("success");
					}
				}
			}
		} else {
			 得意先コードチェック 
			VenderDetail bean = venderDetailLogic.getDetailEntity(TS, frm
					.getVenderCd());

			if (bean != null) {
				if (StringUtils.isNotEmpty(frm.getOrganizationCd())
						|| StringUtils.isNotEmpty(bean.getOrganizationCd())) {
					if (!frm.getOrganizationCd().equals(
						bean.getOrganizationCd())) {
						 得意先と同じ担当部署を入力して下さい。 
						saveError(request,
							"errors.vender.ts.different.organization.cd");
						return mapping.findForward("success");
					}
				}
			}
		}
*/
		if (!StringUtils.isEmpty(frm.getOrganizationCd())) {
			/* 担当部署コードチェック */
			OrganizationDetail beanOrganization = venderDetailLogic
					.getOrganizationEntity(frm.getOrganizationCd());

			if (beanOrganization == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.vender.organization.cd");
				return mapping.findForward("success");
			}
		}

		// 受注と売上のメール送信区分が設定されている場合、メール送信元部署の設定されているか確認する。
		if(frm.getOrderMailOutput().equals(BigDecimal.ONE) || frm.getSalesMailOutput().equals(BigDecimal.ONE)){

			// メール送信元部署の設定がない場合エラーとする。
			if(frm.getMailOrganizationCd() == null || frm.getMailOrganizationCd().isEmpty()){
				/* エラーメッセージ */
				saveError(request, "errors.vender.mail.organization.cd.need");
				return mapping.findForward("success");
			}
		}


		if (!StringUtils.isEmpty(frm.getMailOrganizationCd())) {
			/* メール送信元部署コードチェック */
			OrganizationDetail beanMailOrganization = venderDetailLogic.getMailOrganizationEntity(frm.getMailOrganizationCd());

			if (beanMailOrganization == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.vender.mail.organization.cd");
				return mapping.findForward("success");
			}

			// メール送信元部署のFromメールアドレスが未設定の場合エラーとする。
			if(beanMailOrganization.getFromMailAddress1() == null || beanMailOrganization.getFromMailAddress1().isEmpty()){
				saveError(request, "errors.nodata.vender.mail.organization.frommail.need");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getTantoCd())) {
			/* 営業担当者コードチェック */
			LoginDetail beanLogin = venderDetailLogic.getLoginEntity(frm
					.getTantoCd());

			if (beanLogin == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.vender.tanto.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getAreaCd())) {
			/* 地区コードチェック */
			AreaDetail beanArea = venderDetailLogic.getAreaEntity(frm
					.getAreaCd());

			if (beanArea == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.vender.area.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getSectionCd())) {
			/* 会計部門コードチェック */
			BumonDetail beanBumon = venderDetailLogic.getBumonEntity(frm
					.getSectionCd());

			if (beanBumon == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.vender.section.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getAccountsCd())) {
			/* 勘定科目コードチェック */
			AccountsDetail beanAccounts = venderDetailLogic
					.getAccountsEntity(frm.getAccountsCd());

			if (beanAccounts == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.vender.accounts.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getCalendarCd())) {
			/* カレンダーコードチェック */
			CalDetail beanCal = venderDetailLogic.getCalEntity(frm
					.getCalendarCd());

			if (beanCal == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.vender.calendar.cd");
				return mapping.findForward("success");
			}
		}

		// 売上・仕入インボイスパターンチェック
		if(frm.getVenderDivision().equals(TS)) {

			// 得意先コード、請求書が一致する場合
			if(StringUtils.equals(frm.getVenderCd(), frm.getPaymentInvoiceCd())) {

				// コンボボックスが非活性の場合は活性にする
				if(!StringUtils.equals(frm.getTsInvoiceActiveFlg(), "true")) {
					frm.setTsInvoiceActiveFlg("true");
					saveError(request, "errors.vender.ts.invoiceptn");
					return mapping.findForward("success");
				} else {
					// 活性の場合は売上インボイスパターンの空白チェック
					if(StringUtils.equals(frm.getTsInvoicePtn(), "0")) {
						saveError(request, "errors.vender.ts.invoiceptn");
						return mapping.findForward("success");
					}
				}
			}

		} else {

			// 仕入インボイスパターンの空白チェック
			if(StringUtils.equals(frm.getSiInvoicePtn(), "0")) {
				saveError(request, "errors.vender.si.invoiceptn");
				return mapping.findForward("success");
			}

			// 申請業者の場合のインボイス登録番号チェック
			if(StringUtils.equals(frm.getSiInvoicePtn(), "1")){
				// 必須入力
				if(StringUtils.isEmpty(frm.getInvoiceNo())){
					saveError(request, "errors.vender.si.invoiceNo.required");
					return mapping.findForward("success");
				}

				// インボイス登録番号の入力チェック
				if(!checkInoviceNo(frm.getInvoiceNo())) {
					saveError(request, "errors.company.inoviceno.length");
					return mapping.findForward("success");
				}
			}else if(StringUtils.equals(frm.getSiInvoicePtn(), "2")){
				// 未申請業者でT+13桁のインボイス番号の入力がある場合、エラーとする
				if(checkInoviceNo(frm.getInvoiceNo())) {
					saveError(request, "errors.company.inoviceno.unapplied");
					return mapping.findForward("success");
				}
			}
		}

		/* 締日日数チェック */
		if (!AecNumberUtils.convertBigDecimal(frm.getStrClosingDate()).equals(
			new BigDecimal("99"))
				&& (AecNumberUtils.convertBigDecimal(frm.getStrClosingDate())
						.compareTo(new BigDecimal("1")) < 0 || 0 < AecNumberUtils
						.convertBigDecimal(frm.getStrClosingDate()).compareTo(
							new BigDecimal("31")))) {
			/* エラーメッセージ */
			saveError(request, "errors.vender.closing.date");
			return mapping.findForward("success");
		}

		BigDecimal dataType = new BigDecimal("0");
		BigDecimal noteDivision = new BigDecimal("0");

		if (frm.getVenderDivision().equals("TS")) {
			dataType = new BigDecimal("2");
		} else {
			dataType = new BigDecimal("4");
		}

		/* 手形区分1チェック */
		VenderClassDetail beanClass = venderDetailLogic.getVenderClassEntity(
			dataType, frm.getCreditDivision3().toString());

		if (beanClass == null) {
			noteDivision = new BigDecimal("0");
		} else {
			noteDivision = beanClass.getNoteDivision();
		}

		/* 手形サイト1チェック */
		// if (frm.getCreditDivision3().equals(CREDIT_DIVISION_BILL)) {
		if (noteDivision.equals(new BigDecimal("1"))) {
			if (AecNumberUtils.convertNullToZero(
				AecNumberUtils.convertBigDecimal(frm.getStrNoteSight3()))
					.compareTo(BigDecimal.ZERO) <= 0) {
				/* エラーメッセージ */
				saveErrorWithArgs(request, "errors.required",
					"vender.note.sight3");
				return mapping.findForward("success");
			}
		}

		BigDecimal boundAmount3 = AecNumberUtils
				.convertNullToZero(AecNumberUtils.convertBigDecimal(frm
						.getStrBoundAmount3()));
		BigDecimal boundAmount4 = AecNumberUtils
				.convertNullToZero(AecNumberUtils.convertBigDecimal(frm
						.getStrBoundAmount4()));

		/* 入金(支払)予定1日数必須チェック */
		if (boundAmount3.intValue() != 0) {
			if (StringUtils.isEmpty(frm.getStrCreditScheduledDate1())) {
				/* エラーメッセージ */
				if (frm.getVenderDivision().equals("TS")) {
					saveErrorWithArgs(request, "errors.required",
						"vender.credit.scheduled.date1");
				} else {
					saveErrorWithArgs(request, "errors.required",
						"vender.payment.credit.scheduled.date1");
				}

				return mapping.findForward("success");
			}

			/* 入金(支払)予定1日数チェック */
			if (!AecNumberUtils.convertNullToZero(
				AecNumberUtils.convertBigDecimal(frm
						.getStrCreditScheduledDate1())).equals(
				new BigDecimal("99"))
					&& (AecNumberUtils.convertNullToZero(
						AecNumberUtils.convertBigDecimal(frm
								.getStrCreditScheduledDate1())).compareTo(
						new BigDecimal("1")) < 0 || 0 < AecNumberUtils
							.convertBigDecimal(frm.getStrCreditScheduledDate1())
							.compareTo(new BigDecimal("31")))) {

				/* エラーメッセージ */
				if (frm.getVenderDivision().equals("TS")) {
					saveError(request, "errors.vender.credit.scheduled.date1");
				} else {
					saveError(request,
						"errors.vender.payment.credit.scheduled.date1");
				}

				return mapping.findForward("success");
			}
		}

		/* 手形区分2チェック */
		beanClass = venderDetailLogic.getVenderClassEntity(dataType, frm
				.getCreditDivision4().toString());

		if (beanClass == null) {
			noteDivision = new BigDecimal("0");
		} else {
			noteDivision = beanClass.getNoteDivision();
		}

		/* 手形サイト2チェック */
		// if (frm.getCreditDivision4().equals(CREDIT_DIVISION_BILL)) {
		if (noteDivision.equals(new BigDecimal("1"))) {
			if (AecNumberUtils.convertNullToZero(
				AecNumberUtils.convertBigDecimal(frm.getStrNoteSight4()))
					.compareTo(BigDecimal.ZERO) <= 0) {
				/* エラーメッセージ */
				saveErrorWithArgs(request, "errors.required",
					"vender.note.sight4");
				return mapping.findForward("success");
			}
		}

		/* 入金(支払)予定2日数必須チェック */
		if (boundAmount4.intValue() != 0) {
			if (StringUtils.isEmpty(frm.getStrCreditScheduledDate2())) {
				/* エラーメッセージ */
				if (frm.getVenderDivision().equals("TS")) {
					saveErrorWithArgs(request, "errors.required",
						"vender.credit.scheduled.date2");
				} else {
					saveErrorWithArgs(request, "errors.required",
						"vender.payment.credit.scheduled.date2");
				}

				return mapping.findForward("success");
			}

			/* 入金(支払)予定2日数チェック */
			if (!AecNumberUtils.convertNullToZero(
				AecNumberUtils.convertBigDecimal(frm
						.getStrCreditScheduledDate2())).equals(
				new BigDecimal("99"))
					&& (AecNumberUtils.convertNullToZero(
						AecNumberUtils.convertBigDecimal(frm
								.getStrCreditScheduledDate2())).compareTo(
						new BigDecimal("1")) < 0 || 0 < AecNumberUtils
							.convertBigDecimal(frm.getStrCreditScheduledDate2())
							.compareTo(new BigDecimal("31")))) {

				/* エラーメッセージ */
				if (frm.getVenderDivision().equals("TS")) {
					saveError(request, "errors.vender.credit.scheduled.date2");
				} else {
					saveError(request,
						"errors.vender.payment.credit.scheduled.date2");
				}

				return mapping.findForward("success");
			}
		}

		/* 手形区分3チェック */
		beanClass = venderDetailLogic.getVenderClassEntity(dataType, frm
				.getCreditDivision5().toString());

		if (beanClass == null) {
			noteDivision = new BigDecimal("0");
		} else {
			noteDivision = beanClass.getNoteDivision();
		}

		/* 手形サイト3チェック */
		// if (frm.getCreditDivision5().equals(CREDIT_DIVISION_BILL)) {
		if (noteDivision.equals(new BigDecimal("1"))) {
			if (AecNumberUtils.convertNullToZero(
				AecNumberUtils.convertBigDecimal(frm.getStrNoteSight5()))
					.compareTo(BigDecimal.ZERO) <= 0) {
				/* エラーメッセージ */
				saveErrorWithArgs(request, "errors.required",
					"vender.note.sight5");
				return mapping.findForward("success");
			}
		}

		/* 入金(支払)予定3日数チェック */
		if (boundAmount4.intValue() != 0) {
			if (StringUtils.isEmpty(frm.getStrCreditScheduledDate3())) {
				/* エラーメッセージ */
				if (frm.getVenderDivision().equals("TS")) {
					saveErrorWithArgs(request, "errors.required",
						"vender.credit.scheduled.date3");
				} else {
					saveErrorWithArgs(request, "errors.required",
						"vender.payment.credit.scheduled.date3");
				}

				return mapping.findForward("success");
			}

			if (!AecNumberUtils.convertNullToZero(
				AecNumberUtils.convertBigDecimal(frm
						.getStrCreditScheduledDate3())).equals(
				new BigDecimal("99"))
					&& (AecNumberUtils.convertNullToZero(
						AecNumberUtils.convertBigDecimal(frm
								.getStrCreditScheduledDate3())).compareTo(
						new BigDecimal("1")) < 0 || 0 < AecNumberUtils
							.convertBigDecimal(frm.getStrCreditScheduledDate3())
							.compareTo(new BigDecimal("31")))) {

				/* エラーメッセージ */
				if (frm.getVenderDivision().equals("TS")) {
					saveError(request, "errors.vender.credit.scheduled.date3");
				} else {
					saveError(request,
						"errors.vender.payment.credit.scheduled.date3");
				}

				return mapping.findForward("success");
			}
		}

		/* 境界額大小チェック */
		if (boundAmount3.intValue() != 0 || boundAmount4.intValue() != 0) {
			if (0 <= boundAmount3.compareTo(boundAmount4)) {
				/* エラーメッセージ */
				saveErrorWithArgs(request, "errors.compare",
					"vender.bound.amount3", "vender.bound.amount4");
				return mapping.findForward("success");
			}
		}

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);
		CheckDigitResult result = new CheckDigitResult();
		NumberChkDisitDetail detail = new NumberChkDisitDetail();

		result = check.checkDigit("SONOTA", null, null, frm
				.getStrCreditScheduledDate1());
		detail = result.getDetail();

		switch (result.getCode()) {
		case CheckDigitUtilsLogic.ERROR_NUMBER_FORMAT:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.number",
					"vender.credit.scheduled.date1");
			} else {
				saveErrorWithArgs(request, "errors.number",
					"vender.payment.credit.scheduled.date1");
			}

			return mapping.findForward("success");
		case CheckDigitUtilsLogic.ERROR_NUMBER_MAX_LENGTH:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.maxlength",
					"vender.credit.scheduled.date1", detail.getMaxLength());
			} else {
				saveErrorWithArgs(request, "errors.maxlength",
					"vender.payment.credit.scheduled.date1", detail
							.getMaxLength());
			}

			return mapping.findForward("success");
		case CheckDigitUtilsLogic.ERROR_NUMBER_INTEGER_LENGTH:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.digit.integer",
					"vender.credit.scheduled.date1", detail.getIntegerLength());
			} else {
				saveErrorWithArgs(request, "errors.digit.integer",
					"vender.payment.credit.scheduled.date1", detail
							.getIntegerLength());
			}

			return mapping.findForward("success");
		case CheckDigitUtilsLogic.ERROR_NUMBER_SMALLNUM_LENGTH:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.digit.decimal",
					"vender.credit.scheduled.date1", detail.getSmallnumLength());
			} else {
				saveErrorWithArgs(request, "errors.digit.decimal",
					"vender.payment.credit.scheduled.date1", detail
							.getSmallnumLength());
			}

			return mapping.findForward("success");
		case CheckDigitUtilsLogic.ERROR_NUMBER_RANGE:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.rang",
					"vender.credit.scheduled.date1", detail.getLowerLimit(),
					detail.getUpperLimit());
			} else {
				saveErrorWithArgs(request, "errors.rang",
					"vender.payment.credit.scheduled.date1", detail
							.getLowerLimit(), detail.getUpperLimit());
			}

			return mapping.findForward("success");
		default:
			break;
		}

		result = check.checkDigit("SONOTA", null, null, frm
				.getStrCreditScheduledDate2());
		detail = result.getDetail();

		switch (result.getCode()) {
		case CheckDigitUtilsLogic.ERROR_NUMBER_FORMAT:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.number",
					"vender.credit.scheduled.date2");
			} else {
				saveErrorWithArgs(request, "errors.number",
					"vender.payment.credit.scheduled.date2");
			}

			return mapping.findForward("success");
		case CheckDigitUtilsLogic.ERROR_NUMBER_MAX_LENGTH:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.maxlength",
					"vender.credit.scheduled.date2", detail.getMaxLength());
			} else {
				saveErrorWithArgs(request, "errors.maxlength",
					"vender.payment.credit.scheduled.date2", detail
							.getMaxLength());
			}

			return mapping.findForward("success");
		case CheckDigitUtilsLogic.ERROR_NUMBER_INTEGER_LENGTH:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.digit.integer",
					"vender.credit.scheduled.date2", detail.getIntegerLength());
			} else {
				saveErrorWithArgs(request, "errors.digit.integer",
					"vender.payment.credit.scheduled.date2", detail
							.getIntegerLength());
			}

			return mapping.findForward("success");
		case CheckDigitUtilsLogic.ERROR_NUMBER_SMALLNUM_LENGTH:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.digit.decimal",
					"vender.credit.scheduled.date2", detail.getSmallnumLength());
			} else {
				saveErrorWithArgs(request, "errors.digit.decimal",
					"vender.payment.credit.scheduled.date2", detail
							.getSmallnumLength());
			}

			return mapping.findForward("success");
		case CheckDigitUtilsLogic.ERROR_NUMBER_RANGE:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.rang",
					"vender.credit.scheduled.date2", detail.getLowerLimit(),
					detail.getUpperLimit());
			} else {
				saveErrorWithArgs(request, "errors.rang",
					"vender.payment.credit.scheduled.date2", detail
							.getLowerLimit(), detail.getUpperLimit());
			}

			return mapping.findForward("success");
		default:
			break;
		}

		result = check.checkDigit("SONOTA", null, null, frm
				.getStrCreditScheduledDate3());
		detail = result.getDetail();

		switch (result.getCode()) {
		case CheckDigitUtilsLogic.ERROR_NUMBER_FORMAT:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.number",
					"vender.credit.scheduled.date3");
			} else {
				saveErrorWithArgs(request, "errors.number",
					"vender.payment.credit.scheduled.date3");
			}

			return mapping.findForward("success");
		case CheckDigitUtilsLogic.ERROR_NUMBER_MAX_LENGTH:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.maxlength",
					"vender.credit.scheduled.date3", detail.getMaxLength());
			} else {
				saveErrorWithArgs(request, "errors.maxlength",
					"vender.payment.credit.scheduled.date3", detail
							.getMaxLength());
			}

			return mapping.findForward("success");
		case CheckDigitUtilsLogic.ERROR_NUMBER_INTEGER_LENGTH:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.digit.integer",
					"vender.credit.scheduled.date3", detail.getIntegerLength());
			} else {
				saveErrorWithArgs(request, "errors.digit.integer",
					"vender.payment.credit.scheduled.date3", detail
							.getIntegerLength());
			}

			return mapping.findForward("success");
		case CheckDigitUtilsLogic.ERROR_NUMBER_SMALLNUM_LENGTH:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.digit.decimal",
					"vender.credit.scheduled.date3", detail.getSmallnumLength());
			} else {
				saveErrorWithArgs(request, "errors.digit.decimal",
					"vender.payment.credit.scheduled.date3", detail
							.getSmallnumLength());
			}

			return mapping.findForward("success");
		case CheckDigitUtilsLogic.ERROR_NUMBER_RANGE:
			/* エラーメッセージ */
			if (frm.getVenderDivision().equals("TS")) {
				saveErrorWithArgs(request, "errors.rang",
					"vender.credit.scheduled.date3", detail.getLowerLimit(),
					detail.getUpperLimit());
			} else {
				saveErrorWithArgs(request, "errors.rang",
					"vender.payment.credit.scheduled.date3", detail
							.getLowerLimit(), detail.getUpperLimit());
			}

			return mapping.findForward("success");
		default:
			break;
		}

		/* 数値変換 */
		frm.setClosingDate(check.round("SONOTA", null, null, strToNum(frm
				.getStrClosingDate())));
		frm.setCreditLimitPrice(check.round("URKINGAKU", null, null,
			strToNum(frm.getStrCreditLimitPrice())));
		frm.setNoteSight3(check.round("SONOTA", null, null, strToNum(frm
				.getStrNoteSight3())));
		frm.setNoteSight4(check.round("SONOTA", null, null, strToNum(frm
				.getStrNoteSight4())));
		frm.setNoteSight5(check.round("SONOTA", null, null, strToNum(frm
				.getStrNoteSight5())));
		frm.setBoundAmount3(check.round("URKINGAKU", null, null, strToNum(frm
				.getStrBoundAmount3())));
		frm.setBoundAmount4(check.round("URKINGAKU", null, null, strToNum(frm
				.getStrBoundAmount4())));
		frm.setCreditScheduledDate1(check.round("SONOTA", null, null,
			strToNum(frm.getStrCreditScheduledDate1())));
		frm.setCreditScheduledDate2(check.round("SONOTA", null, null,
			strToNum(frm.getStrCreditScheduledDate2())));
		frm.setCreditScheduledDate3(check.round("SONOTA", null, null,
			strToNum(frm.getStrCreditScheduledDate3())));
		frm.setPurchaseDiscountDays1(check.round("SONOTA", null, null,
			strToNum(frm.getStrPurchaseDiscountDays1())));
		frm.setPurchaseDiscountDays2(check.round("SONOTA", null, null,
			strToNum(frm.getStrPurchaseDiscountDays2())));
		frm.setPurchaseDiscountDays3(check.round("SONOTA", null, null,
			strToNum(frm.getStrPurchaseDiscountDays3())));
		frm.setTaxRatio(check.round("SONOTA", null, null, strToNum(frm
				.getStrTaxRatio())));

		// 得意先コード、請求書が一致しなければnullで登録する
		if (!StringUtils.equals(frm.getVenderCd(), frm.getPaymentInvoiceCd())){
			frm.setTsInvoicePtn(null);
		}

		if (frm.getUpdateDate() == null) {
			/* 追加処理を実行 */
			venderDetailLogic.insert(insertVender(frm, getLoginInfo(request)
					.getTantoCd()));
		} else {
			/* 更新処理を実行 */
			venderDetailLogic.update(updateVender(frm, getLoginInfo(request)
					.getTantoCd()));
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");
	}

	/**
	 * インボイス登録番号の入力チェック.
	 * @param invoiceNo インボイス番号
	 * @return boolean
	 */
	private boolean checkInoviceNo(final String invoiceNo){
		boolean errorCheckFlg = false;

		/* 桁数チェック */
		if(invoiceNo.length() != 14) {
			return errorCheckFlg;
		}

		/* 頭文字チェック */
		if(!StringUtils.equals(StringUtils.substring(invoiceNo, 0, 1), INVOICE_NO_HEAD)) {
			return errorCheckFlg;
		}

		/* 半角数字チェック(2～14桁目) */
		if(!AecStringUtils.isNumeric(StringUtils.substring(invoiceNo, 1, 13))) {
			return errorCheckFlg;
		}
		errorCheckFlg = true;
		return errorCheckFlg;
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
	 * 削除処理
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

		VenderDetailForm frm = (VenderDetailForm) form;

		/* 削除処理を実行 */
		venderDetailLogic.delete(deleteVender(frm, getLoginInfo(request)
				.getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用のVenderを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Vender
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Vender updateVender(final VenderDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Vender newBean = new Vender();

		try {
			newBean = venderDetailLogic.getEntity(frm.getVenderDivision(), frm
					.getVenderCd());
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setCreditMonthDivision(new BigDecimal("0"));
		newBean.setCreditScheduledDate(new BigDecimal("0"));
		newBean.setCreditDivision1(new BigDecimal("0"));
		newBean.setBoundAmount1(new BigDecimal("0"));
		newBean.setCreditDivision2(new BigDecimal("0"));
		newBean.setBoundAmount2(new BigDecimal("0"));
		newBean.setNoteSight1(new BigDecimal("0"));
		newBean.setNoteSight2(new BigDecimal("0"));
		newBean.setPayingCheckDivision(new BigDecimal("0"));
		newBean.setPayingCheckConditionAmount(new BigDecimal("0"));
		newBean.setSalesBasic(new BigDecimal("0"));
		newBean.setAccountsPayableUpdate(AecDateUtils.getCurrentTimestamp());
		newBean.setClosingUpdate(AecDateUtils.getCurrentTimestamp());
		newBean.setLedgerPublish(new BigDecimal("0"));
		newBean.setExpenceRatio(new BigDecimal("0"));
		newBean.setCurrencyDivision(new BigDecimal("0"));
		newBean.setNewTaxRatio(new BigDecimal("0"));
		newBean.setNewTaxApplyDate(AecDateUtils.getCurrentTimestamp());
		newBean.setPurchaseOrderDataDivision(new BigDecimal("0"));
		newBean.setFaxOutput(new BigDecimal("0"));
		newBean.setDeliverDay1(new BigDecimal("0"));
		newBean.setDeliverDay2(new BigDecimal("0"));
		newBean.setPreInfoSupplyDivision(new BigDecimal("0"));
		newBean.setBillOutsideCdPublish(new BigDecimal("0"));
		newBean.setZeroTargetPublish(new BigDecimal("0"));

		if (frm.getSubcontractLaw()) {
			newBean.setSubcontractLaw(new BigDecimal("2"));
		} else {
			newBean.setSubcontractLaw(new BigDecimal("1"));
		}

		newBean.setBankCd(frm.getBankMasterCd());
		newBean.setOtherBankCd(frm.getOtherBankMasterCd());
		newBean.setOtherAccountDivision(frm.getOtherAccountDivision());
		newBean.setOtherAccountNo(frm.getOtherAccountNo());
		newBean.setOtherAccountStockhold(frm.getOtherAccountStockhold());

		newBean.setUpdatorCd(tantoCd);

		// 2015/9/7 自動FAX対応
		newBean.setFaxOutput(frm.getFaxOutput());

		return newBean;
	}

	/**
	 * 追加処理用のVenderを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Vender
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Vender insertVender(final VenderDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Vender newBean = new Vender();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setCreditMonthDivision(new BigDecimal("0"));
		newBean.setCreditScheduledDate(new BigDecimal("0"));
		newBean.setCreditDivision1(new BigDecimal("0"));
		newBean.setBoundAmount1(new BigDecimal("0"));
		newBean.setCreditDivision2(new BigDecimal("0"));
		newBean.setBoundAmount2(new BigDecimal("0"));
		newBean.setNoteSight1(new BigDecimal("0"));
		newBean.setNoteSight2(new BigDecimal("0"));
		newBean.setPayingCheckDivision(new BigDecimal("0"));
		newBean.setPayingCheckConditionAmount(new BigDecimal("0"));
		newBean.setSalesBasic(new BigDecimal("0"));
		newBean.setAccountsPayableUpdate(AecDateUtils.getCurrentTimestamp());
		newBean.setClosingUpdate(AecDateUtils.getCurrentTimestamp());
		newBean.setLedgerPublish(new BigDecimal("0"));
		newBean.setExpenceRatio(new BigDecimal("0"));
		newBean.setCurrencyDivision(new BigDecimal("0"));
		newBean.setNewTaxRatio(new BigDecimal("0"));
		newBean.setNewTaxApplyDate(AecDateUtils.getCurrentTimestamp());
		newBean.setPurchaseOrderDataDivision(new BigDecimal("0"));
		newBean.setFaxOutput(new BigDecimal("0"));
		newBean.setDeliverDay1(new BigDecimal("0"));
		newBean.setDeliverDay2(new BigDecimal("0"));
		newBean.setPreInfoSupplyDivision(new BigDecimal("0"));
		newBean.setBillOutsideCdPublish(new BigDecimal("0"));
		newBean.setZeroTargetPublish(new BigDecimal("0"));

		if (frm.getSubcontractLaw()) {
			newBean.setSubcontractLaw(new BigDecimal("2"));
		} else {
			newBean.setSubcontractLaw(new BigDecimal("1"));
		}

		newBean.setBankCd(frm.getBankMasterCd());
		newBean.setOtherBankCd(frm.getOtherBankMasterCd());
		newBean.setOtherAccountDivision(frm.getOtherAccountDivision());
		newBean.setOtherAccountNo(frm.getOtherAccountNo());
		newBean.setOtherAccountStockhold(frm.getOtherAccountStockhold());

		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		// 2015/9/7 自動FAX対応
		newBean.setFaxOutput(frm.getFaxOutput());

		return newBean;
	}

	/**
	 * 削除処理用のVenderを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Vender
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Vender deleteVender(final VenderDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Vender newBean = new Vender();

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

		VenderDetailForm frm = (VenderDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_VENDER,
			Constants.TAB_ID_VENDER_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		String venderDivision = frm.getVenderDivision();

		frm.clear();

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		frm.setVenderDivision(venderDivision);

		if (venderDivision.equals("TS")) {
			frm.setVenderDivisionName("得意先");
		} else if (venderDivision.equals("SI")) {
			frm.setVenderDivisionName("仕入先");
		}

		/* 自社銀行口座セット */
		setBankMasterCdCombobox(frm);

		/* 売上インボイスパターンセット */
		setTsInvoicePtnCombobox(frm);

		/* 仕入インボイスパターンセット */
		setSiInvoicePtnCombobox(frm);

		/* サイト区分セット */
		setCategoryDivisionCombobox(frm, new BigDecimal("1"));
		setCategoryDivisionCombobox(frm, new BigDecimal("2"));

		frm.setBankMasterCd(frm.getBankMasterCdValues()[0]);

		/* 自社銀行検索 */
		setBankName(mapping, form, request, response);

		// 2015/9/7 自動FAX対応
		frm.setFaxOutput(BigDecimal.ONE);

		// 新規初期表示の場合、一旦コンボボックスを非活性にする
		frm.setTsInvoiceActiveFlg("false");

		return mapping.findForward("success");
	}

	/**
	 * 自社銀行検索
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward setBankName(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("setBankName");
		}

		VenderDetailForm frm = (VenderDetailForm) form;

		/* 銀行検索 */
		BankDetail beanBank = venderDetailLogic.getBankEntity(frm
				.getBankMasterCd());

		/* 自社検索 */
		CompanyDetail beanCompany = venderDetailLogic
				.getCompanyEntity("000001");

		if (beanBank == null) {
			frm.setDispBankMasterCd(null);
			frm.setDispBankName(null);
			frm.setDispBranchName(null);
		} else {
			frm.setDispBankMasterCd(beanBank.getBankMasterCd());
			frm.setDispBankName(beanBank.getBankName());
			frm.setDispBranchName(beanBank.getBranchName());
		}

		if (beanCompany == null) {
			frm.setAccountDivision(null);
			frm.setAccountNo(null);
			frm.setAccountStockhold(null);
		} else {
			if (frm.getBankMasterCd().equals(beanCompany.getBankMasterCd1())) {
				frm.setAccountDivision(beanCompany.getAccountDivision1());
				frm.setAccountNo(beanCompany.getAccountNo1());
				frm.setAccountStockhold(beanCompany.getAccountStockhold1());
			} else if (frm.getBankMasterCd().equals(
				beanCompany.getBankMasterCd2())) {
				frm.setAccountDivision(beanCompany.getAccountDivision2());
				frm.setAccountNo(beanCompany.getAccountNo2());
				frm.setAccountStockhold(beanCompany.getAccountStockhold2());
			} else if (frm.getBankMasterCd().equals(
				beanCompany.getBankMasterCd3())) {
				frm.setAccountDivision(beanCompany.getAccountDivision3());
				frm.setAccountNo(beanCompany.getAccountNo3());
				frm.setAccountStockhold(beanCompany.getAccountStockhold3());
			} else if (frm.getBankMasterCd().equals(
				beanCompany.getBankMasterCd4())) {
				frm.setAccountDivision(beanCompany.getAccountDivision4());
				frm.setAccountNo(beanCompany.getAccountNo4());
				frm.setAccountStockhold(beanCompany.getAccountStockhold4());
			}
		}

		/* 口座区分名称 */
		if (frm.getAccountDivision().equals(ACCOUNT_DIVISION1)) {
			frm.setAccountDivisionName(ACCOUNT_DIVISION_NAME1);
		} else if (frm.getAccountDivision().equals(ACCOUNT_DIVISION2)) {
			frm.setAccountDivisionName(ACCOUNT_DIVISION_NAME2);
		} else if (frm.getAccountDivision().equals(ACCOUNT_DIVISION3)) {
			frm.setAccountDivisionName(ACCOUNT_DIVISION_NAME3);
		} else {
			frm.setAccountDivisionName(null);
		}

		return mapping.findForward("success");
	}

	/**
	 * 同一コード検索
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward copy(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("copy");
		}

		VenderDetailForm frm = (VenderDetailForm) form;

		if (StringUtils.isEmpty(frm.getVenderCd())) {
			/* 取引先コードを入力して下さい。 */
			saveErrorWithArgs(request, "errors.required", "vender.vender.cd");
		} else {
			String venderDivision = null;

			if (frm.getVenderDivision().equals(TS)) {
				venderDivision = SI; /* 得意先の場合は仕入先 */
			} else {
				venderDivision = TS; /* 仕入先の場合は得意先 */
			}

			/* 取引先検索 */
			VenderDetail bean = venderDetailLogic.getDetailEntity(
				venderDivision, frm.getVenderCd());

			if (bean == null) {
				if (venderDivision.equals(TS)) {
					/* 得意先には同一コードはありません。 */
					saveError(request, "errors.vender.ts.copy");
				} else {
					/* 仕入先には同一コードはありません。 */
					saveError(request, "errors.vender.si.copy");
				}
			} else {
				/* データコピー */
				frm.setOrganizationCd(bean.getOrganizationCd()); /* 担当部署コード */
				frm.setOrganizationName(bean.getOrganizationName()); /* 担当部署名称 */
				frm.setVenderName1(bean.getVenderName1()); /* 取引先名称1 */
				frm.setVenderName2(bean.getVenderName2()); /* 取引先名称2 */
				frm.setVenderShortedName(bean.getVenderShortedName()); /* 取引先略称 */
				frm.setZipcodeNo(bean.getZipcodeNo()); /* 郵便番号 */
				frm.setCityCd(bean.getCityCd()); /* 市町村コード */
				frm.setAddress1(bean.getAddress1()); /* 住所1 */
				frm.setAddress2(bean.getAddress2()); /* 住所2 */
				frm.setAddress3(bean.getAddress3()); /* 住所3 */
			}
		}

		return mapping.findForward("success");
	}

	/**
	 * リフレッシュ
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward refresh(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("refresh");
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * venderDetailLogicを設定します。
	 * @param venderDetailLogic venderDetailLogic
	 */
	public void setVenderDetailLogic(final VenderDetailLogic venderDetailLogic) {
		this.venderDetailLogic = venderDetailLogic;
	}
}
