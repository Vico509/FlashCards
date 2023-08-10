package com.example.flashcards

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.flashcards.R

class MainActivity : AppCompatActivity() {
    //private var isShowingAnswers = true
    //private var lauchNewCardActivity = true
    private lateinit var flashcardQuestion : TextView
    private lateinit var flashcardAnswer : TextView
    private lateinit var addButton : ImageView
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


        flashcardQuestion.setOnClickListener {
            toggleAnswerVisibility()
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
            addCardActivityResultLauncher.launch(intent)
        }



    //        flashcardAnswer.setOnClickListener {
//            flashcardAnswer.setBackgroundResource(R.drawable.red_background)
//
//        }

//        flashcardAnswer2.setOnClickListener {
//            flashcardAnswer2.setBackgroundResource(R.drawable.green_background)
//        }


//        // Add onClickListener to the eye icon
//        ivToggleChoiceVisibility.setOnClickListener {
//            isShowingAnswers = !isShowingAnswers
//
//            if (!isShowingAnswers) {
//                ivToggleChoiceVisibility.setImageResource(R.drawable.icon_eye_lined)
//                hideAnswers()
//            }else{
//                ivToggleChoiceVisibility.setImageResource(R.drawable.icon_eye_off_lined)
//                showAnswers()
//            }
//        }
//    }

//    private fun hideAnswers(){
//        findViewById<TextView>(R.id.flashcard_answer).visibility = View.INVISIBLE
//        findViewById<TextView>(R.id.flashcard_answer1).visibility = View.INVISIBLE
//        findViewById<TextView>(R.id.flashcard_answer2).visibility = View.INVISIBLE
//    }

//    private fun showAnswers () {
//        findViewById<TextView>(R.id.flashcard_answer).visibility = View.VISIBLE
//        findViewById<TextView>(R.id.flashcard_answer1).visibility = View.VISIBLE
//        findViewById<TextView>(R.id.flashcard_answer2).visibility = View.VISIBLE




   }

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
    }

    // This extracts any data that was passed back from AddCardActivity
    private val addCardActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data

            if (data != null) {
                val question = data.extras!!.getString("question")
                val answer = data.extras!!.getString("answer")


                flashcardQuestion.text = question
                flashcardAnswer.text = answer

            }
        }
    }
}