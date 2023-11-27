Imports log4net
Imports Microsoft.Office.Interop
Imports Microsoft.Office.Interop.Excel
Imports Common
Imports System.Configuration

''' <summary>
''' メインクラス
''' </summary>
''' <remarks></remarks>
Class Main
    Inherits ReportCreatorBase

#Region "変数"

    ''' <summary>
    ''' ロガー
    ''' </summary>
    ''' <remarks></remarks>
    Private m_log As ILog _
            = LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType)

	''' <summary>
    ''' ページ番号リスト
    ''' </summary>
    ''' <remarks></remarks>
    Private m_pageNumList As  List(of Integer) = New List(of Integer)

#Region "引数パラメータ"

    ''' <summary>
    ''' 購入依頼番号
    ''' </summary>
    ''' <remarks></remarks>
    Private m_byOrderNoList As List(Of String) = New List(Of String)

    ''' <summary>
    ''' 明細行番号
    ''' </summary>
    ''' <remarks></remarks>
    Private m_detailRowNoList As List(Of String) = New List(Of String)

    ''' <summary>
    ''' 外注行番号
    ''' </summary>
    ''' <remarks></remarks>
    Private m_outSourcingNoList As List(Of String) = New List(Of String)

#End Region

#End Region

#Region "定数"

    ''' <summary>
    ''' テンプレートの一番上の行
    ''' </summary>
    ''' <remarks></remarks>
    Private Const TEMPLATE_TOP As Integer = 2

    ''' <summary>
    ''' 1ページあたりの行数
    ''' </summary>
    ''' <remarks></remarks>
    Private Const PAGE_ROW As Integer = 30

    ''' <summary>
    ''' 1ページあたりの最大表示データ数
    ''' </summary>
    ''' <remarks></remarks>
    Private Const MAX_ROW = 5

    ''' <summary>
    ''' 1データの行数
    ''' </summary>
    ''' <remarks></remarks>
    Private Const ROW_CELLS_NUM As Integer = 3

    ''' <summary>
    ''' ページの頭から表の一番上の行までの行数
    ''' </summary>
    ''' <remarks></remarks>
    Private Const STANDARD_ROW As Integer = 12

    ''' <summary>
    ''' ページ挿入行
    ''' </summary>
    ''' <remarks></remarks>
    Private Const PAGE_TOP_INDEX = 30

    ''' <summary>
    ''' シート数（先頭以外削除する）
    ''' </summary>
    ''' <remarks></remarks>
    Private Const SHEET_NUM = 6

    ''' <summary>
    ''' 文字列「受託品納期回答」(コピペ用)
    ''' </summary>
    ''' <remarks></remarks>
    Private Const ACCESSION_TITLE As String = "受託品納期回答"

#End Region

#Region "Enum"

    ''' <summary>
    ''' パラメータINDEX（個別）
    ''' </summary>
    ''' <remarks></remarks>
    Private Enum PRM_IDX As Integer
        ORDER_NOS = PRM_IDX_COMMON.COUNT
        DETAIL_NOS
        OUTSOURCING_NOS
        COUNT
    End Enum

#End Region

#Region "スタートアップ"

    ''' <summary>
    ''' スタートアップ関数
    ''' </summary>
    ''' <param name="args"></param>
    ''' <remarks></remarks>
    Public Shared Function Main(args As String()) As Integer
        Dim myReport As Main = Nothing
        Dim result As PROC_RESULT = PROC_RESULT.異常終了_システム例外
        Try
            myReport = New Main
            myReport.m_log.Info("Start.")
            myReport.m_log.Debug(" args = " & String.Join(", ", args))

            '処理実行
            result = myReport.ExecuteProc(args)
            myReport.m_log.Info("Result = " & result)

        Catch ex As Exception
            'エラーメッセージ表示
            myReport.m_log.Error(ex)
            result = PROC_RESULT.異常終了_システム例外
            myReport.m_log.Info("Result = " & result)
        Finally
            myReport.m_log.Info("End.")
        End Try

        Return result
    End Function

#End Region

#Region "オーバーライド"

    ''' <summary>
    ''' 引数のチェック（個別）
    ''' </summary>
    ''' <param name="args"></param>
    ''' <remarks></remarks>
    Protected Overrides Function GetParams(args As String()) As PROC_RESULT
        If args.Count < PRM_IDX.COUNT - 2 Then
            Return PROC_RESULT.異常終了_引数不足
        End If

        '引数の取得
        m_byOrderNoList = args(PRM_IDX.ORDER_NOS).Split(","c).ToList
        m_detailRowNoList = args(PRM_IDX.DETAIL_NOS).Split(","c).ToList
        m_outSourcingNoList = args(PRM_IDX.OUTSOURCING_NOS).Split(","c).ToList

        If m_byOrderNoList Is Nothing _
            OrElse m_byOrderNoList.Count = 0 Then
            Return PROC_RESULT.異常終了_引数不正
        End If

        Return PROC_RESULT.正常終了
    End Function

    ''' <summary>
    ''' シート編集処理
    ''' </summary>
    ''' <param name="exlSheet"></param>
    ''' <remarks></remarks>
    Protected Overrides Function EditSheet(exlSheet As Excel.Worksheet) As Boolean

        m_log.Info("Start.")

        ' 現在の行数
        Dim rowNum As Integer = 1
        ' 表示ページ数
        Dim pageNum As Integer = 1

        ' 挿入データの基準行（受注案内書の受注の行番号）
        Dim rIdx As Integer = 14

        ' 一つ前のデータの受注区分
        Dim bfOrderDivision As Integer = 0
        ' 一つ前のデータの取引先コード
        Dim bfVenderCd As String = ""
        ' 一つ前のデータの受注日付
        Dim bfOrderDate As String = ""

        'For Each dr As DataRow In dt.Rows
        '    ' このデータが新規ページになるか
        '    Dim isNewPage As Boolean = False

        '    ' コピーする位置を決定
        '    isNewPage = CheckNewPage(dr, bfVenderCd, bfOrderDate, bfOrderDivision)

        '    ' 改ページか行数オーバーの場合
        '    If (isNewPage OrElse rowNum = MAX_ROW) Then

        '        If ((Not isNewPage) AndAlso rowNum = MAX_ROW) Then
        '            ' 表示ページ数を追加
        '            pageNum += 1
        '        Else
        '            ' 表示ページ数を初期化
        '            pageNum = 1
        '        End If

        '        ' ページ番号をリストに格納
        '        m_pageNumList.Add(pageNum)

        '        ' ページ全体のレイアウトをコピーし、基準行番号を更新
        '        rIdx = CreateNewPage(exlSheet, rIdx)

        '        ' ヘッダー情報を挿入
        '        HeaderInsertData(exlSheet, dr, rIdx, pageNum)

        '        ' 現在の行数を1行目にする
        '        rowNum = 1

        '    Else

        '        ' 基準行番号を次の行に更新
        '        rIdx = rIdx + ROW_CELLS_NUM

        '        ' 現在の行数を+1する
        '        rowNum += 1

        '    End If
        '    ' データを挿入
        '    ' 出荷案内書
        '    InsertData(exlSheet, dr, rIdx)

        '    ' 前データの内容を更新
        '    bfOrderDivision = CInt(dr("ORDER_DIVISION"))
        '    bfVenderCd = dr("VENDER_CD").ToString
        '    bfOrderDate = CDate(dr("ORDER_DATE")).ToString("yyyy/MM/dd")

        'Next

        '' 各ページにページの分母を割り振る
        'setWholePageNum(exlSheet, rIdx)

        '' 一番最初のページ（テンプレート）を非表示にする
        'HiddenTemplate(exlSheet)

        '' 一番最初のページ以外を非表示にする


        ' クリップボードメッセージ非表示対応
        Dim xlRange As Excel.Range = exlSheet.Cells.Range("A2")
        xlRange.Copy()

        m_log.Info("End.")

        Return True
    End Function

    ''' <summary>
    ''' ブック編集処理（継承クラスで実装）
    ''' </summary>
    ''' <param name="exlBook"></param>
    ''' <remarks></remarks>
    Protected Overrides Function EditBook(exlBook As Excel.Workbook) As Boolean

        Dim i As Integer

        Try
            For i = 2 To exlBook.Sheets.Count
                Dim exlSheet As Excel.Worksheet = Nothing
                exlSheet = CType(exlBook.Worksheets(i), Excel.Worksheet)
                exlSheet.Visible = XlSheetVisibility.xlSheetHidden
            Next

        Catch

            Return False

        End Try

        Return True
    End Function

#End Region

#Region "個別処理"

#Region "データ取得"
    ''' <summary>
    ''' データ取得
    ''' </summary>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Function GetOrderCheckReportData() As Data.DataTable

        Try
            Dim sql As New System.Text.StringBuilder

            sql.Clear()
            sql.Append("   SELECT ")
            sql.Append("     ODH.ORDER_DATE ")
            sql.Append("     , ODH.VENDER_CD ")
            sql.Append("     , VND.ZIPCODE_NO ")
            sql.Append("     , VND.ADDRESS1 ")
            sql.Append("     , VND.ADDRESS2 ")
            sql.Append("     , VND.ADDRESS3 ")
            sql.Append("     , VND.VENDER_NAME1 ")
            sql.Append("     , VND.VENDER_NAME2 ")
            sql.Append("     , VND.TEL_NO ")
            sql.Append("     , VND.FAX_NO ")
            sql.Append("     , ODH.ORDER_NO ")
            sql.Append("     , VND.VENDER_SHORTED_NAME ")
            sql.Append("     , ITM.ITEM_NAME ")
            sql.Append("     , ISQ.SPECIFICATION_NAME ")
            sql.Append("     , ODD.ORDER_QTY ")
            sql.Append("     , ISNULL(ODD.ORDER_QTY, 0) * ISNULL(ISQ.KG_OF_FRACTION_MANAGEMENT, 1) + ISNULL(ODD.ORDER_FRACTION_QTY, 0) ")
            sql.Append("      AS ORDER_CONV_QTY ")
            sql.Append("     , ODH.DELIVERY_EXPECTED_DATE ")
            sql.Append("     , ODH.DELIVERY_EXPECTED_TIME ")
            sql.Append("     , ODH.SCHEDULED_SHIPPING_DATE ")
            sql.Append("     , ODH.CUSTOMER_ORDER_NO ")
            sql.Append("     , ODH.ORDER_SUMMERY ")
            sql.Append("     , ODH.BALANCE_CD ")
            sql.Append("     , ODH.ORDER_DIVISION ")
            sql.Append("   FROM ")
            sql.Append("     ORDER_HEAD ODH ")
            sql.Append("     LEFT JOIN V_VENDER VND ")
            sql.Append("       ON ODH.VENDER_CD = VND.VENDER_CD ")
            sql.Append("       AND VND.VENDER_DIVISION = 'TS' ")
            sql.Append("       AND VND.LANGUAGE_ID = " & "'" & m_langId & "'")
            sql.Append("     LEFT JOIN ORDER_DETAIL ODD ")
            sql.Append("       ON ODH.ORDER_NO = ODD.ORDER_NO ")
            sql.Append("     LEFT JOIN V_ITEM_MAX_VERSION ITM ")
            sql.Append("       ON ODD.ITEM_CD = ITM.ITEM_CD ")
            sql.Append("       AND ITM.LANGUAGE_ID = " & "'" & m_langId & "'")
            sql.Append("     LEFT JOIN V_ITEM_SPECIFICATION_QUEUE ISQ ")
            sql.Append("       ON ODD.ITEM_CD = ISQ.ITEM_CD ")
            sql.Append("       AND ODD.SPECIFICATION_CODE = ISQ.SPECIFICATION_CODE ")
            sql.Append("       AND ITM.VERSION = ISQ.VERSION ")
            sql.Append("       AND ISQ.LANGUAGE_ID = " & "'" & m_langId & "'")
            If m_byOrderNoList.Count > 0 Then
                sql.Append(" WHERE")
                sql.Append(" ODH.ORDER_NO IN ( '")
                sql.Append(String.Join("', '", m_byOrderNoList.ToArray))
                sql.Append("' ) ")
            End If

            sql.Append(" ORDER BY ODH.ORDER_DIVISION, ODH.ORDER_DATE, ODH.VENDER_CD, ODH.ORDER_NO")
            Return Me.GetData(sql.ToString)

        Catch ex As Exception
            'エラー出力
            m_log.Error("[GetOrderReportData]", ex)
        End Try

        Return Nothing

    End Function

#End Region

#Region "ヘッダーデータ挿入"

    ''' <summary>
    ''' ヘッダーデータ挿入
    ''' </summary>
    ''' <param name="exlSheet"></param>
    ''' <param name="dr"></param>
    ''' <param name="rIdx"></param>
    ''' <remarks></remarks>
    Private Sub HeaderInsertData(exlSheet As Excel.Worksheet, dr As DataRow, rIdx As Integer, pageNum As Integer)
        ' 基準位置rIdxは受注案内書の1行目の受注No

        ' テンプレートの位置にプレビューフラグを持たせておく
        'exlSheet.Range("A1").Value = isPreview

        'exlSheet.Range("BY" & (rIdx - 12).ToString).Value = CDate(dr("ORDER_DATE")).ToString("yyyy/MM/dd")
        'exlSheet.Range("C" & (rIdx - 12).ToString).Value = ZIP_MARK & dr("ZIPCODE_NO").ToString
        'exlSheet.Range("C" & (rIdx - 11).ToString).Value = dr("ADDRESS1")
        'exlSheet.Range("C" & (rIdx - 10).ToString).Value = dr("ADDRESS2")
        'exlSheet.Range("C" & (rIdx - 8).ToString).Value = dr("VENDER_NAME1")
        'exlSheet.Range("C" & (rIdx - 7).ToString).Value = dr("VENDER_NAME2")
        'exlSheet.Range("C" & (rIdx - 6).ToString).Value = TEL_HEADER & dr("TEL_NO").ToString
        'exlSheet.Range("N" & (rIdx - 6).ToString).Value = FAX_HEADER & dr("FAX_NO").ToString

        '' 社名が1行しかない場合は1行目に「御中」を追加
        'If (String.IsNullOrEmpty(dr("VENDER_NAME2").ToString)) Then
        '    exlSheet.Range("C" & (rIdx - 8).ToString).Value = dr("VENDER_NAME1").ToString & DEAR
        'Else
        '    exlSheet.Range("C" & (rIdx - 8).ToString).Value = dr("VENDER_NAME1")
        '    exlSheet.Range("C" & (rIdx - 7).ToString).Value = dr("VENDER_NAME2").ToString & DEAR
        'End If

        '' 受託品区分が受託品ならヘッダ名を変更
        'If (Integer.Equals(ACCESSION_DIVISION, CInt(dr("ORDER_DIVISION")))) Then
        '    exlSheet.Range("AF" & (rIdx - 12).ToString).Value = ACCESSION_TITLE
        'End If

        '' 現在のページ数を追加
        'exlSheet.Range("B" & (rIdx + 16).ToString).Value = pageNum

    End Sub

#End Region

#Region "データ挿入"

    ''' <summary>
    ''' データ挿入
    ''' </summary>
    ''' <param name="exlSheet"></param>
    ''' <param name="dr"></param>
    ''' <param name="rIdx"></param>
    ''' <remarks></remarks>
    Private Sub InsertData(exlSheet As Excel.Worksheet, dr As DataRow, rIdx As Integer)

        exlSheet.Range("B" & (rIdx).ToString).Value = dr("ORDER_NO")
        exlSheet.Range("G" & (rIdx).ToString).Value = dr("VENDER_SHORTED_NAME")
        exlSheet.Range("Z" & (rIdx).ToString).Value = dr("ITEM_NAME")
        exlSheet.Range("Z" & (rIdx + 2).ToString).Value = dr("SPECIFICATION_NAME")
        exlSheet.Range("AO" & (rIdx).ToString).Value = dr("ORDER_QTY")
        exlSheet.Range("AT" & (rIdx).ToString).Value = dr("ORDER_CONV_QTY")
        exlSheet.Range("BA" & (rIdx).ToString).Value = dr("DELIVERY_EXPECTED_DATE")
        exlSheet.Range("BE" & (rIdx).ToString).Value = dr("DELIVERY_EXPECTED_TIME")
        exlSheet.Range("BH" & (rIdx).ToString).Value = dr("SCHEDULED_SHIPPING_DATE")
        exlSheet.Range("BL" & (rIdx).ToString).Value = dr("CUSTOMER_ORDER_NO")
        exlSheet.Range("BL" & (rIdx + 1).ToString).Value = dr("ORDER_SUMMERY")

    End Sub

#End Region

#Region "コピー位置決定"

    ''' <summary>
    ''' コピー位置決定
    ''' </summary>
    ''' <param name="dr"></param>
    ''' <param name="bfVenderCd"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Function CheckNewPage(dr As DataRow, bfVenderCd As String, bfOrderDate As String, bfOrderDivision As Integer) As Boolean

        Dim isNewPage As Boolean = True

        ' 前のデータの出荷先と同じ場合
        If (String.IsNullOrEmpty(bfVenderCd) = False AndAlso String.Compare(bfVenderCd, dr("VENDER_CD").ToString) = 0 _
           AndAlso String.IsNullOrEmpty(bfOrderDate) = False _
           AndAlso String.Compare(bfOrderDate, CDate(dr("ORDER_DATE")).ToString("yyyy/MM/dd")) = 0 _
           AndAlso Integer.Equals(bfOrderDivision, CInt(dr("ORDER_DIVISION")))) Then
            isNewPage = False
        End If

        Return isNewPage

    End Function

#End Region

#Region "改ページ"

    ''' <summary>
    ''' 改ページ
    ''' </summary>
    ''' <param name="exlSheet"></param>
    ''' <param name="rIdx"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Function CreateNewPage(exlSheet As Excel.Worksheet, rIdx As Integer) As Integer

        ' 次ページの一番最初の行にインデックスを移動
        Dim newPageidx As Integer = CInt(Math.Floor((rIdx + PAGE_ROW) / PAGE_ROW)) * PAGE_ROW + TEMPLATE_TOP

        ' 1ページ文をコピー
        Dim xlRange As Excel.Range = exlSheet.Cells.Range("A2:BY31")
        xlRange.Copy()

        ' コピー内容を貼り付け
        Dim xlPasteRange As Excel.Range = exlSheet.Cells.Range("A" & newPageidx.ToString)
        exlSheet.Paste(xlPasteRange)

        ' ページのレイアウトを調整
        PageShapeTrim(exlSheet, newPageidx)

        ' インデックスを出荷案内書の出荷Noの位置にあわせる
        newPageidx = newPageidx + STANDARD_ROW

        Return newPageidx

    End Function

#End Region

#Region "ページレイアウト調整"

    ''' <summary>
    ''' ページレイアウト調整
    ''' </summary>
    ''' <param name="exlSheet"></param>
    ''' <param name="newPageidx"></param>
    ''' <remarks></remarks>
    Private Sub PageShapeTrim(exlSheet As Excel.Worksheet, newPageidx As Integer)

        ' 行の大きさを変更する
        For i = 0 To PAGE_ROW - 1
            exlSheet.Range("A" & (newPageidx + i).ToString).RowHeight = exlSheet.Range("A" & (i + 2).ToString).RowHeight
        Next

    End Sub

#End Region

#Region "ページ分母割り振り"

    ''' <summary>
    ''' ページ分母割り振り
    ''' </summary>
    ''' <param name="exlSheet"></param>
    ''' <param name="rIdx"></param>
    ''' <remarks></remarks>
    Private Sub setWholePageNum(exlSheet As Excel.Worksheet, rIdx As Integer)

        ' そのページの分母(初期値は最後のページのページ数)
        Dim numerator As Integer = m_pageNumList(m_pageNumList.Count - 1)

        ' ページ数に分母を足して表示
        For i = m_pageNumList.Count - 1 To 1 Step -1

            Dim numeratorPage As String = exlSheet.Range("B" & PAGE_TOP_INDEX + (PAGE_ROW * (i + 1))).Text.ToString _
                                              & " / " & numerator.ToString
            exlSheet.Range("B" & (PAGE_TOP_INDEX + (PAGE_ROW * (i + 1))).ToString).Value = numeratorPage

            If (m_pageNumList(i - 1) >= m_pageNumList(i)) Then
                numerator = m_pageNumList(i - 1)
            End If
        Next

        Dim firstNumeratorPage = exlSheet.Range("B" & PAGE_TOP_INDEX + PAGE_ROW).Text.ToString _
                                          & " / " & numerator.ToString
        exlSheet.Range("B" & (PAGE_TOP_INDEX + PAGE_ROW).ToString).Value = firstNumeratorPage

    End Sub

#End Region

#Region "テンプレート非表示"
    ''' <summary>
    ''' テンプレート非表示
    ''' </summary>
    ''' <param name="exlSheet"></param>
    ''' <remarks></remarks>
    Private Sub HiddenTemplate(exlSheet As Excel.Worksheet)
        ' 上から一ページ分を非表示にする
        Dim xlRange As Excel.Range = exlSheet.Cells.Range("1:" & PAGE_ROW + TEMPLATE_TOP - 1)
        xlRange.EntireRow.Hidden = True

    End Sub

#End Region

#Region "FAX番号取得"

    ''' <summary>
    ''' FAX番号の取得
    ''' </summary>
    ''' <param name="faxNo"></param>
    ''' <remarks></remarks>
    Private Function getFaxNo(faxNo As String) As Data.DataTable

        Try
            Dim sql As New System.Text.StringBuilder

            sql.Clear()
            sql.Append("   SELECT ")
            sql.Append("     ORDER_REPLY_FAX_NO ")
            sql.Append("   FROM ")
            sql.Append("     V_DELIVERY ")
            sql.Append("   WHERE")
            sql.Append("     DELIVERY_CD = '" & faxNo & "'")
            sql.Append("     AND LANGUAGE_ID = " & "'" & m_langId & "'")

            Return Me.GetData(sql.ToString)

        Catch ex As Exception
            'エラー出力
            m_log.Error("[GetOrderReportData]", ex)
        End Try

        Return Nothing

    End Function

#End Region

#Region "担当名称取得"

    ''' <summary>
    ''' 担当名称の取得
    ''' </summary>
    ''' <param name="tantoCd"></param>
    ''' <remarks></remarks>
    Private Function getTantoName(tantoCd As String) As Data.DataTable

        Try
            Dim sql As New System.Text.StringBuilder

            sql.Clear()
            sql.Append("   SELECT ")
            sql.Append("     TANTO_NM AS TANTO_NAME ")
            sql.Append("   FROM ")
            sql.Append("     LOGIN ")
            sql.Append("   WHERE")
            sql.Append("     TANTO_CD = '" & tantoCd & "'")

            Return Me.GetData(sql.ToString)

        Catch ex As Exception
            'エラー出力
            m_log.Error("[GetOrderReportData]", ex)
        End Try

        Return Nothing

    End Function

#End Region

#End Region

End Class
