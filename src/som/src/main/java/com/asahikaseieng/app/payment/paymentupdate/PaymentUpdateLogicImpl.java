/*
 * Created on 2008/08/04
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.payment.paymentupdate;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.procparam.ProcParam;
import com.asahikaseieng.dao.entity.procparam.ProcParamDao;
import com.asahikaseieng.dao.nonentity.common.procedurelog.ProcedureLog;
import com.asahikaseieng.dao.nonentity.common.procedurelog.ProcedureLogDao;
import com.asahikaseieng.dao.nonentity.payment.paymentupdate.PaymentUpdate;
import com.asahikaseieng.dao.nonentity.payment.paymentupdate.PaymentUpdateDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProPaymentUpdateCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;

/**
 * 支払更新処理 ロジック実装クラス
 * @author tosco
 */
public class PaymentUpdateLogicImpl implements PaymentUpdateLogic {

	private PaymentUpdateDao paymentUpdateDao;

	/** 支払更新PROCEDURE DAO宣言 */
	private ProcedureCallDao procedureCallDao;

	private ProcParamDao procParamDao;

	private ProcedureLogDao procedureLogDao;

	/**
	 * コンストラクタ.支払更新
	 */
	public PaymentUpdateLogicImpl() {
	}

	/**
	 * 支払ヘッダー検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @param venderCd 支払先コード
	 * @param paymentDate 支払締め日
	 * @return List<PaymentUpdate> 支払ヘッダーデータ
	 */
	public List<PaymentUpdate> getSearchList(final String organizationCd,
			final String venderCd, final Date paymentDate) {
		checkParams(organizationCd);

		List<PaymentUpdate> list = paymentUpdateDao.getSearchList(
			organizationCd, venderCd, paymentDate);

		return list;
	}

	/**
	 * 支払PROCEDURE DTOセット
	 * @param frm 支払更新Form
	 * @param companyCd 会社コード
	 * @return ProClUpdateCallDto 支払更新処理用Dto
	 */
	public ProPaymentUpdateCallDto setProcedureDto(final PaymentUpdateForm frm,
			final String companyCd) {
		ProPaymentUpdateCallDto dto = new ProPaymentUpdateCallDto();

		// 部署コード
		dto.setPStrOrganizationCd(frm.getOrganizationCd());
		if (frm.getOrganizationCd().equals("")) {
			dto.setPStrOrganizationCd(null);
		}
		// 支払先コード
		dto.setPStrCustomerCd(frm.getVenderCd());
		if (frm.getVenderCd().equals("")) {
			dto.setPStrCustomerCd(null);
		}
		// 支払締め日
		Date creditDate = frm.getPayableDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		dto.setPStrPayableDate(sdf.format(creditDate));
		// 登録者ＩＤ
		dto.setPStrInputorCd(frm.getTantoCd());
		// 会社コード
		dto.setPStrCompanyCd(companyCd);

		return dto;
	}

	/**
	 * 支払更新
	 * @param proDto 支払更新処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 */
	public BigDecimal callProcedure(final ProPaymentUpdateCallDto proDto) {
		// 支払更新処理
		procedureCallDao.proPaymentUpdate(proDto);

		return proDto.getPNumRet();
	}

	/**
	 * パラメータチェック.getSearchList
	 * @param condition 検索条件
	 */
	private void checkParams(final String param) {

		if (param == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearch");
		}
	}

	/**
	 * パラメータ情報取得
	 * @param procCd プロシージャ名
	 * @return ProcParam 検索結果データ
	 */
	public ProcParam getProcParam(final String procCd) {
		if (procCd == null || procCd.equals("")) {
			throw new IllegalArgumentException();
		}

		// バッチテーブルを検索
		ProcParam procParamBean = procParamDao.getEntity(procCd);

		return procParamBean;
	}

	/**
	 * ログ情報取得
	 * @param procCd プロシージャ名
	 * @param inputorCd 登録者名
	 * @return List<ProcedureLog> 検索結果データ
	 */
	public List<ProcedureLog> getProcLog(final String procCd,
			final String inputorCd) {
		if (procCd == null || inputorCd == null) {
			throw new IllegalArgumentException();
		}

		// ログファイルを検索
		List<ProcedureLog> bean = procedureLogDao.getSearchList(procCd,
			inputorCd);

		return bean;
	}

	/**
	 * パラメータ情報登録
	 * @param procCd プロシージャ名
	 * @param frm 登録データ
	 * @param companyCd 自社コード
	 */
	public void insertProcParam(final String procCd,
			final PaymentUpdateForm frm, final String companyCd) {
		ProcParam newBean = new ProcParam();

		try {
			// パラメータ

			// 部署コード
			if (StringUtils.isEmpty(frm.getOrganizationCd())) {
				newBean.setParam1(null);
			} else {
				newBean.setParam1(frm.getOrganizationCd());
			}

			// 支払先コード
			if (StringUtils.isEmpty(frm.getVenderCd())) {
				newBean.setParam2(null);
			} else {
				newBean.setParam2(frm.getVenderCd());
			}

			// 支払締め日
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			newBean.setParam3(sdf.format(frm.getPayableDate()));

			newBean.setParam4(companyCd); // 自社コード
			newBean.setInputorCd(frm.getTantoCd()); // 登録者ＩＤ

			newBean.setProcCd(procCd); // プロシージャ名
			newBean.setCheckFlg(BigDecimal.ONE); // プロシージャフラグ
			newBean.setInputDate(newBean.getCurrentTimestamp()); // 登録日時
			newBean.setInputorCd(frm.getTantoCd()); // 登録者ID
			newBean.setUpdateDate(newBean.getCurrentTimestamp()); // 更新日時
			newBean.setUpdatorCd(frm.getTantoCd()); // 更新者ID

			// 追加登録
			procParamDao.insert(newBean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * PaymentUpdateDaoを設定します。
	 * @param paymentUpdateDao PaymentUpdateDao
	 */
	public void setPaymentUpdateDao(final PaymentUpdateDao paymentUpdateDao) {
		this.paymentUpdateDao = paymentUpdateDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * procParamDaoを設定します。
	 * @param procParamDao procParamDao
	 */
	public void setProcParamDao(final ProcParamDao procParamDao) {
		this.procParamDao = procParamDao;
	}

	/**
	 * procedureLogDaoを設定します。
	 * @param procedureLogDao procedureLogDao
	 */
	public void setProcedureLogDao(final ProcedureLogDao procedureLogDao) {
		this.procedureLogDao = procedureLogDao;
	}
}
