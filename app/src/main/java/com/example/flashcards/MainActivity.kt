package com.example.flashcards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var isShowingAnswers = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // The Answer textView and the eyes icons layout
        //val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)
        //val flashcardAnswer1 = findViewById<TextView>(R.id.flashcard_answer1)
        val flashcardAnswer2 = findViewById<TextView>(R.id.flashcard_answer2)
        val ivToggleChoiceVisibility = findViewById<ImageView>(R.id.toggle_choices_visibility)



        flashcardAnswer.setOnClickListener {
            flashcardAnswer.setBackgroundResource(R.drawable.red_background)

        }

        flashcardAnswer2.setOnClickListener {
            flashcardAnswer2.setBackgroundResource(R.drawable.green_background)
        }


        // Add onClickListener to the eye icon
        ivToggleChoiceVisibility.setOnClickListener {
            isShowingAnswers = !isShowingAnswers

            if (!isShowingAnswers) {
                ivToggleChoiceVisibility.setImageResource(R.drawable.icon_eye_lined)
                hideAnswers()
            }else{
                ivToggleChoiceVisibility.setImageResource(R.drawable.icon_eye_off_lined)
                showAnswers()
            }
        }
    }

    private fun hideAnswers(){
        findViewById<TextView>(R.id.flashcard_answer).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.flashcard_answer1).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.flashcard_answer2).visibility = View.INVISIBLE

    }

    private fun showAnswers () {
        findViewById<TextView>(R.id.flashcard_answer).visibility = View.VISIBLE
        findViewById<TextView>(R.id.flashcard_answer1).visibility = View.VISIBLE
        findViewById<TextView>(R.id.flashcard_answer2).visibility = View.VISIBLE
    }

}