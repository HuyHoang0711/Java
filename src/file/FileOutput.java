/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;
import classname.GiangVien;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.ss.usermodel.BuiltinFormats;

// Thư viện cho Word
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
// Thư viện cho Excel
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xslf.usermodel.SlideLayout;

// Thư viện cho Pdf
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
/**
 *
 * @author Admin
 */
public class FileOutput {
    private static final String fontTieuDe = "font/vuTimesBold.ttf";
    private static final String fontDuLieu = "font/vuTimes.ttf";
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    public static void word(ArrayList<GiangVien> listGiangVien, TableModel tableModel)
    {
        XWPFDocument document = new XWPFDocument();
        XWPFHeader header = document.createHeader(HeaderFooterType.FIRST);
        XWPFParagraph paragraph = header.createParagraph();
        XWPFParagraph paragraphHeading = document.createParagraph();
        paragraphHeading.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun run = paragraph.createRun();
        run.setFontFamily("Time News Roman");
        run.setFontSize(14);
        run.setText("Lịch thực hành");
        run = paragraphHeading.createRun();
        run.setFontFamily("Time News Roman");
        run.setText("LỊCH HỌC THỰC HÀNH");
        run.setFontSize(20);
        XWPFTable table = document.createTable(listGiangVien.size()+1, tableModel.getColumnCount()+1);      
        // Khởi tạo ColumnHeader
        XWPFParagraph paragraphData = document.createParagraph();
        for(int i=0; i<tableModel.getColumnCount()+1; i++)
        {        
             run= paragraphData.createRun();        
             run.setFontFamily("Times New Roman");
             run.setFontSize(14);
             paragraphData.setAlignment(ParagraphAlignment.CENTER);
             run.setBold(true);
             //table.getRow(0).getCell(i).setWidth("5000");
             if(i==0)
                 run.setText("STT");
             else
             run.setText(tableModel.getColumnName(i-1));
              table.getRow(0).getCell(i).setParagraph(paragraphData);
              paragraphData.removeRun(0);
        }  
        for(int i=1; i<listGiangVien.size()+1; i++)
        {
            for(int j=0; j<tableModel.getColumnCount()+1; j++)
            {
                run = paragraphData.createRun();
                //paragraphColumnHeader.setAlignment(ParagraphAlignment.LEFT);
                run.setFontFamily("Times New Roman");
                run.setFontSize(14);
                switch(j)
                  {
                    case 0:
                    run.setText(String.valueOf(i));
                    break;
                    case 1:
                    run.setText(listGiangVien.get(i-1).getListLopDocLap().get(0).getHeDaoTao());
                    break;
                    case 2:
                   run.setText(listGiangVien.get(i-1).getListLopDocLap().get(0).getKhoaDaoTao());
                   break;
                    case 3:
                    run.setText(listGiangVien.get(i-1).getListLopDocLap().get(0).getMaLop());
                    break;
                    case 4:
                    run.setText(listGiangVien.get(i-1).getListDangKyPhong().get(0).getNhom());
                    break;
                    case 5:
                    run.setText(String.valueOf(listGiangVien.get(i-1).getListLopDocLap().get(0).getSoLuongHSSV()));
                    break;
                    case 6:
                    run.setText(listGiangVien.get(i-1).getListLopDocLap().get(0).getTenMonHoc());
                    break;
                    case 7:
                    run.setText(sdf.format(listGiangVien.get(i-1).getListDangKyPhong().get(0).getThoiGian()));
                    break;
                    case 8:
                    run.setText(listGiangVien.get(i-1).getListDangKyPhong().get(0).getCaThucHanh());
                    break;
                    case 9:
                    run.setText(listGiangVien.get(i-1).getListDangKyPhong().get(0).getMaPhong());
                    break;
                    case 10:
                    run.setText(listGiangVien.get(i-1).getHoTen());
                    break;
                    default:
                    run.setText("Không có thông tin");
                  }               
                    table.getRow(i).getCell(j).setWidth("1000");
                    table.getRow(i).getCell(j).setParagraph(paragraphData);  
                    paragraphData.removeRun(0);
            }  
        }   
        try{
        FileOutputStream fos = new FileOutputStream(new File("LichThuHanh.docx"));
        document.write(fos);
        fos.close();
        document.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }      
    }
    public static void excel(ArrayList<GiangVien> listGiangVien, TableModel tableModel)
    {
        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet("Lịch thực hành");
        //String viTri = String.valueOf((char)(tableModel.getColumnCount()+98)).toUpperCase() + String.valueOf(listGiangVien.size());
        //System.out.println("Vị trí: " + viTri);
        //Font tiêu đề chính
        Font fontTieuDe = sheet.getWorkbook().createFont();
        fontTieuDe.setFontName("Times New Roman");
        fontTieuDe.setFontHeightInPoints((short)20);
        fontTieuDe.setBold(true);
        //
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short)14);
        font.setBold(true);
        //
        Font fontData = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short)14);
        //
        
        //DataFormat dataFormat = workBook.createDataFormat();
        //short format = dataFormat.getFormat("##");
        //
        XSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        XSSFCellStyle styleTieuDe = sheet.getWorkbook().createCellStyle();
        XSSFCellStyle cellStyleData = sheet.getWorkbook().createCellStyle();
        XSSFCellStyle cellStyleSubject = sheet.getWorkbook().createCellStyle();
       // XSSFCellStyle cellNumber = sheet.getWorkbook().createCellStyle();
     //  cellNumber.setDataFormat(format);
        cellStyleSubject.setFont(fontData);
        styleTieuDe.setFont(fontTieuDe);
        cellStyle.setFont(font);
        cellStyleData.setFont(fontData);
        cellStyleData.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        styleTieuDe.setAlignment(HorizontalAlignment.CENTER);
        cellStyleSubject.setAlignment(HorizontalAlignment.JUSTIFY);
        //AreaReference tableArea = new AreaReference("A1:"+viTri, sheet.getWorkbook().getSpreadsheetVersion());
        //XSSFTable table = sheet.createTable(tableArea);
        //Tạo tiêu đề LỊCH HỌC THỰC HÀNH
       XSSFRow row = sheet.createRow(0);
       XSSFCell cell = row.createCell(0);
       cell.setCellStyle(styleTieuDe);
       cell.setCellValue("LỊCH HỌC THỰC HÀNH");
       sheet.addMergedRegion(new CellRangeAddress(0,0,0,tableModel.getColumnCount()+1));
        row = sheet.createRow(3);
        for(int i=0; i<tableModel.getColumnCount()+2; i++)
        {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
             sheet.setColumnWidth(i, 4500);
             if(i==6)
             {
             sheet.setColumnWidth(i, 9000);         
             }
             if(i==0 || i==1 || i==2 || i==4 || i==8 || i==9)
            {
                sheet.setColumnWidth(i, 2500);
            }
            if(i==0)
            {
                cell.setCellValue("STT");
                continue;
            }
          
            if((i+1)==(tableModel.getColumnCount()+2))
            {
                cell.setCellValue("Ghi chú");
                break;
            }
            cell.setCellValue(tableModel.getColumnName(i-1));
        }
        // In dữ liệu
         for(int i=0; i<listGiangVien.size(); i++)
        {
            row = sheet.createRow(4+i);
            for(int j=0; j<tableModel.getColumnCount()+2; j++)
            {
                cell = row.createCell(j);
                cell.setCellStyle(cellStyleData);
                //paragraphColumnHeader.setAlignment(ParagraphAlignment.LEFT);
                switch(j)
                  {
                    case 0:
                    cell.setCellValue(i+1);
                    break;
                    case 1:
                    cell.setCellValue(listGiangVien.get(i).getListLopDocLap().get(0).getHeDaoTao());
                    break;
                    case 2:
                   cell.setCellValue(listGiangVien.get(i).getListLopDocLap().get(0).getKhoaDaoTao());
                   break;
                    case 3:
                    cell.setCellValue(listGiangVien.get(i).getListLopDocLap().get(0).getMaLop());
                    break;
                    case 4:
                    cell.setCellValue(listGiangVien.get(i).getListDangKyPhong().get(0).getNhom());
                    break;
                    case 5:
                    cell.setCellValue(listGiangVien.get(i).getListLopDocLap().get(0).getSoLuongHSSV());
                    break;
                    case 6:
                   cell.setCellStyle(cellStyleSubject);
                    cell.setCellValue(listGiangVien.get(i).getListLopDocLap().get(0).getTenMonHoc());
                    break;
                    case 7:
                    cell.setCellValue(sdf.format(listGiangVien.get(i).getListDangKyPhong().get(0).getThoiGian()));
                    break;
                    case 8:
                    cell.setCellValue(listGiangVien.get(i).getListDangKyPhong().get(0).getCaThucHanh());
                    break;
                    case 9:
                    cell.setCellValue(listGiangVien.get(i).getListDangKyPhong().get(0).getMaPhong());
                    break;
                    case 10:
                    cell.setCellValue(listGiangVien.get(i).getHoTen());
                    break;
                    case 11:
                    break;
                    default:
                    cell.setCellValue("Không có thông tin");
                  }               
                    
            }  
        }          
        try{
        FileOutputStream fos = new FileOutputStream(new File("LichThuHanh.xlsx"));
        workBook.write(fos);
        fos.close();
        workBook.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }      
    }
    public static void pdf(ArrayList<GiangVien> listGiangVien, TableModel tableModel)
    {
        Document document = new Document();
        String duLieuCot = "";
        try
        {
        PdfWriter.getInstance(document, new FileOutputStream("LichThucHanh.pdf"));
        document.open();
        com.itextpdf.text.Font tieuDe = FontFactory.getFont(fontTieuDe, BaseFont.IDENTITY_H, true);
        com.itextpdf.text.Font duLieu = FontFactory.getFont(fontDuLieu, BaseFont.IDENTITY_H, true);
        tieuDe.setSize(20);
        duLieu.setSize(14);
        Paragraph paragraphTieuDe = new Paragraph("LỊCH HỌC THỰC HÀNH", tieuDe);
        paragraphTieuDe.setAlignment(Element.ALIGN_CENTER);
        paragraphTieuDe.setSpacingAfter(30F);
        document.add(paragraphTieuDe);
        PdfPTable table = new PdfPTable(tableModel.getColumnCount()+1);
        table.setWidthPercentage(100);
        float[] widths = {10, 10, 15, 20, 15, 15, 20, 25, 10, 15, 15}; 
        table.setWidths(widths);
        tieuDe.setSize(14);
        for(int i=0; i<tableModel.getColumnCount()+1; i++)
        {
            if(i==0)
         {
        table.addCell(new Paragraph("STT", tieuDe));
        continue;
        }
        table.addCell(new Paragraph(tableModel.getColumnName(i-1), tieuDe));
        }
        for(int i=1; i<listGiangVien.size()+1; i++)
        {
            for(int j=0; j<tableModel.getColumnCount()+1; j++)
            {
                switch(j)
                  {
                    case 0:
                    duLieuCot = String.valueOf(i);
                    break;
                    case 1:
                    duLieuCot = listGiangVien.get(i-1).getListLopDocLap().get(0).getHeDaoTao();
                    break;
                    case 2:
                    duLieuCot = listGiangVien.get(i-1).getListLopDocLap().get(0).getKhoaDaoTao();
                   break;
                    case 3:
                    duLieuCot = listGiangVien.get(i-1).getListLopDocLap().get(0).getMaLop();
                    break;
                    case 4:
                    duLieuCot = listGiangVien.get(i-1).getListDangKyPhong().get(0).getNhom();
                    break;
                    case 5:
                    duLieuCot = String.valueOf(listGiangVien.get(i-1).getListLopDocLap().get(0).getSoLuongHSSV());
                    break;
                    case 6:
                    duLieuCot = listGiangVien.get(i-1).getListLopDocLap().get(0).getTenMonHoc();
                    break;
                    case 7:
                    duLieuCot = listGiangVien.get(i-1).getListDangKyPhong().get(0).getThoiGian().toString();
                    break;
                    case 8:
                    duLieuCot = listGiangVien.get(i-1).getListDangKyPhong().get(0).getCaThucHanh();
                    break;
                    case 9:
                    duLieuCot = listGiangVien.get(i-1).getListDangKyPhong().get(0).getMaPhong();
                    break;
                    case 10:
                     duLieuCot = listGiangVien.get(i-1).getHoTen();
                    break;
                    default:
                    table.addCell("Không có thông tin");
            }
                Paragraph paragraphCot = new Paragraph(duLieuCot, duLieu);
                PdfPCell cell = new PdfPCell(paragraphCot);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
        }
        }
        document.add(table);
        }
        catch(DocumentException|IOException ex)
        {
            ex.printStackTrace();
        }
        document.close();
    }
}
