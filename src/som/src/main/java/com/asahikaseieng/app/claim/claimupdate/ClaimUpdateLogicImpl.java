/*
 * Created on 2008/08/04
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.claim.claimupdate;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.claimupdate.ClaimUpdate;
import com.asahikaseieng.dao.nonentity.claim.claimupdate.ClaimUpdateDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProClUpdateCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;

/**
 * 請求更新処理 ロジック実装クラス
 * @author tosco
 */
public class ClaimUpdateLogicImpl implements ClaimUpdateLogic {

	/** 仮締区分(仮締め) */
	private static final String TEMP_CLOSING_FLG_KARI = "1";

	/** 仮締区分(本締め) */
	private static final String TEMP_CLOSING_FLG_HON = "0";

	private ClaimUpdateDao claimUpdateDao;

	/** 売掛更新PROCEDURE DAO宣言 */
	private ProcedureCallDao procedureCallDao;

	/**
	 * コンストラクタ.請求更新
	 */
	public ClaimUpdateLogicImpl() {
	}

	/**
	 * 請求ヘッダー検索処理
	 * 
	 * @param sectionCd 部門コード
	 * @param venderCd 請求先コード
	 * @param cleditDate 請求締め日
	 * @return List<Update> 請求ヘッダーデータ
	 */
	public List<ClaimUpdate> getSearchList(final String sectionCd,
			final String venderCd, final Date cleditDate) {
		checkParams(sectionCd);

		List<ClaimUpdate> list = claimUpdateDao.getSearchList(sectionCd,
			venderCd, cleditDate);

		return list;
	}

	/**
	 * 請求PROCEDURE DTOセット
	 * @param frm 請求更新Form
	 * @return ProClUpdateCallDto 請求更新処理用Dto
	 */
	public ProClUpdateCallDto setProcedureDto(final ClaimUpdateForm frm) {
		ProClUpdateCallDto dto = new ProClUpdateCallDto();

		// 部門コード
		dto.setPStrOrganizationCd(frm.getSectionCd());
		if (frm.getSectionCd().equals("")) {
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
		// 仮締区分
		if (frm.isTempClosingFlg()) {
			dto.setPStrTempClosingFlg(TEMP_CLOSING_FLG_KARI); // 仮締め

		} else {
			dto.setPStrTempClosingFlg(TEMP_CLOSING_FLG_HON); // 本締め

		}
		// 登録者ＩＤ
		dto.setPStrInputorCd(frm.getTantoCd());

		return dto;
	}

	/**
	 * 請求更新
	 * @param proDto 請求更新処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 */
	public BigDecimal callProcedure(final ProClUpdateCallDto proDto) {
		// 請求更新処理
		procedureCallDao.proClUpdate(proDto);

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
	 * claimUpdateDaoを設定します。
	 * @param claimUpdateDao ClaimUpdateDao
	 */
	public void setUpdateDao(final ClaimUpdateDao claimUpdateDao) {
		this.claimUpdateDao = claimUpdateDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

}
