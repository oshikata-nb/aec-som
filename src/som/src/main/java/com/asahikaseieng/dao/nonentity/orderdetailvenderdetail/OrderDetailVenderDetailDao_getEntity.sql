/**
 * 取引先詳細取得SQL
 *
 * @author tosco
 *
 * entityName=OrderDetailVenderDetail
 * packageName=orderdetailvenderdetail
 * methodName=getEntity
 *
 */
SELECT
    VENDER.ORGANIZATION_CD         AS ORGANIZATION_CD
,   ORGANIZATION.ORGANIZATION_NAME AS ORGANIZATION_NAME
FROM
    VENDER
,   ORGANIZATION    
WHERE
    VENDER.VENDER_CD  = /*venderCd*/
AND VENDER.VENDER_DIVISION = 'TS'
AND VENDER.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
