package beyonity.water_vendor.Activitys;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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
	EditText phoneNo, VerificationCode;
	TextView phoneText;
	Button login, resend;
	String lastChar = " ";


	private static final String TAG = "LoginActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		/*if(user!=null){
			FirebaseAuth.getInstance().signOut();
		}
		getSupportActionBar().setTitle("Vendor Login/Signup");
		if (user != null) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		} else {

			setView();
		}*/
		setContentView(R.layout.activity_login);
		setView();

	}

	private void setView() {
		auth = FirebaseAuth.getInstance();
		phoneText = (TextView) findViewById(R.id.phoneText);
		phoneNo = (EditText) findViewById(R.id.input_no);
		resend = (Button) findViewById(R.id.resend);
		resend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (resend.getText().equals("resend")) {


					resendVerificationCode(phoneNo.getText().toString().trim(), mResendingToken);
				}
			}
		});
		VerificationCode = (EditText) findViewById(R.id.verficationcode);
		VerificationCode.setEnabled(false);

		phoneNo.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				int digits = phoneNo.getText().toString().length();
				if (digits > 1)
					lastChar = phoneNo.getText().toString().substring(digits - 1);
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				int digits = phoneNo.getText().toString().length();
				Log.d("LENGTH", "" + digits);
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
		login.setText("Send Verification Code");
		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!TextUtils.isEmpty(phoneNo.getText().toString()) && phoneNo.isEnabled()) {
					String no = phoneNo.getText().toString().trim().replaceAll("-", "");
					if (no.length() < 10) {
						Toast.makeText(LoginActivity.this, "not valid phone number", Toast.LENGTH_SHORT).show();
						return;
					} else {
						Toast.makeText(LoginActivity.this, "passed", Toast.LENGTH_SHORT).show();
						no = "+91" + no;
						setSignIn(no);
					}


				} else if (!TextUtils.isEmpty(VerificationCode.getText().toString()) && VerificationCode.isEnabled()) {
					if (VerificationCode.getText().toString().trim().equals(mVerificationId)) {

					}
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
					Toast.makeText(getApplicationContext(), "Not a valid phone Number", Toast.LENGTH_LONG);

				} else if (e instanceof FirebaseTooManyRequestsException) {
					Toast.makeText(getApplicationContext(), "too many request made try again later", Toast.LENGTH_LONG);
				}
				phoneNo.setText("");
				phoneNo.setEnabled(true);
				VerificationCode.setText("");
				VerificationCode.setEnabled(false);
				phoneText.setText("Try again");
				login.setText("Send verification code");
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
				mResendingToken = token;
				phoneNo.setEnabled(false);
				VerificationCode.setEnabled(true);
				login.setText("verify and login");
				phoneText.setText("Verification code sent to " + phoneNo.getText().toString().trim());
				setTimer();

				// ...
			}
		};

	}

	private void setTimer() {

		new CountDownTimer(30000, 1000) {

			public void onTick(long millisUntilFinished) {
				resend.setText("00:" + millisUntilFinished / 1000);
				//here you can have your logic to set text to edittext
			}

			public void onFinish() {
				resend.setText("Resend");
				resend.setEnabled(true);

			}

		}.start();

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
							Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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

	private void resendVerificationCode(String phoneNumber,
	                                    PhoneAuthProvider.ForceResendingToken token) {
		PhoneAuthProvider.getInstance().verifyPhoneNumber(
				phoneNumber,        // Phone number to verify
				60,                 // Timeout duration
				TimeUnit.SECONDS,   // Unit of timeout
				this,               // Activity (for callback binding)
				mcallBack,         // OnVerificationStateChangedCallbacks
				token);             // ForceResendingToken from callbacks
	}
}
