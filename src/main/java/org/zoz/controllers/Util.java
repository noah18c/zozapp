package org.zoz.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
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
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/org/zoz/data/incidentcodes_2024.csv"), "UTF-8"))) {
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

    private static boolean isFileLocked(String path) {
        File file = new File(path);
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            // Attempting to open a file output stream will fail if the file is locked
            return false; // File is not locked
        } catch (IOException e) {
            return true; // File is locked
        }
    }
    

    public static void saveToExcel(Dossier dossier) throws IOException{
        if (isFileLocked(filePath)) {
            throw new IOException("The file is currently open by another application. Please close it and try again.");
        }

        // load all sheets
        Sheet aangifteSheet = excelFile.getSheet("AANGIFTE");
        Sheet verdachteSheet = excelFile.getSheet("VERDACHTE");
        Sheet voortgangSheet = excelFile.getSheet("VOORTGANG");

        // load aangiftesheet data
        int lastRow = aangifteSheet.getLastRowNum()+1;
        aangifteSheet.createRow(lastRow);
        String[] data = dossier.getAangifte().getInfo().split(";");
        String mutatieNummer = data[0];
        String aangifteDatum = data[1];


        // set first cell as dossiernum
        aangifteSheet.getRow(lastRow).createCell(0).setCellValue(dossier.getId());

        // iterate over all data, create a cell, insert it into the cell
        for (int i = 0; i<data.length; i++){
            if(i == 1){
                insertDate(aangifteSheet, data[i], i+1, lastRow);
            } else {
                aangifteSheet.getRow(lastRow).createCell(i+1).setCellValue(data[i]);
            }
            System.out.println("cel: "+i);
        }
        

        // for every verdachte, create a row, use dossiernum that we already have, and copy the other stuff we want
        for (Verdachte verdachte:dossier.getAangifte().getVerdachtes()){
            lastRow = verdachteSheet.getLastRowNum()+1;
            verdachteSheet.createRow(lastRow);

            data = verdachte.getInfo().split(";");


            verdachteSheet.getRow(lastRow).createCell(0).setCellValue(dossier.getId());
            verdachteSheet.getRow(lastRow).createCell(1).setCellValue(mutatieNummer);
            insertDate(verdachteSheet, aangifteDatum, 2, lastRow);
            for(int j = 0; j<data.length;j++){
                if (j==6){
                    insertDate(verdachteSheet, data[j], j+3, lastRow);
                } else {
                    verdachteSheet.getRow(lastRow).createCell(j+3).setCellValue(data[j]); 
                }
            }
        }

        // write voortgang 1 and 2
        lastRow = voortgangSheet.getLastRowNum()+1;
        voortgangSheet.createRow(lastRow);

        String dataString = dossier.getInfo1() +";"+ dossier.getInfo2();

        data = dataString.split(";");

        System.out.println(dataString);

        voortgangSheet.getRow(lastRow).createCell(0).setCellValue(dossier.getId());

        int lre = lastRow+1;

        voortgangSheet.getRow(lastRow).createCell(14).setCellFormula("IF(COUNTA($D"+lre+":$M"+lre+")=0,\"!!!\",IFERROR(LOOKUP(2,1/($D"+lre+":$M"+lre+"=MAX($D"+lre+":$M"+lre+")),$D$1:$M$1),\"!!!\"))");
        voortgangSheet.getRow(lastRow).createCell(15).setCellFormula("IF(AND(COUNT($K"+lre+")=1,COUNT($L"+lre+")=0),DAYS360($K"+lre+",TODAY()),IF(AND(COUNT($K"+lre+")=1,COUNT($L"+lre+")=1),$L"+lre+"-$K"+lre+",\"!!!\"))");


        System.out.println(Arrays.toString(data));
        for (int i = 0; i<data.length;i++){

            // for voortgang #1
            if(i < 12){
                System.out.println("string date: "+data[i]);
                insertDate(voortgangSheet, data[i], i+1, lastRow);
            } else if(i<13) {
                voortgangSheet.getRow(lastRow).createCell(i+1).setCellValue(data[i]); 
            } else if(i == 16){
                System.out.println("string date: "+data[i]);
                insertDate(voortgangSheet, data[i], i+3, lastRow);   
            } else {
                // rest van voortgang #2
                voortgangSheet.getRow(lastRow).createCell(i+3).setCellValue(data[i]); 
            }
            

        } 
        

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            excelFile.write(fos);
        }  catch (IOException e) {
            throw new IOException("Error writing to Excel file. Ensure the file is not open.", e);
        }
    }

    private static void insertDate(Sheet sheet, String dataString, int column, int row){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date date = dateFormat.parse(dataString);
            System.out.println("current date: "+date.toString());
            sheet.getRow(row).createCell(column).setCellValue(date);

            // Optionally, you can create and set a date format (recommended)
            CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
            CreationHelper createHelper = sheet.getWorkbook().getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
            sheet.getRow(row).getCell(column).setCellStyle(cellStyle);
        } catch (ParseException e){
            System.out.println("There is an error converting the string to a date");
            //e.printStackTrace();
        }
    }

    public static Dossier createNewDossier() {
        Dossier dossier = new Dossier();

        double currentDossier = Util.getBottomMostCellInFirstColumn() + 1;

        dossier.setId((int) currentDossier);

        return dossier;

    }
}
