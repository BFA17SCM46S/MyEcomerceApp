package com.example.myecomerceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.simplify.android.sdk.CardEditor;
import com.simplify.android.sdk.CardToken;
import com.simplify.android.sdk.Simplify;

public class PaymentActivity extends AppCompatActivity {
    private static final String TAG = PaymentActivity.class.getSimpleName();
    private Button button;
    private CardEditor cardEditor;
    private Simplify simplify;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        button = findViewById(R.id.submitbutton);
        cardEditor = findViewById(R.id.card_editor);
        simplify = new Simplify();
        simplify.setApiKey("sbpb_Y2ZlMmNmN2UtYjJmNS00NTU3LWE3ZWUtNjhlOGE2ODM3YmQw");

        cardEditor.addOnStateChangedListener(new CardEditor.OnStateChangedListener() {
            @Override
            public void onStateChange(CardEditor cardEditor) {
                button.setEnabled(true);
                
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplify.createCardToken(cardEditor.getCard(), new CardToken.Callback() {
                    @Override
                    public void onSuccess(CardToken cardToken) {
                        Log.e(TAG, " Transaction ID"+cardToken.getId());
                        token = cardToken.getId();

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e(TAG, " Transaction ID"+throwable.getMessage());

                    }
                });
                if (cardEditor.getCard().getNumber().equals("")&&
                        cardEditor.getCard().getCvc().equals("")&&cardEditor.getCard().getAddressZip().equals("")) {

                    Toast.makeText(PaymentActivity.this, "please complete the card imformation", Toast.LENGTH_SHORT).show();


                }else {
                    Intent intent = new Intent(PaymentActivity.this, CompletePayment.class);
                    intent.putExtra("token",token);
                    startActivity(intent);
                    Toast.makeText(PaymentActivity.this, "pay successful", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
