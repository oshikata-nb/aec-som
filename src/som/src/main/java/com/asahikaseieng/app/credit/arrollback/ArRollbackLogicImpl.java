/*
 * Created on 2008/07/30
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.credit.arrollback;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.asahikaseieng.dao.nonentity.credit.arrollback.ArRollback;
import com.asahikaseieng.dao.nonentity.credit.arrollback.ArRollbackDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProArRollbackCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 売掛ロールバック処理 ロジック実装クラス
 * @author tosco
 */
public class ArRollbackLogicImpl implements ArRollbackLogic {

	private ArRollbackDao arRollbackDao;

	/** 売掛ロールバックPROCEDURE DAO宣言 */
	private ProcedureCallDao procedureCallDao;

	/**
	 * コンストラクタ.売掛ロールバック
	 */
	public ArRollbackLogicImpl() {
	}

	/**
	 * 売掛ヘッダーからMAX(売掛締め日)の年月を取得
	 * 
	 * @param sectionCd 部門コード
	 * @return ArRollback 売掛ヘッダーデータ(売掛締め日の翌年月)
	 * @throws NoDataException NoDataException
	 */
	public ArRollback getSearch(final String sectionCd) throws NoDataException {
		checkParams(sectionCd);

		ArRollback bean = arRollbackDao.getSearch(sectionCd);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 売掛ヘッダー(ロールバック対象)検索メソッド
	 * 
	 * @param sectionCd 部門コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 売掛ヘッダーデータ
	 */
	public List<ArRollback> getSearchRbList(final String sectionCd,
			final Date cleditDate) {
		checkParams(sectionCd);

		List<ArRollback> list = arRollbackDao.getSearchRbList(sectionCd,
			cleditDate);

		return list;
	}

	/**
	 * 売掛ヘッダー(未来締めデータ)検索メソッド
	 * 
	 * @param sectionCd 部門コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 売掛ヘッダーデータ
	 */
	public List<ArRollback> getSearchList(final String sectionCd,
			final Date cleditDate) {
		checkParams(sectionCd);

		List<ArRollback> list = arRollbackDao.getSearchList(sectionCd,
			cleditDate);

		return list;
	}

	/**
	 * 消込データ検索メソッド
	 * 
	 * @param sectionCd 部門コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 検索データ
	 */
	public List<ArRollback> getSearchEraserList(final String sectionCd,
			final Date cleditDate) {
		checkParams(sectionCd);

		List<ArRollback> list = arRollbackDao.getSearchEraserList(sectionCd,
			cleditDate);

		return list;
	}

	/**
	 * グループ間相殺データ(締め未処理)検索処理
	 * 
	 * @param sectionCd 部門コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 検索データ
	 */
	public List<ArRollback> getSearchOffsetList(final String sectionCd,
			final Date offsetFromDate,final Date offsetToDate) {
		checkParams(sectionCd);

		//List<ArRollback> list = arRollbackDao.getSearchOffsetList(sectionCd,cleditDate);
		List<ArRollback> list = arRollbackDao.getSearchOffsetList(sectionCd,offsetFromDate,offsetToDate);
		return list;
	}

	/**
	 * 売掛PROCEDURE DTOセット
	 * @param frm 売掛ロールバックForm
	 * @return ProArRollbackCallDto 売掛ロールバック処理用Dto
	 */
	public ProArRollbackCallDto setProcedureDto(final ArRollbackForm frm) {
		ProArRollbackCallDto dto = new ProArRollbackCallDto();

		// 部門コード
		dto.setPStrOrganizationCd(frm.getSectionCd());
		if (frm.getSectionCd().equals("")) {
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
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

}
