package com.mobileapp.recallsage;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.mobileapp.recallsage.databinding.FragmentCreationBinding;

public class CreationFragment extends Fragment {

    private FragmentCreationBinding binding;
    private CreationViewModel creationViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        creationViewModel = new ViewModelProvider(requireActivity()).get(CreationViewModel.class);

        Button nextButton = binding.button;
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeQuestionAnswer();
            }
        });

        Button submitButton = binding.submit;
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBackToMain();
            }
        });

        creationViewModel.getFlashcardCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer flashcardCount) {
                binding.totalCards.setText("Total Cards: " + flashcardCount);
            }
        });

        return view;
    }

    private void storeQuestionAnswer() {    // Adds the question and answer from each EditText into the flashcard list
        String question = binding.question.getText().toString();
        String answer = binding.answer.getText().toString();

        if (!question.isEmpty() && !answer.isEmpty()) {
            creationViewModel.addFlashcard(question, answer);

            binding.question.setText("");
            binding.answer.setText("");

            binding.currentPairTextView.setText("Q: " + question + "\n" + "A: " + answer);
        }
    }

    private void navigateBackToMain() {
        NavHostFragment.findNavController(this).popBackStack(R.id.mainFragment, false);
    }
}
