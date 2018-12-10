package com.example.brian.entregableparcial;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityEntregable2 extends AppCompatActivity {

    //Request CODE
    private static final int GET_CONTACT=1;
    private static final int MULT_CODE=2;
    public TextView tcont;
    public TextView tmul;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("CONTACTO",tcont.getText().toString());
        outState.putString("MULT",tmul.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregable2);

        tcont= (TextView) findViewById(R.id.tView_contacto);
        tmul= (TextView) findViewById(R.id.tView_mult);

        if (savedInstanceState != null) {
            String multi = savedInstanceState.getString("MULT");
            String contact = savedInstanceState.getString("CONTACTO");
            if (tcont != null)
                tcont.setText(contact);
            if (tmul !=null)
                tmul.setText(multi);
        }


        Button bcont= (Button) findViewById(R.id.butt_contacto);
        bcont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setType(ContactsContract.Contacts.CONTENT_TYPE);
                startActivityForResult(i,GET_CONTACT);
            }
        });



        Button bM= (Button) findViewById(R.id.butt_mult);
        bM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityEntregable2.this,Activity2Entregable2.class);
                startActivityForResult(i,MULT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode==GET_CONTACT){

            if(resultCode==RESULT_OK){
                String[] projection = {ContactsContract.Contacts.DISPLAY_NAME};
                Cursor c= getContentResolver().query(data.getData(), projection, null,null,null);
                c.moveToNext();
                String dat = data.getData().toString()+"\n";
                dat = dat +" + "+c.getString(0);
                tcont.setText(dat);
            }
            else {
                tcont.setText(R.string.cancel);
            }

        }
        else if (requestCode==MULT_CODE){
            if(resultCode==RESULT_OK){
                int resultado = data.getIntExtra("MULT",0);
                String s = String.valueOf(resultado);
                tmul.setText("Resultado: "+s);
            }
            else {
                tmul.setText(R.string.cancel);
            }
        }
    }
}
