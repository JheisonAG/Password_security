package com.example.securitypassword;

import android.graphics.Color; // 👈 importa esto
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContrasnaAdapter extends RecyclerView.Adapter<ContrasnaAdapter.VH> {

    private final List<Password> original;
    private final List<Password> visible;

    public ContrasnaAdapter() {
        this.original = new ArrayList<>();
        this.visible = new ArrayList<>();
    }

    public void addPassword(Password p) {
        original.add(p);
        visible.add(p);
        notifyItemInserted(visible.size() - 1);
    }

    public void setAll(List<Password> list) {
        original.clear();
        original.addAll(list);
        visible.clear();
        visible.addAll(list);
        notifyDataSetChanged();
    }

    public void filter(String query) {
        visible.clear();
        if (query == null || query.trim().isEmpty()) {
            visible.addAll(original);
        } else {
            String q = query.toLowerCase(Locale.getDefault());
            for (Password p : original) {
                if ((p.getServicio() != null && p.getServicio().toLowerCase(Locale.getDefault()).contains(q)) ||
                        (p.getUsuario()  != null && p.getUsuario().toLowerCase(Locale.getDefault()).contains(q))) {
                    visible.add(p);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contrasena, parent, false);
        return new VH(v);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        Password p = visible.get(pos);
        h.txtServicio.setText(p.getServicio());
        h.txtUsuario.setText(p.getUsuario());

        // Fuerza negro siempre (evita que el tema los ponga grises)
        h.txtServicio.setTextColor(Color.BLACK);
        h.txtUsuario.setTextColor(Color.BLACK);
    }

    @Override public int getItemCount() { return visible.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView txtServicio, txtUsuario;
        VH(@NonNull View itemView) {
            super(itemView);
            txtServicio = itemView.findViewById(R.id.txtServicio);
            txtUsuario  = itemView.findViewById(R.id.txtUsuario);
        }
    }
}
