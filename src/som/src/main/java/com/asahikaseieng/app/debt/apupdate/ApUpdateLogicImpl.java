/*
 * Created on 2008/07/24
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.debt.apupdate;

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
import com.asahikaseieng.dao.nonentity.debt.apupdate.ApUpdate;
import com.asahikaseieng.dao.nonentity.debt.apupdate.ApUpdateDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProApUpdateCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;

/**
 * 買掛更新処理 ロジック実装クラス
 * @author tosco
 */
public class ApUpdateLogicImpl implements ApUpdateLogic {

	/** 仮締区分(仮締め) */
	private static final String TEMP_CLOSING_FLG_KARI = "1";

	/** 仮締区分(本締め) */
	private static final String TEMP_CLOSING_FLG_HON = "0";

	private ApUpdateDao apUpdateDao;

	/** 買掛更新PROCEDURE DAO宣言 */
	private ProcedureCallDao procedureCallDao;

	private ProcParamDao procParamDao;

	private ProcedureLogDao procedureLogDao;

	/**
	 * コンストラクタ.買掛更新
	 */
	public ApUpdateLogicImpl() {
	}

	/**
	 * 買掛ヘッダーからMAX(買掛締め日)の翌年月を取得
	 * 
	 * @param organizationCd 部署コード
	 * @return ApUpdate 買掛ヘッダーデータ(買掛締め日の翌年月)
	 * @throws NoDataException NoDataException
	 */
	public ApUpdate getSearch(final String organizationCd)
			throws NoDataException {
		checkParams(organizationCd);

		ApUpdate bean = apUpdateDao.getSearch(organizationCd);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 買掛ヘッダー存在チェックメソッド
	 * 
	 * @param organizationCd 部署コード
	 * @param cleditDate 買掛締め日
	 * @return List<ApUpdate> 買掛ヘッダーデータ
	 */
	public List<ApUpdate> getSearchList(final String organizationCd,
			final Date cleditDate) {
		checkParams(organizationCd);

		List<ApUpdate> list = apUpdateDao.getSearchList(organizationCd,
			cleditDate);

		return list;
	}

	/**
	 * 買掛PROCEDURE DTOセット
	 * @param frm 買掛更新Form
	 * @return ProArUpdateCallDto 買掛更新処理用Dto
	 */
	public ProApUpdateCallDto setProcedureDto(final ApUpdateForm frm) {
		ProApUpdateCallDto dto = new ProApUpdateCallDto();

		// 部署コード
		dto.setPStrOrganizationCd(frm.getOrganizationCd());
		if (frm.getOrganizationCd().equals("")) {
			dto.setPStrOrganizationCd(null);
		}
		// 買掛締め日
		Date payableDate = frm.getPayableDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		dto.setPStrPayableDate(sdf.format(payableDate));
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
	 * 買掛更新
	 * @param proDto 買掛更新処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 * 
	 */
	public BigDecimal callProcedure(final ProApUpdateCallDto proDto) {
		// 買掛更新処理

		procedureCallDao.proApUpdate(proDto);

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
	 */
	public void insertProcParam(final String procCd, final ApUpdateForm frm) {
		ProcParam newBean = new ProcParam();

		try {
			// パラメータ

			// 部署コード
			if (StringUtils.isEmpty(frm.getOrganizationCd())) {
				newBean.setParam1(null);
			} else {
				newBean.setParam1(frm.getOrganizationCd());
			}

			// 買掛締め日
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			newBean.setParam2(sdf.format(frm.getPayableDate()));

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
	 * apUpdateDaoを設定します。
	 * 
	 * @param apUpdateDao ApUpdateDao
	 */
	public void setApUpdateDao(final ApUpdateDao apUpdateDao) {
		this.apUpdateDao = apUpdateDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * 
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
