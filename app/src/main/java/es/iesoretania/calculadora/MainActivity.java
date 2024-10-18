package es.iesoretania.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import es.iesoretania.calculadora.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityMainBinding.inflate( getLayoutInflater( ) );
        setContentView( binding.getRoot( ) );

        binding.buttonOne.setOnClickListener( this );
        binding.buttonTwo.setOnClickListener( this );
        binding.buttonThree.setOnClickListener( this );
        binding.buttonFour.setOnClickListener( this );
        binding.buttonFive.setOnClickListener( this );
        binding.buttonSix.setOnClickListener( this );
        binding.buttonSeven.setOnClickListener( this );
        binding.buttonEight.setOnClickListener( this );
        binding.buttonNine.setOnClickListener( this );
        binding.buttonZero.setOnClickListener( this );

        //binding.buttonPlus.setOnClickListener();
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) findViewById( view.getId( ) );
        String text = binding.textView.getText().toString() + button.getText().toString();

        binding.textView.setText(text);
    }
}