
/*
 * 運送会社ファイル設定マスタ一覧用SQL
 *
 * entityName=CarryFileList
 * packageName=carryfilelist
 * methodName=getList
 *
 */
SELECT 
    CARRY.CARRY_CD
    , CARRY.CARRY_NAME1 || CARRY.CARRY_NAME2 AS CARRY_NAME
    , CASE WHEN CARRY_FILE_LAYOUT.CARRY_CD IS NULL 
        THEN '×'
        ELSE '〇'
      END AS EXISTS_SETTING
FROM 
    CARRY
LEFT JOIN
(
    SELECT CARRY_CD FROM CARRY_FILE_LAYOUT
    GROUP BY CARRY_CD
) CARRY_FILE_LAYOUT
ON
    CARRY_FILE_LAYOUT.CARRY_CD = CARRY.CARRY_CD
WHERE CARRY.CARRY_CD IS NOT NULL

/*IF (condition.srhCarryCd != null) && (condition.srhCarryCd != "")*/
AND (CARRY.CARRY_CD LIKE /*condition.srhCarryCd*/'%' OR CARRY.CARRY_NAME1 LIKE /*condition.srhCarryCd*/'%')
/*END*/
/*IF (condition.srhExistsSetting != null) && (condition.srhExistsSetting == "1")*/
AND NOT　CARRY_FILE_LAYOUT.CARRY_CD IS NULL 
/*END*/
/*IF (condition.srhExistsSetting != null) && (condition.srhExistsSetting == "2")*/
AND　CARRY_FILE_LAYOUT.CARRY_CD IS NULL 
/*END*/

ORDER BY
    CARRY.CARRY_CD
