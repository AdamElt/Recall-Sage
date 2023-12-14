package com.mobileapp.recallsage;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class CreationViewModel extends ViewModel {
    private List<Flashcard> flashcardList;
    private MutableLiveData<Integer> flashcardCount = new MutableLiveData<>();

    public CreationViewModel() {
        flashcardList = new ArrayList<>();
        flashcardCount.setValue(0);
    }

    public void addFlashcard(String question, String answer) { // Function to create a flashcard and add it to the list
        Flashcard flashcard = new Flashcard(question, answer);
        flashcardList.add(flashcard);

        flashcardCount.setValue(flashcardList.size());
    }

    public LiveData<Integer> getFlashcardCount() {
        return flashcardCount;
    }

    public List<Flashcard> getFlashcardList() {
        return flashcardList;
    }

    public static class Flashcard {
        private String question;
        private String answer;

        public Flashcard(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }
    }
}
