/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.entity.master.names.Names;
import com.asahikaseieng.dao.entity.master.names.NamesDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionInspectionList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionInspectionListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 製造指図検索 ロジック実装クラス
 * @author tosco
 */
public class DirectionInspectionDetailLogicImpl implements DirectionInspectionDetailLogic {

	/** 製造指図ヘッダー操作DAO */
	private DirectionDirectionHeaderListDao directionDirectionHeaderListDao;
	/** 製造指図検査Dao */
	private DirectionDirectionInspectionListDao directionDirectionInspectionListDao;
	/** NamesDao */
	private NamesDao namesDao;
	/** 製造指図検索 ロジッククラス */
	private DirectionCommonsLogic directionCommonsLogic;

	/**
	 * コンストラクタ.製造指図検索
	 */
	public DirectionInspectionDetailLogicImpl() {
	}

	/**
	 * ヘッダー部のデータを製造指図ヘッダーから取得する。
	 * @param directionNo 指図番号
	 * @param stepNo ｽﾃｯﾌﾟNO
	 * @return DirectionDirectionHeaderList 検索結果データ
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	public DirectionDirectionHeaderList getInspectionHeader(
			final String directionNo, final BigDecimal stepNo) throws NoDataException {
		if (StringUtils.isEmpty(directionNo) || stepNo == null) {
			throw new IllegalArgumentException(
			"IllegalArgumentException : Paramater is empty.パラメータチェック.getEntity");
		}

		DirectionDirectionHeaderList header
		= directionDirectionHeaderListDao.getInspectionHeader(directionNo, stepNo);

		return header;
	}

	/**
	 * 製造指図検査-検索処理を行う.
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @param lineNo LINE_NO
	 * @return DirectionDirectionInspectionList 検索結果Bean
	 * @throws NoDataException データが存在しない例外
	 */
	public DirectionDirectionInspectionList getEntity(final String directionNo,
			final BigDecimal stepNo, final BigDecimal lineNo) throws NoDataException {
		if (StringUtils.isEmpty(directionNo)
				|| stepNo == null || lineNo == null) {
			throw new IllegalArgumentException(
			"IllegalArgumentException : Paramater is empty.パラメータチェック.getEntity");
		}

		DirectionDirectionInspectionList bean = directionDirectionInspectionListDao
		.getEntity(directionNo, stepNo, lineNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * 各種名称マスタにデータがない場合はエラーとする。
	 * @param bean 製造指図検査Bean
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForUpdate(final DirectionDirectionInspectionList bean) {
		ActionMessages errors = new ActionMessages();

		if (bean == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 各種名称マスタを検索
		Names nameBean = namesDao.getEntity(bean.getInspectionCd(), "STDV");

		if (nameBean == null) {
			// データが存在しない場合
			errors = DirectionUtil.addError(errors, "errors.direction.no.names");
		}

		return errors;
	}

	/**
	 * 製造指図検査-更新処理を行う.
	 * @param bean 製造指図検査Bean
	 * @param header 製造指図ヘッダ
	 * @throws Exception データが存在しない例外
	 */
	public void update(final DirectionDirectionInspectionList bean,
			final DirectionDirectionHeaderList header) throws Exception {

		try {
			//製造指図ヘッダ更新
			directionCommonsLogic.update(header);

			// 更新処理
			directionDirectionInspectionListDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図ヘッダー操作DAOを設定します。
	 * @param directionDirectionHeaderListDao 製造指図ヘッダー操作DAO
	 */
	public void setDirectionDirectionHeaderListDao(
			final DirectionDirectionHeaderListDao directionDirectionHeaderListDao) {
		this.directionDirectionHeaderListDao = directionDirectionHeaderListDao;
	}
	/**
	 * 製造指図検査Daoを設定します。
	 * @param directionDirectionInspectionListDao 製造指図検査Dao
	 */
	public void setDirectionDirectionInspectionListDao(final DirectionDirectionInspectionListDao
			directionDirectionInspectionListDao) {
		this.directionDirectionInspectionListDao = directionDirectionInspectionListDao;
	}
	/**
	 * NamesDaoを設定します。
	 * @param namesDao NamesDao
	 */
	public void setNamesDao(final NamesDao namesDao) {
		this.namesDao = namesDao;
	}
	/**
	 * 製造指図-共通ロジッククラスを設定します。
	 * @param directionCommonsLogic 製造指図-共通ロジッククラス
	 */
	public void setDirectionCommonsLogic(final DirectionCommonsLogic directionCommonsLogic) {
		this.directionCommonsLogic = directionCommonsLogic;
	}
}
