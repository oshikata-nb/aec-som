/*
 * Created on 2009/02/10
 *
 * $copyright$
 *
*/

/**
 * 包装指図－検索画面一覧検索用SQL
 *
 * @author tosco
 *
 * entityName=PkgDirectionList
 * packageName=pkgdirection
 * methodName=getList
 *
 */
SELECT
	 DIRHEAD.DIRECTION_DIVISION AS  DIRECTION_DIVISION
	,DIRHEAD.DIRECTION_NO AS        DIRECTION_NO
	,DIRHEAD.ASP_ORDER_NO AS        ASP_ORDER_NO
	,DIRHEAD.DIRECTION_STATUS AS    DIRECTION_STATUS
	,DIRHEAD.RECIPE_ID AS           RECIPE_ID
	,DIRHEAD.RECIPE_CD AS           RECIPE_CD
	,DIRHEAD.RECIPE_VERSION AS      RECIPE_VERSION
	,DIRHEAD.PRODUCTION_LINE AS     PRODUCTION_LINE
	,DIRHEAD.FILLTANK_NO AS         FILLTANK_NO
	,DIRHEAD.PACKAGE_LINE AS        PACKAGE_LINE
	,DIRHEAD.ITEM_CD AS             ITEM_CD
	,DIRHEAD.PLANED_QTY AS          PLANED_QTY
	,DIRHEAD.LOT_NO AS              LOT_NO
	,DIRHEAD.PLANED_SDATE AS        PLANED_SDATE
	,DIRHEAD.PLANED_EDATE AS        PLANED_EDATE
	,DIRHEAD.PRODUCT_LABEL_FLAG AS  PRODUCT_LABEL_FLAG
	,DIRHEAD.UPDATE_DATE AS         UPDATE_DATE
	,DIRHEAD.UPDATOR_CD AS          UPDATOR_CD
    ,ITEM.ITEM_NAME AS              ITEM_NAME
    ,ITEM.STYLE_OF_PACKING AS       STYLE_OF_PACKING
    ,LINE.PRODUCTION_LINE_NAME AS   PRODUCTION_LINE_NAME
    ,NAME.NAME01 AS                 UNIT_NAME
    ,ATRQUE.PALETTE_PRODUCTS AS     PALETTE_PRODUCTS
    ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIV
    ,ITEM.OTHER_COMPANY_CD1	AS		OTHER_COMPANY_CD1
    ,ITEM.SHIPPER_DIVISION AS		SHIPPER_DIVISION
    , '0' AS                        JISSEKI_FLAG
FROM
    LINE LINE,
    NAMES NAME,
    ITEM,
	ARTICLE_ATTRIBUTE_QUEUE ATRQUE,
	DIRECTION_HEADER DIRHEAD
WHERE (DIRHEAD.DIRECTION_DIVISION = 2 OR DIRHEAD.DIRECTION_DIVISION = 3)
　　AND (DIRHEAD.DIRECTION_STATUS = 1 OR DIRECTION_STATUS = 2)
    AND DIRHEAD.ITEM_CD = ITEM.ITEM_CD(+)
	AND ITEM.ITEM_CD = ATRQUE.ITEM_CD(+)
	AND ITEM.VERSION = ATRQUE.VERSION(+)
    AND DIRHEAD.PRODUCTION_LINE = LINE.PRODUCTION_LINE(+)
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND ITEM.UNIT_OF_OPERATION_MANAGEMENT = NAME.NAME_CD(+)

/*IF ( condition.directionDivision != null )*/
	AND	DIRHEAD.DIRECTION_DIVISION = /*condition.directionDivision*/
/*END*/

/*IF (( condition.directionNo != null ) && ( condition.directionNo != "" ))*/
	AND	DIRHEAD.DIRECTION_NO = /*condition.directionNo*/
/*END*/

/*IF (( condition.directionStatus != null ) && ( condition.directionStatus != 0 ))*/
	AND	DIRHEAD.DIRECTION_STATUS = /*condition.directionStatus*/
/*END*/

/*IF (( condition.productionLine != null ) && ( condition.productionLine != "" ))*/
	AND	DIRHEAD.PRODUCTION_LINE = /*condition.productionLine*/
/*END*/

/*IF (( condition.itemCd != null ) && ( condition.itemCd != "" ))*/
	AND	DIRHEAD.ITEM_CD LIKE /*condition.itemCd*/
/*END*/

/*IF (( condition.otherCompanyCd1 != null ) && ( condition.otherCompanyCd1 != "" ))*/
	AND	ITEM.OTHER_COMPANY_CD1 LIKE /*condition.otherCompanyCd1*/
/*END*/

/*IF ( condition.planedSdateFrom != null )*/
	AND	DIRHEAD.PLANED_SDATE >= /*condition.planedSdateFrom*/
/*END*/

/*IF ( condition.planedSdateTo != null )*/
	AND	DIRHEAD.PLANED_SDATE <= /*condition.planedSdateTo*/
/*END*/

/*IF ( condition.planedEdateFrom != null )*/
	AND	DIRHEAD.PLANED_EDATE >= /*condition.planedEdateFrom*/
/*END*/

/*IF ( condition.planedEdateTo != null )*/
	AND	DIRHEAD.PLANED_EDATE <= /*condition.planedEdateTo*/
/*END*/

/*IF (( condition.aspOrderNo != null ) && ( condition.aspOrderNo != "" ))*/
	AND	DIRHEAD.ASP_ORDER_NO LIKE /*condition.aspOrderNo*/'%'
/*END*/
/*IF (( condition.packageLine != null ) && ( condition.packageLine != "" ))*/
	AND	DIRHEAD.PACKAGE_LINE LIKE /*condition.packageLine*/'%'
/*END*/

ORDER BY
	DIRHEAD.PACKAGE_LINE,
	DIRHEAD.PLANED_SDATE,
	DIRHEAD.DIRECTION_NO
