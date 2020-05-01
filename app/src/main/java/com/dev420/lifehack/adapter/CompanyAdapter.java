package com.dev420.lifehack.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev420.lifehack.R;
import com.dev420.lifehack.data.Company;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>{

    private List<Company> companies;
    private OnCompanyClickListener onCompanyClickListener;

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
        notifyDataSetChanged();
    }

    public interface OnCompanyClickListener{
        void onCompanyClick(int position);
    }

    public void setOnCompanyClickListener(OnCompanyClickListener onCompanyClickListener){
        this.onCompanyClickListener = onCompanyClickListener;
    }


    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_item, parent, false);
        return new CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
        Company company = companies.get(position);
        holder.twCompanyName.setText(company.getName());
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public class CompanyViewHolder extends RecyclerView.ViewHolder{

        private TextView twCompanyName;

        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);
            twCompanyName = itemView.findViewById(R.id.twCompanyName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onCompanyClickListener != null){
                        onCompanyClickListener.onCompanyClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
