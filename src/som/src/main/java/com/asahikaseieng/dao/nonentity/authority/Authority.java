/*
 * Created on 2007/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.authority;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Authorityクラス.
 * @author jbd
 */
public class Authority extends AuthorityBase {

	private static final long serialVersionUID = 1L;

	/**
	 * Comparatorを取得
	 * @return Comparator<Authority>
	 */
	public static Comparator<String> createSortComparator() {
		return new MyComparator();
	}

	/**
	 * 
	 * ガジェット設定画面での選択可能ガジェットのソート用クラス
	 * ソート順：ガジェットID＞メニューID＞タブID
	 * 
	 * @author tosco
	 */
	private static class MyComparator implements Comparator<String>, Serializable {

		/** serialVersionUID */
		private static final long serialVersionUID = 1L;

		/**
		 * コンストラクタ.
		 */
		public MyComparator() {
			super();
		}

		public int compare(final String o1, final String o2) {
			String[] s1 = o1.split("_");
			String[] s2 = o2.split("_");
			int i1 = Integer.parseInt(s1[0]);
			int i2 = Integer.parseInt(s2[0]);
			if (i1 != i2) {
				return i1 - i2;
			}
			int i = s1[1].compareTo(s2[1]);
			if (i != 0) {
				return i;
			}
			return s1[2].compareTo(s2[2]);
		}
	};

	/**
	 * コンストラクタ.
	 */
	public Authority() {
		super();
	}

}

