package com.mobileapp.recallsage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.mobileapp.recallsage.databinding.FragmentGameOverBinding;

public class GameOverFragment extends Fragment {

    private FragmentGameOverBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGameOverBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        TextView quizResultTextView = binding.quizResult;
        TextView end = binding.results;

        int score = GameOverFragmentArgs.fromBundle(getArguments()).getScore(); // Takes score from QuizFragment

        quizResultTextView.setText("Your Score: " + score + "%");

        if (score == 100) { // Result Checker
            end.setText("Great Job!" + "\n" + score + "%");
        } else if (score < 100) {
            quizResultTextView.setText("You can do better!" + "\n" + score + "%");
        } else {
            quizResultTextView.setText("Your Score: " + score + "%");
        }

        Button newQuizButton = binding.newQuiz;
        newQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToQuiz();
            }
        });


        binding.studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToStudy();
            }
        });
        return view;
    }

    private void navigateToQuiz() {
        NavHostFragment.findNavController(this).navigate(R.id.action_gameOverFragment_to_quizFragment);
    }

    private void navigateToStudy() {
        NavHostFragment.findNavController(this).navigate(R.id.action_gameOverFragment_to_studyFragment);
    }
}
