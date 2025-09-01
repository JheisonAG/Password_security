package com.example.securitypassword;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referencias como variables locales
        final EditText edtUsuario       = findViewById(R.id.edtUsuario);
        final EditText edtContrasena    = findViewById(R.id.edtContrasena);
        MaterialButton btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        TextView txtRegistrarse         = findViewById(R.id.txtRegistrarse);
        TextView txtOlvideContrasena    = findViewById(R.id.txtOlvideContrasena);

        // Ir a Registrarse
        txtRegistrarse.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegistrarseActivity.class))
        );

        // Olvidé mi contraseña
        txtOlvideContrasena.setOnClickListener(v ->
                Toast.makeText(this, getString(R.string.toast_forgot), Toast.LENGTH_SHORT).show()
        );

        // Iniciar sesión
        btnIniciarSesion.setOnClickListener(v -> {
            String usuario = edtUsuario.getText().toString().trim();
            String pass    = edtContrasena.getText().toString().trim();

            if (TextUtils.isEmpty(usuario)) {
                edtUsuario.setError(getString(R.string.err_username_required));
                edtUsuario.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(pass)) {
                edtContrasena.setError(getString(R.string.err_password_required));
                edtContrasena.requestFocus();
                return;
            }

            // Aquí puedes implementar la lógica de inicio de sesión
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        });
    }
}
