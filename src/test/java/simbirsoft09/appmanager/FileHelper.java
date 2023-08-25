package simbirsoft09.appmanager;

import io.qameta.allure.Step;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileHelper extends BaseObject {
    public FileHelper(MyAppManager app) {
        super(app);
    }

    @Step("Изменить строковое значение {str} с одним элементом после точки")
    public String changeValue(String str) {
        String[] list = str.split("\\.");
        return list[0] + "." + list[1].charAt(0);
    }

    @Step("Привести номер телефона в одному формату")
    public String formatPhoneNumber(String phoneNumber) {
        String formattedNumber = phoneNumber.replaceAll("[^\\d]", "");
        if (formattedNumber.startsWith("8")) {
            formattedNumber = "7" + formattedNumber.substring(1);
        }
        return formattedNumber;
    }

    @Step("Получить текущую дату в формате {pattern}")
    public String getCurrentDate(String pattern) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    @Step("Конвертировать дату из одного паттерна в другой")
    public String convertDateInPattern(String fromPattern, String toPattern, String date) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat(fromPattern);
        SimpleDateFormat sdf2 = new SimpleDateFormat(toPattern);
        return sdf2.format(sdf1.parse(date));
    }

    @Step("Получить число Фибоначи из списка по индексу {index}")
    public int getFibonacciNumber(int index) {
        return 0;
    }

    @Step("")
    public int getFirstElementFromString(int index) {
        return 0;
    }
}