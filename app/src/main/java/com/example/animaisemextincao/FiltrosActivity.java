package com.example.animaisemextincao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.material.textfield.TextInputEditText;


public class FiltrosActivity extends Activity {

    private Button bt_voltar;
    private TextInputEditText ti_nome;
    private TextInputEditText ti_nomeCientifico;
    private CheckBox cb_vulneravel;
    private CheckBox cb_emPerigo;
    private CheckBox cb_criticamentePerigo;
    private CheckBox cb_possivelmenteExtinta;
    private CheckBox cb_ES;
    private CheckBox cb_MG;
    private CheckBox cb_RJ;
    private CheckBox cb_SP;
    private Button bt_confirmar;

    private String vulneravel = "";
    private String emPerigo = "";
    private String criticamentePerigo = "";
    private String possivelmenteExtinta = "";
    private String EspiritoSanto = "";
    private String MinasGerais = "";
    private String RioJaneiro = "";
    private String SaoPaulo = "";

    String nome = "";
    String nomeCientifico = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);
        this.setTitle("Filtros");

        inicializaElementos();

        botaoVoltar();
        botaoConfirmar();
    }

    private void inicializaElementos(){
        bt_voltar = (Button)findViewById(R.id.voltar_botao);
        ti_nome = (TextInputEditText)findViewById(R.id.input_nome);
        ti_nomeCientifico = (TextInputEditText)findViewById(R.id.nomeCientifico);
        cb_vulneravel = (CheckBox) findViewById(R.id.vulneravel);
        cb_emPerigo = (CheckBox) findViewById(R.id.emPerigo);
        cb_criticamentePerigo = (CheckBox) findViewById(R.id.criticamenteEmPerigo);
        cb_possivelmenteExtinta = (CheckBox) findViewById(R.id.possivelmenteExtinta);
        cb_ES = (CheckBox) findViewById(R.id.checkBoxES);
        cb_MG = (CheckBox) findViewById(R.id.checkBoxMG);
        cb_RJ = (CheckBox) findViewById(R.id.checkBoxRJ);
        cb_SP = (CheckBox) findViewById(R.id.checkBoxSP);
        bt_confirmar = (Button)findViewById(R.id.confirma_botao);
    }


    private void botaoConfirmar(){
        bt_confirmar .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verificaValores();

                Intent intent = new Intent(FiltrosActivity.this, AnimaisActivity.class);
                intent.putExtra("nome",nome);
                intent.putExtra("nomeCientifico",nomeCientifico);
                intent.putExtra("vulneravel",vulneravel);
                intent.putExtra("emPerigo",emPerigo);
                intent.putExtra("criticamentePerigo",criticamentePerigo);
                intent.putExtra("possivelmenteExtinta",possivelmenteExtinta);
                intent.putExtra("MinasGerais",MinasGerais);
                intent.putExtra("RioJaneiro",RioJaneiro);
                intent.putExtra("SaoPaulo",SaoPaulo);
                intent.putExtra("EspiritoSanto",EspiritoSanto);
                intent.putExtra("tipo","PesquisaAvancada");
                startActivity(intent);
            }
        });
    }

    private void verificaValores(){
        if (cb_vulneravel.isChecked()) vulneravel = "Vulnerável";
        if (cb_emPerigo.isChecked()) emPerigo = "Em perigo";
        if (cb_criticamentePerigo.isChecked()) criticamentePerigo = "Criticamente em perigo";
        if (cb_possivelmenteExtinta.isChecked()) possivelmenteExtinta = "Possivelmente Extinta";
        if (cb_MG.isChecked()) MinasGerais = "Minas Gerais";
        if (cb_RJ.isChecked()) RioJaneiro = "Rio de Janeiro";
        if (cb_SP.isChecked()) SaoPaulo = "São Paulo";
        if (cb_ES.isChecked()) EspiritoSanto = "Espírito Santo";

        nome = ti_nome.getText().toString();
        nomeCientifico = ti_nomeCientifico.getText().toString();
    }


    private void botaoVoltar(){
        bt_voltar .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FiltrosActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
