package jp.androidbook.drunkchecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static jp.androidbook.drunkchecker.R.id.textView7;
import static jp.androidbook.drunkchecker.R.id.textView5;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent=getIntent();
        int data1=intent.getIntExtra("DATA1",0);             //体重
        String data2=intent.getStringExtra("DATA2");         //体質
        int data3=intent.getIntExtra("DATA3",0);             //度数
        int data4=intent.getIntExtra("DATA4",0);             //飲酒量
        double taisitu=0;

        switch(data2){
            case "普通":
                taisitu=0.11;
                break;
            case "弱い":
                taisitu=0.1;
                break;
            case "強い":
                taisitu=0.15;
                break;
        }

        double drunk=data4*0.8*data3*0.01/(taisitu*data1);
        drunk *=3600;

        //元データをBigDecimal型にする
        BigDecimal bd = new BigDecimal(drunk);

        //切り捨て
        BigDecimal bd1 = bd.setScale(0, BigDecimal.ROUND_DOWN);
        int num1 = bd1.intValue();//酔う秒

        ((TextView)this.findViewById(textView5)).setTextSize(40);
        ((TextView)this.findViewById(textView5)).setText("約"+String.valueOf(num1/3600)+"時間"+String.valueOf((num1%3600)/60)+"分");
        double koki=(double)data4*(double)data3/(833*(double)data1)*5;
        BigDecimal bd2=new BigDecimal(koki);
        BigDecimal bd3=bd2.setScale(2,BigDecimal.ROUND_HALF_UP);
        ((TextView)this.findViewById(textView7)).setTextSize(40);
        ((TextView)this.findViewById(R.id.textView7)).setText(String.valueOf("約"+bd3+"mg/l"));
        Calendar cale=Calendar.getInstance();
        SimpleDateFormat sdf1=new SimpleDateFormat("H時mm分頃");
        cale.add(Calendar.HOUR,num1/3600);
        cale.add(Calendar.MINUTE,(num1%3600)/60);
        ((TextView)this.findViewById(R.id.textView8)).setText(sdf1.format(cale.getTime()));
}
}

