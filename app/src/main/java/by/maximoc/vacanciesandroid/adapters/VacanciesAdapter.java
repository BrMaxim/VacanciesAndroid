package by.maximoc.vacanciesandroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import by.maximoc.vacanciesandroid.utils.StringHtmlFormat;
import by.maximoc.vacanciesandroid.utils.DateFormat;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Salary;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.R;

public class VacanciesAdapter extends RecyclerView.Adapter<VacanciesAdapter.ViewHolder> {

    private Vacancies vacancies;
    private Listener clickListener;

    public Vacancies getVacancies() {
        return vacancies;
    }

    public interface Listener {
        void onClick(String urlVacancy);
    }

    public void setClickListener(Listener clickListener) {
        this.clickListener = clickListener;
    }

    public VacanciesAdapter(Vacancies vacancies) {
        this.vacancies = vacancies;
    }

    public void updateAdapter(Vacancies vacancies) {
        if (this.vacancies == null)
            this.vacancies = vacancies;
        else {
            this.vacancies.getItems().addAll(vacancies.getItems());
        }
        notifyItemRangeInserted(getItemCount(), getItemCount() + vacancies.getItems().size());
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
        holder.nameVacancies.setText(vacancies.getItems().get(position).getName());
        if (vacancies.getItems().get(position).getSnippet().getRequirement() != null) {
            holder.descriptionVacancies
                    .setText(new StringHtmlFormat().fromHtml(vacancies.getItems().get(position).getSnippet().getRequirement()));
        }
        holder.companyVacancies.setText(vacancies.getItems().get(position).getEmployer().getName());
        holder.cityVacancies.setText(vacancies.getItems().get(position).getArea().getName());
        holder.dateVacancies.setText(DateFormat.createData(vacancies.getItems().get(position).getPublishedAt()));

        if (vacancies.getItems().get(position).getSalary() != null) {
            holder.salaryVacancies.setText(createStringSalary(vacancies.getItems().get(position).getSalary()));
        } else {
            holder.salaryVacancies.setText(R.string.salary);
        }

        if (vacancies.getItems().get(position).getAddress() != null
                && vacancies.getItems().get(position).getAddress().getMetro() != null) {
            holder.subwayVacancies.setVisibility(View.VISIBLE);
            holder.subwayVacancies.setText(vacancies.getItems().get(position).getAddress().getMetro().getStationName());
        } else {
            holder.subwayVacancies.setVisibility(View.GONE);
        }

        layout.setOnClickListener(v -> {
            if (clickListener != null)
                clickListener.onClick(vacancies.getItems().get(position).getId());
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
        if (vacancies != null) {
            return vacancies.getItems().size();
        } else {
            return 0;
        }
    }
}
