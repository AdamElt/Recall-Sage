package com.mobileapp.recallsage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.mobileapp.recallsage.databinding.FragmentStudyBinding;

import java.util.List;

public class StudyFragment extends Fragment {

    private FragmentStudyBinding binding;

    private CreationViewModel creationViewModel;
    private List<CreationViewModel.Flashcard> flashcards;
    private int currentCardIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStudyBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        creationViewModel = new ViewModelProvider(requireActivity()).get(CreationViewModel.class);
        flashcards = creationViewModel.getFlashcardList();

        binding.studyAnswer.setVisibility(View.GONE); // Set initial answer to invisible

        if (!flashcards.isEmpty()) {
            displayFlashcard(currentCardIndex);
        }

        View.OnClickListener toggleAnswerClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleAnswerVisibility();
            }
        };

        binding.studyQuestion.setOnClickListener(toggleAnswerClickListener);
        binding.studyAnswer.setOnClickListener(toggleAnswerClickListener);

        binding.studyFinish.setOnClickListener(new View.OnClickListener() { // If you click the finish button you go back to the main menu
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(StudyFragment.this).navigate(R.id.action_studyFragment_to_mainFragment);
            }
        });

        binding.studyNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCardIndex = (currentCardIndex + 1) % flashcards.size();
                displayFlashcard(currentCardIndex);
                hideAnswer();
            }
        });

        return view;
    }

    private void displayFlashcard(int index) { // Get the next questiona and answer
        binding.studyQuestion.setText(flashcards.get(index).getQuestion());
        binding.studyAnswer.setText(flashcards.get(index).getAnswer());
    }

    private void toggleAnswerVisibility() { // Change visibility of the answer
        if (binding.studyAnswer.getVisibility() == View.VISIBLE) {
            binding.studyAnswer.setVisibility(View.GONE);
        } else {
            binding.studyAnswer.setVisibility(View.VISIBLE);
        }
    }

    private void hideAnswer() {
        binding.studyAnswer.setVisibility(View.GONE);
    } // Rehide
}
