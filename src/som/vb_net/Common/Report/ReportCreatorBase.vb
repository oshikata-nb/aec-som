Imports log4net
Imports Microsoft.Office.Interop
Imports Microsoft.Office.Interop.Excel
Imports PdfConverter
Imports System.Configuration
Imports System.IO
Imports log4net.Repository.Hierarchy
Imports log4net.Appender
Imports System.Reflection
Imports log4net.Config
Imports System.Windows.Forms

''' <summary>
''' 帳票作成ベースクラス
''' </summary>
''' <remarks></remarks>
Public MustInherit Class ReportCreatorBase

#Region "変数"

    ''' <summary>
    ''' ロガー
    ''' </summary>
    ''' <remarks></remarks>
    Private m_log As ILog _
            = LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType)

    ''' <summary>
    ''' FAX処理クラス
    ''' </summary>
    ''' <remarks></remarks>
    Private m_faxManager As New MyTalkFaxManager

#Region "引数パラメータ"

    ''' <summary>
    ''' 担当者コード
    ''' </summary>
    ''' <remarks></remarks>
    Protected m_tantoCd As String = String.Empty

    ''' <summary>
    ''' 言語ID
    ''' </summary>
    ''' <remarks></remarks>
    Protected m_langId As String = String.Empty

    ''' <summary>
    ''' テンプレートファイル
    ''' </summary>
    ''' <remarks></remarks>
    Private m_templateFile As String = String.Empty

    ''' <summary>
    ''' 出力ファイル
    ''' </summary>
    ''' <remarks></remarks>
    Private m_outputFile As String = String.Empty

    ''' <summary>
    ''' 出力ファイルタイプ
    ''' </summary>
    ''' <remarks></remarks>
    Private m_fileType As FILE_TYPE = FILE_TYPE.EXCEL

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
    Protected Function ExecuteProc(args As String()) As PROC_RESULT
        Try
            m_log.Info("Start.")

            '引数チェック/取得
            Dim getParam As PROC_RESULT = GetParamsCommon(args)
            If getParam <> PROC_RESULT.正常終了 Then
                Return getParam
            End If
            '帳票ファイル作成
            Return CreateReport()
        Catch ex As Exception
            'エラーメッセージ表示
            m_log.Error(ex)
            Return PROC_RESULT.異常終了_システム例外
        Finally
            'アプリケーションの終了
            m_log.Info("End.")
        End Try
    End Function

    ''' <summary>
    ''' 引数パラメータの取得
    ''' </summary>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Function GetParamsCommon(args As String()) As PROC_RESULT

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

        Select Case args(PRM_IDX_COMMON.FILE_TYPE)
            Case "PDF"
                m_fileType = FILE_TYPE.PDF
            Case "FAX"
                m_fileType = FILE_TYPE.FAX
            Case Else
                m_fileType = FILE_TYPE.EXCEL
        End Select

        '引数取得（個別）
        Return GetParams(args)

    End Function

    ''' <summary>
    ''' 引数の取得
    ''' </summary>
    ''' <param name="args"></param>
    ''' <remarks></remarks>
    Protected MustOverride Function GetParams(args As String()) As PROC_RESULT

#End Region

#Region "Excel関連"

    ''' <summary>
    ''' 帳票ファイル作成
    ''' </summary>
    ''' <remarks></remarks>
    Private Function CreateReport() As PROC_RESULT

        Try
            'Excelアプリケーション
            Dim exlApp As Excel.Application = Nothing
            Try
                'テンプレート読み込み
                exlApp = CType(CreateObject("Excel.Application"), Excel.Application)
                'Excelを画面に表示しない
                exlApp.Application.Visible = False
                'アラートを出さない
                exlApp.DisplayAlerts = False

                'Excelワークブック
                Dim exlBook As Excel.Workbook = Nothing
                Try
                    ' マクロを起動するためにファイルを開く
                    exlBook = exlApp.Application.Workbooks.Open(Filename:=m_templateFile)

                    'Excelワークシート
                    Dim exlSheet As Excel.Worksheet = Nothing
                    Try

                        'ファイル保存処理
                        Select Case m_fileType
                            Case FILE_TYPE.PDF
                                'PDFとして保存
                                exlBook.ExportAsFixedFormat(XlFixedFormatType.xlTypePDF, m_outputFile, XlFixedFormatQuality.xlQualityStandard)

                            Case FILE_TYPE.FAX
                                'Excelとして保存
                                Dim filePath As String = m_faxManager.GetOutputFilePath(m_outputFile)
                                exlBook.SaveAs(filePath)
                                m_faxManager.FileName = filePath      '送信ファイルの保存先を設定しておく

                                'FAX用CSVを出力
                                Dim csvPath As String = m_faxManager.GetOutputCsvPath(m_outputFile)
                                m_faxManager.OutputCsv(csvPath)

                            Case Else
                                'Excelとして保存
                                exlBook.SaveAs(m_outputFile)

                        End Select

                        ' 正常終了
                        Return PROC_RESULT.正常終了

                    Catch
                        Throw
                    Finally
                        If Not exlSheet Is Nothing Then
                            System.Runtime.InteropServices.Marshal.ReleaseComObject(exlSheet)
                            exlSheet = Nothing
                        End If
                    End Try

                Catch
                    Throw
                Finally
                    If Not exlBook Is Nothing Then
                        Try
                            Clipboard.Clear()
                            exlBook.Close(SaveChanges:=False)
                        Finally
                            System.Runtime.InteropServices.Marshal.ReleaseComObject(exlBook)
                            exlBook = Nothing
                        End Try
                    End If
                End Try

            Catch ex As Exception
                m_outputFile = String.Empty
                Throw
            Finally
                If Not exlApp Is Nothing Then
                    Try
                        exlApp.Quit()
                    Finally
                        System.Runtime.InteropServices.Marshal.ReleaseComObject(exlApp)
                        exlApp = Nothing
                    End Try
                End If
            End Try

        Catch ex As Exception
            'エラー出力
            m_log.Error("[CreateReport]", ex)
            Throw
        End Try

    End Function

#End Region

End Class
