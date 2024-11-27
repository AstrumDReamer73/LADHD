package com.example.ladhd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ladhd.databinding.AnadirtareaBinding;
import com.example.ladhd.databinding.ListatareasBinding;

public class DataInsert extends AppCompatActivity {
    AnadirtareaBinding binding;

    @Override protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding=AnadirtareaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String type=getIntent().getStringExtra("tipo");

        if(type.equals("modificar")){
            setTitle("modificar");
            binding.nombre.setText(getIntent().getStringExtra("nombre"));
            binding.fechaEntrega.setText(getIntent().getStringExtra("fechaEntrega"));
            binding.estado.setText(getIntent().getStringExtra("estado"));
            binding.descripcion.setText(getIntent().getStringExtra("descripcion"));
            int id=getIntent().getIntExtra("id",0);
            binding.add.setText("nodificar tarea");
            binding.add.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.putExtra("nombre",binding.nombre.getText().toString());
                    intent.putExtra("estado",binding.nombre.getText().toString());
                    intent.putExtra("fecha de entrega",binding.nombre.getText().toString());
                    intent.putExtra("descripcion",binding.nombre.getText().toString());
                    intent.putExtra("id",id);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }else{
            setTitle("añadir");
            binding.add.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.putExtra("nombre",binding.nombre.getText().toString());
                    intent.putExtra("estado",binding.nombre.getText().toString());
                    intent.putExtra("fecha de entrega",binding.nombre.getText().toString());
                    intent.putExtra("descripcion",binding.nombre.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }

        //setTitle("añadir modo");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataInsert.this, MainActivity.class));
    }
}
