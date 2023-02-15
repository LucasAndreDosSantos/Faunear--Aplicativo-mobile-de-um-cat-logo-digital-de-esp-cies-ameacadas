package com.example.animaisemextincao;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class AnimaisAdapter extends ArrayAdapter<Animais>{

    private Context contextoAtual;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private Drawable Imagem;

    public AnimaisAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Animais> objects) {
        super(context, resource, objects);

        StorageReference islandRef = storage.getReferenceFromUrl("gs://ataque-a-biodiversidade-d41f4.appspot.com/Sem Imagem/Ponto de interrogação.png");

        islandRef.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Imagem = new BitmapDrawable(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
            }
        });

        contextoAtual = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_animal, parent, false);
        }

        ImageView animalImageView = (ImageView) convertView.findViewById(R.id.animal_imagem);
        TextView nomeTextView = (TextView) convertView.findViewById(R.id.animal_nome);
        TextView nomeCientificoTextView = (TextView) convertView.findViewById(R.id.animal_nome_cientifico);
        TextView localizacaoTextView = (TextView) convertView.findViewById(R.id.animal_localizacao);
        TextView classificacaoTextView = (TextView) convertView.findViewById(R.id.animal_classificacao);
        TextView tendenciaTextView = (TextView) convertView.findViewById(R.id.animal_tendencia);

        Animais animal = getItem(position);
        StorageReference storageReference = storage.getReferenceFromUrl("gs://ataque-a-biodiversidade-d41f4.appspot.com/Imagens/"+ animal.getNOMECIENTIFICO() +".jpg");

        Glide.with(contextoAtual)
             .load(storageReference)
             .error(Imagem)
             .into(animalImageView);

        nomeTextView.setText(animal.getNOME());
        nomeCientificoTextView.setText(animal.getNOMECIENTIFICO());
        localizacaoTextView.setText(animal.getLOCALIZACAO());
        classificacaoTextView.setText(animal.getCLASSIFICACAO());
        tendenciaTextView.setText(animal.getTENDENCIA());

        return convertView;
    }


}
