/*
 * Created on 2008/08/06
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.claim.claimrollback;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.entity.master.organization.OrganizationDao;
import com.asahikaseieng.dao.nonentity.claim.claimrollback.ClaimRollback;
import com.asahikaseieng.dao.nonentity.claim.claimrollback.ClaimRollbackCsmDao;
import com.asahikaseieng.dao.nonentity.claim.claimrollback.ClaimRollbackDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProClRollbackCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallCsmDao;

/**
 * 請求更新ロールバック処理 ロジック実装クラス(カスタマイズ)
 * @author tosco
 */
public class ClaimRollbackCsmLogicImpl implements ClaimRollbackCsmLogic {

	private ClaimRollbackDao claimRollbackDao;

	private ClaimRollbackCsmDao claimRollbackCsmDao;

	/** 請求ロールバックPROCEDURE DAO宣言 */
	private ProcedureCallCsmDao procedureCallDao;

	/** 部署マスタ用Dao */
	private OrganizationDao organizationDao;

	/**
	 * コンストラクタ.請求更新ロールバック(カスタマイズ)
	 */
	public ClaimRollbackCsmLogicImpl() {
	}

	/**
	 * 請求ヘッダー(ロールバック対象)検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @param venderCd 請求先コード
	 * @param cleditDate 請求締め日
	 * @return List<ClaimRollback> 請求ヘッダーデータ
	 */
	public List<ClaimRollback> getSearchRbList(final String organizationCd,
			final String venderCd, final Date cleditDate) {
		checkParams(organizationCd);

		List<ClaimRollback> list = claimRollbackDao.getSearchRbList(
			organizationCd, venderCd, cleditDate);

		return list;
	}

	/**
	 * 請求ヘッダー(未来締めデータ)検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @param venderCd 請求先コード
	 * @param cleditDate 請求締め日
	 * @return List<ClaimRollback> 請求ヘッダーデータ
	 */
	public List<ClaimRollback> getSearchList(final String organizationCd,
			final String venderCd, final Date cleditDate) {
		checkParams(organizationCd);

		List<ClaimRollback> list = claimRollbackDao.getSearchList(
			organizationCd, venderCd, cleditDate);

		return list;
	}

	/**
	 * 消込データ検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @param venderCd 請求先コード
	 * @param cleditDate 請求締め日
	 * @return List<ClaimRollback> 検索データ
	 */
	public List<ClaimRollback> getSearchEraserList(final String organizationCd,
			final String venderCd, final Date cleditDate) {
		checkParams(organizationCd);

		List<ClaimRollback> list = claimRollbackCsmDao.getSearchEraserList(
			organizationCd, venderCd, cleditDate);

		return list;
	}

	/**
	 * 請求更新ロールバックPROCEDURE DTOセット
	 * @param frm 請求更新ロールバックForm
	 * @return ClaimCsmRollbackForm 請求更新ロールバック処理用Dto
	 */
	public ProClRollbackCallDto setProcedureDto(final ClaimRollbackCsmForm frm) {
		ProClRollbackCallDto dto = new ProClRollbackCallDto();

		// 部署コード
		dto.setPStrOrganizationCd(frm.getOrganizationCd());
		if (frm.getOrganizationCd().equals("")) {
			dto.setPStrOrganizationCd(null);
		}
		// 請求先コード
		dto.setPStrVenderCd(frm.getVenderCd());
		if (frm.getVenderCd().equals("")) {
			dto.setPStrVenderCd(null);
		}
		// 請求締め日
		Date creditDate = frm.getCreditDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		dto.setPStrCreditDate(sdf.format(creditDate));
		// 登録者ＩＤ
		dto.setPStrInputorCd(frm.getTantoCd());

		return dto;
	}

	/**
	 * 請求更新ロールバック
	 * @param proDto 請求更新ロールバック処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 */
	public BigDecimal callProcedure(final ProClRollbackCallDto proDto) {
		// 請求更新ロールバック処理
		procedureCallDao.proClRollbackCsm(proDto);

		return proDto.getPNumRet();
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
	 * claimRollbackDaoを設定します。
	 * @param claimRollbackDao ClaimRollbackDao
	 */
	public void setClaimRollbackDao(final ClaimRollbackDao claimRollbackDao) {
		this.claimRollbackDao = claimRollbackDao;
	}

	/**
	 * claimRollbackCsmDaoを設定します。
	 * @param claimRollbackCsmDao ClaimRollbackCsmDao
	 */
	public final void setClaimRollbackCsmDao(
			final ClaimRollbackCsmDao claimRollbackCsmDao) {
		this.claimRollbackCsmDao = claimRollbackCsmDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao ProcedureCallCsmDao
	 */
	public void setProcedureCallDao(final ProcedureCallCsmDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * organizationDaoを設定します。
	 * @param organizationDao OrganizationDao
	 */
	public void setOrganizationDao(final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

}
