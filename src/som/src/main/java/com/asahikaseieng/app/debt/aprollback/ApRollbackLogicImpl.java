/*
 * Created on 2008/07/30
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.debt.aprollback;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.asahikaseieng.dao.nonentity.debt.aprollback.ApRollback;
import com.asahikaseieng.dao.nonentity.debt.aprollback.ApRollbackDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProApRollbackCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 買掛ロールバック処理 ロジック実装クラス
 * @author tosco
 */
public class ApRollbackLogicImpl implements ApRollbackLogic {

	private ApRollbackDao apRollbackDao;

	/** 買掛ロールバックPROCEDURE DAO宣言 */
	private ProcedureCallDao procedureCallDao;

	/**
	 * コンストラクタ.買掛ロールバック
	 */
	public ApRollbackLogicImpl() {
	}

	/**
	 * 買掛ヘッダーからMAX(買掛締め日)の年月を取得
	 * 
	 * @param organizationCd 部署コード
	 * @return ApRollback 買掛ヘッダーデータ(買掛締め日の翌年月)
	 * @throws NoDataException NoDataException
	 */
	public ApRollback getSearch(final String organizationCd)
			throws NoDataException {
		checkParams(organizationCd);

		ApRollback bean = apRollbackDao.getSearch(organizationCd);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 買掛ヘッダー(ロールバック対象)検索メソッド
	 * 
	 * @param organizationCd 部署コード
	 * @param payableDate 買掛締め日
	 * @return List<ApRollback> 買掛ヘッダーデータ
	 */
	public List<ApRollback> getSearchRbList(final String organizationCd,
			final Date payableDate) {
		checkParams(organizationCd);

		List<ApRollback> list = apRollbackDao.getSearchRbList(organizationCd,
			payableDate);

		return list;
	}

	/**
	 * 買掛ヘッダー(未来締めデータ)検索メソッド
	 * 
	 * @param organizationCd 部署コード
	 * @param payableDate 買掛締め日
	 * @return List<ApRollback> 買掛ヘッダーデータ
	 */
	public List<ApRollback> getSearchList(final String organizationCd,
			final Date payableDate) {
		checkParams(organizationCd);

		List<ApRollback> list = apRollbackDao.getSearchList(organizationCd,
			payableDate);

		return list;
	}

	/**
	 * 消込データ検索メソッド
	 * 
	 * @param organizationCd 部署コード
	 * @param payableDate 買掛締め日
	 * @return List<ApRollback> 検索データ
	 */
	public List<ApRollback> getSearchEraserList(final String organizationCd,
			final Date payableDate) {
		checkParams(organizationCd);

		List<ApRollback> list = apRollbackDao.getSearchEraserList(
			organizationCd, payableDate);

		return list;
	}

	/**
	 * グループ間相殺データ(締め未処理)検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @return List<ApRollback> 検索データ
	 */
	public List<ApRollback> getSearchOffsetList(final String organizationCd,final Date offsetFromDate,final Date offsetToDate) {
		checkParams(organizationCd);

		List<ApRollback> list = apRollbackDao
				.getSearchOffsetList(organizationCd,offsetFromDate,offsetToDate);

		return list;
	}

	/**
	 * 買掛PROCEDURE DTOセット
	 * @param frm 買掛ロールバックForm
	 * @return ProArRollbackCallDto 買掛ロールバック処理用Dto
	 */
	public ProApRollbackCallDto setProcedureDto(final ApRollbackForm frm) {
		ProApRollbackCallDto dto = new ProApRollbackCallDto();

		// 部署コード

		dto.setPStrOrganizationCd(frm.getOrganizationCd());
		if (frm.getOrganizationCd().equals("")) {
			dto.setPStrOrganizationCd(null);
		}
		// 買掛締め日
		Date payableDate = frm.getPayableDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		dto.setPStrPayableDate(sdf.format(payableDate));
		// 登録者ＩＤ
		dto.setPStrInputorCd(frm.getTantoCd());

		return dto;
	}

	/**
	 * 買掛ロールバック
	 * @param proDto 買掛ロールバック処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 */
	public BigDecimal callProcedure(final ProApRollbackCallDto proDto) {
		// 買掛ロールバック処理

		procedureCallDao.proApRollback(proDto);

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

	/* -------------------- setter -------------------- */

	/**
	 * apRollbackDaoを設定します。
	 * @param apRollbackDao ApRollbackDao
	 */
	public void setApRollbackDao(final ApRollbackDao apRollbackDao) {
		this.apRollbackDao = apRollbackDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

}
