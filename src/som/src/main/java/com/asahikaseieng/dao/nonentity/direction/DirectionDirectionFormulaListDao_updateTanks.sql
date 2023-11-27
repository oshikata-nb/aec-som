/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
*/

/**
 * 製造指図フォーミュラ-タンク情報更新用SQL
 *
 * @author tosco
 *
 * entityName=DirectionDirectionFormulaList
 * packageName=direction
 * methodName=updateTanks
 *
 */
UPDATE
    DIRECTION_FORMULA
SET
    LOCATION_CD = /*condition.locationCd*/'1',
    NEXT_LOCATION_CD = /*condition.nextLocationCd*/'1',
    NEXT_AFTER_LOCATION_CD = /*condition.nextAfterLocationCd*/'1',
    UPDATOR_CD = /*condition.updatorCd*/'1',
    UPDATE_DATE = /*condition.updateDate*/'2009/02/26'

WHERE
        DIRECTION_DIVISION = /*condition.directionDivision*/'0'
    AND DIRECTION_NO = /*condition.directionNo*/'OMORI00001'
    AND STEP_NO = /*condition.stepNo*/'1'
    AND LINE_NO = /*condition.lineNo*/'1'
