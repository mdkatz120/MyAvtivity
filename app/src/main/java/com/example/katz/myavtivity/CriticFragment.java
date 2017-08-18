package com.example.katz.myavtivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import static com.example.katz.myavtivity.R.id.critic_fragment;
import com.example.katz.myavtivity.R;
import com.example.katz.myavtivity.src.entities.ConstValue;
import com.example.katz.myavtivity.src.entities.Critics;
import com.example.katz.myavtivity.src.model.backend.BackendFactory;

import static com.example.katz.myavtivity.R.id.fragment_container;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CriticFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CriticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CriticFragment extends Fragment {
   // Context thiscontext;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // private OnFragmentInteractionListener mListener;


    public CriticFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CriticFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CriticFragment newInstance(String param1, String param2) {
        CriticFragment fragment = new CriticFragment();
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
       View listView = getActivity().findViewById(fragment_container);



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_critic, container, false);

        final Button button4 = (Button) view.findViewById(R.id.button4);

       // thiscontext = container.getContext();

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Critics critics = new Critics();
                try {
                critics.setName(((EditText) view.findViewById(R.id.editText3)).getText().toString());
                critics.setId(Integer.parseInt(((EditText) view.findViewById(R.id.editText4)).getText().toString()));
                critics.setPassword(Integer.parseInt(((EditText) view.findViewById(R.id.editText5)).getText().toString()));
                critics.setE_mail(((EditText) view.findViewById(R.id.editText6)).getText().toString());
                critics.setTelephone(Integer.parseInt(((EditText) view.findViewById(R.id.editText7)).getText().toString()));
                critics.setAddress(((EditText) view.findViewById(R.id.editText8)).getText().toString());

                    instance.getInstance().addCritic(critics);
                    Toast.makeText(getActivity(), "ID :" + critics.getId() + "\n" + "Password :" + critics.getPassword(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("ERROR");
                    alertDialog.setMessage("One of your details is not correct");
                    alertDialog.show();
                    return;
                }
                Intent intent = new Intent(getActivity(),BooksActivity.class);
                intent.putExtra(ConstValue.getCriticObj(),critics);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
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
    }
    */
}
