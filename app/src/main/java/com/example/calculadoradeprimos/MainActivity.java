package com.example.calculadoradeprimos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        TextView txt;
        txt = findViewById(R.id.txtGerado);
        savedInstanceState.putString("gerado", txt.getText().toString());
        txt = findViewById(R.id.txtParidade);
        savedInstanceState.putString("paridade", txt.getText().toString());
        txt = findViewById(R.id.txtPrimo);
        savedInstanceState.putString("primo", txt.getText().toString());
        txt = findViewById(R.id.txtDivisores);
        savedInstanceState.putString("divisores", txt.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView txt;
        String s;
        s = savedInstanceState.getString("gerado");
        txt = findViewById(R.id.txtGerado);
        txt.setText(s);
        s = savedInstanceState.getString("paridade");
        txt = findViewById(R.id.txtParidade);
        txt.setText(s);
        s = savedInstanceState.getString("primo");
        txt = findViewById(R.id.txtPrimo);
        txt.setText(s);
        s = savedInstanceState.getString("divisores");
        txt = findViewById(R.id.txtDivisores);
        txt.setText(s);
    }

    public void btnGerarOnClick(View view) {
        Random gerador = new Random();
        int valor = gerador.nextInt(100000);
        String s = String.format("%d", valor);
        TextView txtGerado = findViewById(R.id.txtGerado);
        txtGerado.setText(s);
    }

    public void btnTransferirOnClick(View view) {
        TextView txtGerado = findViewById(R.id.txtGerado);
        String s = txtGerado.getText().toString();
        if (s != "") {
            EditText edtTransfere = findViewById(R.id.edtTransfere);
            edtTransfere.setText(txtGerado.getText().toString());
        }
    }

    public void btnParidadeOnClick(View view) {
        TextView txtParidade = findViewById(R.id.txtParidade);
        EditText edtTransfere = findViewById(R.id.edtTransfere);
        String s = edtTransfere.getText().toString();
        try {
            int valor = Integer.parseInt(s);
            if (valor % 2 == 0) txtParidade.setText(String.format("%d é Par", valor));
            else txtParidade.setText(String.format("%d é Ímpar", valor));
        } catch (Exception e) {
            Toast.makeText(this, "É necessário fornecer um número inteiro.", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnPrimoOnClick(View view) {
        TextView txtPrimo = findViewById(R.id.txtPrimo);
        EditText edtTransfere = findViewById(R.id.edtTransfere);
        String s = edtTransfere.getText().toString();
        try {
            int valor = Integer.parseInt(s);
            if (ePrimo(valor)) txtPrimo.setText(String.format("%d é Primo", valor));
            else txtPrimo.setText(String.format("%d não é Primo", valor));
        } catch (Exception e) {
            Toast.makeText(this, "É necessário fornecer um número inteiro.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean ePrimo (int pN) {
        int i;
        int r = 1;
        double raiz;
        if (pN == 2) return(true);
        else if (pN % 2 == 0) return(false);
        else {
            raiz = Math.sqrt(pN);
            i = 3;
            while (i <= raiz && r != 0) {
                r = pN % i; i += 2;
            } return(r != 0);
        }
    }

    public void txtDivisoresOnClick(View view) {
        EditText edtTransfere = findViewById(R.id.edtTransfere);
        String s = edtTransfere.getText().toString();
        try {
            int valor = Integer.parseInt(s);
            s = produzDivisores(valor);
            TextView txtDivisores = findViewById(R.id.txtDivisores);
            txtDivisores.setText(s);
            txtDivisores.setMovementMethod(new ScrollingMovementMethod());
            txtDivisores.scrollTo(0, 0);
        } catch (Exception e) {
            Toast.makeText(this, "É necessário fornecer um número inteiro.", Toast.LENGTH_SHORT).show();
        }
    }

    private String produzDivisores(int valor) {
        int i;
        double fim;
        String s = "";
        fim = valor / 2;
        i = 2;
        while (i <= fim) {
            if (valor % i == 0) s = s + i + "\n";
            i++;
        } return(s);
    }

    public void btnLimpaTudo(View view) {
        TextView v;
        v = findViewById(R.id.txtGerado);
        v.setText("");
        v = findViewById(R.id.txtParidade);
        v.setText("");
        v = findViewById(R.id.txtPrimo);
        v.setText("");
        v = findViewById(R.id.txtDivisores);
        v.setText("");
        v = findViewById(R.id.edtTransfere);
        v.setText("");
    }
}