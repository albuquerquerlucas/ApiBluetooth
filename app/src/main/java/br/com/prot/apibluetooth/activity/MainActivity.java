package br.com.prot.apibluetooth.activity;

import android.bluetooth.BluetoothAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import br.com.prot.apibluetooth.fragment.BuscaFragment;
import br.com.prot.apibluetooth.fragment.PareadosFragment;
import br.com.prot.apibluetooth.util.Const;
import br.com.prot.apibluetooth.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtHardware, txtStatus;
    private Switch swtHabilitar;
    private Button btnDispPareados, btnBuscarDisp;

    private BluetoothAdapter btAdapter = null;
    private FragmentTransaction ft = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btAdapter = BluetoothAdapter.getDefaultAdapter();
        initUi();
    }

    @Override
    protected void onResume() {
        super.onResume();

        verificaStatusHardware();
        configComponents();
    }

    private void initUi(){
        this.txtHardware = (TextView) findViewById(R.id.txt_status_hardware);
        this.txtStatus = (TextView) findViewById(R.id.txt_status_bluetooth);
        this.swtHabilitar = (Switch) findViewById(R.id.swt_habilitar);
        this.btnDispPareados = (Button) findViewById(R.id.btn_disp_pareados);
        this.btnBuscarDisp = (Button) findViewById(R.id.btn_buscar_disp);
    }

    private void configComponents(){
        this.swtHabilitar.setOnClickListener(this);
        verificaSwith();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.swt_habilitar:
                if(swtHabilitar.isChecked()){
                    this.btAdapter.enable();
                    this.txtStatus.setText(Const.STATUS_ON);
                }else {
                    this.btAdapter.disable();
                    this.txtStatus.setText(Const.STATUS_OFF);
                }
            break;
        }
    }

    private void verificaStatusHardware(){
        if(btAdapter == null){
            this.txtHardware.setText(Const.BLUETOOTH_NAO_SUPORTADO);
        }else{
            this.txtHardware.setText(Const.BLUETOOTH_SUPORTADO);
        }
    }

    private void verificaSwith(){
        if(btAdapter.isEnabled()){
            this.txtStatus.setText(Const.STATUS_ON);
            this.swtHabilitar.setChecked(true);
            this.btAdapter.enable();
        }else{
            this.txtStatus.setText(Const.STATUS_OFF);
            this.swtHabilitar.setChecked(false);
            this.btAdapter.disable();
        }
    }

    public void dispositivosPareados(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_01, new PareadosFragment()).commit();
    }

    public void buscarDispositivos(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_01, new BuscaFragment()).commit();
    }
}
