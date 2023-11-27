/*
 * Created on 2008/07/24
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.credit.arupdate;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.entity.master.organization.OrganizationDao;
import com.asahikaseieng.dao.entity.procparam.ProcParam;
import com.asahikaseieng.dao.entity.procparam.ProcParamDao;
import com.asahikaseieng.dao.nonentity.common.procedurelog.ProcedureLog;
import com.asahikaseieng.dao.nonentity.common.procedurelog.ProcedureLogDao;
import com.asahikaseieng.dao.nonentity.credit.arupdate.ArUpdate;
import com.asahikaseieng.dao.nonentity.credit.arupdate.ArUpdateDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProArUpdateCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 売掛更新処理 ロジック実装クラス
 * @author tosco
 */
public class ArUpdateLogicImpl implements ArUpdateLogic {

	/** 仮締区分(仮締め) */
	private static final String TEMP_CLOSING_FLG_KARI = "1";

	/** 仮締区分(本締め) */
	private static final String TEMP_CLOSING_FLG_HON = "0";

	private ArUpdateDao arUpdateDao;

	/** 売掛更新PROCEDURE DAO宣言 */
	private ProcedureCallDao procedureCallDao;

	/** 部署マスタ用Dao */
	private OrganizationDao organizationDao;

	/** エラーログ出力用Dao */
	private ErrorLogDao errorLogDao;

	private ProcParamDao procParamDao;

	private ProcedureLogDao procedureLogDao;

	/**
	 * コンストラクタ.売掛更新
	 */
	public ArUpdateLogicImpl() {
	}

	/**
	 * 売掛ヘッダーからMAX(売掛締め日)の翌年月を取得
	 * 
	 * @param organizationCd 部署コード
	 * @return ArUpdate 売掛ヘッダーデータ(売掛締め日の翌年月)
	 * @throws NoDataException NoDataException
	 */
	public ArUpdate getSearch(final String organizationCd)
			throws NoDataException {
		checkParams(organizationCd);

		ArUpdate bean = arUpdateDao.getSearch(organizationCd);
		if (bean == null) {
			throw new NoDataException();
		}
		return bean;
	}

	/**
	 * 売掛ヘッダー存在チェックメソッド
	 * 
	 * @param organizationCd 部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArUpdate> 売掛ヘッダーデータ
	 */
	public List<ArUpdate> getSearchList(final String organizationCd,
			final Date cleditDate) {
		checkParams(organizationCd);

		List<ArUpdate> list = arUpdateDao.getSearchList(organizationCd,
			cleditDate);

		return list;
	}

	/**
	 * 売掛PROCEDURE DTOセット
	 * @param frm 売掛更新Form
	 * @return ProArUpdateCallDto 売掛更新処理用Dto
	 */
	public ProArUpdateCallDto setProcedureDto(final ArUpdateForm frm) {
		ProArUpdateCallDto dto = new ProArUpdateCallDto();

		// 部署コード
		dto.setPStrOrganizationCd(frm.getOrganizationCd());
		if (frm.getOrganizationCd().equals("")) {
			dto.setPStrOrganizationCd(null);
		}
		// 売掛締め日
		Date creditDate = frm.getCreditDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		dto.setPStrCreditDate(sdf.format(creditDate));
		// 本締区分
		if (frm.isClosingFlg()) {
			dto.setPStrTempClosingFlg(TEMP_CLOSING_FLG_HON); // 本締め
		} else {
			dto.setPStrTempClosingFlg(TEMP_CLOSING_FLG_KARI); // 仮締め
		}
		// 登録者ＩＤ
		dto.setPStrInputorCd(frm.getTantoCd());

		return dto;
	}

	/**
	 * 売掛更新
	 * @param proDto 売掛更新処理用Dto
	 * @return ProArUpdateCallDto 処理結果
	 */
	public ProArUpdateCallDto callProcedure(final ProArUpdateCallDto proDto) {
		// 売掛更新処理
		procedureCallDao.proArUpdate(proDto);

		return proDto;
	}

	/**
	 * 部署マスタ取得
	 * @param organizationCd 部署コード
	 * @return Organization 部署情報
	 */
	public Organization getOrganization(final String organizationCd) {
		return organizationDao.getEntity(organizationCd);
	}

	/**
	 * エラーログ出力処理
	 * @param errorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	public void outPutErrorLog(final BigDecimal errorCd,
			final String strErrorMsg, final String tantoCd) throws Exception {
		ErrorLog log = new ErrorLog();

		log.setModuleCd("PRO_ARUPDATE");
		log.setErrorDate(AecDateUtils.getCurrentTimestamp());
		log.setClient(tantoCd);
		if (errorCd != null) {
			log.setErrorMes(errorCd.toString());
		} else {
			log.setErrorMes("");
		}
		log.setSqlStr(strErrorMsg);

		try {
			errorLogDao.insert(log);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
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
	 */
	public void insertProcParam(final String procCd, final ArUpdateForm frm) {
		ProcParam newBean = new ProcParam();

		try {
			// パラメータ

			// 部署コード
			if (StringUtils.isEmpty(frm.getOrganizationCd())) {
				newBean.setParam1(null);
			} else {
				newBean.setParam1(frm.getOrganizationCd());
			}

			// 売掛締め日
			Date creditDate = frm.getCreditDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			newBean.setParam2(sdf.format(creditDate));

			// 本締区分
			if (frm.isClosingFlg()) {
				newBean.setParam3(TEMP_CLOSING_FLG_HON); // 本締め
			} else {
				newBean.setParam3(TEMP_CLOSING_FLG_KARI); // 仮締め
			}

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
	 * arUpdateDaoを設定します。
	 * @param arUpdateDao ArUpdateDao
	 */
	public void setArUpdateDao(final ArUpdateDao arUpdateDao) {
		this.arUpdateDao = arUpdateDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * organizationDaoを設定します。
	 * @param organizationDao OrganizationDao
	 */
	public void setOrganizationDao(final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	/**
	 * エラーログ出力用daoを設定します。
	 * @param errorLogDao エラーログ出力用dao
	 */
	public void setErrorLogDao(final ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
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
