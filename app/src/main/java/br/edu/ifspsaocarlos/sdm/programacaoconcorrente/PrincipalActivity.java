package br.edu.ifspsaocarlos.sdm.programacaoconcorrente;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Romancini on 18/05/2017.
 */

public class PrincipalActivity extends ListActivity {
    private final String[] activities = new String[]{"Mensagem -> Handler", "Runnable -> Handler",
            "runOnUIThread", "Sair"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activities);
        this.setListAdapter(adaptador);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = null;
        switch (getListAdapter().getItem(position).toString()){
            case "Mensagem -> Handler":
                intent = new Intent(this, MensagemHandlerActivity.class);
                startActivity(intent);
                break;
            case "Runnable -> Handler":
                intent = new Intent(this, RunnableHandlerActivity.class);
                startActivity(intent);
                break;
            case "runOnUIThread":
                intent = new Intent(this, RunOnUIThreadHandlerActivity.class);
                startActivity(intent);
                break;
            case "Sair":
            default:
                finish();
                break;
        }
    }
}
