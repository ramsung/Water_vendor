package beyonity.water_vendor.Activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import beyonity.water_vendor.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
	FirebaseAuth auth;
	PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallBack;
	PhoneAuthProvider.ForceResendingToken mResendingToken;
	String mVerificationId;
	EditText phoneNo;
	Button login;
	String lastChar = " ";

	private static final String TAG = "LoginActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

		if(user != null){
			Intent intent = new Intent(this,MainActivity.class);
			startActivity(intent);
		}else {
			setContentView(R.layout.activity_login);
			setView();
		}

	}

	private void setView() {
		auth = FirebaseAuth.getInstance();
		phoneNo = (EditText) findViewById(R.id.input_no);
		phoneNo.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				int digits = phoneNo.getText().toString().length();
				if (digits > 1)
					lastChar = phoneNo.getText().toString().substring(digits-1);
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				int digits = phoneNo.getText().toString().length();
				Log.d("LENGTH",""+digits);
				if (!lastChar.equals("-")) {
					if (digits == 3 || digits == 7) {
						phoneNo.append("-");
					}
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		login = (Button) findViewById(R.id.btn_login);
		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(!TextUtils.isEmpty(phoneNo.getText().toString())){
					String no = phoneNo.getText().toString().trim().replaceAll("-","");
					if(no.length()<10){
						Toast.makeText(LoginActivity.this, "not valid phone number", Toast.LENGTH_SHORT).show();
						return;
					}
					no = "+91"+no;
					setSignIn(no);
				}
			}
		});

		mcallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
			@Override
			public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
				Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);

				signInWithPhoneAuthCredential(phoneAuthCredential);
			}

			@Override
			public void onVerificationFailed(FirebaseException e) {
				// This callback is invoked in an invalid request for verification is made,
				// for instance if the the phone number format is not valid.
				Log.w(TAG, "onVerificationFailed", e);

				if (e instanceof FirebaseAuthInvalidCredentialsException) {
					// Invalid request
					// ...
				} else if (e instanceof FirebaseTooManyRequestsException) {
					// The SMS quota for the project has been exceeded
					// ...
				}

				// Show a message and update the UI
				// ...
			}

			@Override
			public void onCodeSent(String verificationId,
			                       PhoneAuthProvider.ForceResendingToken token) {
				// The SMS verification code has been sent to the provided phone number, we
				// now need to ask the user to enter the code and then construct a credential
				// by combining the code with a verification ID.
				Log.d(TAG, "onCodeSent:" + verificationId);

				// Save verification ID and resending token so we can use them later

				mVerificationId = verificationId;
				mResendingToken= token;

				// ...
			}
		};

	}

	private void setSignIn(String phoneNumber) {
		PhoneAuthProvider.getInstance().verifyPhoneNumber(
				phoneNumber,        // Phone number to verify
				60,                 // Timeout duration
				TimeUnit.SECONDS,   // Unit of timeout
				this,               // Activity (for callback binding)
				mcallBack);        // OnVerificationStateChangedCallbacks

	}

	private void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {
		auth.signInWithCredential(credential)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							// Sign in success, update UI with the signed-in user's information
							Log.d(TAG, "signInWithCredential:success");

							FirebaseUser user = task.getResult().getUser();
							Toast.makeText(LoginActivity.this, "successfully signed in", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(LoginActivity.this , MainActivity.class);
							startActivity(intent);
							// ...
						} else {
							// Sign in failed, display a message and update the UI
							Log.w(TAG, "signInWithCredential:failure", task.getException());
							if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
								// The verification code entered was invalid
								Toast.makeText(LoginActivity.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
							}
						}
					}
				});
	}
}
