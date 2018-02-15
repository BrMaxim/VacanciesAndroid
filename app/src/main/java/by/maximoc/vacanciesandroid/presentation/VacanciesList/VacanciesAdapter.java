package by.maximoc.vacanciesandroid.presentation.VacanciesList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.maximoc.vacanciesandroid.R;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Item;
import by.maximoc.vacanciesandroid.domain.entities.pojo.GsonVacancies.Vacancies;
import by.maximoc.vacanciesandroid.utils.StringHtmlFormat;

public class VacanciesAdapter extends RecyclerView.Adapter<VacanciesAdapter.ViewHolder> {

    private Listener clickListener;
    private List<Item> vacancies;

    public interface Listener {
        void onClick(String urlVacancy, String nameVacancy);
    }

    void setClickListener(Listener clickListener) {
        this.clickListener = clickListener;
    }

    VacanciesAdapter() {
        vacancies = new ArrayList<>();
    }

    void updateAdapter(Vacancies vacancies) {
        if (this.vacancies == null) {
            this.vacancies = vacancies.getItems();
        } else if (vacancies != null) {
            this.vacancies.addAll(vacancies.getItems());
        }
        notifyItemRangeInserted(getItemCount(), getItemCount() + this.vacancies.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameVacancies;
        private final TextView descriptionVacancies;
        private final TextView companyVacancies;
        private final TextView cityVacancies;
        private final TextView subwayVacancies;
        private final TextView dateVacancies;
        private final TextView salaryVacancies;
        private final View view;

        ViewHolder(View v) {
            super(v);
            view = v;
            nameVacancies = v.findViewById(R.id.name_vacancies);
            descriptionVacancies = v.findViewById(R.id.descriptions_vacancies);
            companyVacancies = v.findViewById(R.id.company_vacancies);
            cityVacancies = v.findViewById(R.id.city);
            subwayVacancies = v.findViewById(R.id.subway);
            dateVacancies = v.findViewById(R.id.date);
            salaryVacancies = v.findViewById(R.id.salary);

            view.setOnClickListener(v1 -> {
                if (clickListener != null) {
                    Item vacancy = vacancies.get(getAdapterPosition());
                    clickListener.onClick(vacancy.getId(), vacancy.getName());
                }
            });
        }
    }

    @Override
    public VacanciesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_vacancies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Item vacancy = vacancies.get(position);

        holder.nameVacancies.setText(vacancy.getName());
        holder.companyVacancies.setText(vacancy.getEmployer().getName());
        holder.cityVacancies.setText(vacancy.getArea().getName());
        holder.dateVacancies.setText(vacancy.getPublishedAt());
        holder.salaryVacancies.setText(vacancy.getFormatSalary());

        if (vacancy.getSnippet().getRequirement() != null) {
            holder.descriptionVacancies.setText(StringHtmlFormat.fromHtml(vacancy.getSnippet().getRequirement()));
        }
        if (vacancy.getAddress() != null && vacancy.getAddress().getMetro() != null) {
            holder.subwayVacancies.setVisibility(View.VISIBLE);
            holder.subwayVacancies.setText(vacancy.getAddress().getMetro().getStationName());
        } else {
            holder.subwayVacancies.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return vacancies == null ? 0 : vacancies.size();
    }
}
