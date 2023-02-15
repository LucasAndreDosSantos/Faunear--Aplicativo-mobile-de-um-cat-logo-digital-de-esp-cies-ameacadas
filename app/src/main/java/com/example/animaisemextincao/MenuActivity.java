package com.example.animaisemextincao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

public class MenuActivity extends AppCompatActivity {

    private Button bt_mamifero;
    private Button bt_anfibio;
    private Button bt_ave;
    private Button bt_peixe;
    private Button bt_reptil;
    private Button bt_invertebrado;
    private Button bt_filtros;
    private SearchView pesquisa_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.setTitle("Menu");

        inicializaElementos();

        pesquisarAnimal();
        selecionarCategoria();
    }

    private void inicializaElementos(){
        bt_mamifero = (Button)findViewById(R.id.mamiferos_botao);
        bt_anfibio = (Button)findViewById(R.id.anfibios_botao);
        bt_ave = (Button)findViewById(R.id.aves_botao);
        bt_peixe = (Button)findViewById(R.id.peixes_botao);
        bt_reptil = (Button)findViewById(R.id.repteis_botao);
        bt_invertebrado = (Button)findViewById(R.id.invertebrados_botao);
        bt_filtros = (Button)findViewById(R.id.filtros_botao);
        pesquisa_view = (SearchView)findViewById(R.id.pesquisar_botao);
    }

    private void selecionarCategoria(){
        bt_mamifero .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AnimaisActivity.class);
                intent.putExtra("categoria","Mamífero");
                intent.putExtra("tipo","SelecionarCategoria");
                startActivity(intent);
            }
        });

        bt_anfibio .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AnimaisActivity.class);
                intent.putExtra("categoria","Anfíbio");
                intent.putExtra("tipo","SelecionarCategoria");
                startActivity(intent);
            }
        });

        bt_ave .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AnimaisActivity.class);
                intent.putExtra("categoria","Ave");
                intent.putExtra("tipo","SelecionarCategoria");
                startActivity(intent);
            }
        });

        bt_reptil .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AnimaisActivity.class);
                intent.putExtra("categoria","Réptil");
                intent.putExtra("tipo","SelecionarCategoria");
                startActivity(intent);
            }
        });

        bt_peixe .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AnimaisActivity.class);
                intent.putExtra("categoria","Peixe");
                intent.putExtra("tipo","SelecionarCategoria");
                startActivity(intent);
            }
        });

        bt_invertebrado .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AnimaisActivity.class);
                intent.putExtra("categoria","Invertebrado");
                intent.putExtra("tipo","SelecionarCategoria");
                startActivity(intent);
            }
        });
    }

    private void pesquisarAnimal(){
        pesquisa_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                pesquisa_view.setQuery("",false);
                Intent intent = new Intent(MenuActivity.this, AnimaisActivity.class);
                intent.putExtra("valor",query);
                intent.putExtra("tipo","PesquisaBasica");
                startActivity(intent);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText){ return false; }
        });

        bt_filtros .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, FiltrosActivity.class);
                startActivity(intent);
            }
        });
    }
}