/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.entity.master.names.Names;
import com.asahikaseieng.dao.entity.master.names.NamesDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderListDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionInspectionList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionInspectionListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 製造実績検索 ロジック実装クラス
 * @author tosco
 */
public class RdirectionInspectionDetailLogicImpl implements RdirectionInspectionDetailLogic {

	/** 製造実績ヘッダー操作DAO */
	private RdirectionDirectionHeaderListDao rdirectionDirectionHeaderListDao;
	/** 製造実績検査Dao */
	private RdirectionDirectionInspectionListDao rdirectionDirectionInspectionListDao;
	/** NamesDao */
	private NamesDao namesDao;

	/**
	 * コンストラクタ.製造実績検索
	 */
	public RdirectionInspectionDetailLogicImpl() {
	}

	/**
	 * ヘッダー部のデータを製造実績ヘッダーから取得する。
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @return RdirectionDirectionHeaderList 検索結果データ
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	public RdirectionDirectionHeaderList getInspectionHeader(
			final String directionNo, final BigDecimal stepNo) throws NoDataException {
		if (StringUtils.isEmpty(directionNo) || stepNo == null) {
			throw new IllegalArgumentException(
			"IllegalArgumentException : Paramater is empty.パラメータチェック.getEntity");
		}

		RdirectionDirectionHeaderList header
		= rdirectionDirectionHeaderListDao.getInspectionHeader(directionNo, stepNo);

		return header;
	}

	/**
	 * 製造実績検査-検索処理を行う.
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @param lineNo ラインNO
	 * @return RdirectionDirectionInspectionList 検索結果Bean
	 * @throws NoDataException データが存在しない例外
	 */
	public RdirectionDirectionInspectionList getEntity(final String directionNo,
			final BigDecimal stepNo, final BigDecimal lineNo) throws NoDataException {
		if (StringUtils.isEmpty(directionNo)
				|| stepNo == null || lineNo == null) {
			throw new IllegalArgumentException(
			"IllegalArgumentException : Paramater is empty.パラメータチェック.getEntity");
		}

		RdirectionDirectionInspectionList bean = rdirectionDirectionInspectionListDao
		.getEntity(directionNo, stepNo, lineNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * 各種名称マスタにデータがない場合はエラーとする。
	 * @param bean 製造実績検査Bean
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForUpdate(final RdirectionDirectionInspectionList bean) {
		ActionMessages errors = new ActionMessages();

		if (bean == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 各種名称マスタを検索
		Names nameBean = namesDao.getEntity(bean.getInspectionCd(), "STDV");

		if (nameBean == null) {
			// データが存在しない場合
			errors = RdirectionUtil.addError(errors, "errors.rdirection.no.names");
		}

		return errors;
	}

	/**
	 * 製造実績検査更新処理を行う.
	 * @param bean 製造実績検査Bean
	 * @throws Exception データが存在しない例外
	 */
	public void update(final RdirectionDirectionInspectionList bean) throws Exception {
		try {
			// 更新処理
			rdirectionDirectionInspectionListDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}



	/* -------------------- setter -------------------- */

	/**
	 * 製造実績ヘッダー操作DAOを設定します。
	 * @param rdirectionDirectionHeaderListDao 製造実績ヘッダー操作DAO
	 */
	public void setRdirectionDirectionHeaderListDao(
			final RdirectionDirectionHeaderListDao rdirectionDirectionHeaderListDao) {
		this.rdirectionDirectionHeaderListDao = rdirectionDirectionHeaderListDao;
	}
	/**
	 * 製造実績検査Daoを設定します。
	 * @param rdirectionDirectionInspectionListDao 製造実績検査Dao
	 */
	public void setRdirectionDirectionInspectionListDao(final RdirectionDirectionInspectionListDao
			rdirectionDirectionInspectionListDao) {
		this.rdirectionDirectionInspectionListDao = rdirectionDirectionInspectionListDao;
	}
	/**
	 * NamesDaoを設定します。
	 * @param namesDao NamesDao
	 */
	public void setNamesDao(final NamesDao namesDao) {
		this.namesDao = namesDao;
	}
}
