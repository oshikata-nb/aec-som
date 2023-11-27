/*
 * Created on 2009/06/10
 *
 * $copyright$
 *
*/

/**
 * 会計部門マスタ検索用SQL
 *
 * @author t0011036
 *
 * entityName=MgrecipeBumonDetail
 * packageName=mgrecipe
 * methodName=getEntity
 *
 */

SELECT SECTION_CD, SECTION_NAME
FROM BUMON
WHERE SECTION_CD = /*sectionCd*/'%'


