package br.edu.ifspsaocarlos.sdm.programacaoconcorrente;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Romancini on 18/05/2017.
 */

public class RunnableHandlerActivity extends Activity implements View.OnClickListener{
    public Button btnCliqueAqui;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geral);
        TextView tvNomeActivity = (TextView)findViewById(R.id.tv_nome_activity);
        tvNomeActivity.setText("Runnable -> Handler");
        btnCliqueAqui = (Button)findViewById(R.id.bt_clique_aqui);
        btnCliqueAqui.setOnClickListener(this);
        handler = new Handler();
    }

    @Override
    public void onClick(View view) {
        if (view == btnCliqueAqui){
            new Thread(){
                public void run(){
                    handler.post(new Runnable() {
                        public void run() {
                            TextView tvAlvo = (TextView)findViewById(R.id.tv_alvo);
                            tvAlvo.setText(getString(R.string.texto_alterado));
                        }
                    });
                }
            }.start();
        }
    }
}
