package com.training.ankocommonsdemo

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var rootView: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootView = findViewById(R.id.rootView)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            openProductActivity()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openProductActivity() {
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)
    }


    private fun openProductActivityWithOneParam() {
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("id", 7)
        startActivity(intent)

    }

    private fun openProductActivityWithTwoParams() {
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("id", 7)
        intent.putExtra("name", "android")
        startActivity(intent)

    }


    private fun openProductActivityWithFlag() {
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("id", 7)
        intent.putExtra("name", "android")
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
    }

    private fun openLink() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("http://www.google.com")
        startActivity(intent)
    }


    private fun shareContents() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Sample Subject")
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Sample Contents")
        startActivity(Intent.createChooser(shareIntent, null))
    }


    private fun shareContentsViaEmail(address: String, subject: String, text: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(intent)
    }


    private fun showToast() {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
    }


    private fun showAlert() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Title")
        alert.setMessage("Message")

        alert.setPositiveButton("Yes") { _, _ ->
            Toast.makeText(this, "Yes Clicked", Toast.LENGTH_SHORT).show()
        }
        alert.setNegativeButton("No") { _, _ ->
            Toast.makeText(this, "No Clicked", Toast.LENGTH_SHORT).show()
        }
        alert.create().show()


    }

    private fun showSnackBar() {
        Snackbar.make(rootView, "Welcome my Friend", Snackbar.LENGTH_SHORT).show()
    }


    private fun showSelector() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose an animal")

        val animals = arrayOf("horse", "cow", "camel", "sheep", "goat")
        builder.setItems(animals) { dialog, which ->
            when (which) {
                0 -> Toast.makeText(this,"horse", Toast.LENGTH_SHORT).show()
                1 -> Toast.makeText(this,"cow", Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(this,"camel", Toast.LENGTH_SHORT).show()
                3 -> Toast.makeText(this,"sheep", Toast.LENGTH_SHORT).show()
                4 -> Toast.makeText(this,"goat", Toast.LENGTH_SHORT).show()
            }
        }
        val dialog = builder.create()
        dialog.show()
    }
}
