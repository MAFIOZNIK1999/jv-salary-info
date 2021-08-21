package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INFO = 0;
    private static final int NAME_INFO = 1;
    private static final int HOURS_INFO = 2;
    private static final int SALARY_FOR_HOURS = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int totalAmount = 0;
            for (String dataTotal : data) {
                String[] dataInfo = dataTotal.split(" ");
                if (name.equals(dataInfo[NAME_INFO])
                        && isCorrectDate(dateFrom, dataInfo, dateTo)) {
                    totalAmount += Integer.parseInt(dataInfo[HOURS_INFO])
                            * Integer.parseInt(dataInfo[SALARY_FOR_HOURS]);
                }
            }
            result.append(name)
                    .append(" - ")
                    .append(totalAmount)
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }

    public boolean isCorrectDate(String dateFrom,
                                 String[] dataInfo,
                                 String dateTo) {
        return LocalDate.parse(dateFrom, FORMATTER)
                .isBefore(LocalDate.parse(
                        dataInfo[DATE_INFO], FORMATTER))
                && LocalDate.parse(dateTo, FORMATTER)
                .plusDays(1).isAfter((LocalDate.parse(
                        dataInfo[DATE_INFO], FORMATTER)));
    }
}