/*
 * Created on 2008/10/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.credit.arrollback;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.entity.master.organization.OrganizationDao;
import com.asahikaseieng.dao.nonentity.credit.arrollback.ArRollback;
import com.asahikaseieng.dao.nonentity.credit.arrollback.ArRollbackCsmDao;
import com.asahikaseieng.dao.nonentity.credit.arrollback.ArRollbackDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProArRollbackCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 売掛ロールバック処理 ロジック実装クラス(カスタマイズ)
 * @author tosco
 */
public class ArRollbackCsmLogicImpl implements ArRollbackCsmLogic {

	private ArRollbackDao arRollbackDao;

	private ArRollbackCsmDao arRollbackCsmDao;

	/** 売掛ロールバックPROCEDURE DAO宣言 */
	private ProcedureCallDao procedureCallDao;

	/** 部署マスタ用Dao */
	private OrganizationDao organizationDao;

	/**
	 * コンストラクタ.売掛ロールバック
	 */
	public ArRollbackCsmLogicImpl() {
	}

	/**
	 * 売掛ヘッダーからMAX(売掛締め日)の年月を取得
	 * 
	 * @param organizationCd 部署コード
	 * @return ArRollback 売掛ヘッダーデータ(売掛締め日の翌年月)
	 * @throws NoDataException NoDataException
	 */
	public ArRollback getSearch(final String organizationCd)
			throws NoDataException {
		checkParams(organizationCd);

		ArRollback bean = arRollbackDao.getSearch(organizationCd);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 売掛ヘッダー(ロールバック対象)検索メソッド
	 * 
	 * @param organizationCd 部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 売掛ヘッダーデータ
	 */
	public List<ArRollback> getSearchRbList(final String organizationCd,
			final Date cleditDate) {
		checkParams(organizationCd);

		List<ArRollback> list = arRollbackDao.getSearchRbList(organizationCd,
			cleditDate);

		return list;
	}

	/**
	 * 売掛ヘッダー(未来締めデータ)検索メソッド
	 * 
	 * @param organizationCd 部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 売掛ヘッダーデータ
	 */
	public List<ArRollback> getSearchList(final String organizationCd,
			final Date cleditDate) {
		checkParams(organizationCd);

		List<ArRollback> list = arRollbackDao.getSearchList(organizationCd,
			cleditDate);

		return list;
	}

	/**
	 * 消込データ検索メソッド(カスタマイズ)
	 * 
	 * @param organizationCd 部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 検索データ
	 */
	public List<ArRollback> getSearchEraserList(final String organizationCd,
			final Date cleditDate) {
		checkParams(organizationCd);

		List<ArRollback> list = arRollbackCsmDao.getSearchEraserList(
			organizationCd, cleditDate);

		return list;
	}

	/**
	 * グループ間相殺データ(締め未処理)検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 検索データ
	 */
	public List<ArRollback> getSearchOffsetList(final String organizationCd,
			final Date offsetFromDate,final Date offsetToDate) {
		checkParams(organizationCd);
		//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　開始
		//List<ArRollback> list = arRollbackDao.getSearchOffsetList(organizationCd, cleditDate);
		List<ArRollback> list = arRollbackDao.getSearchOffsetList(organizationCd, offsetFromDate,offsetToDate);
		//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　終了

		return list;
	}

	/**
	 * 売掛PROCEDURE DTOセット
	 * @param frm 売掛ロールバックForm
	 * @return ProArRollbackCallDto 売掛ロールバック処理用Dto
	 */
	public ProArRollbackCallDto setProcedureDto(final ArRollbackCsmForm frm) {
		ProArRollbackCallDto dto = new ProArRollbackCallDto();

		// 部門コード
		dto.setPStrOrganizationCd(frm.getOrganizationCd());
		if (frm.getOrganizationCd().equals("")) {
			dto.setPStrOrganizationCd(null);
		}
		// 売掛締め日
		Date creditDate = frm.getCreditDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		dto.setPStrCreditDate(sdf.format(creditDate));
		// 登録者ＩＤ
		dto.setPStrInputorCd(frm.getTantoCd());

		return dto;
	}

	/**
	 * 売掛ロールバック
	 * @param proDto 売掛ロールバック処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 */
	public BigDecimal callProcedure(final ProArRollbackCallDto proDto) {
		// 売掛ロールバック処理
		procedureCallDao.proArRollback(proDto);

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
	 * arRollbackDaoを設定します。
	 * @param arRollbackDao ArRollbackDao
	 */
	public void setArRollbackDao(final ArRollbackDao arRollbackDao) {
		this.arRollbackDao = arRollbackDao;
	}

	/**
	 * arRollbackCsmDaoを設定します。
	 * @param arRollbackCsmDao ArRollbackCsmDao
	 */
	public final void setArRollbackCsmDao(
			final ArRollbackCsmDao arRollbackCsmDao) {
		this.arRollbackCsmDao = arRollbackCsmDao;
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

}
