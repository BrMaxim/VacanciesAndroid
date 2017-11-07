package by.maximoc.vacanciesandroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.maximoc.vacanciesandroid.R;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Item;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Salary;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.utils.DateFormat;
import by.maximoc.vacanciesandroid.utils.StringHtmlFormat;

public class VacanciesAdapter extends RecyclerView.Adapter<VacanciesAdapter.ViewHolder> {

    private Listener clickListener;
    private List<Item> items;

    public interface Listener {
        void onClick(String urlVacancy);
    }

    public void setClickListener(Listener clickListener) {
        this.clickListener = clickListener;
    }

    public VacanciesAdapter() {
        items = new ArrayList<>();
    }

    public void updateAdapter(Vacancies vacancies) {
        if (this.items == null) {
            this.items = vacancies.getItems();
        }
        else if (vacancies != null){
            this.items.addAll(vacancies.getItems());
        }
        notifyItemRangeInserted(getItemCount(), getItemCount() + items.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameVacancies, descriptionVacancies, companyVacancies, cityVacancies, subwayVacancies,
                dateVacancies, salaryVacancies;

        private LinearLayout linearLayout;

        public ViewHolder(LinearLayout v) {
            super(v);
            linearLayout = v;
            nameVacancies = (TextView) v.findViewById(R.id.name_vacancies);
            descriptionVacancies = (TextView) v.findViewById(R.id.descriptions_vacancies);
            companyVacancies = (TextView) v.findViewById(R.id.company_vacancies);
            cityVacancies = (TextView) v.findViewById(R.id.city);
            subwayVacancies = (TextView) v.findViewById(R.id.subway);
            dateVacancies = (TextView) v.findViewById(R.id.date);
            salaryVacancies = (TextView) v.findViewById(R.id.salary);
        }
    }

    @Override
    public VacanciesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_vacancies, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(VacanciesAdapter.ViewHolder holder, int position) {
        final LinearLayout layout = holder.linearLayout;
        holder.nameVacancies.setText(items.get(position).getName());

        if (items.get(position).getSnippet().getRequirement() != null) {
            holder.descriptionVacancies
                    .setText(new StringHtmlFormat().fromHtml(items.get(position).getSnippet().getRequirement()));
        }
        holder.companyVacancies.setText(items.get(position).getEmployer().getName());

        holder.cityVacancies.setText(items.get(position).getArea().getName());

        holder.dateVacancies.setText(DateFormat.createData(items.get(position).getPublishedAt()));

        if (items.get(position).getSalary() != null) {
            holder.salaryVacancies.setText(createStringSalary(items.get(position).getSalary()));
        } else {
            holder.salaryVacancies.setText(R.string.salary);
        }

        if (items.get(position).getAddress() != null
                && items.get(position).getAddress().getMetro() != null) {
            holder.subwayVacancies.setVisibility(View.VISIBLE);
            holder.subwayVacancies.setText(items.get(position).getAddress().getMetro().getStationName());
        } else {
            holder.subwayVacancies.setVisibility(View.GONE);
        }

        layout.setOnClickListener(v -> {
            if (clickListener != null)
                clickListener.onClick(items.get(position).getId());
        });
    }

    private String createStringSalary(Salary salary) {
        String salaryStr = "";
        if (salary.getFrom() != 0)
            salaryStr = "От " + salary.getFrom() + " ";
        if (salary.getTo() != 0)
            salaryStr = salaryStr + "До " + salary.getTo();
        salaryStr = salaryStr + " " + salary.getCurrency();
        return salaryStr;
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        } else {
            return 0;
        }
    }
}
