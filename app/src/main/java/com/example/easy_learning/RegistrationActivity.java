package com.example.easy_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.TextUtilsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private TextInputEditText emailEdt,pwdEdt,cnfPwdEdt;
    private Button registerBtn;
    private ProgressBar loadingPB;
    private FirebaseAuth mAuth;
    private TextView loginTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        emailEdt=findViewById(R.id.idEditEmail);
        pwdEdt=findViewById(R.id.idEditPwd);
        cnfPwdEdt=findViewById(R.id.idEditCnfPwd);
        registerBtn=findViewById(R.id.idBtnRegister);
        loadingPB=findViewById(R.id.idPBLoading);
        loginTV=findViewById(R.id.idTVLogin);
        mAuth=FirebaseAuth.getInstance();
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadingPB.setVisibility(View.VISIBLE);
                String email= emailEdt.getText().toString();
                 String pwd=pwdEdt.getText().toString();
                String  cnfPwd=cnfPwdEdt.getText().toString();
                if(!pwd.equals(cnfPwd)){
                    Toast.makeText(RegistrationActivity.this,"Please check both password",Toast.LENGTH_SHORT).show();

            }else if(TextUtils.isEmpty(email) && TextUtils.isEmpty(pwd) &&  TextUtils.isEmpty(cnfPwd)){
                Toast.makeText(RegistrationActivity.this,"Please add Your Credentials...",Toast.LENGTH_SHORT).show();
            }else  {
                mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            loadingPB.setVisibility(View.GONE);
                            Toast.makeText(RegistrationActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(RegistrationActivity.this,LoginActivity.class);
                            startActivity(i);
                            finish();
                        } else{
                            loadingPB.setVisibility(View.GONE);
                            Toast.makeText(RegistrationActivity.this,"Fail to register user..",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        }
    });
}
}