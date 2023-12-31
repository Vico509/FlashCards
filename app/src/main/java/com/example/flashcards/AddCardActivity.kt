package com.example.flashcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.example.flashcards.R


class AddCardActivity : AppCompatActivity() {
    //private var LaunchMainActivity = true
    private lateinit var editQuestion: EditText
    private lateinit var editAnswer: EditText
    private lateinit var saveButton: ImageView
    private lateinit var cancelButton: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        //Initialization of the View layout
        initializationViewLayout()
        // This extracts any data that was passed back from EndingActivity
        getData()

        // Save the data
        saveData()



        //Add OnClickListener for add and cancel button
        saveButton.setOnClickListener {
            saveData()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun saveData() {
        val data = Intent()

        val question = editQuestion.text.toString()
        val answer = editAnswer.text.toString()

        if (question.isNotEmpty() && answer.isNotEmpty()) {
            data.putExtra("question",question)
            data.putExtra("answer",answer)

            setResult(RESULT_OK,data)
            finish() // Close the AddCardActivity and return to MainActivity
      } //else {
//            // Show an error message in a Toast
//            Toast.makeText(
//                applicationContext,
//                "Must enter both Question and Answer!",
//                Toast.LENGTH_SHORT
//            ).show()
//        }

}

    private fun getData() {
        // Extraction des valeurs de question et réponse depuis l'intent
        editQuestion.setText(intent.getStringExtra("question") ?: "")
        editAnswer.setText(intent.getStringExtra("answer") ?: "")
    }

    private fun initializationViewLayout() {
        editQuestion = findViewById(R.id.editTextFieldQuestion)
        editAnswer = findViewById(R.id.editTextFieldAnswer)
        saveButton = findViewById(R.id.save_button_image)
        cancelButton = findViewById(R.id.cancel_button_image)
    }
}



//        fun launchComposeView() {
//            val i = Intent(this@AddCardActivity, MainActivity::class.java)
//            startActivity(i)
//        }

//        //ivToggleChoiceVisibility.setOnClickListener {
//            //LaunchMainActivity = !LaunchMainActivity
//            launchComposeView()
//        }

