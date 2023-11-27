/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package org.seasar.dao.pager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.seasar.extension.jdbc.ResultSetFactory;

/**
 * org.seasar.dao.pager.PagerResultSetFactoryWrapper を閾値対応したWrapperクラス.<br>
 * (s2daoのソースを改造している為コーディング規約に沿っていません).
 * @author jbd
 */
public class ThresholdPagerResultSetFactoryWrapper implements ResultSetFactory {

    /* オリジナルのResultSetFactory */
    private ResultSetFactory resultSetFactory;

    private boolean useScrollCursor = true;

    /**
     * コンストラクタ.
     * 
     * @param resultSetFactory
     *            オリジナルのResultSetFactory
     */
    public ThresholdPagerResultSetFactoryWrapper(
    		final ResultSetFactory resultSetFactory) {
        this.resultSetFactory = resultSetFactory;
    }

    /**
     * @param useScrollCursor useScrollCursor 
     */
    public void setUseScrollCursor(boolean useScrollCursor) {
        this.useScrollCursor = useScrollCursor;
    }

    /**
     * @param useScrollCursor useScrollCursor 
     */
    public ResultSet getResultSet(final Statement statement) {
        ResultSet resultSet = resultSetFactory.getResultSet(statement);
        Object[] args = PagerContext.getContext().peekArgs();
        if (PagerContext.isPagerCondition(args)) {
            PagerCondition condition = PagerContext.getPagerCondition(args);
            // jbd modify
            return new ThresholdPagerResultSetWrapper(resultSet, condition,
                    useScrollCursor);
        } else {
            return resultSet;
        }
    }

    /**
     * ResultSetを生成します。
     * <p>
     * PagerContextにPagerConditionがセットされている場合、
     * ResultSetをPagerResultSetWrapperでラップして返します。
     * 
     * @param ps PreparedStatement
     * @return ResultSet
     */
    public ResultSet createResultSet(final PreparedStatement ps) {
        ResultSet resultSet = resultSetFactory.createResultSet(ps);
        Object[] args = PagerContext.getContext().peekArgs();
        if (PagerContext.isPagerCondition(args)) {
            PagerCondition condition = PagerContext.getPagerCondition(args);
            // jbd modify
            return new ThresholdPagerResultSetWrapper(resultSet, condition,
                    useScrollCursor);
        } else {
            return resultSet;
        }
    }

}
