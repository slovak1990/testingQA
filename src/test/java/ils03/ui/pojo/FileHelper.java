package ils03.ui.pojo;

import io.qameta.allure.Step;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_NULL_AND_BLANK;

public class FileHelper {
    @Step("Изменить строковое значение {str} с одним элементом после точки")
    public String changeValue(String str) {
        String[] list = str.split("\\.");
        return list[0] + "." + list[1].charAt(0);
    }

    private Boolean checkExtensionFile(String fileName) {
        return fileName.split("\\.")[1].equals("xlsx");
    }

    @Step("Создание объекта Workbook")
    private Workbook getWorkBook(File file) {
        if (this.checkExtensionFile(file.getName())) {
            try {
                return new XSSFWorkbook(Files.newInputStream(file.toPath()));
            } catch (IOException e) {
                System.out.println("Файл не найден!!");
                throw new RuntimeException(e);
            }
        } else {
            try {
                return new HSSFWorkbook(Files.newInputStream(file.toPath()));
            } catch (IOException e) {
                System.out.println("Файл не найден!!");
                throw new RuntimeException(e);
            }
        }
    }

    @Step("Получить данные ячейки строки листа объекта Workbook")
    private String getStringValueFromCell(Workbook wb, String nameOfSheetInFile, int columnIndex, int rowIndex) {
        DataFormatter formatter = new DataFormatter();
        Sheet sheet = wb.getSheet(nameOfSheetInFile);
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.getCell(columnIndex, RETURN_NULL_AND_BLANK);
        return formatter.formatCellValue(cell);
    }

    @Step("Получить данные строки {rowIndex} листа {nameOfSheetInFile} объекта Workbook")
    public List<String> getCellValuesOfRowFromExcel(File file, String nameOfSheetInFile, int rowIndex) {
        List<String> cellValuesOfRow = new ArrayList<>();
        Workbook wb = getWorkBook(file);
        Sheet sheet = wb.getSheet(nameOfSheetInFile);
        rowIndex -= 1;
        int columnIndex = sheet.getRow(0).getLastCellNum();
        for (int col = 0; col < columnIndex; col++) {
            cellValuesOfRow.add(getStringValueFromCell(wb, nameOfSheetInFile, col, rowIndex));
        }
        System.out.println("Excel " + cellValuesOfRow.size());
        System.out.println("Excel " + Collections.singletonList(cellValuesOfRow));
        return cellValuesOfRow;
    }

    @Step("Получить количество строк данных листа {nameOfSheetInFile} объекта Workbook")
    public int getCountOfDataRowFromExcel(File file, String nameOfSheetInFile) {
        Workbook wb = getWorkBook(file);
        Sheet sheet = wb.getSheet(nameOfSheetInFile);
        return sheet.getPhysicalNumberOfRows() - 2;
    }

    @Step("Прочитать построчно текстовый файл и записать в список")
    public List<String> readFileRowByRow(File file) {
        List<String> listOfRows = new ArrayList<>();
        try (FileReader fr = new FileReader(file)) {
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                listOfRows.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfRows;
    }

    @Step("Получить кол-во строк файла {fileName} формата csv/txt")
    public int getCountLines(String fileName) {
        int lineCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(String.format("src/test/resources/data-files/%s", fileName)))) {
            while (br.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineCount - 1; // 1ая строка - заголовки
    }

    @Step("Сравнение двух списков на несовпадение")
    public void compareLists(List<String> list1, List<String> list2) {
        assert list1.size() == list2.size() : "Списки имеют разную длину";
        for (int i = 0; i < list1.size(); i++) {
            String value1 = list1.get(i);
            String value2 = list2.get(i);
            assert !value1.equals(value2) : "Значение \"" + value1 + "\" в первом списке совпадает со значением \"" + value2 + "\" во втором списке";
        }
    }

    @Step("Привести номер телефона в одному формату")
    public String formatPhoneNumber(String phoneNumber) {
        String formattedNumber = phoneNumber.replaceAll("[^\\d]", "");
        if (formattedNumber.startsWith("8")) {
            formattedNumber = "7" + formattedNumber.substring(1);
        }
        return formattedNumber;
    }

    @Step("Проверить больше 2 совпадений между списками")
    public boolean hasTwoOrMoreMatches(List<String> array, List<String> list) {
        Set<String> uniqueMatches = new HashSet<>();
        for (String element : array) {
            if (list.contains(element)) {
                uniqueMatches.add(element);
            }
        }
        return uniqueMatches.size() >= 2; // Более одного уникального совпадения найдено
    }

    @Step("Проверить что только 1 совпадение между списками")
    public boolean hasOnlyOneMatch(List<String> array, List<String> list) {
        Set<String> uniqueValues = new HashSet<>(list);
        int matchCount = 0;
        for (String element : array) {
            if (uniqueValues.contains(element)) {
                matchCount++;
                if (matchCount > 1) {
                    return false; // Более одного уникального совпадения найдено, возвращаем false
                }
            }
        }
        return matchCount == 1; // Возвращаем true, если найдено только одно совпадение
    }

    @Step("Распарсить значение строки по {parseSplitPattern} и вставить в список")
    public List<String> parseStringToList(String input, String parseSplitPattern) {
        String[] elements = input.split(parseSplitPattern);
        return new ArrayList<>(Arrays.asList(elements));
    }

    @Step("Получить значение {valuePrefix}")
    public String extractValues(String content, String valuePrefix) {
        StringBuilder result = new StringBuilder();
        int startIndex = 0;
        while (startIndex != -1) {
            int valueStartIndex = content.indexOf(valuePrefix, startIndex);
            if (valueStartIndex != -1) {
                int valueEndIndex = content.indexOf("\n", valueStartIndex);
                String value = content.substring(valueStartIndex + valuePrefix.length(), valueEndIndex).trim();
                result.append(value).append(" ");
                startIndex = valueEndIndex;
            } else {
                startIndex = -1;
            }
        }
        return result.toString().trim();
    }

    @Step("Оставить в строке только цифры")
    public String keepOnlyNumbers(String input) {
        return input.replaceAll("[^\\d]", "");
    }

    @Step("Оставить в списке значений только цифры")
    public List<String> keepOnlyNumbersInList(List<String> values) {
        List<String> result = new ArrayList<>();
        for (String value : values) {
            String digitsOnly = keepOnlyNumbers(value);
            result.add(digitsOnly);
        }
        return result;
    }

    @Step("Сравнить все значения из списка с 1 строкой")
    public boolean compareListWithString(List<String> list, String valueString) {
        for (String value : list) {
            if (!value.equals(valueString)) {
                return false;
            }
        }
        return true;
    }

    @Step("Сравнить 2 списка в разном порядке")
    public boolean compareListWithRandomIndex(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        List<String> sortedList1 = new ArrayList<>(list1);
        List<String> sortedList2 = new ArrayList<>(list2);
        Collections.sort(sortedList1);
        Collections.sort(sortedList2);
        return sortedList1.equals(sortedList2);
    }

    @Step("Сравнить строковые даты без учёта времени")
    public boolean compareDates(String date1, String date2) {
        String[] dates1 = date1.split(" ");
        String[] dates2 = date2.split(" ");
        return dates1[0].equals(dates2[0]);
    }

    @Step("Изменить формат даты из списка без учёта времени")
    public List<String> changeListFormatDates(List<String> dates) {
        List<String> list = new ArrayList<>();
        for (String element : dates) {
            String[] date = element.split(" ");
            list.add(date[0]);
        }
        return list;
    }
}
