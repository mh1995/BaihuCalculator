package com.example.piety.myapplication;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int m=4;
    double p = 0;
    int[] TN = new int[4];
    int[] HN = new int[4];
    int[] HX = new int[4];
    int[] rsTN = new int[4];
    int[] rsHN = new int[4];
    private Button btnExit,btnCalaulate,btnReset;
    private EditText price,tn1,tn2,tn3,tn4,hn1,hn2,hn3,hn4,hx1,hx2,hx3,hx4;
    private TextView rs1,rs2,rs3,rs4,role4;
    private RadioButton three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalaulate =(Button) findViewById(R.id.btn_result);
        price = (EditText)findViewById(R.id.et_price);
        tn1= (EditText)findViewById(R.id.et_tn1);
        tn2= (EditText)findViewById(R.id.et_tn2);
        tn3= (EditText)findViewById(R.id.et_tn3);
        tn4= (EditText)findViewById(R.id.et_tn4);
        hn1= (EditText)findViewById(R.id.et_hn1);
        hn2= (EditText)findViewById(R.id.et_hn2);
        hn3= (EditText)findViewById(R.id.et_hn3);
        hn4= (EditText)findViewById(R.id.et_hn4);
        hx1= (EditText)findViewById(R.id.et_hx1);
        hx2= (EditText)findViewById(R.id.et_hx2);
        hx3= (EditText)findViewById(R.id.et_hx3);
        hx4= (EditText)findViewById(R.id.et_hx4);
        rs1 = (TextView) findViewById(R.id.tv_rs1);
        rs2 = (TextView) findViewById(R.id.tv_rs2);
        rs3 = (TextView) findViewById(R.id.tv_rs3);
        rs4 = (TextView) findViewById(R.id.tv_rs4);
        role4 = (TextView)findViewById(R.id.tv_07);
        three = (RadioButton) findViewById(R.id.rb_three);
        //选择人数
        three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(three.isChecked()==true)
                {
                    m=3;
                    role4.setEnabled(false);
                    hn4.setEnabled(false);
                    tn4.setEnabled(false);
                    hx4.setEnabled(false);
                    rs4.setEnabled(false);
                }
                else{
                    m=4;
                    role4.setEnabled(true);
                    hn4.setEnabled(true);
                    tn4.setEnabled(true);
                    hx4.setEnabled(true);
                    rs4.setEnabled(true);
                }
            }
        });
        //计算
        btnCalaulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                p = Double.parseDouble(price.getText().toString().equals("")?"0":price.getText().toString());
                String t = null;
                t=tn1.getText().toString();
                TN[0]=Integer.parseInt(t.equals("")?"0":t);
                t=tn2.getText().toString();
                TN[1]=Integer.parseInt(t.equals("")?"0":t);
                t=tn3.getText().toString();
                TN[2]=Integer.parseInt(t.equals("")?"0":t);
                t=tn4.getText().toString();
                TN[3]=Integer.parseInt(t.equals("")?"0":t);
                t=hn1.getText().toString();
                HN[0]=Integer.parseInt(t.equals("")?"0":t);
                t=hn2.getText().toString();
                HN[1]=Integer.parseInt(t.equals("")?"0":t);
                t=hn3.getText().toString();
                HN[2]=Integer.parseInt(t.equals("")?"0":t);
                t=hn4.getText().toString();
                HN[3]=Integer.parseInt(t.equals("")?"0":t);
                t=hx1.getText().toString();
                HX[0]=(int)Math.rint(Integer.parseInt(t.equals("")?"0":t)/10.0)*10;
                t=hx2.getText().toString();
                HX[1]=(int)Math.rint(Integer.parseInt(t.equals("")?"0":t)/10.0)*10;
                t=hx3.getText().toString();
                HX[2]=(int)Math.rint(Integer.parseInt(t.equals("")?"0":t)/10.0)*10;
                t=hx4.getText().toString();
                HX[3]=(int)Math.rint(Integer.parseInt(t.equals("")?"0":t)/10.0)*10;
                cTuoNiao();
                cHuoNiao();
                rs1.setText(rsHN[0]+rsTN[0]+"");
                rs2.setText(rsHN[1]+rsTN[1]+"");
                rs3.setText(rsHN[2]+rsTN[2]+"");
                rs4.setText(rsHN[3]+rsTN[3]+"");
            }
        });
        //退出
        btnExit = (Button) findViewById(R.id.btn_out);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
        //清除数据
        btnReset = (Button) findViewById(R.id.btn_remove);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price.setText("0");
                hn1.setText("0");
                hn2.setText("0");
                hn3.setText("0");
                hn4.setText("0");
                tn1.setText("0");
                tn2.setText("0");
                tn3.setText("0");
                tn4.setText("0");
                hx1.setText("0");
                hx2.setText("0");
                hx3.setText("0");
                hx4.setText("0");
                rs1.setText("0");
                rs2.setText("0");
                rs3.setText("0");
                rs4.setText("0");
            }
        });

    }
    public void cTuoNiao(){
        for(int i = 0;i<m;i++) {
            rsTN[i]=0;
            for(int j = 0;j<m;j++){
                rsTN[i]+=(TN[i]-TN[j]);
            }
        }
    }
    public void cHuoNiao(){
        for(int i = 0;i<m;i++) {
            rsHN[i]=0;
            for(int j = 0;j<m;j++){
                rsTN[i]+=((HX[i]-HX[j])*p*(HN[i]+1)*(HN[j]+1));
            }
        }
    }
}
