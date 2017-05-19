package br.edu.ifspsaocarlos.sdm.programacaoconcorrente;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Romancini on 18/05/2017.
 */

public class MensagemHandlerActivity extends Activity implements View.OnClickListener{
    private Button btCliqueAqui;
    private Handler handler;
    private final int MUDAR_TEXTO = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geral);
        TextView tvNomeActivity = (TextView)findViewById(R.id.tv_nome_activity);
        tvNomeActivity.setText("Mensagem -> Handler");
        btCliqueAqui = (Button)findViewById(R.id.bt_clique_aqui);
        btCliqueAqui.setOnClickListener(this);
        handler = new Manipulador();
    }

    @Override
    public void onClick(View view) {
        if (view == btCliqueAqui){
            new Thread() {
                public void run(){
                    super.run();
                    Message mensagem = new Message();
                    mensagem.what = MUDAR_TEXTO;
                    handler.sendMessage(mensagem);
                }
            }.start();
        }
    }

    private class Manipulador extends Handler{
        public void handleMessage(Message m){
            super.handleMessage(m);
            switch (m.what){
                case MUDAR_TEXTO:
                    TextView tvAlvo = (TextView)findViewById(R.id.tv_alvo);
                    tvAlvo.setText(getString(R.string.texto_alterado));
                    break;
                default:
                    break;
            }
        }
    }
}
