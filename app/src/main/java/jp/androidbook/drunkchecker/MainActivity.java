package jp.androidbook.drunkchecker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner spinner=(Spinner)this.findViewById(R.id.spinner);


        ((Button)findViewById(R.id.button7)).setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        new AlertDialog.Builder(MainActivity.this)
                                .setMessage(getString(R.string.meyasu))
                                .setPositiveButton(
                                        "閉じる",
                                        new DialogInterface.OnClickListener(){
                                            @Override
                                            public void onClick(DialogInterface dialog,int which){

                                            }
                                        })

                    .show();
                    }
                });

        ((Button)findViewById(R.id.button)).setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        EditText edit=(EditText)findViewById(R.id.editText);
                        EditText edit2=(EditText)findViewById(R.id.editText4);
                        EditText edit3=(EditText)findViewById(R.id.editText5);
                        try {

                            String str=(String)spinner.getSelectedItem();
                            Editable getText = edit.getText();
                            Editable getText2=edit2.getText();
                            Editable getText3=edit3.getText();
                            int int_text = Integer.parseInt(getText.toString());
                            int int_text2 = Integer.parseInt(getText2.toString());
                            int int_text3 = Integer.parseInt(getText3.toString());
                            Intent intent = new Intent(MainActivity.this, Result.class);
                            intent.putExtra("DATA1",int_text);
                            intent.putExtra("DATA2",str);
                            intent.putExtra("DATA3",int_text2);
                            intent.putExtra("DATA4",int_text3);
                            startActivity(intent);
                       }
                        catch(Exception e){
                            TextView error=(TextView)findViewById(R.id.textView4);
                            error.setText(getString(R.string.error));
                        }

                    }
                }
        );
    }
}
