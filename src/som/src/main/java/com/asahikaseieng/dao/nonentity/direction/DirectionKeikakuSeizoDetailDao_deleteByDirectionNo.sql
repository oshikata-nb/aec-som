/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
*/

/**
 * 製造計画、指図番号で削除用SQL
 *
 * @author tosco
 *
 * entityName=DirectionKeikakuSeizoDetail
 * packageName=direction
 * methodName=deleteByDirectionNo
 *
 */
DELETE
FROM
	KEIKAKU_SEIZO
WHERE
	SEIZOSASHIZUNO = /*directionNo*/'S0903060001'
