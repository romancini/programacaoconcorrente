package br.edu.ifspsaocarlos.sdm.programacaoconcorrente;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Romancini on 18/05/2017.
 */

public class RunOnUIThreadHandlerActivity extends Activity implements View.OnClickListener{
    private Button btCliqueAqui;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geral);
        TextView tvNomeActivity = (TextView)findViewById(R.id.tv_nome_activity);
        tvNomeActivity.setText("RunOnUIThread");
        btCliqueAqui = (Button)findViewById(R.id.bt_clique_aqui);
        btCliqueAqui.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btCliqueAqui){
            new Thread(){
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
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
