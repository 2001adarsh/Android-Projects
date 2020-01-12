package com.adarsh.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Stack;

public class calci extends AppCompatActivity {

    Button one, two,three, four, five,six,seven,eight,nine,ac,del,eq,dot,rem;
    Button mul, divide, add, sub;
    TextView preview, ans;
    Boolean flag=false;
    String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calci);

        setui();
        final Stack<Float> numbers = new Stack<Float>();
        final Stack<Character> operations = new Stack<Character>();

        //Inputs in string.
        input = "";

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ans.getText().toString().equals(""))
                {
                    preview.setText("");
                    ans.setText("");
                    numbers.clear();
                    operations.clear();
                }
                preview.setText(preview.getText().toString() + "1");
                input = input+ "1";

            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ans.getText().toString().equals(""))
                {
                    preview.setText("");
                    ans.setText("");
                    numbers.clear();
                    operations.clear();
                }
                preview.setText(preview.getText().toString() + "2");
                input = input+ "2";
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ans.getText().toString().equals(""))
                {
                    preview.setText("");
                    ans.setText("");
                    numbers.clear();
                    operations.clear();
                }
                preview.setText(preview.getText().toString() + "3");
                input = input+ "3";
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ans.getText().toString().equals(""))
                {
                    preview.setText("");
                    ans.setText("");
                    numbers.clear();
                    operations.clear();
                }
                preview.setText(preview.getText().toString() + "4");
                input = input +"4";
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ans.getText().toString().equals(""))
                {
                    preview.setText("");
                    ans.setText("");
                    numbers.clear();
                    operations.clear();
                }
                preview.setText(preview.getText().toString() + "5");
                input = input +"5";
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ans.getText().toString().equals(""))
                {
                    preview.setText("");
                    ans.setText("");
                    numbers.clear();
                    operations.clear();
                }
                preview.setText(preview.getText().toString() + "6");
                input = input + "6";
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ans.getText().toString().equals(""))
                {
                    preview.setText("");
                    ans.setText("");
                    numbers.clear();
                    operations.clear();
                }
                preview.setText(preview.getText().toString() + "7");
                input =input+ "7";
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ans.getText().toString().equals(""))
                {
                    preview.setText("");
                    ans.setText("");
                    numbers.clear();
                    operations.clear();
                }
                preview.setText(preview.getText().toString() + "8");
                input = input+ "8";
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ans.getText().toString().equals(""))
                {
                    preview.setText("");
                    ans.setText("");
                    numbers.clear();
                    operations.clear();
                }
                preview.setText(preview.getText().toString() + "9");
                input = input+ "9";
            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ans.getText().toString().equals(""))
                {
                    preview.setText("");
                    ans.setText("");
                    numbers.clear();
                    operations.clear();
                }
                preview.setText(preview.getText().toString() + ".");
                input = input+ ".";
            }
        });



        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    numbers.push(Float.parseFloat(input));
                    preview.setText(preview.getText().toString() + "*");
                    input = "";
                    operations.push('*');
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                preview.setText(preview.getText().toString() + "/");
                numbers.push(Float.parseFloat(input));
                input="";
                operations.push('/');
            }
        });
        rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    preview.setText(preview.getText().toString() + "%");
                    numbers.push(Float.parseFloat(input));
                    input = "";
                    operations.push('%');

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    preview.setText(preview.getText().toString() + "+");
                    numbers.push(Float.parseFloat(input));
                    input = "";
                    operations.push('+');
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    preview.setText(preview.getText().toString() + "-");
                    numbers.push(Float.parseFloat(input));
                    input = "";
                    operations.push('-');
            }
        });

        eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                while (!numbers.isEmpty()){
                    float input1 = numbers.pop();
                    char op = operations.pop();
                    switch (op){
                        case '+' : float x = Float.parseFloat(input) + input1;
                                    input = String.valueOf(x);
                                    break;
                        case '-' : float y = input1 - Float.parseFloat(input) ;
                                    input = String.valueOf(y);
                                    break;
                        case '*' : float z = Float.parseFloat(input) * input1;
                                    input = String.valueOf(z);
                                    break;
                        case '/' : float c =  input1 /Float.parseFloat(input) ;
                                    input = String.valueOf(c);
                                    break;
                        case '%' : float q= input1 % Float.parseFloat(input) ;
                                    input = String.valueOf(q);
                                    break;
                    }
                }
                ans.setText("=" + input);
                input="";
            }
        });


        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preview.setText(""); ans.setText("");
                numbers.clear();
                operations.clear();
            }
        });




    }

    private void setui(){
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three =(Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight= (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        ac= (Button)findViewById(R.id.ac);
        eq = (Button)findViewById(R.id.equals);
        del = (Button) findViewById(R.id.del);
        mul = (Button)findViewById(R.id.multiply);
        divide = (Button)findViewById(R.id.divide);
        add = (Button)findViewById(R.id.plus);
        sub = (Button)findViewById(R.id.minus);
        dot = (Button) findViewById(R.id.dot);
        rem = (Button) findViewById(R.id.rem);

        preview = (TextView) findViewById(R.id.preview);
        ans = (TextView)findViewById(R.id.ans);
    }


}
