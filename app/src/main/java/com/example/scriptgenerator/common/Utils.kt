package com.example.scriptgenerator.common

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

fun generatePdf(){
    val title = Paint().apply {
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
        textSize = 15f
        color = Color.BLACK
    }

    val pdfDocument = PdfDocument()
    val pageInfo = PdfDocument
        .PageInfo
        .Builder(
            720,
            1120,
            1
        )
        .create()

    val page = pdfDocument.startPage(pageInfo)

    page.canvas.drawText("This is sample document which we have created.", 0f,0f , title)
    pdfDocument.finishPage(page)

    val file = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
        System.currentTimeMillis().toString()
    )

    // writing our PDF file to that location.
    pdfDocument.writeTo(FileOutputStream(file))
    // closing our PDF file.
    pdfDocument.close()
}