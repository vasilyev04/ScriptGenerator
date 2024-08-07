package com.example.scriptgenerator.common

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

fun generatePdfInDownloadPath(text: String): String {
    val title = Paint().apply {
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
        textSize = 15f
        color = Color.BLACK
    }

    val pdfDocument = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(720, 1120, 1).create()

    var page = pdfDocument.startPage(pageInfo)
    var canvas = page.canvas
    val xPos = 10f
    var yPos = 25f
    val lineHeight = title.descent() - title.ascent()

    val lines = text.split("\n")

    for (line in lines) {
        var currentLine = StringBuilder()
        val words = line.split(" ")

        for (word in words) {
            val testLine = if (currentLine.isEmpty()) word else "$currentLine $word"
            val testWidth = title.measureText(testLine)

            if (testWidth > pageInfo.pageWidth - 20) {
                canvas.drawText(currentLine.toString(), xPos, yPos, title)
                currentLine = StringBuilder(word)
                yPos += lineHeight

                if (yPos + lineHeight > pageInfo.pageHeight - 20) {
                    pdfDocument.finishPage(page)
                    page = pdfDocument.startPage(pageInfo)
                    canvas = page.canvas
                    yPos = 25f
                }
            } else {
                currentLine.append(if (currentLine.isEmpty()) word else " $word")
            }
        }

        // Draw the last line of the current paragraph
        if (currentLine.isNotEmpty()) {
            canvas.drawText(currentLine.toString(), xPos, yPos, title)
            yPos += lineHeight
        }

        // Add an extra lineHeight for the new paragraph
        yPos += lineHeight

        if (yPos + lineHeight > pageInfo.pageHeight - 20) {
            pdfDocument.finishPage(page)
            page = pdfDocument.startPage(pageInfo)
            canvas = page.canvas
            yPos = 25f
        }
    }

    pdfDocument.finishPage(page)

    val file = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
        "${System.currentTimeMillis()}.pdf"
    )

    // writing our PDF file to that location.
    pdfDocument.writeTo(FileOutputStream(file))
    // closing our PDF file.
    pdfDocument.close()

    return file.absolutePath
}









