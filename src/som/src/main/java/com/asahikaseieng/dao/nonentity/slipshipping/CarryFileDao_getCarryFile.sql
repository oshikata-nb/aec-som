/**
 * 運送会社連携ファイルの取得
 *
 * @author tosco
 *
 * entityName=CarryFile
 * packageName=slipshipping
 * methodName=getCarryFile
 *
 */
SELECT
    TEMPORARY_CARRY_FILE.INPUTOR_CD
    , TEMPORARY_CARRY_FILE.INPUT_DATE
    , TEMPORARY_CARRY_FILE.DATA_CLS
    , TEMPORARY_CARRY_FILE.ROW_NUM
    , TEMPORARY_CARRY_FILE.CSV_DATA
FROM
    TEMPORARY_CARRY_FILE
WHERE
    TEMPORARY_CARRY_FILE.INPUTOR_CD = /*inputorCd*/
ORDER BY 
    TEMPORARY_CARRY_FILE.DATA_CLS ASC
    , TEMPORARY_CARRY_FILE.ROW_NUM ASC