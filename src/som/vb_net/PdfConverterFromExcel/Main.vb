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

End Class
