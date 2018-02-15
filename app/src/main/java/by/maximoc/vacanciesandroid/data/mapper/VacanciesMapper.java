package by.maximoc.vacanciesandroid.data.mapper;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import by.maximoc.vacanciesandroid.R;
import by.maximoc.vacanciesandroid.domain.entities.pojo.Address;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Item;
import by.maximoc.vacanciesandroid.domain.entities.pojo.Salary;
import by.maximoc.vacanciesandroid.utils.DateFormat;

public class VacanciesMapper implements IVacanciesMapper {

    private static final int EMPTY_SALARY = 0;
    private static final String EMPTY_STRING = "";
    private final Context context;
    private final String formatSalaryFrom;
    private final String formatSalaryTo;
    private final String formatTwoString;

    @Inject
    public VacanciesMapper(Context context) {
        this.context = context;
        formatSalaryFrom = this.context.getString(R.string.format_salary_from);
        formatSalaryTo = this.context.getString(R.string.format_salary_to);
        formatTwoString = this.context.getString(R.string.format_two_string);
    }

    @Override
    public String formatAddress(Address address) {
        String addressStr = EMPTY_STRING;
        if (address == null) {
            return context.getString(R.string.vacancy_address);
        } else {
            if (address.getStreet() != null) {
                addressStr = address.getStreet();
            }
            if (address.getBuilding() != null) {
                addressStr = String.format(formatTwoString, addressStr, address.getBuilding());
            }
            return addressStr;
        }
    }

    @Override
    public void formatSalaryItems(List<Item> items) {
        for (Item item : items) {
            item.setFormatSalary(formatSalary(item.getSalary()));
        }
    }

    @Override
    public void formatDateItems(List<Item> items) {
        for (Item item : items) {
            item.setFormatSalary(formatSalary(item.getSalary()));
            item.setPublishedAt(formatDate(item.getPublishedAt()));
        }
    }

    private String formatDate(String date) {
        return DateFormat.createData(date);
    }

    @Override
    public String formatSalary(Salary salary) {
        String formatSalary = EMPTY_STRING;
        if (salary == null) {
            return context.getString(R.string.vacancy_salary);
        } else {
            if (salary.getFrom() != EMPTY_SALARY) {
                formatSalary = String.format(formatSalaryFrom, salary.getFrom());
            }
            if (salary.getTo() != EMPTY_SALARY) {
                formatSalary = String.format(formatSalaryTo, formatSalary, salary.getTo());
            }
            formatSalary = String.format(formatTwoString, formatSalary, salary.getCurrency());
            return formatSalary;
        }
    }
}
