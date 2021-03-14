package com.example.aula04_03_atividade1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SegundaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private String dadosJSON;
    private ListView listView;
    private List<Estudante> lista;
    private ArrayAdapter<Estudante> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        dadosJSON = getIntent().getStringExtra("dados");
        listView = findViewById(R.id.listViewDados);
        lista = consumirJSON();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    private List<Estudante> consumirJSON() {
        String resultado = "";
        List<Estudante> estudantes = null;

        if (dadosJSON != null) {
            Gson gson  = new Gson();
            Type type = new TypeToken<List<Estudante>>(){}.getType();
            estudantes = gson.fromJson(dadosJSON,type);
            Toast.makeText(getApplicationContext(), estudantes.toString(), Toast.LENGTH_LONG).show();
        }

        return estudantes;
        /*List<Estudante> estudantes = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(dadosJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("estudantes");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Estudante estudante = new Estudante();
                estudante.setNome(object.getString("nome"));
                estudante.setDisciplina(object.getString("disciplina"));
                estudante.setNota(object.getInt("nota"));
                estudantes.add(estudante);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return estudantes;*/
        }

        @Override
        public void onItemClick (AdapterView < ? > parent, View view,int position, long id){
            Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onItemLongClick (AdapterView < ? > parent, View view,int position, long id){
            Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }