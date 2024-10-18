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
    private Boolean ShouldReset = true;

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

        binding.buttonPlus.setOnClickListener( this );
        binding.buttonMinus.setOnClickListener( this );
        binding.buttonMultiply.setOnClickListener( this );
        binding.buttonDivide.setOnClickListener( this );

        binding.buttonEquals.setOnClickListener( this );
    }

    @Override
    public void onClick( View view ) {
        Button button = (Button) findViewById( view.getId( ) );

        String text = "";
        if( ShouldReset )
        {
            text = button.getText( ).toString();
            ShouldReset = false;
        }
        else
            text = binding.textView.getText( ).toString( ) + button.getText( ).toString( );

        String[ ] simbolos = { "+", "-", "*", "/" };
        String simboloGuardado = "";

        if( button.getText( ).toString( ).equals( "=" ) )
        {
            text = text.substring( 0, text.length( ) - 1 );

            for( String simbolo : simbolos )
            {
                if( text.contains( simbolo ) )
                {
                    simboloGuardado = simbolo;
                    text = text.replace( simbolo, " " );
                    break;
                }
            }

            String[ ] parts = text.split( " " );

            if( parts.length == 2 )
            {
                try {
                    double num1 = Double.parseDouble( parts[ 0 ] );
                    double num2 = Double.parseDouble( parts[ 1 ] );
                    double resultado = 0;

                    switch( simboloGuardado )
                    {
                        case "+":
                        {
                            resultado = num1 + num2;
                            break;
                        }
                        case "-":
                        {
                            resultado = num1 - num2;
                            break;
                        }
                        case "*":
                        {
                            resultado = num1 * num2;
                            break;
                        }
                        case "/":
                        {
                            if( num2 != 0 )
                                resultado = num1 / num2;
                            else
                            {
                                binding.textView.setText( "División por 0" );
                                ShouldReset = true;
                                return;
                            }

                            break;
                        }
                    }

                    //Mostramos el resultado
                    binding.textView.setText( String.valueOf( resultado ) );
                    ShouldReset = true;
                }
                catch ( NumberFormatException e ) {
                    ShouldReset = true;
                    binding.textView.setText( "Formato inválido" );
                }
            } else {
                ShouldReset = true;
                binding.textView.setText( "Expresión inválida" );
            }
        }
        else {
            // Si no es el botón "=", solo actualizamos el texto en el TextView
            binding.textView.setText( text );
        }
    }
}