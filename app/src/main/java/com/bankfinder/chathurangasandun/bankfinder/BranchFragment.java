package com.bankfinder.chathurangasandun.bankfinder;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bankfinder.chathurangasandun.bankfinder.model.Bank;
import com.bankfinder.chathurangasandun.bankfinder.model.Branches;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BranchFragment extends Fragment implements SearchView.OnQueryTextListener{



    View view;

    private BranchAdapter branchAdapter;
    private RecyclerView recyclerView;


    private List<Branches> branchesList = new ArrayList<>();
    private BranchAdapter mAdapter;

    public BranchFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_branch, container, false);
        //recyclerView = (RecyclerView) view.findViewById(R.id.branchlist);




        /*branchAdapter = new BranchAdapter(getActivity(),allBranches);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(branchAdapter);*/

        DatabaseOpenHelper db =new DatabaseOpenHelper(getContext());

        branchesList = db.getBankBranches(Bank.selectedBank);



        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_branch);
        SearchView searchBranch = (SearchView) view.findViewById(R.id.search);

        mAdapter = new BranchAdapter(branchesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        final TextView tvNoBranches = (TextView) view.findViewById(R.id.tvNoBranches);

        searchBranch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("filter", newText);
                final List<Branches> filteredModelList = filter(branchesList, newText);
                if(filteredModelList.size() == 0){
                    tvNoBranches.setVisibility(View.VISIBLE);
                    tvNoBranches.setText("Sorry!!  There is no Matching Branch name");
                }else{
                    tvNoBranches.setVisibility(View.INVISIBLE);
                }

                mAdapter.setFilter(filteredModelList);
                return  true;
            }
        });
        mAdapter.notifyDataSetChanged();
        //prepareMovieData();


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d("branch",branchesList.get(position).getName());



                Branches branches = branchesList.get(position);
                Toast.makeText(getContext(), branches.getName() + " is selected!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), BranchDetailActivity.class);
                intent.putExtra("SELECTEDBRANCH",branches.getId());

                startActivity(intent);


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        return  view;

    }












    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("filter",newText);
        final List<Branches> filteredModelList = filter(branchesList, newText);
        mAdapter.setFilter(filteredModelList);
        return true;
    }

    private List<Branches> filter(List<Branches> models, String query) {
        query = query.toLowerCase();

        final List<Branches> filteredModelList = new ArrayList<>();
        for (Branches model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }






    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private BranchFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final BranchFragment.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }




}
