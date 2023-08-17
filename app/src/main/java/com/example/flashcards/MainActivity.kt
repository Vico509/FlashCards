package com.example.flashcards

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    //private var isShowingAnswers = true
    //private var lauchNewCardActivity = true
    private lateinit var flashcardQuestion : TextView
    private lateinit var flashcardAnswer : TextView
    private lateinit var addButton : ImageView
    private lateinit var flashcardDatabase: FlashcardDatabase
    private  lateinit var  nextButton : ImageView
    private var allFlashcards = mutableListOf<Flashcard>()
    private var currentCardDisplayIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // The Answer textView and the eyes icons layout
       // val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        //val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)
        //val flashcardAnswer1 = findViewById<TextView>(R.id.flashcard_answer1)
       // val flashcardAnswer2 = findViewById<TextView>(R.id.flashcard_answer2)
        //val ivToggleChoiceVisibility = findViewById<ImageView>(R.id.add_button_image)

        // Look up the views in layout
        initializeViews()

        // Initialization of flashcardDatabase variable
        flashcardDatabase = FlashcardDatabase(this)
        allFlashcards = flashcardDatabase.getAllCards().toMutableList()

        nextButton.setOnClickListener {
            // don't try to go to next card if you have no cards to begin with
            if (allFlashcards.size == 0) {
                // return here, so that the rest of the code in this onClickListener doesn't execute
                return@setOnClickListener
            }
            // return here, so that the rest of the code in this onClickListener doesn't execute
            currentCardDisplayIndex++

            // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
            if (currentCardDisplayIndex >= allFlashcards.size){
                Snackbar.make(
                    flashcardQuestion,// This should be the TextView for displaying your flashcard question
                    "You've reached the end of the cards, going back to start.",
                    Snackbar.LENGTH_SHORT)
                    .show()
                currentCardDisplayIndex = 0
            }

            // set the question and answer TextViews with data from the database
            allFlashcards = flashcardDatabase.getAllCards().toMutableList()
            val (question,answer) = allFlashcards[currentCardDisplayIndex]

            flashcardAnswer.text = answer
            flashcardQuestion.text = question

            // Reinitialize the inital State
            toggleAnswerVisibility()
        }



        // if condition for verify if the list of card is empty or not, if the list of card not empty you can display the memory save.
        if (allFlashcards.size > 0){
            flashcardQuestion.text = allFlashcards[0].question
            flashcardAnswer.text = allFlashcards[0].answer
        }

        flashcardQuestion.setOnClickListener {
            toggleAnswerVisibility()
        }


//
//


//
//        fun launchComposeView() {
//            val i = Intent(this,AddCardActivity::class.java)
//            resultLauncher.launch(i)
//        }
//        //Add OnClickListenr when the add button is click
//        ivToggleChoiceVisibility.setOnClickListener {
//            //lauchNewCardActivity = !lauchNewCardActivity
//            launchComposeView()
//        }

        addButton.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            resultLauncher.launch(intent)
        }

   }



//    private fun startAddCardActivity() {
//        // create an Intent to start AddCardActivity
//        val intent = Intent(this,AddCardActivity::class.java)
//
//        // Get the strings from the TextViews
//        val currentQuestion = flashcardQuestion.text.toString()
//        val currentAnswer = flashcardAnswer.text.toString()
//
//        // Add the current question and answer as extras to the Intent
//        intent.putExtra("question",currentQuestion)
//        intent.putExtra("answer",currentAnswer)
//        addCardActivityResultLauncher.launch(intent)
//    }

//    private fun startAddCardActivity() {
//        val intent = Intent(this,AddCardActivity::class.java)
//        val question = flashcardQuestion.text
//        val answer = flashcardAnswer.text
//        intent.putExtra("question", question)
//        intent.putExtra("answer", answer)
//        addCardActivityResultLauncher.launch(intent)
//    }

    private fun toggleAnswerVisibility() {
        if (flashcardAnswer.visibility == View.VISIBLE) {
            flashcardAnswer.visibility = View.INVISIBLE
            flashcardQuestion.visibility = View.VISIBLE
        } else {
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardAnswer.visibility = View.VISIBLE
        }
    }

    private fun initializeViews() {
        flashcardQuestion = findViewById(R.id.flashcard_question)
        flashcardAnswer = findViewById(R.id.flashcard_answer)
        addButton = findViewById(R.id.add_button_image)
        nextButton = findViewById(R.id.next_button_image)
    }

    // This extracts any data that was passed back from AddCardActivity
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data

            if (data != null) {
                val question = data.extras?.getString("question")
                val answer = data.extras?.getString("answer")

                // Log the value of the strings for easier debugging
                Log.i("MainActivity", "question: $question")
                Log.i("MainActivity", "answer: $answer")

                flashcardQuestion.text = question
                flashcardAnswer.text = answer


                // Save newly created flashcard to database
                if (question !=null && answer !=null) {
                    flashcardDatabase.insertCard(Flashcard(question,answer))
                    // Update set of flashcards to include new card
                    allFlashcards = flashcardDatabase.getAllCards().toMutableList()

                } else {
                    Log.e("TAG","Missing question or answer to input into database. Question is $question and answer is $answer ")

                }

            } else {
                Log.i("MainActivity","Returned null data from AddCardActivity")
            }
        }
    }
}

//        flashcardAnswer.setOnClickListener {
//            flashcardAnswer.visibility = View.INVISIBLE
//            flashcardQuestion.visibility = View.VISIBLE
//        }

//        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//
//            // This code is executed in StartingActivity after we come back from EndingActivity
//
//            // This extracts any data that was passed back from EndingActivity
//            val data: Intent? = result.data
//            // ToDo: Execute more code here
//
//        }