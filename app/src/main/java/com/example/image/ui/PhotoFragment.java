package com.example.image.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.image.R;
import com.example.image.adapter.Adapter;
import com.example.image.services.AoiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoFragment extends Fragment {

    public List<String> imagesResponseList = new ArrayList<>();
    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        gridView = view.findViewById(R.id.gridView);
        final SwipeRefreshLayout refresh = view.findViewById(R.id.swiperefresh);
        refresh.setOnRefreshListener(() -> {
            getAllImages();
            refresh.setRefreshing(false);
        });

        getAllImages();

        gridView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Intent intent =
                    new Intent(PhotoFragment.this.getActivity(), ClickedPhotoActivity.class)
                            .putExtra("image", imagesResponseList.get(i));
            startActivity(intent);
        });

        return view;
    }

    public void getAllImages() {
        Call<List<String>> imagesResponse = AoiClient.getInterface().getAllImages();

        imagesResponse.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    imagesResponseList = response.body();
                    Adapter adapter = new Adapter(imagesResponseList, getContext());
                    gridView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), R.string.error_occured, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}