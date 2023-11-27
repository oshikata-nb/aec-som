/*
 * 免税措置割合一覧用SQL
 *
 * entityName=Names
 * packageName=names
 * methodName=getTaxFreeRatio
 *
 */

SELECT
    TAX_RATIO.NAME_DIVISION
    , TAX_RATIO.NAME_CD
    , TAX_RATIO.NMQTY01
    , TAX_RATIO.NMEDATE1
    , TAX_RATIO.UPDATE_DATE
FROM
    NAMES TAX_RATIO
    INNER JOIN (
        SELECT
            MIN(NMEDATE1) AS TARGET_DATE
        FROM
            NAMES
        WHERE
            NAME_DIVISION = 'TFRE'
        /*IF (name01 != null) && (name01 != "")*/
            AND NMEDATE1 >= TO_NUMBER(/*name01*/) -- 仕入日付
        /*END*/
        GROUP BY
            NAME_DIVISION
    ) MIN_TAX_RATIO
        ON TAX_RATIO.NMEDATE1 = MIN_TAX_RATIO.TARGET_DATE
        AND TAX_RATIO.NAME_DIVISION = 'TFRE'
