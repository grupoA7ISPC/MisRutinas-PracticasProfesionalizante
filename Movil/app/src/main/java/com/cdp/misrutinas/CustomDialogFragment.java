package com.cdp.misrutinas;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.DialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        builder.setView(view)
                .setTitle("Crear:")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                });

        Button buttonOption1 = view.findViewById(R.id.buttonSocio);
        Button buttonOption2 = view.findViewById(R.id.buttonEntrenador);
        Button buttonOption3 = view.findViewById(R.id.buttonClase);

        buttonOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para iniciar la actividad CrearClaseActivity
                Intent intent = new Intent(getContext(), CrearClaseActivity.class);

                // Iniciar la actividad
                startActivity(intent);

                // Opcional: Cerrar el diálogo modal después de iniciar la actividad
                dismiss();
            }
        });

        buttonOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para iniciar la actividad CrearClaseActivity
                Intent intent = new Intent(getContext(), CrearEntrenadorActivity.class);

                // Iniciar la actividad
                startActivity(intent);

                // Opcional: Cerrar el diálogo modal después de iniciar la actividad
                dismiss();
            }
        });

        buttonOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para iniciar la actividad CrearClaseActivity
                Intent intent = new Intent(getContext(), CrearSocioActivity.class);

                // Iniciar la actividad
                startActivity(intent);

                // Opcional: Cerrar el diálogo modal después de iniciar la actividad
                dismiss();
            }
        });

        return builder.create();
    }
}