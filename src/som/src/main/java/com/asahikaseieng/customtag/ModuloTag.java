/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.customtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <ジュロ演算用(例 10 % 3 = 1)カスタムタグ
 * (用途例) <br>
 * 1 テーブルの1record毎に色分け (loopIndex % 2)で処理分け <br>
 * 2 横にx個のチェックボックスを動的に出力する(loopIndex % x)で改行を入れる <br>
 * <br>
 * 
 * numerator:分子=>割られる数<br>
 * donominator:分母=>割る数<br>
 * zero:true=>余りが0の時中身を評価 false=>余りが非0の時中身を評価
 * 
 * @author jbd
 */
public class ModuloTag extends TagSupport {

	private static final long serialVersionUID = -7561352701500131667L;

	private int numerator;

	private int denominator;

	private boolean zero;

	/**
	 * コンストラクタ.
	 */
	public ModuloTag() {
		super();
	}

	/**
	 * denominatorを取得します.
	 * 
	 * @return denominator
	 */
	private int getDenominator() {
		return denominator;
	}

	/**
	 * numeratorを取得します.
	 * 
	 * @return numerator
	 */
	private int getNumerator() {
		return numerator;
	}

	/**
	 * zeroを取得します.
	 * 
	 * @return zero
	 */
	private boolean isZero() {
		return zero;
	}

	/**
	 * 割られる数を設定します.
	 * 
	 * @param i
	 *            割られる数
	 */
	public void setDenominator(final String i) {
		denominator = Integer.parseInt(i);
	}

	/**
	 * 割る数を設定します.
	 * 
	 * @param i
	 *            割る数
	 */
	public void setNumerator(final String i) {
		numerator = Integer.parseInt(i);
	}

	/**
	 * 割り算の結果が0の時の挙動を制御する.
	 * 
	 * @param b
	 *            割り算の余りが0の場合タグ内を評価するかどうか true 0なら内容を評価する false 0以外なら内容を評価する
	 */
	public void setZero(final String b) {
		zero = Boolean.valueOf(b).booleanValue();
	}

	/**
	 * {@inheritDoc}
	 */
	public int doStartTag() throws JspException {

		int modulo = getNumerator() % getDenominator();
		if (isZero()) {
			if (modulo == 0) {
				return EVAL_BODY_INCLUDE;
			}
			return SKIP_BODY;
		}
		if (modulo == 0) {
			return SKIP_BODY;
		}
		return EVAL_BODY_INCLUDE;

	}

	/**
	 * {@inheritDoc}
	 */
	public void release() {
		super.release();
		numerator = 0;
		denominator = 0;
		zero = false;
	}
}
