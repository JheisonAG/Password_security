package com.example.securitypassword;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ContrasnaAdapter adapter;
    private EditText edtBuscar;

    private ActivityResultLauncher<Intent> addPasswordLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vistas
        RecyclerView recycler = findViewById(R.id.recyclerPasswords);
        FloatingActionButton fab = findViewById(R.id.btnAgregar);
        edtBuscar = findViewById(R.id.edtBuscar);

        // RecyclerView
        adapter = new ContrasnaAdapter();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        // Activity Result
        addPasswordLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String servicio = data.getStringExtra("servicio");
                        String usuario  = data.getStringExtra("usuario");
                        String pass     = data.getStringExtra("password");

                        adapter.addPassword(new Password(servicio, usuario, pass));

                        // Re-aplicar filtro si había texto en búsqueda
                        CharSequence q = edtBuscar.getText();
                        adapter.filter(q != null ? q.toString() : "");
                    }
                }
        );

        // Abrir pantalla de agregar
        fab.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, AgregarContrasenaActivity.class);
            addPasswordLauncher.launch(i);
        });

        // Búsqueda
        edtBuscar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s != null ? s.toString() : "");
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }
}
