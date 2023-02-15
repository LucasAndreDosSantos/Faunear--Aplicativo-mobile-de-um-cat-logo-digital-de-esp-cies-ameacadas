package com.example.animaisemextincao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.List;

public class AnimaisActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private ArrayList<Animais> animaisArrayList;
    private ListView animaisListView;
    private AnimaisAdapter adapter;
    private Source source;

    private String titulo = "";
    private String pesquisaAnimal = "";
    private String tipoPesquisa = "";
    private String pesquisaNome = "";
    private String pesquisaNomeCientifico = "";
    private String pesquisaVulneravel = "";
    private String pesquisaEmPerigo = "";
    private String pesquisaCriticamentePerigo = "";
    private String pesquisaPossivelmenteExtinta = "";
    private String pesquisaMinasGerais = "";
    private String pesquisaRioJaneiro = "";
    private String pesquisaSaoPaulo = "";
    private String pesquisaEspiritoSanto = "";
    private String tipoPesquisaSelecionada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animais);

        animaisListView = findViewById(R.id.animais_lista);
        animaisArrayList = new ArrayList<Animais>();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        firebaseFirestore = FirebaseFirestore.getInstance();
        source = Source.DEFAULT;

        tipoPesquisaSelecionada = bundle.get("tipo").toString();

        defineTipoPesquisa(bundle);

        selecionarAnimal();
        botaoVoltar();

    }

    private void defineTipoPesquisa(Bundle bundle){
        if(tipoPesquisaSelecionada.equals("SelecionarCategoria")){

            pesquisaAnimal = bundle.get("categoria").toString();
            tipoPesquisa = "CATEGORIA";

            this.setTitle(pesquisaAnimal);
            titulo = pesquisaAnimal;
            categoriaSelecionada();

        }else if(tipoPesquisaSelecionada.equals("PesquisaBasica")) {
            pesquisaAnimal= bundle.get("valor").toString();

            this.setTitle(pesquisaAnimal);
            titulo = pesquisaAnimal;

            pesquisaBasica();

        }else{
            pesquisaNome = bundle.get("nome").toString();
            pesquisaNomeCientifico = bundle.get("nomeCientifico").toString();
            pesquisaVulneravel = bundle.get("vulneravel").toString();
            pesquisaEmPerigo = bundle.get("emPerigo").toString();
            pesquisaCriticamentePerigo = bundle.get("criticamentePerigo").toString();
            pesquisaPossivelmenteExtinta = bundle.get("possivelmenteExtinta").toString();
            pesquisaMinasGerais = bundle.get("MinasGerais").toString();
            pesquisaRioJaneiro = bundle.get("RioJaneiro").toString();
            pesquisaSaoPaulo = bundle.get("SaoPaulo").toString();
            pesquisaEspiritoSanto = bundle.get("EspiritoSanto").toString();

            tituloPesquisaAvancada();

            pesquisaAvancada();

        }
    }

    private void tituloPesquisaAvancada(){
        ArrayList<String> opcoes = new ArrayList<String>();

        opcoes.add(pesquisaNome);
        opcoes.add(pesquisaNomeCientifico);
        opcoes.add(pesquisaEspiritoSanto);
        opcoes.add(pesquisaMinasGerais);
        opcoes.add(pesquisaRioJaneiro);
        opcoes.add(pesquisaSaoPaulo);
        opcoes.add(pesquisaCriticamentePerigo);
        opcoes.add(pesquisaEmPerigo);
        opcoes.add(pesquisaPossivelmenteExtinta);
        opcoes.add(pesquisaVulneravel);
        opcoes.add("Sem filtro");

        boolean tituloOficial = false;
        int posicao = 0;

        while(!tituloOficial){
            if (!opcoes.get(posicao).equals("")) {
                this.setTitle(opcoes.get(posicao));
                titulo = opcoes.get(posicao);
                tituloOficial = true;
            }
            posicao++;
        }
    }

    private void categoriaSelecionada(){

        firebaseFirestore.collection("Animais")
                .whereEqualTo(tipoPesquisa, pesquisaAnimal)
                .get(source)
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> lista = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot document : lista) {
                                Animais animais = document.toObject(Animais.class);
                                animaisArrayList.add(animais);
                            }

                            adapter = new AnimaisAdapter(AnimaisActivity.this, 0, animaisArrayList);
                            animaisListView.setAdapter(adapter);
                        } else {
                            Toast.makeText(AnimaisActivity.this, "Falha ao acessar os dados", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void pesquisaBasica(){
        firebaseFirestore.collection("Animais")
                .get(source)
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> lista = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot document : lista) {
                                Animais animais = document.toObject(Animais.class);
                                if(animais.getNOME().toLowerCase().contains(pesquisaAnimal.toLowerCase()) || animais.getNOMECIENTIFICO().toLowerCase().contains(pesquisaAnimal.toLowerCase()) || animais.getCLASSIFICACAO().toLowerCase().contains(pesquisaAnimal.toLowerCase()) || animais.getLOCALIZACAO().toLowerCase().contains(pesquisaAnimal.toLowerCase()))  {
                                    animaisArrayList.add(animais);
                                }
                            }

                            adapter = new AnimaisAdapter(AnimaisActivity.this, 0, animaisArrayList);
                            animaisListView.setAdapter(adapter);
                        } else {
                            Toast.makeText(AnimaisActivity.this, "Falha ao acessar os dados", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void pesquisaAvancada(){
        firebaseFirestore.collection("Animais")
                .get(source)
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> lista = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot document : lista) {
                                Animais animais = document.toObject(Animais.class);
                                if(animais.getNOME().toLowerCase().contains(pesquisaNome.toLowerCase())){
                                    if (animais.getNOMECIENTIFICO().toLowerCase().contains(pesquisaNomeCientifico.toLowerCase()))
                                        if(animais.getCLASSIFICACAO().contains(pesquisaVulneravel) && animais.getCLASSIFICACAO().contains(pesquisaEmPerigo) && animais.getCLASSIFICACAO().contains(pesquisaCriticamentePerigo) && animais.getCLASSIFICACAO().contains(pesquisaPossivelmenteExtinta))
                                            if(animais.getLOCALIZACAO().toLowerCase().contains(pesquisaMinasGerais.toLowerCase()) && animais.getLOCALIZACAO().toLowerCase().contains(pesquisaRioJaneiro.toLowerCase()) && animais.getLOCALIZACAO().toLowerCase().contains(pesquisaSaoPaulo.toLowerCase()) && animais.getLOCALIZACAO().toLowerCase().contains(pesquisaEspiritoSanto.toLowerCase()))
                                                animaisArrayList.add(animais);
                                }
                            }

                            adapter = new AnimaisAdapter(AnimaisActivity.this, 0, animaisArrayList);
                            animaisListView.setAdapter(adapter);
                        } else {
                            Toast.makeText(AnimaisActivity.this, "Falha ao acessar os dados", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void selecionarAnimal(){
        animaisListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animais animal = adapter.getItem(position);

                Bundle bundle = new Bundle();
                bundle.putString("titulo", titulo);
                bundle.putString("nome", animal.getNOME());
                bundle.putString("nomeCieTifIco", animal.getNOMECIENTIFICO());
                bundle.putString("ameAca" , animal.getAMEACAS());
                bundle.putString("claSsiFicaCao",animal.getCLASSIFICACAO());
                bundle.putString("descRicaO",animal.getDESCRICAO());
                bundle.putString("catEgorIa",animal.getCATEGORIA());

                bundle.putString("tipOPesQuiSa",tipoPesquisaSelecionada);

                if(tipoPesquisaSelecionada.equals("SelecionarCategoria")){
                    bundle.putString("pesquisaAnimal", pesquisaAnimal);
                    bundle.putString("tipoPesquisa", tipoPesquisa);
                }else if(tipoPesquisaSelecionada.equals("PesquisaBasica")) {
                    bundle.putString("pesquisaAnimal", pesquisaAnimal);
                }else{
                    bundle.putString("pesquisaNome", pesquisaNome);
                    bundle.putString("pesquisaNomeCientifico", pesquisaNomeCientifico);
                    bundle.putString("pesquisaVulneravel", pesquisaVulneravel);
                    bundle.putString("pesquisaEmPerigo", pesquisaEmPerigo);
                    bundle.putString("pesquisaCriticamentePerigo", pesquisaCriticamentePerigo);
                    bundle.putString("pesquisaPossivelmenteExtinta", pesquisaPossivelmenteExtinta);
                    bundle.putString("pesquisaMinasGerais", pesquisaMinasGerais);
                    bundle.putString("pesquisaRioJaneiro", pesquisaRioJaneiro);
                    bundle.putString("pesquisaSaoPaulo", pesquisaSaoPaulo);
                    bundle.putString("pesquisaEspiritoSanto", pesquisaEspiritoSanto);
                }

                Intent intent = new Intent(AnimaisActivity.this, InfoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void botaoVoltar(){
        Button bt_voltar = (Button)findViewById(R.id.voltar_botao);

        bt_voltar .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimaisActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}