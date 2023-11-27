/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package org.seasar.dao.pager;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

import org.seasar.extension.jdbc.impl.ResultSetWrapper;
import org.seasar.framework.exception.SQLRuntimeException;
import org.seasar.framework.log.Logger;

/**
 * org.seasar.dao.pager.PagerResultSetWrapper を閾値対応したWrapperクラス.<br>
 * (s2daoのソースを改造している為コーディング規約に沿っていません).
 * 
 * ---------- original explain ---------- *
 * ページャ用のResultSetラッパー。
 * <p>
 * 検索条件オブジェクトのoffset位置から、limitまでの範囲の結果を nextメソッドで返します。
 * <p>
 * limitが-1の場合、全ての結果をnextメソッドで返します。
 * 
 * @author Toshitaka Agata(Nulab,inc.)
 * -------------------------------------- *
 */
class ThresholdPagerResultSetWrapper extends ResultSetWrapper {

    /* ログ */
    private static final Logger LOGGER = Logger
            .getLogger(ThresholdPagerResultSetWrapper.class);

    /* カウント */
    private int counter = 0;

    /* オリジナルのResultSet */
    private ResultSet original;

    /* 検索条件オブジェクト */
    private PagerCondition condition;

    /* absoluteメソッドを使用するかどうかのフラグ */
    private boolean useAbsolute = true;

    /**
     * コンストラクタ.
     * 
     * @param original
     *            オリジナルのResultSet
     * @param condition
     *            検索条件オブジェクト
     * @param useAbsolute
     * @throws SQLException
     */
    public ThresholdPagerResultSetWrapper(final ResultSet original,
    		final PagerCondition condition,
    		final boolean useAbsolute) {
        super(original);
        this.original = original;
        this.condition = condition;
        this.useAbsolute = useAbsolute;
        moveOffset();
    }

    /**
     * 開始位置までカーソルを進めます。
     * 
     * @throws SQLException
     */
    private void moveOffset() {
        if (isUseCursor()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("S2Pager use scroll cursor.");
            }
            try {

            	// 最終ページ(の1行目)が無くなっていたら、最新の最終ページ(の1行目)を設定し直す
				if (!original.absolute(condition.getOffset() + 1)) {
					original.last();
					int row = original.getRow();
					int limit = condition.getLimit();
					int adj = 0;
					if (0 == row % limit) {
						adj = 1;
					}
					condition.setOffset(((row / limit) - adj) * limit);
				}

                if (0 == condition.getOffset()) {
                    original.beforeFirst();
                } else {
                    original.absolute(condition.getOffset());
                }
                counter = original.getRow();
            } catch (SQLException e) {
                throw new SQLRuntimeException(e);
            }
        } else {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("S2Pager not use scroll cursor.");
            }
            try {
                while (original.getRow() < condition.getOffset()
                        && original.next()) {
                    counter++;
                }
            } catch (SQLException e) {
                throw new SQLRuntimeException(e);
            }
        }
    }

    public boolean next() throws SQLException {
        boolean next = super.next();
        if ((condition.getLimit() == PagerCondition.NONE_LIMIT || counter < condition
                .getOffset()
                + condition.getLimit())
                && next) {
            counter += 1;
            return true;
        }

		// 全件数を取得する
		ThresholdPagerCondition tpc = null;
		if (condition instanceof ThresholdPagerCondition) {
			tpc = (ThresholdPagerCondition) condition;
		}

        if (isUseCursor()) {
			if (tpc != null && tpc.getThreshold() != PagerCondition.NONE_LIMIT) {
				// まずthreshold + 1までのデータがあるかどうか？
				if (!original.absolute(tpc.getThreshold() + 1)) {
					// 無い場合は通常処理
					original.last();
					int count = original.getRow();
					condition.setCount(count);
				} else {
					// ある場合は閾値越えしている
					tpc.setCount(tpc.getThreshold());
					tpc.setOverThreshold(true);
					// 例外エラーを投げる
					throw new LargeAmountDataRuntimeException();
				}
			} else {
				// originalの記述
	            original.last();
	            int count = original.getRow();
	            condition.setCount(count);
			}
        } else {
			if (tpc != null && tpc.getThreshold() != PagerCondition.NONE_LIMIT) {
				// まずthreshold + 1までのデータがあるかどうか？
				boolean overThreshold = false;
				if (next) {
					counter++;
				}
				if ((tpc.getThreshold() + 1) <= counter) {
					overThreshold = true;
				}
				while (original.next()) {
					counter++;
					if ((tpc.getThreshold() + 1) <= counter) {
						overThreshold = true;
						break;
					}
				}
				if (!overThreshold) {
					// 無い場合は通常処理
					condition.setCount(counter);
				} else {
					// ある場合は閾値越えしている
					tpc.setCount(tpc.getThreshold());
					tpc.setOverThreshold(true);
					// 例外エラーを投げる
					throw new LargeAmountDataRuntimeException();
				}
			} else {
				// originalの記述
	            if (next) {
	                counter++; // 調整
	                while (original.next()) {
	                    counter++;
	                }
	            }
	            condition.setCount(counter);
			}
        }
        return false;
    }

    private boolean isUseCursor() {
        return useAbsolute && ResultSetUtil.isCursorSupport(original);
    }

}
