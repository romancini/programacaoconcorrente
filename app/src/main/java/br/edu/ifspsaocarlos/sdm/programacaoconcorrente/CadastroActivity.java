package br.edu.ifspsaocarlos.sdm.programacaoconcorrente;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends Activity implements View.OnClickListener{
    private DatabaseHelper helper;
    private EditText etNome, etUsuario, etSenha;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNome = (EditText) findViewById(R.id.et_nome);
        etUsuario = (EditText) findViewById(R.id.et_usuario);
        etSenha = (EditText) findViewById(R.id.et_senha);

        btnCadastrar = (Button)findViewById(R.id.btn_cadastro_usuario);
        btnCadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnCadastrar) {
            btnCadastrar.setEnabled(false);

            final long id = salvarUsuario(
                    etNome.getText().toString(),
                    etUsuario.getText().toString(),
                    etSenha.getText().toString());

            if (id > 0){
                // thread para limpar campos e habilitar botão
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        etNome.setText(null);
                        etUsuario.setText(null);
                        etSenha.setText(null);
                        btnCadastrar.setEnabled(true);
                    }
                });
                // thread para mostrar o ID do usuário criado
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(
                                CadastroActivity.this,
                                "Id do usuario: " + Long.toString(id),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                btnCadastrar.setEnabled(true);
            }
        }
    }

    private long salvarUsuario(String nome, String usuario, String senha){
        long resultado = 0;
        try {
            helper = new DatabaseHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nome_completo", nome);
            values.put("usuario", usuario);
            values.put("senha", senha);
            resultado = db.insert("usuarios", null, values);
        } catch (Exception e) {
            System.out.println("erro: " + e);
        }

        return resultado;
    }
}
