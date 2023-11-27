/*
* 名称マスタ取得SQL
*
* entityName=BatchWaitNamesArray
* packageName=common.batchwait
* methodName=getEntityList
*
*/
SELECT NAMES.NAME_DIVISION
      ,NAMES.NAME_CD
      ,NAMES.NAME01
      ,NAMES.NAME02
      ,NAMES.NAME03
      ,NAMES.NMEVALUE1
FROM   NAMES
WHERE  NAMES.NAME_DIVISION = /*namesdivision*/'%'

      /*IF(menuId != null && menuId != "")*/
AND    NAMES.NAME02 = /*menuId*/'%'
      /*END*/

      /*IF(num != null && num != "")*/
AND    NAMES.NAME03 = /*num*/'%'
      /*END*/
