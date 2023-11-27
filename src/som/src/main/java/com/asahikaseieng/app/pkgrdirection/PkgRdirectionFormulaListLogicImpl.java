/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.app.direction.DirectionUtil;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormula;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormulaDao;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionFormulaLotInventoryList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionFormulaLotInventoryListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 包装実績－配合一覧画面 ロジック実装クラス
 * @author tosco
 */
public class PkgRdirectionFormulaListLogicImpl implements
		PkgRdirectionFormulaListLogic {

	/** 包装実績－製造指図フォーミュラDao */
	private PkgRdirectionDirectionFormulaListDao pkgRdirectionDirectionFormulaListDao;

	/** 製造指図ヘッダDao */
	private DirectionHeaderDao directionHeaderDao;

	/** 品目マスタ検索Dao */
	private ItemDao itemDao;

	/** 包装実績共通ロジッククラス */
	private PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic;

	/** 数値桁数チェックロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** 配合データ取得用Dao * */
	private DirectionFormulaDao directionFormulaDao;

	/** ﾛｯﾄ番号を元にﾛｯﾄ在庫のﾛｹｰｼｮﾝを取得用Dao * */
	private RdirectionFormulaLotInventoryListDao rdirectionFormulaLotInventoryListDao;

	/**
	 * コンストラクタ
	 */
	public PkgRdirectionFormulaListLogicImpl() {
	}

	/**
	 * 配合一覧検索処理
	 * @param frm 包装実績－配合一覧画面 Form
	 * @return List<PkgRdirectionDirectionFormulaList> データ
	 * @throws NoDataException データなし
	 */
	public List<PkgRdirectionDirectionFormulaList> getSearchList(
			final PkgRdirectionFormulaListForm frm) throws NoDataException {
		checkParams(frm);

		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String directionNo = frm.getDirectionNo();
		BigDecimal stepNo = new BigDecimal(frm.getProcStepNo());

		List<PkgRdirectionDirectionFormulaList> list = pkgRdirectionDirectionFormulaListDao
				.getList(directionDivision, directionNo, stepNo);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 配合一覧編集処理
	 * @param request リクエスト
	 * @param frm 包装実績－配合一覧画面 Form
	 */
	public void editList(final HttpServletRequest request,
			final PkgRdirectionFormulaListForm frm) {
		BigDecimal multiple = null;
		String unitDiv = PkgRdirectionConst.UNIT_DIVISION_HAIGO;
		BigDecimal roundDivision = new BigDecimal(
				CheckDigitUtilsLogic.ROUND_DOWN_UP); // 切上げ
		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String directionNo = frm.getDirectionNo();

		PkgRdirectionDirectionFormulaList finishBean = pkgRdirectionDirectionFormulaListDao
				.getFinishQty(directionDivision, directionNo);
		if (finishBean != null) {
			DirectionHeader headerBean = directionHeaderDao.getEntity(
				directionDivision, directionNo);
			BigDecimal resultQty = finishBean.getResultQty();
			if (resultQty != null && resultQty.compareTo(BigDecimal.ZERO) != 0) {

				// 数値桁数マスタの設定を取得
				NumberChkDisitDetail check = checkDigitUtilsLogic
						.getCheckDigit(unitDiv, null, null);

				// 丸めモードを取得
				RoundingMode round = checkDigitUtilsLogic.getRoundingMode(check
						.getRoundDivision().intValue());

				// 少数点以下桁数を取得
				// int decPos = check.getSmallnumLength().intValue();

				if (headerBean.getPlanedQty() != null
						&& headerBean.getPlanedQty().compareTo(BigDecimal.ZERO) != 0) {
					// 仕上実績数量÷生産予定数量で比率を算出
					multiple = resultQty.divide(headerBean.getPlanedQty(),
						Constants.SYOSU_KETA, round);
				}
			}
		}
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (PkgRdirectionDirectionFormulaList bean : frm.getSearchFormList()) {

			// 用途を設定
			bean.setRcpUse(frm.getRecipeUse());

			// 予定数量を設定
			bean.setStrQty(checker.format(
				PkgRdirectionConst.UNIT_DIVISION_HAIGO, bean.getQty()));

			// 使用数を設定(実績数量が未設定の場合は、初期値を自動計算)
			BigDecimal qty = bean.getResultQty();
			if (qty == null) {
				if (multiple != null) {

					// 比率を掛ける
					qty = bean.getQty().multiply(multiple);
					// 切り上げ
					qty = checkDigitUtilsLogic.round(unitDiv, null, null, qty,
						roundDivision);
				}
			}
			bean.setStrResultQty(checker.format(
				PkgRdirectionConst.UNIT_DIVISION_HAIGO, qty));
			if (bean.getStrResultQty() == null) {
				bean.setStrResultQty("");
			}

			// ｻﾝﾌﾟﾙを設定
			bean.setStrSampleQty(checker.format(
				PkgRdirectionConst.UNIT_DIVISION_HAIGO, bean.getSampleQty()));

			// ロスを設定
			bean.setStrLossQty(checker.format(
				PkgRdirectionConst.UNIT_DIVISION_HAIGO, bean.getLossQty()));

			// 不良を設定
			bean.setStrDefectQty(checker.format(
				PkgRdirectionConst.UNIT_DIVISION_HAIGO, bean.getDefectQty()));

			// 調整を設定
			bean.setStrAdjustQty(checker.format(
				PkgRdirectionConst.UNIT_DIVISION_HAIGO, bean.getAdjustQty()));

			// 入荷ロットNo
			if (bean.getLotNo() == null) {
				bean.setLotNo("");
			}
			// 品目名称
			if (bean.getItemName() == null) {
				bean.setItemName("");
			}
		}
	}

	/**
	 * 行削除、行追加時のチェックを行う
	 * @param frm 包装実績－配合一覧画面 Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForAddDelList(
			final PkgRdirectionFormulaListForm frm) {
		ActionMessages errMsgs = new ActionMessages();
		ActionMessage errMsg = null;

		// 工程データ未登録の場合エラー
		if (frm.getSeqCombo().size() == 1) {
			errMsg = new ActionMessage("errors.pkgrdirection.no.procedure");
			errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			frm.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行削除処理はエラー
			if (frm.getSearchFormList() == null) {
				errMsg = new ActionMessage("errors.pkgrdirection.no.search");
				errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
				frm.setDirtyFlg(null);
			}
		}
		return errMsgs;
	}

	/**
	 * 登録時のチェックを行う
	 * @param frm 包装実績－配合一覧画面 Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final PkgRdirectionFormulaListForm frm) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		ActionMessages errMsgs = new ActionMessages();
		ActionMessage errMsg = null;

		if (frm.getSearchFormList() == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 品目マスタ存在チェック
		for (int i = 0; i < frm.getSearchFormList().size(); i++) {
			PkgRdirectionDirectionFormulaList bean = frm.getSearchFormList()
					.get(i);

			// 品目コード必須チェック
			if (StringUtils.isEmpty(bean.getItemCd())) {
				errMsgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.required.row",
						rb.getString("item.pkgrdirection.formula.item.cd"),
						Integer.toString(i + 1)));
				continue;
			}

			// 品目マスタを検索
			Item itemBean = itemDao.getEntity(bean.getItemCd());
			if (itemBean == null) {
				// データが存在しない場合
				errMsg = new ActionMessage("errors.pkgrdirection.no.item.row",
						String.valueOf(i + 1));
				errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			}

			// 親品目チェック(詰替・貼替時のみ)
			if (PkgRdirectionConst.DIRECTION_DIVISION_REPACK.toString().equals(
				frm.getDirectionDivision())) {
				// 種別＝0:製品の場合
				if (PkgRdirectionConst.TYPE_DIVISION_PRODUCT.equals(itemBean
						.getTypeDivision())) {
					// 親品目コードが異なる場合
					if (!convertNullToBlank(frm.getParentItemCd()).equals(
						convertNullToBlank(itemBean.getParentItemCd()))) {
						errMsg = new ActionMessage(
								"errors.pkgrdirection.not.equal.parent.item.row",
								String.valueOf(i + 1));
						errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
					}
				}
			}
		}
		return errMsgs;
	}

	/**
	 * 製造指図フォーミュラ登録処理
	 * @param frm 包装実績－配合一覧画面 Form
	 * @param tantoCd 登録者
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final PkgRdirectionFormulaListForm frm,
			final String tantoCd) throws Exception {
		StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
		String errMsg = "errors.pkgrdirection.stock.update";

		try {
			BigDecimal directionDivision = new BigDecimal(frm
					.getDirectionDivision());
			String directionNo = frm.getDirectionNo();

			// 製造指図ヘッダー検索
			DirectionHeader headerBean = directionHeaderDao.getEntity(
				directionDivision, directionNo);
			if (headerBean == null) {

				// 更新データなし
				throw new OptimisticLockRuntimeException();
			}

			// 製造指図ヘッダーを包装実績入力済に更新する
			pkgRdirectionCommonsLogic.updateInputResultHeader(frm
					.getDirectionStatus(), directionDivision, directionNo, frm
					.getHeaderUpdateDate(), tantoCd);

			// 行削除されたデータをテーブルより削除する
			if (frm.getDelFormList() != null) {
				for (PkgRdirectionDirectionFormulaList bean : frm
						.getDelFormList()) {
					if (bean.getStrSeq() != null
							&& !bean.getStrSeq().equals("")) {
						try {
							if (bean.getStockpdQty() != null) {
								/* 在庫更新－配合実績取消(一投入) */
								if (!stock.deResultFormula(bean.getStepNo(),
									bean.getLineNo(), directionDivision, bean
											.getDirectionNo(), tantoCd)) {
									throw new LogicExceptionEx(errMsg);
								}
							}

							// 削除処理
							int delNum = pkgRdirectionDirectionFormulaListDao
									.delete(bean);
							if (delNum != 1) {
								// 排他エラー
								throw new OptimisticLockRuntimeException();
							}
						} catch (LogicExceptionEx e) {
							throw new LogicExceptionEx(errMsg);
						}
					}
				}
			}

			Map<String, List<PkgRdirectionDirectionFormulaList>> stepNoBeanMap = Collections
					.synchronizedMap(new HashMap<String, List<PkgRdirectionDirectionFormulaList>>());

			// STEP_NO単位のリストを格納するマップを作成する
			for (PkgRdirectionDirectionFormulaList bean : frm
					.getSearchFormList()) {

				List<PkgRdirectionDirectionFormulaList> tempList = stepNoBeanMap
						.get(bean.getStrStepNo());
				if (tempList == null) {
					// 該当のリストが存在しない場合は、新規にリストを作成しマップに登録する

					// リストを作成
					List<PkgRdirectionDirectionFormulaList> stepNoBeanList = new ArrayList<PkgRdirectionDirectionFormulaList>();

					// リストに追加
					stepNoBeanList.add(bean);

					// マップに登録
					stepNoBeanMap.put(bean.getStrStepNo(), stepNoBeanList);

				} else {
					// 該当のリストが存在する場合は、そのリストに追加する

					// リストに追加
					tempList.add(bean);
				}

			}
			PkgRdirectionDirectionFormulaList lineBean = null;
			PkgRdirectionDirectionFormulaList seqBean = null;

			// STEP_NOごとに更新処理を行う
			for (Map.Entry<String, List<PkgRdirectionDirectionFormulaList>> stepNoBeanList : stepNoBeanMap
					.entrySet()) {

				// キー（STEP_NO）を取得
				BigDecimal stepNo = new BigDecimal(stepNoBeanList.getKey());
				// 値（List<PkgRdirectionDirectionFormulaList>）を取得
				List<PkgRdirectionDirectionFormulaList> beanList = stepNoBeanList
						.getValue();

				// 最終LINE_NO を取得
				lineBean = pkgRdirectionDirectionFormulaListDao.getLastLineNo(
					directionDivision, directionNo, stepNo);
				BigDecimal lastLineNo = lineBean.getLastLineNo();

				// 最終SEQ を取得
				seqBean = pkgRdirectionDirectionFormulaListDao.getLastSeq(
					directionDivision, directionNo, stepNo);
				BigDecimal lastSeqNo = seqBean.getLastSeq();

				for (int i = 0; i < stepNoBeanList.getValue().size(); i++) {

					PkgRdirectionDirectionFormulaList bean = stepNoBeanList
							.getValue().get(i);

					// ｻﾌﾞｽﾃｯﾌﾟ（表示順）の振り直し
					if ((!frm.getTempProcStepNo().equals("0"))
							&& (!frm.getTempProcStepNo().equals(
								bean.getStrStepNo()))) {
						bean.setSeq(lastSeqNo);
						lastSeqNo = lastSeqNo.add(BigDecimal.ONE);
					} else {
						bean.setSeq(new BigDecimal(i + 1));
					}

					// STEP_NO, LINE_NO, LINE_TYPE 更新
					if (bean.getStrSeq() == null || bean.getStrSeq().equals("")) {
						bean.setLineNo(lastLineNo);
						lastLineNo = lastLineNo.add(BigDecimal.ONE);
					}
				}

				// 更新・登録処理
				insertBean(tantoCd, beanList);
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 製造指図フォーミュラの更新・登録処理
	 * @param tantoCd 更新者
	 * @param beanList 配合一覧リスト
	 */
	private void insertBean(final String tantoCd,
			final List<PkgRdirectionDirectionFormulaList> beanList) {

		// 更新処理
		for (PkgRdirectionDirectionFormulaList bean : beanList) {
			bean.setUpdatorCd(tantoCd); // 更新者コード

			if (bean.getStrSeq() == null || bean.getStrSeq().equals("")) {

				// 登録情報
				Timestamp inputDate = AecDateUtils.getCurrentTimestamp();
				bean.setStepNo(new BigDecimal(bean.getStrStepNo()));
				bean.setQty(BigDecimal.ZERO);
				bean.setStockpdQty(null);
				bean.setLotNo(null);
				bean.setManufacturerLotNo(null);
				bean.setLineType(PkgRdirectionConst.LINE_TYPE_COMBINE);
				bean.setUpdateDate(inputDate); // 更新日時
				bean.setInputorCd(tantoCd); // 登録者コード
				bean.setInputDate(inputDate); // 登録日時

				// 登録処理
				int insertNum = pkgRdirectionDirectionFormulaListDao
						.insert(bean);
				if (insertNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			} else {
				// 更新処理
				int updateNum = pkgRdirectionDirectionFormulaListDao
						.update(bean);
				if (updateNum != 1) {
					// 排他エラー
					throw new OptimisticLockRuntimeException();
				}

			}
		}
	}

	/**
	 * パラメータチェック
	 * @param recipeId レシピインデックス
	 */
	private void checkParams(final PkgRdirectionFormulaListForm frm) {
		if (frm == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : frm is empty.パラメータチェック.getSearchList");
		}
		if (frm.getDirectionDivision() == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : directionDivision is empty.パラメータチェック.getSearchList");
		}
		if (frm.getDirectionNo() == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : directionNo is empty.パラメータチェック.getSearchList");
		}
		if (frm.getProcStepNo() == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : procStepNo is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 
	 * 文字列がNULLの場合、ブランク("")に変換して返す。
	 * @param value 文字列
	 * @return String 変換文字列
	 */
	private String convertNullToBlank(final String value) {
		String ret = "";
		if (StringUtils.isEmpty(value)) {
			return ret;
		}
		return value;
	}

	/**
	 * 投入実績取消.
	 * @param frm 包装実績－配合一覧画面 Form
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void reset(final PkgRdirectionFormulaListForm frm,
			final String tantoCd) throws Exception {
		String errMsg = "errors.pkgrdirection.stock.update";
		try {
			StockinoutForDirection stock = new StockinoutForDirection(
					zaiCtrlDao);

			// 在庫引落リセット対象を取得
			PkgRdirectionDirectionFormulaList bean = frm.getSearchFormList()
					.get(Integer.parseInt(frm.getLine()));
			/* 在庫更新－配合実績取消(一投入) */
			if (!stock.deResultFormula(bean.getStepNo(), bean.getLineNo(), bean
					.getDirectionDivision(), bean.getDirectionNo(), tantoCd)) {
				throw new LogicExceptionEx(errMsg);
			}
			bean.setLotNo(null);
			bean.setStockpdQty(BigDecimal.ZERO);
			bean.setResultQty(null);
			bean.setSampleQty(null);
			bean.setAdjustQty(null);
			bean.setLossQty(null);
			bean.setDefectQty(null);
			bean.setLocationCd(null);
			bean.setFillResultQty(null);
			bean.setManufacturerLotNo(null);
			bean.setUpdatorCd(tantoCd);
			bean.setStrResultQty(null);
			bean.setStrSampleQty(null);
			bean.setStrAdjustQty(null);
			bean.setStrLossQty(null);
			bean.setStrDefectQty(null);

			// 更新処理
			int updateNum = pkgRdirectionDirectionFormulaListDao.update(bean);
			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}

		} catch (LogicExceptionEx e) {
			throw new LogicExceptionEx(errMsg);
		}

	}

	/**
	 * 更新時に開始、終了の実績日時が入っているかチェックする 開始、終了の実績日時がNullの場合エラー
	 * @param directionNo 製造指図No
	 * @param directionDivision 製造指図区分
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForResultDate(final String directionDivision,
			final String directionNo) {
		ActionMessages errors = new ActionMessages();

		if (directionNo == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 製造、包装用ヘッダを検索
		DirectionHeader opeBean = directionHeaderDao.getEntity(new BigDecimal(
				directionDivision), directionNo);

		if (opeBean.getResultSdate() == null
				|| opeBean.getResultEdate() == null) {
			// データが存在しない場合
			errors = DirectionUtil.addError(errors,
				"errors.pkgrdirection.result.date.null");
		}

		return errors;
	}

	/**
	 * 実績を変更時、製造配合とロット在庫のロケーションが異なる場合エラーを表示
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @param lineNo LINE_NO
	 * @param directionDivision directionDivision
	 * @return ActionMessages エラー内容
	 */
	public boolean checkForFormulaResultDate(final String directionNo,
			final BigDecimal stepNo, final BigDecimal lineNo,
			final BigDecimal directionDivision) {
		boolean ret = true;

		// ライン番号とステップ番号が無い場合はチェックを行わない
		if (lineNo == null || stepNo == null) {
			return ret;
		}

		DirectionFormula opeBean = directionFormulaDao.getEntity(
			directionDivision, directionNo, lineNo, stepNo);

		// ﾛｯﾄとﾛｹがある場合戻しあり
		if (opeBean.getLotNo() != null && opeBean.getLocationCd() != null) {

			// ﾛｯﾄ番号でﾛｯﾄ在庫を検索
			List<RdirectionFormulaLotInventoryList> locationBean = rdirectionFormulaLotInventoryListDao
					.getLotList(opeBean.getLotNo());

			// ﾛｯﾄ番号でﾛｯﾄ在庫を検索し検索結果がある場合
			if (!locationBean.isEmpty()) {

				// ﾛｯﾄ番号でﾛｯﾄ在庫を検索し検索結果が１件の場合
				if (locationBean.size() == 1) {

					// 現在の配合のﾛｹとﾛｯﾄ在庫のﾛｹが異なる場合エラー
					if (!opeBean.getLocationCd().equals(
						locationBean.get(0).getLocationCd())) {

						ret = false;

					}
				}
			}
		}
		return ret;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装実績－製造指図フォーミュラDao設定
	 * @param pkgRdirectionDirectionFormulaListDao 包装実績－製造指図フォーミュラDao
	 */
	public void setPkgRdirectionDirectionFormulaListDao(
			final PkgRdirectionDirectionFormulaListDao pkgRdirectionDirectionFormulaListDao) {
		this.pkgRdirectionDirectionFormulaListDao = pkgRdirectionDirectionFormulaListDao;
	}

	/**
	 * 製造指図ヘッダーDao設定.
	 * @param directionHeaderDao 製造指図ヘッダーDao
	 */
	public void setDirectionHeaderDao(
			final DirectionHeaderDao directionHeaderDao) {
		this.directionHeaderDao = directionHeaderDao;
	}

	/**
	 * 品目マスタDaoを設定します。
	 * @param itemDao 品目マスタDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * 包装実績共通ロジッククラスを設定します。
	 * @param pkgRdirectionCommonsLogic 包装実績共通ロジッククラス
	 */
	public void setPkgRdirectionCommonsLogic(
			final PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic) {
		this.pkgRdirectionCommonsLogic = pkgRdirectionCommonsLogic;
	}

	/**
	 * 数値桁数チェックロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェックロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * 製造配合用Daoを設定します。
	 * @param directionFormulaDao 製造配合用Dao
	 */
	public void setDirectionFormulaDao(
			final DirectionFormulaDao directionFormulaDao) {
		this.directionFormulaDao = directionFormulaDao;
	}

	/**
	 * rdirectionFormulaLotInventoryListDaoを設定します。
	 * @param rdirectionFormulaLotInventoryListDao
	 *            rdirectionFormulaLotInventoryListDao
	 */
	public void setRdirectionFormulaLotInventoryListDao(
			final RdirectionFormulaLotInventoryListDao rdirectionFormulaLotInventoryListDao) {
		this.rdirectionFormulaLotInventoryListDao = rdirectionFormulaLotInventoryListDao;
	}

}
