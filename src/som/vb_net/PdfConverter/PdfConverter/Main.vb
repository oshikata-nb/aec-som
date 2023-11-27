Imports log4net
Imports Microsoft.Office.Interop
Imports Microsoft.Office.Interop.Excel
Imports System.IO
Imports System.Windows.Forms
Imports log4net.Repository.Hierarchy
Imports log4net.Appender
Imports System.Reflection
Imports log4net.Config

''' <summary>
''' メインクラス
''' </summary>
''' <remarks></remarks>
Class Main

#Region "変数"

    ''' <summary>
    ''' ロガー
    ''' </summary>
    ''' <remarks></remarks>
    Private Shared m_log As ILog _
            = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType)

#Region "引数パラメータ"

    ''' <summary>
    ''' 担当者コード
    ''' </summary>
    ''' <remarks></remarks>
    Protected Shared m_tantoCd As String = String.Empty

    ''' <summary>
    ''' 言語ID
    ''' </summary>
    ''' <remarks></remarks>
    Protected Shared m_langId As String = String.Empty

    ''' <summary>
    ''' テンプレートファイル
    ''' </summary>
    ''' <remarks></remarks>
    Private Shared m_templateFile As String = String.Empty

    ''' <summary>
    ''' 出力ファイル
    ''' </summary>
    ''' <remarks></remarks>
    Private Shared m_outputFile As String = String.Empty

    ''' <summary>
    ''' 出力ファイルタイプ
    ''' </summary>
    ''' <remarks></remarks>
    Private Shared m_fileType As FILE_TYPE = FILE_TYPE.EXCEL

#End Region

#End Region

#Region "Enum"

    ''' <summary>
    ''' パラメータINDEX（共通）
    ''' </summary>
    ''' <remarks></remarks>
    Protected Enum PRM_IDX_COMMON As Integer
        TANTO_CD = 0
        LANGUAGE_ID
        TEMPLATE_FILE
        OUTPUT_FILE
        FILE_TYPE
        COUNT
    End Enum

    ''' <summary>
    ''' 処理結果
    ''' </summary>
    ''' <remarks></remarks>
    Protected Enum PROC_RESULT As Integer
        正常終了 = 0
        異常終了_引数無 = -1
        異常終了_引数不足 = -2
        異常終了_引数不正 = -3
        異常終了_DBエラー = -11
        ロジックエラー = -100
        異常終了_EXCEL開始エラー = -200
        異常終了_ファイル変換エラー = -300
        異常終了_システム例外 = -999
    End Enum

    ''' <summary>
    ''' 出力ファイルタイプ
    ''' </summary>
    ''' <remarks></remarks>
    Protected Enum FILE_TYPE As Integer
        EXCEL = 1
        PDF
        FAX
    End Enum

#End Region

#Region "コンストラクタ"

    ''' <summary>
    ''' コンストラクタ
    ''' </summary>
    ''' <remarks></remarks>
    Public Sub New()

        InitLog()

    End Sub

    ''' <summary>
    ''' ログの初期設定
    ''' </summary>
    ''' <remarks></remarks>
    Private Sub InitLog()

        '実行ファイルのパスを取得
        Dim entryAsm As Assembly = Assembly.GetEntryAssembly()
        Dim dir As DirectoryInfo = Directory.GetParent(entryAsm.Location)
        'log4net設定ファイルのパス
        Dim configPath As String = Path.Combine(dir.FullName, "CommonReportLog.config")

        '設定ファイルの読込
        XmlConfigurator.Configure(New FileInfo(configPath))

        ' Loggerの生成
        Dim logger As ILog = LogManager.GetLogger("samplelogger")
        ' RootのLoggerを取得
        Dim rootLogger As Logger = CType(logger.Logger.Repository, Hierarchy).Root
        ' RootのAppenderを取得
        Dim appender As FileAppender = CType(rootLogger.GetAppender("LogAppender"), FileAppender)

        ' Logファイル名を上書き設定
        Dim filepath As String = appender.File
        appender.File = filepath.Replace("MyReportApp", entryAsm.GetName.Name)
        appender.ActivateOptions()

    End Sub

#End Region

#Region "メイン処理"

    ''' <summary>
    ''' メイン処理
    ''' </summary>
    ''' <param name="args"></param>
    ''' <remarks></remarks>
    Public Shared Function Main(args As String()) As Integer
        Try
            My.Application.Log.WriteEntry("=============================================================================================")
            My.Application.Log.WriteEntry("実行日:" & Now.ToString)
            My.Application.Log.WriteEntry("MAIN 開始")
            '引数チェック/取得
            My.Application.Log.WriteEntry("GetParamsCommon 開始")
            Dim getParam As PROC_RESULT = GetParamsCommon(args)
            My.Application.Log.WriteEntry("GetParamsCommon 終了")
            If getParam <> PROC_RESULT.正常終了 Then
                Return getParam
            End If
            My.Application.Log.WriteEntry("MAIN 終了")
            '帳票ファイル作成
            Return CreateReport()
        Catch ex As Exception
            'エラーメッセージ表示
            My.Application.Log.WriteEntry("MAIN error:" & ex.Message)
            m_log.Error(ex)
            Return PROC_RESULT.異常終了_システム例外
        Finally
            'アプリケーションの終了
            My.Application.Log.WriteEntry("MAIN Finally")
            m_log.Info("End.")
        End Try
    End Function

    ''' <summary>
    ''' 引数パラメータの取得
    ''' </summary>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Shared Function GetParamsCommon(args As String()) As PROC_RESULT

        '引数チェック（共通）
        If args Is Nothing OrElse args.Count = 0 Then
            Return PROC_RESULT.異常終了_引数無
        ElseIf args.Count < PRM_IDX_COMMON.COUNT Then
            Return PROC_RESULT.異常終了_引数不足
        End If

        '引数取得（共通）
        m_tantoCd = args(PRM_IDX_COMMON.TANTO_CD)
        m_langId = args(PRM_IDX_COMMON.LANGUAGE_ID)
        m_templateFile = args(PRM_IDX_COMMON.TEMPLATE_FILE)
        m_outputFile = args(PRM_IDX_COMMON.OUTPUT_FILE)

        My.Application.Log.WriteEntry("-----------------------------------------")
        My.Application.Log.WriteEntry("担当者:" & m_tantoCd)
        My.Application.Log.WriteEntry("言語id:" & m_langId)
        My.Application.Log.WriteEntry("テンプレート:" & m_templateFile)
        My.Application.Log.WriteEntry("出力ファイル:" & m_outputFile)

        Select Case args(PRM_IDX_COMMON.FILE_TYPE)
            Case "PDF"
                My.Application.Log.WriteEntry("PDF")
                m_fileType = FILE_TYPE.PDF
            Case "FAX"
                My.Application.Log.WriteEntry("FAX")
                m_fileType = FILE_TYPE.FAX
            Case Else
                My.Application.Log.WriteEntry("other")
                m_fileType = FILE_TYPE.EXCEL
        End Select

        Return PROC_RESULT.正常終了

    End Function

#End Region

#Region "Excel関連"

    ''' <summary>
    ''' 帳票ファイル作成
    ''' </summary>
    ''' <remarks></remarks>
    Private Shared Function CreateReport() As PROC_RESULT

        'Excelアプリケーション
        Dim exlApp As Excel.Application = Nothing
        'Excelワークブック
        Dim exlBook As Excel.Workbook = Nothing
        Try
            My.Application.Log.WriteEntry("CreateReport 開始v20230317")
            'テンプレート読み込み
            exlApp = CType(CreateObject("Excel.Application"), Excel.Application)
            My.Application.Log.WriteEntry("CreateReport Excel読込")
            'Excelを画面に表示しない
            exlApp.Application.Visible = False
            'アラートを出さない
            exlApp.DisplayAlerts = False
            ' マクロを起動するためにファイルを開く
            exlApp = New Excel.Application
            exlBook = exlApp.Workbooks.Open(Filename:=m_templateFile)
            My.Application.Log.WriteEntry("CreateReport Excelマクロ実行")
            'ファイル保存処理
            Select Case m_fileType
                Case FILE_TYPE.PDF
                    'PDFとして保存
                    exlBook.ExportAsFixedFormat(XlFixedFormatType.xlTypePDF, m_outputFile, XlFixedFormatQuality.xlQualityStandard)
                    My.Application.Log.WriteEntry("CreateReport PDF作成")
                Case Else
                    'Excelとして保存
                    exlBook.SaveAs(m_outputFile)
            End Select
            exlBook.Close(SaveChanges:=False)
            exlApp.Quit()

        Catch ex As Exception
            My.Application.Log.WriteEntry("CreateReport end " & ex.Message)
            'エラー出力
            m_log.Error("[CreateReport]", ex)
            Throw
        Finally
            My.Application.Log.WriteEntry("CreateReport PDFFinally")
            ReleaseObject(exlBook)
            ReleaseObject(exlApp)
        End Try

        ' 正常終了
        Return PROC_RESULT.正常終了

    End Function

    Private Shared Sub ReleaseObject(ByVal obj As Object)
        Try
            System.Runtime.InteropServices.Marshal.ReleaseComObject(obj)
            obj = Nothing
        Catch ex As Exception
            obj = Nothing
        Finally
            GC.Collect()
        End Try
    End Sub
#End Region

End Class
