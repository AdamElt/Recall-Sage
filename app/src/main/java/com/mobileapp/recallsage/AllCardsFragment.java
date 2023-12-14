package com.mobileapp.recallsage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class AllCardsFragment extends Fragment {

    private CreationViewModel creationViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_cards, container, false);

        creationViewModel = new ViewModelProvider(requireActivity()).get(CreationViewModel.class);
        TextView allCardsInput = view.findViewById(R.id.allCardsInput);

        displayFlashcards(allCardsInput);

        return view;
    }

    private void displayFlashcards(TextView allCardsInput) {
        List<CreationViewModel.Flashcard> flashcardList = creationViewModel.getFlashcardList();

        StringBuilder displayText = new StringBuilder();

        for (CreationViewModel.Flashcard flashcard : flashcardList) { //Accesses the flashcardList and appends it at the end of displayText with Q and A
            displayText.append("Q: ").append(flashcard.getQuestion()).append("\n")
                    .append("A: ").append(flashcard.getAnswer()).append("\n\n");
        }

        allCardsInput.setText(displayText.toString());
    }
}
