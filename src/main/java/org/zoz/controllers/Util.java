package org.zoz.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zoz.dossier.Dossier;
import org.zoz.dossier.Verdachte;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Util {



    private static Workbook excelFile = null;
    private static String filePath;
    private static int dossier;
    private static ArrayList<String> countries,ic;
    private static ObservableList<String> lijst;

    public static Parent loadFMXL(String fxml) throws IOException{
        URL url = Util.class.getResource("/org/zoz/fxml/"+fxml+".fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        return root;
    }

    public static FXMLLoader getLoader(String fxml) throws IOException{
        URL url = Util.class.getResource("/org/zoz/fxml/"+fxml+".fxml");
        return new FXMLLoader(url);
    }

    public static URL getPath(String file){
        URL url = Util.class.getResource("/org/zoz/fxml/"+file+".fxml");
        return url;
    }

    public static Parent getParent(FXMLLoader loader) throws IOException{
        return loader.load();
    }

    public static String getStyle(String styleSheet) {
        return Util.class.getResource("/org/zoz/styles/"+styleSheet+".css").toExternalForm();
    }

    public static void setExcel(String path) throws IOException {


        File file = new File(path);

        // Set the file path for later use
        filePath = file.getAbsolutePath();

        // Load the workbook only once
        if (excelFile == null) {
            try (FileInputStream fis = new FileInputStream(file)) {
                excelFile = new XSSFWorkbook(fis);
                System.out.println("Workbook loaded successfully.");
            }
        } else {
            System.out.println("Workbook is already loaded.");
        }

    }

    public static Workbook getExcel(){
        return Util.excelFile;
    }

    public static String getFilePath() {
        return filePath;
    }
    
    private static String getCellValue(Cell cell) {
        // Simplified cell value extraction based on the cell type
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return DateUtil.isCellDateFormatted(cell)
                    ? cell.getDateCellValue().toString()
                    : String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "Unknown Cell Type";
        }
    }

    public static double getBottomMostCellInFirstColumn() {
        // Access the sheet called "AANGIFTE"
        Sheet sheet = excelFile.getSheet("AANGIFTE");

        // Get the last row number (0-based index)
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("Number of rows: "+lastRowNum);

        // Iterate from the last row upwards to find the last non-empty cell in the first column
        for (int rowIndex = lastRowNum; rowIndex >= 0; rowIndex--) {
            Row row = sheet.getRow(rowIndex);
            if (row != null) {
                Cell cell = row.getCell(0); // Get the first column (index 0)
                if (cell != null && cell.getCellType() != CellType.BLANK) {

                    //System.out.println(cell.getCellType());
                    //System.out.println(cell.getStringCellValue());
                    return cell.getNumericCellValue(); // Return the value of the first non-empty cell
                }
            }
        }

        return -1;
    }

    public static void setDossier(int dossierNum){
        Util.dossier = dossierNum;
    }
    public static int getDossier(){
        return Util.dossier;
    }

    public static void loadCountries(){
        
        countries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/org/zoz/data/countries.csv"), "UTF-8"))) {
            String line;
            // Skip the header
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                countries.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void loadIC(){
        ic = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/org/zoz/data/incident_codes.csv"), "UTF-8"))) {
            String line;
            // Skip the header
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                ic.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getCountries(){
        return Util.countries;
    }

    public static ArrayList<String> getIC(){
        return Util.ic;
    }

    public static void setTempLijst(ObservableList<String> lijst){
        Util.lijst = lijst;
    }
    public static ObservableList<String> getTempLijst(){
        return Util.lijst;
    }

    public static void saveToExcel(Dossier dossier) throws IOException{


        Sheet aangifteSheet = excelFile.getSheet("AANGIFTE");
        Sheet verdachteSheet = excelFile.getSheet("VERDACHTE");
        Sheet voortgangSheet = excelFile.getSheet("VOORTANG");

        // write aangifte sheet
        int lastRow = aangifteSheet.getLastRowNum();
        aangifteSheet.createRow(lastRow+1);
        String[] data = dossier.getAangifte().getInfo().split(";");
        String mutatieNummer = data[0];
        String aangifteDatum = data[1];

        aangifteSheet.getRow(lastRow).createCell(0).setCellValue(dossier.getId());
        for (int i = 0; i<data.length; i++){
            aangifteSheet.getRow(lastRow).createCell(i+1).setCellValue(data[i]);
            System.out.println("cel: "+i);
        }

        // write verdachte sheet
        

        // for every verdachte, create a row, use dossiernum that we already have, and copy the other stuff we want
        for (Verdachte verdachte:dossier.getAangifte().getVerdachtes()){
            lastRow = verdachteSheet.getLastRowNum();
            verdachteSheet.createRow(lastRow+1);

            data = verdachte.getInfo().split(";");


            verdachteSheet.getRow(lastRow).createCell(0).setCellValue(dossier.getId());
            verdachteSheet.getRow(lastRow).createCell(1).setCellValue(mutatieNummer);
            verdachteSheet.getRow(lastRow).createCell(2).setCellValue(aangifteDatum);
            for(int j = 0; j<data.length;j++){
                if (j==0){
                    verdachteSheet.getRow(lastRow).createCell(j+3).setCellValue(data[j]); 
                } else {
                    verdachteSheet.getRow(lastRow).createCell(j+6).setCellValue(data[j]); 
                }
            }




        }

        

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            excelFile.write(fos);
        }
    }

    public static Dossier createNewDossier() {
        Dossier dossier = new Dossier();

        double currentDossier = Util.getBottomMostCellInFirstColumn() + 1;

        dossier.setId((int) currentDossier);

        return dossier;

    }
}
