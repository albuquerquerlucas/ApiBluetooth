package br.com.prot.apibluetooth.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import br.com.prot.apibluetooth.R;

public class BuscaFragment extends Fragment {

    private ListView listaBusca;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_busca, container, false);
        initUi(view);
        return view;
    }

    private void initUi(View view){
        this.listaBusca = view.findViewById(R.id.lista_busca);
    }
}
