package com.mobileapp.recallsage;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.mobileapp.recallsage.databinding.FragmentMainBinding;

public class mainFragment extends Fragment {

    private FragmentMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.creationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(mainFragment.this)
                        .navigate(R.id.action_mainFragment_to_creationFragment);
            }
        });

        binding.allFlashcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(mainFragment.this)
                        .navigate(R.id.action_mainFragment_to_allCardsFragment);
            }
        });

        binding.studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(mainFragment.this)
                        .navigate(R.id.action_mainFragment_to_studyFragment);
            }
        });

        binding.quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(mainFragment.this)
                        .navigate(R.id.action_mainFragment_to_quizFragment);
            }
        });

        return view;
    }
}
