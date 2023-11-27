/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.dao.entity.keikakuseizo.KeikakuSeizo;
import com.asahikaseieng.dao.entity.keikakuseizo.KeikakuSeizoDao;
import com.asahikaseieng.dao.nonentity.comboboxes.direction.DirectionProcedureSetpNoForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.direction.DirectionProcedureSetpNoForComboboxesDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionKeikakuSeizoDetailDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionSijiSeizoDetailDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * 製造指図 共通ロジック実装クラス
 * @author tosco
 */
public class DirectionCommonsLogicImpl implements DirectionCommonsLogic {
	/** 指図ヘッダ操作DAO  */
	private DirectionDirectionHeaderListDao directionDirectionHeaderListDao;
	/** 製造指図フォーミュラ操作用DAO */
	private DirectionDirectionFormulaListDao directionDirectionFormulaListDao;
	/** 製造指図－工程順序コンボボックス用Dao */
	private DirectionProcedureSetpNoForComboboxesDao directionProcedureSetpNoForComboboxesDao;
	/** 製造計画用DAO */
	private DirectionKeikakuSeizoDetailDao directionKeikakuSeizoDetailDao;
	/** 製造指示用DAO */
	private DirectionSijiSeizoDetailDao directionSijiSeizoDetailDao;
	/** 在庫更新用Dao **/
	private ZaiCtrlDao zaiCtrlDao;
	/** 製造計画用Dao */
	private KeikakuSeizoDao keikakuSeizoDao;

	/**
	 * コンストラクタ
	 */
	public DirectionCommonsLogicImpl() {
	}

	/**
	 * 指図番号で検索
	 * @param directionNo 指図番号
	 * @return DirectionDirectionHeaderList
	 * @throws NoDataException 検索結果が存在しなかった場合発生
	 */
	public DirectionDirectionHeaderList findByDirectionNo(final String directionNo) throws NoDataException {
		DirectionDirectionHeaderList header = directionDirectionHeaderListDao.findByDirectionNo(directionNo);
		if (header == null) {
			//データが存在しなかった場合は例外発生
			throw new NoDataException();
		}
		return header;
	}
	/**
	 * 製造指図フォーミュラを更新する
	 * （nullなフィールド以外の値を更新する。)
	 * @param bean DirectionDirectionFormulaList
	 */
	public void updateFormulaUnlessNull(final DirectionDirectionFormulaList bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}
		try {
			//製造指図フォーミュラに更新
			int updateNum = directionDirectionFormulaListDao.updateUnlessNull(bean);
			if (updateNum != 1) {
				//排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 工程順序リストを取得する
	 * @param directionNo 指図番号
	 * @return List<DirectionProcedureSetpNoForComboboxes>
	 */
	public List<DirectionProcedureSetpNoForComboboxes> getProcedureSetpNoList(final String directionNo) {
		return directionProcedureSetpNoForComboboxesDao.findByDirectionNo(directionNo);
	}
	/**
	 * 工程順序コンボボックス作成
	 * @param directionNo 指図番号
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createProcedureSetpNoCombobox(final String directionNo,  final boolean zero) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		//0:すべてを先頭に設定
		if (zero) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues("0");
			item.setLabales("0");
			res.add(item);
		}
		if (directionNo != null) {

			//工程順序検索
			List<DirectionProcedureSetpNoForComboboxes> list
			= getProcedureSetpNoList(directionNo);
			if (list != null) {
				for (DirectionProcedureSetpNoForComboboxes bean : list) {
					ComboBoxItems item = new ComboBoxItems();
					item.setValues(bean.getStepNo().toString());
					item.setLabales(bean.getSeq().toString());
					res.add(item);
				}
			}
		}
		return res;
	}

	/**
	 * 指図ヘッダ更新処理を行う
	 * @param bean 登録対象ビーン
	 */
	public void update(final DirectionDirectionHeaderList bean) {
		if (DirectionConst.DIRECTION_STATUS_ISSUED.compareTo(bean.getDirectionStatus()) == 0) {
			//指図ステータス=指図書発行済みの場合
			String errMsg = "errors.direction.stock.update";
			try {
				// 在庫更新処理
				StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
				/* 在庫更新－包装・製造指図確定取消 */
				if (!stock.deFixDirection
					(bean.getDirectionDivision(), bean.getDirectionNo(), bean.getInputorCd())) {
					throw new LogicExceptionEx(errMsg);
				}
				/* 在庫更新－配合指図確定取消 */
				if (!stock.deFixFormula
					(bean.getDirectionDivision(), bean.getDirectionNo(), bean.getInputorCd())) {
					throw new LogicExceptionEx(errMsg);
				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}
		}
		//指図ステータスを未確定
		bean.setDirectionStatus(DirectionConst.DIRECTION_STATUS_UN_CONFIRMED);
		try {
			//指図ヘッダーに更新
			int updateNum = directionDirectionHeaderListDao.update(bean);
			if (updateNum != 1) {
				//排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			//排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}
	/**
	 * 製造計画・指示削除
	 * @param directioNo 製造指図番号
	 * @throws DirectionLogicException 削除エラー
	 */
	public void deleteSeizo(final String directioNo) throws DirectionLogicException {
		try {
			//製造計画削除
			directionKeikakuSeizoDetailDao.deleteByDirectionNo(directioNo);
			//製造指示削除
			directionSijiSeizoDetailDao.deleteByDirectionNo(directioNo);
		} catch (SQLRuntimeException e) {
			String errMsg = "errors.direction.update.if.table";
			throw new DirectionLogicException(errMsg, "");
		}
	}

	/**
	 * 製造計画の製造状況をチェックする.<br>
	 * 　製造状況が0:未処理以外はエラーとする。
	 * @param directionNo 製造指図番号
	 * @return String エラーメッセージキー
	 */
	public String checkSeizoKeikaku(final String directionNo) {
		String errMsgKey = null;

		//製造計画取得
		KeikakuSeizo bean = keikakuSeizoDao.getEntity(AecStringUtils.rightPad(directionNo, 11));
		if (bean != null) {
			// 未処理以外は、更新不可
			if (!"0".equals(bean.getSeizojokyo())) {
				errMsgKey = "errors.direction.not.update.status";
			}
		}
		return errMsgKey;
	}

	//setter------------------------------------------
	/**
	 * 指図ヘッダ操作DAOを設定します。
	 * @param directionDirectionHeaderListDao 指図ヘッダ操作DAO
	 */
	public void setDirectionDirectionHeaderListDao(
			final DirectionDirectionHeaderListDao directionDirectionHeaderListDao) {
		this.directionDirectionHeaderListDao = directionDirectionHeaderListDao;
	}
	/**
	 * 製造指図フォーミュラ操作用DAOを設定します。
	 * @param directionDirectionFormulaListDao 製造指図フォーミュラ操作用DAO
	 */
	public void setDirectionDirectionFormulaListDao(
			final DirectionDirectionFormulaListDao directionDirectionFormulaListDao) {
		this.directionDirectionFormulaListDao = directionDirectionFormulaListDao;
	}

	/**
	 * 製造指図－工程順序コンボボックス用Daoを設定します。
	 * @param directionProcedureSetpNoForComboboxesDao 製造指図－工程順序コンボボックス用Dao
	 */
	public void setDirectionProcedureSetpNoForComboboxesDao(
			final DirectionProcedureSetpNoForComboboxesDao directionProcedureSetpNoForComboboxesDao) {
		this.directionProcedureSetpNoForComboboxesDao = directionProcedureSetpNoForComboboxesDao;
	}

	/**
	 * 製造計画用DAOを設定します。
	 * @param directionKeikakuSeizoDetailDao 製造計画用DAO
	 */
	public void setDirectionKeikakuSeizoDetailDao(
			final DirectionKeikakuSeizoDetailDao directionKeikakuSeizoDetailDao) {
		this.directionKeikakuSeizoDetailDao = directionKeikakuSeizoDetailDao;
	}

	/**
	 * 製造指示用DAOを設定します。
	 * @param directionSijiSeizoDetailDao 製造指示用DAO
	 */
	public void setDirectionSijiSeizoDetailDao(
			final DirectionSijiSeizoDetailDao directionSijiSeizoDetailDao) {
		this.directionSijiSeizoDetailDao = directionSijiSeizoDetailDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * 製造計画用DAOを設定します。
	 * @param keikakuSeizoDao 製造計画用DAO
	 */
	public void setKeikakuSeizoDao(
			final KeikakuSeizoDao keikakuSeizoDao) {
		this.keikakuSeizoDao = keikakuSeizoDao;
	}

}
