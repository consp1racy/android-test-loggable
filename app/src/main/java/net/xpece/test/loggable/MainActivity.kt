package net.xpece.test.loggable

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.PrintWriter
import java.io.StringWriter

class MainActivity : Activity() {

    companion object {
        const val SHORT_TAG = "ASDF"
        const val LONG_TAG = "The wheels on the bus go round and round, round and round, round and round"
    }

    @SuppressLint("LongLogTag", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonShortTag.setOnClickListener {
            runAndLogResult {
                Log.w(SHORT_TAG, "Hello, World!")
            }
        }
        buttonLongTag.setOnClickListener {
            runAndLogResult {
                Log.w(LONG_TAG, "Hello, World!")
            }
        }
        buttonShortTagWithCheck.setOnClickListener {
            runAndLogResult {
                if (Log.isLoggable(SHORT_TAG, Log.WARN)) {
                    Log.w(SHORT_TAG, "Hello, World!")
                }
            }
        }
        buttonLongTagWithCheck.setOnClickListener {
            runAndLogResult {
                if (Log.isLoggable(LONG_TAG, Log.WARN)) {
                    Log.w(LONG_TAG, "Hello, World!")
                }
            }
        }
    }

    protected inline fun runAndLogResult(block: () -> Unit) {
        text.text = "Working..."
        try {
            block()
            text.text = "OK"
        } catch (ex: Throwable) {
            val sw = StringWriter(256)
            val pw = PrintWriter(sw)
                ex.printStackTrace(pw)
            text.text = sw.buffer.toString()
        }
    }
}
