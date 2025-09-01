package com.example.securitypassword;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class RegistrarseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        // Referencias como variables locales
        EditText edtNombreCompleto      = findViewById(R.id.edtNombreCompleto);
        EditText edtCorreo              = findViewById(R.id.edtCorreo);
        EditText edtContrasenaRegistro  = findViewById(R.id.edtContrasenaRegistro);
        EditText edtConfirmarContrasena = findViewById(R.id.edtConfirmarContrasena);
        MaterialButton btnRegistrarse   = findViewById(R.id.btnRegistrarse);
        TextView txtYaTengoCuenta       = findViewById(R.id.txtYaTengoCuenta);

        // Volver al Login
        txtYaTengoCuenta.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        // Registrar usuario
        btnRegistrarse.setOnClickListener(v -> {
            String nombre  = edtNombreCompleto.getText() != null ? edtNombreCompleto.getText().toString().trim() : "";
            String correo  = edtCorreo.getText() != null ? edtCorreo.getText().toString().trim() : "";
            String pass    = edtContrasenaRegistro.getText() != null ? edtContrasenaRegistro.getText().toString().trim() : "";
            String confirm = edtConfirmarContrasena.getText() != null ? edtConfirmarContrasena.getText().toString().trim() : "";

            if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, R.string.msg_fill_all_fields, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!pass.equals(confirm)) {
                Toast.makeText(this, R.string.err_password_mismatch, Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: lógica real de registro (guardar en BD, remoto, etc.)
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
