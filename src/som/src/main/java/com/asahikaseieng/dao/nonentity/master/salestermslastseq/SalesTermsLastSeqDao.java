/*
 * Created on 2009/05/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermslastseq;

/**
 * SalesTermsLastSeqDaoクラス
 * @author t0011036
 */
public interface SalesTermsLastSeqDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.salestermslastseq.SalesTermsLastSeq.class;

	/** ARGSアノテーション getLastSeq */
	String getLastSeq_ARGS = "deliveryCd, balanceCd";

	/**
	 * SalesTermsLastSeqメソッド
	 * 
	 * @param deliveryCd deliveryCd
	 * @param balanceCd balanceCd
	 * @return SalesTermsLastSeq
	 */
	SalesTermsLastSeq getLastSeq(final String deliveryCd, final String balanceCd);
}
