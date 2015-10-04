package com.example.diego.winerecommend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ViewMenu extends AppCompatActivity{
    private Button[] buttons;
    private String titulo;
    private String descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] menu = {"Pollo", "Res", "Pasta", "Pizza"};
        final String[] descripciones = {"Vino para pollo blablablabla",
                                    "Vino para res blablablablabl",
                                    "Vino para pasta blablablablabl",
                "Vino para pizza blablablablabl"
        };
        final String[] titulos = {"Tinto", "Blanco", "Dulce", "Rosa"};
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_menu);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.weight = 1;
        p.width = -1;
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        buttons = new Button[menu.length];
        layout.setWeightSum(menu.length);
        Button button;

        View.OnClickListener myOnlyhandler = new View.OnClickListener() {
            public void onClick(View v) {
                for (int i = 0; i < buttons.length; i++) {
                    if (v == buttons[i]) {
                        titulo = titulos[i];
                        descripcion = descripciones[i];
                    }
                }
            }
        };

        for(int i =0; i<menu.length; i++){
            button = new Button(this);
            button.setLayoutParams(p);
            button.setText(menu[i]);
            button.setOnClickListener(myOnlyhandler);
            buttons[i] = button;
            layout.addView(button);
        }
    }

    public String getTitulo(){
        return titulo;
    }
    public String getDescripcion(){
        return descripcion;
    }

    public void gotoRecommendation(){
        Intent intent = new Intent(this, Recommendation.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
