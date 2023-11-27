/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
*/

/**
 * 製造指示、指図番号で削除用SQL
 *
 * @author tosco
 *
 * entityName=DirectionSijiSeizo
 * packageName=direction
 * methodName=deleteByDirectionNo
 *
 */
DELETE
FROM
	SIJI_SEIZO
WHERE
	SEIZOSASHIZUNO = /*directionNo*/'S0903060001'
