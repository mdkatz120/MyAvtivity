package com.example.katz.myavtivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.katz.myavtivity.src.entities.ConstValue;
import com.example.katz.myavtivity.src.model.backend.BackendFactory;
import com.example.katz.myavtivity.R;
import com.example.katz.myavtivity.src.entities.Customer;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CostumerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CostumerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CostumerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   // private OnFragmentInteractionListener mListener;

    public CostumerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CostumerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CostumerFragment newInstance(String param1, String param2) {
        CostumerFragment fragment = new CostumerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public BackendFactory instance;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = new BackendFactory();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_costumer, container, false);


        String[] values =
                {"Living Area", "North", "South", "Center", "Judea and Samaria", "Jerusalem",};
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);

        Button button4 = (Button) view.findViewById(R.id.button4);//-------from here if don't have you-----------

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = new Customer();
                try {
                customer.setName(((EditText) view.findViewById(R.id.editText3)).getText().toString());
                customer.setId(Integer.parseInt(((EditText) view.findViewById(R.id.editText4)).getText().toString()));
                customer.setPassword(Integer.parseInt(((EditText) view.findViewById(R.id.editText5)).getText().toString()));
                customer.setCreditCard(Integer.parseInt(((EditText) view.findViewById(R.id.editText9)).getText().toString()));
                customer.setMail(((EditText) view.findViewById(R.id.editText6)).getText().toString());
                customer.setPhoneNumber(Integer.parseInt(((EditText) view.findViewById(R.id.editText7)).getText().toString()));
                customer.setAddress(((EditText) view.findViewById(R.id.editText8)).getText().toString());
                customer.setWorkArea(((Spinner) view.findViewById(R.id.spinner)).getSelectedItem().toString());

                    instance.getInstance().addCustomer(customer);
                    Toast.makeText(getActivity(), "ID :" + customer.getId() + "\n" + "Password :" + customer.getPassword(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("ERROR");
                    alertDialog.setMessage("One of your details is not correct");
                    alertDialog.show();
                    return;
                }
                Intent intent = new Intent(getActivity(), BooksActivity.class);
                intent.putExtra(ConstValue.getCostumerObj(), customer);
                startActivity(intent);
            }
        });

        return view;
    }
/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    } */
}
