package by.maximoc.vacanciesandroid.utils;

public class SalaryFormat {

    public static String createStringSalary(int from, int to, String currency) {
        String salaryStr = "";
        if (from != 0)
            salaryStr = "От " + from + " ";
        if (to != 0)
            salaryStr = salaryStr + "До " + to;
        salaryStr = salaryStr + " " + currency;
        return salaryStr;
    }
}
