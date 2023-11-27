/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
*/

/**
 * 数値桁数チェックマスタ一覧用SQL
 *
 * @author tosco
 *
 * entityName=NumberChkDisitDetail
 * packageName=master.numberchkdisit
 * methodName=findByPrimaryKey
 *
 */
SELECT
	NUMBER_CHKDISIT.UNIT_DIVISION
,	NUMBER_CHKDISIT.VENDER_DIVISION
,	NUMBER_CHKDISIT.VENDER_CD
,	NUMBER_CHKDISIT.MAX_LENGTH
,	NUMBER_CHKDISIT.INTEGER_LENGTH
,	NUMBER_CHKDISIT.SMALLNUM_LENGTH
,	NUMBER_CHKDISIT.ROUND_DIVISION
,	NUMBER_CHKDISIT.LOWER_LIMIT
,	NUMBER_CHKDISIT.UPPER_LIMIT
FROM
	NUMBER_CHKDISIT
WHERE
	NUMBER_CHKDISIT.UNIT_DIVISION = /*unitDivision*/'A'
/*IF (( venderDivision != null ) && ( venderDivision != "" ))*/
	AND	NUMBER_CHKDISIT.VENDER_DIVISION = /*venderDivision*/'1'
/*END*/

/*IF (( venderCd != null ) && ( venderCd != "" ))*/
	AND	 NUMBER_CHKDISIT.VENDER_CD = /*venderCd*/'1'
/*END*/
/*IF (( unitDivision != null ) && ( unitDivision != "" ))*/
	OR (
            NUMBER_CHKDISIT.UNIT_DIVISION = /*unitDivision*/'A'
        AND NUMBER_CHKDISIT.VENDER_DIVISION = ' '
        AND NUMBER_CHKDISIT.VENDER_CD = ' '
    )
/*END*/

ORDER BY
    NUMBER_CHKDISIT.UNIT_DIVISION,
    NUMBER_CHKDISIT.VENDER_DIVISION,
    NUMBER_CHKDISIT.VENDER_CD
