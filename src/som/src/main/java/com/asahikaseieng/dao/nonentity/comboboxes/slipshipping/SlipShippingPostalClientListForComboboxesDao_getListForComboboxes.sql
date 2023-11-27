/*
 * 郵政依頼主コンボボックス用SQL
 *
 * entityName=SlipShippingPostlClientListForComboboxes
 * packageName=SlipShipping
 * methodName=getListForComboboxes
 *
 */
SELECT 
     POSTAL_CLIENT.POSTAL_CLIENT_CD
    ,POSTAL_CLIENT.POSTAL_NAME
FROM
    POSTAL_CLIENT
ORDER BY
    POSTAL_CLIENT.POSTAL_CLIENT_CD


