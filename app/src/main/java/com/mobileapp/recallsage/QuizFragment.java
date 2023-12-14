package com.mobileapp.recallsage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.mobileapp.recallsage.databinding.FragmentQuizBinding;
import com.mobileapp.recallsage.CreationViewModel;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizFragment extends Fragment {

    private FragmentQuizBinding binding;
    private CreationViewModel creationViewModel;
    private int totalPoints = 0;
    private int totalQuestions = 0;

    public int score = 0;
    private List<CreationViewModel.Flashcard> flashcards;
    private int counter = 0;
    private Random random;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        creationViewModel = new ViewModelProvider(requireActivity()).get(CreationViewModel.class); // Instance of ViewModel
        flashcards = creationViewModel.getFlashcardList(); // Create the flashcard list
        random = new Random();

        binding.quizNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                showNextQuestion();
            }
        });

        totalQuestions = flashcards.size();

        if (!flashcards.isEmpty()) {
            shuffleFlashcards();
            showNextQuestion();
        }


        return view;
    }

    private void shuffleFlashcards() {
        Collections.shuffle(flashcards, random);
    } // Use random to shuffle the flashcards so every quiz is different

    private void showNextQuestion() { // Get the next question
        if (counter < totalQuestions) {
            CreationViewModel.Flashcard flashcard = flashcards.get(counter);
            String nextQuestion = flashcard.getQuestion();
            binding.studyQuestion.setText(nextQuestion);
            binding.quizAnswer.setText("");  // Reset the EditText
            counter++;
        } else {
            checkGameOver();
        }
    }


    private void checkAnswer() { //Compare answers to see if its correct then add to totalPoints
        String answer = binding.quizAnswer.getText().toString().trim();
        if (counter > 0 && counter <= totalQuestions) {
            CreationViewModel.Flashcard flashcard = flashcards.get(counter - 1);
            String correctAnswer = flashcard.getAnswer().trim();
            if (answer.equalsIgnoreCase(correctAnswer)) {
                totalPoints++;
            }
        }
    }

    private void checkGameOver() { // calculate score and send it to GameOverFragment
        score = (totalPoints * 100) / totalQuestions;
        NavController navController = NavHostFragment.findNavController(this);
        QuizFragmentDirections.ActionQuizFragmentToGameOverFragment action =
                QuizFragmentDirections.actionQuizFragmentToGameOverFragment(score);
        navController.navigate(action);
    }
}
