package pl.kpp;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import pl.kpp.controllers.materials.ShowTransactionScreenController;
import pl.kpp.materials.ArticleInTransaction;
import pl.kpp.materials.Transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class HandlingPdfFiles extends Application {
    private String nameFileToSave ="";
    public String print(Transaction transaction, ObservableList<ArticleInTransaction> list) {
        if (transaction.getType() == 1) {
            String nameTransaction = transaction.getNameTransaction().replace("/","_");
            nameFileToSave=ShowTransactionScreenController.getPath()+"/Dokument wydania "+nameTransaction+".pdf";
            Document document = new Document();
            Font fontInTable = FontFactory.getFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, 15);
            Font fontHeadersTable = FontFactory.getFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1250, 20);
            Font fontHeaders = FontFactory.getFont(BaseFont.COURIER_BOLD, BaseFont.CP1250, 30);
            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nameFileToSave));
                document.open();
                Paragraph paragraph = new Paragraph("Dokument wydania z magazynu materiałów informatycznych " + transaction.getNameTransaction(), fontHeaders);
                paragraph.setAlignment(Element.ALIGN_CENTER);
                document.add(paragraph);
                Paragraph paragraphCustomer = new Paragraph("Odbiorca:    " + transaction.getCustomerName() +
                        "\n", fontHeadersTable);
                paragraphCustomer.setSpacingBefore(20f);
                paragraphCustomer.setSpacingAfter(20f);
                paragraphCustomer.setAlignment(Element.ALIGN_CENTER);
                document.add(paragraphCustomer);
                PdfPTable table1 = new PdfPTable(new float[]{3, 1});
                table1.setSpacingBefore(10f);
                table1.addCell(createCell("Materiał", fontHeadersTable, false));
                table1.addCell(createCell("Ilość", fontHeadersTable, false));
                document.add(table1);
                for (ArticleInTransaction article : list) {
                    PdfPTable table = new PdfPTable(new float[]{3, 1});
                    table.addCell(createCell(article.getArticleInTransactionMaterial().getName(), fontInTable, false));
                    table.addCell(createCell(Integer.toString(article.getCount()), fontInTable, false));
                    document.add(table);
                }
                PdfPTable tableDate = new PdfPTable(new float[]{1f, 1f});
                tableDate.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                tableDate.setSpacingBefore(20f);
                tableDate.addCell(createCell(" ", fontInTable, true));
                tableDate.addCell(createCell(transaction.getDateTransaction().toString(), fontInTable, true));
                tableDate.addCell(createCell(" ", fontInTable, true));
                tableDate.addCell(createCell("Potwierdzam odbiór", fontInTable, true));
                tableDate.addCell(createCell(" ", fontInTable, true));
                tableDate.addCell(createCell(" ", fontInTable, true));
                tableDate.addCell(createCell(" ", fontInTable, true));
                tableDate.addCell(createCell("....................................", fontInTable, true));
                document.add(tableDate);
                document.close();
                writer.close();
            } catch (DocumentException e) {
                System.out.println("Błąd dokumenty " + e);
            } catch (FileNotFoundException e) {
                System.out.println("nie znaleziono dokumentu " + e);
            }
            return  nameFileToSave;
        }
            else{
                CreateWindowAlert.createWindowError("Nie można wygenerować pliku pdf do dokumentu Przyjęcia");
            }return ("brak pliku");
    }
    private PdfPCell createCell(String string, Font font, boolean border) {
        PdfPCell cell1 = new PdfPCell(new Paragraph(string, font));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        if (border)
            cell1.setBorder(PdfPCell.NO_BORDER);
        return cell1;
    }

    public void showPdfFile(String path){
        File file = null;
        file = new File(path);
        HostServices hostServices = getHostServices();
        hostServices.showDocument(file.getAbsolutePath());
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
