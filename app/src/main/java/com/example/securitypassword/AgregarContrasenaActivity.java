package com.example.securitypassword;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarContrasenaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contrasena);

        final EditText edtServicio = findViewById(R.id.edtServicio);
        final EditText edtUsuario  = findViewById(R.id.edtUsuario);
        final EditText edtPassword = findViewById(R.id.edtPassword);
        Button btnGuardar          = findViewById(R.id.btnGuardar);
        Button btnRetroceder       = findViewById(R.id.btnRetroceder);

        // Guardar con validación
        btnGuardar.setOnClickListener(v -> {
            String servicio = String.valueOf(edtServicio.getText()).trim();
            String usuario  = String.valueOf(edtUsuario.getText()).trim();
            String password = String.valueOf(edtPassword.getText()).trim();

            if (servicio.isEmpty() || usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, getString(R.string.msg_fill_all_fields), Toast.LENGTH_SHORT).show();
                return;
            }

            Intent data = new Intent();
            data.putExtra("servicio", servicio);
            data.putExtra("usuario", usuario);
            data.putExtra("password", password);
            setResult(RESULT_OK, data);
            finish();
        });

        // Retroceder sin guardar
        btnRetroceder.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}
