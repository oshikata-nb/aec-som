/**
 * 7DRチェックデジット計算結果の取得
 *
 * @author Kiguchi
 *
 * entityName=CarryCheckDigit
 * packageName=slipshipping
 * methodName=get7DR
 *
 */
SELECT
    FUN_REPLACE_CHECKDIGIT_7DR( /*condition.base_str*/ , /*condition.bcCheckdigitStr*/ , /*condition.bcCheckdigitEnd*/ ) AS BARCODE
FROM
    DUAL