package by.maximoc.vacanciesandroid.data.mapper;

import java.util.List;

import by.maximoc.vacanciesandroid.domain.entities.pojo.Address;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Item;
import by.maximoc.vacanciesandroid.domain.entities.pojo.Salary;

public interface IVacanciesMapper {

    String formatAddress(Address address);

    void formatSalaryItems(List<Item> items);

    void formatDateItems(List<Item> items);

    String formatSalary(Salary salary);
}
