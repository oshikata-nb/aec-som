/*
 * ITEM_COMSTRAINT QUEUE SQL
 *
 * entityName=ItemConstraintList
 * packageName=itemconstraintlist
 * methodName=getList
 */
SELECT
    ITEM_CD,
    VERSION,
    PERMISSION,
    PERMISSION_DATE,
    CONSTRAINT_CD,
    ROW_NO,
    PROHIBITION,
    FROM_DATE,
    TO_DATE,
    REASON,
    CHECK_DATE 
FROM
    (
        SELECT
            B.ITEM_CD,
            B.VERSION,
            A.PERMISSION,
            A.PERMISSION_DATE,
            B.CONSTRAINT_CD,
            B.ROW_NO,
            A.ORDER_PROHIBITION PROHIBITION,
            A.ORDER_FROM_DATE FROM_DATE,
            A.ORDER_TO_DATE TO_DATE,
            A.ORDER_REASON REASON,
            A.UPDATE_DATE CHECK_DATE
        FROM
            ITEM_CONSTRAINT A,
            (
                SELECT
                    /*itemCd*/'180012' ITEM_CD,
                    /*version*/1       VERSION,
                    N'受注不可'        CONSTRAINT_CD,
                    1                  ROW_NO
                FROM
                    ITEM_CONSTRAINT
            ) B
        WHERE
            A.ITEM_CD(+) = B.ITEM_CD
        AND A.VERSION(+) = B.VERSION

    UNION

        SELECT
            B.ITEM_CD,
            B.VERSION,
            A.PERMISSION,
            A.PERMISSION_DATE,
            B.CONSTRAINT_CD,
            B.ROW_NO,
            A.SHIPPING_PROHIBITION PROHIBITION,
            A.SHIPPING_FROM_DATE FROM_DATE,
            A.SHIPPING_TO_DATE TO_DATE,
            A.SHIPPING_REASON REASON,
            A.UPDATE_DATE
        FROM
            ITEM_CONSTRAINT A,
            (
                SELECT
                    /*itemCd*/'180012' ITEM_CD,
                    /*version*/1       VERSION,
                    N'出荷不可'        CONSTRAINT_CD,
                    2                  ROW_NO
                FROM
                    ITEM_CONSTRAINT
            ) B
        WHERE
            A.ITEM_CD(+) = B.ITEM_CD
        AND A.VERSION(+) = B.VERSION

    UNION

        SELECT
            B.ITEM_CD,
            B.VERSION,
            A.PERMISSION,
            A.PERMISSION_DATE,
            B.CONSTRAINT_CD,
            B.ROW_NO,
            A.PROVIDE_PROHIBITION PROHIBITION,
            A.PROVIDE_FROM_DATE FROM_DATE,
            A.PROVIDE_TO_DATE TO_DATE,
            A.PROVIDE_REASON REASON,
            A.UPDATE_DATE
        FROM
            ITEM_CONSTRAINT A,
            (
                SELECT
                    /*itemCd*/'180012' ITEM_CD,
                    /*version*/1       VERSION,
                    N'支給不可'        CONSTRAINT_CD,
                    3                  ROW_NO
                FROM
                    ITEM_CONSTRAINT
            ) B
        WHERE
            A.ITEM_CD(+) = B.ITEM_CD
        AND A.VERSION(+) = B.VERSION

    UNION

        SELECT
            B.ITEM_CD,
            B.VERSION,
            A.PERMISSION,
            A.PERMISSION_DATE,
            B.CONSTRAINT_CD,
            B.ROW_NO,
            A.PURCHASE_PROHIBITION PROHIBITION,
            A.PURCHASE_FROM_DATE FROM_DATE,
            A.PURCHASE_TO_DATE TO_DATE,
            A.PURCHASE_REASON REASON,
            A.UPDATE_DATE
        FROM
            ITEM_CONSTRAINT A,
            (
                SELECT
                    /*itemCd*/'180012' ITEM_CD,
                    /*version*/1       VERSION,
                    N'発注不可'        CONSTRAINT_CD,
                    4                  ROW_NO
                FROM
                    ITEM_CONSTRAINT
            ) B
        WHERE
            A.ITEM_CD(+) = B.ITEM_CD
        AND A.VERSION(+) = B.VERSION

    UNION

        SELECT
            B.ITEM_CD,
            B.VERSION,
            A.PERMISSION,
            A.PERMISSION_DATE,
            B.CONSTRAINT_CD,
            B.ROW_NO,
            A.PRODUCT_PROHIBITION PROHIBITION,
            A.PRODUCT_FROM_DATE FROM_DATE,
            A.PRODUCT_TO_DATE TO_DATE,
            A.PRODUCT_REASON REASON,
            A.UPDATE_DATE
        FROM
            ITEM_CONSTRAINT A,
            (
                SELECT
                    /*itemCd*/'180012' ITEM_CD,
                    /*version*/1       VERSION,
                    N'製造不可'        CONSTRAINT_CD,
                    5                  ROW_NO
                FROM
                    ITEM_CONSTRAINT
            ) B
        WHERE
            A.ITEM_CD(+) = B.ITEM_CD
        AND A.VERSION(+) = B.VERSION

    UNION

        SELECT
            B.ITEM_CD,
            B.VERSION,
            A.PERMISSION,
            A.PERMISSION_DATE,
            B.CONSTRAINT_CD,
            B.ROW_NO,
            A.SAMPLE_SHIPPING_PROHIBITION PROHIBITION,
            A.SAMPLE_SHIPPING_FROM_DATE FROM_DATE,
            A.SAMPLE_SHIPPING_TO_DATE TO_DATE,
            A.SAMPLE_SHIPPING_REASON REASON,
            A.UPDATE_DATE
        FROM
            ITEM_CONSTRAINT A,
            (
                SELECT
                    /*itemCd*/'180012'  ITEM_CD,
                    /*version*/1        VERSION,
                    N'サンプル提供不可' CONSTRAINT_CD,
                    6                   ROW_NO
                FROM
                    ITEM_CONSTRAINT
            ) B
        WHERE
            A.ITEM_CD(+) = B.ITEM_CD
        AND A.VERSION(+) = B.VERSION

    UNION

        SELECT
            B.ITEM_CD,
            B.VERSION,
            A.PERMISSION,
            A.PERMISSION_DATE,
            B.CONSTRAINT_CD,
            B.ROW_NO,
            A.SAMPLE_PRODUCT_PROHIBITION PROHIBITION,
            A.SAMPLE_PRODUCT_FROM_DATE FROM_DATE,
            A.SAMPLE_PRODUCT_TO_DATE TO_DATE,
            A.SAMPLE_PRODUCT_REASON REASON,
            A.UPDATE_DATE
        FROM
            ITEM_CONSTRAINT A,
            (
                SELECT
                    /*itemCd*/'180012'  ITEM_CD,
                    /*version*/1        VERSION,
                    N'サンプル製造不可' CONSTRAINT_CD,
                    7                   ROW_NO
                FROM ITEM_CONSTRAINT
            ) B
        WHERE
            A.ITEM_CD(+) = B.ITEM_CD
        AND A.VERSION(+) = B.VERSION
    )
ORDER BY
    ROW_NO ASC
