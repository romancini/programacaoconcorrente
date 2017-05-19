package br.edu.ifspsaocarlos.sdm.programacaoconcorrente;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class CarregandoActivity extends Activity {
    private final int ABRIR_ACTIVITY_PRINCIPAL = 0;
    private final int TEMPO_CARREGAMENTO = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carregando);
        Toast.makeText(this, R.string.mensagem_carregando, Toast.LENGTH_SHORT).show();
        Message mensagem = new Message();
        mensagem.what = ABRIR_ACTIVITY_PRINCIPAL;
        handler.sendMessageDelayed(mensagem, TEMPO_CARREGAMENTO);
    }

    private Handler handler = new Handler(){
        public void handleMessage(Message m){
            super.handleMessage(m);
            switch (m.what){
                case ABRIR_ACTIVITY_PRINCIPAL:
                    Intent intent = new Intent(CarregandoActivity.this, PrincipalActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
        }
    };
}
