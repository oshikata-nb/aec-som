/*
 * Created on 2009/02/26
 *
 * $copyright$
 *
*/

/**
 * 製造計画-計装インターフェイス用SQL
 *
 * @author tosco
 *
 * entityName=DirectionKeikakuSeizoDetail
 * packageName=direction
 * methodName=getMaxJunban
 *
 */
SELECT
    DECODE(MAX(JUNBAN),NULL,0,MAX(JUNBAN)) AS JUNBAN
FROM
    KEIKAKU_SEIZO
WHERE
    /*seizobi*/'2009/02/26' = TO_CHAR(SEIZOBI,'yyyy/mm/dd') AND
    CHOGOTANKNO = /*chogoTankNo*/'1'
