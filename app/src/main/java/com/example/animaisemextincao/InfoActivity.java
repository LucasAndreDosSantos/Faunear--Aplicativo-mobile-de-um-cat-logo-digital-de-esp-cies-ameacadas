package com.example.animaisemextincao;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class InfoActivity extends AppCompatActivity {

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private  StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            this.setTitle(bundle.get("titulo").toString() + " - " + bundle.get("nome").toString());
            carregaDados(bundle);
        }else{
            this.finish();
        }

        botaoVoltar(bundle);
    }

    private void botaoVoltar(Bundle bundle){
        Button bt_voltar = (Button)findViewById(R.id.voltar_botao);

        bt_voltar .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, AnimaisActivity.class);

                if(bundle.get("tipOPesQuiSa").toString().equals("PesquisaBasica")){
                    intent.putExtra("valor",bundle.get("pesquisaAnimal").toString());
                }else if(bundle.get("tipOPesQuiSa").toString().equals("SelecionarCategoria")) {
                    intent.putExtra("categoria",bundle.get("pesquisaAnimal").toString());
                }else{
                    intent.putExtra("nome",bundle.get("pesquisaNome").toString());
                    intent.putExtra("nomeCientifico",bundle.get("pesquisaNomeCientifico").toString());
                    intent.putExtra("vulneravel",bundle.get("pesquisaVulneravel").toString());
                    intent.putExtra("emPerigo",bundle.get("pesquisaEmPerigo").toString());
                    intent.putExtra("criticamentePerigo",bundle.get("pesquisaCriticamentePerigo").toString());
                    intent.putExtra("possivelmenteExtinta",bundle.get("pesquisaPossivelmenteExtinta").toString());
                    intent.putExtra("MinasGerais",bundle.get("pesquisaMinasGerais").toString());
                    intent.putExtra("RioJaneiro",bundle.get("pesquisaRioJaneiro").toString());
                    intent.putExtra("SaoPaulo",bundle.get("pesquisaSaoPaulo").toString());
                    intent.putExtra("EspiritoSanto",bundle.get("pesquisaEspiritoSanto").toString());
                }
                intent.putExtra("tipo",bundle.get("tipOPesQuiSa").toString());
                startActivity(intent);
            }
        });
    }



    private void carregaDados(Bundle bundle) {

        ImageView animalImageView = (ImageView) this.findViewById(R.id.animal_imagem);
        TextView nomeTextView = InfoActivity.this.findViewById(R.id.animal_nome);
        TextView ameacaTextView = InfoActivity.this.findViewById(R.id.animal_ameaca);
        TextView classificacaoTextView = InfoActivity.this.findViewById(R.id.animal_classificacao);
        TextView descricaoTextView = InfoActivity.this.findViewById(R.id.animal_descricao);

        storageReference = storage.getReferenceFromUrl("gs://ataque-a-biodiversidade-d41f4.appspot.com/Imagens/"+ bundle.get("nomeCieTifIco").toString() +".jpg");;
        Glide.with(this)
                .load(storageReference)
                .into(animalImageView);

        nomeTextView.setText(bundle.get("nome").toString() + "(" + bundle.get("nomeCieTifIco").toString() + ")");
        ameacaTextView.setText(bundle.get("ameAca").toString());
        classificacaoTextView.setText(bundle.get("claSsiFicaCao").toString());
        descricaoTextView .setText(bundle.get("descRicaO").toString());
    }
}

