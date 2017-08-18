package com.example.katz.myavtivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.katz.myavtivity.src.entities.ConstValue;
import com.example.katz.myavtivity.src.model.backend.BackendFactory;
import com.example.katz.myavtivity.R;
import com.example.katz.myavtivity.src.entities.Book;
import com.example.katz.myavtivity.src.entities.BookProvider;
import com.example.katz.myavtivity.src.entities.Provider;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewAddBookFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewAddBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewAddBookFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    BackendFactory instance;
    Book book ;
    BookProvider bookProvider;
    Provider provider1;
    ArrayList<Book>books = new ArrayList<Book>();
    ArrayList<BookProvider>bookProviders = new ArrayList<BookProvider>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    public NewAddBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewAddBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewAddBookFragment newInstance(String param1, String param2) {
        NewAddBookFragment fragment = new NewAddBookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = new BackendFactory();
        book = new Book();
        bookProvider = new BookProvider();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_new_add_book, container, false);
        provider1 = (Provider) getActivity().getIntent().getSerializableExtra(ConstValue.getProviderObj());
        Button button6 = (Button) view.findViewById(R.id.button6);

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    book.setName(((EditText) getView().findViewById(R.id.editBookName)).getText().toString());
                    book.setAuthor(((EditText) getView().findViewById(R.id.editAutor)).getText().toString());
                    book.setGenre(((EditText) getView().findViewById(R.id.editGener)).getText().toString());
                    book.setPageNumber(Integer.parseInt(((EditText) getView().findViewById(R.id.editPagenumber)).getText().toString()));
                    bookProvider.setPrice(Double.parseDouble(((EditText) getView().findViewById(R.id.editSummery)).getText().toString()));
                    bookProvider.setProvider(provider1);
                    bookProvider.setBook(book);
                    bookProvider.setAmount(Integer.parseInt(((EditText) getView().findViewById(R.id.editAmount)).getText().toString()));
                    new AddNewBook().execute().get();
                    getFragmentManager().popBackStackImmediate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return view;

    }

    private class AddNewBook extends AsyncTask<Void, Void, Void> {
        private String exception = "";

        @Override
        protected Void doInBackground(Void... params) {
            try {

                instance.getInstance().addBook(book);
                instance.getInstance().addBookProvider(bookProvider);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
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
