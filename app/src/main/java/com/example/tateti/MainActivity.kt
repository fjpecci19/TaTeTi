package com.example.tateti

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var botones: List<Button>
    private lateinit var reiniciar: Button
    private var c = 0
    private lateinit var botonx: Button
    private lateinit var botono: Button
    private lateinit var win: TextView
    private var gameover = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = Color.parseColor("#8B0000")

        win = findViewById(R.id.winner)

        botones = listOf(
            findViewById(R.id.boton1),
            findViewById(R.id.boton2),
            findViewById(R.id.boton3),
            findViewById(R.id.boton4),
            findViewById(R.id.boton5),
            findViewById(R.id.boton6),
            findViewById(R.id.boton7),
            findViewById(R.id.boton8),
            findViewById(R.id.boton9)
        )

        botonx = findViewById(R.id.botonx)
        botono = findViewById(R.id.botono)

        botonx.setOnClickListener {
            desactivar()
            c = 1
        }
        botono.setOnClickListener {
            desactivar()
            c = 2
        }

        reiniciar = findViewById(R.id.boton_jugar)
        reiniciar.setOnClickListener {
            for (boton in botones) {
                boton.text = ""
                boton.isEnabled = true
                c = 1
                botonx.isEnabled = true
                botono.isEnabled = true
            }
            c = 0
            win.text = getString(R.string.eleccion3)
        }

        for (boton in botones) {
            boton.setOnClickListener {
                if (c != 0) {
                    if (c == 1) {
                        boton.text = getString(R.string.x)
                        c = 2
                        boton.isEnabled = false
                    } else {
                        boton.text = getString(R.string.o)
                        c = 1
                        boton.isEnabled = false
                    }
                    ganador()
                } else {
                    win.text = getString(R.string.eleccion2)
                }
            }
        }
    }

    private fun desactivar(){
        reiniciar.text = getString(R.string.Reiniciar)
        botonx.isEnabled = false
        botono.isEnabled = false
        win.text = ""
    }

    private fun ganador() {
        val board = Array(3) { IntArray(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                when (botones[i * 3 + j].text.toString()) {
                    getString(R.string.x) -> {
                        board[i][j] = 1
                    }
                    getString(R.string.o) -> {
                        board[i][j] = 2
                    }
                    else -> {
                        board[i][j] = 0
                    }
                }
            }
        }

        for (i in 0..2) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                val ganador = if (board[i][0] == 1) "X" else "O"
                val g = getString(R.string.ganador_mensaje)
                val r = "$g $ganador"
                win.text = r
                c = 0
                gameover = true
                return
            }
        }

        for (j in 0..2) {
            if (board[0][j] != 0 && board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
                val ganador = if (board[0][j] == 1) "X" else "O"
                val g = getString(R.string.ganador_mensaje)
                val r = "$g $ganador"
                win.text = r
                c = 0
                gameover = true
                return
            }
        }

        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            val ganador = if (board[0][0] == 1) "X" else "O"
            val g = getString(R.string.ganador_mensaje)
            val r = "$g $ganador"
            win.text = r
            c = 0
            gameover = true
            return
        }

        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            val ganador = if (board[0][2] == 1) "X" else "O"
            val g = getString(R.string.ganador_mensaje)
            val r = "$g $ganador"
            win.text = r
            c = 0
            gameover = true
            return
        }

        if (!board.any { it.contains(0) }) {
            win.text = getString(R.string.empate)
            c = 0
            gameover = true
        }
    }
}
